package gateway

import (
	"encoding/json"
	"io/ioutil"
	"log"
	"net/http"
	"os"
	"strings"
	"time"
)

var gatewayMap map[string]string

type endpoint struct {
	Endpoints map[string]string `json:"endpoints"`
}

// init Initialites the Gateway
func init() {
	log.Println("Getting config from Config Server...")

	// Get the profile environment variable
	profile, found := os.LookupEnv("profile")
	if !found {
		// Try to get the uppercase environment variable name
		profileUpper, foundUpper := os.LookupEnv("PROFILE")
		if !foundUpper {
			log.Println("Profile environment variable hasn´t been found. Setting profile to dev.")
			profile = "dev"
		} else {
			profile = profileUpper
		}
	}

	// Build config URL
	url := "http://localhost:9000/gateway-" + strings.ToLower(profile) + ".json"

	// Create Http client with 5s timeout
	var netClient = &http.Client{
		Timeout: time.Second * 5,
	}
	resp, err := netClient.Get(url)
	if err != nil {
		log.Println(err)
		log.Fatalln("Error obtaining config from ConfigServer!")
		os.Exit(1)
	}
	defer resp.Body.Close()

	var endpoints endpoint
	json.NewDecoder(resp.Body).Decode(&endpoints)

	gatewayMap = endpoints.Endpoints

	log.Println("Config obtained!")
}

// HandleRequest Handles requests and redirect them to the proper server
func HandleRequest(w http.ResponseWriter, request *http.Request) {
	url := request.URL.String()
	reqMethod := request.Method

	header := request.Header["User-Agent"]
	log.Println("Received " + request.RemoteAddr + ": " + reqMethod + " " + url + " - " + header[0])

	client := &http.Client{}

	for key, value := range gatewayMap {
		if key == url {
			// Dispatch rest request to value
			log.Println("Dispatching request to " + value)
			reqMethod := request.Method
			req, reqError := http.NewRequest(reqMethod, value, request.Body)
			if reqError != nil {
				log.Println(reqError)
			}

			resp, err := client.Do(req)

			if err != nil {
				log.Println(reqError)
			} else {
				responseData, err := ioutil.ReadAll(resp.Body)
				if err != nil {
					log.Fatal(err)
				}
				w.Write(responseData)
			}
		}
	}
}

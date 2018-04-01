package main

import (
	"log"
	"net/http"

	"nestis/API-gateway/gateway"
)

func main() {
	http.HandleFunc("/", gateway.HandleRequest)
	log.Println("API Gateway up!")
	log.Fatal(http.ListenAndServe(":8090", nil))
}

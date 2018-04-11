package main

import (
	"log"
	"net/http"

	"nestis/api-gateway/gateway"
)

func main() {
	http.HandleFunc("/", gateway.HandleRequest)
	log.Println("API Gateway up!")
	log.Fatal(http.ListenAndServe(":8080", nil))
}

FROM golang:1.10-alpine as builder
WORKDIR /go/src/nestis/api-gateway
COPY . ./
CMD export GOPATH="/go/src"
RUN go build -o gateway-app

FROM alpine:latest
COPY --from=builder /go/src/nestis/api-gateway/gateway-app .
ENTRYPOINT ./gateway-app

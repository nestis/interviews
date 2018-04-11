FROM golang:1.10-alpine
COPY . /go/src/nestis/api-gateway
WORKDIR /go/src/nestis/api-gateway
RUN export GOPATH="/go/src"
RUN go build -o gateway-app
CMD ./gateway-app
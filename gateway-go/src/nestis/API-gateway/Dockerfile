FROM golang:1.10-alpine
COPY . /go/src/github.com/nestis/interview-middleware-go
WORKDIR /go/src/github.com/nestis/interview-middleware-go
RUN go build
CMD ./interview-middleware-go
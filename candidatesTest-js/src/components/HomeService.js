import Rx from 'rx-lite';
export class HomeService {

    http;

    constructor(http) {
        this.http = http;
    }

    getTest(testToken) {
        let response = {};
        const url = `http://localhost:8081/test/${testToken}`;
        return Rx.Observable.fromPromise(this.http.get(url)).flatMap(data => {
            response = { ...data};
            const questionUrl = `http://localhost:8082/questions`;
            return Rx.Observable.fromPromise(this.http.post(questionUrl, data.questions));
        }).catch(error => { 
            return Rx.Observable.throw({ status: error.status, ...error});
        }).map(data => {
            response.questions = data;
            return response;
        });
    }
}
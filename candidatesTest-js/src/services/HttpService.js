import Vue from 'vue';

class HttpService {

    get(url) {
        return new Promise((resolve, reject) => {
            fetch(url, {
                method: 'GET'
            }).then(response => {
                let res;
                if (response.status === 200) {
                    resolve(response.json());
                } else {
                    reject(response);
                }
            }, error => reject(error.json()))
        });
    }

    post(url, body) {
        return fetch(url, {
            headers: { 'Content-Type': 'application/json'},
            method: 'POST',
            body: JSON.stringify(body)
        }).then(response => response.json(),
            error => error.json())
    }
}
export default new HttpService()

Vue.mixin({
    beforeCreate() {
        if (this.$options.http) {
            this.$http = this.$options.http;
        } else {
            this.$http = (this.$parent && this.$parent.$http) || this
        }
    }
});

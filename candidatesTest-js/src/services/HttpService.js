import Vue from 'vue';

class HttpService {

    get(url) {
        return fetch(url, {
            method: 'GET'
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

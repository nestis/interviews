import 'materialize-css/dist/css/materialize.css';
import 'materialize-css/dist/js/materialize';
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import http from './services/HttpService'
import './registerServiceWorker'

Vue.config.productionTip = false
console.log(process.env)
new Vue({
  router,
  http,
  store,
  render: h => h(App)
}).$mount('#app')

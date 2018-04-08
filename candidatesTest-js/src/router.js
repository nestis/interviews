import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Test from './views/Test.vue'
import Page404 from './views/404.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/home?token=testToken'
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/test',
      name: 'test',
      component: Test
    }, {
      path: '/404',
      name: '404',
      component: Page404
    }
  ]
})

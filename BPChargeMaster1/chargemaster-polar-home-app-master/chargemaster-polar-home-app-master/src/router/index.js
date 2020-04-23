import Vue from 'vue'
import Router from 'vue-router'
//import appState from '../services/appState';
import routes from '@/router/routes'
Vue.use(Router)

export default new Router({
    // beforeEach: (to, from, next) => {
    //   if (!appState.apiKey) {
    //     next('/login')
    //   } else {
    //     next()
    //   }
    // },
    routes
})

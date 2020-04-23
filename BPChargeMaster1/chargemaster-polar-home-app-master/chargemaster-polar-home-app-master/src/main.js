require('typeface-montserrat')
import Vue from 'vue'
import '@/plugins/vuetify'
import App from '@/App.vue'
import router from '@/router'
import '@/stylus/main.styl'
import EventBus from '@/event-bus'

Vue.config.productionTip = false

const init = () => {
    new Vue({
        router,
        render: h => h(App)
    }).$mount('#app')
}

document.addEventListener('deviceready', () => {
    init()

    document.addEventListener('offline', onOffline, false)
    document.addEventListener('online', onOnline, false)
})

const isCordovaApp = typeof window.cordova !== 'undefined'
if (!isCordovaApp) {
    document.dispatchEvent(new CustomEvent('deviceready', {}))
}

function onOffline() {
    EventBus.$emit('spin', true)
    EventBus.$emit('notify', {
        message: 'Internet access lost',
        type: 'warning',
        timeout: 0
    })
}

function onOnline() {
    EventBus.$emit('spin', false)
    EventBus.$emit('notify', {
        message: 'Internet access is back',
        type: 'success'
    })
}

import Contact from '@/pages/Contact.vue'
import { shallowMount, createLocalVue } from '@vue/test-utils'
import Vue from 'vue'
import Vuetify from 'vuetify'
import { APPVERSION } from '@/helpers/appVersion'
import appState from '@/services/appState'

const localVue = createLocalVue()
localVue.config.productionTip = false
Vue.use(Vuetify) // Can't use Vuetify on localVue, not supported currently

appState.loginInfo = {
    smartcharger: false,
    consumer: {
        apiKey: 'any-thing-will-do',
        name: 'Tester'
    },
    serial: 'ABCD123'
}

describe('Contact.vue', () => {
    it('calls the appState to get login info on mounting', async () => {
        const wrapper = shallowMount(Contact, {
            localVue: localVue
        })

        expect(wrapper.vm.appVersion).toBe(APPVERSION)
        expect(wrapper.vm.serialNo).toBe('ABCD123')
    })
})

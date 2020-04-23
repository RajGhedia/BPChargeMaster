import Account from '@/pages/Account.vue'
import { shallowMount, createLocalVue } from '@vue/test-utils'
import Vue from 'vue'
import Vuetify from 'vuetify'
import appState from '@/services/appState'
import routes from '@/router/routes'
import VueRouter from 'vue-router'

const localVue = createLocalVue()
localVue.config.productionTip = false
Vue.use(Vuetify) // Can't use Vuetify on localVue, not supported currently
localVue.use(VueRouter)

describe('Account.vue', () => {
    it('populates fields from appState when mounted', async () => {
        appState.loginInfo = {
            smartcharger: false,
            consumer: {
                apiKey: 'any-thing-will-do',
                name: 'Tester'
            },
            serial: 'ABCD123'
        }

        const wrapper = getComponent()
        expect(wrapper.vm.username).toBe('Tester')
        expect(wrapper.vm.serialNo).toBe('ABCD123')
    })

    // TODO: Test that you are logged out automatically if the service returns unauthorized
    // TODO: Test that data is populated from a service call
})

// TODO: Test that the service has been called to save data when the save button is clicked

describe('logout button', () => {
    it('redirects to login page when button is clicked', async () => {
        const wrapper = getComponent()
        const button = wrapper.find('#logoutButton')
        button.trigger('click')
        localVue.nextTick(() => {
            expect(wrapper.vm.$route.path).toBe('/login')
        })
    })

    it('is hidden when in update mode', async () => {
        const wrapper = getComponent()
        const button = wrapper.find('#logoutButton')
        wrapper.vm.updating = true
        expect(button.exists()).toBe(false)
    })

    // TODO: Add a notification when logging out?
    // TODO: Add behaviour for calling logout API and reseting the local state
})

describe('cancel button', () => {
    it('switches to read only mode when button is clicked', async () => {
        window.scrollTo = jest.fn()
        const wrapper = getComponent({ resetFormValidation: jest.fn() })
        wrapper.vm.updating = true
        expect(wrapper.vm.updating).toBe(true)
        const button = wrapper.find('#cancelButton')
        button.trigger('click')
        localVue.nextTick(() => {
            expect(wrapper.vm.updating).toBe(false)
            expect(window.scrollTo).toHaveBeenCalled()
        })
    })

    it('is hidden when in readonly mode', async () => {
        const wrapper = getComponent()
        wrapper.vm.updating = false
        const button = wrapper.find('#cancelButton')
        expect(button.exists()).toBe(false)
    })
})

describe('edit button', () => {
    it('switches to update mode when in readonly mode', async () => {
        const wrapper = getComponent({ reValidateForm: jest.fn() })

        const button = wrapper.find('#editButton')
        wrapper.vm.updating = false
        button.trigger('click')
        localVue.nextTick(() => {
            wrapper.vm.updating = true
        })
    })

    it('is calls saveData when in update mode and form is valid', async () => {
        const onClick = jest.fn()
        const wrapper = getComponent({ saveData: onClick })

        wrapper.setData({ updating: true, isValid: true })

        const button = wrapper.find('#editButton')
        button.trigger('click')
        localVue.nextTick(() => {
            expect(onClick).toHaveBeenCalled()
        })
    })

    it('is does not call saveData when in update mode but form is invalid', async () => {
        const onClick = jest.fn()
        const wrapper = getComponent({ saveData: onClick })
        wrapper.setData({ updating: true, isValid: false })
        const button = wrapper.find('#editButton')
        button.trigger('click')
        localVue.nextTick(() => {
            expect(onClick).not.toHaveBeenCalled()
        })
    })
})

function getComponent(withMethods = {}) {
    const router = new VueRouter(routes)

    return shallowMount(Account, {
        localVue: localVue,
        router,
        methods: withMethods
    })
}

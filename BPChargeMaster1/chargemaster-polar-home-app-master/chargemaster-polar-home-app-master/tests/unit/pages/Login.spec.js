import Login from '@/pages/Login.vue'
import { shallowMount, mount, createLocalVue } from '@vue/test-utils'
import Vue from 'vue'
import Vuetify from 'vuetify'
import EventBus from '@/event-bus'
import appState from '@/services/appState'
import routes from '@/router/routes'
import VueRouter from 'vue-router'
import axios from 'axios'

const localVue = createLocalVue()
localVue.config.productionTip = false
Vue.use(Vuetify) // Can't use Vuetify on localVue, not supported currently
localVue.use(VueRouter)

describe('Login.vue', () => {
    it('sets data model from inputs and tests rules', async () => {
        // Uses mount rather than shallow as we are testing vuetify components
        const wrapper = mount(Login, {
            localVue: localVue
        })

        const expectedUsername = 'andrew.johns@bjss.com'
        const expectedPassword = 'P4ssw0rd'

        setElement(wrapper, '#usernameInput', expectedUsername)
        setElement(wrapper, '#passwordInput', expectedPassword)

        expect(wrapper.vm.username).toBe(expectedUsername)
        expect(wrapper.vm.password).toBe(expectedPassword)
        expect(wrapper.vm.isValid).toBe(true)

        wrapper.destroy()
    })
})

describe('Login button', () => {
    it('it is disabled by default', () => {
        const wrapper = getComponent()

        expect(wrapper.vm.isValid).toBe(false)
        expect(wrapper.find('#loginButton').props().disabled).toBeTruthy()
    })

    it('it is enabled if validation succeeds', () => {
        const wrapper = getComponent()

        wrapper.vm.isValid = true
        expect(wrapper.find('#loginButton').props().disabled).not.toBeTruthy()
    })

    it('calls loginClicked if it is enabled when clicked', () => {
        const onClick = jest.fn()
        const wrapper = getComponent()
        wrapper.setMethods({ loginClicked: onClick })

        wrapper.vm.isValid = true
        const button = wrapper.find('#loginButton')

        expect(button.props().disabled).not.toBeTruthy()
        button.trigger('submit')
        expect(onClick).toHaveBeenCalled()
    })

    it('does not call loginClicked if it is disabled when clicked', () => {
        const onClick = jest.fn()
        const wrapper = getComponent()
        wrapper.setMethods({ loginClicked: onClick })

        expect(wrapper.find('#loginButton').props().disabled).toBeTruthy()
        expect(onClick).not.toHaveBeenCalled()
    })
})

describe('Forgot password link', () => {
    it('calls InAppBrowser.open when clicked', () => {
        const wrapper = getComponent()

        window.cordova = {
            InAppBrowser: {
                open: jest.fn()
            }
        }

        wrapper.find('#forgotPasswordLink').trigger('click')
        expect(window.cordova.InAppBrowser.open).toHaveBeenCalledWith(
            process.env.VUE_APP_RESET_PASSWORD_URL,
            '_system'
        )
    })
})

describe('login', () => {
    it('calls the login service and handles a successful response', async () => {
        const wrapper = getComponent()

        var os = wrapper.vm.login('test', 'test')

        axios.mockSuccess({
            status: 200,
            data: {
                code: 100,
                message: '',
                data: {
                    statusText: 'API KEY NOT SET IN HEADER',
                    consumer: {
                        apiKey: 'aBcDdeFghiJKlmNopQrStUCYZz'
                    }
                }
            }
        })

        os.then(res => {
            expect(res.success).toBe(true)
        })
    })

    it('calls the login service and handles an error response', async () => {
        const wrapper = getComponent()

        var os = wrapper.vm.login('test', 'test')

        axios.mockError({
            status: 401,
            message: 'server error'
        })

        os.then(res => {
            expect(res.success).toBe(false)
            expect(res.loginError).toBe('Unexpected server error')
        })
    })
})

describe('loginClicked', () => {
    it('emits a notify error event when login not successful', async () => {
        const mockLogin = jest.fn(() => {
            return Promise.resolve({
                success: false,
                loginError: 'Mocked response from login service'
            })
        })

        const wrapper = getComponent()
        wrapper.setMethods({ login: mockLogin })

        const mockEmitMethod = jest.fn()
        EventBus.$emit = mockEmitMethod

        wrapper.vm.username = 'a'
        wrapper.vm.password = 'b'

        await wrapper.vm.loginClicked()

        expect(mockLogin).toHaveBeenCalledWith('a', 'b')
        expect(mockEmitMethod).toHaveBeenCalledWith('notify', {
            message: 'Mocked response from login service',
            type: 'error'
        })
    })

    it('emits a notify error event when user is not a smartcharge user', async () => {
        const mockLogin = jest.fn(() => {
            return Promise.resolve({
                success: true
            })
        })

        appState.loginInfo = {
            smartcharger: false,
            consumer: {
                apiKey: 'any-thing-will-do'
            }
        }

        const wrapper = getComponent()
        wrapper.setMethods({ login: mockLogin })

        const mockEmitMethod = jest.fn()
        EventBus.$emit = mockEmitMethod

        wrapper.vm.username = 'a'
        wrapper.vm.password = 'b'

        await wrapper.vm.loginClicked()

        expect(mockEmitMethod).toHaveBeenCalledWith('notify', {
            message:
                'Unfortunately this app is only available to Chargemaster Smartcharge customers',
            type: 'info'
        })
    })

    it('redirects to Schedule when login is successful', async () => {
        const mockLogin = jest.fn(() => {
            return Promise.resolve({
                success: true
            })
        })

        appState.loginInfo = {
            smartcharger: true,
            consumer: {
                apiKey: 'any-thing-will-do'
            }
        }

        const wrapper = getComponent()
        wrapper.setMethods({ login: mockLogin })

        wrapper.vm.username = 'a'
        wrapper.vm.password = 'b'

        await wrapper.vm.loginClicked()
        expect(wrapper.vm.$route.path).toBe('/schedule')
    })
})

function getComponent() {
    const router = new VueRouter(routes)

    return shallowMount(Login, {
        localVue: localVue,
        router
    })
}

function setElement(wrapper, id, value) {
    const inputElement = wrapper.find(id)
    inputElement.element.value = value
    inputElement.trigger('input')
}

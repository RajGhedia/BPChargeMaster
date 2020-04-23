import ChangePassword from '@/pages/ChangePassword.vue'
import {
    shallowMount,
    createLocalVue,
    mount,
    RouterLinkStub
} from '@vue/test-utils'
import Vue from 'vue'
import Vuetify from 'vuetify'
import VueRouter from 'vue-router'
import routes from '@/router/routes'
import EventBus from '@/event-bus'
import axios from 'axios'

const localVue = createLocalVue()
localVue.config.productionTip = false
Vue.use(Vuetify) // Can't use Vuetify on localVue, not supported currently
localVue.use(VueRouter)

describe('ChangePassword.vue', () => {
    it('sets data model from inputs and rule validation occurs', async () => {
        // Uses mount rather than shallow as we are testing vuetify components
        const wrapper = mount(ChangePassword, {
            localVue: localVue,
            stubs: { 'router-link': RouterLinkStub }
        })

        const oldPassword = 'P4ssw0rd1'
        const newPassword = 'P4ssw0rd2'
        let confirmPassword = 'P4ssw0rd'

        setElement(wrapper, '#currentPasswordInput', oldPassword)
        setElement(wrapper, '#newPasswordInput', newPassword)
        setElement(wrapper, '#confirmPasswordInput', confirmPassword)
        Vue.nextTick(() => {
            expect(wrapper.vm.currentPassword).toBe(oldPassword)
            expect(wrapper.vm.newPassword).toBe(newPassword)
            expect(wrapper.vm.confirmPassword).toBe(confirmPassword)
            expect(wrapper.vm.isValid).toBe(false)
        })

        confirmPassword = 'P4ssw0rd2'

        setElement(wrapper, '#confirmPasswordInput', confirmPassword)
        Vue.nextTick(() => {
            expect(wrapper.vm.currentPassword).toBe(oldPassword)
            expect(wrapper.vm.newPassword).toBe(newPassword)
            expect(wrapper.vm.confirmPassword).toBe(confirmPassword)
            expect(wrapper.vm.isValid).toBe(false)
            wrapper.destroy()
        })
    })
})

describe('Save button', () => {
    it('it is disabled by default', () => {
        const wrapper = getComponent()

        expect(wrapper.vm.isValid).toBe(false)
        expect(
            wrapper.find('#updatePasswordButton').props().disabled
        ).toBeTruthy()
    })

    it('it is enabled if validation succeeds', () => {
        const wrapper = getComponent()

        wrapper.vm.isValid = true
        expect(
            wrapper.find('#updatePasswordButton').props().disabled
        ).not.toBeTruthy()
    })

    it('changes route if successful', async () => {
        const wrapper = getComponent()

        const mockEmitMethod = jest.fn()
        EventBus.$emit = mockEmitMethod

        wrapper.vm.currentPassword = 'a'
        wrapper.vm.newPassword = 'b'

        var res = wrapper.vm.updatePassword()

        setUpSuccessfulAxiosResponse()

        res.then(() => {
            expect(wrapper.vm.$route.path).toBe('/account')
        })
    })

    it('emits a success notification if successful', async () => {
        const wrapper = getComponent()

        const mockEmitMethod = jest.fn()
        EventBus.$emit = mockEmitMethod

        wrapper.vm.currentPassword = 'a'
        wrapper.vm.newPassword = 'b'

        var res = wrapper.vm.updatePassword()

        setUpSuccessfulAxiosResponse()

        res.then(() => {
            expect(mockEmitMethod).toHaveBeenCalledWith('notify', {
                message: 'Successfully updated password',
                type: 'success'
            })
        })
    })

    it('emits a failure notification if unsuccessful', async () => {
        const wrapper = getComponent()

        const mockEmitMethod = jest.fn()
        EventBus.$emit = mockEmitMethod

        wrapper.vm.currentPassword = 'a'
        wrapper.vm.newPassword = 'b'

        var res = wrapper.vm.updatePassword()

        axios.mockSuccess({
            status: 200,
            data: {
                code: 151,
                message: 'Invalid password',
                data: []
            }
        })

        res.then(() => {
            expect(mockEmitMethod).toHaveBeenCalledWith('notify', {
                message: 'Error updating details - Invalid password',
                type: 'error'
            })
        })
    })
})

function getComponent() {
    const router = new VueRouter(routes)

    return shallowMount(ChangePassword, {
        localVue: localVue,
        router
    })
}

function setUpSuccessfulAxiosResponse() {
    axios.mockSuccess({
        status: 200,
        data: {
            code: 100,
            message: 'success',
            data: []
        }
    })
}

function setElement(wrapper, id, value) {
    const inputElement = wrapper.find(id)
    inputElement.element.value = value
    inputElement.trigger('input')
}

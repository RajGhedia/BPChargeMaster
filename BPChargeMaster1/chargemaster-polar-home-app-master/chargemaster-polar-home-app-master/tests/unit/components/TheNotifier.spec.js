import TheNotifier from '@/components/TheNotifier.vue'
import Vue from 'vue'
import Vuetify from 'vuetify'
import { mount, createLocalVue } from '@vue/test-utils'
import EventBus from '@/event-bus'

const localVue = createLocalVue()
Vue.use(Vuetify)

describe('TheNotifier.vue', () => {
    it('renders empty by default', () => {
        const wrapper = getWrapper()

        expect(wrapper.element).toMatchSnapshot()
    })

    it('listens for the notify event', () => {
        const wrapper = getWrapper()

        EventBus.$emit('notify', {
            type: 'error',
            message: 'example error message',
            timeout: 10000
        })

        expect(wrapper.vm.message).toBe('example error message')
        expect(wrapper.vm.alertType).toBe('error')
        expect(wrapper.vm.timeout).toBe(10000)
        expect(wrapper.vm.notification).toBe(true)
    })

    it('defaults to 5 second timeout if not supplied', () => {
        const wrapper = getWrapper()

        EventBus.$emit('notify', {
            type: 'warn',
            message: 'example warn message'
        })

        expect(wrapper.vm.message).toBe('example warn message')
        expect(wrapper.vm.alertType).toBe('warn')
        expect(wrapper.vm.timeout).toBe(5000)
        expect(wrapper.vm.notification).toBe(true)
    })

    it('defaults to primary color if no type supplied', () => {
        const wrapper = getWrapper()

        EventBus.$emit('notify', {
            message: 'example info message'
        })

        expect(wrapper.vm.message).toBe('example info message')
        expect(wrapper.vm.alertType).toBe('primary')
        expect(wrapper.vm.timeout).toBe(5000)
        expect(wrapper.vm.notification).toBe(true)
    })

    it('cleans up event listener when component is destroyed', () => {
        const wrapper = getWrapper()

        wrapper.setMethods({
            showNotification: jest.fn()
        })

        const showNotification = wrapper.vm.showNotification
        const cleanup = jest.fn()

        EventBus.$off = cleanup
        wrapper.destroy()

        expect(cleanup).toBeCalledWith('notify', showNotification)
        EventBus.$emit('notify', { message: 'any message' })
        expect(showNotification).not.toHaveBeenCalled()
    })
})

function getWrapper() {
    return mount(TheNotifier, {
        localVue
    })
}

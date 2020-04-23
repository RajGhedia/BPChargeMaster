import App from '@/App.vue'
import { shallowMount, createLocalVue, RouterLinkStub } from '@vue/test-utils'
import Vue from 'vue'
import Vuetify from 'vuetify'
import VueRouter from 'vue-router'
import EventBus from '@/event-bus'

Vue.use(Vuetify)

const localVue = createLocalVue()
localVue.use(VueRouter)
localVue.config.productionTip = false

describe('App.vue', () => {
    it('disables the spinner by default', async () => {
        const wrapper = getWrapper()

        expect(wrapper.vm.callInProgress).toBe(false)
    })

    it('responds to the spin event', async () => {
        const wrapper = getWrapper()

        EventBus.$emit('spin', true)
        expect(wrapper.vm.callInProgress).toBe(true)
    })

    it('stops the spin event listener when component has been destroyed', async () => {
        const wrapper = getWrapper()

        wrapper.setMethods({
            toggleSpin: jest.fn()
        })

        const toggleSpin = wrapper.vm.toggleSpin
        const cleanup = jest.fn()

        EventBus.$off = cleanup
        wrapper.destroy()

        expect(cleanup).toBeCalledWith('spin', toggleSpin)
        EventBus.$emit('spin', true)
        expect(toggleSpin).not.toHaveBeenCalled()
    })
})

function getWrapper() {
    return shallowMount(App, {
        localVue: localVue,
        stubs: { 'router-link': RouterLinkStub }
    })
}

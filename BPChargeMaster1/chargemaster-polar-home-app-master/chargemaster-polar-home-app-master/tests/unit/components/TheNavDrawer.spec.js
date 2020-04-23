import TheNavDrawer from '@/components/TheNavDrawer.vue'
import Vue from 'vue'
import Vuetify from 'vuetify'
import { mount, createLocalVue, RouterLinkStub } from '@vue/test-utils'
import EventBus from '@/event-bus'

const localVue = createLocalVue()
Vue.use(Vuetify)

describe('TheNavDrawer.vue', () => {
    it('renders empty by default', () => {
        const wrapper = getWrapper()

        expect(wrapper.element).toMatchSnapshot()
    })

    it('listens for the notify event', () => {
        const wrapper = getWrapper()

        EventBus.$emit('openDrawer')

        expect(wrapper.vm.open).toBe(true)
        expect(wrapper.element).toMatchSnapshot()
    })

    it('cleans up event listener when component is destroyed', () => {
        const wrapper = getWrapper()

        wrapper.setMethods({
            openDrawer: jest.fn()
        })

        const openDrawer = wrapper.vm.openDrawer
        const cleanup = jest.fn()

        EventBus.$off = cleanup
        wrapper.destroy()

        expect(cleanup).toBeCalledWith('openDrawer', openDrawer)
        EventBus.$emit('openDrawer')
        expect(openDrawer).not.toHaveBeenCalled()
    })
})

function getWrapper() {
    return mount(TheNavDrawer, {
        localVue,
        stubs: {
            'router-link': RouterLinkStub
        }
    })
}

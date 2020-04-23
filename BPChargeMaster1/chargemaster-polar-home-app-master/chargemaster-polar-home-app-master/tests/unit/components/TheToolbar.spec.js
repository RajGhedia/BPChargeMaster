import TheToolbar from '@/components/TheToolbar.vue'
import Vue from 'vue'
import Vuetify from 'vuetify'
import { mount, createLocalVue, RouterLinkStub } from '@vue/test-utils'

const localVue = createLocalVue()
Vue.use(Vuetify)

describe('TheToolbar.vue', () => {
    it('renders correctly', () => {
        const wrapper = mount(TheToolbar, {
            localVue,
            propsData: {
                showMenu: true
            },
            stubs: { 'router-link': RouterLinkStub },
            slots: {
                default: 'A page title would go here'
            }
        })

        expect(wrapper.element).toMatchSnapshot()

        wrapper.destroy()
    })
})

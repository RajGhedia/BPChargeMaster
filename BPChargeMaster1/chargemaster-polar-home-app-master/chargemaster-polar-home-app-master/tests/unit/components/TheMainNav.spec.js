import TheMainNav from '@/components/TheMainNav.vue'
import Vue from 'vue'
import Vuetify from 'vuetify'
import { mount, createLocalVue, RouterLinkStub } from '@vue/test-utils'

const localVue = createLocalVue()
Vue.use(Vuetify)

describe('TheMainNav.vue', () => {
    it('renders correctly when given props', () => {
        const wrapper = mount(TheMainNav, {
            localVue,
            stubs: { 'router-link': RouterLinkStub },
            propsData: {
                active: 'account'
            }
        })

        expect(wrapper.element).toMatchSnapshot()
    })
})

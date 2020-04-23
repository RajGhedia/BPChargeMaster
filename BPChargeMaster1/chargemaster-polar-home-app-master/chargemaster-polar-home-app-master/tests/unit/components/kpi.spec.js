import Kpi from '@/components/kpi.vue'
import Vue from 'vue'
import Vuetify from 'vuetify'
import { mount, createLocalVue } from '@vue/test-utils'

const localVue = createLocalVue()
Vue.use(Vuetify)

describe('kpi.vue', () => {
    it('renders correctly when given props', () => {
        const wrapper = mount(Kpi, {
            localVue,
            propsData: {
                icon: 'total-charges-icon',
                description: 'a label',
                amount: '4.25'
            }
        })

        expect(wrapper.element).toMatchSnapshot()
    })
})

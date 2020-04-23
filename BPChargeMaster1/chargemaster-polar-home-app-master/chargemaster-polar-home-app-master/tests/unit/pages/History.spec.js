import History from '@/pages/History.vue'
import { shallowMount, createLocalVue } from '@vue/test-utils'
import Vue from 'vue'
import Vuetify from 'vuetify'
import VueRouter from 'vue-router'
import routes from '@/router/routes'
import axios from 'axios'
import EventBus from '@/event-bus'

const localVue = createLocalVue()
localVue.config.productionTip = false
Vue.use(Vuetify) // Can't use Vuetify on localVue, not supported currently
localVue.use(VueRouter)

describe('History.vue', () => {
    it('calls the refresh kpi and recharges methods on mounting', async () => {
        const dashboardLoadMethod = jest.fn()
        const rechargesMethod = jest.fn()
        getComponent({
            reloadDashboard: dashboardLoadMethod,
            getRecharges: rechargesMethod
        })

        expect(dashboardLoadMethod).toHaveBeenCalledWith('1')
        expect(rechargesMethod).toHaveBeenCalled()
    })
})

describe('reloadDashboard', () => {
    it('calls the refresh kpi service and updates indicators', async () => {
        const wrapper = getComponent()

        const res = wrapper.vm.reloadDashboard(2)

        axios.mockSuccess({
            status: 200,
            data: {
                code: 100,
                data: {
                    charges: 111,
                    time: '4355218',
                    energy: '494.2560',
                    co2: '413.69',
                    total_money_spent: '0.00'
                }
            }
        })

        res.then(() => {
            expect(wrapper.vm.indicators.charges).toBe(111)
        }).catch(err => {
            throw new Error(err)
        })
    })

    it('emits an error notification if an error occurs in service call', async () => {
        const wrapper = getComponent()

        const mockEmitMethod = jest.fn()
        EventBus.$emit = mockEmitMethod

        const res = wrapper.vm.reloadDashboard(2)

        axios.mockSuccess({
            status: 200,
            data: {
                code: 145,
                message: 'A problem occurred',
                data: {}
            }
        })

        res.then(() => {
            expect(mockEmitMethod).toHaveBeenCalledWith('notify', {
                message: 'A problem occurred',
                type: 'error'
            })
        })
    })
})

describe('recharges', () => {
    it('calls the recharge service and updates recharges data', async () => {
        const wrapper = getComponent()
        const res = wrapper.vm.getRecharges()

        axios.mockSuccess({
            status: 200,
            message: 'success',
            data: {
                code: 100,
                data: {
                    recharges: [
                        {
                            ts_start: 1556631382,
                            ts_end: 1556691202,
                            time_start: '30/04/2019 14:36',
                            time_end: '01/05/2019 07:13',
                            duration: '16h 37m',
                            energy: 7.516,
                            money_spent: '0.00'
                        },
                        {
                            ts_start: 1556631212,
                            ts_end: 1556631381,
                            time_start: '30/04/2019 14:33',
                            time_end: '30/04/2019 14:36',
                            duration: '3m',
                            energy: 0.216,
                            money_spent: '0.00'
                        }
                    ]
                }
            }
        })

        res.then(() => {
            expect(wrapper.vm.recharges.length).toBe(2)
        }).catch(err => {
            throw new Error(err)
        })
    })

    it('emits an error notification if an error occurs in service call', async () => {
        const wrapper = getComponent()

        const mockEmitMethod = jest.fn()
        EventBus.$emit = mockEmitMethod

        const res = wrapper.vm.getRecharges()

        axios.mockSuccess({
            status: 200,
            data: {
                code: 145,
                message: 'Error message here'
            }
        })

        res.then(() => {
            expect(mockEmitMethod).toHaveBeenCalledWith('notify', {
                message: 'Unexpected error occurred while retrieving history',
                type: 'error'
            })
        }).catch(err => {
            throw new Error(err)
        })
    })
})

function getComponent(withMethods = {}) {
    const router = new VueRouter(routes)

    return shallowMount(History, {
        localVue: localVue,
        router,
        methods: withMethods
    })
}

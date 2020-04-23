<template>
    <v-container fluid ma-0 pa-0>
        <TheToolbar>Charging History</TheToolbar>
        <v-layout>
            <v-flex xs5 offset-xs1 pt-4 pb-0>
                <h2>Dashboard</h2>
            </v-flex>
            <v-flex xs5 text-xs-right pt-0>
                <v-select
                    class="timespanPicker"
                    @change="reloadDashboard"
                    :items="timespanOptions"
                    item-value="id"
                    item-text="name"
                    v-model="selectedTimespan"
                ></v-select>
            </v-flex>
        </v-layout>

        <kpi
            v-if="indicators.totalMoneySpent"
            description="Money Spent"
            :amount="'£' + indicators.totalMoneySpent"
            icon="money-spent-icon"
        />
        <kpi
            v-if="indicators.charges"
            description="Total Charges"
            :amount="indicators.charges"
            icon="total-charges-icon"
        />
        <kpi
            v-if="indicators.energy"
            description="Total Energy Usage"
            :amount="indicators.energy + ' kW'"
            icon="total-energy-usage-icon"
        />
        <kpi
            v-if="indicators.co2"
            description="Total CO2 Saved"
            :amount="indicators.co2 + ' tons'"
            icon="co2-saved-icon@3x"
        />

        <v-flex xs12 offset-xs1 pt-3 pb-1>
            <h2>Recent Charges</h2>
        </v-flex>

        <v-flex mb-2 mx-2>
            <v-data-table
                :headers="headers"
                :items="recharges"
                pl-2
                hide-actions
                class="elevation-1 recharges-table"
            >
                <template v-slot:items="props">
                    <tr class="recharges-row">
                        <td>{{ props.item.start }}</td>
                        <td>{{ props.item.duration }}</td>
                        <td>{{ props.item.energy }}</td>
                        <td>£{{ props.item.cost }}</td>
                    </tr>
                </template>
            </v-data-table>
        </v-flex>
        <TheMainNav />
    </v-container>
</template>

<script>
import TheMainNav from '@/components/TheMainNav.vue'
import TheToolbar from '@/components/TheToolbar.vue'
import kpi from '@/components/kpi.vue'
import { dashboardService } from '@/services/dashboard'
import { rechargesService } from '@/services/recharges'
import EventBus from '@/event-bus'

export default {
    components: {
        TheMainNav,
        TheToolbar,
        kpi
    },
    data: () => {
        return {
            timespanOptions: [
                { id: '1', name: 'Last 7 Days' },
                { id: '2', name: 'Last 30 Days' },
                { id: '3', name: 'Last 3 Months' },
                { id: '4', name: 'Last 6 Months' }
            ],
            selectedTimespan: { id: '1', name: 'Last 7 Days' },
            indicators: {},
            headers: [
                {
                    text: 'Start Time',
                    value: 'time_start',
                    sortable: false,
                    class: 'recharge-header'
                },
                {
                    text: 'Duration',
                    value: 'duration',
                    sortable: false,
                    class: 'recharge-header'
                },
                {
                    text: 'kWh',
                    value: 'energy',
                    sortable: false,
                    class: 'recharge-header'
                },
                {
                    text: 'Cost',
                    value: 'cost',
                    sortable: false,
                    class: 'recharge-header'
                }
            ],
            pagination: {
                descending: true,
                page: 1,
                rowsPerPage: -1 // -1 for All"
            },
            recharges: []
        }
    },
    async mounted() {
        const f1 = this.reloadDashboard(this.selectedTimespan.id)
        const f2 = this.getRecharges()
        // Run both calls in parallel (without fast-fail problem of Promise.all)
        await f1
        await f2
    },
    methods: {
        async reloadDashboard(id) {
            const result = await this.getDashboard(id)
            if (result.success) {
                this.indicators = result.data
            } else {
                const payLoad = {
                    message: result.error,
                    type: 'error'
                }
                EventBus.$emit('notify', payLoad)
            }
        },
        async getDashboard(id) {
            return dashboardService
                .getDashboard(id)
                .then(result => {
                    return result
                })
                .catch(() => {
                    return {
                        success: false,
                        error: 'An unexpected error has occurred'
                    }
                })
        },
        async getRecharges() {
            try {
                const result = await rechargesService.get()
                if (result.data) {
                    this.recharges = result.data
                } else {
                    // Will not get here, data either (can be empty), or returns an error, caught below
                    const payLoad = {
                        message: result.error,
                        type: 'error'
                    }
                    EventBus.$emit('notify', payLoad)
                }
            } catch (ex) {
                const payLoad = {
                    message:
                        'Unexpected error occurred while retrieving history',
                    type: 'error'
                }
                EventBus.$emit('notify', payLoad)
            }
        }
    }
}
</script>

<style lang="scss">
.v-select__selection {
    font-size: 1rem;
    max-width: 94% !important;
}

.recharges-row td,
th.recharge-header {
    padding: 1rem !important;
}
</style>

<template>
    <v-layout class="reg-sched-container" color="primary" mx-3 my-4 pa-3>
        <v-flex x12>
            <v-form @submit.native.prevent="createBlock" v-model="isValid">
                <v-layout>
                    <v-flex xs-12>
                        <p>
                            Plug in by
                            <strong>{{ start.format('HH:mm') }}</strong> for a
                            full charge.

                            <v-btn
                                icon
                                small
                                class="mr-2"
                                :ripple="false"
                                absolute
                                right
                                @click.native="cancelClicked"
                            >
                                <v-icon>close</v-icon>
                            </v-btn>
                        </p>
                    </v-flex>
                </v-layout>
                <v-layout mt-1>
                    <!-- <v-text-field class="body-1" label="Finish charging at:" readonly :value="formattedFinish"></v-text-field> -->
                    <v-flex xs-12>
                        <p class="caption pb-0 mb-0">Finish charging at:</p>
                    </v-flex>
                </v-layout>
                <v-layout mb-3 mt-0 pt-0>
                    <v-flex xs-12>
                        <vue-timepicker
                            :minute-interval="15"
                            hide-clear-button
                            format="HH:mm"
                            v-model="finishHHmm"
                            @change="finishChanged"
                        ></vue-timepicker>
                    </v-flex>
                </v-layout>
                <v-layout>
                    <v-flex xs-12>
                        <p class="caption pb-0 mb-1">Repeat on:</p>
                        <!-- <v-btn-toggle multiple v-model="days" :rules="[rules.daysRequired]"> -->
                    </v-flex>
                </v-layout>
                <v-layout mb-4>
                    <v-flex xs-12>
                        <v-btn-toggle
                            multiple
                            v-model="days"
                            mandatory
                            width="100%"
                        >
                            <v-btn flat v-for="d in dow" :key="d.i">
                                {{ d.day }}
                            </v-btn>
                        </v-btn-toggle>
                    </v-flex>
                </v-layout>
                <v-layout>
                    <v-text-field
                        v-model="name"
                        label="Give this schedule a name:"
                        placeholder="Charge until.."
                        :rules="[rules.required]"
                    ></v-text-field>
                </v-layout>
                <v-layout xs12 column mt-3>
                    <v-btn flat outline type="submit" :disabled="!isValid"
                        >Create</v-btn
                    >
                    <v-btn flat color="primary">Advanced Settings</v-btn>
                </v-layout>
            </v-form>
        </v-flex>
    </v-layout>
</template>

<script>
import { getSvgUrl } from '@/helpers/utils'
import appState from '@/services/appState'
import { scheduleService } from '@/services/schedule'
import Validator from '@/helpers/validator'
import VueTimepicker from '@/components/vue-timepicker'
import moment from 'moment'
import EventBus from '@/event-bus'

export default {
    data: () => {
        return {
            isValid: false,
            name: '',
            start: moment()
                .set('hour', 0)
                .set('minute', 0),
            finish: moment()
                .set('hour', 7)
                .set('minute', 0),
            finishHHmm: { HH: '00', mm: '00', m: '0' },
            days: [],
            rules: {
                required: val => Validator.required(val),
                daysRequired: val => {
                    // eslint-disable-next-line no-console
                    console.log('days val', val)
                    return val.length > 1
                }
            }
        }
    },
    computed: {
        formattedStart() {
            return this.start.format('HH:mm')
        },
        formattedFinish() {
            return this.finish.format('HH:mm')
        },
        dow() {
            const days = 'MTWTFSS'

            let result = []
            for (let i = 0; i < 7; i++) {
                result.push({ i, day: days[i] })
            }

            return result
        }
    },
    components: {
        VueTimepicker
    },
    mounted() {
        this.name = 'Charge until 07:00'
        this.finishHHmm.HH = this.finish.format('HH')
        this.finishHHmm.mm = this.finish.format('mm')
    },
    methods: {
        async createBlock() {
            if (this.name) {
                // eslint-disable-next-line
                console.log('Form data', this.name, this.finishHHmm, this.days)

                try {
                    const result = await scheduleService.createFillMeBy(
                        this.name,
                        parseInt(this.finishHHmm.HH),
                        parseInt(this.finishHHmm.mm),
                        this.days
                    )
                    if (result.data) {
                        const payload = {
                            message: 'New schedule successfully created',
                            type: 'success'
                        }
                        EventBus.$emit('notify', payload)
                        EventBus.$emit('scheduleAdded', {
                            success: true,
                            data: result.data
                        })
                    } else {
                        // error
                        const payload = {
                            message: `Error creating new schedule: ${
                                result.message
                            }`,
                            type: 'error'
                        }
                        EventBus.$emit('notify', payload)
                        EventBus.$emit('scheduleAdded', { success: false })
                    }
                } catch (error) {
                    // eslint-disable-next-line no-console
                    console.log(error)
                }
            }
        },
        finishChanged(event) {
            this.start.hour(parseInt(event.data.H))
            this.start.subtract(appState.hoursToFullCharge, 'hour')
            this.start.minute(parseInt(event.data.m))
        },
        cancelClicked() {
            EventBus.$emit('scheduleAddCancel')
        },
        getImgUrl: img => getSvgUrl(img)
    }
}
</script>

<style lang="scss">
.reg-sched-container {
    margin: 0 4px;
    border: 2px solid #06aeef;
    border-radius: 5px;
}

.time-picker {
    width: 100%;
    display: block;

    input.display-time {
        border: none;
        width: 100%;
        border-bottom: 1px solid #06aeef;
    }
}
</style>

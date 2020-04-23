<template>
    <v-container class="chg-blocks-container" px-0 pt-0>
        <ChargingBlock
            v-for="sch of schedules"
            :key="sch.idx"
            :schedule="sch"
        />
    </v-container>
</template>

<script>
import ChargingBlock from '@/components/ChargingBlock.vue'

export default {
    mounted() {
        this.addScheduleKeys()
    },
    components: {
        ChargingBlock
    },
    data: () => {
        return {}
    },
    props: ['schedules'],
    computed: {},
    methods: {
        addScheduleKeys() {
            for (let index = 0; index < this.schedules.length; index++) {
                this.schedules.idx = index
            }
        }
    }
}
</script>

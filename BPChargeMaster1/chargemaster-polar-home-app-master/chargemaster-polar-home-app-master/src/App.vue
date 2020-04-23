<template>
    <v-app>
        <!-- This is the loading spinner overlay. See https://biigpongsatorn.github.io/#/vue-element-loading -->
        <vue-element-loading
            :active="callInProgress"
            color="#06AEEF"
            is-full-screen
        />
        <v-content>
            <TheNotifier />
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
import EventBus from '@/event-bus'
import TheNotifier from '@/components/TheNotifier.vue'
import VueElementLoading from 'vue-element-loading'

export default {
    name: 'App',
    data() {
        return {
            callInProgress: false
        }
    },
    methods: {
        toggleSpin(showSpinner) {
            this.callInProgress = showSpinner
        }
    },
    components: {
        TheNotifier,
        VueElementLoading
    },
    created() {
        EventBus.$on('spin', this.toggleSpin)
    },
    beforeDestroy() {
        EventBus.$off('spin', this.toggleSpin)
    }
}
</script>

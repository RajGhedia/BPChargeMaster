<template>
    <v-snackbar
        class="ma-2"
        v-model="notification"
        top
        auto-height
        :color="alertType"
        :timeout="0"
    >
        <span class="text-xs-center">{{ message }}</span>
    </v-snackbar>
</template>
<script>
import EventBus from '@/event-bus'
export default {
    data: () => {
        return {
            message: '',
            alertType: 'success',
            notification: false
        }
    },
    methods: {
        showNotification(payload) {
            if (typeof payload.timeout === 'undefined') {
                payload.timeout = 5000
            }

            this.alertType = payload.type || 'primary'
            this.message = payload.message
            this.timeout = payload.timeout
            this.notification = true
            // Have to do it like this because the component has an issue
            // with toggling timeout between indefinite and a duration
            if (payload.timeout > 0) {
                setTimeout(() => {
                    this.notification = false
                }, payload.timeout)
            }
        }
    },
    created() {
        EventBus.$on('notify', this.showNotification)
    },
    beforeDestroy() {
        EventBus.$off('notify', this.showNotification)
    }
}
</script>
<style>
.v-snack__content span {
    width: 100%;
}

.v-snack {
    z-index: 4000 !important;
}
</style>

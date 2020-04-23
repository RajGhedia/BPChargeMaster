<template>
    <v-container ma-0 pa-0>
        <TheToolbar showClose backTo="account">Contact us</TheToolbar>
        <v-layout text-xs-left pt-5>
            <v-flex xs12 align-content-center>
                <img :src="require('@/assets/phone.svg')" class="px-3" />
                <a href="tel:0330 016 5126">0330 016 5126</a>
            </v-flex>
        </v-layout>
        <v-layout text-xs-left pt-4>
            <v-flex xs12 align-content-center>
                <img :src="require('@/assets/envelope.svg')" class="px-3" />
                <a href="mailto:customerservice@bpchargemaster.com"
                    >customerservice@bpchargemaster.com</a
                >
            </v-flex>
        </v-layout>
        <v-layout text-xs-left pt-3>
            <v-flex xs12 px-3>
                <p>Our customer service team is available 24/7</p>
            </v-flex>
        </v-layout>
        <v-layout text-xs-left pt-1>
            <v-flex xs12 px-3>
                <hr primary--text />
            </v-flex>
        </v-layout>
        <v-layout text-xs-left pt-3>
            <v-flex xs12 px-3>
                <p>If you contact us we may ask for these details:</p>
            </v-flex>
        </v-layout>
        <v-layout text-xs-left pt-3>
            <v-flex xs5 px-3>
                <p>Serial Number:</p>
            </v-flex>
            <v-flex xs7 px-3>
                <p>{{ serialNo }}</p>
            </v-flex>
        </v-layout>
        <v-layout text-xs-left pt-2>
            <v-flex xs5 px-3>
                <p>App Version:</p>
            </v-flex>
            <v-flex xs7 px-3>
                <p>{{ appVersion }}</p>
            </v-flex>
        </v-layout>
    </v-container>
</template>
<script>
import appState from '@/services/appState'
import { APPVERSION } from '@/helpers/appVersion'
import TheToolbar from '@/components/TheToolbar'

export default {
    async mounted() {
        const loginInfo = appState.loginInfo
        this.serialNo = loginInfo.serial
    },
    data() {
        return {
            serialNo: ''
        }
    },
    computed: {
        appVersion: () => APPVERSION
    },
    components: {
        TheToolbar
    }
}
</script>
<style scoped>
a {
    text-decoration: none;
    font-weight: bold;
}

img {
    vertical-align: middle;
}
</style>

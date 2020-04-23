<template>
    <v-container fluid ma-0 pa-0>
        <v-form v-model="isValid" ref="ç" @submit.native.prevent="loginClicked">
            <v-layout text-xs-center wrap>
                <v-flex xs12>
                    <v-img
                        :src="require('@/assets/login-bg.png')"
                        contain
                    ></v-img>
                </v-flex>
                <v-flex xs10 offset-xs1 pt-2>
                    <v-img :src="require('@/assets/logo.svg')" contain></v-img>
                </v-flex>

                <v-flex xs10 offset-xs1 pt-3>
                    <v-text-field
                        id="usernameInput"
                        label="Email"
                        v-model="username"
                        :rules="[rules.required, rules.isValidEmail]"
                    ></v-text-field>
                </v-flex>
                <v-flex xs10 offset-xs1 pt-2>
                    <v-text-field
                        id="passwordInput"
                        label="Password"
                        :append-icon="
                            showPassword ? 'visibility' : 'visibility_off'
                        "
                        :rules="[rules.required, rules.isMinPassword]"
                        :type="showPassword ? 'text' : 'password'"
                        v-model="password"
                        hint="At least 8 characters"
                        class="input-group--focused"
                        @click:append="showPassword = !showPassword"
                    ></v-text-field>
                    <p
                        class="text-xs-right text-uppercase text-weight-bold pt-2"
                    >
                        <a
                            id="forgotPasswordLink"
                            @click="forgotPasswordClicked"
                            >Forgot password?</a
                        >
                    </p>
                </v-flex>
                <v-flex xs10 offset-xs1 pt-1>
                    <v-btn
                        large
                        type="submit"
                        id="loginButton"
                        :ripple="false"
                        block
                        outline
                        :disabled="!isValid"
                        color="primary"
                        >Log In</v-btn
                    >
                </v-flex>
            </v-layout>
            <v-layout>
                <v-flex xs10 text-xs-center offset-xs1 pt-2>
                    <p>App Version: {{ appVersion }}</p>
                </v-flex>
            </v-layout>
        </v-form>
    </v-container>
</template>
<script>
/*eslint no-unused-vars: ["error", { "argsIgnorePattern": "^_" }]*/
import { loginService } from '@/services/Login'
import EventBus from '@/event-bus'
import appState from '@/services/appState'
import Validator from '@/helpers/validator'
import { APPVERSION } from '@/helpers/appVersion'
/**
 * Controller is a stateless object, containing functions invoked by the component.
 */

export default {
    data: () => {
        return {
            isValid: false,
            password: '',
            username: '',
            showPassword: false,
            rules: {
                required: v => Validator.required(v),
                isMinPassword: v => Validator.isMinPassword(v),
                isValidEmail: v => Validator.isValidEmail(v)
            }
        }
    },
    computed: {
        appVersion: () => APPVERSION
    },
    methods: {
        async reValidateForm() {
            this.$refs.loginForm.validate() // not sure how to mock this yet
        },
        forgotPasswordClicked() {
            window.cordova.InAppBrowser.open(
                process.env.VUE_APP_RESET_PASSWORD_URL,
                '_system'
            )
            return false
        },

        /**
         * Will invoke the login() function, handles the status of the login button, error message etc
         * @param data
         */
        loginClicked() {
            this.login(this.username, this.password).then(res => {
                if (res.success) {
                    if (appState.loginInfo.smartcharger) {
                        const payLoad = {
                            message: 'Login successful',
                            type: 'success',
                            timeout: 2000
                        }
                        EventBus.$emit('notify', payLoad)
                        this.$router.replace('schedule')
                    } else {
                        const payLoad = {
                            message:
                                'Unfortunately this app is only available to Chargemaster Smartcharge customers',
                            type: 'info'
                        }
                        EventBus.$emit('notify', payLoad)
                    }
                } else {
                    const payLoad = {
                        message: res.loginError,
                        type: 'error'
                    }
                    EventBus.$emit('notify', payLoad)
                }
            })
        },
        /**
         * Invokes the login function, handles the response.
         * @param username
         * @param password
         * @returns {Promise<T | never>}
         */
        login: function(username, password) {
            return loginService
                .login(username, password)
                .then(result => {
                    if (result.success) {
                        return { success: true }
                    } else {
                        return { success: false, loginError: result.message }
                    }
                })
                .catch(_ex => {
                    return {
                        success: false,
                        loginError: 'An unexpected error has occurred'
                    }
                })
        }
    }
}
</script>

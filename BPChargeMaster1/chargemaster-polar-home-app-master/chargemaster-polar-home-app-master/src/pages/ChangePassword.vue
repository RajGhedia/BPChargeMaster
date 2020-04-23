<template>
    <v-container ma-0 pa-0>
        <TheToolbar showBack backTo="account">Change Password</TheToolbar>
        <v-form ref="accountForm" v-model="isValid">
            <v-layout text-xs-left wrap>
                <v-flex xs10 offset-xs1 pt-5>
                    <v-text-field
                        label="Current Password"
                        :rules="[rules.required, rules.isMinPassword]"
                        type="password"
                        v-model="currentPassword"
                        id="currentPasswordInput"
                    ></v-text-field>
                </v-flex>
            </v-layout>
            <v-layout text-xs-left wrap>
                <v-flex xs10 offset-xs1 pt-2>
                    <v-text-field
                        label="New Password"
                        :rules="[rules.required, rules.isMinPassword]"
                        type="password"
                        v-model="newPassword"
                        id="newPasswordInput"
                    ></v-text-field>
                </v-flex>
            </v-layout>
            <v-layout text-xs-left wrap>
                <v-flex xs10 offset-xs1 pt-2>
                    <p>
                        8 - 16 characters. Must contain a combination of
                        uppercase letters, lowercase letters, numbers &amp;
                        special characters.
                    </p>
                </v-flex>
            </v-layout>
            <v-layout text-xs-left wrap>
                <v-flex xs10 offset-xs1 pt-0>
                    <v-text-field
                        label="Confirm New Password"
                        :rules="[rules.required, checkPasswordMatch]"
                        type="password"
                        v-model="confirmPassword"
                        id="confirmPasswordInput"
                    ></v-text-field>
                </v-flex>
            </v-layout>
            <v-layout>
                <v-flex xs10 text-xs-center offset-xs1 mt-5>
                    <v-btn
                        large
                        outline
                        block
                        color="primary"
                        id="updatePasswordButton"
                        :disabled="!isValid"
                        @click="updatePassword"
                        >UPDATE PASSWORD</v-btn
                    >
                </v-flex>
            </v-layout>
        </v-form>

        <TheMainNav active="account" />
    </v-container>
</template>
<script>
/*eslint no-unused-vars: ["error", { "argsIgnorePattern": "^_" }]*/
import EventBus from '@/event-bus'
import Validator from '@/helpers/validator'
import TheMainNav from '@/components/TheMainNav.vue'
import TheToolbar from '@/components/TheToolbar'
import { changePasswordService } from '@/services/changePassword'

export default {
    data: () => {
        return {
            isValid: false,
            currentPassword: '',
            newPassword: '',
            confirmPassword: '',
            rules: {
                required: v => Validator.required(v),
                isMinPassword: v => Validator.isMinPassword(v)
            }
        }
    },
    methods: {
        checkPasswordMatch() {
            return Validator.mustMatch(
                this.newPassword,
                this.confirmPassword,
                'Passwords'
            )
        },
        async updatePassword() {
            const result = await changePasswordService.setPassword(
                this.currentPassword,
                this.newPassword
            )

            let payLoad
            if (result.status) {
                payLoad = {
                    message: 'Successfully updated password',
                    type: 'success'
                }
                this.$router.replace('account')
                EventBus.$emit('notify', payLoad)
            } else {
                payLoad = {
                    message: 'Error updating details - ' + result.message,
                    type: 'error'
                }
                EventBus.$emit('notify', payLoad)
            }
        }
    },
    components: {
        TheMainNav,
        TheToolbar
    }
}
</script>

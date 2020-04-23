<template>
    <v-container ma-0 pa-0>
        <TheToolbar showMenu>{{ username }}</TheToolbar>
        <TheNavDrawer></TheNavDrawer>
        <v-layout>
            <v-flex xs6 offset-xs1 pt-3 pb-0>
                <h2>Account Details</h2>
            </v-flex>
            <v-flex xs4 text-xs-right pt-3>
                <span class="caption">Serial: {{ serialNo }}</span>
            </v-flex>
        </v-layout>

        <v-form ref="accountForm" v-model="isValid">
            <v-layout text-xs-left wrap mt-2>
                <v-flex xs10 offset-xs1 pt-0>
                    <v-text-field
                        label="Email"
                        validate-on-blur
                        :readonly="true"
                        v-model="email"
                    ></v-text-field>
                </v-flex>
                <v-flex xs10 offset-xs1 pt-2>
                    <v-text-field
                        label="Street"
                        v-model="address"
                        :readonly="!updating"
                        :rules="[rules.required]"
                    ></v-text-field>
                </v-flex>
                <v-flex xs10 offset-xs1 :pt-2="updating">
                    <v-text-field
                        label="City"
                        validate-on-blur
                        :readonly="!updating"
                        v-model="city"
                        :rules="[rules.required]"
                    ></v-text-field>
                </v-flex>
                <v-flex xs10 offset-xs1 pt-2>
                    <v-text-field
                        label="Postcode"
                        validate-on-blur
                        :readonly="!updating"
                        v-model="postcode"
                        :rules="[rules.required]"
                    ></v-text-field>
                </v-flex>
                <v-flex xs10 offset-xs1 pt-2>
                    <v-text-field
                        label="Country"
                        validate-on-blur
                        :readonly="true"
                        v-model="country"
                    ></v-text-field>
                </v-flex>
                <v-flex xs10 offset-xs1 pt-2>
                    <v-text-field
                        label="Phone"
                        :readonly="!updating"
                        validate-on-blur
                        v-model="phone"
                        :rules="[
                            rules.required,
                            rules.isValidPhoneNumberFormat
                        ]"
                    ></v-text-field>
                </v-flex>
                <v-flex xs10 offset-xs1 pt-2>
                    <v-text-field
                        label="Energy Company"
                        :readonly="!updating"
                        v-model="energyCompany"
                    ></v-text-field>
                </v-flex>
                <v-flex xs10 offset-xs1 pt-2>
                    <v-text-field
                        label="Tariff Type"
                        :readonly="!updating"
                        v-model="tariff"
                    ></v-text-field>
                </v-flex>
            </v-layout>
        </v-form>
        <v-layout>
            <v-flex xs10 text-xs-center offset-xs1 pt-3>
                <v-btn
                    large
                    outline
                    block
                    id="editButton"
                    color="primary"
                    :disabled="updating && !canSubmit"
                    @click.native="editAccountClicked"
                    >{{ editAccountBtnText }}</v-btn
                >
            </v-flex>
        </v-layout>
        <v-layout>
            <v-flex xs10 text-xs-center offset-xs1>
                <v-btn
                    large
                    outline
                    block
                    color="warning"
                    v-if="updating"
                    id="cancelButton"
                    @click.native="cancelClicked"
                    >Cancel</v-btn
                >
            </v-flex>
        </v-layout>

        <v-layout>
            <v-flex xs10 text-xs-center offset-xs1 pt-0 mt-0>
                <v-btn
                    large
                    outline
                    block
                    color="primary"
                    id="changePasswordButton"
                    v-if="!updating"
                    to="change-password"
                    >Change Password</v-btn
                >
            </v-flex>
        </v-layout>
        <v-layout>
            <v-flex xs10 text-xs-center offset-xs1>
                <v-btn
                    large
                    outline
                    block
                    color="error"
                    id="logoutButton"
                    v-if="!updating"
                    @click.native="logoutClicked"
                    >Log out</v-btn
                >
            </v-flex>
        </v-layout>
        <v-layout>
            <v-flex xs10 text-xs-left offset-xs1 pt-2>
                <p>App Version: {{ appVersion }}</p>
            </v-flex>
        </v-layout>
        <TheMainNav />
    </v-container>
</template>

<script>
/*eslint no-unused-vars: ["error", { "argsIgnorePattern": "^_" }]*/
import TheMainNav from '@/components/TheMainNav.vue'
import TheToolbar from '@/components/TheToolbar.vue'
import TheNavDrawer from '@/components/TheNavDrawer'
import EventBus from '@/event-bus'
import appState from '@/services/appState'
import { accountService } from '@/services/account'
import Validator from '@/helpers/validator'
import { APPVERSION } from '@/helpers/appVersion'

let loginInfo = null
let accountInfo = null

export default {
    async mounted() {
        loginInfo = appState.loginInfo
        if (loginInfo) {
            this.username = loginInfo.consumer.name
            this.serialNo = loginInfo.serial
        }

        const result = await this.getAccountInfo()

        if (result.success) {
            const user = accountInfo.user
            this.email = user.email
            this.address = user.address
            this.city = user.city
            this.postcode = user.postcode
            this.country = user.country
            this.countryId = user.country_id
            this.phone = user.phone
        } else {
            this.errorMsg = result.error
        }
    },
    data: () => {
        return {
            isValid: false,
            username: '',
            serialNo: '',
            email: '',
            address: '',
            city: '',
            postcode: '',
            country: '',
            countryId: 0,
            phone: '',
            energyCompany: 'N/A',
            tariff: 'N/A',
            errorMsg: '',
            updating: false,
            rules: {
                required: val => Validator.required(val),
                isValidPhoneNumberFormat(value) {
                    return Validator.isValidPhoneNumberFormat(value)
                }
            }
        }
    },
    components: {
        TheMainNav,
        TheToolbar,
        TheNavDrawer
    },
    computed: {
        editAccountBtnText() {
            if (this.updating) {
                return 'Save account details'
            } else {
                return 'Edit account details'
            }
        },

        appVersion: () => APPVERSION,
        canSubmit() {
            return this.updating && this.isValid
        }
    },
    methods: {
        cancelClicked() {
            this.updating = false
            window.scrollTo(0, 0)
            this.resetFormValidation()
        },
        async editAccountClicked() {
            if (this.canSubmit) {
                this.saveData()
            } else {
                this.updating = true
                this.isValid = this.reValidateForm()
            }
        },
        resetFormValidation() {
            this.$refs.accountForm.resetValidation()
        },
        reValidateForm() {
            this.$refs.accountForm.validate() // not sure how to mock this yet
        },
        async saveData() {
            const result = await accountService.setAccountInfo(
                this.username,
                this.email,
                this.address,
                this.city,
                this.postcode,
                this.countryId,
                this.phone
            )

            let payLoad
            if (result.status) {
                payLoad = {
                    message: 'Successfully updated account details',
                    type: 'success'
                }
                this.updating = false
            } else {
                payLoad = {
                    message: 'Error updating details - ' + result.message,
                    type: 'Error'
                }
            }
            EventBus.$emit('notify', payLoad)
        },
        logoutClicked() {
            // TODO: Should call logout method on API?
            appState.apiKey = ''
            const payLoad = {
                message: "You've been logged out",
                type: 'success'
            }
            EventBus.$emit('notify', payLoad)
            this.$router.replace('login')
            return false
        },
        getAccountInfo() {
            return accountService
                .getAccountInfo()
                .then(result => {
                    if (result && result.data !== undefined) {
                        accountInfo = result.data
                        return { success: true, error: '' }
                    } else {
                        if (result.status == 401 || result.status == 403) {
                            this.$router.replace('login')
                        }

                        return { success: false, error: result.message }
                    }
                })
                .catch(_ex => {
                    return {
                        success: false,
                        error: 'An unexpected error has occurred'
                    }
                })
        }
    }
}
</script>

<style scoped>
.caption {
    color: rgba(0, 0, 0, 0.6);
}

.v-text-field.v-input--is-readonly > .v-input__control > .v-input__slot::before,
.v-text-field > .v-input__control > .v-input__slot::after {
    width: 0;
}

.v-text-field.v-input--is-readonly > .v-input__control {
    height: 32px;
}
</style>

import ApiCaller from '@/helpers/apiCaller'
import appState from './appState'

export const accountService = {
    async getAccountInfo() {
        return await ApiCaller.post('account').then(response => {
            if (response && response.data !== undefined) {
                appState.accountInfo = response.data
            }

            return response
        })
    },

    async setAccountInfo(
        name,
        email,
        address,
        city,
        postcode,
        country_id,
        phone
    ) {
        return await ApiCaller.post('account/update', {
            name,
            email,
            address,
            city,
            postcode,
            country_id,
            phone
        }).then(response => {
            return { status: !!response.data, message: response.message }
        })
    }
}

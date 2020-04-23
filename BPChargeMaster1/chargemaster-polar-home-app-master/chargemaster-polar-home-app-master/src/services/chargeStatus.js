import ApiCaller from '@/helpers/apiCaller'

export const chargeStatusService = {
    async getChargeStatus() {
        return await ApiCaller.post('status').then(response => {
            return response
        })
    }
}

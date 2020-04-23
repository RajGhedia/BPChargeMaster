import ApiCaller from '@/helpers/apiCaller'
import moment from 'moment'

export const rechargesService = {
    async get() {
        return await ApiCaller.post('recharges').then(response => {
            let data = []

            if (response.data.recharges && response.data.recharges.length > 0) {
                data = response.data.recharges.map(recharge => {
                    const start = moment
                        .unix(recharge.ts_start)
                        .format('DD/MM/YY HH:mm')
                    return {
                        start,
                        duration: recharge.duration,
                        energy: parseFloat(recharge.energy).toFixed(2),
                        cost: recharge.money_spent
                    }
                })
            }

            return { status: response.status, data: data }
        })
    }
}

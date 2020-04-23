import ApiCaller from '@/helpers/apiCaller'

export const dashboardService = {
    async getDashboard(id) {
        return await ApiCaller.post('dashboard?when=' + id).then(response => {
            if (response && response.data) {
                return {
                    success: true,
                    error: '',
                    data: {
                        charges: response.data.charges,
                        energy: parseFloat(response.data.energy).toFixed(2),
                        co2: parseFloat(response.data.co2).toFixed(2),
                        totalMoneySpent: response.data.total_money_spent
                    }
                }
            } else {
                return { success: false, error: response.message }
            }
        })
    }
}

import { dashboardService } from '@/services/dashboard'
import ApiCaller from '@/helpers/apiCaller'

describe('dashboardService', () => {
    it('calls the API', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                status: 100,
                message: 'working'
            })
        })

        await dashboardService
            .getDashboard(5)
            .then(() => {
                expect(ApiCaller.post).toBeCalledWith('dashboard?when=5')
            })
            .catch(errorLogging)
    })

    it('handles a successful API response and formats', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                success: true,
                data: {
                    charges: 12.456,
                    energy: 2.453,
                    co2: 3.123
                }
            })
        })

        await dashboardService
            .getDashboard(1)
            .then(res => {
                expect(res.data.charges).toBe(12.456)
                expect(res.data.energy).toBe('2.45')
                expect(res.data.co2).toBe('3.12')
            })
            .catch(errorLogging)
    })

    it('handles an unsuccessful API response', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                data: false,
                message: 'not working'
            })
        })

        await dashboardService
            .getDashboard(5)
            .then(res => {
                expect(res.success).toBe(false)
                expect(res.error).toBe('not working')
            })
            .catch(errorLogging)
    })
})

function errorLogging(err) {
    throw new Error('Test failed with error: ' + err)
}

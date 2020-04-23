import { chargeStatusService } from '@/services/chargeStatus'
import ApiCaller from '@/helpers/apiCaller'

describe('chargeStatusService', () => {
    it('calls the API', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                status: 100,
                message: 'working'
            })
        })

        await chargeStatusService
            .getChargeStatus()
            .then(() => {
                expect(ApiCaller.post).toBeCalledWith('status')
            })
            .catch(errorLogging)
    })

    it('handles the API response', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                data: {
                    foo: 'bar'
                },
                message: 'working'
            })
        })

        await chargeStatusService
            .getChargeStatus()
            .then(res => {
                expect(res.data.foo).toBe('bar')
            })
            .catch(errorLogging)
    })
})

function errorLogging(err) {
    throw new Error('Test failed with error: ' + err)
}

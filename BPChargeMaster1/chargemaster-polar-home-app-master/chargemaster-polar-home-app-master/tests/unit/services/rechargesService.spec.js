import { rechargesService } from '@/services/recharges'
import ApiCaller from '@/helpers/apiCaller'
import moment from 'moment'

describe('rechargesService', () => {
    it('calls the API', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                status: 100,
                message: 'Success',
                data: {
                    code: 100,
                    message: 'Success',
                    recharges: {}
                }
            })
        })

        await rechargesService
            .get()
            .then(() => {
                expect(ApiCaller.post).toBeCalledWith('recharges')
            })
            .catch(errorLogging)
    })

    it('handles a successful API response and formats', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                status: 100,
                data: {
                    recharges: [
                        {
                            ts_start: 1556695721,
                            duration: '16h 37m',
                            energy: 3.123,
                            money_spent: '0.00'
                        }
                    ]
                }
            })
        })

        await rechargesService
            .get()
            .then(res => {
                // moment.unix returns local time, so we can't test for fixed date/time
                expect(res.data[0].start).toBe(
                    moment.unix(1556695721).format('DD/MM/YY HH:mm')
                )
                expect(res.data[0].duration).toBe('16h 37m')
                expect(res.data[0].energy).toBe('3.12')
                expect(res.data[0].cost).toBe('0.00')
            })
            .catch(errorLogging)
    })

    it('handles an unsuccessful API response', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                status: 500,
                data: {
                    errorMsg: 'Not a valid response'
                }
            })
        })

        await rechargesService
            .get()
            .then(res => {
                expect(res.status).toBe(500)
                expect(res.data).toHaveLength(0)
            })
            .catch(errorLogging)
    })
})

function errorLogging(err) {
    throw new Error('Test failed with error: ' + err)
}

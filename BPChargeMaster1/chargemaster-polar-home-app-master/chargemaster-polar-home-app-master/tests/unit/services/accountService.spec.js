import { accountService } from '@/services/account'
import ApiCaller from '@/helpers/apiCaller'
import appState from '@/services/appState'

describe('accountService get api', () => {
    it('calls the API', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                status: 100,
                message: 'working'
            })
        })

        await accountService
            .getAccountInfo()
            .then(() => {
                expect(ApiCaller.post).toBeCalledWith('account')
            })
            .catch(errorLogging)
    })

    it('handles the API response and populates appState', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                data: {
                    foo: 'bar',
                    bar: 'foo',
                    xyz: 'zyx'
                },
                status: 200,
                message: 'working'
            })
        })

        await accountService
            .getAccountInfo()
            .then(res => {
                expect(appState.accountInfo).toMatchObject({
                    foo: 'bar',
                    bar: 'foo',
                    xyz: 'zyx'
                })
                expect(res.status).toBe(200)
            })
            .catch(errorLogging)
    })
})

describe('accountService update api', () => {
    it('calls the API', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                status: 100,
                message: 'working'
            })
        })

        const name = 'Andrew Johns'
        const email = 'andrew.johns@bjss.com'
        const address = '123 This Road'
        const city = 'London'
        const postcode = 'SE11 2YA'
        const country_id = 5 // United Kingdom
        const phone = '07951 123 456'

        await accountService
            .setAccountInfo(
                name,
                email,
                address,
                city,
                postcode,
                country_id,
                phone
            )
            .then(() => {
                expect(ApiCaller.post).toBeCalledWith('account/update', {
                    address: '123 This Road',
                    city: 'London',
                    country_id: 5,
                    email: 'andrew.johns@bjss.com',
                    name: 'Andrew Johns',
                    phone: '07951 123 456',
                    postcode: 'SE11 2YA'
                })
            })
            .catch(errorLogging)
    })

    it('handles the API response', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                data: {
                    code: 100,
                    data: {}
                },
                status: 200,
                message: 'Success'
            })
        })

        await accountService
            .setAccountInfo()
            .then(res => {
                expect(res.status).toBe(true)
                expect(res.message).toBe('Success')
            })
            .catch(errorLogging)
    })
})

function errorLogging(err) {
    throw new Error('Test failed with error: ' + err)
}

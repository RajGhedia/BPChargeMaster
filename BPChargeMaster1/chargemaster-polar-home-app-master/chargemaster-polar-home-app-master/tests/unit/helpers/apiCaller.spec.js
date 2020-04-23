import ApiCaller from '@/helpers/apiCaller'
import axios from 'axios'
import appState from '@/services/appState'

appState.apiKey = '1234-567'

beforeEach(() => {
    axios.reset()
})

// TODO: Add spin event emit tests
// TODO: Add caught exception tests

describe('API Caller handles error responses', () => {
    it('returns status with fixed error message if theres no data in the response', async () => {
        const getPromise = ApiCaller.post('a url', {}).catch(errorLogging)

        axios.mockSuccess({
            status: 400
        })

        await getPromise.then(res => {
            expect(res.status).toBe(400)
            expect(res.message).toBe('Error logging in')
        })
    })

    it('returns status as 401, if response from API is less than 300 but not 200', async () => {
        const getPromise = ApiCaller.post('a url', {}).catch(errorLogging)

        axios.mockSuccess({
            status: 205,
            data: {
                code: 305,
                message: 'This is a custom error response from the API'
            }
        })

        await getPromise.then(res => {
            expect(res.status).toBe(401)
            expect(res.message).toBe(
                'This is a custom error response from the API'
            )
        })
    })
})

describe('API Caller handles request exceptions', () => {
    it('hides real error and returns generic status/message for any request error', async () => {
        const getPromise = ApiCaller.post('login', {}, false).catch(
            errorLogging
        )

        axios.mockError({
            request: {
                status: 401,
                message: 'Does not matter what the message is, we wrap our own'
            }
        })

        await getPromise.then(res => {
            expect(axios.post).toBeCalled()
            expect(res.status).toBe(500)
            expect(res.message).toBe(
                'No response received fronm server. If this persists, please contact customer support.'
            )
        })
    })
})

describe('API Caller handles response exceptions', () => {
    it('returns appropriate message for 401s on login calls', async () => {
        const getPromise = ApiCaller.post('login', {}, false).catch(
            errorLogging
        )

        axios.mockError({
            response: {
                status: 401,
                message: 'Does not matter what the message is, we wrap our own'
            }
        })

        await getPromise.then(res => {
            expect(axios.post).toBeCalled()
            expect(res.status).toBe(401)
            expect(res.message).toBe('Username/password not recognised')
        })
    })

    it('returns appropriate message for 401s on all other API calls', async () => {
        const getPromise = ApiCaller.post('account', {}, false).catch(
            errorLogging
        )

        axios.mockError({
            response: {
                status: 401,
                message: 'Does not matter what the message is, we wrap our own'
            }
        })

        await getPromise.then(res => {
            expect(axios.post).toBeCalled()
            expect(res.status).toBe(401)
            expect(res.message).toBe('Not logged in')
        })
    })

    it('hides real server response and returns "Unauthorized" for 403s', async () => {
        const getPromise = ApiCaller.post('account', {}, false).catch(
            errorLogging
        )

        axios.mockError({
            response: {
                status: 403,
                message: 'Does not matter what the message is, we wrap our own'
            }
        })

        await getPromise.then(res => {
            expect(axios.post).toBeCalled()
            expect(res.status).toBe(403)
            expect(res.message).toBe('Unauthorized')
        })
    })

    it('hides real server response and returns "Unexpected" for 500', async () => {
        const getPromise = ApiCaller.post('account', {}, false).catch(
            errorLogging
        )

        axios.mockError({
            response: {
                status: 500,
                message: 'Does not matter what the message is, we wrap our own'
            }
        })

        await getPromise.then(res => {
            expect(axios.post).toBeCalled()
            expect(res.status).toBe(500)
            expect(res.message).toBe('Unexpected server error')
        })
    })

    it('hides real server response and returns generic error for other error codes', async () => {
        const getPromise = ApiCaller.post('account', {}, false).catch(
            errorLogging
        )

        axios.mockError({
            response: {
                status: 405,
                message: 'Does not matter what the message is, we wrap our own'
            }
        })

        await getPromise.then(res => {
            expect(axios.post).toBeCalled()
            expect(res.status).toBe(405)
            expect(res.message).toBe('Error while receiving response')
        })
    })
})

describe('API Caller handles other unexpected exceptions', () => {
    it('hides real error and returns generic status/message for any unhandled error', async () => {
        const getPromise = ApiCaller.post('login', {}, false).catch(
            errorLogging
        )

        axios.mockError({
            status: 999,
            message: 'Odd error'
        })

        await getPromise.then(res => {
            expect(axios.post).toBeCalled()
            expect(res.status).toBe(500)
            expect(res.message).toBe('Unexpected server error')
        })
    })
})

describe('API Caller receives successful response', () => {
    it('calls axios with apiKey set to false', async () => {
        const getPromise = ApiCaller.post('a url', {}, false).catch(
            errorLogging
        )

        axios.mockSuccess({
            status: 200,
            data: {
                code: 100,
                message: '',
                data: {
                    statusText: 'API KEY NOT SET IN HEADER'
                }
            }
        })

        await getPromise.then(res => {
            expect(axios.defaults.headers.common['API-KEY']).toBeUndefined()
            expect(axios.post).toBeCalled()
            expect(res.status).toBe(200)
            expect(res.data.statusText).toBe('API KEY NOT SET IN HEADER')
        })
    })

    it('sets api key in header by default', async () => {
        const getPromise = ApiCaller.post('a url', {}).catch(errorLogging)

        axios.mockSuccess({
            status: 200,
            data: {
                code: 100,
                message: '',
                data: {
                    statusText: 'API KEY SET IN HEADER'
                }
            }
        })

        await getPromise.then(res => {
            expect(axios.defaults.headers.common['API-KEY']).toBe('1234-567')
            expect(axios.post).toBeCalled()
            expect(res.status).toBe(200)
            expect(res.data.statusText).toBe('API KEY SET IN HEADER')
        })
    })
})

function errorLogging(err) {
    throw new Error('Test failed with error: ' + err)
}

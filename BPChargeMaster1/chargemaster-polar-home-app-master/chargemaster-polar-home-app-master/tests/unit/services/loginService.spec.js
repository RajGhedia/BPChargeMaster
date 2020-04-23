import { loginService } from '@/services/Login'
import ApiCaller from '@/helpers/apiCaller'
import appState from '@/services/appState'

beforeEach(() => {
    appState.reset()
})

describe('loginService', () => {
    it('calls the API with API key param set to false', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                status: 100,
                message: 'working'
            })
        })

        await loginService
            .login('user', 'pass')
            .then(() => {
                expect(ApiCaller.post).toBeCalledWith(
                    'login',
                    { email: 'user', password: 'pass' },
                    false
                )
            })
            .catch(errorLogging)
    })

    it('handles an API response with data and populates appState', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                data: {
                    foo: 'bar',
                    bar: 'foo',
                    xyz: 'zyx',
                    consumer: {
                        apiKey: 'aBcdeFGhiJkLMNopqRsTuVWXyZ'
                    }
                },
                code: 100,
                message: 'Success'
            })
        })

        await loginService
            .login('user', 'pass')
            .then(res => {
                expect(appState.loginInfo).toMatchObject({
                    foo: 'bar',
                    bar: 'foo',
                    xyz: 'zyx'
                })
                expect(res.success).toBe(true)
                expect(res.message).toBe('')
            })
            .catch(errorLogging)
    })

    it('handles an API response with no data and returns an error state', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                code: 401,
                message: 'No entry'
            })
        })

        await loginService
            .login('user', 'pass')
            .then(res => {
                expect(appState.loginInfo).toBeNull()
                expect(res.success).toBe(false)
                expect(res.message).toBe('No entry')
            })
            .catch(errorLogging)
    })

    it('catches potential exceptions and returns an error state', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.reject({
                code: 401,
                message: 'Something went wrong'
            })
        })

        await loginService
            .login('user', 'pass')
            .then(res => {
                expect(appState.loginInfo).toBeNull()
                expect(res.success).toBe(false)
                expect(res.message).toBe('Something went wrong')
            })
            .catch(errorLogging)
    })
})

function errorLogging(err) {
    throw new Error('Test failed with error: ' + err)
}

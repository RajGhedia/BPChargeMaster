import { changePasswordService } from '@/services/changePassword'
import ApiCaller from '@/helpers/apiCaller'

describe('changePasswordService', () => {
    it('calls the API', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                status: 100,
                message: 'working'
            })
        })

        await changePasswordService
            .setPassword('old', 'new')
            .then(() => {
                expect(ApiCaller.post).toBeCalledWith('password/update', {
                    oldpass: 'old',
                    password: 'new'
                })
            })
            .catch(errorLogging)
    })

    it('handles a successful API response', async () => {
        ApiCaller.post = jest.fn(() => {
            return Promise.resolve({
                data: true,
                message: 'working'
            })
        })

        await changePasswordService
            .setPassword('old', 'new')
            .then(res => {
                expect(res.status).toBe(true)
                expect(res.message).toBe('working')
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

        await changePasswordService
            .setPassword('old', 'new')
            .then(res => {
                expect(res.status).toBe(false)
                expect(res.message).toBe('not working')
            })
            .catch(errorLogging)
    })
})

function errorLogging(err) {
    throw new Error('Test failed with error: ' + err)
}

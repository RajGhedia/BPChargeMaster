import { contentService } from '@/services/content'
import axios from 'axios'
import EventBus from '@/event-bus'

EventBus.$emit = jest.fn()

describe('content service', () => {
    it('calls the API', async () => {
        const caller = contentService.getContent('a-page-id')

        axios.mockSuccess({
            status: 200,
            data: {}
        })

        caller
            .then(() => {
                expect(axios.get).toBeCalledWith(
                    'https://bpchargemaster.com/smartapp/smartpage/a-page-id'
                )
            })
            .catch(errorLogging)
    })

    it('handles a successful API response and formats', async () => {
        const caller = contentService.getContent('a-page-id')

        axios.mockSuccess({
            status: 100,
            data: 'some html\n\nextends over multiple lines<h1>With title</h1>'
        })

        caller
            .then(res => {
                expect(res).toBe(
                    'some html<br /><br />extends over multiple lines<h1>With title</h1>'
                )
                expect(EventBus.$emit).toHaveBeenCalledWith('spin', true)
                expect(EventBus.$emit).toHaveBeenCalledWith('spin', false)
            })
            .catch(errorLogging)
    })

    it('handles an unsuccessful API response', async () => {
        const caller = contentService.getContent('a-page-id')

        axios.mockError({
            status: 403,
            data: 'some html\n\nextends over multiple lines<h1>With title</h1>'
        })

        caller
            .then(res => {
                expect(res).toBe('Error retrieving content')
                expect(EventBus.$emit).toHaveBeenCalledWith('spin', true)
                expect(EventBus.$emit).toHaveBeenCalledWith('spin', false)
            })
            .catch(errorLogging)
    })
})

function errorLogging(err) {
    throw new Error('Test failed with error: ' + err)
}

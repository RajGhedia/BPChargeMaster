import axios from 'axios'
import EventBus from '@/event-bus'
export const contentService = {
    async getContent(id) {
        // uses a different API to the other services
        EventBus.$emit('spin', true)
        const instance = axios.create({
            baseURL: '',
            transformRequest: [
                (data, headers) => {
                    delete headers.common.Authorization
                    delete headers.common['API-KEY']
                    return data
                }
            ]
        })

        return await instance
            .get(`https://bpchargemaster.com/smartapp/smartpage/${id}`)
            .then(res => {
                EventBus.$emit('spin', false)
                return this.format(res.data)
            })
            .catch(() => {
                EventBus.$emit('spin', false)
                return 'Error retrieving content'
            })
    },

    format(text) {
        return text.replace(/(?:\r\n|\r|\n)/g, '<br />')
    }
}

import axios from 'axios'
import appState from '@/services/appState'
import EventBus from '@/event-bus'

const BASE_URL = process.env.VUE_APP_API_URL

axios.defaults.baseURL = BASE_URL
axios.defaults.headers.common['Authorization'] =
    process.env.VUE_APP_API_AUTHORIZATION_KEY
axios.defaults.timeout = 5000

export const API_STATUS_SUCCESS = 100

export default class ApiCaller {
    constructor() {}

    static async post(endpoint, payload = null, sendApiKey = true) {
        if (sendApiKey) {
            axios.defaults.headers.common['API-KEY'] = appState.apiKey
        }

        EventBus.$emit('spin', true)
        return await axios
            .post(`${endpoint}`, payload)
            .then(response => {
                EventBus.$emit('spin', false)
                let data = response.data
                if (!data) {
                    return {
                        status: response.status,
                        message: 'Error logging in'
                    }
                }
                if (
                    parseInt(data.code) !== API_STATUS_SUCCESS ||
                    response.status !== 200
                ) {
                    return {
                        status: response.status < 300 ? 401 : response.status,
                        message: response.data.message
                    }
                }

                return {
                    data: data.data,
                    status: response.status,
                    message: ''
                }
            })
            .catch(error => {
                EventBus.$emit('spin', false)

                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx

                    // eslint-disable-next-line no-console
                    //console.log(error.response.data);
                    // eslint-disable-next-line no-console
                    //console.log(error.response.status);
                    // eslint-disable-next-line no-console
                    //console.log(error.response.headers);

                    const result = {
                        status: error.response.status,
                        message: 'Server error'
                    }

                    switch (error.response.status) {
                        case 401:
                            result.message =
                                endpoint.indexOf('login') === -1
                                    ? 'Not logged in'
                                    : 'Username/password not recognised'
                            break
                        case 403:
                            result.message = 'Unauthorized'
                            break
                        case 500:
                            result.message = 'Unexpected server error'
                            break
                        default:
                            result.message = 'Error while receiving response'
                            break
                    }
                    return result
                } else if (error.request) {
                    // The request was made but no response was received
                    // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                    // http.ClientRequest in node.js

                    // eslint-disable-next-line no-console
                    //console.log(error.request);

                    return {
                        status: 500,
                        message:
                            'No response received fronm server. If this persists, please contact customer support.'
                    }
                } else {
                    // Something happened in setting up the request that triggered an Error
                    // eslint-disable-next-line no-console
                    //console.log('Error', error.message);
                    return {
                        status: 500,
                        message: 'Unexpected server error'
                    }
                }
            })
    }


    static async postForm(endpoint, payload = null, sendApiKey = true) {
        if (sendApiKey) {
            axios.defaults.headers.common['API-KEY'] = appState.apiKey
        }

        EventBus.$emit('spin', true)
        return await axios
            .post(`${endpoint}`, payload, {headers: {'Content-Type':'application/x-www-form-urlencoded'}})
            .then(response => {
                EventBus.$emit('spin', false)
                let data = response.data
                if (!data) {
                    return {
                        status: response.status,
                        message: 'Error logging in'
                    }
                }
                if (
                    parseInt(data.code) !== API_STATUS_SUCCESS ||
                    response.status !== 200
                ) {
                    return {
                        status: response.status < 300 ? 401 : response.status,
                        message: response.data.message
                    }
                }

                return {
                    data: data.data,
                    status: response.status,
                    message: ''
                }
            })
            .catch(error => {
                EventBus.$emit('spin', false)

                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx

                    // eslint-disable-next-line no-console
                    //console.log(error.response.data);
                    // eslint-disable-next-line no-console
                    //console.log(error.response.status);
                    // eslint-disable-next-line no-console
                    //console.log(error.response.headers);

                    const result = {
                        status: error.response.status,
                        message: 'Server error'
                    }

                    switch (error.response.status) {
                        case 401:
                            result.message =
                                endpoint.indexOf('login') === -1
                                    ? 'Not logged in'
                                    : 'Username/password not recognised'
                            break
                        case 403:
                            result.message = 'Unauthorized'
                            break
                        case 500:
                            result.message = 'Unexpected server error'
                            break
                        default:
                            result.message = 'Error while receiving response'
                            break
                    }
                    return result
                } else if (error.request) {
                    // The request was made but no response was received
                    // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                    // http.ClientRequest in node.js

                    // eslint-disable-next-line no-console
                    //console.log(error.request);

                    return {
                        status: 500,
                        message:
                            'No response received fronm server. If this persists, please contact customer support.'
                    }
                } else {
                    // Something happened in setting up the request that triggered an Error
                    // eslint-disable-next-line no-console
                    //console.log('Error', error.message);
                    return {
                        status: 500,
                        message: 'Unexpected server error'
                    }
                }
            })
    }
}

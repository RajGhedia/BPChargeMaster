const APIKEY_KEY = 'apiKey'
const LAST_LOGIN_KEY = 'lastLogin'
const CONSUMER_INFO_KEY = 'consumerInfo'
const ACCOUNT_INFO_KEY = 'accountInfo'

const storage = window.localStorage

const appState = {
    // This is the response to the login request
    set loginInfo(info) {
        storage.setItem(CONSUMER_INFO_KEY, JSON.stringify(info))
        storage.setItem(APIKEY_KEY, info.consumer.apikey)
        storage.setItem(LAST_LOGIN_KEY, Date.now())
    },
    get loginInfo() {
        return JSON.parse(storage.getItem(CONSUMER_INFO_KEY))
    },
    set apiKey(apiKey) {
        storage.setItem(APIKEY_KEY, apiKey)
    },
    get apiKey() {
        return storage.getItem(APIKEY_KEY)
    },

    set accountInfo(info) {
        storage.setItem(ACCOUNT_INFO_KEY, JSON.stringify(info))
    },

    get accountInfo() {
        return JSON.parse(storage.getItem(ACCOUNT_INFO_KEY))
    },

    get hoursToFullCharge() {
        return 8 // TODO: Get this from API
    },

    reset() {
        storage.setItem(CONSUMER_INFO_KEY, null)
        storage.setItem(APIKEY_KEY, null)
        storage.setItem(LAST_LOGIN_KEY, null)
        storage.setItem(ACCOUNT_INFO_KEY, null)
    }
}

export default appState

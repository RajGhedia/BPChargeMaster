import appState from './appState'
import ApiCaller from '@/helpers/apiCaller'

export const loginService = {
    async login(username, password) {
        return await ApiCaller.post(
            'login',
            { email: username, password: password },
            false
        )
            .then(response => {
                if (response && response.data !== undefined) {
                    appState.loginInfo = response.data

                    return {
                        success: true,
                        message: ''
                    }
                } else {
                    if (!response.data) {
                        return {
                            success: false,
                            message: response.message
                        }
                    }
                }
            })
            .catch(error => {
                return {
                    success: false,
                    message: error.message || error
                }
            })
    }
}

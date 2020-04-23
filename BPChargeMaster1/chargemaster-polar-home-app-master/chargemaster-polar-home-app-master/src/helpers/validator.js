export default class Validator {
    static isValidEmail(email) {
        //eslint-disable-next-line no-useless-escape
        const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        return re.test(email) || 'Does not appear to be a valid email address'
    }

    static isMinPassword(password) {
        return (
            (!!password && password.length >= 8) ||
            'Minimum password length not met'
        )
    }

    static isValidPhoneNumberFormat(val) {
        const re = /^\+?(?:\d\s?){10,12}$/
        return (
            re.test(val) ||
            'Phone number can only contain numbers and be between 10-12 digits'
        )
    }

    static mustMatch(value1, value2, message) {
        return value1 === value2 || message + ' must match'
    }

    static required(val) {
        return !!val || 'Required'
    }
}

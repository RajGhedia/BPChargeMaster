import Validator from '@/helpers/validator'

describe('email validator', () => {
    it('validates a valid email address with just ascii characters', () => {
        let result = Validator.isValidEmail('dom.sparks@bjss.com')
        expect(result).toBe(true)
    })

    it('validates a valid email address with special characters', () => {
        let result = Validator.isValidEmail('user@g.com')
        expect(result).toBe(true)
    })

    it('rejects an invalid email address with no domain', () => {
        let result = Validator.isValidEmail('user@g.')
        expect(result).toBe('Does not appear to be a valid email address')
    })

    it('rejects an invalid email address with spaces in', () => {
        let result = Validator.isValidEmail('us er@g.com')
        expect(result).toBe('Does not appear to be a valid email address')
    })
})

describe('required validator', () => {
    it('returns an error if value is empty', () => {
        let result = Validator.required('')
        expect(result).toBe('Required')
    })

    it('returns true if value is not empty', () => {
        let result = Validator.required('a value')
        expect(result).toBe(true)
    })
})

describe('match validator', () => {
    it("returns an error if values don't match", () => {
        let result = Validator.mustMatch('value 1', 'value 2 ', 'these fields')
        expect(result).toBe('these fields must match')
    })

    it("returns an error if values don't match and one is null", () => {
        let result = Validator.mustMatch('value 1', null, 'these fields')
        expect(result).toBe('these fields must match')
    })

    it('returns true if values match', () => {
        let result = Validator.mustMatch('a value', 'a value', 'these fields')
        expect(result).toBe(true)
    })
})

describe('min password validator', () => {
    it('returns an error if the length is less than 8', () => {
        let result = Validator.isMinPassword('passwor')
        expect(result).toBe('Minimum password length not met')
    })

    it('returns true password is at least 8 characters', () => {
        let result = Validator.isMinPassword('password')
        expect(result).toBe(true)
    })

    it('returns true if values match', () => {
        let result = Validator.mustMatch('a value', 'a value', 'these fields')
        expect(result).toBe(true)
    })
})

describe('phone validator', () => {
    const expectedFailMessage =
        'Phone number can only contain numbers and be between 10-12 digits'

    it('rejects numbers less than 10 digits long', () => {
        let result = Validator.isValidPhoneNumberFormat('123456789')
        expect(result).toBe(expectedFailMessage)
    })

    it('rejects numbers more than 12 digits long', () => {
        let result = Validator.isValidPhoneNumberFormat('1234567890123')
        expect(result).toBe(expectedFailMessage)
    })

    it('rejects any value with letetrs in it', () => {
        let result = Validator.isValidPhoneNumberFormat('abcdefghij')
        expect(result).toBe(expectedFailMessage)
    })

    it('accepts a value starting with a plus sign', () => {
        let result = Validator.isValidPhoneNumberFormat('+33208558511')
        expect(result).toBe(true)
    })

    it('accepts a value with spaces in it', () => {
        let result = Validator.isValidPhoneNumberFormat('07951 530 470')
        expect(result).toBe(true)
    })

    it('accepts a value of 10 digits', () => {
        let result = Validator.isValidPhoneNumberFormat('07951530470')
        expect(result).toBe(true)
    })

    it('accepts a value of 12 digits', () => {
        let result = Validator.isValidPhoneNumberFormat('123456789012')
        expect(result).toBe(true)
    })

    it('should accept a value with spaces and plus signs', () => {
        let result = Validator.isValidPhoneNumberFormat('+44 208 556 5412')
        expect(result).toBe(true)
    })
})

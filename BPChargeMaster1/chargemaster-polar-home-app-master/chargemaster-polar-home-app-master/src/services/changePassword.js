import ApiCaller from '@/helpers/apiCaller'

export const changePasswordService = {
    async setPassword(oldPassword, newPassword) {
        return await ApiCaller.post('password/update', {
            oldpass: oldPassword,
            password: newPassword
        }).then(response => {
            return { status: !!response.data, message: response.message }
        })
    }
}

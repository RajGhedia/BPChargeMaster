import ApiCaller from '@/helpers/apiCaller'

export const scheduleService = {
    async get() {
        return await ApiCaller.post('schedule').then(response => {
            return response
        })
    },

    getChargeMeBy(schedules) {
        return schedules && schedules.filter(s => s.fillmeby === 1)
    },
    getChargingBlocks(schedules) {
        return schedules && schedules.filter(s => s.fillmeby === 0)
    },

    async createFillMeBy(name, endHour, endMin, dow) {
        const fd=new FormData()
        fd.set('schedule[name]', name)
        fd.set('schedule[endhour]',endHour)
        fd.set('schedule[endmin]', endMin)
        fd.set('schedule[fillmeby]', 1)
        fd.set('schedule[chargelevel]', 3)  // Fast

        for (let i = 0; i < dow.length; i++) {
            fd.set(`schedule[dow][${i}]`,dow[i]);
        }

        return await ApiCaller.post('schedule/update', fd)
            .then(response => {
                return response
            })
    }
}

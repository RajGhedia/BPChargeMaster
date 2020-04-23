import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import 'vuetify/src/stylus/app.styl'

Vue.use(Vuetify, {
    iconfont: 'md',
    theme: {
        // primary: '#FFFFFF',//'#0099FF',
        // secondary: '#06AEEF',
        primary: '#06AEEF',
        secondary: '#FFFFFF',
        accent: '#99CC02',
        error: '#E84F4F', // TODO
        info: '#616365', // TODO
        success: '#009900', // TODO
        warning: '#FFBC3D' // TODO
    }
})

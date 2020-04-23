import Login from '@/pages/Login'
import Schedule from '@/pages/Schedule'
import History from '@/pages/History'
import Account from '@/pages/Account'
import ChangePassword from '@/pages/ChangePassword'
import Contact from '@/pages/Contact'
import Content from '@/pages/Content'

export default [
    { path: '/login', name: 'login', component: Login },
    { path: '/schedule', name: 'schedule', component: Schedule },
    { path: '/history', name: 'history', component: History },
    {
        path: '/change-password',
        name: 'changePassword',
        component: ChangePassword
    },
    { path: '/account', name: 'account', component: Account },
    { path: '/contact', name: 'contact', component: Contact },
    {
        path: '/privacy',
        name: 'privacy',
        meta: { title: 'Privacy policy', query: 'app-privacy-policy' },
        component: Content
    },
    {
        path: '/faqs',
        name: 'faqs',
        meta: { title: 'FAQs', query: 'app-faqs' },
        component: Content
    },
    //   beforeEnter: (to, from, next) => {
    //     if (!appState.apiKey) {
    //       next('/login')
    //     } else {
    //       next()
    //     }
    //   }
    { path: '*', redirect: '/login' }
]

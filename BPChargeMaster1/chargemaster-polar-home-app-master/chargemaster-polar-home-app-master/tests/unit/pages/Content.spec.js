import Content from '@/pages/Content.vue'
import { shallowMount, createLocalVue } from '@vue/test-utils'
import Vue from 'vue'
import Vuetify from 'vuetify'
import { contentService } from '@/services/content'

const localVue = createLocalVue()
localVue.config.productionTip = false
Vue.use(Vuetify) // Can't use Vuetify on localVue, not supported currently

describe('Content.vue', () => {
    it('calls the content service on mounting', async () => {
        const $route = {
            path: '/some/path',
            meta: {
                title: 'test title',
                query: 'app-test'
            }
        }

        contentService.getContent = jest.fn()

        const wrapper = shallowMount(Content, {
            localVue: localVue,
            mocks: {
                $route,
                contentService
            }
        })

        expect(wrapper.vm.$route.meta.title).toBe('test title')
        expect(contentService.getContent).toHaveBeenCalledWith('app-test')
    })
})

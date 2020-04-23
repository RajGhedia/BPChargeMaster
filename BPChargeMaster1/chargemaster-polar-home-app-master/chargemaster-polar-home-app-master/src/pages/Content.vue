<template>
    <v-container ma-0 pa-0>
        <TheToolbar showClose backTo="account">{{
            $route.meta.title
        }}</TheToolbar>
        <v-layout text-xs-left wrap>
            <v-flex xs10 offset-xs1 pt-3>
                <span v-html="content"></span>
            </v-flex>
        </v-layout>
    </v-container>
</template>
<script>
import { contentService } from '@/services/content'
import TheToolbar from '@/components/TheToolbar'
export default {
    async mounted() {
        this.content = await contentService.getContent(this.$route.meta.query)
    },
    components: {
        TheToolbar
    },
    data() {
        return {
            content: ''
        }
    }
}
</script>

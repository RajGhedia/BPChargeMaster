<template>
    <v-toolbar>
        <v-container pa-0 ma-0>
            <v-layout>
                <v-flex xs2 pt-4 text-xs-center>
                    <v-icon v-if="showMenu" @click.stop="openDrawer"
                        >menu</v-icon
                    >
                    <router-link v-if="showBack" :to="backTo">
                        <v-icon>chevron_left</v-icon>
                    </router-link>
                </v-flex>
                <v-flex xs8 text-xs-center class="title" py-4>
                    <slot></slot>
                </v-flex>
                <v-flex xs2 pt-4 text-xs-center>
                    <router-link v-if="showClose" :to="backTo">
                        <v-icon>close</v-icon>
                    </router-link>
                </v-flex>
            </v-layout>
        </v-container>
    </v-toolbar>
</template>
<script>
import EventBus from '@/event-bus'
export default {
    props: {
        showMenu: Boolean,
        showClose: Boolean,
        showBack: Boolean,
        backTo: String
    },
    methods: {
        openDrawer() {
            EventBus.$emit('openDrawer')
        }
    }
}
</script>
<style>
.v-image__image--cover {
    background-size: initial !important;
}

.title {
    font-size: 1.3rem;
}

a {
    text-decoration: none;
}
</style>

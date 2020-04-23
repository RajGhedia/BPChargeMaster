<template>
    <v-navigation-drawer v-model="open" absolute temporary>
        <v-layout>
            <v-flex xs7 offset-xs1 pt-3 pb-3>
                <v-img :src="require('@/assets/logo.svg')" contain></v-img>
            </v-flex>
        </v-layout>
        <v-divider></v-divider>
        <v-list two-line>
            <template v-for="(item, index) in items">
                <v-list-tile :key="item.icon" ripple :to="item.to">
                    <v-list-tile-avatar>
                        <v-img
                            :src="require(`@/assets/${item.icon}.svg`)"
                            class="icon"
                        ></v-img>
                    </v-list-tile-avatar>

                    <v-list-tile-content>
                        <v-list-tile-title class="primary--text">{{
                            item.title
                        }}</v-list-tile-title>
                    </v-list-tile-content>

                    <v-list-tile-action>
                        <v-btn icon ripple :to="item.to">
                            <v-icon color="primary">chevron_right</v-icon>
                        </v-btn>
                    </v-list-tile-action>
                </v-list-tile>
                <v-divider :key="index"></v-divider>
            </template>
        </v-list>
    </v-navigation-drawer>
</template>
<script>
import EventBus from '@/event-bus'
export default {
    methods: {
        openDrawer() {
            this.open = true
        }
    },
    data() {
        return {
            open: false,
            items: [
                { icon: 'info', title: "FAQ's", to: '/faqs' },
                { icon: 'phone', title: 'Contact Us', to: '/contact' },
                { icon: 'polar-icon', title: 'Polar Plus Network' },
                { icon: 'tc', title: "Privacy Policy, T&C's", to: '/privacy' }
            ]
        }
    },
    created() {
        EventBus.$on('openDrawer', this.openDrawer)
    },
    beforeDestroy() {
        EventBus.$off('openDrawer', this.openDrawer)
    }
}
</script>

<template>
    <v-layout mb-3 class="kpi">
        <v-flex xs8 offset-xs1 pt-3>
            <div class="headline ma-0 pa-0">{{ amount }}</div>
            <p class="subheading pa-0 mb-2">{{ description }}</p>
        </v-flex>
        <v-flex xs4 pt-1 pr-3>
            <v-img
                class="icon"
                :src="require(`@/assets/${icon}.svg`)"
                contain
            ></v-img>
        </v-flex>
    </v-layout>
</template>
<script>
export default {
    props: ['description', 'amount', 'icon']
}
</script>
<style>
.kpi p {
    color: rgba(0, 0, 0, 0.6);
}
</style>

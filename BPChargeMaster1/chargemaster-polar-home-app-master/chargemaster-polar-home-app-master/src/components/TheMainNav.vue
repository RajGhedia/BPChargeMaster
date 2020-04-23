<template>
    <v-bottom-nav
        app
        :value="true"
        fixed
        :active.sync="activeTab"
        color="white"
        height="60"
    >
        <v-btn
            color="primary"
            flat
            :ripple="false"
            value="schedule"
            to="/schedule"
        >
            <span>Schedule</span>
            <svg
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                width="24"
                height="24"
                viewBox="0 0 24 24"
            >
                <defs>
                    <path
                        id="aa"
                        d="M18.625 4.5H16.75V2.469A.47.47 0 0 0 16.281 2h-.312a.47.47 0 0 0-.469.469V4.5H8V2.469A.47.47 0 0 0 7.531 2H7.22a.47.47 0 0 0-.469.469V4.5H4.875C3.84 4.5 3 5.34 3 6.375v13.75C3 21.16 3.84 22 4.875 22h13.75c1.035 0 1.875-.84 1.875-1.875V6.375c0-1.035-.84-1.875-1.875-1.875zM4.875 5.75h13.75c.344 0 .625.281.625.625V8.25h-15V6.375c0-.344.281-.625.625-.625zm13.75 15H4.875a.627.627 0 0 1-.625-.625V9.5h15v10.625a.627.627 0 0 1-.625.625zM8.781 14.5H7.22a.47.47 0 0 1-.469-.469V12.47A.47.47 0 0 1 7.219 12H8.78a.47.47 0 0 1 .469.469v1.562a.47.47 0 0 1-.469.469zm3.75 0H10.97a.47.47 0 0 1-.469-.469V12.47a.47.47 0 0 1 .469-.469h1.562a.47.47 0 0 1 .469.469v1.562a.47.47 0 0 1-.469.469zm3.75 0H14.72a.47.47 0 0 1-.469-.469V12.47a.47.47 0 0 1 .469-.469h1.562a.47.47 0 0 1 .469.469v1.562a.47.47 0 0 1-.469.469zm-3.75 3.75H10.97a.47.47 0 0 1-.469-.469V16.22a.47.47 0 0 1 .469-.469h1.562a.47.47 0 0 1 .469.469v1.562a.47.47 0 0 1-.469.469zm-3.75 0H7.22a.47.47 0 0 1-.469-.469V16.22a.47.47 0 0 1 .469-.469H8.78a.47.47 0 0 1 .469.469v1.562a.47.47 0 0 1-.469.469zm7.5 0H14.72a.47.47 0 0 1-.469-.469V16.22a.47.47 0 0 1 .469-.469h1.562a.47.47 0 0 1 .469.469v1.562a.47.47 0 0 1-.469.469z"
                    />
                </defs>
                <g fill="none" fill-rule="evenodd">
                    <mask id="ab" fill="#fff">
                        <use xlink:href="#aa" />
                    </mask>
                    <g fill="#000" fill-opacity=".54" mask="url(#ab)">
                        <path d="M0 0h24v24H0z" />
                    </g>
                </g>
            </svg>
        </v-btn>

        <v-btn
            color="primary"
            flat
            :ripple="false"
            value="history"
            to="/history"
        >
            <span>History</span>
            <svg
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                width="24"
                height="24"
                viewBox="0 0 24 24"
            >
                <defs>
                    <path
                        id="ba"
                        d="M2.484 2.645h.403c.267 0 .484.217.484.484v3.816A9.994 9.994 0 0 1 12.032 2c5.5.017 9.977 4.517 9.968 10.016C21.991 17.532 17.518 22 12 22a9.963 9.963 0 0 1-6.7-2.577.485.485 0 0 1-.02-.703l.285-.285a.483.483 0 0 1 .664-.018A8.595 8.595 0 0 0 12 20.629c4.748 0 8.629-3.844 8.629-8.629 0-4.748-3.844-8.629-8.629-8.629a8.627 8.627 0 0 0-7.673 4.677H8.29c.268 0 .484.217.484.484v.403a.484.484 0 0 1-.484.484H2.484A.484.484 0 0 1 2 8.935V3.13c0-.267.217-.484.484-.484zm12.97 12.711l.19-.26a.484.484 0 0 0-.107-.677l-2.892-2.102V6.355a.484.484 0 0 0-.484-.484h-.322a.484.484 0 0 0-.484.484v6.619l3.423 2.489c.216.157.518.11.676-.107z"
                    />
                </defs>
                <g fill="none" fill-rule="evenodd">
                    <mask id="bb" fill="#fff">
                        <use xlink:href="#ba" />
                    </mask>
                    <g fill="#000" fill-opacity=".54" mask="url(#bb)">
                        <path d="M0 0h24v24H0z" />
                    </g>
                </g>
            </svg>
        </v-btn>

        <v-btn
            color="primary"
            flat
            :ripple="false"
            value="account"
            to="/account"
        >
            <span>Account</span>
            <svg
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                width="24"
                height="24"
                viewBox="0 0 24 24"
            >
                <defs>
                    <path
                        id="ca"
                        d="M12.337 10.315a3.44 3.44 0 0 0 3.437-3.437 3.44 3.44 0 0 0-3.437-3.437A3.44 3.44 0 0 0 8.9 6.878a3.44 3.44 0 0 0 3.437 3.437zm0-8.315a4.84 4.84 0 0 1 4.834 4.834c0 2.678-2.169 4.856-4.834 4.856a4.84 4.84 0 0 1-4.834-4.834C7.503 4.177 9.672 2 12.337 2zm8.36 18.559v.732c0 .39-.32.709-.71.709-.39 0-.71-.32-.71-.71v-.731a6.592 6.592 0 0 0-6.585-6.586h-.688A6.592 6.592 0 0 0 5.42 20.56v.732c0 .39-.32.709-.71.709-.39 0-.709-.32-.709-.71v-.731c0-4.415 3.59-8.005 8.004-8.005h.688c4.414 0 8.004 3.59 8.004 8.005z"
                    />
                </defs>
                <g fill="none" fill-rule="evenodd">
                    <mask id="cb" fill="#fff">
                        <use xlink:href="#ca" />
                    </mask>
                    <g fill="#000" fill-opacity=".54" mask="url(#cb)">
                        <path d="M0 0h24v24H0z" />
                    </g>
                </g>
            </svg>
        </v-btn>
    </v-bottom-nav>
</template>
<script>
export default {
    data() {
        return {
            activeTab: this.active
        }
    },
    props: ['active']
}
</script>

<style>
.v-btn--active g {
    fill: #06aeef;
}

.v-btn span {
    padding: 0.3rem;
}
</style>

<template>
  <v-container fluid ma-0 pa-0>
    <v-layout class="charge-status pa-2">
      <v-flex xs8 pt-2 pl-1>
        <div
          class="headline ma-0 pa-0"
        >{{isCharging ? "Your EV is charging" : "Your EV is not charging"}}</div>
        <p class="subheading pa-0 mb-2">{{description}}</p>
      </v-flex>
      <v-flex align-end xs4 pt-1 pr-1>
        <v-img :src="getChargeImgUrl()" contain></v-img>
      </v-flex>
    </v-layout>
    <div class="my-3 charge-status-sep">
      <hr>
    </div>
    <div v-if="!isEditing">
      <div v-if="schedules.length > 0" class="schedule-list">
        <ChargingBlocks :schedules="schedules"/>
      </div>
      <v-layout v-else align-center column pa-5 class="no-sched">
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
            ></path>
          </defs>
          <g fill="none" fill-rule="evenodd">
            <mask id="ab" fill="#fff">
              <use xlink:href="#aa"></use>
            </mask>
            <g fill="#000" fill-opacity="0.54" mask="url(#ab)">
              <path d="M0 0h24v24H0z"></path>
            </g>
          </g>
        </svg>
        <div class="subheading">No schedules yet</div>
        <v-flex
          px-2
          class="caption text-xs-center"
        >Create a new charging slot so you can charge at certain times of the day</v-flex>
      </v-layout>
      <v-layout justify-center>
        <v-btn outline color="primary" @click.native="createBlock">New Charge Schedule</v-btn>
      </v-layout>
    </div>
    <CreateRegChargingBlock v-else></CreateRegChargingBlock>
    <TheMainNav/>
  </v-container>
</template>

<script>
import TheMainNav from "@/components/TheMainNav.vue";
import ChargingBlocks from "@/components/ChargingBlocks.vue";
import CreateRegChargingBlock from "@/components/CreateRegChargingBlock.vue";
import EventBus from "@/event-bus";
import { chargeStatusService } from "@/services/chargeStatus";
import { scheduleService } from "@/services/schedule";

export default {
  scheduleAddedEvent:'scheduleAdded',
  created() {
        EventBus.$on('scheduleAdded', this.onScheduleAdded)
        EventBus.$on('scheduleAddCancel', this.onScheduleAddCancel)
  },
  beforeDestroy() {
        EventBus.$off('scheduleAdded')
        EventBus.$off('scheduleAddCancel')
  },
  async mounted() {
    try {
      const result = await this.getChargeStatus();
      if (result.success) {
        this.isCharging = result.isCharging;
      } else {
        throw result;
      }

      const result2 = await this.getSchedules();
      if (!result.success) {
        throw result2;
      }
    } catch (err) {
      if (err.error) {
        const errNotify = {
          message: err.error,
          type: "error"
        };
        EventBus.$emit("notify", errNotify);
      }

      if (err.status == 401 || err.status == 403) {
        this.$router.replace("login");
      }
    }
  },
  components: {
    TheMainNav,
    ChargingBlocks,
    CreateRegChargingBlock
  },
  data: () => {
    return {
      isCharging: false,
      isEditing: false,
      description: "You're plugged in.",
      schedules: []
    };
  },
  methods: {

    createBlock() {
      this.isEditing = true
    },
    getChargeImgUrl() {
      const images = require.context("../assets/", false, /\.svg$/);
      return this.isCharging
        ? images("./ev-charging-icon.svg")
        : images("./ev-not-charging-icon.svg");
    },
    getChargeStatus() {
      return chargeStatusService
        .getChargeStatus()
        .then(result => {
          if (result && result.data !== undefined) {
            return { success: true, error: "", isCharging: result.data.status };
          } else {
            return {
              success: false,
              error: result.message,
              isCharging: false,
              status: result.status
            };
          }
        })
        .catch(() => {
          return {
            success: false,
            error:
              "An unexpected error has occurred whilst getting charging status",
            isCharging: false
          };
        });
    },
    onScheduleAdded(eventData) {
      this.isEditing = false
      //await this.getSchedules()

      if (eventData.success) {
        this.schedules = eventData.data.schedule
      }
    },
    onScheduleAddCancel() {
       this.isEditing = false
    },
    async getSchedules() {
      try {
        const response = await scheduleService.get();
        if (response.data) {
          this.schedules = response.data.schedule;

          return {
            success: true,
            error: ""
          };
        } else {
          this.schedules = [];

          return {
            success: false,
            error: response.message
          };
        }
      } catch (error) {
        this.schedules = [];

        return {
          success: false,
          error: "An unexpected error has occurred whilst getting schedules"
        };
      }
    }
  }
};
</script>

<style scoped>
.charge-status p,
.charge-status-sep {
  color: rgba(0, 0, 0, 0.6);
}

.schedule-list {
  /* overflow-y: auto;
  overflow-x: hidden;
  height: 350px; */
}

.no-sched svg {
  height: 66px;
  width: 77px;
}
.no-sched g {
  fill: #cccccc;
}
</style>

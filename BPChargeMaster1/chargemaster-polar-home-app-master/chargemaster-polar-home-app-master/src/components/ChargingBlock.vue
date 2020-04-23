<template>
    <v-layout mb-2 pb-0 mx-2 class="chg-block" column>
        <v-layout ml-1 ma-1 mb-0 pb-0 xs6>
          <v-flex xs9 pl-1>
              <span
                  class="dow"
                  v-for="{ i, day, selected } in dow" :key="i"
                  :class="{ 'font-weight-black': selected }">
                  {{ day }}
              </span>
          </v-flex>
        </v-layout>
          <v-layout>
            <v-flex pl-2 pa-1>
                <div class="title">{{ schedule.name }}</div>
            </v-flex>
              <v-flex xs3 pa-0>
                <v-switch
                    mr-0 pr-0 readonly color="primary" class="live-switch"
                    v-model="isLive" :label="isLive ? 'ON' : 'OFF'" />
            </v-flex>
            </v-layout>
        <v-layout column>
            <v-flex pl-2>
                <span class="caption">{{ caption }}</span>
                  <v-btn
                    ma-0 pa-0 flat small
                    color="primary" class="font-weight-bold">Edit</v-btn>
            </v-flex>
            <v-flex xs8 pa-0>

            </v-flex>

        </v-layout>
    </v-layout>
</template>

<script>
export default {
    computed: {
        isLive() {
            return parseInt(this.schedule.live) === 1 ? true : false
        },
        caption() {
            const sch = this.schedule

            // if (sch.fillmeby === 1) {
            //     return `Plug in by ${sch.timestart} for a full charge`
            // } else {
                return `${sch.timestart} - ${sch.timeend}`
            //}
        },
        dow() {
            const days = 'MTWTFSS'

            let result = []
            for (let i = 0; i < 7; i++) {
                result.push({
                    i,
                    day: days[i],
                    selected:
                        this.schedule.days.find(d => d === i) !== undefined
                })
            }

            return result
        }
    },
    props: ['schedule'],
    methods: {
        makeDow() {
            const days = 'MTWTFSS'

            for (let i = 0; i < 7; i++) {
                this.dow.push({
                    day: days[i],
                    selected: i === parseInt(this.schedule.dow)
                })
            }
        }
    }
}
</script>
<style lang="scss">
.kpi p {
    color: rgba(0, 0, 0, 0.6);
}

.chg-block {
    border: 1px solid lightgray;
        border-radius: 4px;
  &:hover {
      border: 2px solid #06aeef;
  }



  .dow {
      color: #009900;
      font-size: 12px;
      font-weight: 300;

      &.selected {
      font-weight: bold;
    }
  }

  .v-btn {
      margin: 0;
    padding: 0;
      .v-btn__content {
      padding: 0;
      }
  }
  .v-input--switch {
    margin-top: 0;

    .v-input--selection-controls__ripple {
      margin: 0;
    }
  }
  .v-input.live-switch {
    .v-input__slot {
      margin-top: 0;
      margin-bottom: 0 !important;
    }

  }
}



</style>

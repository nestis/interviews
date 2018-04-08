<template>
  <div class="home">
    <Home v-bind:candidate="test.candidate" v-bind:evaluator="test.evaluator" v-bind:time="test.minutes"></Home>
  </div>
</template>

<script>
import Home from "@/components/Home.vue";
import { HomeService } from '@/components/HomeService';

export default {
  name: "home",
  components: {
    Home
  },

  data: function() {
    return {
      test : {
        candidate: '',
        evaluator: '',
        minutes: 0
      }
    }
  },

  mounted() {
    const testToken = this.$route.query.token;
    if (testToken === undefined) {
      this.$router.push('404');
    } else {
      const homeService = new HomeService(this.$http);
      homeService.getTest(testToken).finally(() => {
        debugger;
        this.$eventHub.$emit('hideLoader')}
      ).subscribe(data => {
        data.minutes = 30;
        this.$store.dispatch('setInitData', {...data})

        this.test.candidate = data.name;
        this.test.evaluator = data.leader
        this.test.minutes = data.minutes;2

        Object.freeze(this.test);
        
      }, error => {
        switch(error.status) {
          case 204: this.$router.push('404'); break;
        }
      });
    }
  }
};
</script>

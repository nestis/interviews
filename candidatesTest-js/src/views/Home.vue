<template>
  <div class="home">
    <Home v-bind:candidate="test.candidate" v-bind:evaluator="test.evaluator" v-bind:time="test.minutes"></Home>
  </div>
</template>

<script>
import Home from "@/components/Home.vue";

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
      const url = `http://localhost:8080/test/${testToken}`;
      // Mock server response...
      setTimeout(() => {
        const response = {
          candidate: 'Carlos',
          evaluator: 'Alejandro Capel',
          minutes: 30,
          questions: []
        }
        
        const reduxData = {...response};
        this.$store.dispatch('setInitData', reduxData)

        this.test.candidate = response.candidate;
        this.test.evaluator = response.evaluator
        this.test.minutes = response.minutes;


        Object.freeze(this.test);
        this.$eventHub.$emit('hideLoader');
      }, 1000);
    }
  }
};
</script>

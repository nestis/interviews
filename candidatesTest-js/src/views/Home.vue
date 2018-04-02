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
      this.$http.get(url)
    }
  }
};
</script>

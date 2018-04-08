<template>
  <div class="test">
    <Header v-bind:minutes="minutes"></Header>
    <TestForm :questions="questions"></TestForm>
  </div>
</template>

<script>
import Header from '@/components/test/Header.vue';
import TestForm from '@/components/test/TestForm.vue';

export default {
  name: 'Test',
  components: { Header, TestForm },
  data: function() {
    return {
      minutes: 0,
      questions: []
    }
  },

  created: function() {
    const state = this.$store.state;
    this.minutes = state.minutes;
    this.questions = state.questions;
  }
}
</script>

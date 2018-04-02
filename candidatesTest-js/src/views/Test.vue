<template>
  <div class="test">
    <Header></Header>
    <TestForm></TestForm>
  </div>
</template>

<script>
import Header from '@/components/test/Header.vue';
import TestForm from '@/components/test/TestForm.vue';

export default {
  name: 'Test',
  components: { Header, TestForm }
}
</script>

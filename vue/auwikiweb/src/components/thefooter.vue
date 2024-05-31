<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'thefooter',
  props: {
    msg: String,
  },
});
</script>

<template>
  <a-layout-footer style="text-align: center">
    李伟电子书阅览.
  </a-layout-footer>
</template>

<style scoped>

</style>
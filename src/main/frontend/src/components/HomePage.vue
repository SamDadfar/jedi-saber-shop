<template>
  <div>
    <calendar id="calendar"> </calendar>
  </div>
</template>

<script>
import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
import Calendar from "./Calendar.vue";

Vue.use(VueAxios, axios);

export default {
  components: { Calendar },
  name: "HomePage",
  data() {
    this.getMessage();
    return { msg: null };
  },
  computed: {
    currentUser() {
      return false;
    },
  },
  methods: {
    getMessage() {},
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}

#calendar {
  /* background: #fbf10f; */
  max-width: 80%;
  width: 500px;
  margin: 40px auto;
}
</style>

<template>
  <div class="container">
    <h3>Padawans:</h3>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">User Name</th>
          <th scope="col">Email</th>
          <th scope="col">Force</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="padawan in padawans" v-bind:key="padawan.id">
          <th scope="row">{{ padawan.id }}</th>
          <td>{{ padawan.user.username }}</td>
          <td>{{ padawan.user.email }}</td>
          <td>{{ padawan.force }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script lang="ts">
import axios from "axios";

export default {
  name: "UsersView",
  data() {
    return {
      padawans: null,
    };
  },
  created: function () {
    axios.get("http://localhost:8080/Jedisabershop/padawan").then((res) => {
      this.padawans = res.data;
    });
  },
};
</script>

<style>
@import "bootstrap/dist/css/bootstrap.min.css";
</style>

<template>
   <div id="app">
     <Navbar :isAdmin="isAdmin"
     :isLoggedIn="isLoggedIn"
     :userName="currentUser.username"/>
    <router-view></router-view>
  </div>
</template>

<script>
import Navbar from './components/view/Navbar.vue';

export default {
  name: 'app',
  components: {
    Navbar,
  },
  computed: {
    isLoggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    currentUser() {
      return (this.$store.state.auth.data.user || {});
    },
    isAdmin() {
      if (this.currentUser && this.currentUser.roles) {
        return this.$store.state.auth.data.user.roles.includes('ADMIN');
      }
      return false;
    },
  },
};
</script>
<style>
#app2 {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
  margin-top: 20px;
}
#nav {
  padding: 30px;
  background-color: lightblue;
}
</style>

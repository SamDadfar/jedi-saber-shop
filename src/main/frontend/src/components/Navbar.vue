<template>
  <b-container>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand href="#">Navbar</b-navbar-brand>
      <b-navbar-nav>
        <b-nav-item href="#">Link 1</b-nav-item>
        <b-nav-item href="#">Link2</b-nav-item>
      </b-navbar-nav>
    </b-navbar>
  </b-container>
</template>

<script lang="ts">
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Navbar",
  data() {
    return {
      username: "sam",
    };
  },
  props: {
    isAdmin: Boolean,
    isLoggedIn: Boolean,
    userName: String,
  },
  // computed: {
  //   msg() {
  //     return "this.$store.state.auth.msg";
  //   },
  // },
  // methods: {
  //   redirectToHome() {
  //     this.$router.push({ path: "/home" });
  //   },
  //   signup() {
  //     this.$router.push({ path: "/signup" });
  //   },
  //   login() {
  //     this.$router.push("/login");
  //   },
  //   logout() {
  //     this.$store.dispatch("logout");
  //     this.$toasted.show(`${this.userName} is ${this.msg}`).goAway(1500);
  //     this.login();
  //   },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import "bootstrap/dist/css/bootstrap.min.css";

h3 {
  margin: 40px 0 0;
}
.b-dropdown-item {
  margin-inline: 2;
}
.navbar.navbar-dark.bg-dark {
  background-color: #0066a1 !important;
}

.navbar-dark .navbar-nav .nav-link {
  color: white !important;
}

.bg-primary {
  background-color: red !important;
}

.btn-secondary {
  color: #fff;
  background-color: #0066a1;
  border-color: #0066a1;
}

.btn-secondary:focus {
  color: #fff;
  background-color: #0066a1;
  border-color: #0066a1;
}

.btn-secondary:hover {
  color: #fff;
  background-color: #01446b !important;
  border-color: #01446b !important;
}

.btn-secondary:hover:focus {
  color: #fff;
  background-color: #01446b !important;
  border-color: #01446b !important;
}

.btn-secondary:active:focus {
  color: #fff;
  background-color: #01446b !important;
  border-color: #01446b !important;
}

.btn-secondary:active {
  color: #fff;
  background-color: #01446b !important;
  border-color: #01446b !important;
}
</style>

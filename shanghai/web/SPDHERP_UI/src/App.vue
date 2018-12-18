<template>
	<div id="app">
		<transition name="fade" mode="out-in"><router-view></router-view></transition>
		<loginDailog :showLoginDialog="getLoginFlag"></loginDailog>
	</div>
</template>

<script>
import loginDailog from "./components/Login4Ajax";
import { mapGetters } from 'vuex';
export default {
	name: 'app',	
	data() {
		return {
			
		}
	},
	components: { "loginDailog": loginDailog },	
	computed: {
      ...mapGetters(["getLoginFlag"])
    }
}

</script>

<style>
#app{
	height: 100%;
}
.fade-enter-active, .fade-leave-active {
  transition: opacity .5s
}
.fade-enter, .fade-leave-active {
  opacity: 0
}
</style>
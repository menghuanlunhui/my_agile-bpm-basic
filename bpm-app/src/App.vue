<template>
  <div id="app">
     <router-view/>
  </div>
</template>

<script>
import Vue from 'vue'

export default {
  name: 'App',
  data:function(){
	  return {
	  }
  },
  created:function(){
  /* 	var post = Vue.baseService.postForm(Vue.__ctx+"/login/valid",{"account":"admin","password":"1"});
	post.then(function(data,a){
		console.log(data.msg)
	}) */ 
  }
}
</script>

<style lang="less">
@import '~vux/src/styles/reset.less';

body {
  background-color: #fbf9fe;
}

</style>

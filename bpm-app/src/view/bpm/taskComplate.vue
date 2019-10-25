<template>
  <div>
  	 <x-header>{{task.name}}<router-link to="/"  slot="right"> <a class="fa fa-home fa-2x"></a> </router-link></x-header>
  	 	<div v-if="form.type=='INNER'">
			<ab-custom-form></ab-custom-form>
	   </div> 
	   <bpm-buttons :buttons="buttons"></bpm-buttons>
	   <div v-if="dynamicComponent.show"  :is="dynamicComponent.key" @cancel="dynamicComponent.show=false" @callback="dynamicComponent.callback" :show-dialog="dynamicComponent.show" :passConf="dynamicComponent.flowParam"></div>
  </div>
</template>

<script>
import Vue from 'vue'
import bpmService from '@/service/bpm/bpmService'
import formService from '@/service/form/formService'

export default {
  name: 'abCheckbox',
  methods: {
    getTaskData () {
      console.log('on item click')
    }
  },
  data :function () {
	    return {
	      task: "",
	      instance:"",
	      form:"",
	      buttons:"",
	      dynamicComponent:{
	    	  key:"opinion-dialog",
	    	  show:false
	      }
	    }
	},
  created : function(){
  	this.taskId = this.$route.query.taskId;
  	var thisVue = this;
  	bpmService.init({taskId:this.taskId,formType:"mobile"},function(data){
  		formService.initCustFormFu(data,Vue);
		thisVue.form = data.form;
		thisVue.task = data.task;
		thisVue.buttons = data.buttonList;
		thisVue.instance = data.instance;
	});
  }
  
}
</script>

<style scoped>

</style>

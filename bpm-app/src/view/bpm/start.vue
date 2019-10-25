<template>
  <div>
  	 <x-header>{{defName}}<router-link to="/"  slot="right"> <a class="fa fa-home fa-2x"></a> </router-link></x-header>
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
  },
  data :function (){
	    return {
	      defName: "",
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
  	this.defId = this.$route.query.defId;
  	this.instanceId = this.$route.query.instanceId || "";
  	var thisVue = this;
  	bpmService.init({defId:this.defId,instanceId:this.instanceId,formType:"mobile"},function(data){
  		formService.initCustFormFu(data,Vue);
		thisVue.form = data.form;
		thisVue.defName = data.defName;
		thisVue.buttons = data.buttonList;
		thisVue.instance = data.instance;
	});
  }
  
}
</script>

<style scoped>

</style>

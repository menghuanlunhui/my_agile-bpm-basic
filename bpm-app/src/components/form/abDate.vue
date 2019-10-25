<template>
	<datetime v-model="val" v-bind:start-date="startDate" :readonly="abPermission==='r'" v-bind:end-date="endDate"></datetime>
</template>

<script>
import Vue from 'vue'
import { Datetime } from 'vux'
Vue.component('datetime', Datetime)

export default {
  	props: ['value',"min","max","minformat","maxformat","cal","caltype","abPermission"],
  	data :function () {
	    return {
	    	val:this.value,
	    	startDate:"",
	    	endDate:""
	    }
	},
	mounted : function(){
		this.handleMin();
		this.handleMax();
 	},
  	methods: {
    	//计算差距 拿最小值跟自身比较
    	handleCal(){
    		if(!this.min||!this.val){
    			this.$emit('update:cal',"");
    			return;
    		}
    		var rtl = this.min.toDate(this.minformat).between(this.val.toDate(this.format),this.caltype);
    		this.$emit('update:cal',rtl);
    	},
    	handleMin(){
    		this.handleCal();
 			if(!this.min){
 				this.startDate = "";
 				return;
 			}
 			var date = this.min.toDate(this.minformat);
 			this.startDate = date.format("yyyy-MM-dd");
    	},
    	handleMax(){
    		if(!this.max){
 				this.endDate = "";
 				return;
 			}
 			var date = this.max.toDate(this.maxformat);
 			this.endDate = date.format("yyyy-MM-dd");
    	}
  	},
 	watch : {
 		val : function(newVal,oldVal){
 			this.$emit('input', this.val);
 			this.handleCal();
 		},
 		min : function(newVal,oldVal){
 			this.handleMin();
 		},
 		max : function(newVal,oldVal){
 			this.handleMax();
 		}
 	}
  
}
</script>

<template>
<div>
	<ab-upload v-model="files" :permission="permission">
		<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary fa fa-upload">上传</a>
	</ab-upload>
	--{{files}}
</div>
</template>

<script>

import Vue from 'vue';
import AbUpload from '@/components/form/abUpload.vue';
Vue.component('abUpload', AbUpload);

export default {
	data: function () {
	  return {
		  files:'[{"id":"406477870392410113","name":"Penguins.jpg"}]',
		  permission:"r"
	  }
	},
	created : function(){
	},
	methods: {
	}
}
</script>

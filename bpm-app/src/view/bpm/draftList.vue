<template>
<div>
	<x-header>草稿箱 <router-link to="/" slot="right"> <a class="fa fa-home fa-2x"></a> </router-link></x-header>
	<div>
		<ab-scroller url="/bpm/my/draftList?supportMobile$V=true">
			<div slot-scope="slotScope">
				<cell v-for="def in slotScope.rows" :title="def.defName" :value="def.createTime" :link="{path:'/bpm/start',query:{instanceId:def.id}}" :inline-desc="def.subject"></cell>
			</div>
		</ab-scroller>
	</div>
</div>
</template>

<script>

import Vue from 'vue';

export default {
	data: function () {
	  return {
		  tasks:[]
	  }
	},
	created : function(){
	},
	methods: {	    
	}
}
</script>

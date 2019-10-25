<template>
<div>
	<x-header> 发起申请 <router-link to="/" slot="right"> <a class="fa fa-home fa-2x"></a> </router-link></x-header>
	<div>
		<ab-scroller url="/bpm/my/definitionList?support_mobile_$VEQ=1">
			<div slot-scope="slotScope">
				<cell v-for="def in slotScope.rows" :title="def.name" :value="def.key"  :link="{path:'/bpm/start',query:{defId:def.id}}" :inline-desc="def.desc"></cell>
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

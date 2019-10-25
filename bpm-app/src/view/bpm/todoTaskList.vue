<template>
<div>
	<x-header> 个人待办 <router-link to="/" slot="right"> <a class="fa fa-home fa-2x"></a> </router-link></x-header>
	<div>
		<ab-scroller url="/bpm/my/todoTaskList?supportMobile$V=true" :conditions="conditions">
			<div slot-scope="slotScope">
				<cell v-for="task in slotScope.rows" :key="task.id" :title="task.name"  :link="{path:'/bpm/complateTask',query:{taskId: task.id}}" :inline-desc="task.subject"></cell>
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
		  tasks:[],
		  conditions:[{key:"subject_^VLK",value:"标题"},{key:"name_^VLK",value:"任务名"}]
	  }
	},
	created : function(){
	},
	methods: {	    
	}
}
</script>

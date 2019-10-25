<template>
<div>
	<x-header> 办理历史 <router-link to="/" slot="right"> <a class="fa fa-home fa-2x"></a> </router-link></x-header>
	<div>
		<ab-scroller url="/bpm/my/approveList?supportMobile$V=true" :conditions='[{key:"inst.subject_^VLK",value:"标题"}]'>
			<div slot-scope="slotScope">
				<cell v-for="task in slotScope.rows" :title="task.nodeName" :inline-desc="task.subject" :link="{path:'/bpm/instanceDetail',query:{instanceId: task.id,nodeId:task.nodeId}}" >
					<div>
							<span v-if="task.approveStatus=='agree'" class="weui-btn weui-btn_mini weui-btn_plain-primary">同意</span>
							<span v-else-if="task.approveStatus=='oppose'" class="weui-btn weui-btn_mini weui-btn_plain-default">反对</span>
							<span v-else-if="task.approveStatus=='reject'" class="weui-btn weui-btn_mini weui-btn_plain-default">驳回</span>
							<span v-else class="weui-btn weui-btn_mini weui-btn_plain-default">{{task.approveStatus}}</span>
					</div>
				</cell>
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

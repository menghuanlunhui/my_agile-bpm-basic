<template>
<div>
	<x-header>申请历史 <router-link to="/" slot="right"> <a class="fa fa-home fa-2x"></a> </router-link></x-header>
	<div>
		<ab-scroller url="/bpm/my/applyTaskList?supportMobile$V=true" :conditions='[{key:"subject_^VLK",value:"标题"},{key:"def_name_^VLK",value:"流程名"}]'>
			<div slot-scope="slotScope">
				<cell v-for="apply in slotScope.rows" :title="apply.defName" :inline-desc="apply.subject" :link="{path:'/bpm/instanceDetail',query:{instanceId:apply.id}}" >
					<div>
							<span v-if="apply.status=='running'" class="weui-btn weui-btn_mini weui-btn_plain-primary">运行中</span>
							<span v-else-if="apply.status=='end'" class="weui-btn weui-btn_mini weui-btn_primary">完成审批</span>
							<span v-else-if="apply.status=='manualend'" class="weui-btn weui-btn_mini weui-btn_warn">人工终止</span>
							<span v-else-if="apply.status=='back'" class="weui-btn weui-btn_mini weui-btn_warn">驳回</span>
							<span v-else-if="apply.status=='revoke'" class="weui-btn weui-btn_mini weui-btn_plain-default">撤销</span>
							<span v-else-if="apply.status==='draft'" class="weui-btn weui-btn_mini weui-btn_plain-default">草稿</span>
							<span v-else class="weui-btn weui-btn_mini weui-btn_plain-default">{{apply.status}}</span>
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

	  }
	},
	created : function(){
	},
	methods: {
	}
}
</script>

import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/view/Home'
import TodoTaskList from '@/view/bpm/todoTaskList'

import ApproveList from '@/view/bpm/approveList'
import DefinitionList from '@/view/bpm/definitionList'
import ApplyList from '@/view/bpm/applyList'
import DraftList from '@/view/bpm/draftList'

import ComplateTask from '@/view/bpm/taskComplate'
import InstanceDetail from '@/view/bpm/instanceDetail'
import Start from '@/view/bpm/start'


import Test from '@/view/test'

Vue.use(Router)

export default new Router({
	routes: [{
		path: '/',
		name: '首页',
		component: Home
	},
	{
		path: '/bpm/todoTaskList',
		name: '我的待办',
		component: TodoTaskList
	},
	{
		path: '/test',
		name: '测试组件页面',
		component: Test
	},
	{
		path: '/bpm/approveList',
		name: '办理历史',
		component: ApproveList
	},
	{
		path: '/bpm/definitionList',
		name: '发起申请',
		component: DefinitionList
	},
	{
		path: '/bpm/applyList',
		name: '申请历史',
		component: ApplyList
	},
	{
		path: '/bpm/draftList',
		name: '我的草稿',
		component: DraftList
	},
	{
		path: '/bpm/start',
		name: '流程启动',
		component: Start
	},
	{
		path: '/bpm/complateTask',
		name: '处理任务',
		component: ComplateTask
	},
	{
		path: '/bpm/instanceDetail',
		name: '流程详情',
		component: InstanceDetail
	}
]
})

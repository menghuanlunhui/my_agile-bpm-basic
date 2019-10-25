// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import App from './App'

import router from './router/index'
import './assets/css/font-awesome/css/font-awesome.min.css'
import './assets/css/weui/weui.css'

import BaseService from './service/common/baseService'
import Tools from './service/common/tools'

import { LoadingPlugin, ToastPlugin, AlertPlugin,GroupTitle,Cell, Grid,GridItem,XHeader,Popup,TransferDom,XSwitch, Tabbar, TabbarItem,Loading,Flexbox, FlexboxItem,XDialog } from 'vux'
Vue.use(LoadingPlugin)
Vue.use(AlertPlugin)
Vue.use(ToastPlugin)


Vue.component('x-header', XHeader)
Vue.component('grid', Grid)
Vue.component('grid-item', GridItem)
Vue.component('group-title', GroupTitle)
Vue.component('cell', Cell)
Vue.directive('transfer-dom', TransferDom)
Vue.component('x-switch', XSwitch)
Vue.component('tabbar', Tabbar)
Vue.component('tabbar-item', TabbarItem)

Vue.component('popup', Popup)
Vue.component('x-dialog', XDialog)

Vue.component('flexbox', Flexbox)
Vue.component('flexbox-item', FlexboxItem)

//Vue.use(Vuex)
Vue.use(VueRouter)

// util 和baseService 直接注入到Vue上
Vue.use(Tools)
Vue.use(BaseService)
// seesion 过期弹框登录
import login from '@/components/user/loginDialog.vue'
Vue.use(login)

//注册AbScroller
import AbScroller from '@/components/form/abScroller.vue';
Vue.component('abScroller', AbScroller);
//注册AbCustDialog
import AbCustDialog from '@/components/form/abCustDialog.vue';
Vue.component('abCustDialog', AbCustDialog);

FastClick.attach(document.body)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app-box')

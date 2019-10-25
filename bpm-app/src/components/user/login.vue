<template>
<x-dialog v-model="show"  style="height:300px;width:350px">
	<div class="weui-dialog__hd showContent">
        <strong class="weui-dialog__title">用户身份验证</strong>
     </div>
     <div class="weui-dialog__bd weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">账号</label>
            </div>
            <div class="weui-cell__bd">
                <input type="text" class=" weui-input" v-model="user.account"  v-ab-validate="{'required':true}" desc="名字"/>
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">密码</label>
            </div>
            <div class="weui-cell__bd">
                <input type="password" class=" weui-input" v-model="user.password" v-ab-validate="{'required':true}" desc="名字"/>
            </div>
        </div>
     </div>
      <div class="weui-dialog__ft">
        <a class="weui-dialog__btn weui-dialog__btn_default" v-on:click="ok()">登录</a>
        <a class="weui-dialog__btn weui-dialog__btn_default"  v-on:click="cancel()">取消</a>
      </div>
  </x-dialog>
</template>

<script>
import Vue from 'vue'
export default {
  props:[],
  data :function () {
	    return {
	    	user:{account:"",password:""},
	    	show : false
	    }
   },
   methods: {
	    ok:function(){
	    	if(!this.user.account || !this.user.password){
	    		Vue.tools.toast("请完善登录信息！","warn");
	    		return ;
	    	}
	    	var post = Vue.baseService.postForm(Vue.__ctx+"/org/login/valid",this.user);
	    	var vm = this;
	 		Vue.tools.getResultData(post,function(){
	 			vm.show = false;
	 			Vue.tools.toast("身份认证成功！","success");
	 		},"toast")
	    },
		cancel:function(){
			this.show = false;
			this.$emit('cancel',this.flowParam);
		}
   },
  created :function(){
	  
  }
}


function createDialogVM(Vue){
	if (typeof document === 'undefined') {
	    console.error('[VUX] Alert plugin cannot be used in ssr.')
	    return
	  }
	
	
	
	
}

</script>

<template>
  <div>
  	 <x-header :left-options="{showBack: false}">
       <div class="vux-header-left" onclick="javascript:jsi_zhibang.gohome();">
         <a class="vux-header-back" href="javascript:jsi_zhibang.gohome();" >返回</a>
         <div class="left-arrow" ></div>
       </div>首页 <!--<a slot="right" class="fa fa-exist">退出</a>--></x-header>
  	 <group-title>事项办理</group-title>
	 <grid>
	  <grid-item label="待办事项" link="bpm/todoTaskList"> <a  slot="icon" class = "fa fa-tasks fa-2x"></a> </grid-item>
	  <grid-item label="办理历史" link="bpm/approveList"> <span slot="icon" class = "fa fa-server fa-2x"></span></grid-item>
	</grid>
	<group-title>事项申请</group-title>
	<grid>
	  <grid-item label="发起申请" link="bpm/definitionList"> <span slot="icon" class = "fa fa-heart fa-2x"></span> </grid-item>
	  <grid-item label="我的草稿" link="bpm/draftList"><span slot="icon" class = "fa fa-server fa-2x"></span> </grid-item>
	  <grid-item label="申请历史" link="bpm/applyList"> <span slot="icon" class = "fa fa-history fa-2x"></span></grid-item>
	</grid>
  </div>
</template>

<script>

export default {
  methods: {
    onItemClick () {
      console.log('on item click')
    }
  },
  name: 'Home',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<style scoped>
.fa-2x{
	color: #09BB07;

}

.grid-center {
  display: block;
  text-align: center;
  color: #666;
}
.weui-grids {
  background-color: #fff;
}
</style>

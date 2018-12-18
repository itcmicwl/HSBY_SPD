<template>
	<el-container style="height:100%;overflow: hidden;">
		<el-header height='60px' class="gy_top-header" v-show="!maxPage"><home></home></el-header>
		<el-container>
				<el-aside :width="getwidth()" v-show="!maxPage">
					<template v-for="(sltNav) in $route.matched" v-if="sltNav.meta.navId">
						<el-menu :default-active="$route.path" class='el-menu-vertical-demo' :collapse="collapsed" router unique-opened :key="sltNav.meta.navId">
							<template v-for="(item,index) in getSideNavs[sltNav.meta.navId]" v-if="!item.hidden">
								<el-submenu :index="index+''" v-if="item.type=='group'" :key="item.path + index">
									<template slot="title"><i :class="item.iconCls"></i><span slot="title">{{item.name}}</span></template>
									<el-menu-item :title="child.name" v-for="child in item.children" :route="getRoute(item,child)" :index="getPath(item.path,child.path)" :key="getPath(item.path,child.path)" v-if="!child.hidden">{{child.name}}</el-menu-item>
								</el-submenu>
								<!-- <el-menu-item v-if="item.leaf&&item.children.length>0" :route="getRoute(item,child)" :index="getPath(item.path,item.children[0].path)" :key="item">
									<i :class="item.iconCls"></i>{{item.children[0].name}}</el-menu-item> -->
							</template>
						</el-menu>
					</template>
					<template v-else>
						<el-menu  class='el-menu-vertical-demo' :collapse="collapsed" router unique-opened>
							<template v-for="(item,index) in getSideNavs[getHomeNavs[0].id]" v-if="!item.hidden">
								<el-submenu :index="index+''" v-if="item.type=='group'" :key="item.path + index">
									<template slot="title"><i :class="item.iconCls"></i><span slot="title">{{item.name}}</span></template>
									<el-menu-item :title="child.name" v-for="child in item.children" :route="getRoute(item,child)" :index="getPath(item.path,child.path)" :key="getPath(item.path,child.path)" v-if="!child.hidden">{{child.name}}</el-menu-item>
								</el-submenu>
							</template>
						</el-menu>
					</template>
				</el-aside>		
				<el-container class="pageMainC">
					<el-main class="page-main">
						<div class="breadtoolbar">
									<i class="fa i-btn i-btn-collapse" :class="collapsed?'fa-angle-double-right':'fa-angle-double-left'" @click="collapsed=!collapsed"></i>
									<el-breadcrumb separator="/" class="breadcrumb-inner">
										<el-breadcrumb-item v-for="item in $route.matched" :key="item.path" :to="item.path?item.path:'/'">
											{{ item.name }}
										</el-breadcrumb-item>
									</el-breadcrumb>
									<i class="fa i-btn i-btn-max" :class="maxPage?'fa-window-restore':'fa-window-maximize'" @click="maxPage=!maxPage"></i>
							</div>
							<div class="page-content">											
								<transition name="el-zoom-in-center">
									<router-view></router-view>
								</transition>
							</div>
					</el-main>
					<el-footer height="16px;">Copyright @2017-2018 中国医疗器械有限公司版权所有</el-footer>
				</el-container>
		</el-container>		
	</el-container>	
</template>
<script>
import { mapGetters } from 'vuex';
import home from './Home'
import util from '@/common/js/util.js'
export default {
	data() {
		return {
			maxPage:false,
			collapsed: false
		}
	},
	components: { home },
	computed: {
		...mapGetters(["getSideNavs","getHomeNavs"])
	},
	methods: {
		getwidth(){
			return this.collapsed?"64px":"200px";
		},
		isActive(r) {
			var rName = this.$route.name;
			if (rName && r && r.children) {
				return r.children.findIndex(item => item.name == rName) >= 0;
			}
			return false;
		},
		handleopen() {
		},
		handleclose() {
		},
		handleselect: function (a, b) {
		},
		//折叠导航栏
		collapse: function () {
			this.collapsed = !this.collapsed;
		},
		showMenu(i, status) {
			this.$refs.menuCollapsed[0].getElementsByClassName('submenu-hook-' + i)[0].style.display = status ? 'block' : 'none';
		},
		getPath(pPath, cPath) {
			return pPath + "/" + cPath;
		},
		getRoute(item,child){
			var param = child.meta.param;
			var res = {path:item.path +"/"+child.path,redirect:child.redirect};
			if(param){
					res.query = util.queryStringToObject(param);
			}
			return res;
		},
		beforeLeave(e) {
			e.style.width = 0;
		}
	},
	mounted() {
	}
}

</script>

<style lang="scss">
@import '~gycolor';
body {
	margin: 0px;
	padding: 0px;
	font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;
	font-size: 12px;
	-webkit-font-smoothing: antialiased;
	overflow-y: hidden;
	#app {
		position: absolute;
		top: 0px;
		bottom: 0px;
		width: 100%;		
	}
}
.el-header{  
		padding: 0;
	}
	.gy_top-header{
		background-color: #f9f9fc;
	}
  .el-footer {
    background-color: #eef1f6;
    text-align: center;
    line-height: 16px;
		padding: 0;
		font-size: 10px;
		color: #a8b0b9;
  }
  .el-aside {
    color: #333;
    text-align: center;
		flex-shrink:0;
		overflow: hidden;
  }
  .breadtoolbar{
		height: 20px;
		background-color:#d3eeeb;
		.i-btn-collapse{
				float: left;
				font-size: 16px;
				cursor:pointer;
				margin-right: 15px;
				margin-left: 5px;
		}		
		.el-breadcrumb{
			float: left;
			font-size: 12px;
			height: 16px;
			line-height: 20px;
		}
		.i-btn-max{
			float: right;
			font-size: 15px;
			cursor:pointer;
			margin-right: 15px;
			margin-top: 2px;
		}
		.i-btn{
			color: $--color-primary;
		}
		.i-btn:hover{
			color: $-color-active;
		}
	}
	.breadtoolbar:after { 
		background-color: $--color-primary;
	}
	
  .el-main {    
		padding: 0px 0px 0px 2px;
		overflow-x: hidden;
		
  }
	.page-main{
		padding: 5px 8px 40px 8px;;
		flex-basis:0;
		overflow-y: hidden;
		background:#d3eeeb;
		.page-content{
			height: 100%;
			background-color: #fff;
			padding: 15px 10px 0px 10px;
			overflow: auto;
		}
	}
  .el-menu{
		height: 100%;	
    background-color: #eef1f6;
			
	}
 .el-submenu [class^=fa] {
	vertical-align: baseline;
	margin-right: 10px;
	font-size:14px;
}
.el-submenu .el-menu-item {
    height: 35px;
    line-height: 35px;
}
.el-submenu__title {
	border-top: none;
	border-right: none;
	border-bottom: none;
	height: 40px;
	line-height: 40px;
}
.el-menu-item.is-active {
    color: #44549d;
    background-color: #d3eeeb;
}
.el-submenu {
	margin-bottom: 2px;
}


		
</style>
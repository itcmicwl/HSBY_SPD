// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/styles/gyqx.scss';
import App from './App'
import 'nprogress/nprogress.css'
import _ from 'underscore';
import '@/common/js/globalFn'
import store from './vuex/store';
import 'font-awesome-webpack';
import { appConfig } from './config/appConfigs';
import routerConfig from '@/config/baseRoutes';       //基本路由
import axios from './config/axiosConfig';                  //ajax配置
import './common/filter';                       //加入自定义过滤器
import './common/directive';                    //加入自定义指令
import {loginOut as uloginOut} from '@/common/js/loginUtil';
Vue.use(Element, { size: 'mini' });
Vue.use(axios);
Vue.config.productionTip = false
Vue.prototype.appConfig = appConfig;
Vue.prototype.tools={_:_};
/**
 * 
 * @param {*} spdRouter 
 * @param {*} resCode -1 用户权限分配有误 -2 用户未登陆
 */
let buildVue = function(spdRouter,resCode){
  var spdVue = new Vue({
    router:spdRouter,
    store,
    render: h => h(App),
    mounted() {
      if(resCode < 0 ){
        uloginOut();
        this.$router.replace("login");
      }
    }
  }).$mount('#app');
}

let user = JSON.parse(LocalStorage.getItem('user'));
store.commit('userLogin', user);
if(user){
  routerConfig.addUserRouter(user.userId).then(res=>{
    buildVue(res.data,res.code);
  },err=>{
    Element.Message({showClose: true, message: err.msg,type: 'error'});    
    buildVue(routerConfig.router,err.code);
  });
}else{
  buildVue(routerConfig.router,-2);
}

import store from '../vuex/store';
import axios from 'axios';
import { appConfig } from './appConfigs';
import routerConfig from '@/config/baseRoutes';       //基本路由
var uriOpMap=[
    {uri:'/platformService/sys/login/exit',opType:0},
    {uri:'/platformService/permission/menuPageviewItemsConfig/getUserMenusByProjectCode/',opType:1}
];
axios.install=function(vue,option){
    axios.interceptors.request.use(
        config => {
            var user = store.getters["getLoginUser"];
            if (user && user.xus) {  // 判断是否存在xus，如果存在的话，则每个http header都加上xus
                config.headers["X-US"] = user.xus;
                config.headers["X-APP-CODE"] = appConfig.appCode;
            }
            return config;
        },
        err => {
            return Promise.reject(err);
        });
    axios.interceptors.response.use(
        response => {
            return response;
        },
        error => {
            if (error.response) {
                switch (error.response.status) {
                    case 401:
                        var uriOp = uriOpMap.find(o=>o.opType>0 && o.uri.indexOf(error.config.url)>=0);
                        if(uriOp){
                            if(uriOp.opType == 1){
                                routerConfig.router.replace("/login");
                            }
                        }else{
                            store.commit('loginDialogShow', true);
                            //routerConfig.router.replace("/login");
                        }                        
                }
            }
            return Promise.reject(error.response.data)   // 返回接口返回的错误信息
        });
    vue.prototype.axios = axios;
}
export default axios;

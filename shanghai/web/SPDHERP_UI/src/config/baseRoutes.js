/**
 * 构建路由及菜单列表
 */
import Vue from 'vue';
import axios from 'axios';
import VueRouter from 'vue-router';
import store from '../vuex/store';
import setNav from './menu';
import { Message } from 'element-ui';
import NProgress from 'nprogress'

NProgress.configure({ easing: 'ease', speed: 500 });
const Content = r => require.ensure([], () => r(require('../views/content.vue')), 'main');
const NotFound = r => require.ensure([], () => r(require('../views/404.vue')), 'main');
const Forbidden = r => require.ensure([], () => r(require('../views/403.vue')), 'main');
const login = r => require.ensure([], () => r(require('../views/login.vue')), 'main');
const baseInfo = r => require.ensure([], () => r(require('../views/userSettings/baseInfo.vue')), 'main');
const index = r => require.ensure([], () => r(require('../views/index.vue')), 'main');
Vue.use(VueRouter);

let baseRoutes = [
    {
        path: '/',
        component: Content,
        name: '',
        hidden: true,
        children:[],
        redirect: { path: '/user/index' }
    },
    {
        path: '/user',
        component: Content,
        name: '个人信息',
        hidden: true,
        children:[
            {
                path: 'usersetting',
                component: baseInfo,
                name: '个人设置',
                hidden: true
            },
            {
                path: 'index',
                component: index,
                name: '首页',
                hidden: true
            }
        ],
    },
    {
        path: '/404',
        component: NotFound,
        name: '404',
        hidden: true
    },
    {
        path: '/403',
        component: Forbidden,
        name: '403',
        hidden: true
    },
    {
        path: '/login',
        component: login,
        name: 'login',
        hidden: true
    },
    {
        path: '*',
        name: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

const router = new VueRouter({ routes: baseRoutes });
router.beforeEach((to, from, next) => {
    NProgress.start();
    Vue.prototype.$permission = {};
    var user = store.getters.getLoginUser;
    if (user) {
        if(!to.meta.id){
            next();
            return;
        }
        var url = `/platformService/permission/roleConfig/getUserMenuElementCodes/${user.userId}/${to.meta.id}`;
        axios.get(url).then(res => {
            NProgress.set(0.5);
            if(res.data.code == "0"){                    
                var pList = res.data.data;
                if (Array.isArray(pList)) {
                    store.commit('setPageEls', pList);
                }
                next();
            }
        },err=>{
            Message({showClose: true, message: '获取页面权限失败，请联系系统管理员',type: 'error'});
            next(from);
        });        
    }
    else {
        store.commit('loginDialogShow', false);
        if (to.name == 'login') {
            next();
        } else {
            var last = from.path == "/login" ? "/" : from.path; 
            next({ path: '/login', query: { last: last } });
        }
    }
    
});
router.afterEach(transition => {
    NProgress.done();
});
let addUserRouter = function (userId) {//加载用户权限
    return new Promise(function (resolve,reject) {
        if(!userId){
            return reject({code:-2,msg:'无用户登陆信息'});
        }
        setNav(userId).then(menu => {
            if(!Array.isArray(menu.homeNavs) || !menu.sideNavs || menu.homeNavs.length ==0){
                return reject({code:-2,msg:'当前用户无权限，请联系管理员分配'});
            }
            store.commit('setSideNavs', menu.sideNavs);
            store.commit('setHomeNavs', menu.homeNavs);
            store.commit('setSysRoutes', menu.routes);
            router.options.routes[0].children = menu.routes;
            router.addRoutes(router.options.routes[0].children);
            return resolve({code:0,msg:'OK',data:router});
        },err=>{
            return reject(err);
        });
    });
}
export default {
    router:router,
    addUserRouter:addUserRouter
};

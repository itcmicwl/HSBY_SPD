import Vue from 'vue';
import Vuex from 'vuex';
import * as actions from './actions';
import * as getters from './getters';
import dialog_store from '../components/js/dialog_store';
Vue.use(Vuex);
const state = {
    loginUser: null,
    loginFlag: false,
    routes: {
        homeNavs: null,
        sideNavs: [],
        sysRoutes: null
    },
    permission: {
        userMenuTree:[],
        pageEls: []
    },
    settleBill:null,
    settleBill4Out:null,
    spdMsg:[]
};
const mutations = {
    userLogin(state, data) {
        if (data) {
            state.loginUser = data;
            LocalStorage.setItem('user', JSON.stringify(data));
            Vue.prototype.user = state.loginUser;
        } else {
            state.loginUser = null;
            localStorage.removeItem('user');
            localStorage.removeItem('routes');
            Vue.prototype.user = null;
        }
    },
    loginDialogShow(state, flag) {
        state.loginFlag = flag;
    },    
    setSysRoutes(state, data) {
        state.routes.sysRoutes = data;
    },
    setSideNavs(state, data) {
        state.routes.sideNavs = data;
    },    
    setHomeNavs(state, data) {
        state.routes.homeNavs = data;
    },
    setPageEls(state, data) {
        if(data){
            state.permission.pageEls = data;
        }
    },
    setUserMenuTree(state, data) {
        if(Array.isArray(data) && data.length > 0){
            state.permission.userMenuTree = data;
        }
    },
    setSettleBill(state,data){
        state.settleBill = data;
    },
    setSettleBill4Out(state,data){
        state.settleBill4Out = data;
    },
    setSpdMsg(state,data){
        if(Array.isArray(data) && data.length>0){
            state.spdMsg = data;
        }else{
            state.spdMsg = [];
        }
    },
    clearCache(state){
        state.routes = {
            homeNavs: null,
            sideNavs: [],
            sysRoutes: null
        };
        state.permission = {
            userMenuTree: [],
            pageEl: {}
        };
        state.loginUser = null;
    }
};
// 创建 store 实例
export default new Vuex.Store({
    actions,
    getters,
    state,
    mutations,
    modules:{
        dialog : dialog_store
    }
})
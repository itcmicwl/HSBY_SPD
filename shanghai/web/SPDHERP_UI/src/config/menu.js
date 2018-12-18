import axios from 'axios';
import { appConfig } from './appConfigs';
import store from '../vuex/store';
let sortMenu = function(a,b){
    return a.ordinal - b.ordinal;
}
let buildMeunTree = function (data) {
    var res = [];
    if (data.length > 0) {
        var topM = data.filter(item => item.menuLevel == 0).sort(sortMenu);
        if (topM.length > 0) {
            topM.forEach(ritem => {
                var canAdd = true;
                ritem.children = data.filter(item => item.pcode == ritem.code).sort(sortMenu);
                ritem.children.forEach(item => {
                    item.children = data.filter(citem => citem.pcode == item.code).sort(sortMenu);
                    if(Array.isArray(item.children) && item.children.length>0 && canAdd){
                        res.push(ritem);
                        canAdd = false;
                    }
                });
            });
        }
    }
    return res;
}
let buildNav = function (routJson) {
    let routeMap = {
        homeNavs: [],
        routes: [],
        routeNav: {}//左边导航菜单
    };
    if (routJson.length > 0) {
        routJson.forEach(item => {//第一级菜单
            let comp = r => require.ensure([], () => r(require("../" + item.directory)), "main");
            let pr = { path: item.path, component: comp, name: item.cname, redirect: item.redirect, meta: { navId: item.id, requireAuth: item.requireAuth,id:item.id,param:item.meta,pageEls:item.pageEls }, children: [] };
            routeMap.routeNav[item.id] = [];
            let goupMap = {};
            if (item.children && item.children.length > 0) {
                item.children.forEach(citem => {//第二级菜单(分组)
                    let group = { name: citem.cname, iconCls: citem.iconCls, path: item.path, meta: { navId: citem.id, requireAuth: citem.requireAuth,id:citem.id,param:citem.meta ,pageEls:citem.pageEls},children: [], type: "group" };
                    if (citem.children && citem.children.length > 0) {  //页面
                        citem.children.forEach(ccitem => {
                            let cComp = r => require.ensure([], () => r(require("../" + ccitem.directory)), "main");
                            let cr = { path: ccitem.path, component: cComp, name: ccitem.cname, redirect: ccitem.redirect, hidden: ccitem.hidden == "1", meta: { requireAuth: ccitem.requireAuth, id: ccitem.id,param:ccitem.meta,pageEls:ccitem.pageEls } };
                            pr.children.push(cr);
                            if(ccitem.hidden == "0"){
                                group.children.push({ path: ccitem.path, name: ccitem.cname, redirect: ccitem.redirect, hidden: false ,meta: { requireAuth: ccitem.requireAuth, id: ccitem.id,param:ccitem.meta,pageEls:ccitem.pageEls }});
                            }

                        });
                    }
                    if(Array.isArray(group.children) && group.children.length>0){
                        routeMap.routeNav[item.id].push(group);
                    }
                });
            }
            routeMap.routes.push(pr);    //加入路由    
            if(routeMap.routeNav[item.id].length>0){
                routeMap.homeNavs.push({ name: item.cname, path: item.path, hidden: item.hidden == '1',id:item.id});//页面顶部菜单            
            }
        });
    }
    return routeMap;
}
let setNav = function (userId) {
    return new Promise(function (resolve,reject) {
        var result = false;
        //var user = store.getters.getLoginUser;
        var menu = {
            arrMenu: [],
            homeNavs: [],
            sideNavs: []
        }
        if (!userId) {
            return reject({code:-2,msg:'无法获取用户登陆信息'});
        }        
        var serviceUrl = `/platformService/permission/menuPageviewItemsConfig/getUserMenusByProjectCode/${appConfig.appCode}/${userId}`;
        axios.get(serviceUrl).then(res => {
            var arrMenu = res.data.data;
            if (arrMenu && arrMenu.length > 0) {
                menu.arrMenu = arrMenu;
                var navTree = buildMeunTree(arrMenu);
                store.commit('setUserMenuTree', navTree);
                var routeInfo = buildNav(navTree);
                //设置菜单
                menu.sideNavs = routeInfo.routeNav;
                menu.homeNavs = routeInfo.homeNavs;
                menu.routes = routeInfo.routes;
                return resolve(menu);
            }
            return reject({code:-1,msg:'当前用户无任何权限信息，请分配'});
        }, err => {
            return reject({code:-1,msg:'获取用户权限失败'});
        });
    });
}
export default setNav;

import axios from 'axios';
import sha256 from './sha256';
import store from '../../vuex/store';
import routerConfig from '@/config/baseRoutes';       //基本路由
let userLogin = function (loginParams) {
    return new Promise((resolve,reject) => {
        var result = {
            flag: false,
            accountErr: '',
            passwordErr: '',
            errInfo: null
        }
        axios.post(`/platformService/sys/login/checkUserLoginCode`, { userLoginCode: loginParams.username }).then(res => {
            if (res.data.code == "-120") {
                result.accountErr = "用户名不存在!";
                return reject(result);
            }
            if (res.data.code == "-122") {
                result.accountErr = "用户名错误!";
                return reject(result);
            }
            if (res.data.code == "-1000") {
                result.accountErr = "系统内部异常，请联系管理员!";
                return reject(result);
            }
            let r = res.data.data;
            var hash = sha256(loginParams.password + r.salt);
            let loginPara = { userLoginCode: loginParams.username, hash: hash, rcode: r.randomCode };
            axios.post(`/platformService/sys/login/login`, loginPara).then(res => {
                if (res.data.code == 0) {   //登陆成功
                    var user = res.data.data;
                    user.xus = res.data.tag;
                    store.commit('userLogin', user);
                    LocalStorage.setItem("userCode",user.userCode)
                    routerConfig.addUserRouter(user.userId).then(res=>{
                        result.flag = true;
                        return resolve(result);
                    },err=>{
                        result.accountErr = err.msg;
                        return reject(result);
                    });
                } else if (res.data.code == "-111") {
                    result.passwordErr = "密码错误!";
                    return reject(result);
                } else {
                    result.accountErr = "登陆失败!";
                    return reject(result);
                }
            }, err => {
                result.accountErr = "登陆失败!";
                result.errInfo = err;
                return reject(result);
            });
        }, err => {
            result.accountErr = "登陆失败!";
            result.errInfo = err;
            return reject(result);
        });
    });
}
let userLoginOut = function(){
    window.localStorage.clear();
    store.commit('clearCache', null);
    axios.get(`/platformService/sys/login/exit`);
}
export const login = userLogin;
export const loginOut = userLoginOut;
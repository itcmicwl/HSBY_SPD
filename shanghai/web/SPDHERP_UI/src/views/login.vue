<template>
  <div class="login-container">
    <div class="login-info">
      <div class="philosophy">
        <img src="../assets/images/philosophy.png" alt="" />
      </div>
  
      <div class="login-copyright"></div>
  
      <div class="login-link">
        <div class="login-link-img">
          <img src="@/assets/images/login-link.png" alt="" />
        </div>       
      </div>
      
    </div>
    <div class="login-box" @keyup.enter="login">
      <div class="login">
        <div class="login-logo">
          <img src="../assets/images/login-logo.png" alt="">
          <img>
        </div>
        <div class="login-tip"><div class="login-welcome">欢迎您，请登录</div><div class="login-version">内网版</div></div>        
        <div class="login-input">
          <div class="triangle"></div>
          <div class="login-user">
            <span class="user-icon"></span>
            <input class="user-input" type="text" v-focus="true" autocomplete="off" v-model="ruleForm2.account"/>
          </div>
          <div class="login-password">
            <span class="password-icon"></span>
            <input class="password-input" type="password" autocomplete="off" v-model="ruleForm2.checkPass"/>
          </div>
        </div>
  
        <div class="login-bottom">
          <div class="remember">
            <el-checkbox v-model="checked" checked class="remember">记住账号</el-checkbox>
          </div>
          <span v-if="err.accountErr" class="err-info">{{err.accountErr}}</span>
          <div class="clear"></div>
        </div>
        <div class="login-submit">
          <el-button type="primary" class="submit-btn" @click.native.prevent="login" :loading="logining">登录</el-button>
        </div>
      </div>
    </div>
    <div class="login-link-con"> {{copyRight}} </div>
  </div>
</template>

<script>
//import ulogin from '@/common/js/userLogin';
import {login as ulogin} from '@/common/js/loginUtil';
import moment from 'moment';
import { mapGetters, mapActions } from 'vuex';
export default {
  data() {
    return {
      logining: false,
      copyRight:'',
      ruleForm2: {
        account: '',
        checkPass: '',
      },
      err: {
        accountErr: "",
      },
      checked: true
    };
  },
  methods: {
    ...mapActions([
      'userLogin'
    ]),
    handleReset2() {
      this.ruleForm2.account = "";
      this.ruleForm2.checkPass = "";
    },
    login(ev) {
      var _this = this;
      this.logining = true;
      var loginParams = { username: this.ruleForm2.account, password: this.ruleForm2.checkPass, remember: this.checked };
      ulogin(loginParams).then(res => {
        _this.logining = false;
          let redirect = decodeURIComponent(_this.$route.query.last || '/');
          _this.$router.push({ path: redirect });
      },err=>{
        _this.logining = false;
          _this.err.accountErr = err.accountErr || err.passwordErr;
      });
    }
  },
  mounted() {
    var userCode = LocalStorage.getItem("userCode");
    this.userLogin(null);
    var year = new Date().getFullYear();
    var yearStr = (year - 1)+" - " +year;
    this.copyRight = `Copyright @ ${yearStr} 中国医疗器械有限公司版权所有`;
    if (userCode) {
      this.ruleForm2.account = userCode;
    } else {
      this.checked = false;
    }
  }
}
</script>

<style scoped>
@import '../assets/styles/normalize.css';
@import '../assets/styles/style.css';
</style>

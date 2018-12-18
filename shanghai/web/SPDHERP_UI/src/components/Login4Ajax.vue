<template>
  <el-dialog title="用户登陆" :visible.sync="showLoginDialog"  width="30%" center :show-close="false" @open="onOpen" :close-on-press-escape="false" :close-on-click-modal='false'>
    <div @keyup.enter="login">
      <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px" class="demo-ruleForm">
        <el-form-item prop="account" :error="err.accountErr">
          <el-input type="text" v-model="ruleForm2.account" v-focus="userNameF" auto-complete="off" placeholder="账号"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass" :error="err.passwordErr">
          <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="密码"></el-input>
        </el-form-item>
        <el-checkbox v-model="checked" class="remember">记住账号</el-checkbox>
        <el-form-item style="width:100%;">
          <el-button type="primary" style="width:100%;" @click.native.prevent="login" :loading="logining">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-dialog>
</template>

<script>
import axios from 'axios';
//import ulogin from '../common/js/userLogin';
import {login as ulogin} from '@/common/js/loginUtil';
import { mapGetters, mapActions } from 'vuex';
export default {
  name: 'loginDailog',
  data() {
    return {
      userNameF: true,
      logining: false,
      ruleForm2: {
        account: '',
        checkPass: '',
      },
      rules2: {
        account: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        checkPass: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ]
      },
      err: {
        accountErr: "",
        passwordErr: ""
      },
      checked: true
    };
  },
  props: ['showLoginDialog'],
  methods: {
    ...mapActions([
      'loginDialogShow',
      'userLogin'
    ]),
    onOpen() {
      this.$nextTick(function () {
        this.userNameF = true;
      });
    },
    handleReset2() {
      this.$refs.ruleForm2.resetFields();
    },
    login() {
      var _this = this;      
      this.$refs.ruleForm2.validate((valid) => {
        if (valid) {
          this.logining = true;
          _this.err.accountErr = '';
          _this.err.passwordErr = '';
          var loginParams = { username: this.ruleForm2.account, password: this.ruleForm2.checkPass, remember: this.checked };
          ulogin(loginParams).then(res => {
            _this.logining = false;
              _this.loginDialogShow(false);
              window.location.reload();              
            },err=>{
              _this.logining = false;
              _this.err.accountErr = err.accountErr;
              _this.err.passwordErr = err.passwordErr;
            });
        }
      });
    }
  },
  mounted() {
    var userCode = LocalStorage.getItem("userCode");
    //this.userLogin(null);
    if (userCode) {
      this.ruleForm2.account = userCode;
    } else {
      this.checked = false;
    }
  }
}

</script>

<style lang="scss" scoped>
.el-form-item--mini .el-form-item__content {
    width: 100%;
}
.login-container {
  /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
  -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  margin: auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  .title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .remember {
    margin: 0px 0px 35px 0px;
  }
}
</style>

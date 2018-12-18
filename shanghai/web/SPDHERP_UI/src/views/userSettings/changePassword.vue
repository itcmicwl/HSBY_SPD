<template>
    <el-col :span="8">
        <el-form label-width="80px" v-model="formData">
            <el-form-item label="用户名" :error="err.accountErr">
                <span>{{formData.userName}}</span>
            </el-form-item>
            <el-form-item label="原始密码" :error="err.oldPasswordErr">
                <el-input type="password" v-model="formData.oldPassword" ></el-input>
            </el-form-item>
            <el-form-item label="新密码" :error="err.newPasswordErr">
                <el-input type="password" v-model="formData.newPassword" ></el-input>
            </el-form-item>
            <el-form-item label="确认密码" :error="err.repeatPasswordErr">
                <el-input type="password" v-model="formData.repeatPassword" ></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click.native="changePass"  :loading="logining">确定</el-button>
            </el-form-item>
        </el-form>
    </el-col>
</template>
<script>
import sha256 from '../../common/js/sha256';
export default {
    data() {
        return {
            logining: false,
            formData: {
                userName: '',
                oldPassword: '',
                newPassword: '',
                repeatPassword: '',
            },
            err: {
                accountErr: "",
                oldPasswordErr: "",
                newPasswordErr: '',
                repeatPasswordErr: ""
            }

        }
    },
    methods: {
        validForm() {
            var flag = true;
            this.err.accountErr = "";
            this.err.oldPasswordErr = "";
            this.err.newPasswordErr = "";
            this.err.repeatPasswordErr = "";

            if (this.formData.oldPassword == '') {
                this.err.oldPasswordErr = "请输入原始密码";
                flag = false;
            }
            if (this.formData.newPassword == '' || this.formData.newPassword.length < 6) {
                this.err.newPasswordErr = "新密码不能为空且长度必不得小于6位";
                flag = false;
            }
            if (this.formData.repeatPassword == '' || this.formData.repeatPassword !== this.formData.newPassword) {
                this.err.repeatPasswordErr = "确认密码不能为空且必须和新密码保持一致";
                flag = false;
            }
            return flag;
        },
        changePass() {
            var _this = this;
            if (this.validForm()) {
                this.logining = true;
                this.axios.post('/platformService/sys/login/checkUserLoginCode', { userLoginCode: this.user.userCode }).then(res => {
                    if (res.data.code == "-120") {
                        _this.logining = false;
                        _this.err.accountErr = "用户名不存在!";
                        return;
                    } else if (res.data.code == '-122') {
                        _this.err.accountErr = "验证失败";
                        _this.logining = false;
                        return;
                    }
                    let r = res.data.data;
                    var hash = sha256(this.formData.oldPassword + r.salt);
                    var newHash = sha256(this.formData.newPassword + r.newSalt);
                    let loginPara = {
                        userLoginCode: this.user.userCode,
                        hash: hash,
                        newHash: newHash,
                        rcode: r.randomCode,
                        newSalt: r.newSalt
                    };
                    this.axios.post(`/platformService/sys/login/changePassword`, loginPara).then(res => {
                        _this.logining = false;
                        if (res.data.code == 0) {
                            _this.$msgbox({
                                title: "提示",
                                message: "操作成功",
                                type: "success",
                                callback: action => {
                                    _this.$router.push("/login");
                                }
                            });
                        } else if (res.data.code == '-111') {
                            _this.err.oldPasswordErr = "原始密码错误";
                        } else {
                            _this.err.accountErr = "操作失败!";
                        }
                    }, err => {
                        _this.logining = false;
                        _this.err.accountErr = "操作失败!";
                    });
                }, err => {
                    _this.logining = false;
                    _this.err.accountErr = "操作失败!";
                });
            }
        }
    },
    mounted() {
        this.formData.userName = this.user.ename;
    }
}
</script>
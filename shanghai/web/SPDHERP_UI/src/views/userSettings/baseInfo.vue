<template>
    <el-tabs id="base-info" type="border-card" :active-name="showTab">
        <el-tab-pane label="基本信息" name="baseInfo">
            <el-col :span="8">
                <el-form label-width="80px" v-model="user" class="el-form-item-nomsg">
                    <el-form-item label="账号">
                        <span>{{user.userCode}}</span>
                    </el-form-item>
                    <el-form-item label="用户名">
                        <span>{{user.ename}}</span>
                    </el-form-item>
                    <el-form-item label="昵称">
                        <span>{{user.cname}}</span>
                    </el-form-item>
                    <el-form-item label="所属机构">
                        <span>{{user.corpName}}</span>
                    </el-form-item>
                    <el-form-item label="部门">
                        <span>{{user.orgName}}</span>
                    </el-form-item>
                    <el-form-item label="工作">
                        <span>{{user.job}}</span>
                    </el-form-item>
                    <el-form-item label="电话">
                        <span>{{user.tel}}</span>
                    </el-form-item>
                    <el-form-item label="邮箱">
                        <span>{{user.email}}</span>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click.native="goback" >返回</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-tab-pane>
        <el-tab-pane label="修改密码" name="changePass">
            <changePassword></changePassword>
        </el-tab-pane>
    </el-tabs>
</template>
<script>
import changePassword from './changePassword.vue';
export default {
    data() {
        return {
            showTab: 'baseInfo',
        }
    },
    methods: {
        goback() {
            this.$router.go(-1);
        }
    },
    mounted() {

    },
    components: { changePassword }

}
</script>
<style>
#base-info .el-table {
    font-size: 12px;
}
#base-info .el-tabs,.el-tabs .el-tabs__content{
    height:94%;
}
#base-info .el-tabs__item{
    height:30px;
    line-height: 30px;
    font-size:12px;
}
</style>


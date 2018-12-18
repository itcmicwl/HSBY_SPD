<template>
  <div  style="height: 100%;" @keyup.enter="query()">
	<!--工具条-->
    <div style="padding-bottom: 1px;">
    <el-card>        
        <div style="margin-top: 20px;"></div>
        <el-form :inline="true"  label-width="80px" ref="queryForm">
            <el-form-item label="企业编码">
                <el-input v-model="comCode" :clearable="true" placeholder="请输入企业编码" ></el-input>
            </el-form-item>
            <el-button-group>
                <el-button type="primary" v-on:click="query()" :loading="queryBtnLoading">查询</el-button>
            </el-button-group>
        </el-form>
        <div style="margin-top: 20px;"></div>
        <el-form label-width="80px" :model="ygCmpInfoModel" disabled>
            <el-form-item label="企业编码">
                <el-input v-model="ygCmpInfoModel.qybm"></el-input>
            </el-form-item>
            <el-form-item label="企业名称">
                <el-input v-model="ygCmpInfoModel.qymc"></el-input>
            </el-form-item>
            <el-form-item label="企业地址">
                <el-input v-model="ygCmpInfoModel.qydz"></el-input>
            </el-form-item>
            <el-form-item label="联系人">
                <el-input v-model="ygCmpInfoModel.lxr"></el-input>
            </el-form-item>
            <el-form-item label="联系电话">
                <el-input v-model="ygCmpInfoModel.lxdh"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" size='medium' @click="save">保存</el-button>
            </el-form-item>
        </el-form>
    </el-card>
    </div>
  </div>
</template>
<script>
export default {
    data() {
         return {
            baseUrl:'/spdHERPService/yg/companyInfo',
            //查询条件
            ygCmpInfoModel:{qybm:'',qydz:'',qymc:'',lxr:'',lxdh:''},
            initBtnLoading:false,
            queryBtnLoading:false,
            saveBtnLoading: false
        }
    },
    props: ['com-code'],
    methods: {
        /**
         * 查询
         */
        query:function(){
            if(this.comCode == ''){
                this.$message({ message: "请输入企业编码！", type: "error" });
                return;
            }
            this.queryBtnLoading = true;
            var param = {
                qybm : this.comCode 
            };
            this.axios.post(this.baseUrl + '/getYgCmpInfo', param).then(res => {
                if(res.data.data && (res.data.data != null)){
                    this.ygCmpInfoModel = res.data.data;
                }else{
                    this.$message({ message: "未查到对应的企业信息！", type: "error" });
                }
                this.queryBtnLoading = false;
            });
        },
        /**
         *保存
         */
        save: function () {
            if(this.ygCmpInfoModel.qybm=='' || this.ygCmpInfoModel.qymc=='' ){
                this.$message({ message: "企业编码、企业名称不能为空！", type: "success" });
                return;
            }
            this.saveBtnLoading = true;
            var param = {
                qybm:this.ygCmpInfoModel.qybm,
                qydz:this.ygCmpInfoModel.qydz,
                qymc:this.ygCmpInfoModel.qymc,
                lxr:this.ygCmpInfoModel.lxr,
                lxdh:this.ygCmpInfoModel.lxdh
            };
            this.axios.post(this.baseUrl + '/saveYgCmpInfo', param).then(res => {
                if(res.data.code == '0'){
                    this.$message({ message: "操作成功！", type: "success" });
                }else{
                   // this.$message({ message: "操作失败！==> "+res.data.msg, type: "success" });
                    this.$message.error("操作失败！==> "+ res.data.msg);
                }
                this.saveBtnLoading =false;
            });
        },
        initProvInfos:function(){
            this.initBtnLoading = true;
            this.axios.post('/spdHERPService/yg/companyInfo/getYgCmpInfosAndSave').then(res => {
                if(res.data.code == '0'){
                    this.$message({ message: "初始化操作成功！", type: "success" });
                }else{
                    this.$message.error("操作失败！==> "+ res.data.msg);
                }
                this.initBtnLoading = false;
            });
        },
    },
    mounted() {
    }
};
</script>
<style>
</style>
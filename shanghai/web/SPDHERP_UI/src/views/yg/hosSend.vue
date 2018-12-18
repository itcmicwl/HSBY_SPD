<template>
  <div  style="height: 100%;">
	<el-row :gutter="10" height="auto" style="height: 100%;">
        <el-form :model="req" style="padding-left: 5px;">
            <el-form-item label="消息类型码" prop="code"  style="margin-bottom:0px;">
                <el-select v-model="req.code" placeholder="请选择接口"  @change="codeChange">
                    <el-option v-for="item in objs" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
                <el-button-group>
                    <el-button type="danger" @click="clearReq" >清空</el-button>
                    <el-button type="success" @click="send" >发送</el-button>
                </el-button-group>
            </el-form-item>
            <div style="color:red;">临时文件位于本地磁盘D:/yg_temp/</div>
            <div style="color:red;">文件仅供测试，环境部署前删除</div>
            <el-col :span="12" style="padding-left: 0px;">
                <el-form-item label="请求内容" prop="contentReq" style="margin-bottom:0px;">
                    <el-input
                        type="textarea"
                        :autosize="{ minRows: 12,maxRows: 20}"
                        placeholder="自定义数据"
                        v-model="req.contentReq">
                    </el-input>
                </el-form-item>   
            </el-col>
            <el-col :span="12">
                <el-form-item label="返回内容" prop="contentRep" style="margin-bottom:0px;">
                    <el-input
                        type="textarea"
                        :autosize="{ minRows: 12,maxRows: 20}"
                        placeholder="接口返回消息"
                        v-model="req.contentRep"
                        >
                    </el-input>
                </el-form-item>
            </el-col>
        </el-form>
    </el-row>
    </div>
</template>
<script>
export default {
    data() {
         return {
            dialogVisible: false,
            baseUrl:'/spdHERPService/yg/hosSend',
            //
            req:{
            },
            objs:[
                { value: 'YY101', label: 'YY101配送点传报' },
                { value: 'YY111', label: 'YY111采购单填报' },
                { value: 'YY112', label: 'YY112采购单确认' },
                { value: 'YY113', label: 'YY113退货单填报' },
                { value: 'YY114', label: 'YY114退货单确认' },
                { value: 'YY131', label: 'YY131配送验收确认' },
                { value: 'YY132', label: 'YY132发票验收确认' },
                { value: 'YY133', label: 'YY133发票支付确认' },
                { value: 'YY151', label: 'YY151采购明细信息获取' },
                { value: 'YY152', label: 'YY152退货明细信息获取' },
                { value: 'YY153', label: 'YY153按配送明细获取' },
                { value: 'YY154', label: 'YY154配送单获取' },
                { value: 'YY155', label: 'YY155配送明细获取' },
                { value: 'YY156', label: 'YY156按发票明细获取' },
                { value: 'YY157', label: 'YY157发票信息获取' },
                { value: 'YY158', label: 'YY158发票明细获取' },
                { value: 'YY159', label: 'YY159按采购单获取采购明细状态' },
                { value: 'YY160', label: 'YY160发票状态获取' },
                { value: 'YY161', label: 'YY161配送单状态获取' },
                { value: 'YY162', label: 'YY162按退货单获取退货明细状态' },
                { value: 'YY163', label: 'YY163耗材单品UDI信息获取' },
                { value: 'YY164', label: 'YY164企业信息获取' },
                { value: 'YY165', label: 'YY165企业资证信息获取' },
                { value: 'YY166', label: 'YY166企业授权信息获取' }
            ],

        }
    },
    methods: {
        send(){
            this.req.contentRep = '';
            if(this.req.code.length == 0 || this.req.contentReq.length == 0){
                return;
            }
            var param = {
                code : this.req.code,
                contentReq : this.req.contentReq
            };
            this.axios.post(this.baseUrl + '/send', param).then(res => {
                if(res.data.data){
                    this.req = res.data.data;
                }
            });
        },
        codeChange(val){
            this.getReq();
        },
        getReq: function () {
            var param = {
                code : this.req.code
            };
            this.axios.post(this.baseUrl + '/getReq', param).then(res => {
                if(res.data.data){
                    this.req = res.data.data;
                }
            });
        },
        clearReq(){
            this.req.contentReq = '';
            this.req.contentRep = '';
        }
    },
    mounted() {
    }
};
</script>
<style>
</style>
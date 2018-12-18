<template>
    <div id="dept-mgr" style="height: 100%;">
        <el-row height="auto" style="height: 99%;">
            <el-col :span="6"  style="height: 100%;">
                <div style="margin-bottom: 5px">
                        <el-input 
                            v-model="filters.orgName" placeholder="请输入机构名称"  
                            icon="search" :on-icon-click="searchOrgs"   >
                         </el-input>
                </div>         		
              
                        <el-tree :data="treeValue" node-key="id"
                                :default-expanded-keys="expadoIds"
                                :props="defaultProps"
                                :highlight-current="true"
                                @node-click="handleNodeClick"  >
                        </el-tree>

               
            </el-col>
            <el-col :span="18" height="auto"  style="padding-left:5px;height: 99%">
                    <el-row >
                            <div style="margin-bottom: 7px;">
                                <el-button-group>
                                    <el-button type="success"   @click="addOrg" :disabled="btoFlag">添加</el-button>
                                    <el-button type="primary"  @click="updateOrg">保存</el-button>
                                    <el-button type="danger"  @click="delOrg" :disabled="btoFlag">删除</el-button>
                                </el-button-group>
                            </div>
                    </el-row>
                    <el-row>
                            <el-col  :span="18"  style="height: 99%">
                                <el-form  :model="formValue" labelWidth="100px" :rules="editFormRules" ref="editForm"  class="el-form-item-nomsg">
                                    <el-form-item label="id" v-show="false">
                                        <el-input v-model="formValue.id" :disabled="true" ></el-input>
                                    </el-form-item>
                                    <el-form-item label="pid"  v-show="false">
                                        <el-input v-model="formValue.pid" :disabled="true" ></el-input>
                                    </el-form-item>
                                    <el-form-item label="code" v-show="false">
                                        <el-input v-model="formValue.code" ></el-input>
                                    </el-form-item>
                                    <el-row>
                                        <el-col>
                                            <el-form-item label="机构名称：" prop="ename" :error="errors.enameErr">
                                                <el-input v-model="formValue.ename" ></el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                    <el-row>
                                        <el-col :span="8">
                                            <el-form-item label="自编码：" >
                                                <el-input v-model="formValue.selfCode"  ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="16">
                                            <el-form-item label="联系方式：" prop="contactWay" :error="errors.contactWayErr">
                                                <el-input v-model="formValue.contactWay" ></el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
									<!--
                                    <el-form-item label="机构别名：" prop="cname" :error="errors.cnameErr">
                                        <el-input v-model="formValue.cname" ></el-input>
                                    </el-form-item>
									-->
                                    <el-form-item label="地址：" prop="address">
                                        <el-input v-model="formValue.address" ></el-input>
                                    </el-form-item>   
                                    <el-form-item label="nodeKind" v-show="false">
                                        <el-input v-model="formValue.nodeKind" ></el-input>
                                    </el-form-item>
                                    <el-form-item label="flag" v-show="false">
                                        <el-input v-model="formValue.flag" ></el-input>
                                    </el-form-item>
                                    <el-form-item label="lastUpdateDatetime" v-show="false" >
                                        <el-input v-model="formValue.lastUpdateDatetime" ></el-input>
                                    </el-form-item>
                                    <el-form-item label="version" v-show="false">
                                        <el-input v-model="formValue.version"  ></el-input>
                                    </el-form-item>
                                    <el-form-item label="所属单位名："  prop="corpName">
                                        <el-input v-model="formValue.corpName"  :disabled="true" ></el-input>
                                    </el-form-item>
                                
                                    <el-form-item label="所属单位类型值：" prop="corpKind" v-show="false"><!-- 1医疗机构、2配送机构-->
                                        <el-input v-model="formValue.corpKind"  :disabled="true"  ></el-input>
                                    </el-form-item>
                                    <el-form-item label="所属单位ID：" prop="corpId">
                                        <el-input v-model="formValue.corpId"  :disabled="true" ></el-input>
                                    </el-form-item>
                                    <!--
                                    <el-form-item label="上级机构名：" prop="pName">
                                        <el-input v-model="formValue.pName"  :disabled="true" ></el-input>
                                    </el-form-item>
                                    <el-form-item label="上级机构别名："  prop="pCname">
                                        <el-input v-model="formValue.pCname" :disabled="true" ></el-input>
                                    </el-form-item>
                                    -->
                                    <el-form-item label="上级机构" prop="orgCode"  :error="errors.orgCodeErr" >
                            
                                        <el-cascader placeholder="请选择上级机构"
                                        v-model="selectedOrgParent"  
                                        :show-all-levels="false" 
                                        :options="orgInfo4cascader" 
                                        filterable 
                                        :change-on-select="true"  
                                        :props="orgSltProps" >
                                        </el-cascader>
                                    
                                    </el-form-item>
                                    <el-form-item label="排序号：">
                                        <el-input-number v-model="formValue.ordinal" @change="handleChange" :min="1" :max="100" ></el-input-number>
                                        <!--	<el-input v-model="formValue.ordinal"></el-input>-->
                                    </el-form-item>
                                    <el-form-item label="ERP编码：" >
                                        <el-input v-model="formValue.erpCode"  ></el-input>

                                    </el-form-item>
                                    <el-row>
                                        <el-col>
                                                <el-form-item label="备注：">
                                                    <el-input   type="textarea"
                                                                :rows="2"
                                                                placeholder="请输入内容" v-model="formValue.remark"></el-input>
                                                </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-form>
                            
                            </el-col>
                    </el-row>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import ElButtonGroup from "../../../node_modules/element-ui/packages/button/src/button-group";
    export default {
        components: {ElButtonGroup},
        data(){
            return {
                radioVal: '1',
                unitKinds:[],
                unit:{
                    id:'',
                    cname:'',
                    kind:''
                },
            
                btoFlag:false,
                editFormRules: {
                    ename: [{ required: true, message: '请输入机构名称', trigger: 'blur' }],
                    contactWay: [{ required: false, message: '请输入正确的电话号码', trigger: 'blur' }]

                },
                treeValue:[],
                defaultProps: {
                    children: 'children',
                    label: 'label',
                    id:'id',
                    pid:'pid',
                    code:'code'
                },
                
                orgInfo4cascader:[],
                orgSltProps: {
                    value: 'code',
                    label: 'label'
                },
                filters:{
                    orgName:''
                },
                ifUpdate:true,
                formValue:{
                    corpId:'',
                    corpName:''
                },
                selectedOrgId:'',
                selectedOrgCode:'',
                selectedOrgPid:'',
                expadoIds:['1'],
                    errors: {
                    contactWayErr: '',
                    enameErr: '',
                    cnameErr: ''
                },

            }
        },
        computed: {
            selectedOrgParent: {
                get: function () {
                    let res = [];
                    if (this.selectedOrgCode) {  //通过当前点击的orgId 的orgCode 找到其父code
                        let orgCodes = this.selectedOrgCode.split('.');
                        if (orgCodes.length > 1) {
                            var i = 0;
                            for (i; i < orgCodes.length; i++) {
                                let tempArr = orgCodes.slice(0, i + 1);
                                res.push(tempArr.join('.'));
                            
                            }
                            
                        } else {
                            res = orgCodes;
                        }
                    }
                    res.pop(); //移除最后一个
                    return res;
                },
                set: function (orgCodeArr) {
                    let res = "";
                    if (orgCodeArr.length > 0) {
                        res = orgCodeArr[orgCodeArr.length - 1];
                    }
                        this.formValue.pid = res;   //借用pid 存储pCode 后台通过该值查询后取真实的pid
                    
            
                }

            }
        },
        methods:{
            getOrgsByUnit:function (unitId) {

                var param = {};
                this.loading=true;
                this.axios.post(`/platformService/sys/org/getOrgsByUnit`, {}).then(res=>{
                    this.treeValue=res.data.data.children;
                    this.unit.id=this.treeValue[0].id;
                    this.unit.cname=this.treeValue[0].label;
                        this.unit.kind=this.treeValue[0].nodeType;
                        this.formValue.corpId = this.unit.id;
                        this.formValue.corpKind = this.unit.kind;
                        this.formValue.corpName = this.unit.cname;
                        this.expadoIds=[this.treeValue[0].id];
                            this.orgInfo4cascader = res.data.data.children[0].children;
                    this.loading=false;
                });
            
            },
            getOrg:function () {

                var param = {};
                this.loading=true;
                this.axios.post(`/platformService/sys/org/getOrgs`, null).then(res=>{

                    this.treeValue=res.data.data.children;
                    this.loading=false;
                });
                this.axios.post(`/platformService/sys/org/getTheOrg`, {id:this.selectedOrgId}).then(res=>{
                    this.formValue=res.data.data;

                });
            },
            handleNodeClick(data) {
               
                     this.errors.contactWayErr = '';
                     this.errors.cnameErr = '';
                    this.errors.enameErr = '';
                    
                //this.$refs.editForm.resetFields();    

                this.formValue={};
                this.ifUpdate = true;
                this.selectedOrgId = data.id;
                this.selectedOrgPid = data.pid;
                this.selectedOrgCode=data.code;
                if (data.id.indexOf('org') >= 0) {

                    let orgParams = {id: data.id};

                    this.axios.post(`/platformService/sys/org/getTheOrg`, orgParams).then(res => {
                        this.formValue = res.data.data;
                        this.btoFlag = false;
                        this.formValue.corpId = this.unit.id;
                        this.formValue.corpKind = this.unit.kind;
                        this.formValue.corpName = this.unit.cname;
                        //  this.formValue.corpKindName = ""; //需要关联查字典值表
                    });

                }
            },
            addOrg:function () {
                //this.$refs.editForm.resetFields(); 
                  this.errors.contactWayErr = '';
                     this.errors.cnameErr = '';
                    this.errors.enameErr = '';   
                this.btoFlag=true;
                this.ifUpdate=false;
                var pCname= this.formValue.cname;
                var pName=this.formValue.ename;
                this.formValue={id:null, pid:null, code:null,selfCode:null, kind:null, ename:null, cname:null,ordinal:null, shortPinyin:null, address:null,contactWay:null,remark:null,nodeKind:null,flag:null, lastUpdateDatetime:null, version:0};
                this.formValue.pid = '';
                /*pid 必须通过code查询
                if (this.selectedOrgId.indexOf('c') == -1) {
                    this.formValue.pid = this.selectedOrgId;
                }*/
                this.formValue.pCname=pCname;
                this.formValue.pName=pName;
                this.formValue.corpId=this.unit.id;
                this.formValue.corpKind=this.unit.kind;
                this.formValue.corpName=this.unit.cname;
                //  this.formValue.corpKindName=""; //需要关联查字典值表





            },
            updateOrg:function () {
                    this.errors.contactWayErr = '';
                    this.errors.cnameErr = '';
                    this.errors.enameErr = '';
                var _this = this;
                var form =  this.$refs.editForm;
                const h = this.$createElement;
                var arr=new Array();
                form.validate((valid) => {
                if (valid) {
                                        this.formValue.cname =this.formValue.ename;
 
                    this.axios.post(`/platformService/sys/org/update`, this.formValue).then(res=>{
                        
                        if(res.data.code=="0"){
                        
                            this.$message({
                                message: '操作成功！',
                                type: 'success'
                            });
                            
                            this.formValue=res.data.data;
                            this.formValue.corpId=this.unit.id;
                            this.formValue.corpKind=this.unit.kind;
                            this.formValue.corpName=this.unit.cname;
                            this.axios.post(`/platformService/sys/org/getOrgsByUnit`, {corpId:this.unit.id,corpCname:this.unit.cname}).then(res=>{
                                this.treeValue=res.data.data.children;
                                this.loading=false;
                            
                                this.expadoIds=[this.selectedOrgId];
                            });
                            this.btoFlag=false;

                        }
                            else if (res.data.code == "-122") {
                                    let err = res.data.validateErrors.fieldErrors[0];
                                        if (err.field == "ename") {
                                        this.errors.enameErr += err.message;
                                            arr.push(h('span',null,err.message));
                                        }
                                        if (err.field == "cname") {
                                            this.errors.cnameErr += err.message;
                                                arr.push(h('span',null,err.message));
                                        }
                                        if (err.field == "contactWay") {
                                            this.errors.contactWayErr += err.message;
                                                arr.push(h('span',null,err.message));
                                        }
                                            _this.$msgbox({title:"错误",type:"error",message:h('p',null,arr)});
                                    }
                        else {
                            this.$message.error('操作失败！');
                        }
                    }, err => {



                    });
                }else{
                        var fields = form.fields;
                        var msg ="";
                        fields.forEach(f=>{
                            if(f.validateState =="error"){
                                msg +="</br>"+f.validateMessage;
                                if (arr.length>0) arr.push(h('br',null,null));
                                arr.push(h('span',null,f.validateMessage));
                            }
                        });
                        _this.$msgbox({title:"错误",type:"error",message:h('p',null,arr)});
                    }

            });
            },
            delOrg:function () {
            
                //删除成功后树应该定位到删除节点的父亲
                var point =this.formValue.pid;
                this.axios.post(`/platformService/sys/org/delete`, this.formValue).then(res=>{
                    if(res.data.code=="0"){
                        this.$message({
                            message: '操作成功！',
                            type: 'success'
                        });
                        this.formValue={id:null, pid:null, code:null,selfCode:null, kind:null, ename:null, cname:null,ordinal:null, shortPinyin:null, address:null,contactWay:null,remark:null,nodeKind:null,flag:null, lastUpdateDatetime:null, version:0};
                        this.btoFlag=true;
                        this.axios.post(`/platformService/sys/org/getOrgsByUnit`, {}).then(res=>{
                            this.treeValue=res.data.data.children;
                            this.loading=false;
                
                            this.selectedOrgId=point;
                            this.expadoIds=[this.selectedOrgId];
                            if(res.data.data.children[0].children){
                                this.orgInfo4cascader = res.data.data.children[0].children;
                            }
                        });
                    }
                    else{
                        if(res.data.validateErrors.fieldErrors.length>0){
                            this.$message.error( res.data.validateErrors.fieldErrors[0]);
                        }
                        else {
                            this.$message.error(res.data.msg);
                        }
                    }
                });
            },
            searchOrgs:function () {
                if(this.unit.id==null){
                    alert("请先选择单位！");
                    return;
                }
                var p = this.filters.orgName;
                let param = {cname:p,corpId:this.unit.id};
                this.loading=true;
                this.axios.post(`/platformService/sys/org/searchOrgs`, param).then(res=>{
                    var ids=[];
                    for(var i = 0;i < res.data.data.length; i++) {
                        ids[i] =res.data.data[i].id;
                    }
                    this.expadoIds= ids;
                });
            },
            handleChange(value) {
            }
        },
        mounted() {
            this.userInfo;
            //  this.getOrg(); //展开树
            this.getOrgsByUnit();

        }
    }
</script>

<style>
	#dept-mgr .el-tree-node__label {
		font-size: 12px;
	}
	#dept-mgr .el-table {
		font-size: 12px;
	}
	#dept-mgr .el-tree {
		min-height: calc(100% - 75px);
     
	}
 #dept-mgr .div4Tree {
    border:1px solid #d1dbe5;
    height: calc(100% - 5px);
    overflow-y:auto;
  }
 #dept-mgr .fullheight {
       height: calc(100% - 75px);
    }
</style>
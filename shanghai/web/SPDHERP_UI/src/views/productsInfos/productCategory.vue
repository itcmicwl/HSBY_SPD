<!--产品分类-->
<template>
    <div style="height:95%">
        <el-row height="auto" style="height: 100%;">
            <el-col :span="6"  style="height: 100%;">
                <div style="margin-bottom: 5px">
                    <el-input v-model="filters.kindName" placeholder="请输入类型名称"
                            icon="search" :on-icon-click="searchs"  @keyup.enter.native="searchs"    >
                    </el-input>
                </div>
                <el-tree :data="kindsValue" node-key="id"                       
                          :default-expanded-keys="expadoIds"
                          :expand-on-click-node="false"
                         :props="defaultProps"
                         :highlight-current="true"
                         @node-click="handleNodeClick"  >
                </el-tree>
            </el-col>
            <el-col :span="18"  style="height: 100%; padding-left:5px;">
                     <el-row >
                            <div style="margin-bottom: 7px;">
                                <el-button type="primary"   @click="add" :disabled="btoFlag">添加</el-button>
                                <el-button type="primary"  @click="update">保存</el-button>
                                <el-button type="primary"  @click="del" :disabled="btoFlag">删除</el-button>
                            </div>
                    </el-row>
                    <el-row  >
                            <el-col   :span="18">
                            
                                <el-form  :model="formValue" labelWidth="100px" :rules="editFormRules" ref="editForm"  class="el-form-item-nomsg">
                                    <el-form-item label="id" v-show="false">
                                        <el-input v-model="formValue.id" :disabled="true" ></el-input>
                                    </el-form-item>
                                    <el-row>
                                        <el-col>
                                            <el-form-item label="上级分类名称：" prop="pname" >
                                                <el-input v-model="pname" ></el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                    <el-row>
                                        <el-col>
                                            <el-form-item label="分类名称：" prop="kindName" :error="errors.kindName">
                                                <el-input v-model="formValue.kindName" ></el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                     <el-row>
                                        <el-col>
                                            <el-form-item label="内部编码：" prop="inCode">
                                                <el-input v-model="formValue.inCode" ></el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                    <el-row>
                                        <el-col >
                                            <el-form-item label="状态：" >   
                                                 <el-radio class="radio" v-model="formValue.state" label="10">正常</el-radio>
                                                 <el-radio class="radio" v-model="formValue.state" label="20">停用</el-radio>
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
    export default{
        data(){
            return{
                defaultProps: {
                    children: 'children',
                    label: 'label',
                    id:'id',
                    pId:'pid',
                    code:'code',
                    hosId:'hosId',
                    state:'state',
                    
                },
                filters:{
                    kindName:null,
                    hosId:null,

                },
                btoFlag:false,
                editFormRules:{},
                formValue:{id:null,hosId:null,kindName:null,state:null,levelCode:null,inCode:null,version:null},
            
                kindsValue:[],
                expadoIds:[null],
                loading:'false',
                pname:'商品分类',
                selectedNode:null,
                editFormRules: {
                  kindName: [{ required: true, message: '请输入分类名称', trigger: 'blur' }]
                },
                errors: {
                    kindNameErr: ''
                }
            }
        },
      
        methods:{
            getKinds:function () {
               
                this.loading=true;
              
                let param = { orderBy: "", queryObject: {hosId:this.filters.hosId}, page:0, rows: 0 };
                this.axios.post(`/spdHERPService/productsInfos/hosKindcode/list4tree`, param).then(res=>{
                  
                    this.kindsValue=res.data.data;
                    this.loading=false;
                });
            },
            searchs:function () {
                 this.loading=true;
                let param = { orderBy: "", queryObject: {hosId: this.filters.hosId,kindName: this.filters.kindName}, page:0, rows: 0 };
                 this.axios.post(`/spdHERPService/productsInfos/hosKindcode/query`, param).then(res=>{
                    var ids=[];
                    for(var i = 0;i < res.data.data.length; i++) {
                        ids[i] =res.data.data[i].id;
                    }
                    this.loading=false;
                    this.expadoIds= ids;
                });
               
            },
            handleNodeClick(data) {
                this.rSetFromVale();
               this.formValue.id= data.id;
               this.formValue.kindName=data.label;
               this.formValue.state=data.state;
               this.formValue.hosId=data.hosId;
                this.formValue.inCode= data.inCode;
               this.formValue.levelCode=data.code;
              //查询上级
              // alert(data.code);
               let pid  = this.deal4Pid(data);
             
                  this.axios.get(`/spdHERPService/productsInfos/hosKindcode/get/`+pid).then(res=>{
                      
                     if(res.data.data)
                       this.pname =res.data.data.kindName;
                  
                });
                this.selectedNode = data;
             
               
            },
            rSetFromVale:function(){
               this.formValue.id= null;
               this.formValue.kindName=null;
               this.formValue.state=null;
               this.formValue.hosId=null;
               this.formValue.levelCode=null;
               this.formValue.inCode=null;
               this.pname ='商品分类';
             
            },

            add(){
                if(this.selectedNode){
                    this.formValue.state='10';
                    this.formValue.kindName=null;
                    this.formValue.hosId=this.selectedNode.hosId;
                    this.formValue.levelCode=this.selectedNode.code;
                    this.formValue.inCode = this.selectedNode.inCode;
                    this.formValue.pid= this.selectedNode.id;
                    this.formValue.id = null;
                    this.pname = this.selectedNode.label;

                }
                else{
                    alert("请从左侧选择一层分组");
                }
                   
            },
            deal4Pid(data){
                let pid = null;
                if( data.code){
                    let codes = data.code.split('.');
                    if (codes.length > 2) {
                      //  alert(codes[codes.length-2]);
                        pid =  codes[codes.length-2];
                    }
                    else{
                      //  let arr = codes[0];
                      //  let tem=   arr.split(':')
                        
                      //  alert(tem[1]);
                        pid = codes[0].split(':')[1];
                    }

                }

                if(pid === data.id) 
                   pid =null;
                return pid;
            },
            update:function () {
                if(this.selectedNode ==null){
                      alert("请从左侧选择一层分组");
                      return;
                }
                var _this = this;
                var form =  this.$refs.editForm;
                const h = this.$createElement;
                var arr=new Array();
                form.validate((valid) => {
                if (valid) {
                    let serviceUrl = this.formValue.id ? "/spdHERPService/productsInfos/hosKindcode/update" : "/spdHERPService/productsInfos/hosKindcode/add";
                    this.axios.post(serviceUrl, this.formValue).then(res=>{   
                        if(res.data.code=="0"){
                            this.$message({
                                message: '操作成功！',
                                type: 'success'
                            });
                         this.getKinds();
                         this.expadoIds=[this.selectedNode.id]
                        }
                            else if (res.data.code == "-122") {
                                    let err = res.data.validateErrors.fieldErrors[0];
                                        if (err.field == "kindName") {
                                        this.errors.kindNameErr += err.message;
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
            del(){
                if(this.selectedNode ==null){
                      alert("请从左侧选择一层分组");
                      return;
                }
                    this.axios.post(`/spdHERPService/productsInfos/hosKindcode/delete`,this.formValue).then(res=>{
                        if(res.data.code=="0"){
                                this.$message({
                                    message: '删除成功！',
                                    type: 'success'
                                });
                               this.getKinds();    
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
            }
       
        },
        mounted(){
             this.filters.hosId = this.user.corpId;
             this.getKinds();
        }
    }
</script>
<style scoped>
  .el-tree {
		min-height: calc(100% - 75px);
     
	}
</style>
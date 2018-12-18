<template>
    <div id="companyInfo" style="height:100%;overflow-y: scroll;">
        <el-row style="margin-bottom: 10%">
            <el-col :span="12" style="min-width: 740px;">
                <div>
                    <el-form :model="company" label-width="120px" :rules="editFormRules" ref="editForm"
                             class="myInfocompanyOutput">
                        <el-row>
                            <el-col :span="11">
                                <el-col :span="24">
                                    <el-form-item label="企业名称：" :error="errors.codeErr">
                                        <el-input v-model="company.cname" disabled style="border: 0px;"
                                                  ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="24">
                                    <el-form-item label="企业编号：" prop="code" :error="errors.codeErr">
                                        <el-input v-model="company.code" :disabled="editDisable" ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="24">
                                    <el-form-item label="公司别名：" prop="anotherName" :error="errors.codeErr">
                                        <el-input v-model="company.anotherName" :disabled="editDisable" ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="24">
                                    <el-form-item label="法人代表：" prop="legal" :error="errors.codeErr">
                                        <el-input v-model="company.legal" :disabled="editDisable" ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="24">
                                    <el-form-item label="地址：" prop="address" :error="errors.codeErr">
                                        <el-input v-model="company.address" :disabled="editDisable" ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="24">
                                    <el-form-item label="电子邮箱：" prop="email" :error="errors.codeErr">
                                        <el-input v-model="company.email" :disabled="editDisable" ></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col :span="13">
                                <el-col :span="15" style="margin-left: 17%;">
                                        <el-upload
                                                class="avatar-uploader"
                                                :action="uploadLogoUrl"
                                                :show-file-list="false"
                                                :headers="userInfo"
                                                :on-success="handleAvatarSuccess"
                                                :before-upload="beforeAvatarUpload">
                                            <img v-if="logoUrl" :src="logoUrl" style="border: 1px dashed #d9d9d9;border-radius: 6px;width: 95%;display: block;">
                                            <i v-else class="el-icon-plus logoImgStyle"></i>
                                        </el-upload>
                                </el-col>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="11">
                                <el-form-item label="联系人：" prop="linkman" :error="errors.codeErr">
                                    <el-input v-model="company.linkman" :disabled="editDisable" ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="10">
                                <el-form-item label="电话：" prop="contactWay" :error="errors.codeErr">
                                    <el-input v-model="company.contactWay" :disabled="editDisable" ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="11">
                                <el-form-item label="邮政编码：" prop="postCode" :error="errors.codeErr">
                                    <el-input v-model="company.postCode" :disabled="editDisable" ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="10">
                                <el-form-item label="传真：" prop="contactFax" :error="errors.codeErr">
                                    <el-input v-model="company.contactFax" :disabled="editDisable" ></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-col :span="21">
                            <el-form-item label="备注信息：" prop="remark">
                                <el-input type="textarea" :rows="2" :disabled="editDisable" v-model="company.remark"
                                          ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="21">
                            <el-form-item label="是否三证合一：" prop="isThreeInOne">                       
                                <el-switch
                                v-model="company.isThreeInOne"
                                active-color="#13ce66"
                                inactive-color="#ff4949"
                                :disabled="editDisable"
                                active-text="是"
                                inactive-text="否"
                                active-value="1"
                                inactive-value="0"
                                @change="isCertMergeChange">
                                </el-switch>
                            </el-form-item>
                        </el-col>
                        <el-col :span="21">
                            <el-form-item label="企业三证：">
                            </el-form-item>
                        </el-col>
                        <el-col :span="23">
                            <el-form-item label="">
                            <el-col :span="7">
                                <div class="myInfoCompanyCerts">
                                    <div id="test" style="margin-top: 3%;">
                                        <el-upload
                                                :disabled="editDisable"
                                                :action="uploadCompanyCertsUrl"
                                                list-type="picture-card"
                                                :show-file-list="false"
                                                :headers="userInfo"
                                                :on-success="yyzzSuccessUpload"
                                                :before-upload="beforeAvatarUpload">
                                            <img v-if="newyyzz" :src="newyyzz" style="width: 100%;height: 100%;display: block;">
                                            <i v-else class="el-icon-plus"></i>
                                        </el-upload>
                                        <div>
                                            <div class="certStyle">
                                                <span class="certWordsStyle">营业执照</span>
                                                <span><el-button type="text" class="button certButtonStyle" @click="checkYyzzCert">查看</el-button></span>
                                            </div>
                                            <div class="certCodes">
                                                <el-input placeholder="注册号" v-model="yyzzCertCode" :disabled="editDisable" ></el-input>
                                            </div>
                                            <div class="block" style="margin-left: 3%;">
                                                <span class="demonstration"></span>
                                                <el-date-picker
                                                        @change="checkYYZZDate"
                                                        :disabled="editDisable"
                                                        style="width: 96%;margin-bottom: 2px;"
                                                        size = "mini"
                                                        v-model="yyzzBeginDate"
                                                        type="date"
                                                        placeholder="选择生效日期">
                                                </el-date-picker>
                                            </div>
                                            <div class="block" style="margin-left: 3%;">
                                        <span class="">
                                            <el-checkbox v-model="yyzzChecked" @change="handleCheckedYYZZChange" :disabled="editDisable" >
                                                <span style="font-size: 10px;">是否长期有效</span>
                                            </el-checkbox>
                                        </span>
                                                <el-date-picker
                                                        @change="checkYYZZDate"
                                                        :disabled="editDisable"
                                                        style="width: 96%;margin-bottom: 5%;"
                                                        size = "mini"
                                                        v-model="yyzzEndDate"
                                                        type="date"
                                                        placeholder="选择截止日期">
                                                </el-date-picker>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </el-col>

                            <el-col :span="7" style="margin-left: 1%;">
                                <div class="myInfoCompanyCerts">
                                    <div id="test" style="margin-top: 3%;">
                                        <el-upload
                                                :disabled="editDisable"
                                                :action="uploadCompanyCertsUrl"
                                                list-type="picture-card"
                                                :show-file-list="false"
                                                :headers="userInfo"
                                                :on-success="jyxkSuccessUpload"
                                                :before-upload="beforeAvatarUpload">
                                            <img v-if="newjyxk" :src="newjyxk" style="width: 100%;height: 100%;display: block;">
                                            <i v-else class="el-icon-plus"></i>
                                        </el-upload>
                                        <div>
                                            <div class="certStyle">
                                                <span class="certWordsStyle">执业许可证</span>
                                                <span><el-button type="text" class="button certButtonStyle" @click="checkJyxkCert">查看</el-button></span>
                                            </div>
                                            <div class="certCodes">
                                                <el-input placeholder="许可证号" v-model="jyxkCertCode" :disabled="editDisable" ></el-input>
                                            </div>
                                            <div class="block" style="margin-left: 3%;">
                                                <span class="demonstration"></span>
                                                <el-date-picker
                                                        @change="checkJYXKDate"
                                                        :disabled="editDisable"
                                                        style="width: 96%;margin-bottom: 2px;"
                                                        size = "mini"
                                                        v-model="jyxkBeginDate"
                                                        type="date"
                                                        placeholder="选择生效日期">
                                                </el-date-picker>
                                            </div>
                                            <div class="block" style="margin-left: 3%;">
                                        <span class="">
                                            <el-checkbox v-model="jyxkChecked" @change="handleCheckedJYXKChange" :disabled="editDisable" >
                                                <span style="font-size: 10px;">是否长期有效</span>
                                            </el-checkbox>
                                        </span>
                                                <el-date-picker
                                                        @change="checkJYXKDate"
                                                        :disabled="editDisable"
                                                        style="width: 96%;margin-bottom: 5%;"
                                                        size = "mini"
                                                        v-model="jyxkEndDate"
                                                        type="date"
                                                        placeholder="选择截止日期">
                                                </el-date-picker>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </el-col>

                            <el-col :span="7" style="margin-left: 1%;" v-if="isThreeInOne == 0">
                                <div class="myInfoCompanyCerts">
                                    <div id="test" style="margin-top: 3%;">
                                        <el-upload
                                                :disabled="editDisable"
                                                :action="uploadCompanyCertsUrl"
                                                list-type="picture-card"
                                                :show-file-list="false"
                                                :headers="userInfo"
                                                :on-success="swdjSuccessUpload"
                                                :before-upload="beforeAvatarUpload">
                                            <img v-if="newswdj" :src="newswdj" style="width: 100%;height: 100%;display: block;">
                                            <i v-else class="el-icon-plus"></i>
                                        </el-upload>
                                        <div>
                                            <div class="certStyle">
                                                <span class="certWordsStyle">税务登记证</span>
                                                <span><el-button type="text" class="button certButtonStyle" @click="checkSwdjCert">查看</el-button></span>
                                            </div>
                                            <div class="certCodes">
                                                <el-input placeholder="登记证号" v-model="swdjCertCode" :disabled="editDisable" ></el-input>
                                            </div>
                                            <div class="block" style="margin-left: 3%;">
                                                <span class="demonstration"></span>
                                                <el-date-picker
                                                        @change="checkSWDJDate"
                                                        :disabled="editDisable"
                                                        style="width: 96%;margin-bottom: 2px;"
                                                        size = "mini"
                                                        v-model="swdjBeginDate"
                                                        type="date"
                                                        placeholder="选择生效日期">
                                                </el-date-picker>
                                            </div>
                                            <div class="block" style="margin-left: 3%;">
                                        <span class="">
                                            <el-checkbox v-model="swdjChecked" @change="handleCheckedSWDJChange" :disabled="editDisable" >
                                                <span style="font-size: 10px;">是否长期有效</span>
                                            </el-checkbox>
                                        </span>
                                                <el-date-picker
                                                        @change="checkSWDJDate"
                                                        :disabled="editDisable"
                                                        style="width: 96%;margin-bottom: 5%;"
                                                        size = "mini"
                                                        v-model="swdjEndDate"
                                                        type="date"
                                                        placeholder="选择截止日期">
                                                </el-date-picker>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </el-col>
                            <!-- 组织机构代码正 -->
                             <el-col :span="7" style="margin-left: 1%;" v-if="isThreeInOne == 0">
                                <div class="myInfoCompanyCerts">
                                    <div id="test" style="margin-top: 3%;">
                                        <el-upload
                                                :disabled="editDisable"
                                                :action="uploadCompanyCertsUrl"
                                                list-type="picture-card"
                                                :show-file-list="false"
                                                :headers="userInfo"
                                                :on-success="zzjgSuccessUpload"
                                                :before-upload="beforeAvatarUpload">
                                            <img v-if="newzzjg" :src="newzzjg" style="width: 100%;height: 100%;display: block;">
                                            <i v-else class="el-icon-plus"></i>
                                        </el-upload>
                                        <div>
                                            <div class="certStyle">
                                                <span class="certWordsStyle">组织机构代码证</span>
                                                <span><el-button type="text" class="button certButtonStyle" @click="checkZzjgCert">查看</el-button></span>
                                            </div>
                                            <div class="certCodes">
                                                <el-input placeholder="许可证号" v-model="zzjgCertCode" :disabled="editDisable" ></el-input>
                                            </div>
                                            <div class="block" style="margin-left: 3%;">
                                                <span class="demonstration"></span>
                                                <el-date-picker
                                                        @change="checkZZJGDate"
                                                        :disabled="editDisable"
                                                        style="width: 96%;margin-bottom: 2px;"
                                                        size = "mini"
                                                        v-model="zzjgBeginDate"
                                                        type="date"
                                                        placeholder="选择生效日期">
                                                </el-date-picker>
                                            </div>
                                            <div class="block" style="margin-left: 3%;">
                                        <span class="">
                                            <el-checkbox v-model="zzjgChecked" @change="handleCheckedZZJGChange" :disabled="editDisable" >
                                                <span style="font-size: 10px;">是否长期有效</span>
                                            </el-checkbox>
                                        </span>
                                                <el-date-picker
                                                        @change="checkZZJGDate"
                                                        :disabled="editDisable"
                                                        style="width: 96%;margin-bottom: 5%;"
                                                        size = "mini"
                                                        v-model="zzjgEndDate"
                                                        type="date"
                                                        placeholder="选择截止日期">
                                                </el-date-picker>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </el-col>
                            </el-form-item>
                        </el-col>

                    </el-form>

                    <el-col :span="24">
                        <el-button style="margin-left: 51%;margin-top: 10px;" :loading="editLoading" @click="handleBtnEditClick"  type="primary">
                            {{editDisable ? '编辑' : '提交'}}
                        </el-button>
                        <el-button style="margin-top: 10px;" v-if="editDisable === false" @click="handleBtnCancleClick"  type="warning">取消
                        </el-button>
                    </el-col>

                </div>
            </el-col>
        </el-row>
        <el-dialog :title="yyzzImgDailogTitle"  close="onClose"  :visible.sync="yyzzImgDailog" :close-on-click-modal="false" >
            <img width="100%" :src="dialogYyzzImageUrl" alt="">
        </el-dialog>
        <el-dialog :title="jyxkImgDailogTitle"  close="onClose"  :visible.sync="jyxkImgDailog" :close-on-click-modal="false" >
            <img width="100%" :src="dialogJyxkImageUrl" alt="">
        </el-dialog>
        <el-dialog :title="swdjImgDailogTitle"  close="onClose"  :visible.sync="swdjImgDailog" :close-on-click-modal="false" >
            <img width="100%" :src="dialogSwdjImageUrl" alt="">
        </el-dialog>
        <el-dialog :title="zzjgImgDailogTitle"  close="onClose"  :visible.sync="zzjgImgDailog" :close-on-click-modal="false" >
            <img width="100%" :src="dialogZzjgImageUrl" alt="">
        </el-dialog>
    </div>
</template>
<script>
    import {fileServer} from '../../common/js/constance';
    import {mapGetters} from 'vuex';
    import moment from 'moment';
    export default {
        data() {
            return {
                yyzzImgDailogTitle:'',
                yyzzImgDailog:false,
                dialogYyzzImageUrl:'',
                jyxkImgDailogTitle:'',
                jyxkImgDailog:false,
                dialogJyxkImageUrl:'',
                swdjImgDailogTitle:'',
                swdjImgDailog:false,
                dialogSwdjImageUrl:'',
                zzjgImgDailogTitle:'',
                zzjgImgDailog:false,
                dialogZzjgImageUrl:'',
                yyzzBeginDate:'',
                jyxkBeginDate:'',
                swdjBeginDate:'',
                zzjgBeginDate:'',
                yyzzEndDate:'',
                jyxkEndDate:'',
                swdjEndDate:'',
                zzjgEndDate:'',
                yyzzCertCode:'',
                jyxkCertCode:'',
                swdjCertCode:'',
                zzjgCertCode:'',
                yyzzId:'',
                jyxkId:'',
                swdjId:'',
                zzjgId:'',
                yyzzChecked:false,
                jyxkChecked:false,
                swdjChecked:false,
                zzjgChecked:false,
                newjyxk:'',
                newyyzz:'',
                newswdj:'',
                newzzjg:'',
                uploadLogoUrl: '',
                uploadCompanyCertsUrl: '',
                imgBasePath: '',
                basCompanyCertificateImage: {id: ''},
                certKind: '',
                yyzz: [],
                jyxk: [],
                swdj: [],
                zzjg: [],
                value6: '',
                value5: '',
                value4: '',
                dialogImageUrl: '',
                dialogVisible: false,
                dialogImageUrl2: '',
                dialogVisible2: false,
                dialogImageUrl3: '',
                dialogVisible3: false,
                logoUrl: '',
                certKinds: [{
                    id: 1,
                    name: '营业执照'
                }, {
                    id: 2,
                    name: '经营许可证'
                }, {
                    id: 3,
                    name: '税务登记证'
                }, {
                    id: 4,
                    name: '组织机构代码证'
                }],
                loading: false,
                company: {
                    cname: '',
                    code: '',
                    isThreeInOne:'',
                    companyCertificateVos: [],
                },
                modelTitle: "",
                editFormVisible: false,
                editFormVisible1: false,
                editLoading: false,
                editCertDateLoading: false,
                editFormRules: {},
                errors: {
                    codeErr: '',
                    cnameErr: ''
                },
                editDisable: true,
                isThreeInOne:'',
            }
        },
        computed: {
            userInfo(){
                return {
//                    'X-US':this.getLoginUser.xue
                }
            },
        },
        methods: {
            handleBtnCancleClick () {
                this.editDisable = true;
            },
            handleBtnEditClick () {
                if (!this.editDisable) {
                    this.updateDate(function () {
                        //this.editDisable = !this.editDisable;
                    });
                } else {
                    this.editDisable = !this.editDisable;
                }

            },
            updateLogo: function () {
                this.editFormVisible1 = true;
            },
            //获取企业信息
            
            getCompanyInfo: function () {
                var _this = this;
                _this.loading = true;
                _this.axios.post('/spdHERPService/myInfo/companyInfo/getCompanyInfo', _this.user.corpId).then(res => {
                    _this.loading = false;
                    if (res.data.code == "0") {
                        _this.company = res.data.data;
                        _this.isThreeInOne = _this.company.isThreeInOne;
                        if (_this.company.companyCertificateVos.length > 0) {
                            // 营业执照
                            var yyzzObj = res.data.data.companyCertificateVos.find(item => item.certKind == 1);
                            if(yyzzObj){
                                _this.yyzz = yyzzObj.basCompanyCertificateImages;
                                if(_this.yyzz.length>0){
                                    _this.newyyzz = this.imgBasePath + _this.yyzz[0].filePath;
                                }
                                _this.yyzzBeginDate = yyzzObj.expdtBeginDate;
                                _this.yyzzEndDate = yyzzObj.expdtEndDate;
                                _this.yyzzChecked = yyzzObj.isValiding == '1' ? true : false;
                                _this.yyzzCertCode = yyzzObj.certCode;
                                _this.yyzzId = yyzzObj.id;
                            }
                            // 经营许可证
                            var jyxkObj = res.data.data.companyCertificateVos.find(item => item.certKind == 2);
                            if(jyxkObj){
                                _this.jyxk = jyxkObj.basCompanyCertificateImages;
                                if(_this.jyxk.length>0){
                                    _this.newjyxk = this.imgBasePath + _this.jyxk[0].filePath;
                                }
                                _this.jyxkBeginDate = jyxkObj.expdtBeginDate;
                                _this.jyxkEndDate = jyxkObj.expdtEndDate;
                                _this.jyxkChecked = jyxkObj.isValiding == '1' ? true : false;
                                _this.jyxkCertCode = jyxkObj.certCode;
                                _this.jyxkId = jyxkObj.id;
                            }
                            //非三证合一，则显示税务登记证和组织机构代码证
                            if(_this.isThreeInOne != 1){
                                // 税务登记证
                                var swdjObj = res.data.data.companyCertificateVos.find(item => item.certKind == 3);
                                if(swdjObj){
                                    _this.swdj = swdjObj.basCompanyCertificateImages;
                                    if(_this.swdj.length>0){
                                        _this.newswdj = this.imgBasePath + _this.swdj[0].filePath;
                                    }
                                    _this.swdjBeginDate = swdjObj.expdtBeginDate;
                                    _this.swdjEndDate = swdjObj.expdtEndDate;
                                    _this.swdjChecked = swdjObj.isValiding == '1' ? true : false;
                                    _this.swdjCertCode = swdjObj.certCode;
                                    _this.swdjId = swdjObj.id;
                                }
                                // 组织机构代码证
                                var zzjgObj = res.data.data.companyCertificateVos.find(item => item.certKind == 4);
                                if(zzjgObj){
                                    _this.zzjg = zzjgObj.basCompanyCertificateImages;
                                    if(_this.zzjg.length>0){
                                        _this.newzzjg = this.imgBasePath + _this.zzjg[0].filePath;
                                    }
                                    _this.zzjgBeginDate = zzjgObj.expdtBeginDate;
                                    _this.zzjgEndDate = zzjgObj.expdtEndDate;
                                    _this.zzjgChecked = zzjgObj.isValiding == '1' ? true : false;
                                    _this.zzjgCertCode = zzjgObj.certCode;
                                    _this.zzjgId = zzjgObj.id;
                                }
                            }
                        }
                        _this.logoUrl = _this.imgBasePath + res.data.data.logo;
                    } else {
                        _this.$message.error(res.data.msg);
                    }
                }, err => {
                    _this.loading = false;
                });
            },
            isCertMergeChange: function (value) {
                if(value == 1){
                    this.isThreeInOne = value;
                    this.newswdj = '';
                    this.swdjBeginDate = '';
                    this.swdjEndDate = '';
                    this.swdjChecked = false;
                    this.swdjCertCode = '';
                    this.swdjId = '';
                    this.newzzjg = '';
                    this.zzjgBeginDate = '';
                    this.zzjgEndDate = '';
                    this.zzjgChecked = false;
                    this.zzjgCertCode = '';
                    this.zzjgId = '';
                } else {
                    this.isThreeInOne = value;
                }
            },
            editSubmit: function (callback) {
                var _this = this;
                var form = this.$refs.editForm;
                const h = this.$createElement;
                var arr = new Array();
                form.validate((valid) => {
                    if (valid) {
                        this.editLoading = true;
                        let serviceUrl = "/spdHERPService/myInfo/companyInfo/updateCompanyInfoVo";
                        this.axios.post(serviceUrl, this.company).then(res => {
                            this.editLoading = false;
                            if (res.data.code == "0") {
                                this.$message({message: '操作成功！', type: 'success'});
                                this.editFormVisible = false;
                                this.getCompanyInfo();
                                callback();
                            } else if (res.data.code == "-122") {
                                let err = res.data.validateErrors.fieldErrors[0];
                                if (err.field == "code") {
                                    this.errors.codeErr += err.message;
                                }
                                if (err.field == "cname") {
                                    this.errors.cnameErr += err.message;
                                }
                            }
                            else {
                                this.$message.error('操作失败！');
                            }
                        }, err => {
                            this.editLoading = false;
                        });
                    } else {
                        var fields = form.fields;
                        var msg = "";
                        fields.forEach(f => {
                            if (f.validateState == "error") {
                                msg += "</br>" + f.validateMessage;
                                if (arr.length > 0) arr.push(h('br', null, null));
                                arr.push(h('span', null, f.validateMessage));
                            }
                        });
                        _this.$msgbox({title: "错误", type: "error", message: h('p', null, arr)});
                    }
                });
            },

            updateDate: function (callback) {
                var certArray = this.company.companyCertificateVos.slice(0);// 完全复制图片数组至临时数组
                this.company.companyCertificateVos.length = 0;// 图片数组复制出来后，清空数组，以便push新数据提交至后台
                // 营业执照
                var tempYyzzObj = certArray.find(item => item.certKind == 1);
                if(tempYyzzObj){
                    //tempYyzzObj.expdtBeginDate = this.yyzzChecked ? '' : this.yyzzBeginDate;
					tempYyzzObj.expdtBeginDate = this.yyzzBeginDate;
                    tempYyzzObj.expdtEndDate = this.yyzzChecked ? '' : this.yyzzEndDate;
                    tempYyzzObj.isValiding = this.yyzzChecked ? 1 : 0;
                    tempYyzzObj.certCode = this.yyzzCertCode;
                    tempYyzzObj.id = this.yyzzId;
                    this.company.companyCertificateVos.push(tempYyzzObj) ;
                }else {
                    var tempObj = {
                        companyId : this.company.id,
                        certKind : 1,
                        expdtBeginDate : this.yyzzBeginDate,
                        expdtEndDate : this.yyzzEndDate,
                        isValiding : this.yyzzChecked ? 1 : 0,
                        certCode : this.yyzzCertCode,
                        id : this.yyzzId
                    };
                    this.company.companyCertificateVos.push(tempObj);
                }

                // 经营许可证
                var tempJyxkObj = certArray.find(item => item.certKind == 2);
                if(tempJyxkObj){
                    //tempJyxkObj.expdtBeginDate = this.jyxkChecked ? '' : this.jyxkBeginDate;
					tempJyxkObj.expdtBeginDate = this.jyxkBeginDate;
                    tempJyxkObj.expdtEndDate = this.jyxkChecked ? '' : this.jyxkEndDate;
                    tempJyxkObj.isValiding = this.jyxkChecked ? 1 : 0;
                    tempJyxkObj.certCode = this.jyxkCertCode;
                    tempJyxkObj.id = this.jyxkId;
                    this.company.companyCertificateVos.push(tempJyxkObj) ;
                }else {
                    var tempObj = {
                        companyId : this.company.id,
                        certKind : 2,
                        expdtBeginDate : this.jyxkBeginDate,
                        expdtEndDate : this.jyxkEndDate,
                        isValiding : this.jyxkChecked ? 1 : 0,
                        certCode : this.jyxkCertCode,
                        id : this.jyxkId
                    };
                    this.company.companyCertificateVos.push(tempObj);
                }
                //若非三证合一则不保存税务登记证和组织机构代码证
                if(this.isThreeInOne != 1){
                    // 税务登记证
                    var tempSwdjObj = certArray.find(item => item.certKind == 3);
                    if(tempSwdjObj){
                        //tempSwdjObj.expdtBeginDate = this.swdjChecked ? '' : this.swdjBeginDate;
                        tempSwdjObj.expdtBeginDate = this.swdjBeginDate;
                        tempSwdjObj.expdtEndDate = this.swdjChecked ? '' : this.swdjEndDate;
                        tempSwdjObj.isValiding = this.swdjChecked ? 1 : 0;
                        tempSwdjObj.certCode = this.swdjCertCode;
                        tempSwdjObj.id = this.swdjId;
                        this.company.companyCertificateVos.push(tempSwdjObj) ;
                    }else {
                        var tempObj = {
                            companyId : this.company.id,
                            certKind : 3,
                            expdtBeginDate : this.swdjBeginDate,
                            expdtEndDate : this.swdjEndDate,
                            isValiding : this.swdjChecked ? 1 : 0,
                            certCode : this.swdjCertCode,
                            id : this.swdjId
                        };
                        this.company.companyCertificateVos.push(tempObj);
                    }

                    // 组织机构代码证
                    var tempZzjgObj = certArray.find(item => item.certKind == 4);
                    if(tempZzjgObj){
                        //tempSwdjObj.expdtBeginDate = this.swdjChecked ? '' : this.swdjBeginDate;
                        tempZzjgObj.expdtBeginDate = this.zzjgBeginDate;
                        tempZzjgObj.expdtEndDate = this.zzjgChecked ? '' : this.zzjgEndDate;
                        tempZzjgObj.isValiding = this.zzjgChecked ? 1 : 0;
                        tempZzjgObj.certCode = this.zzjgCertCode;
                        tempZzjgObj.id = this.zzjgId;
                        this.company.companyCertificateVos.push(tempZzjgObj) ;
                    }else {
                        var tempObj = {
                            companyId : this.company.id,
                            certKind : 4,
                            expdtBeginDate : this.zzjgBeginDate,
                            expdtEndDate : this.zzjgEndDate,
                            isValiding : this.zzjgChecked ? 1 : 0,
                            certCode : this.zzjgCertCode,
                            id : this.zzjgId
                        };
                        this.company.companyCertificateVos.push(tempObj);
                    }
                }
                this.editCertDateLoading = true;
                let serviceUrl = "/spdHERPService/myInfo/companyInfo/updateCompanyCertificate";
                this.axios.post(serviceUrl, this.company).then(res => {
                    this.editCertDateLoading = false;
                    if (res.data.code == "0") {
                        this.$message({message: '操作成功！', type: 'success'});
                        this.editFormVisible = false;
                        this.getCompanyInfo();
                        this.editDisable = !this.editDisable;
                    } else {
                        this.$message.error('操作失败！');
                    }
                }, err => {
                    this.editCertDateLoading = false;
                });
            },
            handleAvatarSuccess(res, file) {
                var id = file.response.data[0].id;
                var ext = file.response.data[0].ext;
                var path = file.response.data[0].path;
                var logoPath = path + "/" + id + "." + ext;
                this.logoUrl = this.imgBasePath + logoPath;
                this.savePathInfo(file,logoPath);
            },
            savePathInfo(file,logoPath){
                this.company.logo = logoPath;
                var param = this.company;
                this.loading = true;
                this.axios.post('/spdHERPService/myInfo/companyInfo/updateLogo', param).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        this.$message({message: '操作成功！', type: 'success'});
                        this.logoUrl = this.imgBasePath + logoPath;
                        this.getCompanyInfo();
                    } else {
                        this.$message.error(res.data.msg);
                    }
                }, err => {
                    this.loading = false;
                });
            },
            beforeAvatarUpload(file) {
//                if (!isJPG) {
//                    this.$message.error('上传头像图片只能是 JPG 格式!');
//                }
                // 上传LOGO图片格式暂时不要求
//                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 20;
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 20MB!');
                }
//                return isJPG && isLt2M;
                return isLt2M;
            },

            /**
             * 查看营业执照
             */
            checkYyzzCert() {
                this.yyzzImgDailogTitle = '营业执照 查看';
                this.yyzzImgDailog = true;
                this.dialogYyzzImageUrl = this.newyyzz;
            },
            /**
             * 查看经营许可证
             */
            checkJyxkCert() {
                this.yyzzImgDailogTitle = '经营许可证 查看';
                this.yyzzImgDailog = true;
                this.dialogYyzzImageUrl = this.newjyxk;
            },
            /**
             * 查看税务登记证
             */
            checkSwdjCert() {
                this.swdjImgDailogTitle = '税务登记证 查看';
                this.swdjImgDailog = true;
                this.dialogSwdjImageUrl = this.newswdj;
            },
             /**
             * 查看组织机构代码证
             */
            checkZzjgCert() {
                this.zzjgImgDailogTitle = '组织机构代码证 查看';
                this.zzjgImgDailog = true;
                this.dialogZzjgImageUrl = this.newzzjg
            },
            /**
             * 营业执照成功上传回调方法
             * @param response
             * @param file
             * @param fileList
             */
            yyzzSuccessUpload(response, file, fileList) {
                var id = file.response.data[0].id;
                var ext = file.response.data[0].ext;
                var path = file.response.data[0].path;
                var _filePath = path + "/" + id + "." + ext;
                this.newyyzz = this.imgBasePath + _filePath;
                this.certKind = '1';// 营业执照
                this.saveComanyCerts(file,_filePath);
            },
            /**
             * 经营许可证成功上传回调方法
             * @param response
             * @param file
             * @param fileList
             */
            jyxkSuccessUpload(response, file, fileList) {
                var id = file.response.data[0].id;
                var ext = file.response.data[0].ext;
                var path = file.response.data[0].path;
                var _filePath = path + "/" + id + "." + ext;
                this.newjyxk = this.imgBasePath + _filePath;
                this.certKind = '2';// 经营许可证
                this.saveComanyCerts(file,_filePath);
            },
            /**
             * 税务登记证成功上传回调方法
             * @param response
             * @param file
             * @param fileList
             */
            swdjSuccessUpload(response, file, fileList) {
                var id = file.response.data[0].id;
                var ext = file.response.data[0].ext;
                var path = file.response.data[0].path;
                var _filePath = path + "/" + id + "." + ext;
                this.newswdj = this.imgBasePath + _filePath;
                this.certKind = '3';// 税务登记证
                this.saveComanyCerts(file,_filePath);
            },

            /**
             * 组织机构代码证成功上传回调方法
             * @param response
             * @param file
             * @param fileList
             */
            zzjgSuccessUpload(response, file, fileList) {
                var id = file.response.data[0].id;
                var ext = file.response.data[0].ext;
                var path = file.response.data[0].path;
                var _filePath = path + "/" + id + "." + ext;
                this.newzzjg = this.imgBasePath + _filePath;
                this.certKind = '4';// 组织机构代码证
                this.saveComanyCerts(file,_filePath);
            },
            saveComanyCerts(file,filePath){
                var tempFileObject = {
                    certKind : this.certKind,
                    companyId: this.company.id,
                    basCompanyCertificateImages: [
                        {
                            filePath : filePath
                        }
                    ]
                };
                // var companyCertificateVo = this.company.companyCertificateVos.find(item => item.certKind == this.certKind);
                // if (!!companyCertificateVo) {
                //     this.company.companyCertificateVos.splice(0);
                // }
                this.company.companyCertificateVos = [];
                this.company.companyCertificateVos.push(tempFileObject);
                var param = this.company;
                this.loading = true;
                this.axios.post('/spdHERPService/myInfo/companyInfo/insertCompanyCertificate', param).then(res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                        let obj = res.data.data.companyCertificateVos.find(item => item.certKind == this.certKind);
                        if(this.certKind == "1"){
                            this.yyzzId = obj.id;
                        }else if(this.certKind == "2"){
                            this.jyxkId = obj.id;
                        }else if(this.certKind == "3"){
                            this.swdjId = obj.id;
                        }else if(this.certKind == "4"){
                            this.zzjgId = obj.id;
                        }
                        this.$message.success("上传成功！");
                    } else {
                        this.$message.error(res.data.msg);
                    }
                }, err => {
                    this.loading = false;
                });
            },
            handlePictureCardPreview3(file) {
                this.dialogImageUrl3 = file.url;
                this.dialogVisible3 = true;
            },
            /**
             * 营业执照是否长期有效事件
             */
            handleCheckedYYZZChange(event){
                this.yyzzEndDate = event.target.checked ? '' : this.yyzzEndDate;
            },
            /**
             * 经营许可证是否长期有效事件
             */
            handleCheckedJYXKChange(event){
                this.jyxkEndDate = event.target.checked ? '' : this.jyxkEndDate;
            },
            /**
             * 税务登记证是否长期有效事件
             */
            handleCheckedSWDJChange(event){
                this.swdjEndDate = event.target.checked ? '' : this.swdjEndDate;
            },
            /**
             * 组织机构代码证是否长期有效事件
             */
            handleCheckedZZJGChange(event){
                this.zzjgEndDate = event.target.checked ? '' : this.zzjgEndDate;
            },
            checkYYZZDate(d){
                if(this.yyzzChecked){
                    this.yyzzEndDate = '';
                }
            },
            checkJYXKDate(d){
                if(this.jyxkChecked){
                    this.jyxkEndDate = '';
                }
            },
            checkSWDJDate(d){
                if(this.swdjChecked){
                    this.swdjEndDate = '';
                }
            },
            checkZZJGDate(d){
                if(this.zzjgChecked){
                    this.zzjgEndDate = '';
                }
            }
        },
        mounted() {
            this.getCompanyInfo();
            this.uploadLogoUrl = fileServer.upload + "logo";
            this.uploadCompanyCertsUrl = fileServer.upload + "companyCert";
            this.imgBasePath = fileServer.imgBasePath;
        }
    };

</script>
<style>
    #companyInfo .button {
        padding: 0;
        float: right;
    }
    #test .el-upload--picture-card{
        border:none;
        background-color: #fbfdff;
        border-radius: 6px;
        box-sizing: border-box;
        width: 93%;
        height: 130px;
        cursor: pointer;
        line-height: 146px;
        vertical-align: top;
        margin-left: 3%;
    }
    #companyInfo .myInfocompanyOutput .el-form-item {
        margin-bottom: 6px;
    }
    #companyInfo .certStyle{
        margin-left: 3%;
        width: 93%;
        background-color: #ffffff;
        height: 26px;
        margin-bottom: 2%;
    }
    #companyInfo .certWordsStyle{
        font-size: 12px;
        line-height: 9px;
        font-weight: bold;
        margin-left: 6%;
    }
    #companyInfo .certButtonStyle{
        line-height: 28px;
        margin-right: 3%;
    }
    #companyInfo .certCodes{
        margin-left: 3%;
        margin-right: 4%;
    }
    #companyInfo .myInfoCompanyCerts{
        margin-bottom: 10%;
        background-color: rgb(238, 241, 246);
        border: 1px solid #d1dbe5;
        border-radius: 4px;
        overflow: hidden;
        box-shadow: 0 2px 4px 0 rgba(0,0,0,.12), 0 0 6px 0 rgba(0,0,0,.04);
    }
</style>

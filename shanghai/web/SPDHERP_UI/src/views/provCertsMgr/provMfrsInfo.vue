<template>
    <div id="provMfrsInfo" style="height:100%">
        <el-row style="margin-bottom:7px">
            <div class="inline">
                <el-input placeholder="请输入厂家名称" v-model="searchText"  @keyup.enter.native="searchBtnClick"
                          style="margin-top:2px;">
                    <el-select v-model="searchType" slot="prepend" placeholder="全部" style="width:70px;">
                        <el-option label="全部" value="0"></el-option>
                        <el-option :label="cjlxDicts[1]" value="1"></el-option>
                        <el-option :label="cjlxDicts[2]" value="2"></el-option>
                    </el-select>
                    <el-button slot="append" icon="search" @click="searchBtnClick"></el-button>
                </el-input>
            </div>
            <div class="inline right">
                <el-button  type="primary" @click="addPrepare">
                    新增证件信息
                </el-button>
            </div>
        </el-row>
        <el-table highlight-current-row class="provMfrsInfo-tableheight" ref="mfrsTable" :data="mfrsList"
                  style="width: 100%" v-loading="mLoading" border height="outer">
            <el-table-column prop="mfrsInfo.mfrsName" label="厂家名称" show-overflow-tooltip sortable header-align="center">
            </el-table-column>
            <el-table-column prop="mfrsInfo.mfrsKind" label="生产厂家类型" :formatter="formatMfrsKind" show-overflow-tooltip
                             sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="mfrsInfo.remark" label="备注" show-overflow-tooltip sortable header-align="center"
                             align="center">
            </el-table-column>
            <!--<el-table-column prop="mfrsInfo.isThreeInOne" :formatter="formatOne" label="三证合一" sortable-->
            <!--header-align="center"-->
            <!--align="center">-->
            <!--</el-table-column>-->
            <el-table-column fixed="right" label="操作" width="160" header-align="center" align="center">
                <template slot-scope="scope">
                    <el-switch v-model="scope.row.flag" active-text="启用" inactive-text="停用" active-value="1" inactive-value="0"
                               @change="changeFlag(scope.row)">
                    </el-switch>
                    <el-button size="mini" icon="edit" type="primary" @click="handleEdit(scope.row)">编辑
                    </el-button>
                    <!--<el-button-group>-->
                    <!--<el-button size="mini" icon="delete" type="danger"-->
                    <!--@click.native.prevent="handleDelete(scope.row)">-->
                    <!--删除-->
                    <!--</el-button>-->
                    <!--</el-button-group>-->
                </template>
            </el-table-column>
        </el-table>
        <el-pagination v-if="mTotal > 0" style="float:right" small @size-change="handlemSizeChange"
                       @current-change="handlemCurrentChange" :current-page.sync="mPageNum"
                       layout="total, sizes, prev, pager, next" :page-sizes="[10, 15, 20, 30]" :page-size="mPageSize"
                       :total="mTotal">
        </el-pagination>

        <el-dialog title="厂家信息" :visible.sync="dlgVisible" size="large">
            <div style="margin-bottom:7px;" class="text-align-center">
                <el-autocomplete :trigger-on-focus="false" v-model="dlgMfrsName" v-if="dlgAddMode" style="width:50%;"
                                 :fetch-suggestions="querySearchMfrsAsync"  placeholder="搜索生产厂家"
                                 @select="handleDlgMfrsSelect" :props="{'value':'mfrsName','label':'mfrsName'}">
                </el-autocomplete>
            </div>
            <el-form :model="currentMfrs" ref="mfrsForm" label-width="100px" class="el-form-item-nomsg">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="厂家名称" prop="mfrsName">
                            <el-input v-model="currentMfrs.mfrsInfo.mfrsName" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="厂家类型" prop="mfrsKind">
                            <el-radio-group v-model="currentMfrs.mfrsInfo.mfrsKind">
                                <el-radio v-for="(value, key, index) in cjlxDicts" :key="key" :label="key"
                                          :value="value" disabled>{{value}}
                                </el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <div v-if="dlgAddMode">
                        <el-col :span="12">
                            <el-form-item label="营业执照注册号" prop="regCode">
                                <el-input v-model="currentMfrs.mfrsInfo.regCode" disabled ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="经营许可证编号" prop="jyxkCode">
                                <el-input v-model="currentMfrs.mfrsInfo.jyxkCode" disabled ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="纳税人识别号" prop="nsrsbCode">
                                <el-input v-model="currentMfrs.mfrsInfo.nsrsbCode" disabled ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="生产许可证" prop="productCode">
                                <el-input v-model="currentMfrs.mfrsInfo.productCode" disabled ></el-input>
                            </el-form-item>
                        </el-col>
                    </div>
                </el-row>
                <div class="text-align-center" style="margin-bottom:5px;">
                    <el-button size="mini" type="primary" @click="addCert" :disabled="currentMfrs.mfrsInfo.id === ''">
                        增加证件信息
                    </el-button>
                </div>
                <el-row v-for="(item, index) in currentMfrs.certList" :key="index" class="cert-info-row">
                    <el-form-item :label="zzlxDicts[item.certKind]">
                        <el-col :span="12">
                            <div>
                                <span class="inline">证照类型</span>
                                <el-form-item :prop="'certList.' + index + '.certKind'" class="inline"
                                              :rules="{required: true, trigger: 'change',
                                                    validator: function (rule, value, callback) {
                                                        if(!value) {
                                                            return callback(new Error('请选择证件类型'));
                                                        } else {
                                                            if(currentMfrs.certList.length>3){
                                                                return callback(new Error('证件数不能超过3个！'));
                                                            }

                                                            const duplicate = currentMfrs.certList.filter(function(o){
                                                                return o.certKind === value;
                                                            });
                                                            if(duplicate.length>1){
                                                                return callback(new Error('已存在证件类型！'));
                                                            }

                                                            const combineIndex = currentMfrs.certList.findIndex(function(o){
                                                                return o.certKind === '4';
                                                            })
                                                            if(combineIndex!==-1 && currentMfrs.certList.length>1){
                                                                return callback(new Error('已选择三证合一，无法多选其它证件类型。'));
                                                            }
                                                            callback();
                                                        }
                                                    }}">
                                    <el-select class="form-content input-width"  placeholder="请选择证件类型"
                                               v-model="item.certKind">
                                        <el-option v-for="(value, key, index) in zzlxDicts" :key="key" :label="value"
                                                   :value="key"></el-option>
                                    </el-select>
                                </el-form-item>
                            </div>
                            <div>
                                <span class="inline">证照编号</span>
                                <el-form-item :prop="'certList.' + index + '.certCode'" class="inline"
                                              :rules="{required: true, message: '证照编号不能为空', trigger: 'change'}">
                                    <el-input class="form-content input-width" 
                                              v-model="item.certCode"></el-input>
                                </el-form-item>
                            </div>
                            <div>
                                <span class="inline">有效日期</span>
                                <div class="form-content">
                                    <el-form-item :prop="'certList.' + index+'.expdtBeginDate'" :rules="{required: true, trigger: 'change',
                                                                  validator: function (rule, value, callback) {
                                                                        if (!value) {
                                                                            return callback(Error('开始日期不能为空'))
                                                                        } else {
                                                                            if (item.expdtEndDate) {
                                                                                validate('mfrsForm', 'certList.' + index + '.expdtEndDate');
                                                                            }
                                                                            callback();
                                                                        }
                                                                  }}" class="inline">
                                        <el-date-picker style="width:120px;" size="mini" type="date"
                                                        v-model="item.expdtBeginDate"
                                                        placeholder="开始日期"></el-date-picker>
                                    </el-form-item>
                                    <span class="inline">-</span>
                                    <el-form-item :prop="'certList.' + index + '.expdtEndDate'" :rules="{required: true, trigger: 'change',
                                                                  validator: function (rule, value, callback) {
                                                                        if(!(item.isValiding === '1')) {
                                                                            if (!value) {
                                                                                return callback(new Error('结束日期不能为空'))
                                                                            } else {
                                                                                if (Date.parse(item.expdtBeginDate) > Date.parse(item.expdtEndDate)) {
                                                                                    return callback(new Error('结束日期必须大于开始日期'))
                                                                                }
                                                                                callback();
                                                                            }
                                                                        } else {
                                                                            callback();
                                                                        }
                                                                  }}" class="inline">
                                        <el-date-picker style="width:120px;" size="mini" type="date"
                                                        v-model="item.expdtEndDate" :disabled="item.isValiding === '1'"
                                                        placeholder="结束日期"></el-date-picker>
                                    </el-form-item>
                                    <el-checkbox style="margin-left:10px;" true-label="1" false-label="0"
                                                 v-model="item.isValiding" class="inline"
                                                 @change="validateEndDate(index, $event)">
                                        <span>长期有效</span>
                                    </el-checkbox>
                                </div>
                            </div>
                            <div class="text-align-center">
                                <form class="upload-form" @submit.prevent>
                                    <input @change="processFile(index, $event)" type="file" accept=".bmp,.jpg,.png,.pdf"
                                           multiple/>
                                    <!--:ref="'file_'+index"-->
                                    <span v-if="!item.uploadPercentCompleted || item.uploadPercentCompleted === 0"
                                          style="font-size:12px;color:grey;">
                                        <br/>注：单文件大小不能超过15M，上传后占击保存生效。</span>
                                    <div>
                                        <el-progress
                                                v-if="item.uploadPercentCompleted && item.uploadPercentCompleted !== 0"
                                                :text-inside="true" :stroke-width="18"
                                                :percentage="item.uploadPercentCompleted" class="process-bar"
                                                :status="item.uploadPercentCompleted === 100 ? 'success':''">
                                        </el-progress>
                                    </div>
                                </form>
                            </div>
                        </el-col>
                        <el-col :span="12" class="pic-card">
                            <div v-if="item.imageList.length === 0" style="text-align:center;">
                                <span>暂无相关证照照片</span>
                            </div>
                            <el-card :body-style="{ 'padding': '1px', 'max-height':'120px', 'max-width':'120px' }"
                                     v-for="(img, imageIndex) in item.imageList" :key="imageIndex" class="card">
                                <el-tooltip placement="left" effect="light" :open-delay="300">
                                    <div slot="content">
                                        <img class="tooltip-pic" :ref="'img_'+index+'_'+imageIndex">
                                    </div>
                                    <img :src="picURLPrefix+img.thumbFilePath" class="image"
                                         @mouseenter="imageMouseEnter(index, imageIndex)">
                                </el-tooltip>
                                <div class="bottom clearfix">
                                    <el-button type="text" class="button" size="mini"
                                               @click="handlePicDel(index, imageIndex)">删除
                                    </el-button>
                                </div>
                            </el-card>
                        </el-col>
                    </el-form-item>
                    <div class="cert-info-del-btn">
                        <el-button size="mini" type="danger" @click="handleCertDel(index)">
                            删除{{zzlxDicts[item.certKind]}}
                        </el-button>
                    </div>
                </el-row>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dlgVisible = false" >取消</el-button>
                <el-button type="primary" @click="dlgConfirm" >保存</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex';
    import debounce from 'throttle-debounce/debounce';
    import ElCheckbox from "../../../node_modules/element-ui/packages/checkbox/src/checkbox";

    export default {
        components: {ElCheckbox},
        data() {
            return {
                baseURL: '/spdHDIService/provMfrsInfo',
                uploadURL: '/platformService/sys/upload/file/certs',
                picURLPrefix: '/upload/',
                searchType: '0',
                searchText: '',
                cjlxDicts: {},
                zzlxDicts: {},
                mfrsList: [],
                origCurrentMfrsCertList: [],
                mLoading: false,
                mPageNum: 1,
                mPageSize: 30,
                mTotal: 0,
                dlgVisible: false,
                dlgAddMode: false,
                currentMfrs: {},
                dlgMfrsName: '',
                allowedFileTypes: ['image/bmp', 'image/jpeg', 'image/png', 'application/pdf'],
                //                uploadFileList: [],
                //                uploadPercentCompleted: 0,
            }
        },
        computed: {
            userInfo() {
                return {
                    'X-US': this.getLoginUser.xus
                }
            },
            ...mapGetters(["getLoginUser"])
        },
        methods: {
            formatMfrsKind(row, colum) {
                if (row.mfrsInfo.mfrsKind < 1) {  //  || row.mfrsKind > this.dicts.mfrsKind
                    return 'Error'
                } else {
                    return this.cjlxDicts[row.mfrsInfo.mfrsKind]
                }
            },
            formatOne(row, colum) {
                if (!row.mfrsInfo.isThreeInOne || row.mfrsInfo.isThreeInOne === '' || row.mfrsInfo.isThreeInOne === '0') {
                    return '否'
                } else {
                    return '是'
                }
            },
            addPrepare() {
                this.dlgAddMode = true;
                this.currentMfrs = this.newMfrsInfo();
                this.dlgMfrsName = '';
                this.dlgVisible = true;
            },
            addCert() {
                if (this.currentMfrs.certList.length >= 3) {
                    this.$message.error("证照数最多为3个，无法再添加证照。");
                } else {
                    this.currentMfrs.certList.push(this.newCert(''));
                }
            },
            getMfrsInfo() {
                this.mLoading = true;
                this.axios.get(this.baseURL + '/getMfrsInfo', {
                    params: {
                        'page': this.mPageNum,
                        'rows': this.mPageSize,
                        'searchType': this.searchType,
                        'searchText': this.searchText,
                    }
                }).then(res => {
                    this.mfrsList = res.data.data.data;
                    // this.mPageNum = res.data.data.pageNum;
                    this.mPageSize = res.data.data.pageSize;
                    this.mTotal = res.data.data.total;
                    this.mLoading = false
                }).catch(error => {
                    this.$message.error('数据异常，请联系管理员。')
                })
            },
            searchBtnClick() {
                this.getMfrsInfo()
            },
            changeFlag(item) {
                this.axios.patch(this.baseURL + '/changeFlag', {
                    'id': item.id,
                    'version': item.version,
                    'flag': (item.flag === '1' ? 0 : 1)
                }).then(res => {
                    if (res.data.code >= 0) {
                        item.flag = res.data.data.flag;
                        item.version = res.data.data.version;
                        this.$message.success('修改成功。')
                    } else {
                        this.$message.error('修改失败。' + res.data.msg.substring(0, 50) + '...')
                    }
                }).catch(_ => {
                    this.$message.info('修改失败，请刷新后重试，如无法解决请联系管理员。')
                })
            },
            handleEdit(item) {
                this.currentMfrs = JSON.parse(JSON.stringify(item));
                this.dlgAddMode = false;
                this.axios.get(this.baseURL + '/getCertList', {
                    params: {
                        id: item.id
                    }
                }).then(res => {
                    if (res.data.code >= 0) {
                        this.currentMfrs.certList = res.data.data;
                        this.origCurrentMfrsCertList = JSON.parse(JSON.stringify(res.data.data));
                        for (let cert of this.currentMfrs.certList) {
                            for (let image of cert.imageList) {
                                let slashIndex = image.filePath.lastIndexOf("/");
                                if (slashIndex !== -1) {
                                    image.thumbFilePath = image.filePath.substring(0, slashIndex) + '/thumb-' + image.filePath.substring(slashIndex + 1);
                                } else {
                                    image.thumbFilePath = '';
                                }
                            }
                        }
                    } else {
                        this.$message.error('获取数据失败。' + res.data.msg.substring(0, 50) + '...')
                    }
                }).catch(err => {
                    this.$message.info('获取数据失败，请刷新后重试，如无法解决请联系管理员。')
                });
                this.dlgVisible = true;
            },
            imageMouseEnter(index, imageIndex) {
                const ref = this.$refs['img_' + index + '_' + imageIndex];
                let len = ref.length;
                if (len > 1) {
                    ref.splice(0, len - 1);
                }
                ref[0].src = this.picURLPrefix + this.currentMfrs.certList[index].imageList[imageIndex].filePath;
            },
            handlePicDel(index, imageIndex) {
                if (this.dlgAddMode) {
                    delete this.currentMfrs.certList[index].imageList.splice(imageIndex, 1);
                } else {
                    this.axios.delete(this.baseURL + '/delPic', {
                        params: {
                            id: this.currentMfrs.certList[index].imageList[imageIndex].id
                        }
                    }).then(res => {
                        if (res.data.code >= 0) {
                            this.$message.success("照片删除成功。");
                            delete this.currentMfrs.certList[index].imageList.splice(imageIndex, 1);
                        } else {
                            this.$message({
                                type: 'error',
                                showClose: 'true',
                                duration: 0,
                                message: res.data.msg.substring(0, 50) + '...'
                            })
                        }
                    }).catch(err => {
                        this.$message.error('删除照片失败，请联系管理员。')
                    })
                }
            },
            handleCertDel(index) {
                if (!this.currentMfrs.certList[index].id) {
                    this.currentMfrs.certList.splice(index, 1)
                } else {
                    this.axios.delete(this.baseURL + '/delCert', {
                        params: {
                            id: this.currentMfrs.certList[index].id,
                            version: this.currentMfrs.certList[index].version
                        }
                    }).then(res => {
                        if (res.data.code >= 0) {
                            this.currentMfrs.certList.splice(index, 1);
                        } else {
                            this.$message({
                                type: 'error',
                                showClose: 'true',
                                duration: 0,
                                message: res.data.msg.substring(0, 50) + '...'
                            })
                        }
                    }).catch(err => {
                        this.$message.error('数据异常，请联系管理员。')
                    })
                }
            },
            handlemSizeChange(val) {
                this.mPageNum = val;
                this.getMfrsInfo()
            },
            handlemCurrentChange(val) {
                this.mPageSize = val;
                this.getMfrsInfo()
            },
            dlgConfirm() {
                if (this.currentMfrs.certList.length === 0) {
                    this.$message.error('未增加有效证件，无法保存。');
                    return
                }
                this.$refs['mfrsForm'].validate((valid) => {
                    if (valid) {
                        if (this.dlgAddMode) {
                            this.axios.post(this.baseURL + '/addMfrsInfo', this.handleMfrsData(this.currentMfrs)).then(res => {
                                if (res.data.code >= 0) {
                                    this.$message.success("厂商证照信息增加成功。");
                                    this.currentMfrs.id = res.data.data;
                                    this.mfrsList.push(this.currentMfrs);
                                    this.dlgVisible = false
                                } else {
                                    this.$message({
                                        type: 'error',
                                        showClose: 'true',
                                        duration: 0,
                                        message: res.data.msg.substring(0, 50) + '...'
                                    })
                                }
                            }).catch(err => {
                                this.$message.error('数据异常，请联系管理员。')
                            })
                        } else {
                            this.axios.patch(this.baseURL + '/modMfrsInfo', this.handleMfrsData(this.currentMfrs)).then(res => {
                                if (res.data.code >= 0) {
                                    this.$message.success("厂商证照更新成功。");
                                    this.dlgVisible = false
                                } else {
                                    this.$message({
                                        type: 'error',
                                        showClose: 'true',
                                        duration: 0,
                                        message: res.data.msg.substring(0, 50) + '...'
                                    })
                                }
                            }).catch(err => {
                                this.$message.error('数据异常，请联系管理员。')
                            })
                        }
                    } else {
                        this.$message.error('数据验证失败，请更正！');
                    }
                });

            },
            handleMfrsData(data) {
                let mfrsInfo = JSON.parse(JSON.stringify(data));
                mfrsInfo.mfrsId = mfrsInfo.mfrsInfo.id;
                delete mfrsInfo.mfrsInfo;
                let removeCertId = [];
                for (let cert of mfrsInfo.certList) {
                    cert.expdtBeginDate = Date.parse(cert.expdtBeginDate);
                    cert.expdtEndDate = Date.parse(cert.expdtEndDate);
                    if (!this.dlgAddMode) {
                        if (cert.added === true) {
                            delete cert.added;
                        } else {
                            let origCert = JSON.parse(JSON.stringify(this.origCurrentMfrsCertList.find(o => o.id === cert.id)));
                            origCert.expdtBeginDate = Date.parse(origCert.expdtBeginDate);
                            origCert.expdtEndDate = Date.parse(origCert.expdtEndDate);
                            delete cert.imageList;
                            delete origCert.imageList;
                            if (JSON.stringify(cert) === JSON.stringify(origCert)) {
                                removeCertId.push(cert.id);
                                continue;
                            }
                        }
                    }
                    //                    if (cert.uploadPercentCompleted)
                    delete cert.uploadPercentCompleted;
                    if (cert.imageList)
                        for (let image of cert.imageList) {
                            delete image.thumbFilePath;
                        }
                }
                for (let id of removeCertId) {
                    let index = mfrsInfo.certList.findIndex(o => o.id === id);
                    mfrsInfo.certList.splice(index, 1);
                }
                return mfrsInfo;
            },
            querySearchMfrsAsync: debounce(500, function (queryString, callback) {
                this.currentMfrs.mfrsId = '';
                this.axios.get(this.baseURL + '/getBasMfrs', {
                    params: {
                        'mfrsName': queryString
                    }
                }).then(res => {
                    callback(res.data.data)
                }, err => {
                    this.$message.error('数据异常，请联系管理员。')
                })
            }),
            handleDlgMfrsSelect(item) {
                if (this.currentMfrs.mfrsInfo.id !== item.id) {
                    this.currentMfrs.mfrsInfo = item;
                    if (this.currentMfrs.certList.length === 0) {
//                        this.currentMfrs.certList.push(this.newCert('1'), this.newCert('2'), this.newCert('3'))
                        this.currentMfrs.certList.push(this.newCert('4'))
                    }
                    this.$refs['mfrsForm'].resetFields();
                }
            },
            processFile(index, event) {
                const files = event.target.files;
                for (var i = 0; i < files.length; i++) {
                    if (this.allowedFileTypes.indexOf(files[i].type) === -1) {
                        this.$message.error('文件格式不符合要求，请按要求上传' + this.allowedFileTypes.join(',') + '文件！');
                        return false;
                    }
                    if (files[i].size / 1024 / 1024 > 15) {
                        this.$message.error("上传图片大小不能超过15M！");
                        return false;
                    }
                }
                ;
                // for (let file of files) {
                //     if (this.allowedFileTypes.indexOf(file.type) === -1) {
                //         this.$message.error('文件格式不符合要求，请按要求上传' + this.allowedFileTypes.join(',') + '文件！');
                //         return false;
                //     }
                //     if (file.size / 1024 / 1024 > 15) {
                //         this.$message.error("上传图片大小不能超过15M！");
                //         return false;
                //     }
                // };
                if (files.length > 0)
                    this.uploadFile(files, index, event.target);
            },
            uploadFile(files, index, target) {
                let data = new FormData();
                for (var i = 0; i < files.length; i++) {
                    data.append('file', files[i]);
                }
                // for (let item of files) {
                //     data.append('file', item);
                // }
                this.currentMfrs.certList[index].uploadPercentCompleted = 0;
                const that = this;
                let config = {
                    onUploadProgress: function (progressEvent) {
                        that.currentMfrs.certList[index].uploadPercentCompleted = Math.round((progressEvent.loaded * 100) / progressEvent.total);
                    }
                };
                this.axios.post(this.uploadURL, data, config)
                    .then(function (res) {
                        if (res.data.code >= 0) {
                            if (!that.currentMfrs.certList[index].id) {
                                for (let image of res.data.data) {
                                    let imageObject = {
                                        id: '',
                                        filePath: image.path + '/' + image.id + '.' + image.ext,
                                        thumbFilePath: image.path + '/' + image.thumbnailId + '.' + image.ext,
                                    };
                                    that.currentMfrs.certList[index].imageList.push(imageObject);
                                    if (!that.dlgAddMode && !that.currentMfrs.certList[index].added) {
                                        that.currentMfrs.certList[index].added = true;
                                    }
                                }
                            } else {
                                let cert = {
                                    id: '',
                                    imageList: []
                                };
                                cert.id = that.currentMfrs.certList[index].id;
                                for (let image of res.data.data) {
                                    cert.imageList.push({
                                        filePath: image.path + '/' + image.id + '.' + image.ext,
                                    })
                                }
                                that.axios.post(that.baseURL + '/addPic', cert)
                                    .then(response => {
                                        if (response.data.code >= 0) {
                                            for (let image of response.data.data) {
                                                let slashIndex = image.filePath.lastIndexOf("/");
                                                if (slashIndex !== -1) {
                                                    image.thumbFilePath = image.filePath.substring(0, slashIndex) + '/thumb-' + image.filePath.substring(slashIndex + 1);
                                                } else {
                                                    image.thumbFilePath = '';
                                                }
                                                that.currentMfrs.certList[index].imageList.push({
                                                    id: image.id,
                                                    filePath: image.filePath,
                                                    thumbFilePath: image.thumbFilePath,
                                                })
                                            }
                                        } else {
                                            that.$message.error('上传失败，' + res.data.msg.substring(0, 50) + '...');
                                        }
                                    })
                                    .catch(err => {
                                        that.$message.error('上传失败，请联系管理员。')
                                    })
                            }
                        } else {
                            that.$message.error('上传失败，' + res.data.msg.substring(0, 50) + '...');
                        }
                    })
                    .catch(function (err) {
                        that.$message.error('上传失败，请联系管理员。')
                    });
            },
            Error(msg) {
                return new Error(msg);
            },
            validate(form, prop) {
                return this.$refs[form].validateField(prop);
            },
            validateEndDate(index, event) {
                this.validate('mfrsForm', 'certList.' + index + '.expdtEndDate');
            },
            newMfrsInfo() {
                return {
                    id: '',
                    provId: this.getLoginUser.corpId,
                    mfrsInfo: {
                        id: '',
                        mfrsKind: '',
                        mfrsName: '',
                        agentName: '',
                        regCode: '',
                        jyxkCode: '',
                        nsrsbCode: '',
                        productCode: '',
                        remark: '',
                        isThreeInOne: ''
                    },
                    flag: '1',
                    version: 0,
                    certList: [],
                }
            },
            newCert(certKind) {
                return {
                    id: '',
                    certKind: certKind,
                    certCode: '',
                    expdtBeginDate: '',
                    expdtEndDate: '',
                    isValiding: '0',
                    version: 0,
                    uploadPercentCompleted: 0,
                    imageList: [
                        /*
                         {
                         id: '',
                         filePath: ''
                         }*/
                    ]
                }
            }
        },
        mounted() {
            this.mLoading = true;
            this.axios.get(this.baseURL + '/getMfrsKind').then(res => {
                for (let item of res.data.data) {
                    this.cjlxDicts[item.value] = item.name;
                }
                this.getMfrsInfo();
            }).catch(error => {
                this.$message.error('数据异常，请联系管理员。')
            })
            this.axios.get(this.baseURL + '/getCertKind').then(res => {
                for (let item of res.data.data) {
                    this.zzlxDicts[item.value] = item.name;
                }
            }).catch(error => {
                this.$message.error('数据异常，请联系管理员。')
            })
        },
        created() {
            this.currentMfrs = this.newMfrsInfo();
        }
    }
</script>

<style>
    #provMfrsInfo span {
        font-size: 12px;
    }

    .provMfrsInfo-tableheight {
        height: calc(100% - 75px);
        min-height: 400px;
    }

    /* #provMfrsInfo .form-label {
            padding: 8px 0 11px 0;
        } */

    #provMfrsInfo .form-content {
        /*position: relative;*/
        /*width: 80%;*/
        padding: 2px 0 3px 0;
        display: inline-block;
    }

    #provMfrsInfo .input-width {
        width: 337px;
    }

    #provMfrsInfo .el-dialog--large {
        width: 70%;
        min-width: 1000px;
    }

    #provMfrsInfo .el-upload__tip {
        margin: 0;
    }

    #provMfrsInfo .cert-info-row {
        margin: 10px 0;
    }

    #provMfrsInfo .cert-info-row .el-form-item__content {
        border: 1px solid grey;
        padding: 2px 5px;
    }

    #provMfrsInfo .cert-info-row .el-form-item__content .el-form-item__content {
        border: none;
        padding: 0;
    }

    #provMfrsInfo .cert-info-del-btn {
        float: right;
        margin: 5px 0;
    }

    #provMfrsInfo .text-align-center {
        text-align: center;
    }

    #provMfrsInfo .process-bar {
        margin-top: 5px;
        width: 100%;
    }

    #provMfrsInfo .pic-card {
        overflow: auto;
        white-space: nowrap;
        height: 150px;
    }

    #provMfrsInfo .bottom {
        margin: 5px;
        line-height: 12px;
    }

    #provMfrsInfo .button {
        padding: 0;
        float: right;
        color: red;
    }

    #provMfrsInfo .image {
        min-width: 50px;
        min-height: 50px;
        max-width: 100%;
        max-height: 100%;
        display: block;
    }

    img .tooltip-pic {
        max-width: 400px;
    }

    #provMfrsInfo .clearfix:before,
    #provMfrsInfo .clearfix:after {
        display: table;
        content: "";
    }

    #provMfrsInfo .clearfix:after {
        clear: both
    }

    #provMfrsInfo .pic-card .card {
        display: inline-block;
        margin: 0px 5px;
    }

    #provMfrsInfo .inline {
        display: inline-block;
    }

    #provMfrsInfo .right {
        float: right;
    }

    #provMfrsInfo .is-error {
        margin-bottom: 22px;
    }

    #provMfrsInfo .el-form-item__error {
        display: block;
    }
</style>
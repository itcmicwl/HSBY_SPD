<template>
    <div style="height: 100%;">
        <!--编辑界面-->
        <el-form :model="adverseEvents" label-width="120px" ref="editForm">
            <div id="adverseEventsDiv">
                <div class="reportSourceDiv">
                    <el-row class="titleDiv">
                        <span class="title">可疑医疗器械不良事件报告</span>
                    </el-row>
                    <el-row :gutter="5">
                        <el-col :span="12">
                            <el-form-item label="报告来源：" prop="reportSource">
                                <el-radio-group v-model="adverseEvents.reportSource"  >
                                    <el-radio-button label="1">使用单位</el-radio-button>
                                    <el-radio-button label="2">经营企业</el-radio-button>
                                    <el-radio-button label="3" disabled>生产企业</el-radio-button>
                                    <el-radio-button label="4" disabled>个人</el-radio-button>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="报告人：" prop="reporter">
                                <el-input placeholder="请输入报告人" v-model="adverseEvents.reporter" ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="报告日期：" prop="reprotTime">
                                <el-date-picker v-model="adverseEvents.reprotTime" type="date" 
                                        placeholder="选择日期"  style="width: 115px;">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="5">
                        <el-col :span="12">
                            <el-form-item label="单位名称：" prop="orgName">
                                <el-input placeholder="请输入单位名称" v-model="adverseEvents.orgName" ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="报告人类别：" prop="reporterType">
                                <el-radio-group v-model="adverseEvents.reporterType" >
                                    <el-radio :label="1">医师</el-radio>
                                    <el-radio :label="2">技师</el-radio>
                                    <el-radio :label="3">护士</el-radio>
                                    <el-radio :label="4">工程师</el-radio>
                                    <el-radio :label="5">其它</el-radio>
                                </el-radio-group>
                            </el-form-item>
                            <!--<el-form-item label="编码：">-->
                                <!--<span>系统自动生成</span>-->
                            <!--</el-form-item>-->
                        </el-col>
                    </el-row>
                    <el-row  :gutter="5">
                        <el-col :span="12">
                            <el-form-item label="联系地址：" prop="content">
                                <el-input placeholder="请输入联系地址" v-model="adverseEvents.content" ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="联系电话：" prop="reporterTel">
                                <el-input placeholder="请输入联系电话" v-model="adverseEvents.reporterTel" ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="邮编：" prop="postCode">
                                <el-input placeholder="请输入邮编" v-model="adverseEvents.postCode" ></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>
                <div>
                    <el-row :gutter="5">
                        <el-col :span="12">
                            <el-row>
                                <div class="provDiv">
                                    <div class="divTitle"><span class="uploadParam">&nbsp;A.患者资料</span></div>
                                    <div class="rowDiv">
                                        <el-row>
                                            <el-form-item label="姓名：" prop="sickName">
                                                <el-input v-model="adverseEvents.sickName" value="currentProv.id" ></el-input>
                                            </el-form-item>
                                        </el-row>
                                        <el-row>
                                            <el-col :span="12">
                                                <el-form-item label="出生日期：" prop="sickBirthday">
                                                    <el-date-picker v-model="adverseEvents.sickBirthday" type="date" onClick="test()" @change="calAge"  
                                                                    placeholder="选择日期"  style="width: 110px;">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>
                                            <el-col :span="12">
                                                <el-form-item label="年龄：" prop="sickAge">
                                                    <el-input v-model="adverseEvents.sickAge" ></el-input>
                                                </el-form-item>
                                            </el-col>
                                        </el-row>
                                        <el-row>
                                            <el-form-item label="性别：" prop="sickSex">
                                                <el-radio-group v-model="adverseEvents.sickSex"  >
                                                    <el-radio-button label="2">男</el-radio-button>
                                                    <el-radio-button label="1">女</el-radio-button>
                                                </el-radio-group>
                                            </el-form-item>
                                        </el-row>
                                        <el-row>
                                            <el-form-item label="电话：" prop="sickTel">
                                                <el-input v-model="adverseEvents.sickTel" ></el-input>
                                            </el-form-item>
                                        </el-row>
                                        <el-row>
                                            <el-form-item label="预期治疗疾病与作用：" labelWidth="205px">
                                            </el-form-item>
                                        </el-row>
                                        <el-row>
                                            <el-form-item label=""  prop="anticipationEffects">
                                                <el-input type="textarea" :rows="4" v-model="adverseEvents.anticipationEffects" ></el-input>
                                            </el-form-item>
                                        </el-row>
                                    </div>
                                </div>
                                <div class="provDiv">
                                    <div class="divTitle"><span class="uploadParam">&nbsp;B.不良事件情况</span></div>
                                    <div class="rowDiv">
                                        <el-row>
                                            <el-form-item label="事件主要表现：" labelWidth="143px">
                                            </el-form-item>
                                        </el-row>
                                        <el-row>
                                            <el-form-item label="a.器械故障：" prop="eventApparatusFault" labelWidth="143px">
                                                <el-input v-model="adverseEvents.eventApparatusFault" ></el-input>
                                            </el-form-item>
                                        </el-row>
                                        <el-row>
                                            <el-form-item label="b.主要伤害：" prop="eventMainHurt" labelWidth="143px">
                                                <el-input v-model="adverseEvents.eventMainHurt" ></el-input>
                                            </el-form-item>
                                        </el-row>
                                        <el-row>
                                            <el-col :span="12">
                                                <el-form-item label="事件发生日期：" labelWidth="143px" prop="eventOccurrenceTime">
                                                    <el-date-picker v-model="adverseEvents.eventOccurrenceTime" type="date"  
                                                                    placeholder="选择日期" style="width: 115px;">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>
                                            <el-col :span="12">
                                                <el-form-item label="发现或知悉日期：" labelWidth="143px" prop="eventInformedTime">
                                                    <el-date-picker v-model="adverseEvents.eventInformedTime" type="date"  
                                                                    placeholder="选择日期" style="width: 115px;">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>
                                        </el-row>
                                        <el-row>
                                            <el-form-item label="器械使用场所：" labelWidth="143px" prop="apparatusWhereUsed">
                                                <el-radio-group v-model="adverseEvents.apparatusWhereUsed" >
                                                    <el-radio-button :label="1">医疗机构</el-radio-button>
                                                    <el-radio-button :label="2">家庭</el-radio-button>
                                                    <el-radio-button :label="3">其它</el-radio-button>
                                                </el-radio-group>
                                            </el-form-item>
                                        </el-row>
                                        <el-row>
                                                <el-form-item label="事件后果 ：" labelWidth="143px">
                                                    <el-radio-group v-model="adverseEvents.eventResult" >
                                                        <el-radio :label="1">死亡</el-radio><br/>
                                                        <el-radio :label="2">危及生命</el-radio><br/>
                                                        <el-radio :label="3">机体功能结构永久性损伤</el-radio><br/>
                                                        <el-radio :label="4">可能导致机体功能结构永久性损伤</el-radio><br/>
                                                        <el-radio :label="5">需要内、外科治疗避免上述永久损伤</el-radio><br/>
                                                        <el-radio :label="6">其它</el-radio>
                                                    </el-radio-group>
                                                </el-form-item>
                                        </el-row>
                                        <el-row>
                                            <el-form-item label="事件陈述：" labelWidth="143px">
                                                <el-input type="textarea" :rows="8" v-model="adverseEvents.eventDetail" ></el-input>
                                            </el-form-item>
                                        </el-row>
                                        <el-row>
                                            <el-col :span="24">
                                                <el-form-item label="附件">
                                                    <el-upload
                                                            class="upload-demo"
                                                            ref="upload"
                                                            :action="uploadUrl"
                                                            :on-success="successUpload"
                                                            :before-upload="beforeAvatarUpload"
                                                            :headers="userInfo"
                                                            :auto-upload="true"
                                                            :file-list="aeReportingFiles">
                                                        <el-button  type="primary">点击上传</el-button>
                                                        <span style="color: lightgray;font-size: smaller;">注意图片大小不能超过20M</span>
                                                    </el-upload>
                                                </el-form-item>
                                            </el-col>
                                        </el-row>
                                    </div>
                                </div>
                            </el-row>
                        </el-col>
                        <el-col :span="12">
                            <div class="provDiv">
                                <div class="divTitle"><span class="uploadParam">&nbsp;C.医疗器械情况</span></div>
                                <div class="rowDiv">
                                    <el-row>
                                        <el-form-item label="注册证品名称：" prop="registerName">
                                            <el-input v-model="adverseEvents.registerName" ></el-input>
                                        </el-form-item>
                                        <el-form-item label="注册证号：" prop="registerCode">
                                            <el-input v-model="adverseEvents.registerCode" ></el-input>
                                        </el-form-item>
                                        <el-form-item label="产品名称：" prop="goodsName">
                                            <el-input v-model="adverseEvents.goodsName" ></el-input>
                                        </el-form-item>
                                        <el-form-item label="产品型号：" prop="goodsGg">
                                            <el-input v-model="adverseEvents.goodsGg" ></el-input>
                                        </el-form-item>
                                        <el-form-item label="产品编码：" prop="goddsCode">
                                            <el-input v-model="adverseEvents.goddsCode" ></el-input>
                                        </el-form-item>
                                        <el-form-item label="产品批号：" prop="goodsBatch">
                                            <el-input v-model="adverseEvents.goodsBatch" ></el-input>
                                        </el-form-item>
                                        <el-form-item label="操作人：" prop="operator">
                                            <el-radio-group v-model="adverseEvents.operator"  >
                                                <el-radio-button label="1">专业人员</el-radio-button>
                                                <el-radio-button label="2">非专业人员</el-radio-button>
                                                <el-radio-button label="3">患者</el-radio-button>
                                            </el-radio-group>
                                        </el-form-item>
                                        <el-row>
                                            <el-col :span="12">
                                                <el-form-item label="生产日期：" prop="productionDate">
                                                    <el-date-picker v-model="adverseEvents.productionDate" type="date"  
                                                                    placeholder="选择日期"  style="width: 115px;">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>
                                            <el-col :span="12">
                                                <el-form-item label="有效期至：" prop="endDate">
                                                    <el-date-picker v-model="adverseEvents.endDate" type="date"  
                                                                    placeholder="选择日期"  style="width: 115px;">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>
                                        </el-row>
                                        <el-row>
                                            <el-col :span="12">
                                                <el-form-item label="停用日期：" prop="disableDate">
                                                    <el-date-picker v-model="adverseEvents.disableDate" type="date"  
                                                                    placeholder="选择日期"  style="width: 115px;">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>
                                            <el-col :span="12">
                                                <el-form-item label="植入日期：" prop="implanDate">
                                                    <el-date-picker v-model="adverseEvents.implanDate" type="date"  
                                                                    placeholder="选择日期"  style="width: 115px;">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>
                                        </el-row>
                                        <el-form-item label="事件发生初步原因分析：" prop="eventReson">
                                            <el-input type="textarea" :rows="2" v-model="adverseEvents.eventReson" ></el-input>
                                        </el-form-item>
                                        <el-form-item label="事件初步处理情况：" prop="eventDealWith" style="margin-top: 2px;">
                                            <el-input type="textarea" :rows="2" v-model="adverseEvents.eventDealWith" ></el-input>
                                        </el-form-item>
                                        <el-form-item label="事件初步处理情况：" prop="eventReportStatus" style="margin-top: 2px;">
                                            <el-radio-group v-model="adverseEvents.eventReportStatus" >
                                                <el-row>
                                                    <el-col :span="12">
                                                        <el-radio :label="1">已通知使用单位</el-radio>
                                                    </el-col>
                                                    <el-col :span="12">
                                                        <el-radio :label="2">已通知生产企业</el-radio>
                                                    </el-col>
                                                </el-row>
                                                <el-row>
                                                    <el-col :span="12">
                                                        <el-radio :label="3">已通知经营企业</el-radio>
                                                    </el-col>
                                                    <el-col :span="12">
                                                        <el-radio :label="4">已通知药监部门</el-radio>
                                                    </el-col>
                                                </el-row>
                                            </el-radio-group>
                                        </el-form-item>
                                    </el-row>
                                </div>
                            </div>
                            <div class="provDiv">
                                <div class="divTitle"><span class="uploadParam">&nbsp;D.关联性评价</span></div>
                                <div class="rowDiv">
                                    <el-row>
                                        <span>&nbsp;1.使用医疗器械与已发生/可能发生的伤害事件之间是否具有合理的先后时间顺序？</span>
                                    </el-row>
                                    <el-row style="margin-bottom: 3px;">
                                        <el-form-item label="" labelWidth="12px" prop="associateQuestion1">
                                            <el-radio-group v-model="adverseEvents.associateQuestion1"  >
                                                <el-radio label="1">是</el-radio>
                                                <el-radio label="0">否</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                    </el-row>
                                    <el-row>
                                        <span>&nbsp;2.发生/可能发生的伤害事件是否属于所使用医疗器械可能导致的伤害类型？</span>
                                    </el-row>
                                    <el-row style="margin-bottom: 3px;">
                                        <el-form-item label="" labelWidth="12px" prop="associateQuestion2">
                                            <el-radio-group v-model="adverseEvents.associateQuestion2"  >
                                                <el-radio label="1">是</el-radio>
                                                <el-radio label="0">否</el-radio>
                                                <el-radio label="2">无法确定</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                    </el-row>
                                    <el-row>
                                        <span>&nbsp;3.已发生/可能发生的伤害事件是否可以用合并用药和/或械的作用、患者病情或其它非医疗器械因素来解释？</span>
                                    </el-row>
                                    <el-row style="margin-bottom: 3px;">
                                        <el-form-item label="" labelWidth="12px" prop="associateQuestion3">
                                            <el-radio-group v-model="adverseEvents.associateQuestion3"  >
                                                <el-radio label="1">是</el-radio>
                                                <el-radio label="0">否</el-radio>
                                                <el-radio label="2">无法确定</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                    </el-row>
                                    <el-row>
                                        <span>&nbsp;关联性评价结果</span>
                                    </el-row>
                                    <el-row>
                                        <el-form-item label="" labelWidth="12px" prop="associateResult">
                                            <el-radio-group v-model="adverseEvents.associateResult" >
                                                <el-radio label="1">很有可能</el-radio>
                                                <el-radio label="2">可能有关</el-radio>
                                                <el-radio label="3">可能无关</el-radio>
                                                <el-radio label="4">无法确定</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                    </el-row>
                                </div>
                            </div>
                            <div style="margin-top: 20px;text-align: center">
                                <el-button type="primary" v-on:click="editSubmit()"  :loading="editLoading" >提交</el-button>
                            </div>
                        </el-col>
                    </el-row>

                </div>

            </div>
            <!--<el-row>-->
                <!--<el-col :span="12">-->
                    <!--<el-form-item label="分类">-->
                        <!--<el-select v-model="" filterable placeholder="请选择" :clearable="true" >-->
                            <!--<el-option-->
                                    <!--v-for="item in blsjfl"-->
                                    <!--:key="item.val"-->
                                    <!--:label="item.ename"-->
                                    <!--:value="item.val">-->
                            <!--</el-option>-->
                        <!--</el-select>-->
                    <!--</el-form-item>-->
                <!--</el-col>-->
                <!--<el-col :span="12">-->
                    <!--<el-form-item label="级别">-->
                        <!--<el-select v-model="" filterable placeholder="请选择" :clearable="true" >-->
                            <!--<el-option-->
                                    <!--v-for="item in blsjjb"-->
                                    <!--:key="item.val"-->
                                    <!--:label="item.ename"-->
                                    <!--:value="item.val">-->
                            <!--</el-option>-->
                        <!--</el-select>-->
                    <!--</el-form-item>-->
                <!--</el-col>-->
            <!--</el-row>-->

            <!--<el-row>-->
                    <!--<el-form-item label="语音">-->
                    <!--</el-form-item>-->
            <!--</el-row>-->
        </el-form>
    </div>
</template>
<script>
    import {fileServer} from '../../common/js/constance';
    import {mapGetters} from 'vuex';
    import moment from 'moment';
    /*根据出生日期算出年龄*/
    function jsGetAge(strBirthday){
        if(strBirthday){
            var returnAge;
            var strBirthdayArr=strBirthday.split("-");
            var birthYear = strBirthdayArr[0];
            var birthMonth = strBirthdayArr[1];
            var birthDay = strBirthdayArr[2];

            var d = new Date();
            var nowYear = d.getFullYear();
            var nowMonth = d.getMonth() + 1;
            var nowDay = d.getDate();

            if(nowYear == birthYear){
                returnAge = 0;//同年 则为0岁
            }else{
                var ageDiff = nowYear - birthYear ; //年之差
                if(ageDiff > 0){
                    if(nowMonth == birthMonth) {
                        var dayDiff = nowDay - birthDay;//日之差
                        if(dayDiff < 0)
                        {
                            returnAge = ageDiff - 1;
                        }
                        else
                        {
                            returnAge = ageDiff ;
                        }
                    }
                    else
                    {
                        var monthDiff = nowMonth - birthMonth;//月之差
                        if(monthDiff < 0)
                        {
                            returnAge = ageDiff - 1;
                        }
                        else
                        {
                            returnAge = ageDiff ;
                        }
                    }
                }
                else
                {
                    returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
                }
            }
            return returnAge;//返回周岁年龄
        }
    }
    export default {
        data() {
            return {


                blsjfl:[],
                blsjjb:[],adverseEvents:{
                    reportSource:'',
                    sickSex:'2',
                    apparatusWhereUsed:'1',
                    eventResult:6,
                    operator:'1',
                    reporterType:1,
                    eventReportStatus:'',
                    associateQuestion1:'',
                    associateQuestion2:'',
                    associateQuestion3:'',
                    associateResult:'',
                    sickBirthday:'',
                    eventOccurrenceTime:'',
                    eventInformedTime:'',
                    productionDate:'',
                    endDate:'',
                    disableDate:'',
                    implanDate:'',
                    reprotTime:''

                },
                uploadUrl:'',
                imgBasePath:'',
                aeReportingFiles:[],
                userInfo:{},
                editLoading:false
            }
        },
        methods: {
            // 计算年龄
            calAge:function (d1,d2,d3) {
                this.adverseEvents.sickAge = jsGetAge(d1);
            },
            test:function (a1,a2,a3) {
            },
            // 提交不良事件
            editSubmit: function () {
                let o = this;
                var adverseEventsFiles = [];
                o.aeReportingFiles.forEach(item=>{
                    adverseEventsFiles.push({fileType:1,filePath:item.path});
                });
                o.adverseEvents.adverseEventsFiles = adverseEventsFiles;
                o.editLoading = true;
                o.axios.post("spdHERPService/adverseEvents/adverseEvents/add", o.adverseEvents).then(res => {
                    o.editLoading = false;
                    if (res.data.code == "0") {
                        o.adverseEvents = res.data.data;
//                        o.$refs.upload.submit();
                        this.$message({ message: '操作成功！', type: 'success' });
                    } else {
                        o.$message.error('操作失败！');
                    }
                }, err => {
                    o.editLoading = false;
                });
            },

            successUpload(response, file, fileList) {
                var id = file.response.data[0].id;
                var ext = file.response.data[0].ext;
                var name = file.response.data[0].name;
                var path = file.response.data[0].path;
                var _filePath = path + "/" + id + "." + ext;
                var fileObj = {
                    name: name + "." + ext,
                    url: this.imgBasePath + _filePath,
                    path:_filePath,
                    status: 'finished'
                };
                this.aeReportingFiles.push(fileObj);
            },
            beforeAvatarUpload(file) {
                const isLt2M = file.size / 1024 / 1024 < 20;
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 20MB!');
                }
                return isLt2M;
            },

            //字典获取：事件类型分类
            getBlsjfls: function () {
                this.axios.post('/platformService/sys/dict/getDictValueByDictEname',{dictName:'BLSJFL'}).then(res => {
                    if (res.data.code == "0") {
                        this.blsjfl = res.data.data;
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                });
            },
            //字典获取：事件类型级别
            getBlsjjbs: function () {
                this.axios.post('/platformService/sys/dict/getDictValueByDictEname',{dictName:'BLSJJB'}).then(res => {
                    if (res.data.code == "0") {
                        this.blsjjb = res.data.data;
                    }else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                });
            },
        },
        mounted() {
            this.adverseEvents.reprotTime = new Date();
            this.adverseEvents.reporter = this.user.ename;
            this.adverseEvents.reportSource = this.user.corpKind;
            this.adverseEvents.orgName = this.user.corpName;
            this.userInfo = {'X-US':this.user.xus};
            this.uploadUrl = fileServer.upload + "aeReporting";
            this.imgBasePath = fileServer.imgBasePath;
        }
    }

</script>
<style>
    #adverseEventsDiv .el-form-item{
        margin-bottom:0px;
    }
    .reportSourceDiv{
        border: 1px solid #dfe6ec;
    }
    .titleDiv{
        margin-bottom: 5px;
        border-bottom: 1px solid #dfe6ec;
        text-align: center;
        background-color: #eef1f6;
    }
    .title{
        font-size: medium;
        font-weight: bold;
        line-height: 35px;
    }

    .provDiv{
        margin-top: 5px;
    }
    .divTitle{
        background-color: #eef1f6;
        border: 1px solid #e4e8f1;
        height: 25px;
        line-height: 25px;
    }
    .uploadParam{
        margin-left: 2px;
        font-weight: bold;
    }
    .rowDiv{
        border-bottom: 1px solid  #dfe6ec;
        border-left: 1px solid  #dfe6ec;
        border-right: 1px solid  #dfe6ec;
        /*border: 1px solid lightgray;*/
        padding-right: 15px;
        padding-top: 5px;
        padding-bottom: 5px;
    }
</style>

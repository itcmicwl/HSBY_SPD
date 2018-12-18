<template>
    <div id="ae-report-query" style="height: 99%;">
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;height: 100%">
            <div>
                <el-form :inline="true" :model="filters" ref="adverseEventsForm">
                    <el-form-item label="编码：" prop="code">
                        <el-input v-model="filters.code" placeholder="编码" ></el-input>
                    </el-form-item>
                    <el-form-item label="报告来源：" prop="reportSource">
                        <el-select v-model="filters.reportSource" placeholder="请选择"  >
                            <el-option
                                    v-for="item in reportSourceArray"
                                    :key="item.value"
                                    :label="item.text"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="报告人：" prop="reporter">
                        <el-input v-model="filters.reporter" placeholder="报告人" ></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" v-on:click="getAdverseEventsList(1)" >查询</el-button>
                        <el-button type="primary" v-on:click="resetAdverseEvents('adverseEventsForm')" >重置</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <!--列表-->
            <template>
                <el-table :data="adverseEvents" highlight-current-row v-loading="loading" style="width: 100%;" border fit height="outter" class="aeReportQuery-tableheight">
                    <el-table-column type="index" label="序号"  align="center" width="62"></el-table-column>
                    <el-table-column prop="code" label="事件编码" align="center" width="100" ></el-table-column>
                    <el-table-column prop="reportSource" label="报告来源" :formatter="formateReportSource" align="center" width="90"></el-table-column>
                    <el-table-column prop="orgName" label="报告单位" align="center" width="150"></el-table-column>
                    <el-table-column prop="reporter" label="报告人" align="center" width="120"></el-table-column>
                    <el-table-column prop="reporterType" label="报告人类别" align="center" :formatter="formateReporterType" width="80"></el-table-column>
                    <el-table-column prop="eventOccurrenceTime" label="事件发生日期" align="center" :formatter="formateDate" width="100"></el-table-column>
                    <el-table-column prop="eventResult" label="事件后果" header-align="center" :formatter="formateEventResult" width="250"></el-table-column>
                    <el-table-column prop="apparatusWhereUsed" label="器械使用场所" align="center" :formatter="formateApparatusWhereUsed" width="100"></el-table-column>
                    <el-table-column prop="eventReportStatus" label="事件报告状态" align="center" :formatter="formateEventReportStatus" width="110"></el-table-column>
                    <el-table-column label="操作" width="150" align="center">
                        <template slot-scope="scope">
                            <el-button type="primary" size="mini" @click="getAdverseEventsDetail(scope.row)" icon="search">详情</el-button>
                            <el-button type="primary" size="mini" @click="getAdverseEventsFiles(scope.row)" icon="el-icon-picture">附件</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <!--工具条-->
                <el-col :span="24" class="toolbar">
                    <el-pagination small @size-change="handlePSizeChange" style="padding-right: 20px;text-align: right;"
                                   @current-change="handleCurrentChange" :current-page="page"
                                   layout="total, sizes, prev, pager, next" :page-sizes="[20, 30, 50, 100]" :page-size="pageSize" :total="total">
                    </el-pagination>
                </el-col>
            </template>
        </el-col>
        <!--编辑界面-->
        <el-dialog title="不良事件详情"  close="onClose"  :visible.sync="editFormVisible" :close-on-click-modal="false" size="full">
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
                                        <el-radio label="1">医师</el-radio>
                                        <el-radio label="2">技师</el-radio>
                                        <el-radio label="3">护士</el-radio>
                                        <el-radio label="4">工程师</el-radio>
                                        <el-radio label="5">其它</el-radio>
                                    </el-radio-group>
                                </el-form-item>
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
                                                        <el-date-picker v-model="adverseEvents.sickBirthday" type="date"  
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
                                                <el-form-item label="事件后果 ：" labelWidth="143px" prop="eventResult">
                                                    <el-radio-group v-model="adverseEvents.eventResult" >
                                                        <el-radio label="1">死亡</el-radio><br/>
                                                        <el-radio label="2">危及生命</el-radio><br/>
                                                        <el-radio label="3">机体功能结构永久性损伤</el-radio><br/>
                                                        <el-radio label="4">可能导致机体功能结构永久性损伤</el-radio><br/>
                                                        <el-radio label="5">需要内、外科治疗避免上述永久损伤</el-radio><br/>
                                                        <el-radio label="6">其它</el-radio>
                                                    </el-radio-group>
                                                </el-form-item>
                                            </el-row>
                                            <el-row>
                                                <el-form-item label="事件陈述：" labelWidth="143px">
                                                    <el-input type="textarea" :rows="8" v-model="adverseEvents.eventDetail" ></el-input>
                                                </el-form-item>
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
                                                            <el-radio label="1">已通知使用单位</el-radio>
                                                        </el-col>
                                                        <el-col :span="12">
                                                            <el-radio label="2">已通知生产企业</el-radio>
                                                        </el-col>
                                                    </el-row>
                                                    <el-row>
                                                        <el-col :span="12">
                                                            <el-radio label="3">已通知经营企业</el-radio>
                                                        </el-col>
                                                        <el-col :span="12">
                                                            <el-radio label="4">已通知药监部门</el-radio>
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
                                <div style="margin-top: 2%;text-align: right">
                                    <el-button @click.native="editFormVisible = false" type="info">返回</el-button>
                                </div>
                            </el-col>
                        </el-row>
                    </div>
                </div>
            </el-form>
        </el-dialog>
        <el-dialog title="不良事件附件查看"  close="onClose"  :visible.sync="fileDialogVisible" :close-on-click-modal="false" size="full">
            <template v-if="adverseEventsFiles.length<1">
                <img src="../../assets/images/noImg.jpg" class="image">
            </template>
            <template v-else>
                <el-carousel >
                    <el-carousel-item v-for="item in adverseEventsFiles" :key="item.id">
                        <img :src="imgBasePath + item.filePath" />
                    </el-carousel-item>
                </el-carousel>
            </template>
        </el-dialog>
    </div>
</template>
<script>
    import {fileServer} from '../../common/js/constance';
    import {REPORT_SOURCE} from '../../common/js/constance';
    import {REPORTER_TYPE} from '../../common/js/constance';
    import {EVENT_RESULT} from '../../common/js/constance';
    import {APPARATUS_WHEREUSED} from '../../common/js/constance';
    import {EVENTREPORT_STATUS} from '../../common/js/constance';
    export default {
        data() {
            return {
                adverseEvents:{
                    reportSource:'',
                    sickSex:'2',
                    apparatusWhereUsed:'1',
                    eventResult:'',
                    operator:'1',
                    reporterType:'',
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
                    reprotTime:'',
                    code:''
                },

                filters: {
                    code: '',
                    reportSource: '',
                    reporter:''
                },
                total: 0,
                page: 1,
                pageSize:20,
                loading: false,
                adverseEvents: [],
                adverseEventsFiles:[],

                reportSourceArray:[],
                reporterTypeArray:[],
                eventResultArray:[],
                apparatusWhereUsedArray:[],
                eventReportStatusArray:[],

                editFormVisible:false,
                fileDialogVisible:false,

                fullHeight: 0,

                imgBasePath:''
            }
        },
        methods: {
            // 查看不良事件附件
            getAdverseEventsFiles:function (row) {
                this.axios.post('/spdHERPService/adverseEvents/adverseEventsFile/list', row).then(res => {
                    this.adverseEventsFiles = res.data.data;
                    this.fileDialogVisible = true;
                }, err => {
                });


//                this.adverseEvents = row;
//                this.editFormVisible = true;
            },
            // 查看不良事件详情
            getAdverseEventsDetail:function (row) {
                this.adverseEvents = row;
                this.editFormVisible = true;
            },
            formateReportSource:function (d1,d2,d3) {
                if(d3)
                    return this.reportSourceArray.find(item=>item.value==d3).text || '';
            },
            formateReporterType:function (d1,d2,d3) {
                if(d3)
                    return this.reporterTypeArray[d3].text || '';
            },
            formateEventResult:function (d1,d2,d3) {
                if(d3)
                    return this.eventResultArray[d3].text || '';
            },
            formateApparatusWhereUsed:function (d1,d2,d3) {
                if(d3)
                    return this.apparatusWhereUsedArray[d3].text || '';
            },
            formateEventReportStatus:function (d1,d2,d3) {
                if(d3)
                    return this.eventReportStatusArray[d3].text || '';
            },
            formateDate:function (d1,d2,d3) {
                if(d3)
                    return d3.substr(0,10);
            },
            // 重置查询条件
            resetAdverseEvents:function (formName) {
                this.$refs[formName].resetFields();
                this.getAdverseEventsList(1);
            },
            // 获取不良事件列表
            getAdverseEventsList: function (pIndex) {
                this.page = pIndex;
                var param = {
                    orderBy: "",
                    queryObject: {
                        code: this.filters.code,
                        reportSource: this.filters.reportSource,
                        reporter:this.filters.reporter
                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;
                this.axios.post('/spdHERPService/adverseEvents/adverseEvents/listByPage', param).then(res => {
                    this.loading = false;
                    this.adverseEvents = res.data.data.data;
                    this.total = res.data.data.total;
                }, err => {
                    this.loading = false;
                });
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getAdverseEventsList(this.page);
            },
            handlePSizeChange(val) {
                this.pageSize = val;
                this.getAdverseEventsList(this.page);
            }
        },
        mounted() {

            this.fullHeight = document.documentElement.clientHeight;
            this.imgBasePath = fileServer.imgBasePath;
            this.reportSourceArray = REPORT_SOURCE;
            this.reporterTypeArray = REPORTER_TYPE;
            this.eventResultArray = EVENT_RESULT;
            this.apparatusWhereUsedArray = APPARATUS_WHEREUSED;
            this.eventReportStatusArray = EVENTREPORT_STATUS;
            this.getAdverseEventsList(1);
        }
    };
</script>
<style>
    #ae-report-query .aeReportQuery-tableheight {
        height: calc(100% - 85px);
    }
    #adverseEventsDiv .el-form-item{
        margin-bottom:0px;
    }
    #ae-report-query .reportSourceDiv{
        border: 1px solid #dfe6ec;
    }
    #ae-report-query .titleDiv{
        margin-bottom: 5px;
        border-bottom: 1px solid #dfe6ec;
        text-align: center;
        background-color: #eef1f6;
    }
    #ae-report-query .title{
        font-size: medium;
        font-weight: bold;
        line-height: 35px;
    }

    #ae-report-query .provDiv{
        margin-top: 5px;
    }
    #ae-report-query .divTitle{
        background-color: #eef1f6;
        border: 1px solid #e4e8f1;
        height: 25px;
        line-height: 25px;
    }
    #ae-report-query .uploadParam{
        margin-left: 2px;
        font-weight: bold;
    }
    #ae-report-query .rowDiv{
        border-bottom: 1px solid  #dfe6ec;
        border-left: 1px solid  #dfe6ec;
        border-right: 1px solid  #dfe6ec;
        /*border: 1px solid lightgray;*/
        padding-right: 15px;
        padding-top: 5px;
        padding-bottom: 5px;
    }
    #ae-report-query .image {width: 100%;display: block;}
    #ae-report-query .el-carousel__item:nth-child {
        background-color: #99a9bf;
    }
    #ae-report-query .el-carousel {
        height:100%;
        overflow-x: hidden;
        position: relative;
        text-align: center;
        background-color: rgb(80, 87, 95);
    }
    #ae-report-query .el-carousel__container{
        height: calc(100% - 30px);;
    }
    #ae-report-query .el-dialog__body{
        height: calc(100% - 60px);
    }
</style>

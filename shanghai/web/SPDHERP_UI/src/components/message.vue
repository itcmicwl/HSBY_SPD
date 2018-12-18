<template>
    <div>
        <el-popover ref="p_msg" v-model="msgPopover" placement="bottom" width="400" trigger="click" popper-class="p-msg" @show="onMsgShow" @hide="onMsgHide">
            <div v-show="!showDetailMsg">
                <div class="el-popover__title">通知</div>
                <dl class="lst_notice" >
                    <dt v-for="(item) in getSpdMsg" :key="item.id" @click="showDetail(item.id)"><i></i><span class="notice_title">{{item.title}}</span><span class="notice_date">{{item.writeDatetime | dateFormat}}</span></dt>
                </dl>
            </div>
            <div v-show="showDetailMsg">
                <dl class="msgInfo" v-if="msgDetail">
                        <dt>
                            <el-col :span="2">
                                <i class="fa fa-mail-reply back" @click="showDetailMsg = false" title="返回消息列表"></i>
                            </el-col>
                            <el-col :span="16">
                                <span class="notice_title" :title="msgDetail.title">{{msgDetail.title}}</span>												
                            </el-col>
                            <el-col :span="6">
                                <div class="up_down">
                                    <el-button size="mini" type="text" :disabled="currentMsgIndex<=0" @click="msgPrev()">上一条</el-button>
                                    <el-button size="mini" type="text" :disabled="currentMsgIndex>=getSpdMsg.length-1" @click="msgNext()">下一条</el-button>
                                </div>
                            </el-col>
                        </dt>
                        <dd class="dd_content"><span class="notice_content">{{msgDetail.message}}</span><span class="notice_date">{{msgDetail.writeDatetime | dateFormat}}</span></dd>
                        <dd class="dd_foot" v-show="msgDetail.url"><el-button size="mini" type="text" @click="dealMsg(msgDetail.url)">现在处理</el-button></dd>
                </dl>								
            </div>							
        </el-popover>	
        <el-badge :value="getSpdMsg.length" class="msg-bar">
            <i class="fa fa-bell-o i-msg" v-popover:p_msg :title="getSpdMsg.length>0?'您有新的未读消息':'无消息'"></i>
        </el-badge>
    </div>
</template>

<script>
import axios from 'axios';
import { appConfig } from '../config/appConfigs';
import { mapGetters, mapActions } from 'vuex';
export default {
  data() {
    return {
        showDetailMsg:false,
        msgPopover:false,
        currentMsgIndex:-1,
        msgDetail:null//消息明细
    }
  },
  computed: {
		...mapGetters(["getSpdMsg","getLoginUser"])
	},
  methods: {
    ...mapActions(["setSpdMsg",]),
    dealMsg(url){
        if(url){
            //this.$router.push(url);
            this.msgPopover = false;
        }
    },
    onMsgShow(){
        this.showDetailMsg = false;
        this.clearInv();
    },
    onMsgHide(){
        this.startInv();
    },
    showDetail(msgId){
        this.showDetailMsg = true;
        this.currentMsgIndex = this.getSpdMsg.findIndex(item=>item.id==msgId);
        this._setMsgDetail();
    },
    msgPrev(){
        this.currentMsgIndex--;
        this._setMsgDetail();
    },
    msgNext(){
        this.currentMsgIndex++;
        this._setMsgDetail();
    },
    _setMsgDetail(){
            if(this.currentMsgIndex>=0 && this.currentMsgIndex < this.getSpdMsg.length){
                this.msgDetail = this.getSpdMsg[this.currentMsgIndex];
                this.setMsgRead(this.msgDetail);
            }else{
                this.msgDetail = null;
            }
    },
    getMsgList(){
        var _this = this;
        if(this.getLoginUser){
            this.axios.get(`/platformService/sys/sysMessage/getUserMsgList/${appConfig.appCode}/${this.getLoginUser.userId}`).then(res=>{
                if(res.data.code == 0){
                    _this.setSpdMsg(res.data.data);
                }
            });
        }
    },
    setMsgRead(msg){
        var param = {messageId:msg.id,userId:this.getLoginUser.userId}
            this.axios.post(`/platformService/sys/sysMessageConfirm/setMsgRead`,param).then(res=>{
                if(res.data.code == 0){
                    var tmpMsg = this.getSpdMsg;
                    tmpMsg.splice(this.currentMsgIndex,1);
                    this.setSpdMsg(tmpMsg);
                }
            });
    },
    startInv(){
        this.invId = setInterval(this.getMsgList, 1000*30);
    },
    clearInv(){
        clearInterval(this.invId);
    },
  },
  mounted() {
   this.startInv();
  },
  destroyed () {
		this.clearInv();
	}
}
</script>

<style lang="scss">
    @import '~gycolor';    
    .el-badge__content.is-fixed{
        top:25px;
        height:15px;
    }    
    .i-msg{
        font-size:20px;
        cursor: pointer;
        color:$--color-primary;
    }		
</style>

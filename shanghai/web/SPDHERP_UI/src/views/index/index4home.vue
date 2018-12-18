<!--首页显示内容-->
<template>
  <el-row id="homeIndx" >
    <el-row :span="24">
      <el-card>      
          <el-row :gutter="5"> 
            <el-col :span="6">
              <div class="boxs  l-seagreen">
                <div class="boxs-body" @click="clickProvEvt()">
                    <h3 class="mt-0">{{ provCount }}</h3>
                    <p>供应商</p>
                </div>
                <i class="fa fa-id-card fa-5x" aria-hidden="true"  title="供应商数量"></i>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="boxs  l-blue">
                  <div class="boxs-body" @click="clickProductsEvt()">
                      <h3 class="mt-0">{{ hosProductsCount }}</h3>
                      <p>医院商品</p>
                  </div>
                  <i class="fa fa-hospital-o fa-5x" aria-hidden="true" title="医院商品数量"></i>
                </div>
            </el-col>
            <el-col :span="6">
              <div class="boxs  l-parpl">
                  <div class="boxs-body" @click="clickOutDateCertsEvt()">
                      <h3 class="mt-0">{{ outDateInMonth }}</h3>
                      <p>30天内过期</p>
                  </div>
                  <i class="fa fa-warning  fa-5x" aria-hidden="true" title="证照将要过期"></i>
                </div>
            </el-col>
            <el-col :span="6">
              <div class="boxs  l-amber">
                    <div class="boxs-body" @click="clickOutDateCertsEvt()">
                        <h3 class="mt-0">{{ outDateCoumt }}</h3>
                        <p>已经过期</p>
                    </div>
                    <i class="fa fa-times-rectangle-o  fa-5x" aria-hidden="true" title="证照过期"></i>
                </div>
            </el-col>            
          </el-row>
      </el-card>
    </el-row>
    <el-row :gutter="8" >
      <el-col :span="18">
        <el-card>
          <!-- <div slot="header" class="clearfix">
            <span>供应商证照统计</span>
          </div> -->
            <index4Hos></index4Hos>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card><userwidget></userwidget></el-card>
      </el-col>
    </el-row>
  </el-row>
</template>

<script>
import index4Hos from './index4Hos';
import userwidget from './com/userWidget'
export default {
  components: {
    index4Hos,
    userwidget
  },
  data() {
    return {
      userScore:4.5,
      activeNames1: ['1'],
      activeNames2: ['2'],
      activeNames3: ['3'],
      activeNames4: ['4'],
      activeNames5: ['5'],

      outDateCoumt: 0, //已经过期的证照数
      outDateInMonth: 0, //一个月内过期的证照数
      provCount: 0, //医院供应商数量
      hosProductsCount: 0, //医院商品数量
    };
  },
  mounted: function() {
    this.hosId = this.user.corpId;
    this.queryProvOutdateCerts();
    this.getHosProvInfoVoList();
    this.getHosGoodsList();
  },
  methods: {
    // 查询过期证件
    queryProvOutdateCerts: function() {
      var param = {
        orderBy: '',
        queryObject: {
          hosId: this.hosId,
          // outDate:this.outDateCertQuery.outDate
        },
      };
      let o = this;
      o.axios.post('/spdHERPService/reportAnalysis/IndexStatisticsController/queryProvOutdateCerts', param).then(
        res => {
          if (res.data.data) {
            for (var i = 0; i < res.data.data.length; i++) {
              var item = res.data.data[i];
              if (item.outDate && item.outDate > 0) {
                o.outDateCoumt++;
              } else if (item.outDate && item.outDate >= -30) {
                o.outDateInMonth++;
              }
            }
          }
        },
        err => {}
      );
    },

    // 医院获取供应商列表
    getHosProvInfoVoList: function() {
      var param = {
        orderBy: '',
        queryObject: {
          hosId: this.hosId,
        },
      };
      this.loading = true;
      this.axios.post('/spdHERPService/provManager/hosProvInfo/hosProvInfoVoList', param).then(
        res => {
          this.loading = false;
          this.provCount = res.data.data.total;
          // this.page = res.data.data.pageNum;
        },
        err => {
          this.loading = false;
        }
      );
    },

    //医院商品数
    getHosGoodsList: function() {
      let hosId = this.user.corpId;
      var param = {
        orderBy: '',
        queryObject: {
          hosId: hosId,
        },
      };
      this.axios.post('/spdHERPService/myGoods/hosGoods/getHosGoodsCountByHos', param).then(
        res => {
          this.loading = false;
          this.hosProductsCount = res.data;
        },
        err => {
          this.loading = false;
        }
      );
    },
    /**点击过期证照数 */
    clickOutDateCertsEvt: function() {
      this.$router.push({ path: 'provCerts' });
    },
    /**点击供应商数 */
    clickProvEvt: function() {
      this.$router.push({ path: 'provList' });
    },
    /**点击医院商品数 */
    clickProductsEvt: function() {
      this.$router.push({ path: 'productInfo' });
    },
  },
};
</script>

<style scoped="">
 .boxs {
    position: relative;
    color: #4d585f;
    background-color: white;
    filter: alpha(opacity=100);
    opacity: 1;
    -webkit-transition: opacity 0.25s ease-out;
    -moz-transition: opacity 0.25s ease-out;
    transition: opacity 0.25s ease-out;
    border-radius: 3px;
    height: 100px;
     
}
.l-blue {
    background: linear-gradient(45deg, #72c2ff, #86f0ff) !important;
    color: #fff !important;
}
.l-parpl {
    background: linear-gradient(45deg, #a890d3, #edbae7) !important;
    color: #fff !important;
}
.l-seagreen {
    background: linear-gradient(45deg, #8ed8ec, #85f7b5) !important;
    color: #fff !important;
}
.l-amber {
    background: linear-gradient(45deg, #fda582, #f7cf68) !important;
    color: #fff !important;
}
.l-blush {
    background: linear-gradient(45deg, #dd5e89, #f7bb97) !important;
    color: #fff !important;
}
.boxs .boxs-header, .boxs .boxs-widget, .boxs .boxs-body, .boxs .boxs-footer {
    position: relative;
    padding: 15px 0px 15px 15px;
    float: left;
}
.boxs .boxs-body{
    margin-left: 5%;
    cursor: pointer;
    padding: 20px 0px 0px 0px;
}
.fa{
  float: right;
  margin-right: 10%;
  line-height: 100px;
}
.mt-0{
    margin-top: 0 !important;
    font-size: 24px;
    margin-bottom: 12px;
}
p {
    margin: 0 0 10px;
}
</style>


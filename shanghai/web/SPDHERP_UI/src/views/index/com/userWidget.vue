<!--用户基本信息组件-->
<template>
  <section class="boxs user_widget">
      <div class="uw_header l-blush">
          <h3>{{user.ename}}</h3>
          <h5>{{user.job}} &amp; {{user.orgName}}</h5>
      </div>
      <div class="uw_image"> 
        <router-link to="/baseData/baseInfo" class="user-setting"><img class="img-circle" src="@/assets/user.png" alt="User Avatar"></router-link>
      </div>
      <div class="uw_footer">
          <!-- <div class="text-center">
              <el-rate v-model="userScore" disabled show-score  text-color="#ff9900" score-template="你很优秀"> </el-rate>
              <p class="mt-20">It is a long established fact that a reader will be content of a page</p> 
          </div>-->
      </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      userScore:4.5,
    };
  },
  methods: {
  
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
.user_widget {
    position: relative;
    height: 280px;
}
.user_widget .uw_header {
    padding: 20px;
    height: 120px;
    border-top-right-radius: 3px;
    border-top-left-radius: 3px;
    text-align: center;
}
.user_widget .uw_header h3 {
    margin-top: 0;
    margin-bottom: 5px;
    font-size: 25px;
    font-weight: 300;
    text-shadow: 0 1px 1px rgba(0,0,0,0.3);
}
.user_widget .uw_image {
    position: absolute;
    top: 87px;
    left: 50%;
    margin-left: -60px;
}
.user_widget .uw_image img {
    width: 114px;
    height: auto;
    border: 3px solid #fff;
}
.img-circle {
    border-radius: 50%;
    vertical-align: middle;
}
.user_widget .uw_footer {
    padding: 50px 20px 20px 20px;
}
.text-center {
    text-align: center;
}
.user-setting{
  margin-top: 10px;
}
</style>


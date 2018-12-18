<template>
      <el-cascader :options="orgInfo"  v-model="Dept" :props="orgProps" :show-all-levels="false" :change-on-select="true">
      </el-cascader>
</template>

<script>
export default {
    data() {
        return {
            loading: false,
            orgInfo: [],
            orgProps: {
                value: "code",
                label: "label"
            },
        };
    },
     computed: {
        Dept: {
            get: function () {
                var res = [];
                var dept = this.getDeptCodeById(this.value,"id");
                if (dept) {
                    var deptCode = dept.code;
                    let path = deptCode.split('.');
                    if (path.length > 1) {
                        var i = 0;
                        for (i; i < path.length; i++) {
                            let tempArr = path.slice(0, i + 1);
                            res.push(tempArr.join('.'));
                        }
                    } else {
                        res = path;
                    }
                }
                return res;
            },
            set: function (pathArr) {
                let res = this.getDeptCodeById(pathArr[pathArr.length-1],"code");
                this.setCurrentSlt(res);
            }
        },
     },
    props: ['hosId', 'value'],
    methods: {
        getDeptCodeById(deptId,type){
          if(!deptId){
            return null;
          }
          if(Array.isArray(this.orgInfo) && this.orgInfo.length > 0){
            var dept = this.findDeptById(deptId,this.orgInfo,type);
            if(dept){
              return dept.ob;
            }else{
              return null;
            }
          }
        },
        findDeptById(deptId,deptTree,type){//type只能为id或code
          var res = null;
          if(Array.isArray(this.orgInfo) && this.orgInfo.length > 0){
            for(var i =0;i<deptTree.length;i++){
              if(deptTree[i][type] == deptId){
                res = deptTree[i];
                return res;
              }else if(Array.isArray(deptTree[i].children) && deptTree[i].children.length > 0){
                res  = this.findDeptById(deptId,deptTree[i].children,type);
                if(res){
                  return res;
                }
              }
            }            
          }
        },
        getOrgInfo() {
          var _this =this;
            var params = {
              corpId:this.hosId
            };
            this.axios.post('/platformService/sys/org/getOrgsByUnit', params).then(res=>{
                if (res.data.code == "0") {
                  _this.orgInfo = res.data.data.children[0].children;
              }
            });
        },
        setCurrentSlt(dept){
            this.$emit('input', dept.id);
            this.$emit('change', dept);
        },
    },    
    mounted() {
        this.getOrgInfo();
    }
}
</script>


<template>
    <section>    
            <el-cascader :options="stckInfos"  
                v-model="stockObj"
                :props="stckProps" :show-all-levels="false" 
                :change-on-select="false" @change="test">  
            </el-cascader>
    </section>
</template>

<script>
export default {
    data() {
        return {
            loading: false,
            stckInfos:[],
            stckProps:{},
            stockObj:[],        
        };
    },
 
    props: [ 'hosId', 'userId','inStocCode','isDefault'],
    methods: {
        getStocsByParam: function () {
            //定义变量 标识是否只需要当前科室库房
            let param=0;
            if(this.isDefault){
                param=1;
            }
            this.axios.post( `/spdHERPService/deptMgr/stocInfo/getStocksByUser/`+param).then(res => {
                let map = res.data.data;

                let nodes = {};
                let o=this.stckInfos;
                //定义临时对象保存默认选中所需的值
                let _temp={};
                for(let key in map){
                    if(key == 'stocInfoList'){
                        nodes = map[key];   
                    }
                }
                //添加父选项
               for(let i = 0; i < nodes.length; i++){                    
                let temp={};
                let flag=true;
                //判断是否已有该选项
                for(let j=0;j< o.length; j++){
                    if (o[j].label == nodes[i].stockLevel)
                    {
                        flag=false;
                        break;
                    }
                }
                //没有 则添加该选项
                if(flag){
                temp.id=i
                temp.value=i
                temp.label=nodes[i].stockLevel;
                temp.children=[];
                o.push(temp);
                //保存默认选中项的值
                _temp.oneValue=i;
                //保存默认选中项的标题
                _temp.label=nodes[i].stockLevel;
                }
               }
            //遍历 添加子选项
             for(let k = 0;k< nodes.length; k++){
                 for(let l=0;l< o.length; l++){
                     if(o[l].label==nodes[k].stockLevel){
                          o[l].children.push({
                           id: nodes[k].stockId, value:nodes[k].stockId, label:nodes[k].stockName
                      
                     } );
                 }
                 //保存默认选中项的值
                 if(_temp.label == nodes[k].stockLevel ){
                     _temp.twoValue=nodes[k].stockId;
                 }
             }
            }
            //根据父组件参数 判断是否需要默认选中
            if(this.isDefault){
            this.stockObj=[_temp.oneValue,_temp.twoValue];
            this.test(this.stockObj);
            }
        });
     
        // this.$emit('sltChange', []);         
    },
        test(dx){
            this.$emit('input', dx[1]);   
            this.$emit('sltChange', dx);          
        },
         getStockByDeptChange(){
            this.getStocsByParam();
            this.$emit('sltChange', []);
        }
       
    },
    computed: {
           stockInfo: {
            get: function () {
                var res = [];
                if (this.inStocCode) {
                    let path = this.inStocCode.split('.');
                    if (path.length > 1) {
                       res.push(parseInt(path[0]));
                       res.push(path[1]);
                    } else {
                        res = path;
                    }
                   
                }
                
               // res= [0,'stoc-010'];
              
                return res;
            },
            set: function (pathArr) {
                let res = { inStocId: '', inStocCode: '', inStocName: '' };
                if (pathArr.length > 0) {
                    //res = pathArr[pathArr.length - 1];
                    var tmpArr = this.stockObj.concat();
                    for (var i = 0; i < pathArr.length; i++) {
                        if (tmpArr) {
                            var node = tmpArr.find(item => item.stockCode == pathArr[i]);
                            if (node) {
                                tmpArr = node.children;
                                res.inStocCode = node.stockCode;
                                res.inStocId = node.id;
                                res.inStocName = node.label;
                            }
                        }
                    }
                }
                this.d.inStocId = res.inStocId;
                this.d.inStocCode = res.inStocCode;
                this.d.inStocName = res.inStocName;
                
            }
        },
        
    },
    mounted() {
      //  alert(this.userId);
        this.getStocsByParam();
        
    }
}

</script>


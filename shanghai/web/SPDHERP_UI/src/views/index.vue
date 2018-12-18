<template>
    <el-row>
        <index4home v-if="userKind == '1'"></index4home>
        <index4home v-else-if="userKind == '2'"></index4home>
    </el-row>  
</template>
<script>
import index4Hos from './index/index4Hos';
import index4Prov from './index/index4Prov';
import index4home from './index/index4home';
export default {
        data() {
            return {
                userKind:''
            }
        },        
        methods: {   
		
        },
         components: {
            index4Hos,index4Prov,index4home
        },
        mounted: function () {
            this.userKind = this.user.corpKind;
        }
    }
</script>

<style scoped>

</style>
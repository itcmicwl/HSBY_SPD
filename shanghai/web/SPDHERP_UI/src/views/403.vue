<template>
    <div style="font-size: 20px;text-align: center;color: rgb(192, 204, 218);">
        <img src="../assets/images/403.png" />
            <el-button @click.native="goLastPage" >返回</el-button>
        <el-button type="primary" @click.native="changeUser" >切换账号</el-button>
    </div>
</template>
<script>
export default {
    data() {
        return {
           
        };
    },
    methods: {
        changeUser() {
            this.$router.push({ path: '/login' });
        },
        goLastPage(){
            let redirect = decodeURIComponent(this.$route.query.last || '/');
            this.$router.push({ path: redirect });
        }
    },
    mounted() {
    }
}

</script>
<style scoped>

</style>

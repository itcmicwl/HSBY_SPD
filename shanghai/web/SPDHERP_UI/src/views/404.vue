<template>
    <div style="font-size: 20px;text-align: center;color: rgb(192, 204, 218);">
        <img src="../assets/images/404.png" />
        <el-button @click.native="goLastPage" >返回</el-button>
    </div>
</template>

<script>
export default {
    data() {
       return {}
    },
    methods: {        
        goLastPage(){
            let redirect = decodeURIComponent(this.$route.query.last || '/');
            this.$router.push({ path: redirect });
        }
    },
    mounted() {
    }
}

</script>
<style lang="scss" scoped>
    .page-container {
        font-size: 20px;
        text-align: center;
        color: rgb(192, 204, 218);
    }
</style>

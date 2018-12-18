<template>
    <el-steps :space="100" :active="status" finish-status="success" center>
        <el-step title="已保存" ></el-step>
        <el-step title="已提交" ></el-step>
        <el-step title="部分确认" ></el-step>
        <el-step title="全部确认" ></el-step>
    </el-steps>
</template>
<script>
export default {
    name: 'orderStatus',
    data() {
        return {
            
        }
    },
    props: ['status'],
    methods: {

    },
    mounted() {
       
    }
};

</script>

<style>

</style>
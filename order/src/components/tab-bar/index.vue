<template>
    <van-tabbar v-model="active" class="tabbar"> 
  <van-tabbar-item icon="fire-o" to="/dishes">热门电影</van-tabbar-item>
  <van-tabbar-item icon="fire-o" to="/recommend">推荐电影</van-tabbar-item>
  <van-tabbar-item icon="comment" to="/order">评分记录</van-tabbar-item> 
  
  <van-tabbar-item icon="user-o" to="/personal"  >个人中心</van-tabbar-item>
</van-tabbar>
</template>
<script>


export default (await import('vue')).defineComponent({
    name:'TabBar',//组件名称
    data(){
        return{
            active:0,
        }
    },
    methods:{

},
})
</script>

</script>
<style lang="less" scoped>
.tabbar{
    margin-top: 100px;
}
</style>

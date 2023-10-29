<template>
 <van-nav-bar
  :title="$route.meta.title"
  left-text="返回"
  left-arrow
  @click-left="onClickLeft"
  :fixed="true"
  :placeholder="true"
/>
</template>
<script>

export default (await import('vue')).defineComponent({
    name:'NavBar',//组件名称
    data(){
        return{ 

        }
    },
    methods: {
    onClickLeft() {
      this.$router.back(-1); //返回上一级
    },
  },
  
})
</script>
<!-- scoped: 作用域，当前css只当前的组件生效-->
<style lang="less" scoped>

</style>
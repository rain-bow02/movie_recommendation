<template>
  <div class="back-top" :style="visible.default ? '':'display:none'" @click="handlerClick">
      TOP
  </div>
</template>
<script>
//该组件需要使用的插件在此处导入
//********** */
/**导出该组件 */
export default{
  name:'BackTop', //组件名称
  data(){
      return{
          visible:{
              type:Boolean,
              default:true
          }
      }
  },
  methods:{
      handlerClick(){
        this.$emit('click');
      }
  },
  mounted(){
      console.log("top"+this.visible.default)
  }
}
</script>
<!-- scoped: 作用域，当前css只当前的组件生效-->
<style lang="less" scoped>
   .back-top {
          width: 50px;
          height: 50px;
          line-height: 50px;
          text-align: center;
          border: 1px solid;
          border-radius: 40px;
          background: #CCC;
          position: fixed;
          right: 20px;
          bottom: 120px;
}
</style>

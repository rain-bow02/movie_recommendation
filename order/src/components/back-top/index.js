import BackTop from './index.vue'
const plugin = {
    install(Vue){
        //注册全局组件
        Vue.component('back-top',BackTop);
    }
}
export default plugin
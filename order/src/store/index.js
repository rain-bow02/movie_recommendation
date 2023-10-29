import Vue from 'vue'
import Vuex from 'vuex'
import storeDetail from './storeDetail'
Vue.use(Vuex)
//状态管理，默认数据存储到当前本地内存中。
export default new Vuex.Store({
  //需要存储的数据
  state: {
  },
  //获取数据的方法
  getters: {
  },
  //修改数据的方法
  mutations: {
  },
  //数据处理方法
  actions: {
  },
  //模块
  modules: {
    storeDetail,
  }
})

<template>
  <div class="home">
    <!-- 搜索框-->
    <div class="search">
      <i class="iconfont">&#xe8c9;</i>
      <div class="input">
        <van-search
          v-model="searchValue"
          placeholder="请输入搜索关键词"
          @search="onSearch"
        />
      </div>
      <i class="iconfont">&#xe623;</i>
    </div>

    <!-- 导航宫格 -->
    <van-grid :gutter="10">
      <van-grid-item @click="screenDishes(1010)">
        <van-icon class="iconfont"> &#xe606; </van-icon>
        <span style="font-size: 12px; font-weight: 600">快餐</span>
      </van-grid-item>

      <van-grid-item @click="screenDishes(1002)">
        <van-icon class="iconfont"> &#xe8c0;</van-icon>
        <span style="font-size: 12px; font-weight: 600">套饭</span>
      </van-grid-item>
      <van-grid-item @click="screenDishes(1004)">
        <van-icon class="iconfont"> &#xe675; </van-icon>
        <span style="font-size: 12px; font-weight: 600">饮料</span>
      </van-grid-item>

      <van-grid-item @click="screenDishes(1001)">
        <van-icon class="iconfont"> &#xfaa4; </van-icon>
        <span style="font-size: 12px; font-weight: 600">炒菜</span>
      </van-grid-item>

      <van-grid-item @click="screenDishes(1009)">
        <van-icon class="iconfont"> &#xe60a; </van-icon>
        <span style="font-size: 12px; font-weight: 600">儿童餐</span>
      </van-grid-item>

      <van-grid-item @click="screenDishes(1006)">
        <van-icon class="iconfont"> &#xe60c;</van-icon>
        <span style="font-size: 12px; font-weight: 600">面条</span>
      </van-grid-item>

      <van-grid-item @click="screenDishes(1008)">
        <van-icon class="iconfont">&#xe63c; </van-icon>
        <span style="font-size: 12px; font-weight: 600">果蔬</span>
      </van-grid-item>

      <van-grid-item @click="screenDishes(1007)">
        <van-icon class="iconfont"> &#xe725; </van-icon>
        <span style="font-size: 12px; font-weight: 600">肉蛋</span>
      </van-grid-item>
    </van-grid>

    <van-tabs v-model="active" @change="changeTab">
      <van-tab title="全部" name=""></van-tab>
      <van-tab title="时间升序" name="time"></van-tab>
      <van-tab title="距离升序" name="distance"></van-tab>
    </van-tabs>
    <!-- 首页店铺列表 -->
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="我是有底线的应用"
      @load="onLoad"
    >
      <!-- discInfo:拼接距离和时间 -->
      <van-card
        v-for="store in storesList"
        :key="store.id"
        :title="store.name"
        :desc="store.desc"
        :thumb="store.url"
        style="text-align: left"
        @click="jumpToStoreDetail(store)"
      >
        <template #tags>
          <span
            style="
              color: crimson;
              margin: 0px;
              width: 80px;
              height: 20px;
              font-size: 13px;
            "
          >
            {{ store.score }}
          </span>
          <span
            style="
              margin: 0px;
              width: 80px;
              margin-left: 20px;
              margin-right: 80px;
              height: 20px;
              color: #646566;
            "
            >{{ store.sales }}</span
          >
          <van-tag plain type="danger" style="margin-top: 6px">{{
            store.disc
          }}</van-tag>
        </template>
      </van-card>
    </van-list>
  </div>
</template>
<script>
import axios from "axios";
import { getSearchStores, showAll, getSortedStore } from "../../api/home";
/**导出该组件 */
export default {
  name: "Home", //组件名称
  data() {
    return {
      searchValue: "",
      loading: false,
      finished: true,
      visible: false, //回到顶部展示/隐藏
      active: "",
      storesList: [],
    };
  },
  methods: {
    //加载我们的商品
    async onLoad() {
      const { list } = await getSearchStores(active);
      this.storesList = [
        ...this.storesList, //拷贝数组
        ...list,
      ];
      this.loading = false;
      // console.log(storesList);
    },
    //排序店铺
    getSortedShops(name) {
      getSortedStore(name).then((resp) => {
        this.storesList = resp;
      });
    },
    //当菜单被切换调用此方法
    changeTab(name) {
      //console.log(name+title);
      //发送请求，填充商品信息 通过类型ID查找商品数据 name
      //如果不是“全部标签”
      if (name != "") {
        console.log("lllloo");
        this.getSortedShops(name);
      } else {
        showAll().then((resp) => {
          this.storesList = resp;
        });
      }
    },
    //点击搜索按钮进行搜索  回车进行触发该事件s
    onSearch() {
      this.getStoresList(this.searchValue);
    },
    // 查询数据
    getStoresList(id) {
      getSearchStores(id).then((resp) => {
        this.storesList = resp.list;
      });
    },

    //跳转店铺详情
    jumpToStoreDetail(data) {
      this.$router.push({ name: "storeDetail", params: { store: data } });
    },
    // 筛选菜单
    screenDishes(id) {
      this.$router.push({ name: "dishes", params: { typeId: id } });
    },
  },
  async created() {
    // this.getStoresList();
    showAll().then((resp) => {
      this.storesList = resp;
    });
  },
};
</script>
<!-- scoped: 作用域，当前css只当前的组件生效-->
<style lang="less" scoped>
@font-face {
  font-family: "iconfont"; /* Project id 4123028 */
  src: url("//at.alicdn.com/t/c/font_4123028_ad0v51m1pxg.woff2?t=1686758785425")
      format("woff2"),
    url("//at.alicdn.com/t/c/font_4123028_ad0v51m1pxg.woff?t=1686758785425")
      format("woff"),
    url("//at.alicdn.com/t/c/font_4123028_ad0v51m1pxg.ttf?t=1686758785425")
      format("truetype");
}
.iconfont {
  font-family: "iconfont" !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -webkit-text-stroke-width: 0.2px;
  -moz-osx-font-smoothing: grayscale;
}

.van-card__title {
  font-size: 14px;
  font-weight: 600;
}

.iconfont {
  font-size: 24px;
  color: #666;
}
.search {
  display: flex; //flex布局
  height: 50px;
  align-items: center;
  padding: 0 12px;
}
.input {
  flex: 1;
}
.van-card {
  background-color: red;
}
</style>

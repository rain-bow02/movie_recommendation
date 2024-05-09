<template>
  <div class="recommend">
    <!-- 搜索按钮 -->
    <!-- <van-search
      v-model="searchValue"
      show-action
      placeholder="请输入搜索关键词"
      @search="onSearch"
    >
      <template #action>
        <div @click="onSearchDishes()">搜索</div>
      </template>
    </van-search> -->
    <!-- 分类宫格 -->
    <!--
    <van-tabs @click="typeChange">
      <van-tab
        v-for="type in types"
        :key="type.id"
        :title="type.name"
        :name="type.id"
      >
      </van-tab>
    </van-tabs> -->

    <!-- 菜品列表 
      ../../poster/{{ movie.imdb_id }}.jpg-->

    <van-grid :gutter="10" icon-size="94" :column-num="5">
      <van-grid-item
        v-for="movie in movies"
        :key="movie.id"
        :text="movie.name"
        :icon="require('../../poster/' + movie.imdb_id + '.jpg')"
        @click="jumpToMovieDetail(movie)"
      />
    </van-grid>
  </div>
</template>

<script>
import {
  getMoviesByType,
  getSearchMovies,
  getRecommend,
} from "../../api/dishes";

export default {
  name: "Recommend", //组件名称
  data() {
    return {
      searchValue: "",
      activeName: "a",
      typeId: 1,
      movies: [],
      types: [
        { id: 0, name: "全部" },
        { id: 1, name: "音乐剧" },
        { id: 2, name: "战争" },
        { id: 3, name: "犯罪" },
        { id: 4, name: "浪漫" },
        { id: 5, name: "幻想" },
        { id: 6, name: "戏剧" },
        { id: 7, name: "音乐" },
        { id: 8, name: "科幻" },
        { id: 9, name: "动作" },
        { id: 10, name: "喜剧" },
        { id: 11, name: "传记" },
        { id: 12, name: "家庭" },
        { id: 13, name: "恐怖" },
        { id: 14, name: "短片" },
        { id: 15, name: "纪录片" },
        { id: 16, name: "黑色电影" },
        { id: 17, name: "动画" },
        { id: 18, name: "冒险" },
        { id: 19, name: "新闻" },
        { id: 20, name: "神秘" },
        { id: 21, name: "体育运动" },
        { id: 22, name: "历史" },
        { id: 23, name: "惊悚" },
        { id: 24, name: "西方" },
      ],
    };
  },
  methods: {
    //加载我们的商品
    async onLoad() {
      const { list } = await getSearchMovies(active);
      this.movies = [
        ...this.movies, //拷贝数组
        ...list,
      ];
      this.loading = false;
    },
    //点击后切换type
    typeChange(id, tittle) {
      console.log(id, tittle);
      if (id != 0) {
        getMoviesByType(id)
          .then((resp) => {
            console.log(id);
            this.movies = resp;
          })
          .catch((err) => {
            this.$message.error(err.message);
          });
      } else {
        getAllMovies()
          .then((resp) => {
            this.movies = resp;
          })
          .catch((err) => {
            this.$message.error(err.message);
          });
      }
    },
    //点击搜索按钮进行搜索  回车进行触发该事件
    onSearch() {
      this.getMovies(this.searchValue);
    },
    onSearchMovies() {
      this.getMovies(this.searchValue);
      console.log("搜索成功");
    },
    // 查询全部数据
    getMovies(id) {
      getSearchMovies(id).then((resp) => {
        this.Movies = resp;
      });
    },
    // 根据类型查找菜品

    jumpToMovieDetail(data) {
      localStorage.setItem("movie", JSON.stringify(data));
      this.$router.push({ name: "dishesDetail", params: { movie: data } });
    },

    // 展示全部
    showAll() {},
    // 按折扣升序
    sortByDiscount() {
      //  this.getGoodsListBySaleNum()
    },
    // 按价格降序
    sortByPrice() {
      //  this.getGoodsListByPrice()
    },

    sortByDistance() {},
    selectMoviesByTypeId(id) {},
  },
  async created() {
    this.user_id = JSON.parse(localStorage.getItem("user")).id;
    this.recommend = JSON.parse(localStorage.getItem("recommend"));
    console.log(this.recommend);
    this.movies = this.recommend;
  },
};
</script>
<!-- scoped: 作用域，当前css只当前的组件生效-->
<style lang="less" scoped>
.recommend {
  margin-bottom: 100px;
}
// 菜品卡片
.dishes_card {
  text-align: left;
  margin: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.van-card__title {
  font-size: 14px;
  font-weight: 600;
}
.van-card__price {
  color: crimson;
}
.van-card__origin-price {
  margin-left: 5px;
}
// 店铺
.van-card__footer {
  border-top: 1px solid #ebeef5;
  text-align: left;
}
.shop_card {
  border-top: 1px solid #ebeef5;
  text-align: left;
  margin-top: 10px;
  padding-bottom: 5px;
}
.shop_title {
  font-size: 14px;
  font-weight: 600;
  margin-top: 5px;
}
.van-button {
  float: right;
  font-size: 10px;
  padding-left: 5px;
  padding-right: 5px;
  height: 20px;
}
</style>
../../api/movies

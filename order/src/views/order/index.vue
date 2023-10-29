<template>
  <div class="rating">
    <button class="btn" v-on:click="GenerateMovieRecommendations">
      生成推荐电影
    </button>
    <!-- 订单列表 -->
    <el-card
      v-for="rating in ratingList"
      :key="rating.id"
      :title="rating.name"
      :name="rating.name"
      class="orderCard"
      @click="ef(rating)"
    >
      <!-- <van-card
        v-for="movie in rating.movies"
        :key="movie.id"
        :name="movie.name"
        :title="movie.name + '平均打分为'"
        :thumb="require('../../poster/' + movie.imdb_id + '.jpg')"
        :price="rating.score"
        :num="rating.score"
        style="text-align: left"
        v-on:click="ef(rating)"
      > -->
      <div class="movie" v-for="movie in rating.movies" :key="movie.id">
        <div class="moviePhoto">
          <img
            width="60px"
            height="60px"
            fit="cover"
            :src="require('../../poster/' + movie.imdb_id + '.jpg')"
            style="border-radius: 8px; float: left; padding-left: 322px"
          />
        </div>
        <div class="movieInfo">
          <ul>
            <li>电影名：{{ movie.name }}</li>
            <li>评分：{{ movie.avg_rating }}</li>
            <li>类型：{{ movie.genre }}</li>
            <li>我的评分：{{ rating.score }}</li>
            <li>我的评论：{{ rating.comment }}</li>
          </ul>
        </div>
        <span style="float: right" v-on:click="jumpToMovieDetail(movie)"
          >查看电影详情 》</span
        >
      </div>
    </el-card>
  </div>
</template>
<script>
import { Card } from "element-ui";

import { getAllOrders, getOderDishes, getSearchOrder } from "../../api/order";

import { getRecommend } from "../../api/dishes";
import { showRatingByUserId } from "../../api/order";

export default {
  name: "Order",
  data() {
    return {
      userId: 2285159,
      active: "",
      orderList: [],
      dishes: [],
      ratingList: [],
    };
  },
  methods: {
    jumpToOrderDetail(data) {
      this.$router.push({ name: "orderDetail", params: { orderList: data } });
    },
    ef(rating) {
      console.log(rating);
    },
    jumpToMovieDetail(data) {
      localStorage.setItem("movie", JSON.stringify(data));
      this.$router.push({ name: "dishesDetail" });
    },
    GenerateMovieRecommendations() {
      console.log("this.user_id");
      getRecommend(this.user_id).then((resp) => {
        const recommend = resp;
        console.log("resp", resp);
        localStorage.setItem("recommend", JSON.stringify(recommend));
        console.log("recommend", recommend);
        this.$router.push({
          name: "recommend",
        });
      });
    },
    showRating(id) {
      showRatingByUserId(id).then((resp) => {
        this.ratingList = resp;
        localStorage.setItem("rating", JSON.stringify(this.ratingList));
        console.log(this.ratingList);
      });
    },
  },

  async created() {
    //得到当前用户全部订单
    this.user_id = JSON.parse(localStorage.getItem("user")).id;
    this.showRating(this.user_id);
    console.log(this.user_id);
  },
  components: { Card },
};
</script>
<!-- scoped: 作用域，当前css只当前的组件生效-->
<style lang="less" scoped>
// 订单卡片
.movie {
  padding: 0;
  margin: 10px;
  background-color: white;
  border-radius: 10px;
  flex: auto;
}

.movieInfo {
  max-width: 200px;
  margin-left: 50px;
  float: left;
  word-wrap: break-word;
  text-align: left;
}

.rating {
  margin-bottom: 100px;
}
.btn {
  background-color: #1795bb;
  border-radius: 12px;
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  -webkit-transition-duration: 0.4s;
  transition-duration: 0.4s;
  cursor: pointer;
}
.btn:hover {
  background-color: #fff;
  color: #1795bb;
  border: 1px solid #ccc;
}

.orderCard {
  margin: 5px;
  border-radius: 10px;
}
.orderCard_header {
  text-align: center;
  height: 20px;
  //    padding-top: 20px;
}
.order_footer {
  text-align: left;
  padding-bottom: 10px;
  padding-top: 10px;
  border-top: 1px solid #ebeef5;
  margin-left: 20px;
}
.order_button {
  margin-right: 20px;
  height: 25px;
}
.orderCard_title {
  font-size: 16px;
  font-weight: 600;
}
.orderCard_type {
  float: right;
  padding: 3px 10px;
  font-size: 12px;
}
/deep/ .el-card__body {
  padding-top: 0px;
  padding-left: 0px;
  padding-right: 0px;
}
// 图片
.van-card__thumb {
  height: 60px;
  width: 60px;
}
// 标题
.van-card {
  padding: 0;
  margin: 10px;
  background-color: white;
  border-radius: 10px;
}
.van-card__title {
  padding-top: 6px;
  font-size: 14px;
  font-weight: 600;
}
.van-card__content {
  min-height: 60px;
}
.van-card__bottom {
  line-height: 45px;
  padding-right: 10px;
}
// 标签
.van-tag {
  font-size: 9px;
  height: 13px;
  margin-left: 3px;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 100%;
}
</style>

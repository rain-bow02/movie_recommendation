<template>
  <div>
    <div class="orderDetail_top">
      <span>请为这部电影评分</span>
    </div>

    <div>
      <!-- 订单信息 -->
      <el-card class="orderDetail_card">
        <div slot="header" class="orderDetail_header">
          <span class="orderCard_title">{{ orderList.name }} </span>
        </div>

        <div class="orderDetail_body">
          <el-rate v-model="evaluate.score" show-text> </el-rate>
          <el-input
            style="margin-right: 20px; width: 240px"
            type="textarea"
            :rows="2"
            v-model="evaluate.comment"
            placeholder="说说你对这部电影的看法"
          >
          </el-input>
        </div>
        <div class="orderDetail_footer">
          <span style="font-size: 12px">故事情节</span>
          <span style="font-size: 12px; float: right; margin-right: 20px">
            <el-rate v-model="plot_score"> </el-rate
          ></span>
        </div>
        <div class="orderDetail_footer">
          <span style="font-size: 12px">视觉效果</span>
          <span style="font-size: 12px; float: right; margin-right: 20px">
            <el-rate v-model="visual_score"> </el-rate
          ></span>
        </div>
        <div class="orderDetail_footer">
          <span style="font-size: 12px">音乐效果</span>
          <span style="font-size: 12px; float: right; margin-right: 20px">
            <el-rate v-model="sound_score"> </el-rate
          ></span>
        </div>
        <div class="orderDetail_footer">
          <span style="font-size: 12px">角色塑造</span>
          <span style="font-size: 12px; float: right; margin-right: 20px">
            <el-rate v-model="actor_score"> </el-rate
          ></span>
        </div>
        <div class="orderDetail_footer">
          <span style="font-size: 12px">艺术价值</span>
          <span style="font-size: 12px; float: right; margin-right: 20px">
            <el-rate v-model="artistic_score"> </el-rate
          ></span>
        </div>
      </el-card>

      <!-- 配送信息 -->
    </div>
    <div class="toRate">
      <button class="btnToRate" v-on:click="submitEvaluate(evaluate)">
        提交评分
      </button>
    </div>
  </div>
</template>
<script>
import { getAllOrders, getOderDishes, submit } from "../../api/order";

export default {
  name: "OrderDetail", //组件名称
  data() {
    return {
      id: "",
      name: "永辉超市",
      evaluate: {
        movie_id: 0,
        user_id: 0,
        score: 0,
        comment: "好",
      },
      plot_score: null,
      visual_score: null,
      sound_score: null,
      actor_score: null,
      artistic_score: null,

      type: "已完成",
      order: {},
    };
  },
  methods: {
    submitEvaluate(evaluate) {
      this.evaluate.movie_id = this.orderList.id;
      this.evaluate.user_id = this.user_id;

      this.$alert("评价成功");
      console.log(evaluate);
      submit(evaluate);
      this.$alert("将要跳转到首页", {
        confirmButtonText: "确定",
        callback: (action) => {
          this.jumpToHomePage();
        },
      });
    },

    jumpToHomePage() {
      this.$router.push({ name: "dishes" });

      console.log("this.orderList");

      console.log(this.orderList);
      console.log("this.evaluate.movie_id");
      console.log(this.evaluate.user_id);
      console.log("evaluate");
      console.log(this.evaluate);
    },
  },
  async created() {
    this.orderList = JSON.parse(localStorage.getItem("movie"));
    this.user_id = JSON.parse(localStorage.getItem("user")).id;

    console.log(this.user_id);
    console.log(this.orderList.id);

    //得到菜种类
    // this.getAllDishes()
  },
};
</script>
<!-- scoped: 作用域，当前css只当前的组件生效-->
<style lang="less" scoped>
//顶部标题
.orderDetail_top {
  font-size: 18px;
  text-align: left;
  margin-left: 10px;
  margin-top: 5px;
  margin-bottom: 20px;
  font-weight: 600;
}
//评分卡片
.orderDetail_card {
  margin: 5px;
  border-radius: 10px;
}
.orderDetail_header {
  text-align: left;
  height: 20px;
}
.orderDetail_body {
  text-align: left;
  margin-left: 20px;
  margin-top: 5px;
}
.orderDetail_footer {
  text-align: left;
  padding-bottom: 10px;
  padding-top: 10px;
  border-top: 1px solid #ebeef5;
  margin-left: 20px;
}
.orderDetail_button {
  margin-right: 20px;
  height: 25px;
}
.orderDetail_title {
  font-size: 14px;
  font-weight: 600;
}
.orderDetail_type {
  float: right;
  padding: 3px 10px;
  font-size: 12px;
}
/deep/ .el-card {
  padding-right: 10px;
}
/deep/ .el-card__body {
  padding: 0px;
}
/deep/.el-card__header {
  padding: 10px;
}

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

.btnToRate {
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
.btnToRate:hover {
  background-color: #fff;
  color: #1795bb;
  border: 1px solid #ccc;
}
</style>

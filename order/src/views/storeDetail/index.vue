<template>
  <div>
    <!-- 展示店铺信息的标签 -->
    <div class="storeInfor">
      <div class="shop_card">
        <div style="float: left">
          <van-image :src="store.url" class="shop_image" />
        </div>

        <div class="shop_info_box">
          <span class="shop_title">{{ store.name }}</span>
          <br />
          <!-- 评分 -->
          <van-icon color="orange" name="star" />
          <van-tag round color="orange" type="primary">{{
            store.score
          }}</van-tag>
          <!-- 是否商家自配 -->
          <van-tag color="grey" v-if="true" round type="primary"
            >商家自配
          </van-tag>
          <br />
          <span style="font-size: 11px">{{ store.desc }}</span>
        </div>
        <span
          class="van-tag van-tag--plain van-tag--danger"
          style="float: left; margin: 5px"
          >买{{ store.disc }}减10</span
        >
        <span
          class="van-tag van-tag--plain van-tag--danger"
          style="float: left; margin: 5px"
          >买{{ store.disc }}减10</span
        >

        <!-- 优惠券单元格 -->
        <van-coupon-cell
          :coupons="coupons"
          :chosen-coupon="chosenCoupon"
          @click="showDiscount(store.id)"
        />
        <!-- 优惠券列表 -->
        <van-popup
          v-model="showList"
          round
          position="bottom"
          style="height: 90%; padding-top: 4px"
        >
          <van-coupon-list
            v-for="c in coupons"
            :key="c.id"
            :coupons="coupons"
            :chosen-coupon="c.chosenCoupon"
            :disabled-coupons="c.disabledCoupons"
            @change="onCouponChange"
            @exchange="onExchange"
          />
        </van-popup>
      </div>
    </div>
    <!-- 展示分类信息 -->
    <div class="category">
      <van-sidebar v-model="activeKey" @change="onChange">
        <van-sidebar-item
          v-for="type in types"
          :key="type.id"

          :title="type.name"
          @click="getCurrentTypeDishes(type.id)"
        />
      </van-sidebar>

      <div class="dishesDtail">
        <!-- <router-view /> -->
        <van-list
          v-model="loading"
          :finished="finished"
          finished-text="没有更多了"
          @load="onLoad"
        >
          <!-- :desc="Dish.desc"绑定不了 -->
          <van-card
            v-for="dish in dishes"
            :key="dish.id"
            :desc="dish.desc"
            :title="dish.name"
            :thumb="dish.imag"
            style="text-align: left"
            @click="jumpToDishesDetail()"
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
                >￥{{ dish.price }}</span
              >
 
              <span
                style="
                  margin: 0px;
                  width: 80px;
                  margin-left: 10px;
                  font-size: 10px;
                  height: 20px;
                  color: #646566;
                "
                >{{ dish.sales }}
              </span>
              <br />
              <!-- 添加到购物车 -->
              <van-goods-action-icon
                class="add-icon"
                style="float: right; color: crimson"
                icon="add-o"
                @click="addToCart(dish)"
              />

              <van-tag plain type="danger" style="margin-top: 6px">{{
                dish.disc
              }}</van-tag>

              <!-- <van-tag plain type="danger" style="margin-top: 6px;">59减8</van-tag> -->
            </template>
          </van-card>
        </van-list>
      </div>
    </div>
    <!-- 底部购物车 -->
    <div class="cart">
      <!-- 底部提交订单栏 -->'
      <div class="submit">
        <van-submit-bar
          :price="money * 100"
          button-text="提交订单"
          @submit="
            onSubmit(
              252,
              '重庆市渝北区',
              1.5,
              store.time,
              store.score,
              0,
              2285159,
              store.name,
              money - 1.5
            )
          "
        >
          <van-goods-action-icon
            class="cart-icon"
            icon="cart-o"
            text="购物车"
            @click="onClickIcon"
            :badge="cartNum"
          />
        </van-submit-bar>
      </div>

      <!-- 购物车面板 -->
      <van-action-sheet v-model="show" title="已选择菜品">
        <div class="selectedDishes">
          <van-list
            v-model="loading"
            :finished="finished"
            finished-text="没有更多了"
            @load="onLoad"
          >
            <!-- 选中的菜品列表 -->
            <van-card
              v-for="dish in cart"
              :key="dish.id"
              :desc="dish.desc"
              :price="dish.price"
              :title="dish.name"
              :thumb="dish.imag"
              style="text-align: left"
            >
              <template #footer>
                <van-stepper
                  theme="round"
                  button-size="20"
                  disable-input
                  min="1"
                  @change="dishesNumChange(dish)"
                />
              </template>
            </van-card>
          </van-list>
        </div>
      </van-action-sheet>
    </div>
  </div>
</template>


<script>
import { getStoreDishesType ,getStoreDisc, getStoreDishes, getStoreTypeDishes} from "../../api/home";
import { submit } from "../../api/order";
import Vue from "vue";
import { Sidebar, SidebarItem } from "vant";
import { List } from "vant";
import { Card } from "vant";
import { Collapse, CollapseItem } from "vant";
import { Tag } from "vant";
import { Icon } from "vant";
import { CouponCell, CouponList } from "vant";
import { SubmitBar } from "vant";

Vue.use(SubmitBar);
Vue.use(CouponCell);
Vue.use(CouponList);

Vue.use(Icon);
Vue.use(Tag);
Vue.use(Collapse);
Vue.use(CollapseItem);
Vue.use(Card);
Vue.use(List);
Vue.use(Sidebar);
Vue.use(SidebarItem);

const coupon={
        available: 1,
        condition: "无使用门槛\n最多优惠12元",
        reason: "",
        value: 1,
        name: "优惠券名称",
        startAt: 1489104000,
        endAt: 1514592000,
        valueDesc: "1.5",
        unitDesc: "元",
      }

import { mapState, mapMutations,} from "vuex";

export default {
  namespaced: true,
  name: "StoreDetail", //组件名称
  data() {
    return {
      money: 0,
      cartNum: 0, //购物车物品数量
      /**
       * 店铺信息展示
       */
      store: {},
      detailInfo: "hhhhh",
      activeNames: ["1"],
      /**
       *优惠券选择
       **/
      showList: false,
      chosenCoupon: 0,
      chosenCoupon: -1,
      coupons: [coupon],

      disabledCoupons: [coupon],
      /*
        分类信息切换
      */
      activeKey: 0,
      //接收服务器传过来的店铺对应分类数组
      types: [],
      //接收服务器传过来的店铺分类下商品列表信息
      dishes: [],
      cart: [],
      //默认加载过程中不触发load事件，加载完触发
      loading: false,
      //是否加载完毕isEnd
      finished: true,
      /*
      底部购物车数据处理
      */
      show: false,
      /*
       */
    };
  },
  computed: {
    ...mapState({
      // cart: (state) => state.storeDetail.items,
      total: (state) => state.storeDetail.total,
    }),
  },
  methods: {
    //加载Vuex里所有的方法
    ...mapMutations("storeDetail", [
      // "modifyGoodsNum",
      "totalMoney",
    ]),
    getInCategoryDishesDetail() {},
    //加载分类下的商品
    onLoad() {},
    onChange(index) {},
    //商品跳转详情
    jumpToDishesDetail() {

    },
    //显示所有优惠券
    showDiscount(shopId){
      this.showList = true
      getStoreDisc(shopId).then((data) => {
      });
    },
    onCouponChange(index) {
      this.showList = false;
      this.chosenCoupon = index;
   
    },
    onExchange(code) {
      this.coupons.push(coupon);
    },
    //提交方法
    onSubmit() {},
    //点击购物车图标显示购物车详情
    onClickIcon() {
      this.show = !this.show;
    },
    // 加入购物车
    addToCart(data) {
      this.cart.push(data);
      this.cartNum = this.cartNum + 1;
      this.money = this.money + data.price;
    },
    // 增加、减少菜数量
    dishesNumChange(item) {
      this.totalMoney(item, item);
      this.money = this.total;
    },
    // 得到所有的菜的种类
    getAllDishesType(shopId) {
      getStoreDishesType(shopId).then((resp) => {
        // this.types = resp.data;
        this.types=resp;
        this.types.unshift({
          id:1,
          name:"全部",
          shopid:shopId,
        });
      });
    },
    // 得到所有的菜
    getAllDishes(shopId) {
      getStoreDishes(shopId).then((resp) => {
        this.dishes = resp.data;
      });
    },
    //得到当前种类所有的菜
    getCurrentTypeDishes(typeId){
      if (typeId != 1) {
        getStoreTypeDishes(typeId).then((resp) => {
        this.dishes=resp;
      });
      } else {
        getStoreDishes(this.store.id).then((resp) => {
        this.dishes = resp;
      });
      }
    },
     //提交方法
     onSubmit(addressId, address, disc, time, score, type, userId, name, price) {
      submit(
        addressId,
        address,
        disc,
        time,
        score,
        type,
        userId,
        name,
        price
      ).then((resp) => {

        console.log(resp.data);
      });
      alert("订单提交成功！");
    },

  },

  async created() {
    // this.goods = await getDetail(this.$route.params);
    this.store=this.$route.params.store
    //得到菜种类
    this.getAllDishesType(this.store.id);
    //得到全部的菜
    getStoreDishes(this.store.id).then((resp) => {
        this.dishes = resp;
      });
  },

};
</script>
<!-- scoped: 作用域，当前css只当前的组件生效-->
<style lang="less" scoped>
// 店铺信息
.shop_image {
  width: 60px;
  height: 60px;
  text-align: left;
  margin: 10px;
  border-radius: 5px;
}
.shop_info_box {
  text-align: left;
  padding-top: 10px;
}
.shop_title {
  margin-top: 10px;
  font-weight: 600;
}

// 优惠券
.van-cell--clickable {
  border: 1px solid #ebeef5;
}

// 商品样式
.van-image {
  width: 70px;
  height: 70px;
}
.van-card__title {
  font-size: 14px;
  font-weight: 600;
}
.van-card {
  padding-left: 0;
}
.van-card__thumb{
  width: 70PX;
  height: 70px;
  padding-right: 5px;

}
.category {
  display: flex;
}
.dishesDtail {
  flex: 1;
}
.van-card__thumb {
  margin: 0;
}
.add-icon {
  width: 45px;
  height: 45px;
}
//购物车样式
.van-popup {
  height: 55%;
}
.submit {
  z-index: 2000;
}
// .submitBtn {
//   // flex: 1;
// }
</style>

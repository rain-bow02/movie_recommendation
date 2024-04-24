<template>
  <div class="personal-center">
    <div class="personal_photo">
      <!-- 头像 -->
      <van-image
        round
        width="60px"
        height="60px"
        style="margin-top: 10px; margin-bottom: 10px"
        :src="previewImage"
      /><br />
      <span>{{ user.name }}</span>
      <!-- <span click="openFile"></span> -->
      <!-- <input type="file" @change="handleFileChange" accept="image/*" /> -->
    </div>

    <el-card class="personal_card">
      <div slot="header" class="personal_header">
        <span class="personal_title">评分记录</span>
        <button @click="showRating" style="float: right">></button>
      </div>
    </el-card>
    <el-card class="personal_card">
      <div slot="header" class="personal_header">
        <span class="personal_title">收藏夹</span>
        <button @click="showRating" style="float: right">></button>
      </div>
    </el-card>
    <el-card class="personal_card">
      <div slot="header" class="personal_header">
        <span class="personal_title">历史记录</span>
        <button @click="showRating" style="float: right">></button>
      </div>
    </el-card>
    <!-- 分割线 -->
    <!-- <van-divider /> -->

    <!-- 退出登录 -->
    <div>
      <van-button class="login_button" type="danger" @click="quit"
        >退出登录</van-button
      >
    </div>
  </div>
</template>
<script>
import { Upload } from "element-ui";
import { Button, Image as VanImage, CouponCell, CouponList } from "vant";
import { Grid, GridItem } from "vant";
import { showRatingByUserId } from "../../api/order";
export default (await import("vue")).defineComponent({
  name: "Personal", //组件名称
  data() {
    return {
      showList: false,
      rating: [],
      previewImage:
        "https://image.tianjimedia.com/uploadImages/2016/133/18/14B9WJ5PH12U_600.jpg",
    };
  },
  methods: {
    onChange(index) {
      this.showList = false;
      this.chosenCoupon = index;
    },
    onExchange(code) {
      this.coupons.push(coupon);
    },
    quit() {
      this.$router.push({ name: "login" });
    },
    jumpToAddressList() {
      this.$router.push({ name: "addressList" });
    },
    //改变头像
    handleFileChange(event) {
      //预览头像
      const file = event.target.files[0];
      if (file && file.type.match(/^image\//)) {
        const reader = new FileReader();
        reader.onload = () => {
          this.previewImage = reader.result; // 将读取的数据URL赋值给预览图像的src属性
        };
        console.log(file);
        reader.readAsDataURL(file); // 将文件读取为数据URL
      }
      //
      submitupdataImg(file);
    },
    async submitupdataImg(file) {
      const forms = new FormData();
      forms.append("file", file);
      const result = await uploadimage(forms, this.user.id);
      if (result.status === 200) {
        console.log("上传图片成功");
      } else {
        alert("上传图片失败");
      }
    },
    showRating() {
      showRatingByUserId(this.user.id).then((resp) => {
        this.rating = resp;
        localStorage.setItem("rating", JSON.stringify(this.rating));
        console.log(this.rating);
      });
      this.$router.push({ name: "order" });
    },
  },
  async created() {
    // const id=this.$route.params.userid
    this.user = JSON.parse(localStorage.getItem("user"));
    console.log("拿到的用户id没", this.user.id);
  },
});
</script>
<!-- scoped: 作用域，当前css只当前的组件生效-->
<style lang="less" scoped>
// 卡片
.personal_card {
  margin: 5px;
  margin-top: 20px;
  border-radius: 10px;
}
.personal_header {
  text-align: left;
  height: 20px;
}

/deep/ .el-card__body {
  padding: 0;
}
/deep/.el-card__header {
  padding: 10px;
}

.van-cell {
  margin-top: 20px;
  border: 1px solid #ebeef5;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.van-cell__title,
.van-cell__value {
  text-align: center;
}
/deep/ .van-field__body {
  display: none;
}
/deep/ .van-button--plain.van-button--danger {
  display: none;
}
/deep/ .van-coupon-list__bottom {
  display: none;
}
/deep/ .van-coupon__corner {
  display: none;
}

// 地址
.address {
  margin: 5px;
  margin-top: 20px;
  border-radius: 10px;
  height: 45;
  width: 300;
  border: 1px solid #ebeef5;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  padding: 10px;
}

// 登录
.login_button {
  margin-top: 30px;
  height: 40px;
  width: 150px;
  border-radius: 10px;
}
</style>

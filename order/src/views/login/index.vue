<template>
  <div>
    <van-nav-bar
      :title="$route.meta.title"
      right-text="注册账号"
      right-arrow
      @click-right="register"
      :fixed="true"
      :placeholder="true"
    />
    <br /><br /><br /><br /><br />
    <div class="el-icon-video-camera-solid" style="font-size: 50px"></div>
    <p>电影推荐系统</p>
    <br /><br />
    <van-form @submit="onSubmit">
      <van-field
        v-model="name"
        name="账号"
        label="账号"
        placeholder="账号"
        :rules="[{ required: true, message: '请填写账号' }]"
      />
      <van-field
        v-model="password"
        type="password"
        name="密码"
        label="密码"
        placeholder="密码"
        :rules="[{ required: true, message: '请填写密码' }]"
      />
      <div style="margin: 16px">
        <van-button round block type="info" native-type="submit"
          >登录</van-button
        >
      </div>
    </van-form>
  </div>
</template>

<script>
import { Form } from "vant";
import { Field } from "vant";
import axios from "axios";
import { showRatingByUserId } from "../../api/order";
export default {
  name: "Login", //组件名称
  data() {
    return {
      name: "",
      password: "",
      // pattern: /^1\d{10}$/,
    };
  },
  methods: {
    // 登录方法
    onSubmit() {
      console.log("submit");
      axios({
        method: "post",
        url: "http://localhost:8081/user/login",
        params: { name: this.name, password: this.password },
      })
        .then((resp) => {
          //resp:后端传回的接口
          console.log("resppppppp", resp);
          let user = resp.data;
          let status = resp.data.code;
          // console.log("用户",user)
          // console.log("水水水水水水水",status)
          console.log("用户", user);
          console.log("用户的id", user.data.id);
          if (status == 200) {
            localStorage.setItem("user", JSON.stringify(user.data));
            const userInfo = JSON.parse(localStorage.getItem("user"));
            const username = userInfo.name;
            const userid = userInfo.id;
            console.log(username);
            this.$router.push({
              name: "dishes",
            });
            console.log(user.data.id);
          } else {
            alert(resp.data.msg);
          }
        })
        .catch((error) => {
          alert(error);
          alert("与服务器连接异常");
        });
    },

    // 注册方法
    register() {
      this.$router.push({ name: "register" });
    },

    showRating(userid) {
      showRatingByUserId(userid);
    },
  },
};
</script>
<!-- scoped: 作用域，当前css只当前的组件生效-->
<style lang="less" scoped></style>

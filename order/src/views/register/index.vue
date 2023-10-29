<template>
  <div>
    <van-form validate-first @failed="onFailed" @submit="onSubmit">
      <br /><br /><br /><br /><br />
      <div class="el-icon-video-camera-solid" style="font-size: 50px"></div>
      <p>电影推荐系统</p>
      <br /><br />
      <!--昵称  -->
      <van-field
        v-model="userName"
        name="用户名"
        placeholder="用户名"
        :rules="[{ required: true, message: '请输入用户名' }]"
      />

      <!-- 11位用户账号 -->
      <!-- 通过 pattern 进行正则校验 -->
      <van-field
        v-model="email"
        name="邮箱"
        placeholder="邮箱"
        :rules="[{ required: true, message: '请输入邮箱' }]"
      />
      <!-- 注册密码 -->
      <van-field
        v-model="password"
        name="密码"
        placeholder="密码"
        :rules="[{ required: true, message: '请输入密码' }]"
      />
      <div style="margin: 16px">
        <van-button round block type="info" native-type="submit"
          >注册</van-button
        >
      </div>
    </van-form>
  </div>
</template>
<script>
import { Toast } from "vant";
import axios from "axios";
export default {
  name: "Register", //组件名称
  data() {
    return {
      userName: "",
      email: "",
      password: "",
      defaultImg:
        "https://images.pexels.com/photos/17156528/pexels-photo-17156528.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
      pattern: /^1\d{10}$/,
    };
  },
  methods: {
    onSubmit() {
      console.log("submit");
      let user = {
        name: this.userName,
        title: this.defaultImg,
        email: this.email,
        password: this.password,
      };
      console.log(user);
      console.log(JSON.stringify(user));
      axios({
        method: "post",
        url: "http://127.0.0.1:8081/user/register",
        data: user,
      })
        .then((resp) => {
          //resp:后端传回的接口
          console.log(resp);
          let user = resp.data;
          let status = resp.data.code;
          if (status == 200) {
            this.$router.push({
              name: "login",
              params: { user: user },
            });
          } else {
            alert(resp.data.msg);
          }
        })
        .catch((error) => {
          alert(error);
          alert("新增失败");
        });
    },
    onFailed(errorInfo) {
      console.log("failed", errorInfo);
    },
  },
};
</script>
<!-- scoped: 作用域，当前css只当前的组件生效-->
<style lang="less" scoped></style>

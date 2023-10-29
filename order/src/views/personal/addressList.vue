<template>
    <div>
        <van-address-list
            v-model="chosenAddressId"
            :list="list"
            @add="onAdd"
            @edit="onEdit"
            add-button-text="新增地址"
        />
    </div>
</template>
<script>
import { Toast } from 'vant';
export default {
  name:'AddressList', //组件名称
  data() {
    return {
      chosenAddressId: '1',
      list: [
        {
          id: '1',
          name: '张三',
          tel: '13000000000',
          address: '浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室',
          isDefault: true,
        },
        {
          id: '2',
          name: '李四',
          tel: '1310000000',
          address: '浙江省杭州市拱墅区莫干山路 50 号',
        },
      ],
    };
  },
  methods: {
    onAdd() {
      this.$router.push({name:'addressDetail'})
    },
    onEdit(item, index) {
      this.$router.push({name:'addressDetail'})
    },
  },
};
</script>
<!-- scoped: 作用域，当前css只当前的组件生效-->
<style lang="less" scoped>
.van-address-list__add{
    margin-bottom: 15px;
}
</style>

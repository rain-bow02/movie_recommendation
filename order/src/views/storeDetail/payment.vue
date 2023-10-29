<template>
    <div>
        <el-card  
        class="address_card" 
        @click="jumpToOrderDetail()"
        >
        <div slot="header" class="orderDetail_header">
            <span class="orderDetail_title">配送地址</span>
        </div>
        <van-address-list
            v-model="chosenAddressId"
            :list="addressList"

            @edit="onEdit"
        />
    </el-card > 
    <el-card  
        class="orderCard" 
        @click="jumpToOrderDetail()"
        >
            <div slot="header" class="orderCard_header" style="text-align: left; ">
                <span class="orderCard_title">永辉超市 </span>                
            </div>
            <van-card
                title="永辉超市"
                thumb="https://img01.yzcdn.cn/vant/ipad.jpeg"
                style="text-align: left;"
                price="19.00"
                num="1"
                @click="jumpToOrderDetail()"
            />
           
             
            <div  class="order_footer" style="  border-bottom: 1px solid #EBEEF5;">
                <span style="font-size: 12px; ">打包费</span>
                <span style="font-size: 12px;float: right; margin-right: 20px;">￥5</span>
            </div>
               <!-- 优惠券 -->
               <van-coupon-cell
                :coupons="coupons"
                :chosen-coupon="chosenCoupon"
                @click="showList = true"
                />
                <!-- 优惠券窗 -->
                <van-popup
                v-model="showList"
                round
                position="bottom"
                style="height: 90%; padding-top: 4px;"
                >
                <van-coupon-list
                    :coupons="coupons"
                    :chosen-coupon="chosenCoupon"
                    :disabled-coupons="disabledCoupons"
                    @change="onChange"
                    @exchange="onExchange"
                />
                </van-popup> 
            <div  class="order_footer" >
                <span style="font-size: 15px; float: right; font-weight:530;margin-right: 20px;">小计￥39</span>
                <span style="font-size: 15px; float: right; color: crimson;margin-right: 10px;">￥39</span>
                <span style="font-size: 15px; float: right; ">已优惠</span>

            </div>
        </el-card >
                   
    </div>
    
</template>

<script>
import { Toast } from 'vant';
const coupon = {
    available: 1,
    condition: '无使用门槛\n最多优惠12元',
    reason: '',
    value: 150,
    name: '优惠券名称',
    startAt: 1489104000,
    endAt: 1514592000,
    valueDesc: '1.5',
    unitDesc: '元',
    };
export default{
    name:'StoreDetail', //组件名称 
    data(){
        return{
            chosenAddressId: '1',
            chosenCoupon: -1,
            coupons: [coupon],
            disabledCoupons: [coupon],
            showList:false,
            addressList: [
                {
                id: '1',
                name: '张三',
                tel: '13000000000',
                address: '浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室',
                isDefault: true,
                },
            ],

        }
    },
    methods:{
        onEdit() {
            this.$router.push({name:'addressList'})
        },
        onChange(index) {
            this.showList = false;
            this.chosenCoupon = index;
        },
        onExchange(code) {
            this.coupons.push(coupon);
        },
    },
}

</script>
<!-- scoped: 作用域，当前css只当前的组件生效-->
<style lang="less" scoped>
// 名片

/deep/ .el-card{
    padding-right: 10px;
}
/deep/ .el-card__body{
    padding-top:0px ;
    padding-left: 0px;
    padding-right: 0px;
}
/deep/.el-card__header{
    padding:10px ;
}
.orderDetail_title{
    font-size: 14px;
}
// 地址栏
.address_card{
    margin:5px;
    border-radius:10px;
}
.van-address-list{
    padding: 0;
}
.van-address-item__name{
    font-size: 14px;

}
.van-address-item__address{
    font-size: 12px;
}
/deep/ .van-radio__icon,.van-button--block {
    display: none;
}
//编辑图标
.van-address-item__edit {
  top: 60%;
  font-size: 25px;
  right:0;
}
// 订单卡片
.orderCard{
    margin:5px;
    border-radius:10px;
}
.orderCard_header{
    text-align: center;
    height: 20px;
//    padding-top: 20px;

} 
.order_footer{
    text-align: left; 
    padding-bottom: 10px;
    padding-top: 10px;
    border-top: 1px solid #EBEEF5;
    margin-left: 20px;
}
.van-card__thumb{
    height: 60px;
    width: 60px;
}
.van-card{
    padding: 0;
    margin: 10px;
    background-color: white;
    border-radius:10px;

}
.van-card__title{
    padding-top: 6px;
    font-size: 14px;
    font-weight:600;
}
.van-card__content{
    min-height:60px;
}
.van-card__bottom {
  line-height: 45px;
  padding-right: 10px;
}
// 标签
.van-tag{
    font-size: 9px;
    height: 13px;
    margin-left: 3px;
}

</style>
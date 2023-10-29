import Home from '../views/home/index'
import Tabbar from '../components/tab-bar/index'
import Navbar from '../components/nav-bar/index'
import Dishes from '../views/dishes/index'
import Order from '../views/order/index'
import Personal from '../views/personal/index'
import StoreDetail from '../views/storeDetail/index'
import DishesDetail from '../views/dishesDetail/index'
import OrderDetail from '../views/orderDetail/index'
import Payment from '../views/storeDetail/payment'
import AddressList from '../views/personal/addressList'
import AddressDetail from '../views/personal/addressDetail'
import Login from '../views/login/index'
import Register from '../views/register/index'
import Recommend from '../views/recommend/index'

const routes =[
    {
        path:'/',
        redirect:'/login'
    },
    {
        path:'/login',
        name:'login',
        components: {
            default:Login,
        },
        meta:{
            icon:'wap-home-o',
            title:'登录',
            // inTabbar: true ,
        }
    },
    {
        path:'/register',
        name:'register',
        components: {
            header: Navbar,
            default: Register,
        },
        meta:{
            icon:'wap-home-o',
            title:'注册',
            inTabbar: true ,
        }
    },
    {
        path:'/home',
        name:'home',
        components:{
            //header: Navbar, //告知该路由头部应该展示那个组件
            default:Home,
            footer:Tabbar,
        },
        meta:{ //路由其它基本信息 
            //这里面的其它信息都可以进行在定义。
            icon:'wap-home-o',
            title:'首页',
            inTabbar: true ,
        }
    },
    {
        path:'/dishes',
        name:'dishes',
        components:{
           
            default:Dishes,
            footer:Tabbar,
        },
        meta:{ 
            icon:'wap-home-o',
            title:'电影',
            inTabbar: false ,
        }
    },
    {
        path:'/order',
        name:'order',
        components:{
            //header: NavBar //告知该路由头部应该展示那个组件
            header:Navbar,
            default:Order,
            footer:Tabbar,
        },
        meta:{ 
            icon:'wap-home-o',
            title:'评价记录',
            inTabbar: true ,
        }
    },
    {
        path:'/personal',
        name:'personal',
        components:{
            //header: NavBar //告知该路由头部应该展示那个组件
            header:Navbar,
            default:Personal,
            footer:Tabbar,
        },
        meta:{ //路由其它基本信息 
            //这里面的其它信息都可以进行在定义。
            icon:'wap-home-o',
            title:'个人中心',
            inTabbar: true ,
        }
    },
    {
        path:'/storeDetail',
        name:'storeDetail',
        components:{
            //header: NavBar //告知该路由头部应该展示那个组件
            header:Navbar,
            default:StoreDetail,
           footer:Tabbar,
        },
        meta:{ //路由其它基本信息 
            //这里面的其它信息都可以进行在定义。
            title:'店铺详情页面',
            inTabbar: true ,
        }
    },
    {
        path:'/dishesDetail',
        name:'dishesDetail',
        components:{
            //header: NavBar //告知该路由头部应该展示那个组件
            header:Navbar,
            default:DishesDetail,
        //    footer:Tabbar,
        },
        meta:{ //路由其它基本信息 
            //这里面的其它信息都可以进行在定义。
            title:'电影详情页面',
            inTabbar: true ,
        }
    },
    {
        path:'/orderDetail',
        name:'orderDetail',
        components:{
            //header: NavBar //告知该路由头部应该展示那个组件
            header:Navbar,
            default:OrderDetail,
        //    footer:Tabbar,
        },
        meta:{ //路由其它基本信息 
            //这里面的其它信息都可以进行在定义。
            title:'评价详情页面',
            inTabbar: true ,
        }
    },
    {
        path:'/payment',
        name:'payment',
        components:{
            //header: NavBar //告知该路由头部应该展示那个组件
            header:Navbar,
            default:Payment,
        //    footer:Tabbar,
        },
        meta:{ //路由其它基本信息 
            //这里面的其它信息都可以进行在定义。
            title:'提交订单',
            inTabbar: true ,
        }
    },
    {
        path:'/addressList',
        name:'addressList',
        components:{
            //header: NavBar //告知该路由头部应该展示那个组件
            header:Navbar,
            default:AddressList,
        //    footer:Tabbar,
        },
        meta:{ //路由其它基本信息 
            //这里面的其它信息都可以进行在定义。
            title:'地址列表',
            inTabbar: true ,
        }
    },
    {
        path:'/addressDetail',
        name:'addressDetail',
        components:{
            //header: NavBar //告知该路由头部应该展示那个组件
            header:Navbar,
            default:AddressDetail,
        //    footer:Tabbar,
        },
        meta:{ //路由其它基本信息 
            //这里面的其它信息都可以进行在定义。
            title:'地址列表',
            inTabbar: true ,
        }
    },
    {
        path:'/recommend',
        name:'recommend',
        components:{
            //header: NavBar //告知该路由头部应该展示那个组件
            header:Navbar,
            default:Recommend,
           footer:Tabbar,
        },
        meta:{ //路由其它基本信息 
            //这里面的其它信息都可以进行在定义。
            title:'推荐电影',
            inTabbar: true ,
        }
    },
 
]
export default routes
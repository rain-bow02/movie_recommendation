import axios  from "axios";
import { Toast } from "vant"; //导入Tost组件

const baseURL = "http://localhost:8081";//所有请求的根地址
/**
 * 创建axios的实列，封装axios的请求方式
 */
const service = axios.create({
    baseURL,
    timeout:1000000,//请求超时时间
    // withCredentials:true,//是否跨域携带凭证：cookie token
})
//请求拦截器 封装
service.interceptors.request.use(config=>{
    //可以在请求头中携带token或者你的cookie
    //config.headers.token =  token ;

        //加载状态
        Toast.loading({
            message: '加载中...',
            duration: 0,
         });
    return config ;
},err=>{
    Toast.clear();
    return Promise.reject(err);
})
//响应拦截器
service.interceptors.response.use(response=>{
    Toast.clear(); //清楚加载效果
    if(response.status >= 200 && response.status <300){
        //console.log(response.data);
        const {code,data} = response.data;
        console.log(data)
        if(code === 200){
            return data ; //response.data.data
        }
    }
    //响应异常的配置
    const error = new Error("服务器繁忙，请稍后重试！");
    error.reason = response;
    Toast.fail({
        message:'对不起，今天不舒服.......'
    })
        return Promise.reject(error);
    },err=>{
        Toast.fail({
            message:'接口异常.......'
        }) 
        return Promise.reject(err);
    })
    //导出创建好的axios实列
export default service

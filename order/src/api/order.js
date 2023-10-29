import request from '../utils/request'
import API from './contants'

 //展示用户所有评分
export const showRatingByUserId = (id)=>request({
    url:API.API_Rating_ALL+"/"+id,
    method: 'GET',
})

//根据订单查询菜品
export const getOderDishes = (id)=>request({
    url:API.API_ORDER_DISHES+"/"+id,
    method: 'GET',
})

//订单状态展示
export const getSearchOrder = (id) => request({
    url: API.API_ODER_STATUS_SHOW + "/" + id,
    method: 'GET',
})

 //提交用户对一个电影的评分
 export const submit = ( evaluate) => request({
    url: API.API_RATING_SUBMIT,
    method: 'POST',
    data: 
        evaluate
    
})


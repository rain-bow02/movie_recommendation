import request from '../utils/request'
import API from './contants'

/**
 * 搜索店铺
 */
export const getSearchStores = (searchValue) => request({
    url: API.API_STORE_SEARCH + '/' + searchValue,
    method: 'GET',
    data: {
        searchValue,
    }
})
//得到所有店铺
export const showAll = (searchValue) => request({
    url: API.API_STORE_ALL,
    method: 'GET',
})
//得到所有菜品种类
export const getStoreDishesType = (shopId) => request({
    url: API.API_STORE_DISHES_TYPE + '/' + shopId,
    method: 'GET',
})

//得到所有优惠券
export const getStoreDisc = (shopId) => request({
    url: API.API_STORE_DISC + '/' + shopId,
    method: 'GET',
})

//根据类型查询现在店铺菜品
export const getStoreTypeDishes = (shopId) => request({
    url: API.API_STORE_TYPE_DISHES + '/' + shopId,
    method: 'GET',
})

// 查询店铺所有菜品
export const getStoreDishes = (shopId) => request({
    url: API.API_STORE_DISHES + '/' + shopId,
    method: 'GET',
})

/**
 * 排序店铺
 */
export const getSortedStore = (condition) => request({
    url:API.API_STORE_SORT+'/'+condition,
    method:'GET'
})


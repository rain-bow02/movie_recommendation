const API = {
    //获取搜索店铺数据
    API_STORE_SEARCH: '/shop/search',
    //店铺优惠券
    API_STORE_DISC: '/shop/currentShopDiscount',
    //店铺菜品分类列表
    API_STORE_DISHES_TYPE: '/shop/currentShopDishType',
    //店铺所有菜品
    API_STORE_DISHES: '/shop/currentShopDish',
    //店铺排序
    API_STORE_SORT: '/shop/indexSortByCondition',
    //显示所有店铺
    API_STORE_ALL: '/shop/indexAll',
    //根据类型查找当前店铺的所有菜
    API_STORE_TYPE_DISHES:'/shop/currentTypeDish',
    

    //展示用户所有订单
    API_Rating_ALL:'/rating/showRating',
    //根据订单查询菜品
    API_ORDER_DISHES:'/dishes/inOrderDishes',
    // 提交评分
    API_RATING_SUBMIT: '/rating/saveRating',
    //得到所有菜品
    API_Movies_ALL:'/movies/getAllMovies',
    //根据类型查找菜品、
    API_Movies_BY_TYPE:'movies/inTypeMovies',
    //根据状态查找订单
    API_ODER_STATUS_SHOW: '/order/showOrdersByStatus',

    API_USER_SHOW:'/user/userShow',
    //根据用户显示推荐
    API_RECOMMEND:'/movies/recommend',
//得到搜索的电影
API_GET_SEARCH_MOVIES:'movies/search'
 
}
export default API
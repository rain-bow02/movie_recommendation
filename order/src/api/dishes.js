import request from '../utils/request'
import API from './contants'

  //得到所有菜品
export const getAllMovies = (id)=>request({
    // url:API.API_STORE_SEARCH+"/"+id
    url:API.API_Movies_ALL
})

    //根据类型查找菜品、
export const getMoviesByType = (id)=>request({
    // url:API.API_STORE_SEARCH+"/"+id
    url:API.API_Movies_BY_TYPE+"/"+id
})

    //推荐
    export const getRecommend = (id)=>request({
        // url:API.API_STORE_SEARCH+"/"+id
        url:API.API_RECOMMEND+"/"+id
    })
    export const getSearchMovies= (searchValue)=>request({
        // url:API.API_STORE_SEARCH+"/"+id
        url:API.API_GET_SEARCH_MOVIES+"/"+searchValue,
        method: 'GET',
        data: {
            searchValue,
        }
    })


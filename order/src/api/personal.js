import request from '../utils/request'
import API from './contants'

/**
 * 展示用户信息
 */
export const get_user_show = (id) => request({
    method:'GET',
    url:API.API_USER_SHOW+"/"+id
})

export const uploadimage= (forms)=> request({
    method:'POST',
    url:API.API_UPLOAD_IAMGE,
})
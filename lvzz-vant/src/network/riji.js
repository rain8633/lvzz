
import {request} from './request.js'

export function addRiJi(userId,title,feelScore,content,pic,url) {
    return request({
        url:'/RiJi/AddRiJi',
        method:'post',
        params: {
            userId,
            title,
            feelScore,
            content,
            pic,
            url
        }
    })
}

export function queryAllRiJi(userId) {
    return request({
        url:'/RiJi/queryUserRiJiNoPage',
        method:'post',
        params: {
            userId,
        }
    })
}
import {request} from './request.js'

export function getHomeMultidata() {
    return request({
        url:'home/multidata'
    })
}

export function getHomeGoods(){
    return request({
        url:'/trip/queryHotTripList',
       
    });
}

export function getHomeGoodsByUserId(userId){
    return request({
        url:'/trip/queryUserHotTripList',
       params:{
           userId
       }
    });
}
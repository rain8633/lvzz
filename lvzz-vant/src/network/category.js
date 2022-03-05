import {request} from './request'

export function getCategory(){
    return request({
        url:'/category'
    });
}


export function getUserSubCategory(levelName,userId) {
    return request({
        url:'/trip/queryUserTripListByLevelNoPage',
        params:{
           levelName,
           userId
        }
    });
}

export function getSubCategory(levelName) {
    return request({
        url:'/trip/queryTripListByLevelNoPage',
        params:{
           levelName
        }
    });
}

export function getUserAllCategory(userId) {
    return request({
        url:'/trip/queryUserAllTripNoPage',
        params:{
            userId
        }
    });
}

export function getAllCategory() {
    return request({
        url:'/trip/queryAllTripNoPage',
    });
}
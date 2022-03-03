import {request} from './request'

export function getCategory(){
    return request({
        url:'/category'
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


export function getAllCategory() {
    return request({
        url:'/trip/queryAllTripNoPage',
    });
}
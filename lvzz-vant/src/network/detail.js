import {request} from './request.js'

export function getDetail(id){
   return request({
        url:'/detail',
        params:{
            id
        }
    });
}

export function getRank(id){
   return request({
        url:'/rank/queryRankByTripId/'+id,
        method:'post',
       
    });
}

export function AddRank(userId,tripId,score){
   return request({
        url:'/rank/addRank',
           params:{
            userId,
            tripId,
            score
        }
       
    });
}

//用商品推荐接口请求
export function getRecommend(id){
    return request({
        url:'/comment/getAllComment/'+id,
        method:'post'
    });
}

//用商品推荐接口请求
export function getUsers(id){
    return request({
        url:"/user/queryUserById/"+id,
        method:'post'
    });
}

//将服务器返回的杂乱数据整合; 抽离数据
//基本信息数据
export class Goods{  
    constructor(itemInfo,columns,services){
        this.title = itemInfo.title;
        this.desc = itemInfo.desc;
        this.newPrice = itemInfo.price;
        this.oldPrice = itemInfo.oldPrice
        this.discount = itemInfo.discountDesc;
        this.columns = columns;
        this.services = services;
        this.realPrice = itemInfo.lowNowPrice;
    }
}

//店铺信息数据

export class Shop{
    constructor(shopInfo){
        this.logo = shopInfo.shopLogo;
        this.name = shopInfo.name;
        this.fans = shopInfo.cFans;
        this.sells = shopInfo.cSells;
        this.score = shopInfo.score;
        this.goodsCount = shopInfo.cGoods;
    }
}

// 商品图片详细信息


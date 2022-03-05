import {request} from './request.js'

export function addOrder(userName, phone,tripName,address,totalPrice) {
    return request({
        url:'/order/addOrder1',
        method:'post',
        params: {
            userName,
            phone,
            tripName,
            address,
            totalPrice
        }
    })
}

export function findUserOrders(userId) {
    return request({
        url:'/order/findUserOrdersNoPage',
        params: {
            userId
        }
    })
}


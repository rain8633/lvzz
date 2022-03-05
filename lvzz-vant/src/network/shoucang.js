
import {request} from './request.js'

export function queryAllShouCang(userId) {
    return request({
        url:'/shouCang/queryMyShouCangNoPage',
        method: 'post',
        params: {
            userId
        }
    })
}

export function deleteshoucang(userId,tripId) {
    return request({
        url:'/shouCang/deleteShouCang',
        method: 'delete',
        params: {
            userId,
            tripId
        }
    })
}

export function addshoucang(userId,id) {
    return request({
        url:'/shouCang/addShouCang/'+userId+'/'+id,
        method: 'post',
    })
}
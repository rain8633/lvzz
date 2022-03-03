import {request} from './request.js'

export function queryUser(userName, password,code) {
    return request({
        url:'user/queryUser',
        method:'post',
        params: {
            userName,
            password,
            code
        }
    })
}

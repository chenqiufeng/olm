import request from '@/utils/request'

// 查询订单管理列表
export function listOrder(query) {
  return request({
    url: '/management/order/list',
    method: 'get',
    params: query
  })
}

// 查询订单管理详细
export function getOrder(id) {
  return request({
    url: '/management/order/' + id,
    method: 'get'
  })
}

// 新增订单管理
export function addOrder(data) {
  return request({
    url: '/management/order',
    method: 'post',
    data: data
  })
}

// 修改订单管理
export function updateOrder(data) {
  return request({
    url: '/management/order',
    method: 'put',
    data: data
  })
}

// 删除订单管理
export function delOrder(id) {
  return request({
    url: '/management/order/' + id,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询客户管理列表
export function listCustomer(query) {
  return request({
    url: '/management/customer/list',
    method: 'get',
    params: query
  })
}

// 查询客户管理详细
export function getCustomer(id) {
  return request({
    url: '/management/customer/' + id,
    method: 'get'
  })
}

// 新增客户管理
export function addCustomer(data) {
  return request({
    url: '/management/customer',
    method: 'post',
    data: data
  })
}

// 修改客户管理
export function updateCustomer(data) {
  return request({
    url: '/management/customer',
    method: 'put',
    data: data
  })
}

// 删除客户管理
export function delCustomer(id) {
  return request({
    url: '/management/customer/' + id,
    method: 'delete'
  })
}

// 查询客户列表
export function listCustomerAll(query) {
  return request({
    url: '/management/customer/listCustomerAll',
    method: 'get',
    params: query
  })
}

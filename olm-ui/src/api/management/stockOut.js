import request from '@/utils/request'

// 查询出库列表
export function listStockOut(query) {
  return request({
    url: '/management/stockOut/list',
    method: 'get',
    params: query
  })
}

// 查询出库详细
export function getStockOut(id) {
  return request({
    url: '/management/stockOut/' + id,
    method: 'get'
  })
}

// 新增出库
export function addStockOut(data) {
  return request({
    url: '/management/stockOut',
    method: 'post',
    data: data
  })
}

// 修改出库
export function updateStockOut(data) {
  return request({
    url: '/management/stockOut',
    method: 'put',
    data: data
  })
}

// 删除出库
export function delStockOut(id) {
  return request({
    url: '/management/stockOut/' + id,
    method: 'delete'
  })
}

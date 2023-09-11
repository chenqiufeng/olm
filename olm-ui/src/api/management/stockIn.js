import request from '@/utils/request'

// 查询入库列表
export function listStockIn(query) {
  return request({
    url: '/management/stockIn/list',
    method: 'get',
    params: query
  })
}

// 查询入库详细
export function getStockIn(id) {
  return request({
    url: '/management/stockIn/' + id,
    method: 'get'
  })
}

// 新增入库
export function addStockIn(data) {
  return request({
    url: '/management/stockIn',
    method: 'post',
    data: data
  })
}

// 修改入库
export function updateStockIn(data) {
  return request({
    url: '/management/stockIn',
    method: 'put',
    data: data
  })
}

// 删除入库
export function delStockIn(id) {
  return request({
    url: '/management/stockIn/' + id,
    method: 'delete'
  })
}

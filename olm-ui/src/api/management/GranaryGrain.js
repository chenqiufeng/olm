import request from '@/utils/request'

// 查询粮库和粮仓管理列表
export function listGranaryGrain(query) {
  return request({
    url: '/management/GranaryGrain/list',
    method: 'get',
    params: query
  })
}

// 查询粮库和粮仓管理详细
export function getGranaryGrain(id) {
  return request({
    url: '/management/GranaryGrain/' + id,
    method: 'get'
  })
}

// 新增粮库和粮仓管理
export function addGranaryGrain(data) {
  return request({
    url: '/management/GranaryGrain',
    method: 'post',
    data: data
  })
}

// 修改粮库和粮仓管理
export function updateGranaryGrain(data) {
  return request({
    url: '/management/GranaryGrain',
    method: 'put',
    data: data
  })
}

// 删除粮库和粮仓管理
export function delGranaryGrain(id) {
  return request({
    url: '/management/GranaryGrain/' + id,
    method: 'delete'
  })
}

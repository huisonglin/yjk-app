/**
 * name: agriknow.js
 * description: 农知汇服务器提供的服务
 * author: 徐磊
 * date: 2018-5-19
 */
import request from './request.js'
const url_microService = require('../config/config').url_microService; //（与config.js相对路径）
import Notify from '../dist/notify/notify';
class agriknow {
  constructor() {
    this._baseUrl = url_microService
    this._defaultHeader = { 'Content-Type': 'application/json' }
    this._request = new request
    this._request.setErrorHandler(this.errorHander)
  }

  /**
   * 统一的异常处理方法
   */
  errorHander(res) {
    Notify("网络异常！请联系管理员");
  }


  getRequest(url,data){
    return this._request.getRequest(this._baseUrl + url, data).then(res => res.data)
  }

  postRequest(url, data) {
    return this._request.postRequest(this._baseUrl + url, data).then(res => res.data)
  }
}
export default agriknow
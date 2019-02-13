//app.js
const url_microService = require('/config/config').url_microService; //（与config.js相对路径）
import agriknow from './common/agriknow.js'
App({
  agriknow: new agriknow(),
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    // 登录
    var that = this;
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        wx.request({
          url: url_microService +"app/member/loginByWxXcx",
          data: {
            code:res.code
          },
          header: {},
          method: 'GET',
          dataType: 'json',
          responseType: 'text',
          success: function(res) {
            console.log(res)
            wx.setStorageSync("simpleInfo", res.data.info)
            //that.globalData.simpleInfo = res.data.info
          },
          fail: function(res) {},
          complete: function(res) {},
        })
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,
    simpleInfo: null
  }
})
// pages/person_center/person_center.js
var userInfo = null;
const app = getApp()
import Toast from '../../dist/toast/toast';
import Notify from '../../dist/notify/notify';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:null
  },

  bindGetUserInfo:function(e){
    var that = this;
    var token = userInfo.token;
    var iv = e.detail.iv;
    var encryptedData = e.detail.encryptedData;
    app.agriknow.getRequest('/app/member/obtainUserInfo',{
        token: token,
        iv:iv,
        encryptedData:encryptedData
      }).then(res => {
        if(res.code == 0){
          console.log(res)
          wx.setStorageSync('simpleInfo', res.info)
        }
        that.setData({
          userInfo:res.info
        })
      })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    userInfo = wx.getStorageSync("simpleInfo")
    that.setData({
      userInfo: userInfo
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})
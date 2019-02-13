// pages/rent_detail/rent_detail.js
var userInfo = wx.getStorageSync("simpleInfo")
const app = getApp()
import Toast from '../../dist/toast/toast';
import Notify from '../../dist/notify/notify';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    detailInfo:[],
    distance:'',
    isSelectShow:true
  },
  reward:function(e){

  },
  call:function(e){
    
  },
  remind: function(e){
    this.setData({
      isSelectShow:false
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var distance = options.distance;
    var id = options.id;
    app.agriknow.getRequest('/app/info/detail',{
      id:id,
      infoType:1
    }).then(res => {
      console.log(res)
      that.setData({
        detailInfo:res.info,
        distance: distance
      })
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
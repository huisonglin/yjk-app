// pages/search/search.js
const url_microService = require('../../config/config').url_microService; //（与config.js相对路径）
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentValue: 60,
    modelItems: [],
    modelName:'',
    twoStageModelName:'',
    sepcName:''
  },
  onDrag(event) {
    this.setData({
      currentValue: event.detail.value
    });
  },
  seaarchDeviceType: function(){
    wx.navigateTo({
      url: '../search_detail/search_detail'
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: url_microService + '/app/deviceName/dict/getModelList',
      data: {},
      header: {},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        console.log(res)
        that.setData({
          modelItems:res.data.info
        })
      },
      fail: function (res) { },
      complete: function (res) { },
    })
  },
  intoSelect: function(e){
    console.log(e.currentTarget.dataset.name)
    this.setData({
      twoStageModelName: '',
      sepcName: '',
      modelName: e.currentTarget.dataset.name
    })
    wx.navigateTo({
      url: '../search_detail/search_detail?modelId='+e.currentTarget.dataset.id
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
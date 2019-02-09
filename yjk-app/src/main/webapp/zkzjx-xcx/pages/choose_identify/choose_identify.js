// pages/choose_identify/choose_identify.js
const app = getApp();
var userInfo = wx.getStorageSync("simpleInfo")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentIdentifyName: "",
    toWitchIdnetifyName: "",
    identity: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    // 1为我要出租，2为我要求租
    var identity = wx.getStorageSync('identity')
    if (identity == "2") {
      that.setData({
        currentIdentifyName: "我要出租",
        toWitchIdnetifyName: "我要求租",
        identity: 1
      })
    } else {
      that.setData({
        currentIdentifyName: "我要求租",
        toWitchIdnetifyName: "我要出租",
        identity: 2
      })
    }
  },
  swithcIdentify:function(e){
    console.log(e);
    var identity = e.currentTarget.dataset.identify;
    console.log(identity)
    var token = userInfo.token

    app.agriknow.getRequest('/app/member/chooseIdentify', {
      token: token,
      identify: identity
    }).then(res => {
      console.log(res)
      wx.setStorageSync('identity', identity);
      wx.reLaunch({
        url: '/pages/index/index',
      })
    })
  },
  back() {
    wx.navigateBack({
      delta: 1
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
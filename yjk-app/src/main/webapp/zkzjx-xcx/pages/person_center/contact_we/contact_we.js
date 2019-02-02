// pages/person_center/contact_we/contact_we.js
var userInfo = wx.getStorageSync("simpleInfo")
const app = getApp()
import Toast from '../../../dist/toast/toast';
import Notify from '../../../dist/notify/notify';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    feedback:''
  },
  bindinformation: function (e) {
     console.log(e.detail.value)
    this.setData({
      feedback: e.detail.value,
    })
  },
  call:function(){
    wx.makePhoneCall({
      phoneNumber: '18655740045',
      success(e) {
        // console.log(e)
      }
    })
  },
  submit:function(e){
    var that = this;
    var feedback = that.data.feedback;
    var token = userInfo.token
    console.log(feedback)
    if (feedback == ''){
      Notify("请输入反馈内容！")
      return;
    }
    app.agriknow.getRequest('/app/member/feedBack', {
      content: feedback,
      token: token,
    }).then(res => {
      console.log(res);
      Toast.success('提交成功');
      const timer = setInterval(() => {
        wx.navigateBack({
          delta: 1,
        })
        clearInterval(timer);
      },1000)

    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
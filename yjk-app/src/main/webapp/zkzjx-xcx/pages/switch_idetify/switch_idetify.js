const app = getApp();
var userInfo = null;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    identity: 0,
  },

  rent:function(e){
    var identity = e.currentTarget.dataset.identity;
    wx.setStorageSync('identity', 1)
    if (e != null && e.share_query != null) {
      wx.navigateTo({
        url: '/pages/index/index?share_query=' + '/pages/rent_detail/rent_detail&id='+e.id,
      })
    }else{
      wx.reLaunch({
        url: '/pages/index/index',
      })
    }


  },

  rental:function(e){
    var identity = e.currentTarget.dataset.identity;
    wx.setStorageSync('identity', 2)
    if (e != null && e.share_query != null) {
      wx.navigateTo({
        url: '/pages/index/index?share_query=' + '/pages/rental_detail/rental_detail&id=' + e.id,
      })
    } else {
      wx.reLaunch({
        url: '/pages/index/index',
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    userInfo = wx.getStorageSync("simpleInfo")
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
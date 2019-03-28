const app = getApp();
var userInfo = null;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    identity: 0,
    share_query:'',
    id:''
  },

  rent:function(e){
    var identity = e.currentTarget.dataset.identity;
    wx.setStorageSync('identity', 1)
    var share_query = this.data.share_query;
    var id = this.data.id;
    if (share_query != '') {
      wx.reLaunch({
        url: '/pages/index/index?share_query='+share_query+'&id='+id,
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
    var share_query = this.data.share_query;
    var id = this.data.id;
    if (share_query != '') {
      wx.reLaunch({
        url: '/pages/index/index?share_query=' + share_query +'&id=' + id,
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
    this.setData({
      share_query:options.share_query,
      id:options.id
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
// pages/search/search.js
const app = getApp();
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
    sepcName:'',
    address: '',
    addressDetail: '',
    latitude:'',
    longitude: '',
    show: {
      middle: false,
      top: false,
      bottom: false,
      right: false,
      right2: false
    },
    items:[],
    mainActiveIndex: '',
    modelId:'',
    activeId: '',
    twoStageModelId:'',
    specId:''
  },
  onDrag(event) {
    this.setData({
      currentValue: event.detail.value
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    console.log(wx.getStorageSync('twoStageModeId') + '------' + wx.getStorageSync('modeId'))
    that.setData({
      currentValue: wx.getStorageSync('distance') == null ? 60:wx.getStorageSync('distance'),
      modelId: wx.getStorageSync('modeId'),
      twoStageModelId: wx.getStorageSync('twoStageModeId'),
      specId: wx.getStorageSync('specId'),
      modelName: wx.getStorageSync('modelName'),
      twoStageModelName: wx.getStorageSync('twoStageModelName'),
      sepcName: wx.getStorageSync('sepcName'),
      longitude: wx.getStorageSync('longitude'),
      latitude: wx.getStorageSync('latitude'),
      address: wx.getStorageSync('address'),
      addressDetail: wx.getStorageSync('addressDetail'),    
    })
    app.agriknow.getRequest('/app/deviceName/dict/getModelList',null).then(res => {
      if(res.code == 0){
        console.log(res)
        that.setData({
          modelItems: res.info
        })
      }
    } )
  },
  toggle(type) {
    this.setData({
      [`show.${type}`]: !this.data.show[type]
    });
  },
  closePopup:function(){
    this.toggle('bottom');
  },
  intoSelect: function(e){
    this.toggle('bottom');
    var that = this;
    app.agriknow.getRequest('/app/deviceName/dict/getSubTypes', {
      modelId: e.currentTarget.dataset.id,
    }).then(res => {
      console.log(res)
      that.setData({
        items: res.info,
        modelName: e.currentTarget.dataset.name,
        modelId: e.currentTarget.dataset.id,
      })
      wx.setStorageSync('modelName', e.currentTarget.dataset.name)
    })
  },
  onClickNav({ detail }) {
    console.log(detail)
    this.setData({
      mainActiveIndex: detail.index || 0
    });
  },

  onClickItem({ detail }) {
    console.log(detail)
    var ts = detail.id.split("-");
    console.log(ts)
    this.setData({
      activeId: detail.id,
      specId:ts[0],
      sepcName: detail.text,
      twoStageModelName: ts[1],
      twoStageModelId: ts[2]
    });
    wx.setStorageSync('sepcName', detail.text)
    wx.setStorageSync('twoStageModelName', ts[1])
  },
  chooseAddress: function(e){
    var that = this
    wx.chooseLocation({
      success: function(res) {
        console.log(res)
        that.setData({
          address:res.name,
          addressDetail:res.address,
          longitude:res.longitude,
          latitude:res.latitude
        })
        wx.setStorageSync('address', res.name)
        wx.setStorageSync('addressDetail', res.address)
      },
    })
  },
  //开始搜索
  toSearch:function(){
    var that = this;
    var currentValue = that.data.currentValue*10;
    console.log(currentValue)
    var latitude = that.data.latitude;
    console.log(latitude)
    var longitude = that.data.longitude;
    console.log(longitude);
    var modeId = that.data.modelId;
    console.log(modeId)
    var twoStageModeId = that.data.twoStageModelId;
    console.log(twoStageModeId)
    var specId = that.data.specId;
    console.log(specId)
    wx.setStorageSync('distance', currentValue / 10);
    wx.setStorageSync('modeId', modeId);
    wx.setStorageSync('twoStageModeId', twoStageModeId);
    wx.setStorageSync('specId', specId)
    var pages = getCurrentPages(); // 获取页面栈
    var prevPage = pages[pages.length - 2]; // 上一个页面
 
    if (longitude != null && latitude != null){
      prevPage.setData({
        longitude: longitude,
        latitude: latitude,
      })
      wx.setStorageSync('longitude', longitude)
      wx.setStorageSync('latitude', latitude)
    }
    prevPage.setData({
      distance: currentValue,
      modeId: modeId,
      twoStageModeId: twoStageModeId,
      specId: specId,
      isRefresh:1
    })

    wx.navigateBack({
      delta: 1,
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
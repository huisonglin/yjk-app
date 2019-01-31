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
    mainActiveIndex: 0,
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
    this.setData({
      currentValue: wx.getStorageSync('distance'),
      modeId: wx.getStorageSync('modeId'),
      twoStageModeId: wx.getStorageSync('twoStageModeId'),
      specId: wx.getStorageSync('specId'),
      modelName: wx.getStorageSync('modelName'),
      twoStageModelName: wx.getStorageSync('twoStageModelName'),
      sepcName: wx.getStorageSync('sepcName'),
    })
    console.log(wx.getStorageSync('distance'))
    var that = this;
    app.agriknow.getRequest('/app/deviceName/dict/getModelList',null).then(res => {
      if(res.code == 0){
        console.log(res)
        that.setData({
          modelItems: res.info
        })
      }
    } )
    // wx.request({
    //   url: url_microService + '/app/deviceName/dict/getModelList',
    //   data: {},
    //   header: {},
    //   method: 'GET',
    //   dataType: 'json',
    //   responseType: 'text',
    //   success: function (res) {
    //     console.log(res)
    //     that.setData({
    //       modelItems:res.data.info
    //     })
    //   },
    //   fail: function (res) { },
    //   complete: function (res) { },
    // })
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
    // wx.request({
    //   url: url_microService + '/app/deviceName/dict/getSubTypes',
    //   data: {
    //     modelId: e.currentTarget.dataset.id,
        
    //   },
    //   header: {},
    //   method: 'GET',
    //   dataType: 'json',
    //   responseType: 'text',
    //   success: function (res) {
    //     console.log(res)
    //     that.setData({
    //       items: res.data.info,
    //       modelName: e.currentTarget.dataset.name,
    //       modelId: e.currentTarget.dataset.id,
    //     })

    //   },
    //   fail: function (res) { },
    //   complete: function (res) { },
    // })

    
    // console.log(e.currentTarget.dataset.name)
    // this.setData({
    //   twoStageModelName: '',
    //   sepcName: '',
    //   modelName: e.currentTarget.dataset.name
    // })
    // wx.navigateTo({
    //   url: '../search_detail/search_detail?modelId='+e.currentTarget.dataset.id
    // })
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
      },
    })
  },
  //开始搜索
  toSearch:function(){
    var currentValue = this.data.currentValue*10;
    console.log(currentValue)
    var latitude = this.data.latitude;
    console.log(latitude)
    var longitude = this.data.longitude;
    console.log(latitude);
    var modeId = this.data.modelId;
    console.log(modeId)
    var twoStageModeId = this.data.twoStageModelId;
    console.log(twoStageModeId)
    var specId = this.data.specId;
    console.log(specId)

    var pages = getCurrentPages(); // 获取页面栈
    var prevPage = pages[pages.length - 2]; // 上一个页面
    var that = this;
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
    wx.setStorageSync('distance', currentValue/10);
    wx.setStorageSync('modeId', modeId);
    wx.setStorageSync('twoStageModeId', twoStageModeId);
    wx.setStorageSync('specId', specId)
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
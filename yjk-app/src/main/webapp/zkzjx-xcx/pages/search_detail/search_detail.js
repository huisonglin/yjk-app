const url_microService = require('../../config/config').url_microService; //（与config.js相对路径）

var pages = getCurrentPages(); // 获取页面栈

var prevPage = pages[pages.length - 2]; // 上一个页面
Page({
  data: {
    items: [],
    mainActiveIndex: 0,
    activeId: 1,
    specName: '',
    twoStageModelName: ''
  },
  /**
 * 生命周期函数--监听页面加载
 */
  onLoad: function (options) {
    console.log(options)
    var that = this;
    wx.request({
      url: url_microService + '/app/deviceName/dict/getSubTypes',
      data: {
        modelId: options.modelId,
      },
      header: {},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        console.log(res)
        that.setData({
          items:res.data.info
        })

      },
      fail: function (res) { },
      complete: function (res) { },
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
    });

    var pages = getCurrentPages(); // 获取页面栈
    var prevPage = pages[pages.length - 2]; // 上一个页面

    prevPage.setData({
      sepcName: detail.text,
      twoStageModelName: ts[1],
    })
  },

  finish: function(e){

    wx.navigateBack({
      delta: 1
    })

  }
});

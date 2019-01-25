const url_microService = require('../../config/config').url_microService; //（与config.js相对路径）
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: ['a', 'b', 'c'],
    result: [],
    priceItmes:[],
    priceUnitItems:[]
  },

  onChange(event) {
    var name='';
    var id='';
    console.log(event)
    var names = event.detail;
    var priceUnits = this.data.priceUnitItems;
    var p = this.data.priceUnitItems;
    if (priceUnits.length > names.length){//移除操作
    console.log("jl")
      for(var i=0;i<names.length;i++){
        for (var j = 0; j < priceUnits.length;j++){
          console.log(names[i])
          console.log(priceUnits[j].name + "-" + priceUnits[j].id)
          if (names[i] == priceUnits[j].name + "-" + priceUnits[j].id){
            console.log(j)
            p.splice[j,1];
            console.log(priceUnits)
          }
        }
      }
      this.setData({
        priceUnitItems: p
      })
    }else{//新增操作
      for (var i = 0; i < names.length; i++) {
        var ni = names[i].split("-");
        this.setData({
          ["priceUnitItems[" + i + "].id"]: ni[1],
          ["priceUnitItems[" + i + "].name"]: ni[0]
        })
      }
    }


    this.setData({
      result: event.detail,
    });

  },
  getValue:function(e){
    console.log(this.data.priceUnitItems)
    var priceUnitItems = this.data.priceUnitItems;
    for (var i = 0; i < priceUnitItems.length;i++){
      if (priceUnitItems[i].id == e.currentTarget.dataset.id){
        priceUnitItems[i].value = e.detail;
      }
    }
    this.setData({
      priceUnitItems: priceUnitItems
    })
  },
  cancel:function(e){
    console.log(this.data.result)
    console.log(e.currentTarget.dataset.id)
    var pui = this.data.priceUnitItems;
    var result = this.data.result;
    for (var i = 0; i < pui.length;i++){
      console.log(pui[i].id + "---" + e.currentTarget.dataset.id)
      if (pui[i].id == e.currentTarget.dataset.id){
        console.log(true)
        pui.splice(i,1)
        console.log(pui)
        console.log("111111111111111")
        this.setData({
          priceUnitItems: pui
        })
      }
    }
      for(var i=0;i<result.length;i++){
        console.log(result[i] + "iiii" + e.currentTarget.dataset.name + "-" + e.currentTarget.dataset.id)
        if (result[i] == e.currentTarget.dataset.name + "-" + e.currentTarget.dataset.id){
          console.log(true)
          result.splice(i,1);
          this.setData({
            result:result
          })
        }
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: url_microService + '/app/price/unit/sku',
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
          priceItmes:res.data.info
        })
      },
      fail: function (res) { },
      complete: function (res) { },
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
// pages/release_rent/release_rent.js
const url_microService = require('../../config/config').url_microService; //（与config.js相对路径）
const app = getApp()
import Toast from '../../dist/toast/toast';
import Notify from '../../dist/notify/notify';
var userInfo = null;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    priceUnitItems: [],
    count: 0,
    imgs: [],
    address: '',
    addressDetail: '',
    latitude: '',
    longitude: '',
    manufacture: '',
    modelItems: [],
    modeId: '',
    twoStageModeId: '',
    specId: '',
    deviceName: '',
    contactName: '',
    remark:'',
    show: {
      middle: false,
      top: false,
      bottom: false,
      right: false,
      right2: false
    },
    contactMobile: '',
    price: '',
    shows: false,
    actions: [
      {
        name: '30%'
      },
      {
        name: '50%',
      },
      {
        name: "70%"
      },
      {
        name: "100%",
      }
    ],
    exitShows: false,
    exitActions: [
      {
        name: '当时结清'
      },
      {
        name: '一个月内',
      },
      {
        name: "两个月内"
      },
      {
        name: "三个月内",
      },
      {
        name: "六个月内",
      }
    ],
    inTime:'',
    term:'',
    equipmentArrivalPayment:'',
    monthlyPayment:'',
    exitPayment:'',
    remarkItems: [{
      value: "无需任何税费"
    }, {
        value: "需提供3%发票"
    }, {
        value: "需提供10%发票"
    }, {
        value: "运费自理"
    }, {
        value: "报销来回运费"
    }, {
        value: "只付进场运费"
    }, {
        value: "好吃好住"
    }, {
        value: "有住房"
    }, {
        value: "食宿自理"
      }, {
        value: "钱款可靠立此为据"
      }, {
        value: "现金现金"
      }, {
        value: "按约定付款"
      }],
    remarkResult: [],
  },
  // getRemark: function (e) {
  //   console.log(e)
  //   var remark = e.detail;
  //   this.setData({
  //     remark: remark
  //   })
  // },
  //选择备注
  onChange(e) {
    console.log(e)
    this.setData({
      remarkResult: e.detail
    })
  },
  //发布信息
  toRelasae(e) {
    console.log(e)
    var that = this;
    console.log(userInfo)
    var token = userInfo.token
    console.log(token)

    var deviceName = this.data.deviceName;
    console.log(deviceName)
    if (deviceName == null || deviceName == '') {
      Notify("请选择设备名称");
      return;
    }
    var contactMobile = this.data.contactMobile;
    console.log(contactMobile)
    if (contactMobile == null || contactMobile == '') {
      Notify("请填写联系电话");
      return;
    }
    var address = this.data.address;
    console.log(address)
    if (address == null || address == '') {
      Notify("请选择施工位置");
      return;
    }
    var contactName = this.data.contactName;
    console.log(contactName)

    var price = this.data.price;
    console.log(price)
    var pics = '';
    for (var i = 0; i < this.data.imgs.length; i++) {
      if (i == (this.data.imgs.length - 1)) {
        pics += (this.data.imgs[i]);
      } else {
        pics += (this.data.imgs[i] + "#");
      }
    }
    console.log(pics)
    // if (pics == null || pics == '') {
    //   Notify("请至少上传一张图片");
    //   return;
    // }

    var modeId = this.data.modeId;
    console.log(modeId)
    var twoStageModeId = this.data.twoStageModeId
    console.log(twoStageModeId)
    var specId = this.data.specId;
    console.log(specId)

    var addressDetail = this.data.addressDetail;
    console.log(addressDetail)
    var latitude = this.data.latitude;
    console.log(latitude)
    var longitude = this.data.longitude;
    console.log(longitude)



    var inTime = this.data.inTime;
    console.log(inTime)

    var term = this.data.term;
    console.log(term)
    var equipmentArrivalPayment = this.data.equipmentArrivalPayment;
    console.log(equipmentArrivalPayment)

    var monthlyPayment = this.data.monthlyPayment;
    console.log(monthlyPayment)
    var exitPayment = this.data.exitPayment;
    console.log(exitPayment)
    var remark = '';
    for (var i = 0; i < this.data.remarkResult.length; i++) {
      if (i == (this.data.remarkResult.length - 1)) {
        remark += this.data.remarkResult[i]
      } else {
        remark += (this.data.remarkResult[i] + "   ")
      }
    }
    Toast.loading({
      duration: 0,       // 持续展示 toast
      mask: true,
      message: "发布中..."
    });
    wx.request({
      url: url_microService + '/app/rentalInNeedInfo/addOrUpdateNeedInfo',
      data: {
        token: token,
        adress: address,
        addressDetail: addressDetail,
        name: deviceName,
        latitude: latitude,
        longitude: longitude,
        contactMobile: contactMobile,
        modeId: modeId,
        twoStageModeId: twoStageModeId,
        pics: pics,
        inTime: inTime,
        contactName: contactName,
        term: term,
        specId: specId,
        equipmentArrivalPayment: equipmentArrivalPayment,
        exitPayment: exitPayment,
        monthlyPayment: monthlyPayment,
        price: price,
        remark:remark
      },
      header: {},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        if (res.data.code == 0) {
          Toast.clear()
          console.log(res);
          Toast.success('发布成功');
          const timer = setInterval(() => {
            wx.navigateBack({
              delta: 1,
            })
            clearInterval(timer);
          }, 1000)
        } else {
          Toast.clear()
          Notify(res.data.msg);
        }
      },
      fail: function (res) { },
      complete: function (res) { },
    })
  },
  //设备进场付款
  getEquipmentArrivalPayment(e){
    console.log(e)
    this.setData({
      equipmentArrivalPayment:e.detail
    })
  },
  //工期
  getTerm(e){
    console.log(e)
    this.setData({
      term:e.detail
    })
  },
 //授权绑定手机号
  bindMobile:function(e){
    var that = this;
    var token = userInfo.token;
    var encryptedData = e.detail.encryptedData;
    var iv = e.detail.iv;
    app.agriknow.getRequest('app/member/bindMobileByXcx', {
      token: token,
      encryptedData: encryptedData,
      iv: iv
    }).then(res => {
      console.log(res)
      that.setData({
        contactMobile: res.info.phoneNumber
      })
    })
    // wx.request({
    //   url: url_microService + 'app/member/bindMobileByXcx',
    //   data: {
    //     token: token,
    //     encryptedData: encryptedData,
    //     iv: iv
    //   },
    //   header: {},
    //   method: 'GET',
    //   dataType: 'json',
    //   responseType: 'text',
    //   success: function(res) {
    //     console.log(res)
    //     that.setData({
    //       contactMobile: res.data.info.phoneNumber
    //     })
    //   },
    //   fail: function(res) {},
    //   complete: function(res) {},
    // })
  },
  //设备退场结清尾款
  chooseExit:function(e){
    this.setData({
      exitShows: true
    })
  },
  exitOnSelect(e) {
    console.log(e)
    this.setData({
      exitShows: false,
      exitPayment:e.detail.name
    })
  },
  exitOnClose(e) {
    this.setData({
      exitShows: false
    })
  },
  //按月进度付款
  chooseProgress:function(e){
      this.setData({
        shows:true
      })
  },
  onSelect(e){
    console.log(e.detail)
    this.setData({
      shows: false,
      monthlyPayment:e.detail.name
    })
  },
  onClose(e){
    this.setData({
      shows: false
    })
  },
  //获取联系人姓名
  getName(e) {
    console.log(e)
    this.setData({
      contactName: e.detail
    })
  },
  //获取手机号
  getMobile(e) {
    console.log(e)
    this.setData({
      contactMobile: e.detail
    })
  },
  //选择备注
  onChange(e) {
    console.log(e)
    this.setData({
      remarkResult: e.detail
    })
  },
  //选择机械
  toggle(type) {
    this.setData({
      [`show.${type}`]: !this.data.show[type]
    });
    this.setData({
      deviceName: '',
    })
    var that = this;
    app.agriknow.getRequest('/app/deviceName/dict/getModelList',null).then(res => {
      console.log(res)
      that.setData({
        modelItems: res.info
      })
    })
    // wx.request({
    //   url: url_microService + '/app/deviceName/dict/getModelList',
    //   data: {
    //   },
    //   header: {},
    //   method: 'GET',
    //   dataType: 'json',
    //   responseType: 'text',
    //   success: function (res) {
    //     console.log(res)
    //     that.setData({
    //       modelItems: res.data.info
    //     })
    //   },
    //   fail: function (res) { },
    //   complete: function (res) { },
    // })
  },
  selectType: function (e) {
    if (e.currentTarget.dataset.type != '1' && e.currentTarget.dataset.type != '2') {
      this.setData({
        deviceName: this.data.deviceName += e.currentTarget.dataset.name,
      })
    } else {
      this.setData({
        deviceName: this.data.deviceName += ("/" + e.currentTarget.dataset.name),
      })
    }

    console.log(e.currentTarget.dataset.id)
    var that = this;
    console.log(e);
    if (e.currentTarget.dataset.type == '1') {
      wx.request({
        url: url_microService + '/app/deviceName/dict/getSpecByTwoStageModelId',
        data: {
          twoStageModelId: e.currentTarget.dataset.id
        },
        header: {},
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: function (res) {
          console.log(res)
          var info = res.data.info;
          for (var i = 0; i < info.length; i++) {
            info[i].type = 2;
          }
          that.setData({
            modelItems: info,
            twoStageModeId: e.currentTarget.dataset.id
          })
          console.log(info)
        },
        fail: function (res) { },
        complete: function (res) { },
      })
    } else if (e.currentTarget.dataset.type == '2') {
      console.log("end")
      this.setData({
        [`show.bottom`]: !this.data.show['bottom'],
        specId: e.currentTarget.dataset.id
      });
    } else {
      wx.request({
        url: url_microService + '/app/deviceName/dict/getTwoStageModelByModelId',
        data: {
          modelId: e.currentTarget.dataset.id
        },
        header: {},
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: function (res) {
          console.log(res)
          var info = res.data.info;
          for (var i = 0; i < info.length; i++) {
            info[i].type = 1;
          }
          that.setData({
            modelItems: info,
            modeId: e.currentTarget.dataset.id
          })
          console.log(info)
        },
        fail: function (res) { },
        complete: function (res) { },
      })
    }

  },
  toggleBottomPopup() {
    this.toggle('bottom');
  },

  choosePrice: function (e) {
    wx.navigateTo({
      url: '../price_sku/price_sku',
    })
  },
  //选择并上传图片
  chooseImage: function (e) {
    var that = this;
    wx.chooseImage({
      count: 9,
      sizeType: ["compressed"],
      success: function (e) {
        console.log(e)
        for (var i = 0; i < e.tempFilePaths.length; i++) {
          wx.uploadFile({
            url: url_microService + '/app/upload/data',
            filePath: e.tempFilePaths[i],
            name: "file",
            success: function (e) {
              var result = JSON.parse(e.data)
              console.log(result)
              console.log(result.info[0].url)
              var url = result.info[0].url
              var suffix = result.info[0].suffix
              that.setData({
                imgs: that.data.imgs.concat(url),
                count: that.data.count + 1
              })
            }
          })
        }
      }
    });
  },
  deleteImg: function (e) {
    var imgs = this.data.imgs;
    imgs.splice(e.currentTarget.dataset.index, 1)
    this.setData({
      imgs: imgs,
      count: this.data.count - 1
    })
  },

  //选择地址
  chooseAddress: function (e) {
    var that = this
    wx.chooseLocation({
      success: function (res) {
        console.log(res)
        that.setData({
          address: res.name,
          addressDetail: res.address,
          longitude: res.longitude,
          latitude: res.latitude
        })
      },
    })
  },

  //出场日期
  bindDateChange: function (e) {
    console.log(e)
    this.setData({
      inTime: e.detail.value
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    userInfo = wx.getStorageSync("simpleInfo");
    this.setData({
      contactMobile: userInfo.mobile
    })
    console.log(options)
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
//index.js
//获取应用实例
import Toast from '../../dist/toast/toast';
import Notify from '../../dist/notify/notify';
const url_microService = require('../../config/config').url_microService; //（与config.js相对路径）
const app = getApp()

Page({
  data: {
    arrayItems:[],
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    totalCount:0,
    picPosition: 0,
    picLenth:50,
    currentPage: 1,
    pageSize : 50,
    latitude: '',
    longitude: '',
    distance: '',
    type:'1',
    modeId:'',
    twoStageModeId:'',
    specId:'',
    tabActive:'-1',
    isRefresh: 0,
    identify:'',
  },

  toDetail:function(e){
    var distance = e.currentTarget.dataset.distance;
    var id = e.currentTarget.dataset.id;
    var type = e.currentTarget.dataset.type;
    var url = '';
    if(type == 1){
      url =  '/pages/rent_detail/rent_detail?distance=' + distance + '&id=' + id
    }else if(type == 2){
      url = '/pages/rental_detail/rental_detail?distance=' + distance + '&id=' + id
    }
    wx.navigateTo({
      url: url,
    })
  },
  //selectTabp
  selectTab:function(e){
      if(e.detail.index == '0'){ //个人中心
        wx.navigateTo({
          url: "/pages/person_center/person_center",
        })
      }else if(e.detail.index == '1'){//身份切换
        wx.navigateTo({
          url: '/pages/choose_identify/choose_identify',
        })
      }else if(e.detail.index == '2'){//筛选机械
        wx.navigateTo({
          url: '/pages/search/search',
        })
      }
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function (e) {
    if (wx.getStorageSync('identity') == ''){
      if (e != null && e.share_query != null){
        wx.reLaunch({
          url: '/pages/switch_idetify/switch_idetify?share_query='+e.share_query+'&id='+e.id,
        })
      }else{
        wx.reLaunch({
          url: '/pages/switch_idetify/switch_idetify',
        })
      }
      return;
    }
    var url = '/app/search';
    var type = wx.getStorageSync('identity') == 1 ? 2 : 1
    var that = this;
    var identity = wx.getStorageSync('identity') == '' ? 2 : wx.getStorageSync('identity');
    wx.setStorageSync('identity', identity)
    that.setData({
      // longitude: wx.getStorageSync('longitude' + identity),
      // latitude: wx.getStorageSync('latitude' + identity),
      identify: identity,
      distance: wx.getStorageSync('distance' + identity) == '' ? 600 : (wx.getStorageSync('distance' + identity)*10),
      type: type,
      modeId: wx.getStorageSync('modeId' + identity),
      twoStageModeId: wx.getStorageSync('twoStageModeId' + identity),
      specId: wx.getStorageSync('specId' + identity),
      // latitude: wx.getStorageSync('latitude' + identity) == null ? wx.getStorageSync('latitude' + identity):'31.82043',
      // longitude: wx.getStorageSync('longitude' + identity) == null ? wx.getStorageSync('longitude' + identity) : '117.22715',
    })
        var currentPage = 0; // 因为数组下标是从0开始的，所以这里用了0
        that.data.currentPage = 1;
        that.setData({
          arrayItems:[],
          picPosition: 0,
          currentPage: 1,
        })
    var isFirstLogin = app.globalData.isFirstLogin;
    if(isFirstLogin){
      wx.login({
        success: res => {
          // 发送 res.code 到后台换取 openId, sessionKey, unionId
          app.agriknow.getRequest('app/member/loginByWxXcx', {
            code: res.code
          }).then(res => {
            if (res.code == 0) {
              app.globalData.isFirstLogin = false;
              wx.setStorageSync("simpleInfo", res.info)
              if (e != null && e.share_query != null) {
                wx.navigateTo({
                  url: e.share_query + '?id=' + e.id
                })
              }
              if (e != null && e.pass == 'fitMe') {
                var id = e.id;
                type = e.type;
                //开始写逻辑了
                url = '/app/search/fitMe?id=' + id;
              }
              wx.getLocation({
                type: 'gcj02', //返回可以用于wx.openLocation的经纬度
                success: function (res) {
                  if (that.data.latitude == '') {
                    that.setData({
                      latitude: res.latitude,
                      longitude: res.longitude
                    })
                  }
                  app.agriknow.getRequest(url, {
                    pageNo: '1',
                    longitude: that.data.longitude,
                    latitude: that.data.latitude,
                    distance: that.data.distance,
                    type: that.data.type,
                    modeId: that.data.modeId,
                    twoStageModeId: that.data.twoStageModeId,
                    specId: that.data.specId
                  }).then(res => {
                    if (res.code == 0) {
                      that.setData({
                        ["arrayItems[" + currentPage + "]"]: res.info.rows,
                        totalCount: res.info.totalCount,
                        pageSize: 50,
                        picLenth: 50
                      })
                    }
                  })
                },
                fail: function (e) {
                  that.setData({
                    latitude: '31.82043',
                    longitude: '117.22715'
                  })
                  app.agriknow.getRequest(url, {
                    pageNo: '1',
                    longitude: that.data.longitude,
                    latitude: that.data.latitude,
                    distance: that.data.distance,
                    type: that.data.type,
                    modeId: that.data.modeId,
                    twoStageModeId: that.data.twoStageModeId,
                    specId: that.data.specId
                  }).then(res => {
                    if (res.code == 0) {
                      that.setData({
                        ["arrayItems[" + currentPage + "]"]: res.info.rows,
                        totalCount: res.info.totalCount,
                        pageSize: 50,
                        picLenth: 50
                      })
                    }
                  })
                }
              });
            }
          })
        }
      })
    }else{
      if (e != null && e.share_query != null) {
        wx.navigateTo({
          url: e.share_query + '?id=' + e.id
        })
      }
      if (e != null && e.pass == 'fitMe') {
        var id = e.id;
        type = e.type;
        //开始写逻辑了
        url = '/app/search/fitMe?id=' + id;
      }
      wx.getLocation({
        type: 'gcj02', //返回可以用于wx.openLocation的经纬度
        success: function (res) {
          if (that.data.latitude == '') {
            that.setData({
              latitude: res.latitude,
              longitude: res.longitude
            })
          }
          app.agriknow.getRequest(url, {
            pageNo: '1',
            longitude: that.data.longitude,
            latitude: that.data.latitude,
            distance: that.data.distance,
            type: that.data.type,
            modeId: that.data.modeId,
            twoStageModeId: that.data.twoStageModeId,
            specId: that.data.specId,
          }).then(res => {
            if (res.code == 0) {
              that.setData({
                ["arrayItems[" + currentPage + "]"]: res.info.rows,
                totalCount: res.info.totalCount,
                pageSize: 50,
                picLenth: 50
              })
            }
          })
        },
        fail: function (e) {
          that.setData({
            latitude: '31.82043',
            longitude: '117.22715'
          })
          app.agriknow.getRequest(url, {
            pageNo: '1',
            longitude: that.data.longitude,
            latitude: that.data.latitude,
            distance: that.data.distance,
            type: that.data.type,
            modeId: that.data.modeId,
            twoStageModeId: that.data.twoStageModeId,
            specId: that.data.specId
          }).then(res => {
            if (res.code == 0) {
              that.setData({
                ["arrayItems[" + currentPage + "]"]: res.info.rows,
                totalCount: res.info.totalCount,
                pageSize: 50,
                picLenth: 50
              })
            }
          })
        }
      });
    }

  },
  toRelasae:function(e){
    var identify = wx.getStorageSync('identity')
    var url = '';
    if(identify == 1){
      url = '../release_rent/release_rent'
    }else{
      url = '../release_rental/release_rental';
    }
    wx.navigateTo({
      url: url,
    })
  },
  onShow(e){
    if(this.data.isRefresh == 1){
      this.setData({
        isRefresh: 0
      })
      this.onLoad()
    }
  },
  switchPic: function(e){
    var that = this;
    if(e.detail.source == 'touch'){
      //防止swiper控件卡死
      if (e.detail.current == 0 && this.data.picPosition > 1) {//卡死时，重置current为正确索引
        this.setData({ picPosition: this.data.picPosition });
      } else {//正常轮转时，记录正确页码索引
        this.setData({ picPosition: e.detail.current });
      }


      if ( (this.data.picPosition + 1) % this.data.pageSize == 0 && (this.data.picLenth - 1 )== this.data.picPosition){
          Toast.loading({
            duration: 0,       // 持续展示 toast
            mask: true,
            message: "加载中..."
          });
          const timer = setInterval(() => {
            var currentPage = that.data.currentPage; // 获取当前页码
            currentPage += 1; // 加载当前页面的下一页数据
            that.data.currentPage = currentPage;
            app.agriknow.getRequest("/app/search", {
              pageNo: currentPage,
              longitude: that.data.longitude,
              latitude: that.data.latitude,
              distance: that.data.distance,
              type: that.data.type,
              modeId: that.data.modeId,
              twoStageModeId:that.data.twoStageModeId,
              specId:that.data.specId
            }).then(res => {
              if (res.code == 0) {
                var row = res.info.rows;
                that.setData({
                  ["arrayItems[" + (currentPage - 1) + "]"]: res.info.rows,
                  picLenth: that.data.picLenth + that.data.pageSize
                })
                Toast.clear()
              }
            })
            clearInterval(timer);
          }, 600);

      } else if (this.data.picPosition == that.data.totalCount - 1){
        Notify("已经到底了~~")
      }
    }
  },
//跳转筛选页面
  onClickRight: function(){
    wx.navigateTo({
      url: '/pages/search/search',
    })
  },
  getUserInfo: function(e) {
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  /**
 * 用户点击右上角分享
 */
  onShareAppMessage: function () {
    return{
      title: "最快租机械",
      path: "/pages/index/index",
      imageUrl: 'http://img.huisonglin.top/FuCcvykQh5h7rD9odYLRgTIVXuO9',
    }
  }
})

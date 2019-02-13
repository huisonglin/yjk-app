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
    picLenth:5,
    currentPage: 1,
    pageSize : 5,
    latitude: '',
    longitude: '',
    distance: '',
    type:'1',
    modeId:'',
    twoStageModeId:'',
    specId:'',
    tabActive:'-1',
    isRefresh: 0
  },

  toDetail:function(e){
    console.log(e)
    var distance = e.currentTarget.dataset.distance;
    var id = e.currentTarget.dataset.id;
    console.log(distance)
    wx.navigateTo({
      url: '/pages/rent_detail/rent_detail?distance=' + distance+'&id='+id,
    })
  },
  //selectTabp
  selectTab:function(e){
    console.log(e)

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
  onLoad: function () {
    var that = this;
    that.setData({
      longitude: wx.getStorageSync('longitude'),
      latitude: wx.getStorageSync('latitude'),
      distance: wx.getStorageSync('distance') == '' ? 600 : wx.getStorageSync('distance'),
      type: wx.getStorageSync('identity'),
      modeId: wx.getStorageSync('modeId'),
      twoStageModeId: wx.getStorageSync('twoStageModeId'),
      specId: wx.getStorageSync('specId')
    })
    wx.getLocation({
      success: function(res) {
        that.setData({
          latitude:res.latitude,
          longitude: res.longitude
        })
        var currentPage = 0; // 因为数组下标是从0开始的，所以这里用了0
        that.data.currentPage = 1;
        that.setData({
          arrayItems:[],
          picPosition: 0,
          picLenth: 5,
          currentPage: 1,
        })
        app.agriknow.getRequest('/app/search', {
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
            console.log(res)
            that.setData({
              ["arrayItems[" + currentPage + "]"]: res.info.rows,
              totalCount:res.info.totalCount
            })
          }
        })
        // wx.request({
        //   url: url_microService + '/app/search',
        //   data: {
        //     pageNo: '1',
        //     longitude: res.longitude,
        //     latitude: res.latitude,
        //     distance: '1000',
        //     type: '1'
        //   },
        //   header: {},
        //   method: 'GET',
        //   dataType: 'json',
        //   responseType: 'text',
        //   success: function (res) {
        //     console.log(res)
        //     that.setData({
        //       ["arrayItems[" + currentPage + "]"]: res.data.info.rows
        //     })
        //   },
        //   fail: function (res) { },
        //   complete: function (res) { },
        // })
      },
    })

  },
  toRelasae:function(e){
    var identify = wx.getStorageSync('identity')
    console.log("c"+identify)
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
      console.log('开始刷新')
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
      // console.log(this.data.picPosition)
      // console.log((this.data.picPosition + 5) % 10)
      // console.log(this.data.picLenth - 5)
      console.log(e.detail.current)
      console.log((this.data.picPosition + 5) % this.data.pageSize == 0)
      if ( (this.data.picPosition + 1) % this.data.pageSize == 0 && this.data.picLenth - 1 == this.data.picPosition){
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
                console.log(res)
                console.log("分页了")
                var row = res.info.rows;
                console.log(that.arrayItems)
                that.setData({
                  ["arrayItems[" + (currentPage - 1) + "]"]: res.info.rows,
                  picLenth: that.data.picLenth + that.data.pageSize
                })
                Toast.clear()
              }
            })
            // wx.request({
            //   url: url_microService + '/app/search',
            //   data: {
            //     pageNo: currentPage,
            //     longitude: that.data.longitude,
            //     latitude: that.data.latitude,
            //     distance: '1000',
            //     type: '1'
            //   },
            //   header: {},
            //   method: 'GET',
            //   dataType: 'json',
            //   responseType: 'text',
            //   success: function (res) {
            //     console.log(res)
            //     console.log("分页了")
            //     var row = res.data.info.rows;
            //     console.log(that.arrayItems)
            //     that.setData({
            //       ["arrayItems[" + (currentPage - 1) + "]"]: res.data.info.rows,
            //       picLenth: that.data.picLenth + that.data.pageSize
            //     })
            //     Toast.clear()
            //   },
            //   fail: function (res) { },
            //   complete: function (res) { },
            // })
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
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})

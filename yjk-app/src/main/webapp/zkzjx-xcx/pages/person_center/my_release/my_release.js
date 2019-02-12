// pages/person_center/my_release/my_release.js
var userInfo = wx.getStorageSync("simpleInfo")
const app = getApp()
import Toast from '../../../dist/toast/toast';
import Notify from '../../../dist/notify/notify';
import Dialog from '../../../dist/dialog/dialog';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    rentItemsArray:[],
    rentalItemsArray:[],
    rentCurrentPage:1,//发布出租的页面标识
    rentalCurrentPage:1,//发布求租的页面标识
    index:0
  },
  cancelShow:function(e){
    Dialog.confirm({
      message: '确定要下架该条发布信息吗?'
    }).then(() => {
      console.log(e)
      var arrayIndex = e.currentTarget.dataset.arrayindex;
      var subArrayIndex = e.currentTarget.dataset.subarrayindex;
      var token = userInfo.token;
      var infoId = e.currentTarget.dataset.infoid;
      app.agriknow.getRequest("/app/deviceRentOut/cacelRentOutInfo", {
        token: token,
        id: infoId
      }).then(res => {
        console.log(res)
        Toast.success('下架成功');
      })
    }).catch(() => {
      // on cancel
    });

  },
  refreshPotion:function(e){
    console.log(e);
    var that = this
    var arrayIndex = e.currentTarget.dataset.arrayindex;
    var subArrayIndex = e.currentTarget.dataset.subarrayindex;
    var infotype = e.currentTarget.dataset.infotype;
    var infoId = e.currentTarget.dataset.infoid;
    console.log(infotype);
    console.log(infoId);
    console.log(arrayIndex)
    console.log(subArrayIndex)
    var token = userInfo.token;

    wx.chooseLocation({
      success: function (res) {
        console.log(res)
        // that.setData({
        //   address: res.name,
        //   addressDetail: res.address,
        //   longitude: res.longitude,
        //   latitude: res.latitude
        // })
        app.agriknow.getRequest('app/device/refreshPositionAndPublish',{
          token: token,
          infoId: infoId,
          infoType: infotype,
          address: res.name,
          addressDetail: res.address,
          latitude: res.latitude,
          longitude: res.longitude
        }).then(e => {
          that.setData({
            ["rentItemsArray[" + arrayIndex + "][" + subArrayIndex + "].address"]: res.name
          })
          Toast.success('刷新位置成功');
        })
      },
    })
  },
  onClick:function(e){
    console.log(e)
    this.setData({
      index:e.detail.index
    })
  },
  onLoad: function (options) {
    var that = this;
    var token = userInfo.token;
    var currentPage = 0; // 因为数组下标是从0开始的，所以这里用了0
    app.agriknow.getRequest("app/device/deviceList", {
      token: token,
      pageNo: 1,
      identify: 1
    }).then(res => {
      console.log(res);
      that.setData({
        ["rentItemsArray[" + currentPage + "]"]: res.info.list
      })
    })

    app.agriknow.getRequest("app/device/deviceList", {
      token: token,
      pageNo: 1,
      identify: 2
    }).then(res => {
      console.log(res);
      that.setData({
        ["rentalItemsArray[" + currentPage + "]"]: res.info.list
      })
    })
  },
  loadMoreData: function () {
    var that = this;
    var index = that.data.index;
    var identify = that.data.index + 1;
    if(index == 0){//发布出租分页
      var rentCurrentPage = that.data.rentCurrentPage; // 获取当前页码
      rentCurrentPage += 1; // 加载当前页面的下一页数据
      that.data.rentCurrentPage = rentCurrentPage;
      var token = userInfo.token;
      console.log("load page " + (rentCurrentPage + 1));
      app.agriknow.getRequest('app/device/deviceList', {
        token: token,
        pageNo: rentCurrentPage,
        identify: identify
      }).then(res => {
        if (rentCurrentPage > res.info.pages){
          Notify("已经到底了~~")
        }else{
          that.setData({
            ["rentItemsArray[" + (rentCurrentPage - 1) + "]"]: res.info.list
          })
        }

      })
    }else if(index == 1){//求组分页
      var rentalCurrentPage = that.data.rentalCurrentPage; // 获取当前页码
      rentalCurrentPage += 1; // 加载当前页面的下一页数据
      that.data.rentalCurrentPage = rentalCurrentPage;
      var token = userInfo.token;
      console.log("load page " + (rentalCurrentPage + 1));
      console.log("当前传入的页码" + rentalCurrentPage)
      app.agriknow.getRequest('app/device/deviceList', {
        token: token,
        pageNo: rentalCurrentPage,
        identify: identify
      }).then(res => {
        if (rentalCurrentPage > res.info.pages) {
          Notify("已经到底了~~")
        } else {
          that.setData({
            ["rentalItemsArray[" + (rentalCurrentPage - 1) + "]"]: res.info.list
          })
        }
      })
    }
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
    console.log("eeee")
    this.loadMoreData()
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  }
})
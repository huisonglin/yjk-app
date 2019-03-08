// pages/person_center/my_release/my_release.js
var userInfo = null;
const app = getApp()
import Toast from '../../../dist/toast/toast';
import Notify from '../../../dist/notify/notify';
import Dialog from '../../../dist/dialog/dialog';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    rentItemsArray: [],
    rentalItemsArray: [],
    rentCurrentPage: 1,//发布出租的页面标识
    rentalCurrentPage: 1,//发布求租的页面标识
    index: 0
  },
  toDetail:function(e){
    var id = e.currentTarget.dataset.infoid;
    var type = e.currentTarget.dataset.infotype;
    console.log(id +"---"+type)
    if(type == 1){
      wx.navigateTo({
        url: '/pages/rent_detail/rent_detail?id='+id,
      })
    }else if(type == 2){
      wx.navigateTo({
        url: '/pages/rental_detail/rental_detail?id='+id,
      })
    }

  },
  cancelCollection: function (e) {
    var that = this;
    var token = userInfo.token;
    var id = this.data.detailInfo.id;
    var isCollection = 0;
    app.agriknow.getRequest("/app/myManage/collectionOptions", {
      token: token,
      infoId: id,
      status: isCollection
    }).then(res => {
      console.log(res)
      that.setData({
        isCollection: res.status
      })
    })


  },
  fitMe: function (e) {
    console.log(e)
    var id = e.currentTarget.dataset.infoid;
    var type = e.currentTarget.dataset.infotype;
    wx.navigateTo({
      url: '/pages/index/index?pass=fitMe&id=' + id + '&type=' + type,
    })
  },
  cancelShow: function (e) {
    var that = this;
    Dialog.confirm({
      message: '确定要取消收藏吗?'
    }).then(() => {
      console.log(e)
      var arrayIndex = e.currentTarget.dataset.arrayindex;
      var subArrayIndex = e.currentTarget.dataset.subarrayindex;
      var token = userInfo.token;
      var infoId = e.currentTarget.dataset.infoid;
      var infoType = e.currentTarget.dataset.infotype;
      var isCollection = 0;
      console.log("infoType = " + infoType)
      console.log("arrayIndex = " + arrayIndex)
      if (infoType == 1) {//出租
        app.agriknow.getRequest("/app/myManage/collectionOptions", {
          token: token,
          collectionId: infoId,
          status: isCollection
        }).then(res => {
          console.log(res)
          Toast.success('取消成功');
          var items = that.data.rentItemsArray[arrayIndex];
          console.log(items)
          items.splice(subArrayIndex, 1)
          that.setData({
            ["rentItemsArray[" + arrayIndex + "]"]: items
          })
        })
      } else if (infoType == 2) {
        app.agriknow.getRequest("/app/myManage/collectionOptions", {
          token: token,
          collectionId: infoId,
          status: isCollection
        }).then(res => {
          console.log(res)
          Toast.success('取消成功');
          console.log(that.data.rentalItemsArray)
          console.log(arrayIndex)
          var items = that.data.rentalItemsArray[arrayIndex];
          console.log(items)
          items.splice(subArrayIndex, 1)
          that.setData({
            ["rentalItemsArray[" + arrayIndex + "]"]: items
          })
        })
      }

    }).catch(() => {
      // on cancel
    });

  },
  refreshPotion: function (e) {
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
        app.agriknow.getRequest('app/device/refreshPositionAndPublish', {
          token: token,
          infoId: infoId,
          infoType: infotype,
          address: res.name,
          addressDetail: res.address,
          latitude: res.latitude,
          longitude: res.longitude
        }).then(e => {
          if (infotype == 1) {
            that.setData({
              ["rentItemsArray[" + arrayIndex + "][" + subArrayIndex + "].address"]: res.name
            })
          } else if (infotype == 2) {
            that.setData({
              ["rentalItemsArray[" + arrayIndex + "][" + subArrayIndex + "].address"]: res.name
            })
          }

          Toast.success('刷新位置成功');
        })
      },
    })
  },
  onClick: function (e) {
    console.log(e)
    this.setData({
      index: e.detail.index
    })
  },
  onLoad: function (options) {
    userInfo = wx.getStorageSync("simpleInfo")
    var that = this;
    var token = userInfo.token;
    var currentPage = 0; // 因为数组下标是从0开始的，所以这里用了0
    app.agriknow.getRequest("app/device/myCollectionList", {
      token: token,
      pageNo: 1,
      identify: 1
    }).then(res => {
      console.log(res);
      that.setData({
        ["rentItemsArray[" + currentPage + "]"]: res.info.list
      })
    })

    app.agriknow.getRequest("app/device/myCollectionList", {
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
    if (index == 0) {//发布出租分页
      var rentCurrentPage = that.data.rentCurrentPage; // 获取当前页码
      rentCurrentPage += 1; // 加载当前页面的下一页数据
      that.data.rentCurrentPage = rentCurrentPage;
      var token = userInfo.token;
      console.log("load page " + (rentCurrentPage + 1));
      app.agriknow.getRequest('app/device/myCollectionList', {
        token: token,
        pageNo: rentCurrentPage,
        identify: identify
      }).then(res => {
        if (rentCurrentPage > res.info.pages) {
          Notify("已经到底了~~")
        } else {
          that.setData({
            ["rentItemsArray[" + (rentCurrentPage - 1) + "]"]: res.info.list
          })
        }

      })
    } else if (index == 1) {//求组分页
      var rentalCurrentPage = that.data.rentalCurrentPage; // 获取当前页码
      rentalCurrentPage += 1; // 加载当前页面的下一页数据
      that.data.rentalCurrentPage = rentalCurrentPage;
      var token = userInfo.token;
      console.log("load page " + (rentalCurrentPage + 1));
      console.log("当前传入的页码" + rentalCurrentPage)
      app.agriknow.getRequest('app/device/myCollectionList', {
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
  onShareAppMessage: function (e) {
    console.log(e);
    var address = e.target.dataset.address;
    var id = e.target.dataset.id;
    var image = e.target.dataset.image;
    var infotype = e.target.dataset.infotype;
    var title = e.target.dataset.title;
    if (infotype == 1) {
      return {
        title: '【出租】' + title + '(' + address + ')',
        path: '/pages/index/index?share_query=' + '/pages/rent_detail/rent_detail&id=' + id,
        imageUrl: image
      }
    } else if (infotype == 2) {
      return {
        title: '【求租】' + title + '(' + address + ')',
        path: '/pages/index/index?share_query=' + '/pages/rental_detail/rental_detail&id=' + id,
        imageUrl: image == null ? '../../../images/gong.png' : image
      }
    }
    //  return {
    //    title: '【出租】',
    //    path: '/pages/index/index?share_query=' + '/pages/rent_detail/rent_detail&id=',
    //    imageUrl: 'iamge'
    //  }
  }
})
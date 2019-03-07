// pages/rent_detail/rent_detail.js
var userInfo = null;
const app = getApp()
import Toast from '../../dist/toast/toast';
import Notify from '../../dist/notify/notify';
import Dialog from '../../dist/dialog/dialog';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    detailInfo:[],
    distance:'',
    isSelectShow:true,
    show: false,
    tipShow: false,
    remainCount:'',
    //是否采用衔接滑动  
    circular: true,
    //是否显示画板指示点  
    indicatorDots: false,
    //选中点的颜色  
    indicatorcolor: "#000",
    //是否竖直  
    vertical: false,
    //是否自动切换  
    autoplay: true,
    //自动切换的间隔
    interval: 2500,
    //滑动动画时长毫秒  
    duration: 100,
    //所有图片的高度  
    imgheights: [],
    //图片宽度 
    imgwidth: 750,
    //默认  
    current: 0,
    isShowBtn:false,
    isCollection:0
  },
  imageLoad: function (e) {//获取图片真实宽度  
    var imgwidth = e.detail.width,
      imgheight = e.detail.height,
      //宽高比  
      ratio = imgwidth / imgheight;
    console.log(imgwidth, imgheight)
    //计算的高度值  
    var viewHeight = 750 / ratio;
    var imgheight = viewHeight;
    var imgheights = this.data.imgheights;
    //把每一张图片的对应的高度记录到数组里  
    imgheights[e.target.dataset.id] = imgheight;
    this.setData({
      imgheights: imgheights
    })
  },
  collection:function(e){
    var that = this;
    var token = userInfo.token;
    var type = this.data.detailInfo.type;
    var id = this.data.detailInfo.id;
    var isCollection = that.data.isCollection;
    app.agriknow.getRequest("/app/myManage/collectionOptions",{
      token: token,
      infoId:id,
      infoType:type,
      status: isCollection == 0?1:0
    }).then(res => {
      console.log(res)
      that.setData({
        isCollection: res.status
      })
    })
  },
  pay: function (e) {
    console.log(e)
    var that = this;
    var token = userInfo.token;
    var type = this.data.detailInfo.type;
    var id = this.data.detailInfo.id;
    app.agriknow.getRequest('/app/fee/dialing', {
      token: token,
      infoId: id,
    }).then(res => {
      console.log(res)
      wx.requestPayment({
        'timeStamp': res.data.timestamp,
        'nonceStr': res.data.noncestr,
        'package': res.data._package,
        'signType': 'MD5',
        'paySign': res.data.paySign,
        'success': function (res) {
          that.onClose();
          console.log("支付成功了！！！！！！！！！！！！")
          that.setData({
            remainCount: (that.data.remainCount + 1)
          })
          Notify({
            text: '支付成功',
            duration: 3000,
            selector: '#custom-selector',
            backgroundColor: '#1989fa'
          });

        },
        'fail': function (res) {
        }
      })
    })
  },
  bindchange: function (e) {
    // console.log(e.detail.current)
    this.setData({ current: e.detail.current })
  },
  closeFeedback:function(e){
    this.onTipClose();
    Dialog.alert({
      message: '感谢您使用快租机械！'
    }).then(() => {
      // on close
    });
  },
  feedback:function(e){
    var that = this;
    var token = userInfo.token;
    var infoId = this.data.detailInfo.id;
    var infoType = this.data.detailInfo.type;
    var appealContent = e.currentTarget.dataset.info;
    app.agriknow.getRequest('app/member/callAppeal',{
      token:token,
      infoId: infoId,
      infoType: infoType,
      appealContent: appealContent
    }).then(res => {
      that.onTipClose();
      that.refreshRemainCount();
    })
    Dialog.alert({
      message: '感谢您的举报我们会尽快处理！并返还本次拨打次数'
    }).then(() => {
      // on close
    });
  },
  onClose() {
    this.setData({ show: false });
  },
  onTipClose(){
    this.setData({ tipShow: false });
  },
  reward:function(e){

  },
  call:function(e){
    var that = this;
    var token = userInfo.token;
    console.log(token)
    app.agriknow.getRequest('/app/fee/dialingDesc',{
      token:token
    }).then(res => {
      if(res.code == 0){
        var mobile = that.data.detailInfo.contactMobile;
        console.log(mobile)
        wx.makePhoneCall({
          phoneNumber: mobile,
          success(e) {
            that.refreshRemainCount();
            that.setData({
              tipShow:true,
              show:false
            })
          }
        })
      }
    })
  },
  remind: function(e){
    this.setData({ show: true });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    userInfo = wx.getStorageSync("simpleInfo");
    if (options != null && options.pass == 'person'){
      this.setData({
        isShowBtn:true
      })
    }
    var that = this;
    var distance = options.distance;
    var id = options.id;
    var token = userInfo.token;
    app.agriknow.getRequest('/app/info/detail',{
      id:id,
      infoType:1,
      token:token
    }).then(res => {
      console.log(res)
      that.setData({
        detailInfo:res.info,
        isCollection:res.info.isCollection,
        distance: distance == null?'':'(距离我'+distance+')'
      })
    })
    that.refreshRemainCount();

  },
  refreshRemainCount:function(e){
    var that = this;
    var token = userInfo.token;
    if(token != null){
      app.agriknow.getRequest('app/member/remainingNumbercallCount', {
        token: token
      }).then(res => {
        if (res.code == 0) {
          that.setData({
            remainCount: res.info
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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function (e) {
    console.log(e)
    var that = this;
    var id = that.data.detailInfo.id;
    var name = that.data.detailInfo.deviceName;
    var iamge = that.data.detailInfo.originalPic;
    var address = that.data.detailInfo.addressDetail;
    var token = userInfo.token;
    app.agriknow.getRequest('app/member/toShare', {
      token: token
    }).then(res => {
      console.log(res)
      this.refreshRemainCount()
    })

    return {
      title: '【出租】' + name + '(' + address+')',
      path: '/pages/index/index?share_query='+'/pages/rent_detail/rent_detail&id='+id,
      imageUrl: iamge
    }


  }
})
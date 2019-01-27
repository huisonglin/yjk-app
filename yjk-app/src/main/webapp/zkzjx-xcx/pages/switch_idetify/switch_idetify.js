// var e = require("../../url/url.js"), n = e.urls;

// Page({
//   data: {
//     mingzi: "",
//     mingziFan: "",
//     identityL: "",
//     shenfen: e.images.shenfen
//   },
//   onLoad: function (e) {
//     var n = this;
//     "1" == wx.getStorageSync("identity") ? n.setData({
//       mingzi: "我要出租",
//       mingziFan: "我要求租",
//       identityL: 2
//     }) : n.setData({
//       mingzi: "我要求租",
//       mingziFan: "我要出租",
//       identityL: 1
//     });
//   },
//   qiehuan: function (e) {
//     wx.setStorageSync("identity", e.currentTarget.dataset.identityl), wx.request({
//       url: n.chooseIdentify,
//       data: {
//         token: wx.getStorageSync("userId"),
//         identify: e.currentTarget.dataset.identityl
//       },
//       success: function (e) {
//         wx.reLaunch({
//           url: "/pages/index/index"
//         });
//       }
//     });
//   },
//   back: function () {
//     wx.navigateBack({
//       delta: 1
//     });
//   },
//   onReady: function () { },
//   onShow: function () { },
//   onHide: function () { },
//   onUnload: function () { },
//   onPullDownRefresh: function () { },
//   onReachBottom: function () { },
//   onShareAppMessage: function () {
//     return {
//       title: "最快租机械",
//       path: "/pages/index/index",
//       imageUrl: e.images.shouye,
//       success: function (e) {
//         "shareAppMessage:ok" == e.errMsg && wx.showToast({
//           title: "分享成功",
//           duration: 2e3
//         });
//       },
//       fail: function () {
//         "shareAppMessage:fail cancel" == res.errMsg ? wx.showToast({
//           title: "取消分享",
//           duration: 2e3
//         }) : "shareAppMessage:fail" == res.errMsg && wx.showToast({
//           title: "分享失败，请检查网络！",
//           duration: 2e3
//         });
//       }
//     };
//   }
// });
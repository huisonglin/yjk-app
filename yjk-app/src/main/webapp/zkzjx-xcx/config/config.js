//域名要在小程序的管理平台配置好，如果出现调用时报错，无效的域名，可在微信开发工具左边点项目-->配置信息-->看一下配置的域名【request合法域名】有没有刷新下来，没有的话就点下面的刷新

//var host = "http://127.0.0.1:8086/"
//var host = "http://192.168.137.1:8086/"
var host = "https://www.huisonglin.top/"
//var host = "http://192.168.1.104:8086/"
//var host = "http://192.168.43.165:8086/"
var config = {

  // 下面的地址配合 Server 工作

  //配置

  url_microService: `${host}`

};
module.exports = config
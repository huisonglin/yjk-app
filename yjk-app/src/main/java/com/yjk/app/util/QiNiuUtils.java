package com.yjk.app.util;

import java.io.File;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class QiNiuUtils {

	public static String upload(byte[] bytes,String key) {
		//构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());
		//...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		//...生成上传凭证，然后准备上传
		String accessKey = "NKcpfXhgElRn7IZ7BbEBRuX6G1e-rnuCmJbmvqSc";
		String secretKey = "aQIJEbd5-hvTm_HHvwhwSTBJhHGhdlzVNUjr8ehW";
		String bucket = "huisonglin";
		//如果是Windows情况下，格式是 D:\\qiniu\\test.png
		//默认不指定key的情况下，以文件内容的hash值作为文件名
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		try {
		    Response response = uploadManager.put(bytes, key, upToken);
		    //解析上传成功的结果
		    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		    System.out.println(putRet.toString());
		    System.out.println(putRet.key);
		    System.out.println(putRet.hash);
		    return putRet.hash;
		} catch (QiniuException ex) {
			ex.printStackTrace();
		    Response r = ex.response;
		    System.err.println(r.toString());
		        try {
					System.err.println(r.bodyString());
				} catch (QiniuException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		
		return null;
		
	}
}

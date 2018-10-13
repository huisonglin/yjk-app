package com.yjk.app.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.tomcat.util.codec.binary.Base64;

import com.yjk.app.exception.RRException;
import com.yjk.app.vo.FacedeIdCardInfoVO;
import com.yjk.app.vo.backIdCardInfoVO;

import net.sf.json.JSONObject;
 
public class RecognizeUtil {
 
    public static final String APPKEY = "5113f842d189193d";// 你的appkey
    public static final String URL = "http://api.jisuapi.com/idcardrecognition/recognize";
    //public static final String URL = "http://api.jisuapi.com/mobileverify/verify";
    public static final String pic = "D:\\img\\1.jpg";// 图片地址
   // public static final int typeid = 2;// 证件的类型
 
    public static JSONObject Get(byte[] imgFile,int typeId,String remark) throws Exception {
        String result = null;
        JSONObject resultarr=null;
        String url = URL + "?appkey=" + APPKEY + "&typeid=" + typeId;
        Map<String,String> param = new HashMap<>();
        JSONObject json = null;

            param.put("pic", getBase64Bybyte(imgFile));
            result = HttpUtil.sendPost(url, param, "UTF-8");
            System.out.println(result);
            json = JSONObject.fromObject(result);
            if (json.getInt("status") != 0) {
                System.out.println(json.getString("msg"));
                throw new RRException(remark+":"+json.getString("msg"));
            } else {
                resultarr = (JSONObject) json.opt("result");
                Iterator itt = resultarr.keys();
                while (itt.hasNext()) {
                    String key = itt.next().toString();
                    String value = resultarr.getString(key);
                    System.out.println(key + ":" + value);
                }
            
            
        } 
        return resultarr;
    }
    
    /**
     * 根据身份证正面图片获取身份证正面信息
     * @param RemoteUrl
     * @return
     * @throws Exception 
     */
    public static FacedeIdCardInfoVO getFacedeIdCardInfo(byte[] facedeByte) throws Exception {
    	JSONObject info = Get(facedeByte,2,"身份证正面");
    	FacedeIdCardInfoVO f = new FacedeIdCardInfoVO();
    	f.setName(info.getString("name"));
    	f.setSex(info.getString("sex"));
    	f.setNation(info.getString("nation"));
    	f.setBirth(info.getString("birth"));
    	f.setAddress(info.getString("address"));
    	f.setNumber(info.getString("number"));
    	f.setRetain(info.getString("retain"));
    	return f;
    }
    
    public static backIdCardInfoVO getBackIdCardInfo(byte[] facedeByte) throws Exception {
    	JSONObject info = Get(facedeByte,3,"身份证反面");
    	backIdCardInfoVO b = new backIdCardInfoVO();
    	b.setEnddate(info.getString("enddate"));
    	b.setIssueorg(info.getString("issueorg"));
    	b.setStartdate(info.getString("startdate"));
    	b.setRetain(info.getString("retain"));
    	return b;
    }
    
    public static String getBase64Bybyte( byte[] imgFile) {
        // 加密
        byte[] encodeBase64 = Base64.encodeBase64(imgFile);
        return new String(encodeBase64);
    }
    
    /**
     * 图片转为Base64编码格式
     * 
     * @param imgFile
     * @return
     */
    public static String getBase64(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        byte[] encodeBase64 = Base64.encodeBase64(data);
        return new String(encodeBase64);
    }
    
    public static byte[] getByteByRemoteUrl(String remoteUrl) {
    	return getImageFromNetByUrl(remoteUrl);
    }
    
    
    /**  
     * 根据地址获得数据的字节流  
     * @param strUrl 网络连接地址  
     * @return  
     */    
    public static byte[] getImageFromNetByUrl(String strUrl){    
        try {
        	java.net.URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
            conn.setRequestMethod("GET");    
            conn.setConnectTimeout(5 * 1000);    
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据    
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据  
            conn.disconnect();
            return btImg;    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
        return null;    
    } 
    
    /**  
     * 从输入流中获取数据  
     * @param inStream 输入流  
     * @return  
     * @throws Exception  
     */    
    public static byte[] readInputStream(InputStream inStream) throws Exception{    
        ByteArrayOutputStream outStream = null;
		try {
			outStream = new ByteArrayOutputStream();    
			byte[] buffer = new byte[1024];    
			int len = 0;    
			while( (len=inStream.read(buffer)) != -1 ){    
			    outStream.write(buffer, 0, len);    
			}    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(inStream != null) {
				inStream.close();
			}
		}    
		
         byte[] byteArray = outStream.toByteArray(); 
         outStream.close();
         return byteArray;
    }    
  
    public static void main(String[] args) {
		byte[] imageFromNetByUrl = getImageFromNetByUrl("http://img.huisonglin.top/FoFr6g5OWJKXdYHiimNbqlzLrIzs");
	}

}
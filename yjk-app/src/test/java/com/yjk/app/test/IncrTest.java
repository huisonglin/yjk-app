package com.yjk.app.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.yjk.app.common.SelfIncreasingIdService;
import com.yjk.app.vo.MyListVO;
import com.yjk.common.dao.DeviceMapper;

import redis.clients.jedis.Jedis;


@RunWith(SpringRunner.class)  
@SpringBootTest  
public class IncrTest {

	public static void main(String[] args) throws Exception {

		Jedis jedis = new Jedis("YunJiKuRedis.redis.cache.chinacloudapi.cn", 6379);
		jedis.auth("/jq+6e4CyqOca8BSAZhv6XqcORRFLDSkqAVhc7V/dt4=");
		while(true) {
			try {
				List<String> brpop = jedis.brpop(0, "bicyleNotify");
				if(brpop.size() == 2) {
					Thread.sleep(1000);
					String msgBody = brpop.get(1);
					System.out.println("接收异步任务： " + msgBody);
				}

			} catch (Exception e) {
				e.printStackTrace();
				if(jedis != null) {
					jedis.close();
				}
				System.out.println("正在尝试重连！！！");
				try {
					Thread.sleep(5000);
					try {
						jedis = new Jedis("YunJiKuRedis.redis.cache.chinacloudapi.cn", 6379);
						jedis.auth("/jq+6e4CyqOca8BSAZhv6XqcORRFLDSkqAVhc7V/dt4=");
					} catch (Exception e1) {
						e1.printStackTrace();
						// TODO Auto-generated catch block
						System.out.println("从新建立连接还是失败！！！！");
					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		}
	
	}

	private void dealMessage() {}
}

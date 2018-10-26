package com.qianggou;

import java.util.List;
import java.util.Random;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class MyRunnable implements Runnable{

	 String watchkeys = "watchkeys";// 监视keys
	    String userinfo;
	    public MyRunnable() {
	    }
	    public MyRunnable(String uinfo) {
	        this.userinfo=uinfo;
	    }
		public void run() {
    	    Jedis jedis = new Jedis("39.106.4.135", 6379);
    	    jedis.auth("19930711");
	        try {
	            while(!tryQg(jedis)) {
	            	Thread.sleep(new Random().nextInt(300));
	            }
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            jedis.close();
	        }
			
		}
		private Boolean tryQg(Jedis jedis) {
			jedis.watch(watchkeys);// watchkeys

			String val = jedis.get(watchkeys);
			int valint = Integer.valueOf(val);
			
			if (valint <= 100 && valint>=1) {
			
	
			   // tx.incr("watchkeys");
			     int count = new Random().nextInt(5)+1;
			     if(valint-count<0) {
			        String failuserifo = "fail"+userinfo;
			        String failinfo="用户：" + failuserifo + "商品争抢失败，抢购失败(库存不足)";
			        System.out.println(failinfo);
			    	 return false;
			     }
			    Transaction tx = jedis.multi();// 开启事务
			    tx.incrBy("watchkeys", -count);
 
			    List<Object> list = tx.exec();// 提交事务，如果此时watchkeys被改动了，则返回null
			     
			    if (list == null ||list.size()==0) {
			        String failuserifo = "fail"+userinfo;
			        String failinfo="用户：" + failuserifo + "商品争抢失败，抢购失败(尝试重新抢购)";
			        System.out.println(failinfo);
			        /* 抢购失败业务逻辑 */
			        jedis.setnx(failuserifo, failinfo);
			        return false;
			    } else {
			        for(Object succ : list){
			        	if(Integer.parseInt(succ.toString())<0) {
			        		String failuserifo = "fail"+userinfo;
					        String failinfo="用户：" + failuserifo + "商品争抢失败，抢购失败(库存不足)";
					        System.out.println(failinfo);
					        return true;
			        	}
			             String succuserifo ="succ"+succ.toString() +userinfo ;
			             String succinfo="用户：" + succuserifo + "抢购成功,成功抢购了"+count+"件，当前商品剩余:"
			                     + succ+"件" ;
			             System.out.println(succinfo);
			             /* 抢购成功业务逻辑 */
			             jedis.setnx(succuserifo, succinfo);
			        }
			        return true;
			    }
 
			} else {
			    String failuserifo ="kcfail" +  userinfo;
			    String failinfo1="用户：" + failuserifo + "商品被抢购完毕，抢购失败";
			    System.out.println(failinfo1);
			    jedis.setnx(failuserifo, failinfo1);
			    // Thread.sleep(500);
			    return true;
			}
		}
		

}

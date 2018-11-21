package com.yjk.app;

import java.util.Date;
import java.util.List;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import tk.mybatis.mapper.entity.Example;

@RunWith(SpringRunner.class)  
@SpringBootTest  
public class ActiveMQTest {
	
/*	@Autowired
	StudentMapper studentMapper;
	
	@Test
	public void test() {
		
		Student student = new Student();
		student.setAddress("互联网产业园");
		student.setName("惠松林");
		studentMapper.insert(student);
		Example example = new Example(Student.class);
		example.createCriteria().andEqualTo("address", "互联网产业园");
		List<Student> selectByExample = studentMapper.selectByExample(example);
		for (Student student : selectByExample) {
			System.out.println(student);
		}
	}*/

	@Autowired
	JmsTemplate jmsTemplate;
	
	@Test
	public void test() {
		
		/*jmsTemplate.convertAndSend(new ActiveMQQueue("xcxTmeplateNotify"),"你好");*/
		
/*		Object message1 = "corn消息内容：" + "nihaoa";
		//分 时 天 月 星期几

		jmsTemplate.convertAndSend(new ActiveMQQueue("myFirstMessage"),message1, new ScheduleMessagePostProcessor("40 22 * * *"));
		System.out.println("消息1：[" + message1 + "] 延时发送成功！");
		 
		jmsTemplate.convertAndSend(new ActiveMQQueue("myFirstMessage"),message1, new ScheduleMessagePostProcessor("50 22 * * *"));
		System.out.println("消息1：[" + message1 + "] 延时发送成功！");
		 
		//设置创建连接的工厂
		   //JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		    //优化连接工厂，这里应用缓存池 连接工厂就即可
		    //设置默认消费topic
		  //jmsTemplate.setDefaultDestination(topic());
		    //设置发布订阅消息类型


		    //deliveryMode, priority, timeToLive 的开关，要生效，必须配置为true，默认false
		    jmsTemplate.setExplicitQosEnabled(true);
		    //DeliveryMode.NON_PERSISTENT=1:非持久 ; DeliveryMode.PERSISTENT=2:持久
		    jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);

		    //默认不开启事务
		    jmsTemplate.setSessionTransacted(true);
		    System.out.println("是否开启事务"+jmsTemplate.isSessionTransacted());
		    //如果session带有事务，并且事务成功提交，则消息被自动签收。如果事务回滚，则消息会被再次传送。
		    

		    //不带事务的session的签收方式，取决于session的配置。
		    //默认消息确认方式为1，即AUTO_ACKNOWLEDGE
		    System.out.println("是否消息确认方式"+jmsTemplate.getSessionAcknowledgeMode());

		    //消息的应答方式，需要手动确认，此时SessionTransacted必须被设置为false，且为Session.CLIENT_ACKNOWLEDGE模式
		    //Session.AUTO_ACKNOWLEDGE  消息自动签收
		    //Session.CLIENT_ACKNOWLEDGE  客户端调用acknowledge方法手动签收
		    //Session.DUPS_OK_ACKNOWLEDGE 不必必须签收，消息可能会重复发送
		    jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

		Object message2 = "message：" + "我过来啊，接住哈";
		jmsTemplate.convertAndSend(new ActiveMQQueue("myFirstMessage"),message2, new ScheduleMessagePostProcessor(1 * 1000));//延时30秒
		jmsTemplate.convertAndSend(new ActiveMQQueue("myFirstMessage"),message2, new ScheduleMessagePostProcessor(3600 * 24 * 1000));//延时24小时
		System.out.println("消息2：[" + message2 + "] 延时发送成功！");*/
	}
}

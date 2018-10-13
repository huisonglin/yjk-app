package com.activemq;
import javax.jms.JMSException;
import javax.jms.Message;
 
import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.MessagePostProcessor;
 
/**
 * MQ延时投递处理器（注：ActiveMQ的配置文件中，要配置schedulerSupport="true"，否则不起作用）
 * by: 杨俊明 2016-06-16
 */
public class ScheduleMessagePostProcessor implements MessagePostProcessor {
 
    private long delay = 0l;
 
    private String corn = null;
 
    public ScheduleMessagePostProcessor(long delay) {
        this.delay = delay;
    }
 
    public ScheduleMessagePostProcessor(String cron) {
        this.corn = cron;
    }
 
    public Message postProcessMessage(Message message) throws JMSException {
        if (delay > 0) {
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
        }
        if (corn!=null) {
            message.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, corn);
        }
        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 7*1000); //延迟7秒投递
        message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 5);//之后再投递5次
        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 3*1000);//每次间隔3秒
        
        return message;
    }
 
}
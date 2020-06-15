package com.kang.common.constant;

/**
 * <p>Title: RabbitConstant</p>  
 * <p>Description: Rabbit常量</p>  
 * @author chaokang  
 * @date 2018年12月10日
 */
public class RabbitConstants {
	
    /**
     * 延时队列
     * 发送到该队列的message会在一段时间后过期进入到delay_process_queue
     * 队列里所有的message都有统一的失效时间
     */
    public static final String DELAY_QUEUE   = "delay.queue";

    /**
     * 延时的交换器
     */
    public static final String DELAY_EXCHANGE = "delay.queue.exchange";
    /**
     * 超时时间
     */
    public static final Long QUEUE_EXPIRATION = 5000L;
    
    /**
     * 延时队列路由键(路由模式Key)
     */
    public static final String DELAY_ROUTING_KEY = "delay.queue.key";
	
	
    /**
     * 实际消费队列
     * message失效后进入的队列，也就是实际的消费队列
     */
    public static final String PROCESS_QUEUE_1 = "process.queue.one";
    public static final String PROCESS_QUEUE_2 = "process.queue.two";

    /**
     * 处理的交换器
     */
    public static final String PROCESS_EXCHANGE = "process.queue.exchange";
    
    /**
     * 消费队列路由键(路由模式Key)
     */
    public static final String PROCESS_ROUTING_KEY = "process.queue.key";
	
	
}

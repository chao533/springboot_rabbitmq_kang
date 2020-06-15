package com.kang.config.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kang.common.constant.RabbitConstants;

/**
 * <p>Title: RabbitmqConfig</p>
 * <p>Description: 延时队列<p>
 * @author ChaoKang
 * @date 2020年6月15日
 */
@Configuration
public class RabbitmqConfig {

	/**
	 * <p>Title: delayQueue</p>
	 * <p>Description: 配置延时队列</p>
	 * @param @return
	 */
	@Bean
	public Queue delayQueue() {
		return QueueBuilder.durable(RabbitConstants.DELAY_QUEUE)
				// DLX，dead letter发送到的exchange ,设置死信队列交换器到处理交换器
				.withArgument("x-dead-letter-exchange", RabbitConstants.PROCESS_EXCHANGE)
				// dead letter携带的routing key，配置处理队列的路由key
				.withArgument("x-dead-letter-routing-key", RabbitConstants.PROCESS_ROUTING_KEY)
				// 设置过期时间
				.withArgument("x-message-ttl", RabbitConstants.QUEUE_EXPIRATION).build();
	}

	/**
	 * <p>Title: delayExchange</p>
	 * <p>Description: 配置延时交换器</p>
	 * @param @return
	 */
	@Bean
	public DirectExchange delayExchange() {
		return new DirectExchange(RabbitConstants.DELAY_EXCHANGE);
	}

	/**
	 * <p>Title: queueTTLBinding</p>
	 * <p>Description: 将delayQueue2绑定延时交换机中，routingKey为队列名称</p>
	 * @param @return
	 */
	@Bean
	public Binding queueTTLBinding() {
        return BindingBuilder
                .bind(delayQueue())
                .to(delayExchange())
                .with(RabbitConstants.DELAY_ROUTING_KEY);
    }
	
	
	
	/**
	 * <p>Title: delayProcess</p>
	 * <p>Description: 设置处理队列</p>
	 * @param @return
	 */
    @Bean 
    public Queue delayProcess_1() { 
        return QueueBuilder
                .durable(RabbitConstants.PROCESS_QUEUE_1) 
                .build(); 
    }
    
    @Bean 
    public Queue delayProcess_2() { 
        return QueueBuilder
                .durable(RabbitConstants.PROCESS_QUEUE_2) 
                .build(); 
    }
    
    /**
     * <p>Title: processExchange</p>
     * <p>Description: 配置处理交换器</p>
     * @param @return
     */
    @Bean 
    public DirectExchange processExchange() { 
        return new DirectExchange(RabbitConstants.PROCESS_EXCHANGE); 
    }
    
    /**
     * <p>Title: processBinding</p>
     * <p>Description: 将DLX绑定到实际消费队列</p>
     * @param @return
     */
    @Bean 
    public Binding processBinding_1() {
        return BindingBuilder
                .bind(delayProcess_1()) 
                .to(processExchange()) 
                .with(RabbitConstants.PROCESS_ROUTING_KEY); 
    }
    
    @Bean 
    public Binding processBinding_2() {
        return BindingBuilder
                .bind(delayProcess_2()) 
                .to(processExchange()) 
                .with(RabbitConstants.PROCESS_ROUTING_KEY); 
    }

}

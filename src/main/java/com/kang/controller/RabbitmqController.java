package com.kang.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.config.mq.producer.RabbitmqProducer;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;

/**
　 * <p>Title: RabbitmqController</p> 
　 * <p>Description: Rabbitmq操作</p> 
　 * @author CK 
　 * @date 2020年4月26日
 */
@RestController
@RequestMapping(value="/rabbitmq")
public class RabbitmqController {

	@Autowired
	private RabbitmqProducer rabbitmqProducer;

	
	
	@RequestMapping(value="/sendMessage",method=RequestMethod.GET)
	public Message<?> sendMessage() {
		Map<String, Object> params = MapUtil.builder(new HashMap<String,Object>())
			.put("id", IdUtil.simpleUUID()).put("name", "张三").put("birthday", DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN)).build();
		rabbitmqProducer.sendMessage(JSONUtil.toJsonStr(params));
		return new Message<>(ErrorCode.SUCCESS);
	}
}

package com.perfect.community.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@Slf4j
public class EndPointsChecker {

//	@EventListener
//	public void getHandlerMethod(ContextRefreshedEvent event) {
//		ApplicationContext applicationContext = event.getApplicationContext();
//		RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
//				.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
//		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping
//				.getHandlerMethods();
//		map.forEach((key, value) -> log.info(key + "," + value));
//	}
}

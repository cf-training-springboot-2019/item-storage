package com.training.springboot.itemstorage.utils.interceptor;

import static com.training.springboot.itemstorage.utils.constant.ItemStorageConstant.LOGGING_HANDLER_PROCESS_TIME_MSG;

import com.training.springboot.itemstorage.utils.constant.ItemStorageConstant;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoggingHandler implements HandlerInterceptor {

	StopWatch stopWatch;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		log.info(
				String.format(ItemStorageConstant.LOGGING_HANDLER_INBOUND_MSG, request.getMethod(), request.getRequestURI(),
						Instant.now()));
		stopWatch = new StopWatch();
		stopWatch.start();
		return true;
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) {
		log.info(String.format(ItemStorageConstant.LOGGING_HANDLER_OUTBOUND_MSG, response.getStatus(), Instant.now()));
		stopWatch.stop();
		log.info(String.format(LOGGING_HANDLER_PROCESS_TIME_MSG, stopWatch.getTotalTimeMillis()));
	}

}

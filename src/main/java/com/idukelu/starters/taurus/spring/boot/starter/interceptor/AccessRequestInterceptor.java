package com.idukelu.starters.taurus.spring.boot.starter.interceptor;

import com.idukelu.starters.taurus.spring.boot.starter.util.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.Instant;

/**
 * @author duke
 */
@Slf4j
public class AccessRequestInterceptor implements HandlerInterceptor {

    private ThreadLocal<Instant> start = new ThreadLocal<>();
    private ThreadLocal<Instant> end = new ThreadLocal<>();

    public AccessRequestInterceptor() {
        if (log.isDebugEnabled()) {
            log.debug("register success: {}", this.getClass().getName());
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        start.set(Instant.now());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        end.set(Instant.now());
        log.info("RequestURL: {} | {} ms",
                UrlUtils.getConversationInfo(request, response),
                Duration.between(start.get(), end.get()).toMillis());
    }
}

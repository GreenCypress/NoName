package com.fjgcxy.xyb.auto.clock.in.auth.aop;


import com.fjgcxy.xyb.auto.clock.in.auth.anno.AuthLogin;
import com.fjgcxy.xyb.auto.clock.in.exception.NoAuthException;
import com.fjgcxy.xyb.auto.clock.in.model.constant.Constants;
import com.fjgcxy.xyb.auto.clock.in.model.vo.VoUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    @Bean
    public AppUserTokenAuthAop getAppUserTokenAuth(RedisTemplate<String, Object> redisTemplate) {
        return new AppUserTokenAuthAop(redisTemplate);
    }

    @Aspect
    public static class AppUserTokenAuthAop {

        private final RedisTemplate<String, Object> redisTemplate;


        public AppUserTokenAuthAop(RedisTemplate<String, Object> redisTemplate) {
            this.redisTemplate = redisTemplate;
        }

        /**
         * Return request current thread bound or null if none bound.
         *
         * @return Current request or null
         */
        private HttpServletRequest currentRequest() {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return Optional.ofNullable(servletRequestAttributes).map(ServletRequestAttributes::getRequest).orElse(null);
        }


        // 切入点@annotation(net.crisps.cloud.example.annotation.AopTest) 扫描在我们加了自定义注解的地方执行
//        @Pointcut("@annotation(com.dqx.jq.support.auth.anno.anno.AuthLogin)")
//        public void getHeaderToken() {
//
//        }

        @Pointcut("@annotation(com.fjgcxy.xyb.auto.clock.in.auth.anno.AuthLogin)")
        public void getAnnotation() {
        }

        @Before("getAnnotation() && @annotation(authLogin)")
        public void before(AuthLogin authLogin) {
//            System.out.println("before------");
            HttpServletRequest request = currentRequest();
            String userToken = request.getHeader("Authorization");
            if (authLogin.isRequire() && (StringUtils.isBlank(userToken) || redisTemplate.opsForValue().get(userToken) == null)) {
                throw new NoAuthException();
            }

            if (StringUtils.isBlank(userToken) || userToken.split(":").length < 3) {
                return;
            }
            String userId = userToken.split(":")[1];
            request.getSession().setAttribute(Constants.TOKEN_USER_ID_KEY, userId);
        }

//        @After("@annotation(com.dqx.jq.support.auth.anno.anno.AuthLogin)")
//        public void after() {
//            System.out.println("after------");
//        }

//        @AfterReturning(returning = "data", pointcut = "@annotation(com.dqx.jq.support.auth.anno.anno.AuthLogin)")
//        public void afterReturning(JoinPoint joinPoint, Object data) {
//            System.out.println(data.toString());
//        }

        // 环绕切点执行此方法
//        @Around("getHeaderToken()")
//        public Object before(ProceedingJoinPoint joinPoint) {
//            // 获取到原本接口的方法,看是否需要调用接口的原本方法,不使用就直接返回了,不会执行原有接口的方法
//            CacheOperationInvoker cacheOperationInvoker = getCacheOperationInvoker(joinPoint);
//            cacheOperationInvoker.invoke();
//            return null;
//        }
//
//        private CacheOperationInvoker getCacheOperationInvoker(ProceedingJoinPoint joinPoint) {
//            return () -> {
//                try {
//                    return joinPoint.proceed();
//                } catch (Throwable ex) {
//                    throw new CacheOperationInvoker.ThrowableWrapper(ex);
//                }
//            };
//        }
    }
}


package com.mark.test.framework.core.aop;


import com.mark.test.framework.core.annotation.DataSource;
import com.mark.test.framework.core.utils.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by mark .
 * Data   : 2017/12/25
 * Desc   :
 * @author mark
 */
@Aspect
@Component
public class DataSourceAspect {
    private Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut(value = "execution(* *..service..*.*(..))")
    public void aspect(){}


    /**
     * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
     *
     * @param point
     * @throws Exception
     */
    @Before("aspect()")
    public void intercept(JoinPoint point) {
        logger.info("[Aspect] : {}",point.toString());
        Class<?> target = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 默认使用目标类型的注解，如果没有则使用其实现接口的注解
        for (Class<?> clazz : target.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target, signature.getMethod());
    }

    @After("aspect()")
    public void afterPointcut(){
//        logger.info("point cut finished");
    }


    /**
     * 提取目标对象方法注解和类型注解中的数据源标识
     *
     * @param clazz
     * @param method
     */
    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            // 默认使用类型注解
            if (clazz.isAnnotationPresent(DataSource.class)) {
                DataSource source = clazz.getAnnotation(DataSource.class);
                logger.info("Class DataSource annotation: {}" , source.value());
                DataSourceContextHolder.setDataSourceType(source.value());
            }
            // 方法注解可以覆盖类型注解
            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource source = m.getAnnotation(DataSource.class);
                logger.info("Method DataSource annotation: {}" , source.value());
                DataSourceContextHolder.setDataSourceType(source.value());
            }
        } catch (Exception e) {
           logger.info(clazz + ":" + e.getMessage());
        }
    }
}

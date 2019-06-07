package com.namedlock.springaop;

import com.google.common.base.Stopwatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author jingjing.zhijj
 *
 */
public class AopAroundInterceptor {

    private static final Logger logger = LoggerFactory.getLogger("aop.interceptor.log");

    private AopFilter aopFilter;
    private AopResultAcceptor aopResultAcceptor;

    public Object intercept(ProceedingJoinPoint jp) throws Throwable {
        if(aopFilter !=null && aopFilter.accept(jp)){
            Stopwatch stopwatch = Stopwatch.createStarted();
            Object ret =  jp.proceed();
            long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            if(aopResultAcceptor !=null){
                aopResultAcceptor.onResult(jp,ret, elapsed);
            }
            return ret;
        }

        return jp.proceed();

    }

    public AopFilter getAopFilter() {
        return aopFilter;
    }

    public void setAopFilter(AopFilter aopFilter) {
        this.aopFilter = aopFilter;
    }

    public AopResultAcceptor getAopResultAcceptor() {
        return aopResultAcceptor;
    }

    public void setAopResultAcceptor(AopResultAcceptor aopResultAcceptor) {
        this.aopResultAcceptor = aopResultAcceptor;
    }
}

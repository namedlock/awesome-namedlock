package com.namedlock.springaop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author jingjing.zhijj
 */
public interface AopResultAcceptor {
    /**
     * do whatever you want on the result
     * @param joinPoint
     * @param ret
     * @param execTime
     */
    void onResult(ProceedingJoinPoint joinPoint, Object ret, long execTime);
}

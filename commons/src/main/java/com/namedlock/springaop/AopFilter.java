package com.namedlock.springaop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author jingjing.zhijj
 */
public interface AopFilter {


    /**
     * If we should intercept, then return true. else return false.
     * @return
     */
    boolean accept(ProceedingJoinPoint joinPoint);
}

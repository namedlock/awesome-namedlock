package com.namedlock.springaop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;

public class KeeperFilter implements AopFilter {
    @Override
    public boolean accept(ProceedingJoinPoint joinPoint) {
        Object [] args = joinPoint.getArgs();
        if(args!=null && args.length> 0){
            String keeper = String.valueOf(args[0]);
            return StringUtils.equalsIgnoreCase("jonh", keeper);
        }
        return false;
    }
}

package com.namedlock.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/aop/namedlock-aop.xml"})
public class DoorkeeperTest implements AopResultAcceptor {
    @Autowired
    private AopAroundInterceptor interceptor;

    @Autowired
    private Doorkeeper doorkeeper;

    @Before
    public void before() {
        interceptor.setAopResultAcceptor(this);
    }

    @Test
    public void testIntercept() {
        doorkeeper.countDoors("jonh");
    }

    @Override
    public void onResult(ProceedingJoinPoint joinPoint, Object ret, long execTime) {
        System.out.println(String.format("onResult result:%d exec time:%d", ret, execTime));
    }
}

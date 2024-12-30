package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with an @Before advice
// all method name addAccount
//    @Before("execution(public void addAccount())")
    // method into class
//    @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
//    @Before("execution(public void add*())")
//    @Before("execution(public * add*(..))")
//    @Before("execution(public void updateAccount())")
    @Before("execution(public void com.luv2code.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice(){
//        System.out.println("\n=======>>> Executing @Before advice on addAccount()");
        System.out.println("\n=========>>> Executing @Before advice on method");


    }
}

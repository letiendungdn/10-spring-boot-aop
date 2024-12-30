package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


@Aspect
@Component
@Order(2)
class MyDemoLoggingAspect {

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = null;


        try{
            theProceedingJoinPoint.proceed();
        } catch (Exception exc) {
            // log the exception
            System.out.println(exc.getMessage());

            // give user a custom message
            result = "Major accident! But no worries, your private AOP helicopter is on the way!" ;
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>>> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on Method: " + method + "\n");
        // log the exception
        System.out.println("\n=====>>> Executing exception is: " + theExc + "\n");
    }

    // add a new advice for @AfterReturning on the findAccounts method

    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        // print out which method we are

    }

    // this is where we add all of our related advices for logging

    // let's start with an @Before advice
// all method name addAccount
//    @Before("execution(public void addAccount())")
    // method into class
//    @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
//    @Before("execution(public void add*())")
//    @Before("execution(public * add*(..))")
//    @Before("execution(public void updateAccount())")


    @Before("com.luv2code.aopdemo.aspect.LuvAppExpressions.forDaoPackageNoGetterSetter()")

//    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
//        System.out.println("\n=======>>> Executing @Before advice on addAccount()");
        System.out.println("\n=========>>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        // display method arguments

        // get args
        Object[] args = theJoinPoint.getArgs();

        // loop thru args
        for (Object tempArg: args) {
            System.out.println(tempArg);

            if (tempArg instanceof Account theAccount) {

                // downcast and print Account specific stuff

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }

    }

////    @Before("forDaoPackage()")
//@Before("forDaoPackageNoGetterSetter()")
//    public void performApiAnalytics() {
////        System.out.println("\n=======>>> Performing Api Analytics");
//    System.out.println("\n=======>>> Logging to Cloud in async fashion");
//    }
}

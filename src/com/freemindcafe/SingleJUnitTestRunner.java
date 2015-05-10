package com.freemindcafe;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class SingleJUnitTestRunner {
    public static void main(String... args) throws ClassNotFoundException {
        String[] classAndMethod = args[0].split("#");
        //if we want to run a single test case. We should provide the TestClass#testMethod
        if(classAndMethod.length == 2){
	        Request request = Request.method(Class.forName(classAndMethod[0]),
	                classAndMethod[1]);
	        JUnitCore jUnitCore = new JUnitCore();
	        RunListener listener= new TextListener(System.out);
	        jUnitCore.addListener(listener);
	        Result result = jUnitCore.run(request);
	        System.exit(result.wasSuccessful() ? 0 : 1);
        }
        //otherwise run the normal Junit core
        else{
        	JUnitCore.main(args);
        }
    }
}
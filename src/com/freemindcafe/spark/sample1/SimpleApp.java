package com.freemindcafe.spark.sample1;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.junit.Test;

public class SimpleApp {
	
	public static void main(String[] args) throws Exception{
		
		//The following line is needed to overcome an exception
		//http://qnalist.com/questions/4994960/run-spark-unit-test-on-windows-7
		File currentDir = new File(".");
		String currentDirPath = currentDir.getAbsolutePath();
		System.setProperty("hadoop.home.dir", currentDirPath);
		
		Properties sparkProperties = new Properties();
		sparkProperties.load(new FileInputStream("src/com/freemindcafe/spark/simple/spark-defaults.conf"));
		sparkProperties.load(new FileInputStream("src/com/freemindcafe/spark/simple/log4j.properties"));
		
		SparkConf sparkConf = new SparkConf();
		sparkProperties.forEach((k, v) -> sparkConf.set(k.toString(), v.toString()));
		sparkConf.setAppName("sample application");
		JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
		
		String logFile = "src/com/freemindcafe/spark/simple/log.txt";
		JavaRDD<String> logData = sparkContext.textFile(logFile).cache();

		long numAs = logData.filter(new Function<String, Boolean>() {
			public Boolean call(String s) {
				return s.contains("a");
			}
		}).count();

		long numBs = logData.filter(new Function<String, Boolean>() {
			public Boolean call(String s) {
				return s.contains("b");
			}
		}).count();

		System.out.println("Lines with a: " + numAs + ", lines with b: "
				+ numBs);
	}
	

}

package com.freemindcafe.spark.sample4;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;


public class SimpleApp {
	
	public static void main(String[] args) throws Exception{
		Properties sparkProperties = new Properties();
		sparkProperties.load(new FileInputStream("subhash/spark-defaults.conf"));
		sparkProperties.load(new FileInputStream("subhash/log4j.properties"));
		
		SparkConf sparkConf = new SparkConf();
		sparkProperties.forEach((k, v) -> sparkConf.set(k.toString(), v.toString()));
		sparkConf.set("spark.executor.extraClassPath", "/root/samples-spark.jar");
		JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
		
		String logFile = "subhash/log.txt";
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
		
		logData.foreach(str ->{
			System.out.println(str);
		});

		System.out.println("Lines with a: " + numAs + ", lines with b: "
				+ numBs);
	}	

}

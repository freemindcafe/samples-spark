/**
 * A very basic sample to bootstrap spark.
 * 
 * spark.master in  spark-defaults.conf has been set to spark://yamuna:7077. Hence spark will launch a local vm 
 * execute below steps execute code in spark cluster 
 *	1) make a jar file name samples-spark.jar
 *	2) copy log.txt, log4j.properties, spark-default.conf under /root/subhash directory and the jar file as well
 *	3) start spark cluster
 *	4) execute command - "java -classpath samples-spark.jar:spark-1.4.0-bin-hadoop2.4/lib/spark-assembly-1.4.0-hadoop2.4.0.jar com.freemindcafe.spark.sample4.SimpleApp".
 */
package com.freemindcafe.spark.sample4;
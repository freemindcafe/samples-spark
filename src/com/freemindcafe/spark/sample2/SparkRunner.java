package com.freemindcafe.spark.sample2;

public class SparkRunner {
	
	public <Record> void run(IRecordCreator<Record> creator, IRecordProcessorFactory<Record> factory){
		Record record = creator.create();
		factory.newRecordProcesssor(record).process(record);
	}

}

package com.freemindcafe.spark.sample2;

public interface IRecordProcessorFactory<Record> {
	
	public IRecordProcessor<Record> newRecordProcesssor(Record record);

}

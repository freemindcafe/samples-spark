package com.freemindcafe.spark.sample2;

import org.junit.Test;

public class LambdaTest {
	
	@Test
	public void test1(){
		
//		new Thread(new Runnable(){
//			@Override
//			public void run() {
//				for(int i=1; i<=10; i++){
//					System.out.println(i);
//				}
//			}			
//		}).start();
		
		new Thread(
				()-> {
					for(int i=1; i<=10; i++){
						System.out.println(i);
					}					
				}
				).start();
		
	}
	
//	@Test
//	public void test2(){
//		SparkRunner abc = new SparkRunner();
//		abc.run(() -> {new Employee(1);}, 
//				(employee) -> { });
//	}
	
	
	@Test
	public void test3(){
		SparkRunner abc = new SparkRunner();
		abc.run(new IRecordCreator<Employee>() {

			@Override
			public Employee create() {
				return new Employee(1);
			}
		}, new IRecordProcessorFactory<Employee>() {

			@Override
			public IRecordProcessor<Employee> newRecordProcesssor(Employee employee) {
				return new IRecordProcessor<Employee>() {
					
					@Override
					public void process(Employee employee) {
						System.out.println("Employee Id - " + employee.getId());
					}
				};
			}
		} );
	}
		
}

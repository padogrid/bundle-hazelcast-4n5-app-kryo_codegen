package org.hazelcast.addon.demo.kryo;

import org.hazelcast.addon.tools.WrapperGenerator;

/**
 * This class shows you how to run WrapperGenerator programmatically.
 * 
 * @author dpark
 *
 */
public class MyWrapperGenerator {

	public static void main(String[] args) throws Exception {

		// All paths are relative to the working directory.	
		String sp = "org.hazelcast.demo.nw.data.avro.generated";
		String tp = " org.hazelcast.demo.nw.data.avro";
		String dir = "src/main/java";
		String jar = "lib/app-kryo-codegen-hazelcast-4-1.0.0.jar";
		
		WrapperGenerator generator = new WrapperGenerator(sp, tp, jar, dir);
		generator.generateWrappers();
	}
}

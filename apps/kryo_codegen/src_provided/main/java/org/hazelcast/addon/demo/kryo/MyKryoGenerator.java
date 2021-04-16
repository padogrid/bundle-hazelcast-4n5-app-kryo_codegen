package org.hazelcast.addon.demo.kryo;

import com.netcrest.padogrid.tools.HazelcastKryoGenerator;

/**
 * This class shows you how to run HazelcastKryoGenerator programmatically.
 * 
 * @author dpark
 *
 */
public class MyKryoGenerator {

	public static void main(String[] args) throws Exception {

		// All paths are relative to the working directory.
		String packageName = " org.hazelcast.demo.nw.data.avro";
		String jarPath = "lib/app-kryo-codegen-hazelcast-4-1.0.0.jar";
		int typeId = 1200;
		String srcDir = "src/main/java";

		HazelcastKryoGenerator generator = new HazelcastKryoGenerator(packageName, jarPath, typeId, srcDir);
		generator.generateKryoSerializer();
	}
}

package org.hazelcast.addon.demo.kryo;

import org.hazelcast.demo.nw.data.avro.Customer;

import com.github.javafaker.Address;
import com.github.javafaker.Company;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class CustomerIngester {
	private static Faker faker = new Faker();
	
	private static String MAP_NAME = "nw/customers";
	private static int OBJECT_COUNT = 100;

	public static Customer createCustomer() {
		Customer customer = new Customer();
		Address address = faker.address();
		Company company = faker.company();
		PhoneNumber phone = faker.phoneNumber();
		customer.setAddress(address.fullAddress());
		customer.setCity(address.city());
		customer.setCompanyName(company.name());
		customer.setContactName(address.lastName());
		customer.setContactTitle(faker.job().title());
		customer.setCountry(address.country());
		customer.setCustomerId(faker.idNumber().invalidSvSeSsn());
		customer.setFax(phone.cellPhone());
		customer.setPhone(phone.phoneNumber());
		customer.setPostalCode(address.zipCode());
		customer.setRegion(address.stateAbbr());
		return customer;
	}

	public static void main(String...args) {
		HazelcastInstance hz = HazelcastClient.newHazelcastClient();
		IMap<CharSequence, Customer> map = hz.getMap(MAP_NAME);
		for (int i = 0; i < OBJECT_COUNT; i++) {
			Customer customer = createCustomer();
			map.put(customer.getCustomerId(), customer);
		}
		hz.shutdown();
		System.out.println("Data Class: " + Customer.class.getName());
		System.out.println("  Ingested: " + OBJECT_COUNT);
		System.out.println("       Map: " + MAP_NAME);
	}
}

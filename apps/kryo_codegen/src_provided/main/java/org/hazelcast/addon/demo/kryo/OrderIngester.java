package org.hazelcast.addon.demo.kryo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.hazelcast.demo.nw.data.avro.Order;

import com.github.javafaker.Address;
import com.github.javafaker.Company;
import com.github.javafaker.Faker;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class OrderIngester {
	private static Faker faker = new Faker();
	private static Random random = new Random();

	private static String MAP_NAME = "nw/orders";
	private static int OBJECT_COUNT = 100;

	public static Order createOrder() {
		Order order = new Order();
		Company company = faker.company();
		order.setCustomerId(faker.idNumber().invalidSvSeSsn());
		order.setEmployeeId(faker.idNumber().invalidSvSeSsn());
		order.setFreight(200 * random.nextDouble());
		order.setOrderDate(faker.date().past(7, TimeUnit.DAYS));
		order.setOrderId(faker.idNumber().invalidSvSeSsn());
		order.setRequiredDate(faker.date().future(20, TimeUnit.DAYS));
		Address address = faker.address();
		order.setShipAddress(address.fullAddress());
		order.setShipCity(address.city());
		order.setShipCountry(address.country());
		order.setShipName(company.name());
		order.setShippedDate(faker.date().past(4, TimeUnit.DAYS));
		order.setShipPostalCode(address.zipCode());
		order.setShipRegion(address.stateAbbr());
		order.setShipVia(Integer.toString(random.nextInt(5) + 1));
		return order;
	}

	public static void main(String... args) {
		HazelcastInstance hz = HazelcastClient.newHazelcastClient();
		IMap<CharSequence, Order> map = hz.getMap(MAP_NAME);
		for (int i = 0; i < OBJECT_COUNT; i++) {
			Order order = createOrder();
			map.put(order.getCustomerId(), order);
		}
		hz.shutdown();

		System.out.println("Data Class: " + Order.class.getName());
		System.out.println("  Ingested: " + OBJECT_COUNT);
		System.out.println("       Map: " + MAP_NAME);
	}
}

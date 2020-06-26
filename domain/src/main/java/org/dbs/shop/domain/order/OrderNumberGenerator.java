package org.dbs.shop.domain.order;

import java.util.Random;

public class OrderNumberGenerator {

	public static int generate() {
		final Random random = new Random();
		return random.nextInt();
	}

}

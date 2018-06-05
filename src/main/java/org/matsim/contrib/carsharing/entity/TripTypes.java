package org.matsim.contrib.carsharing.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public enum TripTypes {
	PLANNED(0, 1), UNPLANNED(1, 2);

	private int order;

	private Byte code;

	TripTypes(int order, int code) {
		this.order = order;
		this.code = (byte) code;
	}

	public int getOrder() {
		return order;
	}

	public int getCode() {
		return code;
	}

	public static List<TripTypes> convertTripTypes(BigInteger value) {
		if (value == null) {
			return new ArrayList<>();
		}
		if (value.equals(BigInteger.ZERO)) {
			return new ArrayList<>();
		}

		String dbString =
				new StringBuilder(Integer.toBinaryString(value.intValue())).reverse().toString();
		List<TripTypes> result = new ArrayList<>();
		for (TripTypes m : TripTypes.values()) {
			if (m.getOrder() < dbString.length() && dbString.charAt(m.getOrder()) == '1') {
				result.add(m);
			}
		}
		return result;
	}

	public static BigInteger convertTripTypes(List<TripTypes> value) {
		if (value == null)
			return BigInteger.ZERO;
		BigInteger sum = BigInteger.ZERO;
		for (TripTypes m : value) {
			sum = sum.add(BigInteger.valueOf(m.getCode()));
		}
		return sum;
	}

}

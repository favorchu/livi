package com.livi.demo.common.utils;

import java.util.LinkedHashMap;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.livi.demo.exception.BusinessRuntimeExcepion;

/**
 * 
 * @author favorchu
 *
 * @param <T> Value type
 */
public class IntegerRangeMap<T> {

	/**
	 * After this frozen flag is enable, not more edit
	 */

	private LinkedHashMap<ImmutablePair<Integer, Integer>, T> list = new LinkedHashMap<ImmutablePair<Integer, Integer>, T>();

	public IntegerRangeMap<T> addRange(int from, Integer to, T val) {

		// Validation
		if (to != null && from > to)
			throw new BusinessRuntimeExcepion("From is larger then to.");
		if (val == null)
			throw new BusinessRuntimeExcepion("Value is null.");

		// Put to the value
		list.put(new ImmutablePair<Integer, Integer>(from, to), val);
		return this;
	}

	/**
	 * return the value according to the range
	 * 
	 * @param input
	 * @return
	 */
	public T get(int input) {

		for (ImmutablePair<Integer, Integer> key : list.keySet()) {
			if (key.left <= input && (key.right == null || input <= key.right))
				return list.get(key);
		}
		return null;
	}

}

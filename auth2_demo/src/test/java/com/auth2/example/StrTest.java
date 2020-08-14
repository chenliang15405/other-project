package com.auth2.example;

import org.junit.Test;

import java.util.*;

/**
 * @auther alan.chen
 * @time 2019/8/18 8:20 PM
 */
public class StrTest {

	@Test
	public void strTest() {
		String str = "heLLo12WorL11d12";

		Integer sum = statisticsNum(str);
		System.out.println(sum);

	}

	private Integer statisticsNum(String str) {
		Map<Character, Integer> map = new HashMap<Character,Integer>();
		map.put('0', 0);
		map.put('1', 1);
		map.put('2', 2);
		map.put('3', 3);
		map.put('4', 4);
		map.put('5', 5);
		map.put('6', 6);
		map.put('7', 7);
		map.put('8', 8);
		map.put('9', 9);
		List<String> list = new ArrayList<>();

		String oldNumStr = "";

		for (int i = 0; i < str.length(); i++) {
			Integer value = map.get(str.charAt(i));
			if(value == null) {
				if(!oldNumStr.equals("")) {
					list.add(oldNumStr);
					oldNumStr = "";
				}
				continue;
			}
			oldNumStr += value;
		}
		// 最后一位元素如果还是数字，则添加到list中
		if(!oldNumStr.equals("")) {
			list.add(oldNumStr);
		}
		Integer sum = same(list);

		return sum;
	}

	// 获取list中重复最多元素 并计算总和
	public Integer same(List<String> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i);
			Object old = map.get(key);
			if (old != null) {
				map.put(key, old + "," + (i + 1));
			} else {
				map.put(key, "" + (i + 1));
			}
		}
		int count = 0;
		String str = "";
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = String.valueOf(map.get(key));
			if (value.indexOf(",") != -1) {
				if(value.split(",").length > count) {
					count = value.split(",").length;
					str= key;
				}
			}
		}
		System.out.println(str + "   " + count);
		Integer sum = Integer.valueOf(str) * count;
		return sum;
	}


}

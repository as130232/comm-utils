package com.example.commutils.utils;

import java.util.ArrayList;
import java.util.List;

public class ConvertToLetter {
	
	private static int value = 26;
	private static List<String> letterArr = new ArrayList<>();
	
	static {
		// 產生A-Z字母
		for (int i = 0; i < value; i++) {
			int ascii = i + 65;
			String letter = Character.toString((char) ascii);
			letterArr.add(letter);
		}
	}
	
	/**
	 * 取得字母
	 * @author charles.chen
	 * @date 2018年9月28日 下午2:22:06
	 */
	public static String getLetter(int limitSize, int srno) {
		String result = "";
		if (srno > Math.pow(26, limitSize)) {
			throw new RuntimeException("已超出最大值");
		}
		for (int i = 0; i < limitSize; i++) {
			int remainder = srno % 26;
			result = letterArr.get(remainder) + result;
			srno = srno / 26;
		}
		return result;
	}

	//test
//	public static void main(String[] args) {
//		String test = ConvertToLetter.getLetter(3, 3);
//		System.out.println("test:" + test);
//	}

//	public static String input(Integer limitNumber, Integer srno) {
//		String result = "";
//		double max = Math.pow(value, limitNumber);
//		// 檢查流水號是否大於26 n次方
//		if ((double) srno > max) {
//			System.out.println("已超出最大值");
//		}
//
//		result = getLetter(result, srno);
//
//		return result;
//	}
//	public static String getLetter(String letter, Integer number) {
//
//		// 除法
//		int division = number / value;
//		// 餘數
//		int remainder = number % value;
//		if (remainder == 0) {
//			remainder = 26;
//		}
//		// 轉換1 > A, 2 > B
//		String lastLetter = letterArr.get(remainder - 1);
//		if (remainder > 0) {
//			letter = letter + ProjectServiceApplication.getLetter(letter, number);
//		}
//		letter += lastLetter;
//		return letter;
//	}
}

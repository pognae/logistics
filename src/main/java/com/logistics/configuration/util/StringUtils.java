package com.logistics.configuration.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class StringUtils {

	private StringUtils() {

	}

	public static String nvl(String str, String defaultStr) {
		return str == null ? defaultStr : str;
	}

	public static String[] nvl(String[] input) {
		if (input == null) {
			return new String[0];
		}
		return input;
	}

	public static String nvl2(String str, String defaultStr) {
		return str == null ? defaultStr
				: (str == (null)) ? defaultStr : (str == "null") ? defaultStr : (str == "(null)") ? defaultStr : str;
	}

	public static String enterToNull(String str) {
		return str == null ? "" : str.replaceAll("\n", "");
	}

	public static String decode(String msg, String type) throws UnsupportedEncodingException {
		return URLDecoder.decode(msg, type);
	}

	/**
	 * toString();
	 * 
	 * @param value
	 * @return
	 */
	public static String toString(int value) {
		try {
			return value + "";
		} catch (Exception e) {
			return "";
		}
	}

	public static int toInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return 0;
		}
	}

	public static long toLong(String value) {
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			return 0;
		}
	}

	public static String lpad(int value, int length, String prefix) {
		try {
			StringBuilder sb = new StringBuilder();
			String castValue = value + "";

			for (int i = castValue.length(); i < length; i++) {
				sb.append(prefix);
			}
			sb.append(castValue);
			return sb.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public static String rpad(int value, int length, String prefix) {
		try {
			StringBuilder sb = new StringBuilder();
			String castValue = value + "";
			sb.append(castValue);
			for (int i = castValue.length(); i < length; i++) {
				sb.append(prefix);
			}
			return sb.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public static String cutText(String text, int length, String suffix) {
		StringBuffer sb = new StringBuffer();

		if (!text.isEmpty()) {
			if (text.length() > length) {
				sb.append(text.substring(0, length)).append(suffix);
			} else {
				sb.append(text);
			}
		} else {
			sb.append(text);
		}
		return sb.toString();
	}

	// String이 비었거나 null인지 검사
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static String replaceText(String text, String originTxt, String replaceTxt) {
		return text.replaceAll(originTxt, replaceTxt);
	}

	public static String replaceBrTag(String text) {
		return text.replaceAll("\\n", "<br/>");
	}

	public static String removeAllTag(String txt) {
		return txt.replaceAll("(?:<!.*?(?:--.*?--\\s*)*.*?>)|(?:<(?:[^>'\"]*|\".*?\"|'.*?')+>)", "");
	}

	/**
	 * get String of java.util.Map keys and values to log
	 */
	public static String getDatasOfMap(Map<String, String> map) {
		StringBuilder buf = new StringBuilder();
		buf.append("getDatasOfMap() =>");
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			String name = iter.next();
			String value = map.get(name);
			buf.append("[").append(name).append("|").append(value).append("]");
		}
		return buf.toString();
	}

	public static boolean isNullCheck(String str) {
		boolean bool = false;

		if (str != null && !"".equals(str)) {
			bool = true;
		}

		return bool;
	}

	/**
	 * 검색 결과 날짜표기 MM/DD
	 * 
	 * @param value
	 * @return
	 */
	public static String historyDate(String value) {
		return !(nvl(value, "").length() == 0) && value.length() == 8
				? value = value.substring(4, 6) + "/" + value.substring(6, 8)
				: "";
	}

	/**
	 * 검색 결과 날짜표기 YYYY.MM.DD
	 * 
	 * @param value
	 * @return
	 */
	public static String historyDate2(String value) {
		return !(nvl(value, "").length() == 0) && value.length() == 8
				? value = value.substring(0, 4) + "." + value.substring(4, 6) + "." + value.substring(6, 8)
				: "";
	}

	/**
	 * 날짜표기 YYYY-MM-DD ==> YYYYMMDD 변경
	 * 
	 * @param value
	 * @return
	 */
	public static String historyDate3(String value) {
		return !(nvl(value, "").length() == 0) && value.length() == 10
				? value = value.substring(0, 4) + value.substring(5, 7) + value.substring(8, 10)
				: "";
	}

	/**
	 * 콤마 추가
	 * 
	 * @param data
	 * @return
	 */
	public static String addComma(long data) {

		return new DecimalFormat("#,###").format(data);
	}

}
package com.omg.evn.util;

public class GetCNNumber {
	public static void main(String[] args) {
		getCNNum1(123);
		getCNNum1(0);
		getCNNum1(1);
		
	}
	
	public static String getCNNum1(int integer){
		int len = Integer.toString(integer).length();
		String[] num = new String[len];
		String[] c = { "", "十", "百", "千", "万", "十", "百", "千", "亿" };
		String s = "";
		for (int a = 0; a < len; a++) {
			switch (Integer.parseInt(Integer.toString(integer).substring(a,
					a + 1))) {
			case 0:
				num[a] = "零";
				break;
			case 1:
				num[a] = "壹";
				break;
			case 2:
				num[a] = "贰";
				break;
			case 3:
				num[a] = "弎";
				break;
			case 4:
				num[a] = "肆";
				break;
			case 5:
				num[a] = "伍";
				break;
			case 6:
				num[a] = "陆";
				break;
			case 7:
				num[a] = "柒";
				break;
			case 8:
				num[a] = "捌";
				break;
			case 9:
				num[a] = "镹";
				break;
			}
		}
		for (int n = 0, b = len - 1; n <= len - 1 && b >= 0; n++, b--) {
			if (num[0].equals("零") && len == 1) {
				s += num[0];
			} else if ((num[n].equals("零") && n != 0)) {
				s += num[n];
			} else if (!num[n].equals("零")) {
				s += (num[n] + c[b]);
			}
		}
		//System.out.println(s);
		return s;
	}
	
	public static String getCNNum(int integer){
		//只能为正数
		if (integer < 0) {
			return "";
		}
		
		int len = Integer.toString(integer).length();
		String[] num = new String[len];
		String[] c = { "", "十", "百", "千", "万", "十", "百", "千", "亿" };
		String s = "";
		for (int a = 0; a < len; a++) {
			switch (Integer.parseInt(Integer.toString(integer).substring(a,
					a + 1))) {
			case 0:
				num[a] = "零";
				break;
			case 1:
				num[a] = "一";
				break;
			case 2:
				num[a] = "二";
				break;
			case 3:
				num[a] = "三";
				break;
			case 4:
				num[a] = "四";
				break;
			case 5:
				num[a] = "五";
				break;
			case 6:
				num[a] = "六";
				break;
			case 7:
				num[a] = "七";
				break;
			case 8:
				num[a] = "八";
				break;
			case 9:
				num[a] = "九";
				break;
			}
		}
		for (int n = 0, b = len - 1; n <= len - 1 && b >= 0; n++, b--) {
			if (num[0].equals("零") && len == 1) {
				s += num[0];
			} else if ((num[n].equals("零") && n != 0)) {
				s += num[n];
			} else if (!num[n].equals("零")) {
				s += (num[n] + c[b]);
			}
		}
		//System.out.println(s);
		return s;
	}
}
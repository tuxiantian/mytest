package com.tuxt.mytest.algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class CombineMonthTableQuery {
	/**
	 * 返回各个月份应该查询多少数据
	 * @param monthList 月份的集合
	 * @param start 起始记录
	 * @param length 分页大小
	 * @param numArr 各个月份有的记录数
	 * @return
	 */
	public static LinkedHashMap<String, Integer[]> getQueryTblList(List<String> monthList, int start, int length,
			int[] numArr) {
		LinkedHashMap<String, Integer[]> monthNumList = new LinkedHashMap<String, Integer[]>();
		int pageBegin = start;
		int pageEnd = pageBegin + length;
		int tmpTotal = 0;
		int[] tmpSumArr = new int[numArr.length];// 数组累加和
		for (int i = 0, len = monthList.size(); i < len; i++) {
			tmpTotal += numArr[i];
			tmpSumArr[i] = tmpTotal;
			// 小于start 大于结束
			if (numArr[i] == 0 || tmpTotal <= pageBegin || (i > 0 && tmpSumArr[i - 1] >= pageEnd)) {
				continue;
			}
			// 数据<=查询数量
			if (tmpTotal <= pageEnd) {
				Integer[] limitSizeArr = new Integer[2];
				// 第一条或者一直不够
				if (i == 0) {
					limitSizeArr[0] = pageBegin;
					limitSizeArr[1] = numArr[i];
				} else if (tmpSumArr[i - 1] >= pageBegin && tmpTotal - numArr[i] >= 0) {
					// 本次开始满足条件
					limitSizeArr[0] = 0;
					limitSizeArr[1] = numArr[i];
				} else if (tmpSumArr[i - 1] < pageBegin) {
					// 扣除上次数据
					limitSizeArr[0] = numArr[i] + pageBegin - tmpTotal;
					limitSizeArr[1] = numArr[i];
				}
				monthNumList.put(monthList.get(i), limitSizeArr);
			} else if (tmpTotal > pageEnd) {
				if (i > 0) {
					Integer[] limitSizeArr = new Integer[2];
					if (tmpSumArr[i - 1] > pageBegin) {
						// 前一条不够,本次够
						limitSizeArr[0] = 0;
						// 缺少的
						limitSizeArr[1] = pageBegin + length - tmpSumArr[i - 1];
					} else {
						limitSizeArr[0] = pageBegin - tmpSumArr[i - 1];
						limitSizeArr[1] = Math.min(numArr[i], limitSizeArr[0] + length);
					}
					monthNumList.put(monthList.get(i), limitSizeArr);
				} else {
					// 本次足够
					Integer[] limitSizeArr = new Integer[2];
					limitSizeArr[0] = pageBegin;
					// 缺少的
					limitSizeArr[1] = Math.min(numArr[i], limitSizeArr[0] + length);
					monthNumList.put(monthList.get(i), limitSizeArr);
				}
			}
		}
		return monthNumList;
	}
	public static void main(String[] args) {
		List<String> monthList=new ArrayList<String>();
		monthList.add("10");
		monthList.add("09");
		monthList.add("08");
		int start=5;
		int length=21;
		int[] numArr=new int[]{3,22,23};
		LinkedHashMap<String, Integer[]> a=getQueryTblList(monthList, start, length, numArr);
		for (Iterator iterator = monthList.iterator(); iterator.hasNext();) {
			String str = (String) iterator.next();
			Integer[] num=a.get(str);
			if (num!=null) {
				System.out.println("month:"+str+",start:" + num[0] + ",length" + num[1]);
			}
			
		}
	}

}

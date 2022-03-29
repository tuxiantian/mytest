package com.tuxt.mytest.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateStandard {

	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder builder=new StringBuilder(),builder2=new StringBuilder(),builder3=new StringBuilder();
		builder.append("insert into db_ap_rgsh_standard_order(LOGID,BUSI_TYPE,CREATE_DATE)").append("values");
		builder2.append("insert into db_ap_rgsh_standard_order_idcard_201607(LOGID,CUST_CERT_NO)").append("values");
		builder3.append("insert into db_ap_rgsh_standard_order_addition_201607(LOGID,PROPERTY_NAME,PROPERTY_VALUE)").append("values");
		
		for (int i = 0; i < 10; i++) {
			String logid=GenerateIDUtil.generateSequenceNo();
			builder.append("(").append("'"+logid+"'").append(",").append("'21'").append(",")
			.append("'"+sdf.format(new Date())+"'").append(")");
			
			builder2.append("(").append("'"+logid+"'").append(",").append("'412829199109093214'").append(")");
			
			builder3.append("(").append("'"+logid+"'").append(",").append("'sim'").append(",").append("'123'").append(")");
			if (i==10-1) {
				builder.append(";");
				builder2.append(";");
				builder3.append(";");
			}else {
				builder.append(",");
				builder2.append(",");
				builder3.append(",");
			}
		}
		System.out.println(builder.toString());
		System.out.println(builder2.toString());
		System.out.println(builder3.toString());
		
		
	}

}

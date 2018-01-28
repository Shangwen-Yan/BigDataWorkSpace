package cn.tarena.hive;

import org.apache.hadoop.hive.ql.exec.UDF;

public class ToUpper extends UDF{
	
	
	public String evaluate(String str){
		return str.toUpperCase();
		
	}

}

package cn.tarena.season;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.RawComparator;

public class SeasonGroup implements RawComparator<IntWritable>{

	@Override
	public int compare(IntWritable o1, IntWritable o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * return 0 表示输出key当做相同聚合
	 * 
	 */
	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		
		int k1=b1[3]&0xff;
		int k2=b2[3]&0xff;
		System.out.println("compare,"+"b1="+k1+",b="+k2);
		if((k1==11 || k1==12 ||k1==13 ||k1 == 2 || k1 == 3) & (k2==1 || k2 == 2 || k2 == 3)){
			return 0;
		}else if((k1==4 || k1 == 5 || k1 == 6) & (k2==4 || k2 == 5 || k2 == 6)){
			return 0;
		}else{
			return -1;
		}
		
	}

}

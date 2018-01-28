package cn.tarena.ssort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class Ssort implements WritableComparable<Ssort>{
	private int month;
	private String name;
	private int profit;
	
	@Override
	public void readFields(DataInput in) throws IOException {
		this.month=in.readInt();
		this.name=in.readUTF();
		this.profit=in.readInt();
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(month);
		out.writeUTF(name);
		out.writeInt(profit);
		
	}

	@Override
	public int compareTo(Ssort o) {
		int result=this.month -o.month;
		if(result!=0){
			return result;
		}else{
			return o.profit-this.profit;
		}

	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "Ssort [month=" + month + ", name=" + name + ", profit=" + profit + "]";
	}
	

}

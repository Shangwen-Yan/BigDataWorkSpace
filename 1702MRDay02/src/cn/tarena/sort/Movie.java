package cn.tarena.sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class Movie implements WritableComparable<Movie>{
	private String name;
	private int hot;
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeInt(hot);
		
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.name=in.readUTF();
		this.hot=in.readInt();
		
	}

	@Override
	public int compareTo(Movie o) {
		
		return o.hot-this.hot;
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + ", hot=" + hot + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}
	

}

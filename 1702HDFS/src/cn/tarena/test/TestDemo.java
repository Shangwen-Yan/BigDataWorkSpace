package cn.tarena.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.yarn.api.records.URL;
import org.junit.Test;

public class TestDemo {
	
	@Test
	public void testConn() throws IOException, URISyntaxException{
		Configuration conf = new Configuration();
		//相当于配置文件里的<property>里面的<name><value>
		//配置文件优先级低于代码配置优先级
		//conf.set("dfs.replication", "1");
		FileSystem system = FileSystem.get(new URI("hdfs://192.168.174.60:9000"), conf);	
	}
	@Test
	public void testGet() throws IOException, URISyntaxException{
		Configuration conf = new Configuration();
		FileSystem system = FileSystem.get(new URI("hdfs://192.168.174.60:9000"), conf);
		InputStream in = system.open(new Path("/park02/3.txt"));
		OutputStream out = new FileOutputStream(new File("1.txt"));
		IOUtils.copyBytes(in, out, conf);
		system.close();
	}
	@Test
	public void testPut() throws IOException, URISyntaxException{
		Configuration conf=new Configuration();
		FileSystem system =FileSystem.get(new URI("hdfs://192.168.174.60:9000"), conf);
		InputStream in =new FileInputStream("1.txt");
		OutputStream out = system.create(new Path("/park01/1.txt"));
		IOUtils.copyBytes(in, out, conf);
		system.close();

	}
	
	@Test
	public void testDelete() throws IOException, URISyntaxException{
		Configuration conf=new Configuration();
		FileSystem system =FileSystem.get(new URI("hdfs://192.168.174.60:9000"), conf);
		system.delete(new Path("/park01/1.txt"));
		//第二个参数为是否迭代（当删除非空目录时；true为递归删除（相当于rmr），false为非递归
		system.delete(new Path("/park01/1.txt"), true);
		system.close();

	}
	
	@Test
	public void testRename() throws IOException, URISyntaxException{
		Configuration conf=new Configuration();
		FileSystem system =FileSystem.get(new URI("hdfs://192.168.174.60:9000"), conf);
		system.rename(new Path("/park02"), new Path("/park04"));
		system.close();
	}
	
	
	@Test
	public void testList() throws IOException, URISyntaxException{
		Configuration conf=new Configuration();
		FileSystem system =FileSystem.get(new URI("hdfs://192.168.174.60:9000"), conf);
		//true表示递归查看，只显示文件，不显示目录
		RemoteIterator<LocatedFileStatus> ri=system.listFiles(new Path("/"), true);
		while(ri.hasNext()){
			System.out.println(ri.next());
		}
		
		system.close();
	}
	@Test
	public void testGetBlocks() throws IOException, URISyntaxException{
		Configuration conf=new Configuration();
		FileSystem system =FileSystem.get(new URI("hdfs://192.168.174.60:9000"), conf);
		BlockLocation[] bls=system.getFileBlockLocations(new Path("/park04/3.txt"), 0, Integer.MAX_VALUE);
		for(BlockLocation bl:bls){
			System.out.println(bl);
		}
		
		system.close();
	}
	
	

}

package cn.tarena.hbase;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.junit.Test;

public class CRUD {
	@Test
	public void create() throws Exception, ZooKeeperConnectionException, IOException{
    //Configuration conf=new Configuration();
    Configuration conf=HBaseConfiguration.create();
    conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
    HBaseAdmin admin=new HBaseAdmin(conf);
    HTableDescriptor tabDesc= new HTableDescriptor(TableName.valueOf("tabx1"));
    HColumnDescriptor cf1 = new HColumnDescriptor("cf1".getBytes());

    tabDesc.addFamily(cf1 );
    HColumnDescriptor cf2 = new HColumnDescriptor("cf2".getBytes());
    tabDesc.addFamily(cf2 );
    admin.createTable(tabDesc);
    admin.close();
		
	}
	
	@Test
	public void put() throws Exception, ZooKeeperConnectionException, IOException{
		Configuration conf=HBaseConfiguration.create();
	    conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
	    HTable tab=new HTable(conf, "tabx1".getBytes());
		Put put=new Put("rkx1".getBytes());
		put.add("cf1".getBytes(),"c11".getBytes(),"c11values111".getBytes());
		put.add("cf1".getBytes(),"c12".getBytes(),"c12values111".getBytes());
		tab.put(put);
		tab.close();

	}
	
	@Test
	public void get() throws Exception, ZooKeeperConnectionException, IOException{
		Configuration conf=HBaseConfiguration.create();
	    conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
	    HTable tab=new HTable(conf, "tabx1".getBytes());
		Get get=new Get("rk1".getBytes());
		get.addColumn("cf1".getBytes(), "c1".getBytes());
		Result result=tab.get(get);
		
		System.out.println(new String(result.getValue("cf1".getBytes(), "c1".getBytes())));
		tab.close();

	}
	@Test
	public void scan() throws Exception, ZooKeeperConnectionException, IOException{
		Configuration conf=HBaseConfiguration.create();
	    conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
	    HTable tab=new HTable(conf, "tabx1".getBytes());
		Scan scan=new Scan("rk1".getBytes());
		ResultScanner results = tab.getScanner(scan);
		Iterator<Result> iter =results.iterator();
		while(iter.hasNext()){
			Result result =iter.next();
			System.out.println(new String(result.getValue("cf1".getBytes(),"c12".getBytes())));
		}
		tab.close();

	}
	@Test
	public void delete() throws Exception, ZooKeeperConnectionException, IOException{
		Configuration conf=HBaseConfiguration.create();
	    conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
	    HTable tab=new HTable(conf, "tabx1".getBytes());
		Delete del=new Delete("rk3".getBytes());

		tab.delete(del);
		tab.close();

	}
	@Test
	public void drop() throws Exception, ZooKeeperConnectionException, IOException{
		Configuration conf=HBaseConfiguration.create();
	    conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
	    HBaseAdmin admin=new HBaseAdmin(conf);
	    admin.disableTable("tabx1".getBytes());
	    admin.deleteTable("tabx1".getBytes());
	    admin.close();

	}

}

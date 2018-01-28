package cn.tarena.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.filter.InclusiveStopFilter;
import org.apache.hadoop.hbase.filter.KeyOnlyFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.RandomRowFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.junit.Test;



public class ScannerTest {
	@Test
	public void testScan() throws Exception{
		Configuration conf =HBaseConfiguration.create();
	    conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
	    HTable tab = new HTable(conf, "tabx1".getBytes());
	    
	    Scan scan=new Scan();
	    //含头不含尾
	    //scan.setStartRow("rk2".getBytes());
	    //Filter filter = new RowFilter(CompareOp.EQUAL, new BinaryComparator("rk3".getBytes()));
	    //Filter filter = new RowFilter(CompareOp.GREATER_OR_EQUAL, new RegexStringComparator("^[^x]*x[^x]*$"));
	    //Filter filter = new PrefixFilter("rk".getBytes());
	    //Filter filter = new KeyOnlyFilter();
	    //	    每条内容能被筛选出来的几率
	    //Filter filter = new RandomRowFilter(0.5f);
	    //含尾0
	    Filter filter1 = new InclusiveStopFilter("rk3".getBytes());
	    Filter filter2 = new FirstKeyOnlyFilter();
//	    Filter filter = new ColumnPrefixFilter("c12".getBytes());
//	    Filter filter = new ValueFilter(CompareOp.EQUAL, new RegexStringComparator("^[^2]*$"));
	    //???Filter filter = new SingleColumnValueFilter("cf1".getBytes(), "c11".getBytes(), CompareOp.EQUAL, new RegexStringComparator("^[^2]*$"));
	    

	    FilterList f = new FilterList(Operator.MUST_PASS_ALL,filter1,filter2);
	    scan.setFilter(f);
	    ResultScanner rscan=tab.getScanner(scan);
	    Iterator<Result> it=rscan.iterator();
	    
	    
	    while(it.hasNext()){
	    	Result rs = it.next();
	    	String rk = new String(rs.getRow());
	    	
	    	NavigableMap<byte[],NavigableMap<byte[], NavigableMap<Long,byte[] >>>  map=rs.getMap();
	    	for(Entry<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> entry:map.entrySet()){
	    		String cf = new String(entry.getKey());
	    		NavigableMap<byte[], NavigableMap<Long, byte[]>> value=entry.getValue();
	    		for(Entry<byte[], NavigableMap<Long, byte[]>>  vEntry:value.entrySet()){
	    			String c = new String(vEntry.getKey());
	    			String v = new String(vEntry.getValue().firstEntry().getValue());
	    			StringBuffer sb=new StringBuffer();
	    			sb.append("----rk:"+rk+"----");
	    			sb.append("----cf:"+cf+"----");
	    			sb.append("----c:"+c+"----");
	    			sb.append("----v:"+v+"----");
	    			System.out.println(sb.toString());
	    		}
	    	}
	    	
			
	    }
	    
	    
	    
	}

}

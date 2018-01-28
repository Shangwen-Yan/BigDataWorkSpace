package cn.zebra.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.AsyncCallback.VoidCallback;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;


public class ZookeeperState extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setDateHeader("Expires", -1);
			
			//1.连接zookeeper
			final CountDownLatch cdl1 = new CountDownLatch(1);
			ZooKeeper zk = new ZooKeeper("192.168.242.111:2181,192.168.242.112:2181,192.168.242.113:2181", 5000
				, new Watcher(){
					public void process(WatchedEvent event) {
						if(event.getState() == KeeperState.SyncConnected){
							cdl1.countDown();
						}
					}
				});
			cdl1.await();
			
			//3.读取engin1下所有孩子信息，获取一级引擎信息
			List<String> e1List = new ArrayList<String>();
			List<String> e1s = zk.getChildren("/zebra/engin1", null);
			for(int i=0;i<e1s.size();i++){
				String e1 = e1s.get(i);
				final CountDownLatch cdl2 = new CountDownLatch(1);
				zk.sync("/zebra/engin1/"+e1, new VoidCallback() {
					public void processResult(int rc, String path, Object ctx) {
						cdl2.countDown();
					}
				}, null);
				cdl2.await();
				String e1_data = new String(zk.getData("/zebra/engin1/"+e1, false, null));
				Map<String,String> e1_data_map = parseZkData(e1_data);
				String ip = e1_data_map.get("ip");
				e1List.add("{'ip':'"+ip+"'}");
			}
			//3.读取engin2下所有孩子信息，获取二级引擎信息
			List<String> e2List = new ArrayList<String>();
			List<String> e2s = zk.getChildren("/zebra/engin2", null);
			for(int i=0;i<e2s.size();i++){
				String e2 = e2s.get(i);
				final CountDownLatch cdl3 = new CountDownLatch(1);
				zk.sync("/zebra/engin2/"+e2, new VoidCallback() {
					public void processResult(int rc, String path, Object ctx) {
						cdl3.countDown();
					}
				}, null);
				cdl3.await();
				String e2_data = new String(zk.getData("/zebra/engin2/"+e2, false, null));
				Map<String,String> e2_data_map = parseZkData(e2_data);
				String ip = e2_data_map.get("ip");
				String port = e2_data_map.get("port");
				e2List.add("{'ip':'"+ip+"','port':'"+port+"'}");
			}
			
			
			//4.处理成输出结果
			String sendData = "{";
			
			sendData = sendData + "e1s:[";
			for(String e1 : e1List){
				sendData = sendData + e1 + ",";
			}
			if(sendData.charAt(sendData.length()-1) == ','){
				sendData = sendData.substring(0,sendData.length() - 1);
			}
			sendData = sendData + "],";
			
			sendData = sendData + "e2s:[";
			for(String e2 : e2List){
				sendData = sendData + e2 + ",";
			}
			if(sendData.charAt(sendData.length()-1) == ','){
				sendData = sendData.substring(0,sendData.length() - 1);
			}
			sendData = sendData + "]";
			
			sendData = sendData + "}";
			
			System.out.println(sendData);
			
			response.getWriter().write(sendData);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static Map<String,String> parseZkData(String str){
		Map<String,String> map = new HashMap<String,String>();
		String [] attrs = str.split(",");
		for(String attr : attrs){
			String [] kv = attr.split("=");
			map.put(kv[0], kv[1]);
		}
		return map;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

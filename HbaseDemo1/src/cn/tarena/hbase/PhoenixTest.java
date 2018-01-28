package cn.tarena.hbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PhoenixTest {
	public static void main(String[] args) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:phoenix:hadoop01,hadoop02,hadoop03");
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery("select * from \"tabz1\"");
		while(rs.next()){
			String name = rs.getString("name");
			System.out.println(name);
		}
		rs.close();
		stat.close();
		conn.close();
	}

}

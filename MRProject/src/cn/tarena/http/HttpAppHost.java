package cn.tarena.http;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class HttpAppHost implements Writable{
	private String reportTime;
	private String cellid;
	private int appType;
	private int appSubtype;
	private String userIP;
	private int userPort;
	private String appServerIP;
	private int appServerPort;
	private String host;
	private int attempts;
	private int accepts;
	private long trafficUL;
	private long trafficDL;
	private long retranUL;
	private long retranDL;
	private long transDelay;

	
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(reportTime);
		out.writeUTF(cellid);
		out.writeInt(appType);
		out.writeInt(appSubtype);
		out.writeUTF(userIP);
		out.writeInt(userPort);
		out.writeUTF(appServerIP);
		out.writeInt(appServerPort);
		out.writeUTF(host);
		out.writeInt(attempts);
		out.writeInt(accepts);
		out.writeLong(trafficUL);
		out.writeLong(trafficDL);
		out.writeLong(retranUL);
		out.writeLong(retranDL);
		out.writeLong(transDelay);
		
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.reportTime = in.readUTF();
		this.cellid = in.readUTF();
		this.appType = in.readInt();
		this.appSubtype = in.readInt();
		this.userIP = in.readUTF();
		this.userPort = in.readInt();
		this.appServerIP = in.readUTF();
		this.appServerPort = in.readInt();
		this.host = in.readUTF();
		this.attempts = in.readInt();
		this.accepts = in.readInt();
		this.trafficUL = in.readLong();
		this.trafficDL = in.readLong();
		this.retranUL = in.readLong();
		this.retranDL = in.readLong();
		this.transDelay = in.readLong();
		
		
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getCellid() {
		return cellid;
	}

	public void setCellid(String cellid) {
		this.cellid = cellid;
	}

	public int getAppType() {
		return appType;
	}

	public void setAppType(int appType) {
		this.appType = appType;
	}

	public int getAppSubtype() {
		return appSubtype;
	}

	public void setAppSubtype(int appSubtype) {
		this.appSubtype = appSubtype;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	public int getUserPort() {
		return userPort;
	}

	public void setUserPort(int userPort) {
		this.userPort = userPort;
	}

	public String getAppServerIP() {
		return appServerIP;
	}

	public void setAppServerIP(String appServerIP) {
		this.appServerIP = appServerIP;
	}

	public int getAppServerPort() {
		return appServerPort;
	}

	public void setAppServerPort(int appServerPort) {
		this.appServerPort = appServerPort;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public int getAccepts() {
		return accepts;
	}

	public void setAccepts(int accepts) {
		this.accepts = accepts;
	}

	public long getTrafficUL() {
		return trafficUL;
	}

	public void setTrafficUL(long trafficUL) {
		this.trafficUL = trafficUL;
	}

	public long getTrafficDL() {
		return trafficDL;
	}

	public void setTrafficDL(long trafficDL) {
		this.trafficDL = trafficDL;
	}

	public long getRetranUL() {
		return retranUL;
	}

	public void setRetranUL(long retranUL) {
		this.retranUL = retranUL;
	}

	public long getRetranDL() {
		return retranDL;
	}

	public void setRetranDL(long retranDL) {
		this.retranDL = retranDL;
	}

	public long getTransDelay() {
		return transDelay;
	}

	public void setTransDelay(long transDelay) {
		this.transDelay = transDelay;
	}

	@Override
	public String toString() {
		return "HttpAppHost [reportTime=" + reportTime + ", cellid=" + cellid + ", appType=" + appType + ", appSubtype="
				+ appSubtype + ", userIP=" + userIP + ", userPort=" + userPort + ", appServerIP=" + appServerIP
				+ ", appServerPort=" + appServerPort + ", host=" + host + ", attempts=" + attempts + ", accepts="
				+ accepts + ", trafficUL=" + trafficUL + ", trafficDL=" + trafficDL + ", retranUL=" + retranUL
				+ ", retranDL=" + retranDL + ", transDelay=" + transDelay + "]";
	}

}

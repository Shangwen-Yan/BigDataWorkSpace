package cn.tarena.http;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;



public class HttpMapper extends Mapper<LongWritable, Text, Text, HttpAppHost>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, HttpAppHost>.Context context)
			throws IOException, InterruptedException {
		String[] data=value.toString().split("\\|");
		HttpAppHost hah=new HttpAppHost();
		FileSplit split=(FileSplit) context.getInputSplit();

		//��ʼ��������
		hah.setReportTime(split.getPath().getName().split("_")[1]);
		//����С����id
		hah.setCellid(data[16]);
		//Ӧ����
		hah.setAppType(Integer.parseInt(data[22]));
		//Ӧ������
		hah.setAppSubtype(Integer.parseInt(data[23]));
		//�û�ip
		hah.setUserIP(data[26]);
		//�û�port
		hah.setUserPort(Integer.parseInt(data[28]));
		//���ʵķ���ip
		hah.setAppServerIP(data[30]);
		//���ʵķ���port
		hah.setAppServerPort(Integer.parseInt(data[32]));
		//����
		hah.setHost(data[58]);

		int appTypeCode=Integer.parseInt(data[18]);
		String transStatus=data[54];
		//ҵ���߼�����
		if(hah.getCellid()==null||hah.getCellid().equals("")){
		hah.setCellid("000000000");
		}
		if(appTypeCode==103){
		hah.setAttempts(1);
		}
		if(appTypeCode==103 &&"10,11,12,13,14,15,32,33,34,35,36,37,38,48,49,50,51,52,53,54,55,199,200,201,202,203,204,205 ,206,302,304,306".contains(transStatus)){
		hah.setAccepts(1);
		}else{
		hah.setAccepts(0);
		}
		if(appTypeCode == 103){
		hah.setTrafficUL(Long.parseLong(data[33]));
		}
		if(appTypeCode == 103){
		hah.setTrafficDL(Long.parseLong(data[34]));
		}
		if(appTypeCode == 103){
		hah.setRetranUL(Long.parseLong(data[39]));
		}

		if(appTypeCode == 103){
		hah.setRetranDL(Long.parseLong(data[40]));
		}
		if(appTypeCode==103){
		hah.setTransDelay(Long.parseLong(data[20]) -Long.parseLong(data[19]));
		}
		
		
		String newKey=hah.getReportTime() + "|" + hah.getAppType() + "|" + hah.getAppSubtype() + "|" + hah.getUserIP() + "|" + hah.getUserPort() + "|" + hah.getAppServerIP() + "|" + hah.getAppServerPort() +"|" + hah.getHost() + "|" + hah.getCellid();
		context.write(new Text(newKey), hah);
		
		
	}
}
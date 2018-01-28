package cn.zebra.data;

import java.util.HashMap;
import java.util.Map;

public class APP02 {
	private static Map<Integer,String> app02Map = new HashMap<Integer,String>();

	private APP02() {
	}

	static{
//		app02Map.put(1, "����");
//		app02Map.put(2, "����");
//		app02Map.put(3, "Gtalk");
//		app02Map.put(4, "MSN");
//		app02Map.put(5, "QQ");
//		app02Map.put(6, "TM");
//		app02Map.put(7, "��������");
//		app02Map.put(8, "����");
//		app02Map.put(9, "΢��");
//		app02Map.put(10, "��������");
//		app02Map.put(11, "AOL AIM");
//		app02Map.put(12, "Gadu_Gadu");
//		app02Map.put(13, "go��");
//		app02Map.put(14, "ICQ");
//		app02Map.put(15, "IMVU");
//		app02Map.put(16, "Lava-Lava");
//		app02Map.put(17, "NetChat");
//		app02Map.put(18, "Paltalk");
//		app02Map.put(19, "PowWow");
//		app02Map.put(20, "TeamSpeak");
//		app02Map.put(21, "Trillian");
//		app02Map.put(22, "VZOchat");
//		app02Map.put(23, "Xfire");
//		app02Map.put(24, "�ٶ�Hi");
//		app02Map.put(25, "����");
//		app02Map.put(26, "İİ");
//		app02Map.put(27, "����Live");
//		app02Map.put(28, "����");
//		app02Map.put(29, "��������");
//		app02Map.put(30, "����UC");
//		app02Map.put(31, "����UT");
//		app02Map.put(32, "�Ż�ͨ");
//	}
		app02Map.put(1, "�ƶ��ֻ�����");
		app02Map.put(2, "�ƶ��ֻ���Ƶ�Ż�");
		app02Map.put(3, "�ſ���");
		app02Map.put(4, "������");
		app02Map.put(5, "��6��");
		app02Map.put(6, "��Ѷ��Ƶ");
		app02Map.put(7, "�Ѻ���Ƶ");
		app02Map.put(8, "������Ƶ");
		app02Map.put(9, "������Ƶ");
		app02Map.put(10, "A67�ֻ���Ӱ��");
		app02Map.put(11, "80s�ֻ���Ӱ��");
		app02Map.put(12, "7060�ֻ���Ӱ");
		app02Map.put(13, "������");
		app02Map.put(14, "������");
		app02Map.put(15, "56��Ƶ");
		app02Map.put(16, "â��TV");
		app02Map.put(17, "����Ӱ��");
		app02Map.put(18, "CNTV");
		app02Map.put(19, "�ֻ�����Dopool");
		app02Map.put(20, "������");
		app02Map.put(21, "������Ƶ");
		app02Map.put(22, "�����������");
		app02Map.put(23, "�����Ƶ");
		app02Map.put(24, "3G������");
		app02Map.put(25, "��Ӱ��");
		app02Map.put(26, "YouTube��Ƶ");
		app02Map.put(27, "��Ӱ��Ӱ");
		app02Map.put(28, "163TV");
		app02Map.put(29, "517dv");
		app02Map.put(30, "NETiTV");
		app02Map.put(31, "Vsee");
		app02Map.put(32, "ƤƤӰ��");
		app02Map.put(33, "iKu");
		app02Map.put(34, "��ӥ����jinying��");
		app02Map.put(35, "Tomlive");
		app02Map.put(36, "SopCast");
		app02Map.put(37, "CCIPTV");
		app02Map.put(38, "VJBase");
		app02Map.put(39, "����");
		app02Map.put(40, "JeBoo");
		app02Map.put(41, "UCӰ��");
		app02Map.put(42, "QQLive");
		app02Map.put(43, "��ͨ���ӻ�");
		app02Map.put(44, "���䷿");
		app02Map.put(45, "iVӰ��");
		app02Map.put(46, "�������");
		app02Map.put(47, "abacast");
		app02Map.put(48, "Neo��Ƶ");
		app02Map.put(49, "�ٶȵ㲥");
		app02Map.put(50, "����������");
		app02Map.put(51, "�е�");
		app02Map.put(52, "��ħ�������");
		app02Map.put(53, "�ֿ�����¼���");
		app02Map.put(54, "�ڿ�");
		app02Map.put(55, "��˷�");
		app02Map.put(56, "������");
		app02Map.put(57, "�า�����");
		app02Map.put(58, "�Ŷ�ֱ��");
		app02Map.put(59, "�ֻ�A67��Ӱ��");
		app02Map.put(60, "�ٶ���Ƶ");
	}
	
	public static String getName(int id){
		return app02Map.get(id);
	}
	
	public static Integer getId(String name){
		for(Map.Entry<Integer, String> entry : app02Map.entrySet()){
			if(entry.getValue().equals(name)) return entry.getKey();
		}
		return null;
	}
}

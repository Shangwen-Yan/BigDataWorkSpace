package cn.zebra.data;

import java.util.HashMap;
import java.util.Map;

public class APP02 {
	private static Map<Integer,String> app02Map = new HashMap<Integer,String>();

	private APP02() {
	}

	static{
//		app02Map.put(1, "飞聊");
//		app02Map.put(2, "飞信");
//		app02Map.put(3, "Gtalk");
//		app02Map.put(4, "MSN");
//		app02Map.put(5, "QQ");
//		app02Map.put(6, "TM");
//		app02Map.put(7, "阿里旺旺");
//		app02Map.put(8, "米聊");
//		app02Map.put(9, "微信");
//		app02Map.put(10, "人人桌面");
//		app02Map.put(11, "AOL AIM");
//		app02Map.put(12, "Gadu_Gadu");
//		app02Map.put(13, "go聊");
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
//		app02Map.put(24, "百度Hi");
//		app02Map.put(25, "都秀");
//		app02Map.put(26, "陌陌");
//		app02Map.put(27, "天翼Live");
//		app02Map.put(28, "翼聊");
//		app02Map.put(29, "网易泡泡");
//		app02Map.put(30, "新浪UC");
//		app02Map.put(31, "新浪UT");
//		app02Map.put(32, "雅虎通");
//	}
		app02Map.put(1, "移动手机电视");
		app02Map.put(2, "移动手机视频门户");
		app02Map.put(3, "优酷网");
		app02Map.put(4, "土豆网");
		app02Map.put(5, "酷6网");
		app02Map.put(6, "腾讯视频");
		app02Map.put(7, "搜狐视频");
		app02Map.put(8, "新浪视频");
		app02Map.put(9, "网易视频");
		app02Map.put(10, "A67手机电影网");
		app02Map.put(11, "80s手机电影网");
		app02Map.put(12, "7060手机电影");
		app02Map.put(13, "激动网");
		app02Map.put(14, "乐视网");
		app02Map.put(15, "56视频");
		app02Map.put(16, "芒果TV");
		app02Map.put(17, "奇艺影音");
		app02Map.put(18, "CNTV");
		app02Map.put(19, "手机电视Dopool");
		app02Map.put(20, "优米网");
		app02Map.put(21, "东方宽频");
		app02Map.put(22, "乐视网络电视");
		app02Map.put(23, "凤凰视频");
		app02Map.put(24, "3G生活网");
		app02Map.put(25, "电影网");
		app02Map.put(26, "YouTube视频");
		app02Map.put(27, "观影电影");
		app02Map.put(28, "163TV");
		app02Map.put(29, "517dv");
		app02Map.put(30, "NETiTV");
		app02Map.put(31, "Vsee");
		app02Map.put(32, "皮皮影视");
		app02Map.put(33, "iKu");
		app02Map.put(34, "金鹰网（jinying）");
		app02Map.put(35, "Tomlive");
		app02Map.put(36, "SopCast");
		app02Map.put(37, "CCIPTV");
		app02Map.put(38, "VJBase");
		app02Map.put(39, "乐鱼");
		app02Map.put(40, "JeBoo");
		app02Map.put(41, "UC影音");
		app02Map.put(42, "QQLive");
		app02Map.put(43, "联通网视机");
		app02Map.put(44, "六间房");
		app02Map.put(45, "iV影音");
		app02Map.put(46, "暴风盒子");
		app02Map.put(47, "abacast");
		app02Map.put(48, "Neo视频");
		app02Map.put(49, "百度点播");
		app02Map.put(50, "顶悦视听盒");
		app02Map.put(51, "沸点");
		app02Map.put(52, "酷魔网络电视");
		app02Map.put(53, "乐酷网络录像机");
		app02Map.put(54, "磊客");
		app02Map.put(55, "麦克风");
		app02Map.put(56, "青娱乐");
		app02Map.put(57, "喔喔播放器");
		app02Map.put(58, "优度直播");
		app02Map.put(59, "手机A67电影网");
		app02Map.put(60, "百度视频");
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

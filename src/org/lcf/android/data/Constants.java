package org.lcf.android.data;

public class Constants {
	private Constants(String serverAddrAffix,String signature) {
		if(serverAddrAffix!=null){
			serverAddr = serverAddrAffix;
			if(!serverAddr.endsWith("/")){
				serverAddr += '/';
			}
		}else{
			serverAddr = SERVER_ADDR_AFFIX_DEFAULT;
		}
		if(signature!=null){
			this.signature = signature;
		}
		else{
			signature = SIGNATUE_DEFAULT;
		}
	}
	public static String DATA_REQ_EVENT = "DATA_REQ_EVENT";
	public static String DATA_RESP_EVENT = "DATA_RESP_EVENT";
	public static String DATA_RESP_EVENT_RESULT = "DATA_RESP_EVENT_RESULT";
	//缺省服务器地址
	public static String SERVER_ADDR_AFFIX_DEFAULT = "127.0.0.1:8080/sc/";
	//缺省签名码
	public static String SIGNATUE_DEFAULT = "!#^U35{.^(^%()nS@Z*9056-=?+";
	//签名名字
	public static String SIGNATUE_NAME = "signature";
	
	private String serverAddr = null;
	private String signature = null;
	
	public static String getServerAddr() {
		return cnst.serverAddr;
	}

	public static String getSignature() {
		return cnst.signature;
	}

	public static void init(String serverAddrAffix,String signature){
		cnst = new Constants(serverAddrAffix,signature);
	}
	public static Constants cnst = new Constants(SERVER_ADDR_AFFIX_DEFAULT,SIGNATUE_DEFAULT);
}

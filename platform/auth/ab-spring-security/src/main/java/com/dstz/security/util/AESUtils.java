package com.dstz.security.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 对称加密(AES)
 * 
 * @author Joe
 */
public class AESUtils {
	
	public static final String INIT_VECTOR = "RandomInitVector";
	public static final String PRIVATE_KEY = "zbom20180922!@#$";

	/**
	 * 加密
	 * @param key 密钥
	 * @param value 加密数据
	 * @return
	 */
	public static String encrypt(String key, String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Base64Util.encodeBytes(encrypted);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * @param key 密钥
	 * @param encrypted 解密数据
	 * @return
	 */
	public static String decrypt(String key, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64Util.decode(encrypted));

			return new String(original);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		String str = "2[{\"broleName\":\"仓管员\",\"broleStatus\":\"Y\",\"operType\":\"0\",\"bonlyId\":842},{\"broleName\":\"网速测试\",\"broleStatus\":\"Y\",\"operType\":\"0\",\"bonlyId\":1282}]2019-01-11 11:59:07crmzbom20180922!@#$";
//		System.out.println(str);
//		System.out.println(MD5.string2MD5(str).toUpperCase());


		String var = "Xrz4%2Fe1h3pbiLGfVlWYIM3VOMLDjJHkQSLSSErQRiS5vWgG%2B0sGXCQLaIW4Uod1izlp6RKC0K74P%0A1%2BzmgNK7ow%3D%3D";

		//System.out.println(URLEncoder.encode("Xrz4%2Fe1h3pbiLGfVlWYIM12Vj0GtMaZ79IujEd4WNgM4d9QZcv%2BaqOzl%2FR2K%2Fs%2FVaAWS2OleN2uV%0A3lzglMli5g%3D%3D"));
		String key = "zbom20180922!@#$"; // 128 bit key
		Map resultMap = new HashMap();
		resultMap.put("ssouserid", 499);
		resultMap.put("appid", 2);
		resultMap.put("entranceid", 4);
//		System.out.println((URLDecoder.decode("PLFONVn89T%2FbNZKGRbMQaB9wYdaUNh1Wp88cfDBKykVXjdNH4CmfReijn6tl2i98CgyspshTTvg%2F%0AenRyPuIS4g%3D%3D")));

//		System.out.println(URLDecoder.decode("Yu9FRXlPGZ6m4HXRhOsAOrfjRM384t%2FcEwtCtKhoyH3ETT83r3g4CWOc38PdcYRig2xXZNHHhLWu%0At7FNn%2FRjKQ%3D%3D"));
		System.out.println(AESUtils.decrypt(AESUtils.PRIVATE_KEY, URLDecoder.decode(var)));

		//System.out.println("------------>"+AESUtils.decrypt(AESUtils.PRIVATE_KEY, URLDecoder.decode("Yu9FRXlPGZ6m4HXRhOsAOuaM5QFPc0%2BV7yrYkGE2GgAZOfxMV2hdIgjKFy%2Bg%2BO816e6d4oOI6Zeh%0AJxt4tJZswhoSTG%2FW7uUU4AEKoJhoRMU%3D")));
	}
}

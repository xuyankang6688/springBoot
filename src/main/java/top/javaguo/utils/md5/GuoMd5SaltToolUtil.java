package top.javaguo.utils.md5;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.Random;

/**
 * Md5加盐
 * 
 * @datetime 2018/03/07
 * @author javaGuo
 */
public class GuoMd5SaltToolUtil {

	/** 获取盐 **/
	public static String getSalt() {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(16);
		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
		int len = sb.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sb.append("0");
			}
		}
		String salt = sb.toString();
		return salt;
	}

	/**
	 * 加盐MD5
	 */
	public static String generate(String password, String salt) {
		StringBuilder sb = new StringBuilder(salt);
		int len = sb.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sb.append("0");
			}
		}
		salt = sb.toString();
		password = md5Hex(password + salt);
		/*char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = password.charAt(i / 3 * 2);
			char c = salt.charAt(i / 3);
			cs[i + 1] = c;
			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
		}
		return new String(cs);*/
		return password;
	}

	/**
	 * 校验加盐后是否和原文一致
	 */
	public static boolean verify(String password, String md5) {
		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = md5.charAt(i);
			cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
			cs2[i / 3] = md5.charAt(i + 1);
		}
		String salt = new String(cs2);
		return md5Hex(password + salt).equals(new String(cs1));
	}

	/**
	 * 获取十六进制字符串形式的MD5摘要
	 */
	public static String md5Hex(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(src.getBytes());
			return new String(new Hex().encode(bs));
		} catch (Exception e) {
			return null;
		}
	}
/*
	// 测试主函数
	public static void main(String args[]) {
		// 原文
		String plaintext = "DingSai";

		// 获取加盐后的MD5值
		String ciphertext = GuoMd5SaltToolUtil.generate(plaintext, getSalt());
		System.out.println("加盐后MD5：" + ciphertext);
		System.out.println("是否是同一字符串:" + GuoMd5SaltToolUtil.verify(plaintext, ciphertext));
		*//**
		 * 其中某次DingSai字符串的MD5值
		 *//*
		String[] tempSalt = { "c4d980d6905a646d27c0c437b1f046d4207aa2396df6af86",
				"66db82d9da2e35c95416471a147d12e46925d38e1185c043",
				"61a718e4c15d914504a41d95230087a51816632183732b5a" };

		for (String temp : tempSalt) {
			System.out.println("是否是同一字符串:" + GuoMd5SaltToolUtil.verify(plaintext, temp));
		}

	}*/

}
package com.example.javatest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtils {

	public static void main(String[] args) {
		System.out.println(DigestUtils.encryptPassword("123654789", "cloud"));
	}

	public static String encryptPassword(String password, String account) {
		return DigestUtils.md5Hex(password + "{" + account + "}");
	}

	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String md5Hex(final String data) {
		return encodeHexString(md5(data));
	}

	public static byte[] md5(final String data) {
		return md5(getBytesUtf8(data));
	}

	public static byte[] md5(final byte[] data) {
		return getMd5Digest().digest(data);
	}

	public static String encodeHexString(final byte[] data) {
		return new String(encodeHex(data));
	}

	public static char[] encodeHex(final byte[] data) {
		return encodeHex(data, DIGITS_LOWER);
	}

	protected static char[] encodeHex(final byte[] data, final char[] toDigits) {
		final int l = data.length;
		final char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
			out[j++] = toDigits[0x0F & data[i]];
		}
		return out;
	}

	public static MessageDigest getMd5Digest() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (final NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static byte[] getBytesUtf8(final String string) {
		if (string == null) {
			return null;
		}
		byte retByte[] = null;
		try {
			retByte = string.getBytes("UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retByte;
//		return string.getBytes(Charset.forName(CharEncoding.UTF_8));
	}
}

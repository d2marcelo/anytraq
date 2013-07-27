package com.brtracker.shared.utils.packet;

public class GPSReaderUtils {
	
	public static double latHexToDegrees(short[] ibuf) {
		
		short[] buf = new short[4];
		
		buf[0] = ((ibuf[1] & 0x80) == 1) ? (short) 0xFF : 0x00;
		buf[1] = ibuf[0];
		buf[2] = ibuf[1];
		buf[3] = ibuf[2];
		
		int intLat = byteIntoInt(buf);
		
		return intToDegrees(intLat); 
	}
	
	public static double lngHexToDegrees(short[] ibuf) {
		
		int intLng = byteIntoInt(ibuf);
		
		return intToDegrees(intLng); 
	}

	public static int byteIntoInt(short[] buf) {
		int value = 0;
		for (int i=0; i<buf.length; i++) {
			value = value << 8 | (buf[i] & 0xff);
		}
		return value;
	}
	
	public static double intToDegrees(int value) {
		int sign = value < 0 ? -1:1;
		double tempVal = Math.abs(value);
		double degrees = Math.floor(tempVal/100000.0);
		double minutes = (tempVal - (degrees*100000.0))/1000.0; 
		
		double valInDegrees = degrees + (minutes/60.0);
		
		return sign*valInDegrees;
	}

	public static double latHexToDegrees2(short[] ibuf) {
		
		short[] buf = new short[4];
		
		buf[0] = ((ibuf[1] & 0x80) == 1) ? (short) 0xFF : 0x00;
		buf[1] = ibuf[1];
		buf[2] = ibuf[2];
		buf[3] = ibuf[3];
//		printBuffer(buf);		
		int intLat = byteIntoInt(buf);
//		System.out.println("nmeaLat " + binaryToNmea(intLat));
		return binaryToNmea2(intLat); 
	}
	
	public static void printBuffer(short[] buf) {
		System.out.println("0x" + Integer.toHexString(buf[0]) + " 0x" + Integer.toHexString(buf[1])
				+ " 0x" + Integer.toHexString(buf[2]) + " 0x" + Integer.toHexString(buf[3]));
	}
	
	public static double lngHexToDegrees2(short[] buf) {
		
//		printBuffer(buf);
		int intLng = byteIntoInt(buf);
//		System.out.println("nmeaLng " + binaryToNmea(intLng));
		return binaryToNmea2(intLng); 
	}

	public static String binaryToNmea(int value) {
		int sign = value < 0 ? -1:1;
		double tempInteger = Math.abs(value);
		
		double degrees=Math.floor(tempInteger/100000);
		double decimalMinutes = (tempInteger - (degrees * 100000)) / 1000;
		
		return "s:" + sign + " d:" + degrees + " m:" + decimalMinutes + " v=" + (degrees + decimalMinutes/60.0);
	}

	public static double binaryToNmea2(int value) {
		int sign = value < 0 ? -1:1;
		double tempInteger = Math.abs(value);
		
		double degrees=Math.floor(tempInteger/100000);
		double a = (tempInteger - (degrees * 100000));
		
		double b = ((a*1000)/60);
		double c = Math.floor(b);
//		int n = getNumDigits(c);
		double d = divideByPowerOf10(b,6);
//		System.out.println("c:" + c);
//		if (n > 6) {
//			d = divideByPowerOf10(c, n-6);
//			System.out.println("d:" + d);
//			d = Math.floor(d);
//			System.out.println("d:" + d);
//			d = divideByPowerOf10(d, n);
//			System.out.println("d:" + d);
//		} else {
//			d = divideByPowerOf10(c, 6);
//		}
//		System.out.println("d:" + d);
		System.out.println("t:" + tempInteger + " d:" + degrees + " a:" + a + " b:" + b + " d:" + d + " v=" + (degrees + d));
		
//		double decimalMinutes = a / 1000;
//		
//		System.out.println("t:" + tempInteger + " d:" + degrees + " m:" + decimalMinutes 
//				+ " v=" + (degrees + decimalMinutes/60.0));
		
//		return sign*(degrees + decimalMinutes/60.0);
		return sign*(degrees + d);
	}

	public static double intToDegrees2(int value, int times) {
		
		int sign = value < 0 ? -1:1;
		double tempVal = Math.abs(value);
		double t1 = tempVal/100000.0;
		double degrees = Math.floor(t1);
		double t10 = (t1 - degrees);
		double t11 = t10*1000000.0;
		
		double t3 = (t11*1000.0)/60.0;
		
		double t4 = getMinutes(t3, 6);
		
		double valInDegrees = degrees + t4;
		System.out.println("converting " + value + " " + degrees + " " + t10 + " " + t11 + " " + t3 + " " + t4 + " " + valInDegrees);
		return sign*valInDegrees;
	}
	
	public static double getMinutes(double m, int p) {
		double degrees = Math.floor(m);
		int numDigits = getNumDigits(degrees);
		StringBuffer sVal = new StringBuffer();
		sVal.append("0.");
		if (numDigits > p) {
			extractDigits(degrees, p, sVal);
		} else {
			int c = p-numDigits;
			while (c >0) {
				sVal.append("0");
			}
		}
		
		return Double.valueOf(sVal.toString());
	}

	private static void extractDigits(double degrees, int p, StringBuffer sVal) {
		String sDeg = String.valueOf(degrees);
		int c = 0;
		while (sDeg.length() > c && c < p) {
			sVal.append(sDeg.charAt(c));
			c++;
		}
	}

	public static double multiplyByPowerOf10(double degrees, int i) {
		double r = degrees;
		while (i > 0) {
			r *= 10;
			i--;
		}
		return r;
	}

	public static double divideByPowerOf10(double degrees, int i) {
		double r = degrees;
		while (i > 0) {
			r /= 10;
			i--;
		}
		return r;
	}

	public static double getFraction(double v) {
		int digits = getNumDigits(v);
		return divideByPowerOf10(v, digits);
	}
	
	public static int getNumDigits(double v) {
		int d = 0;
		while (v > 1) {
			v /= 10;
			d++;
		}
		return d;
	}

}

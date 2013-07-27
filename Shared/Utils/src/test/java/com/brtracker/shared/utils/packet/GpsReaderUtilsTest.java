package com.brtracker.shared.utils.packet;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import junit.framework.Assert;
import junit.framework.TestCase;

public class GpsReaderUtilsTest extends TestCase {

	public static class ATT {
		private Long id;
		private String name;
		private Date expDate;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getExpDate() {
			return expDate;
		}
		public void setExpDate(Date expDate) {
			this.expDate = expDate;
		}
		
	}
	public void testBeanUtils() {
		
		ATT bean = new ATT();
		try {
			//Map describe = BeanUtilsBean.getInstance().describe(bean);
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//0xff 0x46 0x3f 0xe1
	public void testHexToInt() {
		short[] buf1 = new short[] {
				0x0, 0x38, 0xbf, 0x45
		};
		double v = GPSReaderUtils.byteIntoInt(buf1);
		System.out.println(v);
		
		buf1 = new short[] {
				0xff, 0x46, 0x7c, 0x7c
		};
		v = GPSReaderUtils.byteIntoInt(buf1);
		System.out.println(v);
		
		buf1 = new short[] {
				0x0, 0x38, 0xbd, 0x4
		};
		v = GPSReaderUtils.byteIntoInt(buf1);
		System.out.println(v);
		
		buf1 = new short[] {
				0xff, 0x46, 0x3f, 0xe1
		};
		v = GPSReaderUtils.byteIntoInt(buf1);
		System.out.println(v);
	}
	public void testGpsLng() {
		short[] buf1 = new short[] {
				0xffffffff, 0x46, 0x77, 0xffffffc0
		};
		double lng = GPSReaderUtils.lngHexToDegrees(buf1);
		Assert.assertEquals("Invalid lng value", -121.984, lng);
		
		System.out.println(lng);
		
		short[] buf2 = new short[] {
				0xffffffff, 0x46, 0x3f, 0xfffffff1
		};
		lng = GPSReaderUtils.lngHexToDegrees(buf2);
		Assert.assertEquals("Invalid lng value", -122.22211666666666, lng);
		
		System.out.println(lng);
		
		short[] buf3 = new short[] {
				0xff, 0x46, 0x77, 0xc0
		};
		lng = GPSReaderUtils.lngHexToDegrees(buf3);
		Assert.assertEquals("Invalid lng value", -121.984, lng);
		
		System.out.println(lng);

		short[] buf4 = new short[] {
				0xff, 0x46, 0x3f, 0xf1
		};
		lng = GPSReaderUtils.lngHexToDegrees(buf4);
		Assert.assertEquals("Invalid lng value", -122.22211666666666, lng);
		
		System.out.println(lng);
	}

	//ffffffff 45 ffffffd1 4d
	public void testGpsLatLng() {
		short[] buf1 = new short[] {
				0x00, 0x38, 0xc9, 0x1a
		};
		double lat = GPSReaderUtils.latHexToDegrees2(buf1);
		Assert.assertEquals("Invalid lat value", 37.35829999999999, lat);
		
		System.out.println(lat);
		
		short[] buf2 = new short[] {
				0xff, 0x46, 0x3f, 0x1b
		};
		double lng = GPSReaderUtils.lngHexToDegrees2(buf2);
		Assert.assertEquals("Invalid lng value", -121.12256833333333, lng);
		
		System.out.println(lng);
		
	}

	public void testGpsLng2() {
		short[] buf2 = new short[] {
				0xff, 0x45, 0xd1, 0x4d
		};
		double lng = GPSReaderUtils.lngHexToDegrees2(buf2);
		Assert.assertEquals("Invalid lng value", -122.2751666666666, lng);
		
		System.out.println(lng);
		
	}

	public void testGetNumDigits() {
		
		Assert.assertEquals(6, GPSReaderUtils.getNumDigits(350383.33333333797));
		Assert.assertEquals(5, GPSReaderUtils.getNumDigits(20766.666666673926));
		Assert.assertEquals(0, GPSReaderUtils.getNumDigits(0.666666673926));
		Assert.assertEquals(1, GPSReaderUtils.getNumDigits(01.666666673926));
		Assert.assertEquals(0, GPSReaderUtils.getNumDigits(-01.666666673926));
				
	}
	
	public void testDivideBy() {
		
		Assert.assertEquals(35038.333333333794,GPSReaderUtils.divideByPowerOf10(350383.33333333797, 1));
		Assert.assertEquals(3503.8333333333794,GPSReaderUtils.divideByPowerOf10(350383.33333333797, 2));
		Assert.assertEquals(0.350383333333338,GPSReaderUtils.divideByPowerOf10(350383.33333333797, 6));
		Assert.assertEquals(0.035038333333333796,GPSReaderUtils.divideByPowerOf10(350383.33333333797, 7));
				
	}

	public void testMultiplyBy() {
		
		Assert.assertEquals(3503.3300000000004, GPSReaderUtils.multiplyByPowerOf10(350.333, 1));
		Assert.assertEquals(35033.3, GPSReaderUtils.multiplyByPowerOf10(350.333, 2));
		Assert.assertEquals(3.50333E8, GPSReaderUtils.multiplyByPowerOf10(350.333, 6));
		Assert.assertEquals(3.50333E9, GPSReaderUtils.multiplyByPowerOf10(350.333, 7));
				
	}
	
	public void testGetMinutes() {
		
		Assert.assertEquals(350383.0, GPSReaderUtils.getMinutes(350383.33333333797, 6));
		Assert.assertEquals(276160.0, GPSReaderUtils.getMinutes(27616.66666666921, 6));
		Assert.assertEquals(122238.3, GPSReaderUtils.getMinutes(1222383.333333331, 6));
		Assert.assertEquals(238300.0, GPSReaderUtils.getMinutes(002383.333333331, 6));
		Assert.assertEquals(238300.0, GPSReaderUtils.getMinutes(0238300.333333331, 6));
		
				
	}



}

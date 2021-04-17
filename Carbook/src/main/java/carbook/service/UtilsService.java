package carbook.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilsService {

	public static Date changeStringToDate(String chuoiDate) {
		

		SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		Date ngay = new Date();
		try {
			ngay = df.parse(chuoiDate);
		
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return ngay;
	}
	
	public static String changeDateToString(Date ngay) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String chuoiNgay = formatter.format(ngay);
		return chuoiNgay;
	}
	
	public static Date changeFormatDate(Date ngay) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String chuoiNgay = formatter.format(ngay);
		try {
			ngay = formatter.parse(chuoiNgay);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return ngay;
	}
	public static Date changeFormatDateTime(Date ngay) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String chuoiNgay = formatter.format(ngay);
		try {
			ngay = formatter.parse(chuoiNgay);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return ngay;
	}
	public static String getTimeFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("HH:mm").format(date));
		}
	}
	
	public static String getDateHourFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		}
	}
	
	public static String getDateFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		}
	}
	
	public static String convertListObjectToJsonArrayt(List<?> objects) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			mapper.writeValue(out, objects);
			final byte[] data = out.toByteArray();
			return new String(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getHour(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("HH").format(date));
		}
	}
}

package top.javaguo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述: 日期工具类
 */
public final class GuoDateUtil {

	/**
	 * 描述: 日期转为字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				result = "";
			}
		}
		return result;
	}

	/**
	 * 描述: 字符串转为日期类型
	 * 
	 * @param strDate
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String strDate, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(strDate);
		} catch (Exception er) {
			return null;
		}
	}

	/**
	 * 描述: 获取当前完整时间
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		return dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 描述: 获取当前年份
	 * 
	 * @return
	 */
	public static String getCurrentYear() {
		return dateToString(new Date(), "yyyy");
	}

	/**
	 * 描述: 获取当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return dateToString(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 描述: 获取当前时间(时、分、秒)
	 * 
	 * @return
	 */
	public static String getCurrentSimpleTime() {
		return dateToString(new Date(), "HH:mm:ss");
	}

	/**
	 * 描述: 把日期转成完整格式。如：2014-1-1 转化后为 2014-01-01
	 * 
	 * @param strDate
	 * @return
	 */
	public static String toComplexDate(String strDate) {
		try {
			String tmp_date[] = strDate.split("-");
			String tmp_year = tmp_date[0];
			String tmp_month = tmp_date[1];
			String tmp_day = tmp_date[2];
			if (Integer.parseInt(tmp_month) <= 9) {
				tmp_month = "0" + tmp_month;
			}
			if (Integer.parseInt(tmp_day) <= 9) {
				tmp_day = "0" + tmp_day;
			}
			return tmp_year + "-" + tmp_month + "-" + tmp_day;
		} catch (Exception er) {
			return strDate;
		}
	}

	/**
	 * 描述: 获取N年后日期
	 * 
	 * @param date
	 * @param years
	 *            年数
	 * @return
	 */
	public static Date getAfterDateByYears(Date date, int years) {
		try {
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.add(Calendar.YEAR, years);
			return now.getTime();
		} catch (Exception er) {
			return date;
		}
	}

	/**
	 * 描述: 获取N月后日期
	 * 
	 * @param date
	 * @param months
	 *            月数
	 * @return
	 */
	public static Date getAfterDateByMonths(Date date, int months) {
		try {
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.add(Calendar.MONTH, months);
			return now.getTime();
		} catch (Exception er) {
			return date;
		}
	}

	/**
	 * 描述: 获取N天后日期
	 * 
	 * @param date
	 * @param months
	 *            月数
	 * @return
	 */
	public static Date getAfterDateByDays(Date date, int days) {
		try {
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.add(Calendar.DAY_OF_YEAR, days);
			return now.getTime();
		} catch (Exception er) {
			return date;
		}
	}

	/**
	 * 描述: 获取N小时后日期
	 * 
	 * @param date
	 * @param hours
	 *            小时数
	 * @return
	 */
	public static Date getAfterDateByHours(Date date, int hours) {
		try {
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.add(Calendar.HOUR, hours);
			return now.getTime();
		} catch (Exception er) {
			return date;
		}
	}

	/**
	 * 描述: 获取N分钟后日期
	 * 
	 * @param date
	 * @param hours
	 *            小时数
	 * @return
	 */
	public static Date getAfterDateByMinutes(Date date, int minutes) {
		try {
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.add(Calendar.MINUTE, minutes);
			return now.getTime();
		} catch (Exception er) {
			return date;
		}
	}

	/**
	 * 描述: 计算两日期相差天数
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return
	 */
	public static int getBetweenDays(String startDate, String endDate) {
		try {
			String formart="yyyy-MM-dd HH:mm:ss";
			Date dateOld = stringToDate(startDate,formart);
			Date dateNew = stringToDate(endDate,formart);
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(dateOld);
			long startTime = startCalendar.getTimeInMillis();
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(dateNew);
			long endTime = endCalendar.getTimeInMillis();
			long between_days = (endTime - startTime) / (1000 * 60 * 60 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception er) {
			return 0;
		}
	}

}

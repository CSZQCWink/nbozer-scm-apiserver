package com.sungeon.bos.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @BelongsPackage: com.sungeon.bos.utils
 * @ClassName: DateTimeCoverUtil
 * @Author: 陈苏洲
 * @Description: 日期转换工具
 * @CreateTime: 2023-11-07 13:31
 * @Version: 1.0
 */

/**
 * 时间转化工具
 *
 * @author suxuelian
 */
public class DateConvertUtil {
	/**
	 * @title: dateToStamp
	 * @author: 陈苏洲
	 * @description: String类型的时间转化为时间戳
	 * @param: []
	 * @return: void
	 * @throws:
	 * @date: 2023/11/7 13:32
	 **/
	public Long dateToStamp(String str) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(str);
		long ts = date.getTime();
		return ts;
	}

	/**
	 * 根据时间转换为时间戳
	 *
	 * @param date
	 * @param timestampType 转换类型 0毫秒 1秒
	 * @return
	 */
	public long getTimeStamp(Date date, int timestampType) {
		long times = date.getTime();
		if (timestampType == 1) {
			times = times / 1000L;
		}
		return times;
	}

	/**
	 * 时间戳转时间
	 *
	 * @param timestamp
	 * @param timestampType 时间戳格式 0毫秒 1秒
	 * @return
	 */
	public Date getDateTime(long timestamp, int timestampType) {
		if (timestampType == 1) {
			//如果时间戳格式是秒，需要江时间戳变为毫秒
			timestamp = timestamp * 1000L;
		}
		Date dateTime = new Date(timestamp);
		return dateTime;

	}

	/**
	 * 格式化传入的时间，将时间转化为指定格式字符串
	 *
	 * @param date
	 * @param format 时间格式，如：yyyy-MM-dd HH:mm:ss SSS 或 yyyy年MM月dd日 HH:mm:ss
	 * @return
	 */
	public String getDateTimeString(Date date, String format) {
		if (format == null || format.length() <= 0) {
			return null;
		}
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String timeString = sdf.format(date);
		return timeString;
	}

	/**
	 * 格式化传入的时间戳，将时间戳转化为指定格式字符串
	 *
	 * @param timestamp
	 * @param format        时间格式，如：yyyy-MM-dd HH:mm:ss SSS 或 yyyy年MM月dd日 HH:mm:ss     *
	 * @param timestampType 时间戳格式 0毫秒 1秒
	 * @return
	 */
	public String getTimeStampString(long timestamp, String format, int timestampType) {
		if (format == null || format.length() <= 0) {
			return null;
		}
		if (timestampType == 1) {
			//如果时间戳格式是秒，需要江时间戳变为毫秒
			timestamp = timestamp * 1000L;
		}
		Date dateTime = new Date(timestamp);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String timeString = sdf.format(dateTime);
		return timeString;
	}

}





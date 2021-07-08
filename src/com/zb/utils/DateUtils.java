package com.zb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
 * һ��������
 * 
 * SimpleDateFormat�� ������Date���ַ��������໥֮���ת��
 * 
 * ����format ���Խ�Dateת����String
 * 
 * ����parse  ���Խ�Stringת����Date
 * 				 ע��
 *           	ģʽ�е���ĸ���ܸ��ģ�����ģʽ�ķ��ſ��Ըı�
 *          	��yyyy��MM��dd�� HHʱmm��ss�롱
 */
public class DateUtils {
	private static final String formateStr="yyyy-MM-dd hh:mm:ss";//Ĭ�ϸ�ʽ
	/**
	 * ������ת���ַ���
	 * @param dateҪת�������ڶ���
	 * @param formatestrҪת���ĸ�ʽ
	 * @return ����ת����������ַ���
	 */
	public static String dateToString(Date date,String formateStr) {
		String destestr = null;
		SimpleDateFormat simDate = new SimpleDateFormat(formateStr);
		destestr=simDate.format(date);
		return destestr;
		
	}
	/**
	 * ������ת���ַ���
	 * @param date Ҫת�������ڶ���
	 * @return  ����ת����������ַ���
	 */
	public static String dateToString(Date date) {
		String destestr = null;
		SimpleDateFormat simDate = new SimpleDateFormat(formateStr);
		destestr=simDate.format(date);
		return destestr;
		
	}
	/**
	 * ���ַ���ת������
	 * @param String Ҫת�����ַ�������
	 * @param String Ҫת���ĸ�ʽ
	 * @return ����ת����������ַ���
	 */
	public static Date stringToDate(String value,String formateStr) throws ParseException {
		Date date = null;
		SimpleDateFormat simDate = new SimpleDateFormat(formateStr);
		date=simDate.parse(value);
		return date;
	}
	/**
	 * ���ַ���ת������
	 * @param String Ҫת�����ַ�������
	 * @return ����ת����������ַ���
	 */
	public static Date stringToDate(String value) throws ParseException {
		Date date = null;
		SimpleDateFormat simDate = new SimpleDateFormat(formateStr);
		date=simDate.parse(value);
		return date;
	}
}

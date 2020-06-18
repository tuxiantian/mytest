package com.tuxt.mytest.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.ai.frame.logger.Logger;
import com.ai.frame.logger.LoggerFactory;

/**
 * Parsing The Configuration File
 * 
 * @author shilei6@asiainfo.com
 * @since 2015-01-05
 */
public final class PropertiesUtil {
	private static final Logger logger = LoggerFactory.getUtilLog(PropertiesUtil.class);
	private static final String BUNDLE_NAME = "config/system";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private PropertiesUtil() {
	}

	/**
	 * Get a value based on key , if key does not exist , null is returned
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return null;
		}
	}
	public static String getString(String key,String defaultValue) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			logger.error("PropertiesUtil#getString",key+" not found in system.properties", e);
			return defaultValue;
		}
	}
	public static int getInt(String key,int defaultValue) {
		try {
			return Integer.parseInt(RESOURCE_BUNDLE.getString(key));
		} catch (MissingResourceException e) {
			logger.error("PropertiesUtil#getInt",key+" not found in system.properties", e);
			return defaultValue;
		}
	}
	public static boolean getBoolean(String key,boolean defaultValue) {
		try {
			return Boolean.parseBoolean(RESOURCE_BUNDLE.getString(key));
		} catch (MissingResourceException e) {
			logger.error("PropertiesUtil#getBoolean",key+" not found in system.properties", e);
			return defaultValue;
		}
	}
	public static void main(String[] args) {
		System.out.println(PropertiesUtil.getInt("PAGE_NUMBER1", 10));
	}
}
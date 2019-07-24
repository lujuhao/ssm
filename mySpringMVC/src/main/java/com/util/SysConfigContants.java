package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 属性配置文件工具类
 * @author ljh
 */
public class SysConfigContants {
	
	private static final Logger LOGGER = Logger.getLogger(SysConfigContants.class);
	
	public static String USER_HEADIMG_PATH; //用户头像上传路径
	
	public static String USER_HEADIMG_DEFAULT; //用户默认头像路径
	
	static{
		loadApplicationProperty("application.properties");
	}
	
	private static void loadApplicationProperty(String path){
		InputStream is = null;
		Properties properties = new Properties();
        try{
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    		if (null == is) {
    			throw new FileNotFoundException(path
    					+ " cannot be opened because it does not exist");
    		} else {
    			properties.load(is);
    			USER_HEADIMG_PATH = properties.getProperty("user.headImg.path");
    			USER_HEADIMG_DEFAULT = properties.getProperty("user.headImg.default");
			}
        } catch (IOException e){
            LOGGER.error("Exception happened in loadProp() ", e);
        } finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					LOGGER.error("Exception happened in loadProperty()", e);
				}
			}
		}
	}
	
	
}

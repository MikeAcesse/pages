package com.lanyou.springboothibernatepaging.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author fanzk
 * @version 1.8
 * @date 2020/12/14 16:22
 */
public class JdbcUtil {
	//表示定义数据库的用户名
	private static String USERNAME;
	//定义数据库的密码
	private static String PASSWORD;
	//定义数据库的驱动信息
	private static String DRIVER;
	//定义访问数据库的地址
	private static String URL;
	//定义数据库的链接
	private Connection connection;
	//定义sql 语句的执行对象
	private PreparedStatement pstmt;
	//定义查询返回的结果集合
	private ResultSet resultSet;
	static {
		//加载数据库配置信息，并给相关的属性赋值
		loadConfig();
	}
	/**
	 * 加载数据库配置信息，并给相关的属性赋值
	 */
	public static void loadConfig(){
      try{
	      InputStream inputStream = JdbcUtil.class.getResourceAsStream("/jdbc.properties");
	      Properties prop = new Properties();
	      prop.load(inputStream);
	      USERNAME = prop.getProperty("jdbc.username");
	      PASSWORD = prop.getProperty("jdbc.password");
	      DRIVER = prop.getProperty("jdbc.driver");
	      URL = prop.getProperty("jdbc.url");
      }catch (Exception e){
         throw new RuntimeException("读取数据库配置文件异常！",e);
      }
	}

	public JdbcUtil() {
	}

	/**
	 *  获取数据库链接
	 * @return
	 */
	public Connection getConnection(){
		try{
			Class.forName(DRIVER); //注册驱动
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD); //获取链接
		}catch (Exception e){
			throw  new RuntimeException("get connection error!",e);
		}
		return connection;
	}
}

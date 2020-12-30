package com.lanyou.springbootpaging.config;

import org.hibernate.dialect.MySQL8Dialect;

/**
 * @author fanzk
 * @version 1.8
 * @date 2020/12/9 9:43
 */
public class MysqlConfig extends MySQL8Dialect {
	@Override
	public String getTableTypeString() {
		return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
	}
}

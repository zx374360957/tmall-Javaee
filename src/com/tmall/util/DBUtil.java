package com.tmall.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static String ip = "localhost";
	static int port = 3306;
	static String database = "tmall";
	static String encoding = "utf-8";
	static String user = "root";
	static String password = "xieshuai";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&useSSL=false", ip, port, database,
				encoding);
		return DriverManager.getConnection(url, user, password);
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}

}
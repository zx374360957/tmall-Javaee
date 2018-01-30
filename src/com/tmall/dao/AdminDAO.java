package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tmall.beans.Admin;
import com.tmall.util.DBUtil;

public class AdminDAO {

	public Admin get(String name, String password) {
		Admin admin = null;
		String sql = "SELECT * FROM admin WHERE adminname=? and pwd=?";
		try (Connection conn = DBUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin.setAdminName(name);
				admin.setPassword(password);
				admin.setRole(rs.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}
}

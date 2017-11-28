package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tmall.beans.PropertyValue;
import com.tmall.util.DBUtil;

public class PropertyValueDAO {

	public int getTotal() {
		int total = 0;
		String sql = "SELECT count(*) FROM propertvalue";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	public void add(PropertyValue bean) {
		String sql = "INSERT INTO propertyvalue(id, pid, ptid, value)" + "VALUE (null, ?, ?, ?)";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, bean.getProduct().getId());
			ps.setInt(2, bean.getProperty().getId());
			ps.setString(3, bean.getValue());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				bean.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(PropertyValue bean) {
		String sql = "UPDATE propertyvalue SET pid = ?, ptid = ?, value = ? WHERE id = ?";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, bean.getProduct().getId());
			ps.setInt(2, bean.getProperty().getId());
			ps.setString(3, bean.getValue());
			ps.setInt(4, bean.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM propertyvalue WHERE id = ?";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PropertyValue get(int id) {
		PropertyValue bean = null;

		String sql = "SELECT * FROM propertyvalue WHERE id = ?";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bean = new PropertyValue();
				bean.setId(id);
				bean.setProduct(new ProductDAO().get(rs.getInt("pid")));
				bean.setProperty(new PropertyDAO().get(rs.getInt("ptid")));
				bean.setValue(rs.getString("value"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}
}

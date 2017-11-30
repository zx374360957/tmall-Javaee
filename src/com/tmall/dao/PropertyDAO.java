package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmall.beans.Category;
import com.tmall.beans.Property;
import com.tmall.util.DBUtil;

public class PropertyDAO {

	public int getTotal(int cid) {
		int total = 0;
		String sql = "SELECT count(*) FROM property WHERE cid = ?";
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

	public void add(Property bean) {
		try {
			String sql = "INSERT INTO property(id, name, cid) VALUES (null, ?, ?)";
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, bean.getName());
			ps.setInt(2, bean.getCategory().getId());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				bean.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Property bean) {
		try {
			String sql = "UPDATE property SET name = ?, cid = ? WHERE id = ?";
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, bean.getName());
			ps.setInt(2, bean.getCategory().getId());
			ps.setInt(3, bean.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Property get(int id) {
		Property property = null;
		try {
			String sql = "SELECT * FROM property WHERE id = ?";
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				property = new Property();
				property.setId(id);
				property.setName(rs.getString("name"));
				Category category = new CategoryDAO().get(rs.getInt("cid"));
				property.setCategory(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return property;
	}

	public void dalete(int id) {
		try {
			String sql = "DELETE FROM property WHERE id = ?";
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Property> list(int cid) {
		return list(cid, 0, Short.MAX_VALUE);
	}

	public List<Property> list(int cid, int beg, int len) {
		List<Property> ls = new ArrayList<Property>();
		try {
			String sql = "SELECT * FROM Property WHERE cid = ? LIMIT ?,?";
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setInt(2, beg);
			ps.setInt(3, len);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Property p = new Property();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				Category category = new CategoryDAO().get(cid);
				p.setCategory(category);
				ls.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ls;
	}
}

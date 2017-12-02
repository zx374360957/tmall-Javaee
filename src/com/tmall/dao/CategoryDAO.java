package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmall.beans.Category;
import com.tmall.util.DBUtil;

public class CategoryDAO {
	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection()) {
			Statement s = c.createStatement();
			String sql = "SELECT count(*) FROM category";

			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	public void add(Category bean) {
		String sql = "INSERT INTO category(id, name) VALUES (null, ?)";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, bean.getName());

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

	public void update(Category bean) {
		try (Connection c = DBUtil.getConnection()) {
			String sql = "UPDATE category SET name = ? WHERE id = ?";
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, bean.getName());
			ps.setInt(2, bean.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Category get(int id) {
		Category category = null;
		try (Connection c = DBUtil.getConnection()) {
			String sql = "SELECT * FROM category WHERE id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				category = new Category();
				category.setId(id);
				category.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return category;
	}

	public void dalete(int id) {
		try (Connection c = DBUtil.getConnection()) {
			String sql = "DELETE FROM category WHERE id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Category> list() {
		return list(0, Short.MAX_VALUE);
	}

	public List<Category> list(int beg, int len) {
		List<Category> ls = new ArrayList<Category>();
		try (Connection c = DBUtil.getConnection()) {
			String sql = "SELECT * FROM category LIMIT ?,?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, beg);
			ps.setInt(2, len);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category cg = new Category();
				cg.setId(rs.getInt("id"));
				cg.setName(rs.getString("name"));
				ls.add(cg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ls;
	}
}

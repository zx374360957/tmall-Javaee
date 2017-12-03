package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmall.beans.User;
import com.tmall.util.DBUtil;

public class UserDAO {

	public int getTotal() {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM user";
		try (Connection c = DBUtil.getConnection()) {
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

	public void add(User user) {
		String sql = "INSERT INTO user(id, name, password) VALUE(null, ?, ?)";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User get(int id) {
		User user = null;
		String sql = "SELECT * FROM user WHERE id = ?";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(id);
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public User get(String name) {
		User user = null;
		String sql = "SELECT * FROM user WHERE name = ?";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public User get(String name, String password) {
		User user = null;
		String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public void update(User user) {
		String sql = "UPDATE user SET name = ?, password = ? WHERE id = ?";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "DALETE FROM user WHERE id = ?";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isExist(String name) {
		return get(name) != null;
	}

	public List<User> list() {
		return list(0, Short.MAX_VALUE);
	}

	public List<User> list(int beg, int len) {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM user LIMIT ?, ?";

		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, beg);
			ps.setInt(2, len);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));

				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
}
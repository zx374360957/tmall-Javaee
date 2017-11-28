package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmall.beans.Review;
import com.tmall.util.DBUtil;
import com.tmall.util.DateUtil;

public class ReviewDAO {

	public int getTotal(int pid) {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM review WHERE pid = ?";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, pid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				total = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return total;
	}

	public void add(Review bean) {
		String sql = "INSERT INTO review(id, content, uid, pid, createDate)" + "VALUE (null, ?, ?, ?, ?)";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, bean.getContent());
			ps.setInt(2, bean.getUser().getId());
			ps.setInt(3, bean.getProduct().getId());
			ps.setTimestamp(4, DateUtil.d2t(bean.getCreateDate()));

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				bean.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Review bean) {
		String sql = "UPDATE review " + "SET conten = ?, uid = ?, pid = ?, createDate = ?" + "WHERE id = ?";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, bean.getContent());
			ps.setInt(2, bean.getUser().getId());
			ps.setInt(3, bean.getProduct().getId());
			ps.setTimestamp(4, DateUtil.d2t(bean.getCreateDate()));
			ps.setInt(5, bean.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM review WHERE id = ?";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Review get(int id) {

		Review bean = null;
		String sql = "SELECT * FROM review WHERE id = ?";

		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bean = new Review();
				bean.setId(id);
				bean.setContent(rs.getString("content"));
				bean.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				bean.setUser(new UserDAO().get(rs.getInt("uid")));
				bean.setProduct(new ProductDAO().get(rs.getInt("pid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}

	public List<Review> list(int pid) {
		return list(pid, 0, Short.MAX_VALUE);
	}

	public List<Review> list(int pid, int beg, int len) {
		List<Review> beans = new ArrayList<Review>();
		String sql = "SELECT * FROM review WHERE pid = ?";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, pid);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Review bean = new Review();
				bean.setId(rs.getInt("id"));
				bean.setContent(rs.getString("content"));
				bean.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				bean.setUser(new UserDAO().get(rs.getInt("uid")));
				bean.setProduct(new ProductDAO().get(rs.getInt("pid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}
}

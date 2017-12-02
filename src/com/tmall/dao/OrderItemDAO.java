package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tmall.beans.OrderItem;
import com.tmall.util.DBUtil;

public class OrderItemDAO {
	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection()) {
			Statement s = c.createStatement();
			String sql = "SELECT count(*) FROM orderItem";

			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	public void add(OrderItem bean) {
		try (Connection c = DBUtil.getConnection()) {
			String sql = "INSERT INTO orderItem(id, pid, oid, uid, number)" + " VALUES (null, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, bean.getProduct().getId());
			ps.setInt(2, bean.getOrder().getId());
			ps.setInt(3, bean.getProduct().getId());
			ps.setInt(4, bean.getNumber());

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

	public void update(OrderItem bean) {
		try (Connection c = DBUtil.getConnection()) {
			String sql = "UPDATE orderItem SET pid = ?, oid = ?," + " uid = ?, number = ?" + " WHERE id = ?";
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, bean.getProduct().getId());
			ps.setInt(2, bean.getOrder().getId());
			ps.setInt(3, bean.getProduct().getId());
			ps.setInt(4, bean.getNumber());
			ps.setInt(5, bean.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public OrderItem get(int id) {
		OrderItem bean = null;
		try (Connection c = DBUtil.getConnection()) {
			String sql = "SELECT * FROM orderItem WHERE id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bean = new OrderItem();
				bean.setId(id);
				bean.setProduct(new ProductDAO().get(rs.getInt("pid")));
				bean.setOrder(new OrderDAO().get(rs.getInt("oid")));
				bean.setUser(new UserDAO().get(rs.getInt("uid")));
				bean.setNumber(rs.getInt("number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}

	public void dalete(int id) {
		try (Connection c = DBUtil.getConnection()) {
			String sql = "DELETE FROM orderItem WHERE id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

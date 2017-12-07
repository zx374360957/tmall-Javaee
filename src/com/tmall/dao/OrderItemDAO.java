package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmall.beans.Order;
import com.tmall.beans.OrderItem;
import com.tmall.beans.Product;
import com.tmall.util.DBUtil;

public class OrderItemDAO {
	public int getTotalNumber(int oid) {
		int total = 0;
		String sql = "SELECT count(*) FROM orderItem WHERE oid = ?";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, oid);

			ResultSet rs = ps.executeQuery();
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
			String sql = "INSERT INTO orderItem(id, pid, oid, uid, number) VALUES (null, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, bean.getProduct().getId());
			ps.setInt(2, bean.getOrder().getId());
			ps.setInt(3, bean.getUser().getId());
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
			String sql = "UPDATE orderItem SET pid = ?, oid = ?, uid = ?, number = ? WHERE id = ?";
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

	public float getTotalCost(int oid) {
		float total = 0;
		try (Connection c = DBUtil.getConnection()) {
			String sql = "SELECT * FROM orderItem WHERE oid = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, oid);
			ProductDAO productDAO = new ProductDAO();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				float price = productDAO.get(rs.getInt("pid")).getPromotePrice();
				int number = rs.getInt("number");
				total += price * number;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return total;
	}

	public List<OrderItem> list(int oid) {
		return list(oid, 0, Short.MAX_VALUE);
	}

	public List<OrderItem> list(int oid, int beg, int len) {
		List<OrderItem> ls = new ArrayList<OrderItem>();

		try (Connection c = DBUtil.getConnection()) {
			String sql = "SELECT * FROM orderItem WHERE oid = ? LIMIT ?, ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, oid);
			ps.setInt(2, beg);
			ps.setInt(3, len);

			ResultSet rs = ps.executeQuery();
			ProductDAO productDAO = new ProductDAO();
			OrderDAO orderDAO = new OrderDAO();
			UserDAO userDAO = new UserDAO();
			ProductImageDAO productImageDAO = new ProductImageDAO();
			while (rs.next()) {
				OrderItem bean = new OrderItem();
				bean.setId(rs.getInt("id"));
				Product p = productDAO.get(rs.getInt("pid"));
				productImageDAO.fillImage(p);
				bean.setProduct(p);
				bean.setOrder(orderDAO.get(rs.getInt("oid")));
				bean.setUser(userDAO.get(rs.getInt("uid")));
				bean.setNumber(rs.getInt("number"));

				ls.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ls;
	}

	public void addAll(Order order) {
		List<OrderItem> ois = order.getOrderItems();
		for (OrderItem oi : ois) {
			oi.setOrder(order);
			add(oi);
		}
	}
}

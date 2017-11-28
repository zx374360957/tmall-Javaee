package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tmall.beans.ProductImage;
import com.tmall.util.DBUtil;

public class ProductImageDAO {

	public int getTotal() {
		int total = 0;
		String sql = "SELECT count(*) FROM productimage";
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

	public void add(ProductImage bean) {
		String sql = "INSERT INTO productimage(id, pid, type) VALUE(null, ?, ?)";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, bean.getProduct().getId());
			ps.setString(1, bean.getType());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				bean.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ProductImage get(int id) {
		ProductImage bean = null;
		String sql = "SELECT * FROM productimage WHERE id = ?";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				bean = new ProductImage();
				bean.setId(id);
				bean.setType(rs.getString("type"));
				bean.setProduct(new ProductDAO().get(rs.getInt("pid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}

	public void update(ProductImage bean) {
		String sql = "UPDATE productimage SET pid = ?, type = ? WHERE id = ?";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, bean.getProduct().getId());
			ps.setString(2, bean.getType());
			ps.setInt(3, bean.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dalete(int id) {
		String sql = "DELETE FROM productimage WHERE id = ?";
		try {
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// public List<ProductImage> list(){
	//
	// }
	// public List<ProductImage> list(int beg, int len){
	//
	// }

}

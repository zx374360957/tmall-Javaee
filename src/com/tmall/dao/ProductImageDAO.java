package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmall.beans.Product;
import com.tmall.beans.ProductImage;
import com.tmall.util.DBUtil;

public class ProductImageDAO {

	public int getTotal() {
		int total = 0;
		String sql = "SELECT count(*) FROM productimage";
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

	public void add(ProductImage bean) {
		String sql = "INSERT INTO productimage(id, pid, type) VALUE(null, ?, ?)";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, bean.getProduct().getId());
			ps.setString(2, bean.getType());
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
		try (Connection c = DBUtil.getConnection()) {
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
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, bean.getProduct().getId());
			ps.setString(2, bean.getType());
			ps.setInt(3, bean.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM productimage WHERE id = ?";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ProductImage> list(int pid, String type) {
		return list(pid, 0, Short.MAX_VALUE, type);

	}

	public List<ProductImage> list(int pid, int beg, int len, String type) {
		List<ProductImage> ls = new ArrayList<ProductImage>();
		try (Connection c = DBUtil.getConnection()) {
			String sql = "SELECT * FROM productimage WHERE pid=? AND type=? LIMIT ?,?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, pid);
			ps.setString(2, type);
			ps.setInt(3, beg);
			ps.setInt(4, len);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductImage pi = new ProductImage();
				pi.setId(rs.getInt("id"));
				Product p = new ProductDAO().get(rs.getInt("pid"));
				pi.setProduct(p);
				pi.setType(rs.getString("type"));
				ls.add(pi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ls;
	}

	public ProductImage getOneImage(int pid) {
		List<ProductImage> ones = list(pid, 0, 1, "single");
		if (ones.isEmpty()) {
			return null;
		}
		return ones.get(0);
	}

	public void fillImage(Product p) {
		p.setFirstProductImage(getOneImage(p.getId()));
		p.setProductSingleImages(list(p.getId(), "single"));
		p.setProductDetailsImages(list(p.getId(), "details"));
	}
}

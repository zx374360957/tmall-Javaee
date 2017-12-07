package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tmall.beans.Category;
import com.tmall.beans.Product;
import com.tmall.util.DBUtil;
import com.tmall.util.DateUtil;

public class ProductDAO {

	public int getTotal(int cid) {
		int total = 0;
		String sql = "SELECT count(*) FROM product WHERE cid = ?";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cid);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	public void add(Product bean) {
		String sql = "INSERT INTO product(id, name, subtitle, orignalPrice, promotePrice, stock, cid, createDate)"
				+ " VALUE(null, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getSubTitle());
			ps.setFloat(3, bean.getOrignalPrice());
			ps.setFloat(4, bean.getPromotePrice());
			ps.setInt(5, bean.getStock());
			ps.setInt(6, bean.getCategory().getId());
			ps.setTimestamp(7, DateUtil.d2t(bean.getCreateDate()));
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				bean.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Product get(int id) {
		Product product = null;
		String sql = "SELECT * FROM product WHERE id = ?";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product = new Product();
				product.setId(id);
				product.setName(rs.getString("name"));
				product.setSubTitle(rs.getString("subtitle"));
				product.setOrignalPrice(rs.getFloat("orignalPrice"));
				product.setPromotePrice(rs.getFloat("PromotePrice"));
				product.setStock(rs.getInt("stock"));
				Category category = new CategoryDAO().get(rs.getInt("cid"));
				product.setCategory(category);
				product.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				product.setReviewCount(new ReviewDAO().getTotal(id));
				// ProductImageDAO productImageDAO = new ProductImageDAO();
				// product.setFirstProductImage(productImageDAO.getOneImage(product.getId()));
				// product.setProductSingleImages(productImageDAO.list(product.getId(),
				// "single"));
				// product.setProductDetailsImages(productImageDAO.list(product.getId(),
				// "details"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public void update(Product bean) {
		String sql = "UPDATE product SET name = ?, subtitle = ?, orignalPrice = ?, "
				+ "promotePrice = ?, stock = ?, cid = ?, createDate = ? WHERE id = ?";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getSubTitle());
			ps.setFloat(3, bean.getOrignalPrice());
			ps.setFloat(4, bean.getPromotePrice());
			ps.setInt(5, bean.getStock());
			ps.setInt(6, bean.getCategory().getId());
			ps.setTimestamp(7, DateUtil.d2t(bean.getCreateDate()));
			ps.setInt(8, bean.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM product WHERE id = ?";
		try (Connection c = DBUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Product> list(int cid) {
		return list(cid, 0, Short.MAX_VALUE);
	}

	public List<Product> list(int cid, int beg, int len) {
		List<Product> ls = new ArrayList<Product>();
		try (Connection c = DBUtil.getConnection()) {
			String sql = "SELECT * FROM product WHERE cid=? LIMIT ?,?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setInt(2, beg);
			ps.setInt(3, len);

			CategoryDAO categoryDAO = new CategoryDAO();
			ProductImageDAO productImageDAO = new ProductImageDAO();
			ReviewDAO reviewDAO = new ReviewDAO();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setSubTitle(rs.getString("subTitle"));
				p.setOrignalPrice(rs.getFloat("orignalPrice"));
				p.setPromotePrice(rs.getFloat("promotePrice"));
				p.setStock(rs.getInt("stock"));
				p.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				Category cg = categoryDAO.get(rs.getInt("cid"));
				p.setCategory(cg);
				p.setFirstProductImage(productImageDAO.getOneImage(p.getId()));
				p.setProductSingleImages(productImageDAO.list(p.getId(), "single"));
				p.setProductDetailsImages(productImageDAO.list(p.getId(), "details"));
				p.setReviewCount(reviewDAO.getTotal(p.getId()));

				ls.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ls;
	}

	public List<Product> search(String name) {
		return search(name, 0, Short.MAX_VALUE);
	}

	public List<Product> search(String name, int beg, int len) {
		List<Product> ls = new ArrayList<Product>();
		try (Connection c = DBUtil.getConnection()) {
			String sql = "SELECT * FROM product WHERE name like ? LIMIT ?,?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			ps.setInt(2, beg);
			ps.setInt(3, len);

			CategoryDAO categoryDAO = new CategoryDAO();
			ProductImageDAO productImageDAO = new ProductImageDAO();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setSubTitle(rs.getString("subTitle"));
				p.setOrignalPrice(rs.getFloat("orignalPrice"));
				p.setPromotePrice(rs.getFloat("promotePrice"));
				p.setStock(rs.getInt("stock"));
				p.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				Category cg = categoryDAO.get(rs.getInt("cid"));
				p.setCategory(cg);
				p.setFirstProductImage(productImageDAO.getOneImage(p.getId()));
				p.setProductSingleImages(productImageDAO.list(p.getId(), "single"));
				p.setProductDetailsImages(productImageDAO.list(p.getId(), "details"));

				ls.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ls;
	}

	public void fill(List<Category> cs) {
		Iterator<Category> it = cs.iterator();
		while (it.hasNext()) {
			Category c = it.next();
			c.setProducts(list(c.getId()));
		}
	}

	public void fillByRow(List<Category> cs) {
		Iterator<Category> it = cs.iterator();
		while (it.hasNext()) {
			Category c = it.next();

			int beg = 0;
			List<List<Product>> pss = new ArrayList<List<Product>>();
			List<Product> ls = list(c.getId(), beg, 6);
			while (ls.size() > 0) {
				pss.add(ls);
				beg += 6;
				ls = list(c.getId(), beg, 6);
				pss.add(ls);
			}
			c.setProductsByRow(pss);
		}
	}
}

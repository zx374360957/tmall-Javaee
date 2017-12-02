package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tmall.beans.Product;
import com.tmall.beans.Property;
import com.tmall.beans.PropertyValue;
import com.tmall.util.DBUtil;

public class PropertyValueDAO {

	public int getTotal(int pid) {
		int total = 0;
		String sql = "SELECT count(*) FROM propertvalue WHERE pid=?";
		try (Connection c = DBUtil.getConnection()) {
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

	public void add(PropertyValue bean) {
		String sql = "INSERT INTO propertyvalue(id, pid, ptid, value)" + "VALUE (null, ?, ?, ?)";
		try (Connection c = DBUtil.getConnection()) {
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
		try (Connection c = DBUtil.getConnection()) {
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
		try (Connection c = DBUtil.getConnection()) {
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
		try (Connection c = DBUtil.getConnection()) {

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

	public List<PropertyValue> list(int pid) {
		return list(pid, 0, Short.MAX_VALUE);
	}

	public List<PropertyValue> list(int pid, int beg, int len) {
		List<PropertyValue> ls = new ArrayList<PropertyValue>();
		String sql = "SELECT * FROM propertyvalue WHERE pid = ?";
		try (Connection conn = DBUtil.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PropertyValue ppv = new PropertyValue();
				ppv.setId(rs.getInt("id"));
				Product p = new ProductDAO().get(pid);
				ppv.setProduct(p);
				Property pp = new PropertyDAO().get(rs.getInt("ptid"));
				ppv.setProperty(pp);
				ppv.setValue(rs.getString("value"));
				ls.add(ppv);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ls;
	}

	public int isExist(int pid, int ptid) {
		String sql = "SELECT * FROM propertyValue WHERE pid=? AND ptid=?";
		int id = -1;
		try (Connection conn = DBUtil.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ps.setInt(2, ptid);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public void init(int pid, List<Property> pps) {
		Iterator<Property> it = pps.iterator();
		Product p = new ProductDAO().get(pid);
		while (it.hasNext()) {
			Property pp = it.next();
			if (isExist(pid, pp.getId()) == -1) {
				PropertyValue ppv = new PropertyValue();
				ppv.setProduct(p);
				ppv.setProperty(pp);
				ppv.setValue("");
				add(ppv);
			}
		}
	}

}

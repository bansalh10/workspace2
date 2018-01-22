package com.nagarro.RetailManagement.services;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nagarro.RetailManagement.data.Product;

public class ProductDao {
	private static ProductDao operation = null;

	public static ProductDao getOperation() {
		if (operation == null) {
			operation = new ProductDao();
		}
		return operation;
	}

	public List<Product> getProducts() {
		List<Product> products = null;
		Product product;
		String query = "select * from products";
		try (Connection conn = Util.getUtil().getConnection();
				PreparedStatement psmt = conn.prepareStatement(query);
				ResultSet rs = psmt.executeQuery()) {
			if (rs != null) {
				products = new ArrayList<>();
			}
			while (rs.next()) {
				product = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("type"),
						rs.getInt("cost"), rs.getDouble("discount"),rs.getBoolean("offer"),rs.getBoolean("outofstock"));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	public boolean addEmployee(Product product) {
		boolean added = true;
		String query = "insert into products(name,type,cost,discount,offer,outofstock) values(?,?,?,?,?,?)";
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
			psmt.setString(1, product.getName());
			psmt.setString(2, product.getType());
			psmt.setInt(3, product.getCost());
			psmt.setDouble(4, product.getDiscount());
			psmt.setBoolean(5, product.isOffer());
			psmt.setBoolean(6, product.isOutofstock());
			int status = psmt.executeUpdate();
			if (status == 0) {
				added = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			added = false;
		}
		return added;
	}
	public Product getProductById(int id) {
		String query = "select * from products where id=?";
		Product product = null;
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();
			if (rs.next() ) {
				
				product = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("type"),
						rs.getInt("cost"), rs.getDouble("discount"),rs.getBoolean("offer"),rs.getBoolean("outofstock"));
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	public List<Product> getProductsByName(String name) {
		String query = "select * from products where name like ?";
		Product product = null;
		List<Product> products = null;
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
			name="%"+name+"%";
			psmt.setString(1, name);
			ResultSet rs = psmt.executeQuery();
			if (rs != null) {
				products = new ArrayList<>();
			}
			while (rs.next()) {
				product = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("type"),
						rs.getInt("cost"), rs.getDouble("discount"),rs.getBoolean("offer"),rs.getBoolean("outofstock"));
				products.add(product);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	public List<Product> getProductsByOffer() {
		String query = "select * from products where offer=1";
		Product product = null;
		List<Product> products = null;
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
			ResultSet rs = psmt.executeQuery();
			if (rs != null) {
				products = new ArrayList<>();
			}
			while (rs.next()) {
				product = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("type"),
						rs.getInt("cost"), rs.getDouble("discount"),rs.getBoolean("offer"),rs.getBoolean("outofstock"));
				products.add(product);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
public boolean saveImg(InputStream file){
	boolean added = true;
	String query = "insert into images(image) values(?)";
	try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
		psmt.setBlob(1, file);
		
		int status = psmt.executeUpdate();
		if (status == 0) {
			added = false;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		added = false;
	}
	return added;
}
public Blob getImg(int id){
	String query = "select image from images where id=?";
	Blob img = null;
	try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
		psmt.setInt(1, id);
		ResultSet rs = psmt.executeQuery();
		if (rs.next() ) {
			
			img = rs.getBlob("image");
			}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return img;
	
}
	public boolean updateProduct(Product product) {
		boolean updated = true;
		String query = "update products set name=?,type=?,cost=?,discount=?,offer=?,outofstock=? where id=?";
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
			psmt.setString(1, product.getName());
			psmt.setString(2, product.getType());
			psmt.setInt(3, product.getCost());
			psmt.setDouble(4, product.getDiscount());
			psmt.setBoolean(5, product.isOffer());
			psmt.setBoolean(6, product.isOutofstock());
			psmt.setInt(7, product.getId());
			int status = psmt.executeUpdate();
			if (status == 0) {
				updated = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			updated = false;
		}
		return updated;
	}
	public boolean deleteProduct(int id){
		boolean deleted=true;
		String query="delete from products where id=?";
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
		
			psmt.setInt(1, id);
			
			int status = psmt.executeUpdate();
			if (status == 0) {
				deleted = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			deleted = false;
		}
		return deleted;
	}
}

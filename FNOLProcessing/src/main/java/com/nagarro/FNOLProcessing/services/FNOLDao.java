package com.nagarro.FNOLProcessing.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nagarro.FNOLProcessing.data.FNOLdata;

public class FNOLDao {
	private static FNOLDao fnolDao = null;

	public static FNOLDao getFnolDao() {
		if (fnolDao == null) {
			fnolDao = new FNOLDao();
		}
		return fnolDao;
	}

	public boolean addFnolData(FNOLdata data) {
		boolean added = true;
		String query = "insert into fnoldata(fnolId,name,email,date,time,ssn,happinessIndex,creditScoreVariation,isFraud) values(?,?,?,?,?,?,?,?,?)";
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
			psmt.setString(1, data.getFnolId());
			psmt.setString(2, data.getName());
			psmt.setString(3, data.getEmail());
			psmt.setString(4, data.getDate());
			psmt.setString(5, data.getTime());
			psmt.setString(6, data.getSsn());
			psmt.setString(7, data.getHappinessIndex());
			psmt.setString(8, data.getCreditScoreVariation());
			psmt.setInt(9, data.getIsFraud());
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

	public boolean saveFnolId(FNOLdata data) {
		boolean saved = true;
		String query = "update fnoldata set fnolId=? where id=?";
		String idQuery = "select * from fnoldata where fnolId is null ";
		try (Connection conn = Util.getUtil().getConnection();
				PreparedStatement psmt = conn.prepareStatement(query);
				PreparedStatement psmtforId = conn.prepareStatement(idQuery);) {
			ResultSet rs = psmtforId.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			String fnolId = "FNOL_" + rs.getString("date") + "_" + id;
			psmt.setString(1, fnolId);
			psmt.setInt(2, id);
			int status = psmt.executeUpdate();
			if (status == 0) {
				saved = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			saved = false;
		}
		return saved;
	}

	public int getMaxId() {
		int id = 1;
		System.out.println("Insidefun");
		String query = "select * from fnoldata where id=(select max(id) from fnoldata)";
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query);) {
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				id = Integer.parseInt(rs.getString("fnolId").split("_")[2]) + 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;

	}

	public List<FNOLdata> getFnolsByDate(final Date date) {
		String query = null;
		String sDate = null;
		if (date != null) {
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			sDate = df.format(date);
		}
		query = "select * from fnoldata  where date='" + sDate + "'";

		List<FNOLdata> fnols = new ArrayList<>() ;
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query);) {
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				fnols.add(new FNOLdata(rs.getInt("id"), rs.getString("fnolId"), rs.getString("name"),
						rs.getString("email"), rs.getString("date"), rs.getString("time"), rs.getString("ssn"),
						rs.getString("happinessIndex"), rs.getString("creditScoreVariation"), rs.getInt("isFraud")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fnols;

	}
	
	public List<FNOLdata> getFnols() {
		String query = null;
		
		
		query = "select * from fnoldata " ;

		List<FNOLdata> fnols = new ArrayList<>() ;
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query);) {
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				fnols.add(new FNOLdata(rs.getInt("id"), rs.getString("fnolId"), rs.getString("name"),
						rs.getString("email"), rs.getString("date"), rs.getString("time"), rs.getString("ssn"),
						rs.getString("happinessIndex"), rs.getString("creditScoreVariation"), rs.getInt("isFraud")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fnols;

	}
	public boolean updateFnol(FNOLdata fnol){
		boolean update = true;
		String query = "update fnoldata set isFraud=? where id=?";
		try (Connection conn = Util.getUtil().getConnection();
				PreparedStatement psmt = conn.prepareStatement(query)) {
			psmt.setInt(1, fnol.getIsFraud());
			psmt.setInt(2, fnol.getId());
			int status = psmt.executeUpdate();
			if (status == 0) {
				update = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			update = false;
		}
		return update;
		
	}
	
	
}

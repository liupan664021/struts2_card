package com.db_util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class DbToExcel {
	public static void excelToDb(String excelPath, String table, String[] fieldList, int columnCount) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Workbook workbook = null;
		Sheet sheet = null;
		String sql = "insert into " + table + "(";
		for(int i=0; i<columnCount-1; i++) {
			sql += fieldList[i] + ",";
		}
		sql+=fieldList[columnCount-1] + ",flag)";
		sql +=" values(";
		for(int i=0; i<columnCount; ++i) {
			sql += "?,";
		}
		sql+="0);";
		try {
			connection = JdbcPoolUtils.getConnection();
			pstmt = connection.prepareStatement(sql);
			workbook = Workbook.getWorkbook(new File(excelPath));
			sheet = workbook.getSheet(0);
			int rows = sheet.getRows();
			for(int i=0 ;i<rows;i++) {
				for(int j=0; j<columnCount; j++) {					
					pstmt.setString(j+1, sheet.getRow(i)[j].getContents());
				}
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(workbook!=null)
				workbook.close();
			JdbcPoolUtils.closeConnection(connection, pstmt, null);
		}
	}
	
	public static void dBToExcel(String table, String[] fieldList, String[] titles, String condition,
			String order, String file) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WritableWorkbook workbook = null;
		int filedListLength = fieldList.length;
		String flist = "";
		for(int i=0; i<filedListLength-1; i++) {
			flist += fieldList[i] + ",";
		}
		flist+=fieldList[filedListLength-1];
		String sql = "select " + flist + " from " + table + " where 1=1";
		if(condition!=null && !condition.equals("")) {
			sql+=" and " + condition;
		}
		if(order!=null && !order.equals("")) {
			sql+=" order by " + order;
		}
		
		try {
			connection = JdbcPoolUtils.getConnection();
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			workbook = Workbook.createWorkbook(new File(file));
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			for(int i=0 ;i<titles.length; i++) {
				sheet.addCell(new Label(i, 0, titles[i]));
			}
			int cnt = 1;
			while(rs.next()) {
				for(int i=0; i<filedListLength; i++) {
					sheet.addCell(new Label(i, cnt, rs.getString(i+1)));
				}
				cnt++;
			}
			workbook.write();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}finally {
			if(workbook!=null) {
				try {
					workbook.close();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			JdbcPoolUtils.closeConnection(connection, pstmt, rs);
		}
	}
}

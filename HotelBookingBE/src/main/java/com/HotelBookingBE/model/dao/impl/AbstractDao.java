package com.HotelBookingBE.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.HotelBookingBE.model.dao.genericDao;
import com.HotelBookingBE.mapper.IRowMapper;

public class AbstractDao<T> implements genericDao<T> {

	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hotelbookingdb";
			String user ="root";
			String password = "Quang123";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		} 
//		catch (SQLException e) {
//			return null;		// cach viet cu~
//		}
		
	}
	@Override
	public  List<T> query(String sql, IRowMapper<T> rowMapper, Object... Parameters) {
		List<T> results = new ArrayList<>();
		Connection connect = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try
		{
			connect = getConnection();
			statement = connect.prepareStatement(sql);
			setParameter(statement, Parameters);
			rs = statement.executeQuery();
			while(rs.next())
			{
				results.add(rowMapper.MappingRow(rs));
			}
			return results;
		} catch (SQLException e)
		{
			return null;
		} finally {
			try {
				connect.close();
				statement.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	public void setParameter(PreparedStatement statement, Object... Parameters)
	{
		for(int i=0;i<Parameters.length;i++)
		{
			Object param = Parameters[i];
			int index = i+1;			
			try {
				if(param instanceof String)
				{
					statement.setString(index, (String)param);
				}else if (param instanceof Long)
				{
					statement.setLong(index, (Long)param);
				} else if(param instanceof Boolean)
				{
					statement.setBoolean(index, (Boolean)param);
				}else if(param instanceof Timestamp)
				{
					statement.setTimestamp(index,(Timestamp)param);
				}				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void insert(String sql, Object... Parameters) {
		Connection connect = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connect = getConnection();
			connect.setAutoCommit(false);
			statement= connect.prepareStatement(sql,statement.RETURN_GENERATED_KEYS);
			setParameter(statement, Parameters);
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			rs.next();
			connect.commit();
		} catch (SQLException e) {
			System.err.print(e.toString());
			if(connect!= null)
			{
				try {
					connect.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connect != null)
				connect.close();
				if (statement != null)
					statement.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
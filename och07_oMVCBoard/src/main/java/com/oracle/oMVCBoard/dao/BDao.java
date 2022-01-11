package com.oracle.oMVCBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.oracle.oMVCBoard.dto.BDto;

public class BDao {

	DataSource dataSource;
	
	public BDao() {
		
		try {
			//JNDI
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("생성자 dataSource-->" + e.getMessage());
			//e.printStackTrace();
		}
		
	}
	
	public ArrayList<BDto> list() {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			//mvc_board list 조회
			String query = "SELECT bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent FROM mvc_board order by bGroup desc, bStep asc";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
//				밑에 처럼 주저리주저리 쓸 필요 없이 위와 같이 한줄로 표현 가능.
//				BDto dto = new BDto();
//				dto.setbName(bName);
//				dto.setbContent(bContent); 등등
				
				dtos.add(dto);
			}
			
		}catch (SQLException e) {
			System.out.println("list dataSource SQLException-->" + e.getMessage());
		}
		
		return dtos;
	}

	public BDto contentView(String strId) {
		
		//Content누를때마다 히트수 증가 시키는부분
		upHit(strId);
		
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			//mvc_board bId를 가지고 BDto dto를 담아서 return
			
			String query = "SELECT * FROM mvc_board where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strId));
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {	
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);

			}
				
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
		return dto;
	}

	private void upHit(String strId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
            String query = "UPDATE mvc_board SET bHit = bHit + 1 WHERE bId = ?";
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, strId);
            int rn = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		
	}
	
}

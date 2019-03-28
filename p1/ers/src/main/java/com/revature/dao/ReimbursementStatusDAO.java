package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.data.ReimbursementStatus;
import com.revature.util.ConnectionFactory;

public class ReimbursementStatusDAO {

	public List<ReimbursementStatus> getStatusList() {
		List<ReimbursementStatus> reimbursementStatusList =
				new ArrayList<ReimbursementStatus>();
		
		try(Connection connection = 
				ConnectionFactory.getInstance().getConnection()){
			String query = "select REIMB_STATUS_ID, " + "REIMB_STATUS " +
					"from ERS_REIMBURSEMENT_STATUS";
	
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				short reimbursementStatusId = resultSet.getShort("REIMB_STATUS_ID");
				String reimbursementStatus = resultSet.getString("REIMB_STATUS");
				
				ReimbursementStatus reimbursementStatusObject = 
						new ReimbursementStatus(reimbursementStatusId, reimbursementStatus);
				reimbursementStatusList.add(reimbursementStatusObject);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursementStatusList;
	}
}

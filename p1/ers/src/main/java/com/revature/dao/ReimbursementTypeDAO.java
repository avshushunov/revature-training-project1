package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.data.ReimbursementType;
import com.revature.util.ConnectionFactory;

public class ReimbursementTypeDAO {

	public List<ReimbursementType> getStatusList() {
		List<ReimbursementType> reimbursementTypeList =
				new ArrayList<ReimbursementType>();
		
		try(Connection connection = 
				ConnectionFactory.getInstance().getConnection()){
			String query = "select REIMB_TYPE_ID, " + "REIMB_TYPE " +
					"from ERS_REIMBURSEMENT_TYPE";
	
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				short reimbursementTypeId = resultSet.getShort("REIMB_STATUS_ID");
				String reimbursementType = resultSet.getString("REIMB_STATUS");
				
				ReimbursementType reimbursementTypeObject = 
						new ReimbursementType(reimbursementTypeId, reimbursementType);
				reimbursementTypeList.add(reimbursementTypeObject);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursementTypeList;
	}
}

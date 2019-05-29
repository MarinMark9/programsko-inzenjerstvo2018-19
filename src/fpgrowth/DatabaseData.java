package fpgrowth;

import java.sql.*;

public class DatabaseData {
	
	public static ResultSet getDatabaseData(String querry) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proging?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "student", "student");
			Statement stm = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stm.setFetchSize(100);
			ResultSet result = stm.executeQuery(querry);
			return result;
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		return null;
	} 
}

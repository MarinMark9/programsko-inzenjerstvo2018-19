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
	
	public static boolean checkUser(String ime, String password) {
		String query = "select * from users where username='" + ime + "' AND password='" + password + "'";
		try {
			ResultSet res = DatabaseData.getDatabaseData(query);
			if(res.next()) {
				return true;
			}
		} catch(Exception e){
			System.out.println("Pogresna kombinacija!" + "\n" + e);
		}
		return false;
	}
}

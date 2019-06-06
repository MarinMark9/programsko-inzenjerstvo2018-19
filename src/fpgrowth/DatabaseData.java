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
	
	public static boolean checkUser(String username, String password) {
		DatabaseData.addUser("2", "1");
		String encryptedPassword = Encryption.encryptWithMD5(password);
		
		String query = "select * from users where username='" + username + "' AND password='" + encryptedPassword + "'";
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
	
	public static boolean addUser(String username, String password) {
		String encryptedPassword = Encryption.encryptWithMD5(password);
		
		String query = "insert into users (username, password) values ('" + username + "', '" + encryptedPassword + "')";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proging?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "student", "student");
			Statement stm = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stm.executeUpdate(query);
			System.out.println("Dodan korisnik" + "\n" + "Username: " + username + "\n" + "Password: " + password);
		} catch(Exception e){
			System.out.println("Pogresna kombinacija!" + "\n" + e);
		}
		return false;
	}
}

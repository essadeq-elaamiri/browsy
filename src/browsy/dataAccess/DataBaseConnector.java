package browsy.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/";//jdbc:mysql://127.0.0.1:3306/test", "root", "password"
	private final static String DB_NAME = "browsy";
	private final static String DB_USERNAME = "root";
	private final static String DB_PASSWORD = "";

	private static Connection connection;

	static {
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			//com.mysql.jdbc.Driver"
			connection = DriverManager.getConnection(URL+DB_NAME, DB_USERNAME, DB_PASSWORD);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnexion() {
		return connection;
	}

}

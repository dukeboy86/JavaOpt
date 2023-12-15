package gmbh.conteco.dbintro;

import org.h2.command.dml.Call;

import java.sql.*;

public class H2Example {
	public static void main(String[] args) {

	try {
    // Driver wählen
		Class.forName("org.h2.Driver");
    // Einstellen, dass andere Anwendungen auch Zugriff haben können. 
		String jdbcURL = "jdbc:h2:./tempdb;AUTO_SERVER=TRUE";
		String sqlQuery = "INSERT INTO Users VALUES (12, 'Peter Lustig', 'pl@nature.com');";

    // Verbindungsaufbau über User/Password
		try (Connection connection = DriverManager.getConnection(jdbcURL, "sa", "")) {
			System.out.println("Verbindung aufgebaut");

      // normales Statement
			Statement statement = connection.createStatement();

			statement.executeUpdate(sqlQuery);

      // prepared, etwas schneller vorberechnet
			String statementTemplate = "SELECT * FROM Users WHERE email LIKE ?";
			PreparedStatement preparedSqlStatement = connection.prepareStatement(statementTemplate);
			preparedSqlStatement.setString(1,"%@b.com");
			ResultSet resultSet = preparedSqlStatement.executeQuery();
			final int EMAIL_ARG = 3;
			while (resultSet.next()) {
				System.out.println(resultSet.getString(EMAIL_ARG));
				System.out.println(resultSet.getString("email"));
			}
		}
	} catch (ClassNotFoundException ex) {
		System.err.println("Fehler: H2 JDBC Treiber nicht gefunden.");
		return;
	} catch (Exception e) {
		System.out.println("Nicht behandelte Exception");
		e.printStackTrace();
		// handle other exceptions
	}
	}
}

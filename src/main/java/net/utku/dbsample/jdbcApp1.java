package net.utku.dbsample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class jdbcApp1 {

	private static final String DB_DERBY_URL_SERVER = "jdbc:derby://localhost:1527/mydb1;create=true";
	private static final String DB_DERBY_URL_MEMORY = "jdbc:derby:memory:mydb1;create=true";
	private static final String DB_DERBY_URL_FILE = "jdbc:derby:./.mydb1/;create=true";
	private static final String DB_MYSQL_URL_SERVER = "jdbc:mysql://localhost:3306/mydb3?createDatabaseIfNotExist=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String DB_MSSQL_URL_SERVER = "jdbc:sqlserver://localhost:1434;instanceName=ABC;databaseName=mydb1;integratedSecurity=true;";
	
	private static final String CURRENT_DB_URL = DB_MSSQL_URL_SERVER ;


	public static void main(String[] args) throws SQLException {

		System.out.println("Uygulama aciliyor...");

		Connection dbConn = DriverManager.getConnection(CURRENT_DB_URL);

		System.out.println("Uygulama baglandi...");

		Statement stmt = dbConn.createStatement();
        
		//createTableIfnotExists(stmt);
		addRecord(stmt);
		readRecord(stmt);
		
		
	}

	private static void addRecord(Statement stmt) throws SQLException {
		stmt.executeUpdate("INSERT INTO customer values (3,'Murat')");
		System.out.println("Bilgiler Yazildi");
	}

	private static void createTableIfnotExists(Statement stmt) throws SQLException {
		stmt.executeUpdate("CREATE TABLE Customer (id int primary key,name varchar(30))");
		System.out.println("Tablo oluþturuldu");
	}
	
	private static void readRecord(Statement stmt) throws SQLException {
		
		ResultSet resultset=stmt.executeQuery("SELECT *FROM customer");
		while(resultset.next()) {
			int id =resultset.getInt(1);
			String name = resultset.getString(2);
			System.out.printf("id: %d,name: %s \n",id,name);
			
			
		}
		System.out.println("bilgiler okundu");
	}

}

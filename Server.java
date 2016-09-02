package schoolNews;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Server {
	public Server(){
		Connection conn = new ConnectToMysql().connection;
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("CREATE DATABASE SCHOOL_NEWS;");
			stmt.executeUpdate("USE SCHOOL_NEWS;");
			stmt.executeUpdate("create table USERS"
							+ "(USER_ID int primary key AUTO_INCREMENT,"
							+ "USER_NAME varchar(9),"
							+ "ZHANGHAO varchar(20) not null primary key"
							+ "NIMA varchar(20) not null"
							+ ");");
			stmt.executeUpdate("create table FOLLOW"
							+ "(FOLLOWEE_ID INT PRIMARY KEY ,"
							+ "FOLLOWER_ID INT PRIMARY KEY,"//正确性存疑，查阅数据库知识
							+ "FOREIGN KEY (FOLLOWEE_ID) REFERENCES USERS (USER_ID)"
							+ "ON DELETE CASCADE,"
							+ "FOREIGN KEY (FOLLOWER_ID) REFERENCES USERS (USER_ID)"
							+ "ON DELETE CASCADE "
							+ ");");
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}

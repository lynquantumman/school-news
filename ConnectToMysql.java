package schoolNews;
/**
 * @author Cradle Lee
 */
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
//西面这个地址存储的是jdbc所需要导入的jar文件
//C:\Program Files (x86)\MySQL\Connector.J 5.1
public class ConnectToMysql {
	public Connection connection;
	public ConnectToMysql(){
		try{
			Class.forName("com.mysql.jdbc.Driver");//此句是用来加载驱动的
			String dbURL = "jdbc:mysql://localhost:3306";
			connection = DriverManager.getConnection(dbURL, "root", "chineteklyn123");
			System.out.println("Successfully linked to the mysql server.");
			
			//此句可能会抛出SQLException
//			Statement stmt = connection.createStatement();
//			//sqlString当中为SQL语句.executeUpdate执行该语句。
//			//String sqlUpdateString;
//			//stmt.executeUpdate(sqlString);
//			
//			//
//			String sqlQueryString = "SHOW DATABASES;";
//			ResultSet rs = stmt.executeQuery(sqlQueryString);
//			while(rs.next()){
//				System.out.println(rs.getString("database"));
//			}
//			stmt.close();
		}
		catch(ClassNotFoundException e1){
			System.out.println("class not found");
		}
		catch(SQLException e2){
			System.out.println("SQL exception");
		}
	}
}

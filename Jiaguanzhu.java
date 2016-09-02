package schoolNews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jiaguanzhu extends Guanzhujiemian{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5523952680073497036L;
	Statement stmt;
	int followerID;
	public Jiaguanzhu(Statement stmt, int followerID){
		super();
		this.stmt = stmt;
		this.followerID = followerID;
	}
	
	@Override
	protected void addEvent(){
		queding.addActionListener(
			new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent quedingE){
					try {
						ResultSet rs = stmt.executeQuery(""
								+ "SELECT USER_ID FROM USERS WHERE"
								+ "NICHENG="
								+ "'"
								+ nicheng.getText()
								+ "'");
						
						stmt.executeUpdate("INSERT INTO FOLLOW VAULES ("
								+""+followerID
								+""+rs.getInt("USER_ID")
								+ ")");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		);
	}
}

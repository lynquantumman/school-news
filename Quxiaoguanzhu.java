package schoolNews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Quxiaoguanzhu extends Guanzhujiemian {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2963859727763345303L;
	Statement stmt;
	int followerID;
	public Quxiaoguanzhu(Statement stmt, int followerID){
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
						
						stmt.executeUpdate("DELETE * FROM FOLLOW WHERE "
								+"FOLLOWER_ID"+followerID+","
								+"FOLLOWEDD_ID="+rs.getInt("USER_ID")
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

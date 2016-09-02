package schoolNews;
/**
 * @author Cradle Lee
 * @describe This is the sign up and sign in GUI
 * when you press zhuce button, the sign up function will be finished.
 * when you press denglu button, the real playful GUI will be shown.
 */



import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class SignUpGUI extends JFrame{

	private static final long serialVersionUID = -2807297821795955836L;
	JTextField zhanghao;
	JTextField mima;
	JTextField nicheng;
	JButton zhuce;
	JButton denglu;
	
	
	private Connection conn;
	private Statement stmt;
	public SignUpGUI(){
		nicheng =new JTextField("昵称",10);
		zhanghao = new JTextField("帐号",10);
		mima = new JTextField("密码",10);
		zhuce = new JButton("注册");
		denglu = new JButton("登录");
		setLayout(new FlowLayout());
		setBounds(30,20,600,100);
		add(nicheng);
		add(zhanghao);
		add(mima);
		add(zhuce);
		add(denglu);
		
		setVisible(true);
		conn = new ConnectToMysql().connection;
		try{//下面的语句可能抛出异常，所以要进行处理
			stmt = conn.createStatement();
			stmt.executeUpdate("USE SCHOOL_NEWS");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		addEvent();
	}
	private void addEvent(){
		zhuce.addActionListener(
			new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent zhuceE){
					//向用户信息表当中添加新的用户信息
					//建立与每个人对应的微博表
					
					try {
						stmt.executeUpdate("INSERT INTO USERS VALUES (NULL,"
											+ "'"+ nicheng.getText()+"'"
											+ "'"+ zhanghao.getText()+"'"
											+ "'"+ mima.getText()+"'"
											);
						stmt.executeUpdate("CREATE TABLE WEIBO("
								+ "USER_ID INT"
								+ "WEIBO_ID INT"
								+ "NEIRONG VARCHAR(140)"
								+ ");");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}	
		);
		
		denglu.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent dengluE){
						//跳转到GUI页面
					
						
						try {
							ResultSet rs;
							rs = stmt.executeQuery("SELECT (USER_ID, MIMA)"
									+ "FROM USERS WHERE ZHANGHAO="
									+ "'"+zhanghao.getText()+"';"
									);
							if(rs.getString("MIMA")==mima.getText()){
								new GUI(rs.getInt("USER_ID"), stmt);
								//这里是否需要关闭stmt，下面的语句是测试语句
								System.out.println("哎呀，需要关闭stmt了");
							}
							else
								stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				}	
			);
		
	}
}

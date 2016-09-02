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
		nicheng =new JTextField("�ǳ�",10);
		zhanghao = new JTextField("�ʺ�",10);
		mima = new JTextField("����",10);
		zhuce = new JButton("ע��");
		denglu = new JButton("��¼");
		setLayout(new FlowLayout());
		setBounds(30,20,600,100);
		add(nicheng);
		add(zhanghao);
		add(mima);
		add(zhuce);
		add(denglu);
		
		setVisible(true);
		conn = new ConnectToMysql().connection;
		try{//������������׳��쳣������Ҫ���д���
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
					//���û���Ϣ��������µ��û���Ϣ
					//������ÿ���˶�Ӧ��΢����
					
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
						//��ת��GUIҳ��
					
						
						try {
							ResultSet rs;
							rs = stmt.executeQuery("SELECT (USER_ID, MIMA)"
									+ "FROM USERS WHERE ZHANGHAO="
									+ "'"+zhanghao.getText()+"';"
									);
							if(rs.getString("MIMA")==mima.getText()){
								new GUI(rs.getInt("USER_ID"), stmt);
								//�����Ƿ���Ҫ�ر�stmt�����������ǲ������
								System.out.println("��ѽ����Ҫ�ر�stmt��");
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

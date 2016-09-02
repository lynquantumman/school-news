package schoolNews;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author Cradle Lee
 *
 */
public class GUI extends JFrame{
	 
/**�����л���Ϊ�˺����޸���GUI���ܹ�����ǰ��GUI�����Ķ���
	 * 
	 */
	private static final long serialVersionUID = 2380659271955394856L;
//����serializable��ʲô��˼ѽ
	JButton shuaxin;
	JButton jiaguanzhu;
	JButton quxiaoguanzhu;
	JButton wodexinxi;
	JTextArea neirong;
	int USER_ID;
	Statement stmt;
	public GUI(int USER_ID, Statement stmt){
		this.USER_ID = USER_ID;
		this.stmt = stmt;
		//===����Ļ������
		JButton faweibo = new JButton("��΢��");
		JButton shuaxin = new JButton("ˢ��");
		JPanel faheshua = new JPanel();
		faheshua.add(faweibo);
		faheshua.add(shuaxin);
		JButton jiaguanzhu = new JButton("��ע����");
		JButton quxiaoguanzhu = new JButton("ȡ����ע");
		JButton wodexinxi = new JButton("�ҵ���Ϣ");
		JTextArea neirong = new JTextArea("����ʲô�µ��뷨�أ��װ���");
		setLayout(new BorderLayout());
		
		add(faheshua,BorderLayout.NORTH);
		add(jiaguanzhu,BorderLayout.EAST);
		add(quxiaoguanzhu,BorderLayout.WEST);
		add(wodexinxi,BorderLayout.SOUTH);
		add(neirong,BorderLayout.CENTER);
		
		setVisible(true);
		//===����¼�����
		addEvent();
	}
	
	private void addEvent(){
		shuaxin.addActionListener(
			new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent shuaxinE){
					try {
						ResultSet rs = stmt.executeQuery("SELECT * FROM FOLLOW"
								+ "WHERE FOLLOWER ="
								+ ""+USER_ID);
						while(rs.next()){
							ResultSet rsWeibo = stmt.executeQuery("SELECT * FROM WEIBO WHERE USER_ID="
									+ ""+rs.getInt("FOLLOWEE"));
							neirong.append(rsWeibo.getString("NEIRONG"));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		);
		
		jiaguanzhu.addActionListener(
			new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent jiaguanzhuE){
					new Jiaguanzhu(stmt,USER_ID);
				}
			}
		);
		
		quxiaoguanzhu.addActionListener(
			new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent quxiaoguanzhuE){
					new Quxiaoguanzhu(stmt,USER_ID);
				}
			}
		);
		
		wodexinxi.addActionListener(
			new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent wodexinxiE){
					try {
						ResultSet rs = stmt.executeQuery("SELECT * FROM USERS"
								+ "WHERE USER_ID ="
								+ ""+USER_ID);
						String ans =
						rs.getInt("USER_ID")+
						rs.getString("USER_NAME")+
						rs.getString("ZHANGHAO")+
						rs.getString("MIMA");
						neirong.setText(ans);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		);
	}
}

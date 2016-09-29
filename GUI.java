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
	JButton faweibo;
	JPanel faheshua;
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
		faweibo = new JButton("��΢��");
		shuaxin = new JButton("ˢ��");
		faheshua = new JPanel();
		faheshua.add(faweibo);
		faheshua.add(shuaxin);
		jiaguanzhu = new JButton("��ע����");
		quxiaoguanzhu = new JButton("ȡ����ע");
		wodexinxi = new JButton("�ҵ���Ϣ");
		neirong = new JTextArea("����ʲô�µ��뷨�أ��װ���");
		setLayout(new BorderLayout());
		
		add(faheshua,BorderLayout.NORTH);
		add(jiaguanzhu,BorderLayout.EAST);
		add(quxiaoguanzhu,BorderLayout.WEST);
		add(wodexinxi,BorderLayout.SOUTH);
		add(neirong,BorderLayout.CENTER);
		setBounds(40,20,700,500);
		setVisible(true);
		//===����¼�����
		addEvent();
	}
	
	void addEvent(){
		shuaxin.addActionListener(
			new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent shuaxinE){
					try {
						ResultSet rs = stmt.executeQuery("SELECT * FROM FOLLOW WHERE FOLLOWER_ID ="
								+ ""+USER_ID);
						while(rs.next()){
							ResultSet rsWeibo = stmt.executeQuery("SELECT * FROM WEIBO WHERE USER_ID="
									+ ""+rs.getInt("FOLLOWEE_ID"));
							rsWeibo.absolute(1);
							neirong.append(rsWeibo.getString("NEIRONG"));
						}
						if("����ʲô�µ��뷨�أ��װ���".equals(neirong.getText())){
							neirong.setText("��ѽ��ûʲô��΢����");
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
						ResultSet rs = stmt.executeQuery("SELECT * FROM USERS WHERE USER_ID ="
					+USER_ID);
						rs.absolute(1);
						String ans =
						"USER_ID: "+rs.getInt("USER_ID")+"\r\n"+
						"�ǳ�: "+rs.getString("USER_NAME")+"\r\n"+
						"�ʺ�: "+rs.getString("ZHANGHAO")+"\r\n"+
						"����: "+rs.getString("MIMA");
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

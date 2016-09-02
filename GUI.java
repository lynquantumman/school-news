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
	 
/**这序列化是为了后期修改类GUI后，能够兼容前期GUI产生的对象
	 * 
	 */
	private static final long serialVersionUID = 2380659271955394856L;
//这里serializable是什么意思呀
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
		//===界面的基本设计
		JButton faweibo = new JButton("发微博");
		JButton shuaxin = new JButton("刷新");
		JPanel faheshua = new JPanel();
		faheshua.add(faweibo);
		faheshua.add(shuaxin);
		JButton jiaguanzhu = new JButton("关注别人");
		JButton quxiaoguanzhu = new JButton("取消关注");
		JButton wodexinxi = new JButton("我的信息");
		JTextArea neirong = new JTextArea("您有什么新的想法呢，亲爱的");
		setLayout(new BorderLayout());
		
		add(faheshua,BorderLayout.NORTH);
		add(jiaguanzhu,BorderLayout.EAST);
		add(quxiaoguanzhu,BorderLayout.WEST);
		add(wodexinxi,BorderLayout.SOUTH);
		add(neirong,BorderLayout.CENTER);
		
		setVisible(true);
		//===添加事件监听
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

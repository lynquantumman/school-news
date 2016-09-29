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
		//===界面的基本设计
		faweibo = new JButton("发微博");
		shuaxin = new JButton("刷新");
		faheshua = new JPanel();
		faheshua.add(faweibo);
		faheshua.add(shuaxin);
		jiaguanzhu = new JButton("关注别人");
		quxiaoguanzhu = new JButton("取消关注");
		wodexinxi = new JButton("我的信息");
		neirong = new JTextArea("您有什么新的想法呢，亲爱的");
		setLayout(new BorderLayout());
		
		add(faheshua,BorderLayout.NORTH);
		add(jiaguanzhu,BorderLayout.EAST);
		add(quxiaoguanzhu,BorderLayout.WEST);
		add(wodexinxi,BorderLayout.SOUTH);
		add(neirong,BorderLayout.CENTER);
		setBounds(40,20,700,500);
		setVisible(true);
		//===添加事件监听
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
						if("您有什么新的想法呢，亲爱的".equals(neirong.getText())){
							neirong.setText("哎呀，没什么新微博呢");
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
						"昵称: "+rs.getString("USER_NAME")+"\r\n"+
						"帐号: "+rs.getString("ZHANGHAO")+"\r\n"+
						"密码: "+rs.getString("MIMA");
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

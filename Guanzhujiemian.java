package schoolNews;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Guanzhujiemian extends JFrame{

	private static final long serialVersionUID = 6797869638475710918L;
	protected JButton queding;
	protected JTextField nicheng;
	public Guanzhujiemian(){
		setLayout(new FlowLayout());
		setBounds(400,300,400,100);
		
		queding = new JButton("ȷ��");
		nicheng = new JTextField("�Ǹ��˵��ǳ���ɶ��");
		
		add(nicheng);
		add(queding);
		
		setVisible(true);
		
		addEvent();
		
	}
	protected void addEvent(){
	}
}


package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class CustomPanel extends JPanel{
	private JPanel Panel1;
	private JPanel Panel2;
	private JPanel Panel3;
	private JPanel Panel4;
	private JPanel Panel5;
	private JPanel Panel6;
	private int height ;
	private int width ;
	
	
	public JPanel getPanel1() {
		return Panel1;
	}



	public JPanel getPanel2() {
		return Panel2;
	}



	public JPanel getPanel3() {
		return Panel3;
	}



	public JPanel getPanel4() {
		return Panel4;
	}



	public JPanel getPanel5() {
		return Panel5;
	}



	public JPanel getPanel6() {
		return Panel6;
	}
	
	


	public CustomPanel (){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		height =screenSize.height-100;
		width=screenSize.width;
		Panel1 = new JPanel();
		Panel2 = new JPanel();
		Panel3 = new JPanel();
		this.setSize(new Dimension(width,height));
		Panel1.setPreferredSize(new Dimension(width,height/6));
		Panel2.setPreferredSize(new Dimension(width,height/6));
		Panel3.setPreferredSize(new Dimension(width,height/6));
		this.add(Panel1);
		this.add(Panel2);
		this.add(Panel3);
		Panel1.setBackground(Color.RED);
		Panel2.setBackground(Color.BLUE);
		Panel3.setBackground(Color.BLACK);
		Panel4 = new JPanel();
		Panel5 = new JPanel();
		Panel6 = new JPanel();
		Panel4.setPreferredSize(new Dimension(width,height/6));
		Panel5.setPreferredSize(new Dimension(width,height/6));
		Panel6.setPreferredSize(new Dimension(width,height/6));
		this.add(Panel4);
		this.add(Panel5);
		this.add(Panel6);
		Panel4.setBackground(Color.BLACK);
		Panel5.setBackground(Color.BLUE);
		Panel6.setBackground(Color.RED);
		Panel2.setVisible(false);
		
		Panel1.setLayout(new GridLayout(1,3));
		Panel6.setLayout(new GridLayout(1,3));
		Panel3.setLayout(new GridLayout(1,7));
		Panel4.setLayout(new GridLayout(1,7));
		Panel2.setLayout(new GridLayout(1,10));
		Panel5.setLayout(new GridLayout(1,10));
		
		
		
	
	}
	
	

}

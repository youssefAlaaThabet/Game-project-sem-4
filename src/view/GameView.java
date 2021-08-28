package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.heroes.Hero;
import exceptions.FullHandException;
public class GameView extends JFrame{
	private CustomPanel upper;
	private Hero hero1;
	private Hero hero2;
	private JButton heropower;
	private int height;
	private int width ;
	
	private EndTurn endturn;
	

	

	public int getHeight() {
		return height;
	}





	public int getWidth() {
		return width;
	}





	public JButton getHeropower() {
		return heropower;
	}



	

	public EndTurn getEndturn() {
		return endturn;
	}



	



	public CustomPanel getUpper() {
		return upper;
	}

	

	public Hero getHero1() {
		return hero1;
	}

	public Hero getHero2() {
		return hero2;
	}
	

	public GameView () throws IOException, CloneNotSupportedException{
		//this.setBounds(0,0,1920,960);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setExtendedState(this.MAXIMIZED_BOTH); 
		//this.setUndecorated(true);
		height=screenSize.height-100;
		width=screenSize.width;
		this.setBounds(0, 0, width, height);
		this.setVisible(true);
		this.setTitle("Hearthstone");
		upper = new CustomPanel();
		
		this.add(upper);
	
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		chooseHero p1 =new chooseHero();
		chooseHero p2 =new chooseHero();
		hero1=p1.getHero();
		hero2=p2.getHero();
		//heropower = new HeroPower();
		
		 endturn = new EndTurn();
		 
		
		
		//upper.getPanel6().add(heropower);
		upper.getPanel6().add(endturn);
		
		
		this.revalidate();
		this.repaint();
		
	}
	
	
	


	

}

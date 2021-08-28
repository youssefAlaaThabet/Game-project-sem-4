package view;

import java.awt.Dimension;

import javax.swing.JButton;

import model.heroes.Hero;

public class HeroName extends JButton {
	public Hero getHero() {
		return hero;
	}
	private Hero hero ;
	public HeroName(Hero x){
		this.hero=x ;
		String s = (hero.getName()+"   "
					+"Current HP :"+hero.getCurrentHP()+"   "
					+"Mana :"+hero.getCurrentManaCrystals()+"/"+hero.getTotalManaCrystals()+"   "
					+"Cards left :"+hero.getDeck().size())+"   "+"Cards in hand :"+hero.getHand().size();
		this.setText(s);
		this.setSize(new Dimension(200,200));
		
	}
	
	

}

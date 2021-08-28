package view;

import javax.swing.JButton;

import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.Spell;

public class CardButton extends JButton{
	private Card card;
	

	
	public Card getCard() {
		return card;
	}



	public CardButton (Card c){
		 
		if (c instanceof Spell){
			card = c;
			String s = "<html>"+c.getName()+"<br>"+"Mana cost :"+c.getManaCost()+"<br>"+c.getRarity()+"<html>";
			this.setText(s);
		}
		if (c instanceof Minion){
			card = c;
			Minion m = (Minion)c;
			String s = "<html>"+m.getName()+"<br>"+"Mana cost :"+m.getManaCost()+"<br>"+m.getRarity()+"<br>"+"attack:"+m.getAttack()+"<br>"+"HealthPoints:"+m.getCurrentHP()+"<br>";
			if (m.isTaunt())
				s+="  Taunt";
			if (m.isDivine())
				s+="  Divine <br>";
			if(!(m.isSleeping()))
				s+="  Charge";
			this.setText(s);
		}
	}

}

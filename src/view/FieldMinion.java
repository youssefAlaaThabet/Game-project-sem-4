package view;

import javax.swing.JButton;

import model.cards.minions.Minion;

public class FieldMinion extends JButton{
	private Minion minion;
	public Minion getMinion() {
		return minion;
	}
	public FieldMinion(Minion m){
		this.minion = m ;
		String s = "<html>"+m.getName()+"<br>"+"Mana cost :"+m.getManaCost()+"<br>"+m.getRarity()+"<br>"+"attack:"+m.getAttack()+"<br>"+"HealthPoints:"+m.getCurrentHP();
		if (m.isTaunt())
			s+="<br>Taunt";
		if (m.isDivine())
			s+="<br>Divine";
		if(!(m.isSleeping()))
			s+="<br>Charge";
		s+="<br>   Attacked this turn:"+(m.isAttacked());
		s+="<br> Sleeping :"+(m.isSleeping())+"<html>";
		this.setText(s);
	}
}

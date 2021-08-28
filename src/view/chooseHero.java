package view;

import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class chooseHero extends JCheckBox {
	private Hero hero ;
	public chooseHero() throws IOException, CloneNotSupportedException{
	String[] options ={"Paladin","Mage","Priest","Warlock","Hunter"};
	int x = JOptionPane.showOptionDialog(null, "Choose your hero",
            "Click a button",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
	switch(x) {
	case 0 : hero =new Paladin(); break;
	case 1 : hero =new Mage() ; break ;
	case 2 : hero = new Priest();break ;
	case 3 : hero=new Warlock();break;
	case 4 : hero =new Hunter();break ;
	default : hero =new Mage() ;
	}
	
	}
	public Hero getHero() {
		return hero;
	}
	
	
}

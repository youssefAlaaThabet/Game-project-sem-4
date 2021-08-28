package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;

public class Controller implements GameListener ,ActionListener{
	private Game model;
	private GameView view ;
	private Hero current;
	private Hero opponent;
	private ActionEvent a;
	private ActionEvent j;
	private ActionEvent v;
	private JButton end;
	private int uuu=0;
	private HeroPower heropower ;
	private ActionEvent r ;
	private ActionEvent balabizo ;
	public GameView getView() {
		return view;
	}
	public Game getModel() {
		return model;
	}
	public Controller() throws IOException, CloneNotSupportedException, FullHandException{
		view = new GameView();
		model = new Game(view.getHero1(), view.getHero2());
		current = model.getCurrentHero();
        opponent=model.getOpponent();
        HeroName first =new HeroName(current);
		HeroName second =new HeroName(opponent);
		first.addActionListener(this);
        second.addActionListener(this);
		view.getUpper().getPanel6().add(first);
		view.getUpper().getPanel1().add(second);
        model.setListener(this);
        end=view.getEndturn();
        end.addActionListener(this);
        heropower = new HeroPower();
        heropower.addActionListener(this);
        view.getUpper().getPanel6().add(heropower);
        for(int i = 0 ; i<current.getHand().size();i++){
        	CardButton b = new CardButton(current.getHand().get(i));
        		b.addActionListener(this);
        	    view.getUpper().getPanel5().add(b);
        }
        for(int i = 0 ; i<opponent.getHand().size();i++){
        	CardButton b = new CardButton(opponent.getHand().get(i));
        	b.addActionListener(this);
        	view.getUpper().getPanel2().add(b);
        }
        for(int i = 0 ; i<current.getField().size();i++){
			FieldMinion hh=new FieldMinion(current.getField().get(i));
			hh.addActionListener(this);
			view.getUpper().getPanel4().add(hh);
		}
        for(int i = 0 ; i<opponent.getField().size();i++){
			FieldMinion hh=new FieldMinion(opponent.getField().get(i));
			hh.addActionListener(this);
			view.getUpper().getPanel3().add(hh);
		}
        view.revalidate();
        view.repaint();
	}
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b instanceof FieldMinion && a!=null){
			JButton kaka =(JButton)a.getSource();
			LeechingSpell youssef =(LeechingSpell) ((CardButton)kaka).getCard();
			Minion f = ((FieldMinion)b).getMinion();
			try {
				model.getCurrentHero().castSpell(youssef, f);
			} catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn");
			} catch (NotEnoughManaException e1) {
				JOptionPane.showMessageDialog(null,"I don't have enough mana !!");
			}
			a=null;
		}
		if(b instanceof FieldMinion && j!=null){
			JButton lol =(JButton) j.getSource();
			MinionTargetSpell min=(MinionTargetSpell) ((CardButton)lol).getCard();
			Minion f = ((FieldMinion)b).getMinion();
			try {
				model.getCurrentHero().castSpell(min, f);
			}catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn");
			} catch (NotEnoughManaException e1) {
				JOptionPane.showMessageDialog(null,"I don't have enough mana !!");
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null,"InvalidTarget");
			}
			j=null;
		}
		 if(b instanceof HeroPower){
			 if(model.getCurrentHero() instanceof Mage || model.getCurrentHero() instanceof Priest){
				r=e; 
			 }	 }
		if(b instanceof HeroName && model.getCurrentHero() instanceof Mage && r!=null){
		 Hero g =((HeroName)b).getHero();
			try {
				((Mage)(model.getCurrentHero())).useHeroPower(g);
			} catch (NotEnoughManaException e1) {
				JOptionPane.showMessageDialog(null,"I don't have enough mana !!");
			} catch (HeroPowerAlreadyUsedException e1) {
				JOptionPane.showMessageDialog(null, "I already used my hero power");
			} catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn");
			} catch (FullHandException e1) {
				JOptionPane.showMessageDialog(null,"My hand is too full !!!");
			} catch (FullFieldException e1) {
				JOptionPane.showMessageDialog(null,"Your Field is full");
			} catch (CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null,"");
			}
			r=null;
		}
		if(model.getCurrentHero() instanceof Mage && b instanceof FieldMinion && r!=null){
			Minion m = (Minion)((FieldMinion)b).getMinion();
			try {
				((Mage)(model.getCurrentHero())).useHeroPower(m);
			} catch (NotEnoughManaException e1) {
				JOptionPane.showMessageDialog(null,"I don't have enough mana !!");
			} catch (HeroPowerAlreadyUsedException e1) {
				JOptionPane.showMessageDialog(null, "I already used my hero power");
			} catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn");
			} catch (FullHandException e1) {
				JOptionPane.showMessageDialog(null,"My hand is too full !!!");
			} catch (FullFieldException e1) {
				JOptionPane.showMessageDialog(null,"Your Field is full");
			} catch (CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null,"");
			}
			r=null;
		}
		if(b instanceof HeroName && model.getCurrentHero() instanceof Priest && r!=null){
			 Hero g =((HeroName)b).getHero();
				try {
					((Priest)(model.getCurrentHero())).useHeroPower(g);
				} catch (NotEnoughManaException e1) {
					JOptionPane.showMessageDialog(null,"I don't have enough mana !!");
				} catch (HeroPowerAlreadyUsedException e1) {
					JOptionPane.showMessageDialog(null, "I already used my hero power");
				} catch (NotYourTurnException e1) {
					JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn");
				} catch (FullHandException e1) {
					JOptionPane.showMessageDialog(null,"My hand is too full !!!");
				} catch (FullFieldException e1) {
					JOptionPane.showMessageDialog(null,"Your Field is full");
				} catch (CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null,"");
				}
				r=null;
			}
			if(model.getCurrentHero() instanceof Priest && b instanceof FieldMinion && r!=null){
				Minion m = (Minion)((FieldMinion)b).getMinion();
				try {
					((Priest)(model.getCurrentHero())).useHeroPower(m);
				} catch (NotEnoughManaException e1) {
					JOptionPane.showMessageDialog(null,"I don't have enough mana !!");
				} catch (HeroPowerAlreadyUsedException e1) {
					JOptionPane.showMessageDialog(null, "I already used my hero power");
				} catch (NotYourTurnException e1) {
					JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn");
				} catch (FullHandException e1) {
					JOptionPane.showMessageDialog(null,"My hand is too full !!!");
				} catch (FullFieldException e1) {
					JOptionPane.showMessageDialog(null,"Your Field is full");
				} catch (CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null,"");
				}
				r=null;
			}
		if(b instanceof HeroPower){ 
			if(model.getCurrentHero() instanceof Hunter || model.getCurrentHero() instanceof Paladin ||model.getCurrentHero() instanceof Warlock){
				try {
					model.getCurrentHero().useHeroPower();
					
				} catch (NotEnoughManaException e1) {
					JOptionPane.showMessageDialog(null,"I don't have enough mana !!");
				} catch (HeroPowerAlreadyUsedException e1) {
					JOptionPane.showMessageDialog(null, "I already used my hero power");
				} catch (NotYourTurnException e1) {
					JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn");
				} catch (FullHandException e1) {
					JOptionPane.showMessageDialog(null,"My hand is too full !!!");
				} catch (FullFieldException e1) {
					JOptionPane.showMessageDialog(null,"Your Field is full");
				} catch (CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null,"");
				}	
			}	
		}
		if(b instanceof FieldMinion && v!=null){
			JButton x = (JButton) v.getSource();
			Minion m = (Minion)((FieldMinion)x).getMinion();
			Minion o =(Minion)((FieldMinion)b).getMinion();
			try {
				model.getCurrentHero().attackWithMinion(m, o);
			} catch (CannotAttackException e1) {
				JOptionPane.showMessageDialog(null,"cannot attack");
			} catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null,"NotYourTurn");
			} catch (TauntBypassException e1) {
				JOptionPane.showMessageDialog(null,"TauntBypass");
			} catch (NotSummonedException e1) {
				JOptionPane.showMessageDialog(null,"NotSummoned");
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null,"InvalidTarget");
			}
			v=null;
		}
		if(b instanceof FieldMinion){
			v=e ;
		}
		if(b instanceof HeroName && balabizo!=null && v==null){
			Hero k =((HeroName)b).getHero();
			JButton p =(JButton) balabizo.getSource();
			HeroTargetSpell targ=(HeroTargetSpell) ((CardButton)p).getCard();
			try {
				model.getCurrentHero().castSpell(targ, k);
			} catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn");
			}  catch (NotEnoughManaException e1) {
				JOptionPane.showMessageDialog(null,"I don't have enough mana !!");
			}
			balabizo=null;
		}
		if(b instanceof HeroName && (v!=null) &&(balabizo==null)){
				JButton x = (JButton) v.getSource();
				Minion m = (Minion)((FieldMinion)x).getMinion();
				try {
					model.getCurrentHero().attackWithMinion(m,model.getOpponent());
				} catch (CannotAttackException e1) {
					JOptionPane.showMessageDialog(null,"cannot attack");
				} catch (NotYourTurnException e1) {
					JOptionPane.showMessageDialog(null,"NotYourTurn");
				} catch (TauntBypassException e1) {
					JOptionPane.showMessageDialog(null,"TauntBypass");
				} catch (NotSummonedException e1) {
					JOptionPane.showMessageDialog(null,"NotSummoned");
				} catch (InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null,"InvalidTarget");
				}
				v = null;			
		}
		if(b instanceof CardButton){
			Card c = ((CardButton)b).getCard();
			if(((CardButton)b).getCard() instanceof Minion && (a==null) && (j==null) ){
				Minion m = (Minion)((CardButton)b).getCard();
				int n = 0;
				if(n==0){
				try {
					model.getCurrentHero().playMinion(m);
					FieldMinion x = new FieldMinion(m);
					this.getView().getUpper().getPanel4().add(x);
					this.getView().getUpper().getPanel5().remove(b);
				} catch (NotYourTurnException e1) {
					JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn");
				} catch (NotEnoughManaException e1) {
					JOptionPane.showMessageDialog(null,"I don't have enough mana !!");
				} catch (FullFieldException e1) {
					JOptionPane.showMessageDialog(null,"Your Field is full");			
				}
			}
		}
			if(c instanceof Spell) {
				if((Spell)((CardButton)b).getCard() instanceof AOESpell){
					try {
						model.getCurrentHero().castSpell((AOESpell)c,model.getOpponent().getField());
						this.getView().getUpper().getPanel5().remove(b);
					} catch (NotYourTurnException e1) {
						JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn");
					} catch (NotEnoughManaException e1) {
						JOptionPane.showMessageDialog(null,"I don't have enough mana !!");
					}
				}
					if ((Spell)((CardButton)b).getCard() instanceof FieldSpell){
						try {
							model.getCurrentHero().castSpell((FieldSpell)c);
							this.getView().getUpper().getPanel5().remove(b);
						} catch (NotYourTurnException e1) {
							JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn");
						} catch (NotEnoughManaException e1) {
							JOptionPane.showMessageDialog(null,"I don't have enough mana !!");
						}
					}
					if (((CardButton)b).getCard() instanceof LeechingSpell){
						a = e;
					}
					if (((CardButton)b).getCard() instanceof MinionTargetSpell){
						j = e;	
					}	
					if ((Spell)((CardButton)b).getCard() instanceof HeroTargetSpell){
						balabizo=e;
					}
				}	
		}
		update();
		if(b instanceof EndTurn){
			uuu++;
			a=null;
			j=null;
			v=null;
			r=null;
			balabizo=null;
			String s="" ;
		try {    
			model.getCurrentHero().endTurn();	
		     } catch (FullHandException e1) {
		    	 if(e1.getBurned()instanceof Minion){
		    		 Minion m =(Minion)e1.getBurned();
		    		  s = m.getName()+"  "+"Mana cost :"+m.getManaCost()+"  "+m.getRarity()+"  "+"attack:"+m.getAttack()+"  "+"HealthPoints:"+m.getCurrentHP();
		 			if (m.isTaunt())
		 				s+="  Taunt";
		 			if (m.isDivine())
		 				s+="  Divine";
		 			if(!(m.isSleeping()))
		 				s+="  Charge";
		    	 }
		    	 if(e1.getBurned() instanceof Spell){
		    		 Spell c =(Spell)e1.getBurned();
		    		  s = c.getName()+"  "+"Mana cost :"+c.getManaCost()+"  "+c.getRarity();
		    		 
		    	 }
		    		
				JOptionPane.showMessageDialog(null,"My hand is too full !!! \n " );
				JOptionPane.showMessageDialog(null,"Card burned :"+s);
			} catch (CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null,"");
			}
		if(uuu%2!=0)
			update2();
			if(uuu%2==0)
			update();
		}
		this.getView().revalidate();
		this.getView().repaint();
	}
	public void update(){
		this.getView().getUpper().getPanel5().removeAll();
		 for(int i = 0 ; i<model.getCurrentHero().getHand().size();i++){
	        	CardButton b = new CardButton(model.getCurrentHero().getHand().get(i));
	        		b.addActionListener(this);
	        	view.getUpper().getPanel5().add(b);
	        }
		 this.getView().getUpper().getPanel2().removeAll();
	        for(int i = 0 ; i<model.getOpponent().getHand().size();i++){
	        	CardButton b = new CardButton(model.getOpponent().getHand().get(i));
	        	b.addActionListener(this);
	        	view.getUpper().getPanel2().add(b);
	        }
	        this.getView().getUpper().getPanel4().removeAll();
	        for(int i = 0 ; i<model.getCurrentHero().getField().size();i++){
				FieldMinion hh=new FieldMinion(model.getCurrentHero().getField().get(i));
				hh.addActionListener(this);
				view.getUpper().getPanel4().add(hh);
			}
	        this.getView().getUpper().getPanel3().removeAll();
	        for(int i = 0 ; i<model.getOpponent().getField().size();i++){
				FieldMinion hh=new FieldMinion(model.getOpponent().getField().get(i));
				hh.addActionListener(this);
				view.getUpper().getPanel3().add(hh);
			}
	        view.getUpper().getPanel1().removeAll();
	        view.getUpper().getPanel6().removeAll();
			view.getUpper().getPanel6().add(end);
	        HeroName first1 =new HeroName(model.getCurrentHero());
			HeroName second1 =new HeroName(model.getOpponent());
			first1.addActionListener(this);
	        second1.addActionListener(this);
			view.getUpper().getPanel6().add(first1);
			view.getUpper().getPanel1().add(second1);
			view.getUpper().getPanel6().add(heropower);
	}
	public void onGameOver() {
		if(model.getCurrentHero().getCurrentHP()==0 ){
			String s = model.getOpponent().getName();
			JOptionPane.showMessageDialog(null,s+" "+"is the winner");
		}
		if(model.getOpponent().getCurrentHP()==0){
			String s = model.getCurrentHero().getName();
			JOptionPane.showMessageDialog(null,s+" "+"is the winner");
		}
		System.exit(0);
	}
		public void update2(){
			this.getView().getUpper().getPanel5().removeAll();
			this.getView().getUpper().getPanel2().removeAll();
			this.getView().getUpper().getPanel4().removeAll();
			this.getView().getUpper().getPanel3().removeAll();
			 for(int i = 0 ; i<model.getOpponent().getHand().size();i++){
		        	CardButton bb = new CardButton(model.getOpponent().getHand().get(i));
		        		bb.addActionListener(this);
		        	view.getUpper().getPanel2().add(bb);
		        }
		        for(int i = 0 ; i<model.getCurrentHero().getHand().size();i++){
		        	CardButton bb = new CardButton(model.getCurrentHero().getHand().get(i));
		        	bb.addActionListener(this);
		        	view.getUpper().getPanel5().add(bb);
		        }
		        for(int i = 0 ; i<model.getOpponent().getField().size();i++){
					FieldMinion hh=new FieldMinion(model.getOpponent().getField().get(i));
					hh.addActionListener(this);
					view.getUpper().getPanel3().add(hh);
				}
		        for(int i = 0 ; i<model.getCurrentHero().getField().size();i++){
					FieldMinion hh=new FieldMinion(model.getCurrentHero().getField().get(i));
					hh.addActionListener(this);
					view.getUpper().getPanel4().add(hh);
				}
		        view.getUpper().getPanel1().removeAll();
		        view.getUpper().getPanel6().removeAll();
				view.getUpper().getPanel6().add(end);
		        HeroName first1 =new HeroName(model.getOpponent());
				HeroName second1 =new HeroName(model.getCurrentHero());
				first1.addActionListener(this);
		        second1.addActionListener(this);
				view.getUpper().getPanel6().add(second1);
				view.getUpper().getPanel1().add(first1);
				view.getUpper().getPanel6().add(heropower);
				}
	public static void main(String[] args) throws FullHandException, IOException, CloneNotSupportedException {
		Controller x = new Controller();	
	}
	
	
	

}

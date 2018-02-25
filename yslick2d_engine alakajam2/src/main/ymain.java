package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import screens.credits;
import screens.game;
import screens.game_over;
import screens.menu;
import yengine.yworld;



public class ymain  extends StateBasedGame
{


	public static final String gameName = "alakaam2";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int ycredits = 2;
	public static final int gameover = 3;


	public ymain() throws SlickException 
	{
		super(gameName);
		this.addState(new menu(menu));//add wour worl states
		this.addState(new game(play));
		this.addState(new credits(ycredits));
		this.addState(new game_over(gameover));
	}

	public static void main(String[] args)  throws SlickException
	{
		AppGameContainer appg;

		appg = new AppGameContainer(new ymain());
		appg.setDisplayMode(640,650, false);
		appg.setShowFPS(false);
		appg.start();
   
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException
	{
		this.getState(menu).init(gc,this);
		this.getState(play).init(gc,this);
		this.getState(ycredits).init(gc,this);
		this.enterState(menu);

	}

}

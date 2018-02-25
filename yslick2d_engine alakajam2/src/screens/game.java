package screens;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import entities.enemy;
import entities.game_manger;
import entities.player;
import entities.powerup;
import yengine.yengine;
import yengine.yentity;
import yengine.yworld;

public class game extends yworld {


	public static int kill_count;
	public boolean pause = false;
	public game(int state) throws SlickException {
		super(state);
	
	}
	
	@Override
	public void yinit(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		mc = new ArrayList<yentity>();//reset everything
		kill_count = 0;
		
		String ph = "blue_box.png";//place holder
		String ps = "player_sprit.png";//player sprite
		
		game_manger gm = new game_manger();
		player p = new player(300,300,ps);
		
		enemy e1 = new enemy(600,300,ph);
		enemy e2 = new enemy(20,300,ph);
		
		//add to world
		add(gm);
		add(e1);
		add(e2);
		add(p);
	
	}//end yinit

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		

		
		
		super.update(gc, sbg, delta);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		yengine.ds(g, "kill count: "+kill_count, 20, 20);
		super.render(arg0, arg1, g);
	}
	

	

}

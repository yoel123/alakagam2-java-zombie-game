package screens;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import entities.btn;
import yengine.yengine;
import yengine.yworld;

public class game_over extends yworld {
	
	public btn play_btn;
	public game_over(int state) throws SlickException {
		super(state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void yinit(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//create play btn
		play_btn= new btn(150.0f,200.0f,"playNow.png",250.0f,50.0f);

		add(play_btn);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		//if btn is clicked change world to game world
		if(play_btn.is_clicked)
		{
			sbg.getState( 1 ).init(gc, sbg);
			sbg.enterState(1);
		}
		super.update(gc, sbg, delta);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {


		super.render(arg0, arg1, g);
		yengine.ds(g, "game over ", 20, 120);
		yengine.ds(g, "total kills: "+game.kill_count, 20, 150);
	
	}

}

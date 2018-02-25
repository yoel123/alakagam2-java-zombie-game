package entities;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import yengine.yengine;
import yengine.yentity;

public class flyer extends enemy {

	private boolean go_down;

	public flyer(float x2, String img2) throws SlickException {
		super(x2, 200, img2);
		speed=0.08f;
		hp=1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() throws SlickException 
	{
		ArrayList<yentity> mcs = get_by_type("player");
		
		if(mcs.get(0)==null) {return;}
		float[] cam = world.get_camera();
		player p = (player)mcs.get(0);
		
		int pwidth = (int) (p.x+p.width);//player width
		
		//start landing
		if(x>p.x-cam[0] && x<pwidth-cam[0]) {go_down=true;}// 
		
		if(go_down && y!=300 )
		{
			
			if(y<300) {y++;}
			return;
		}
		
		super.move();
		
	}//end move
	
	

}

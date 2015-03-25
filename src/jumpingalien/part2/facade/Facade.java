package jumpingalien.part2.facade;

import java.util.Collection;

import jumpingalien.model.Direction;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacadePart2 {

	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		try{
			Mazub alien = new Mazub(pixelLeftX, pixelBottomY, sprites);
			return alien;
		} catch (IllegalArgumentException e){
			throw new ModelException("Illegal Sprite argment");
		}
	}

	@Override
	public int[] getLocation(Mazub alien) {
		int location[] = new int[2];
		location[0] = (int) alien.getLocationX();
		location[1] = (int) alien.getLocationY();
		return location;
	}

	@Override
	public double[] getVelocity(Mazub alien) {
		double[] velocity = new double[2];
		velocity[0] = alien.getVelocityX();
		velocity[1] = alien.getVelocityY();
		return velocity;
	}

	@Override
	public double[] getAcceleration(Mazub alien) {
		double[] acceleration = new double[2];
		acceleration[0] = alien.getAccelerationX();
		acceleration[1] = alien.getAccelerationY();
		return acceleration;
	}

	@Override
	public int[] getSize(Mazub alien) {
		try{
			return alien.getSize();
		} catch (ModelException e){
			throw new ModelException("Unvalid current sprite");
		}
	}

	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	@Override
	public void startJump(Mazub alien) {
		alien.startJump();
	}

	@Override
	public void endJump(Mazub alien) {
		alien.endJump();
	}

	@Override
	public void startMoveLeft(Mazub alien) {
		alien.startMove(Direction.LEFT);
	}

	@Override
	public void endMoveLeft(Mazub alien) {
		alien.endMove(Direction.LEFT);
	}

	@Override
	public void startMoveRight(Mazub alien) {
		alien.startMove(Direction.RIGHT);
	}

	@Override
	public void endMoveRight(Mazub alien) {
		alien.endMove(Direction.RIGHT);
	}

	@Override
	public void startDuck(Mazub alien) {
		try{
			alien.startDucking();
		} catch (IllegalStateException e){
			throw new ModelException("Mazub is already ducking");
		}
	}

	@Override
	public void endDuck(Mazub alien) {
		try{
			alien.endDucking();
		} catch (IllegalStateException e){
			throw new ModelException("Mazub is not ducking");
		}	
	}

	@Override
	public void advanceTime(Mazub alien, double dt) {
		try {
			alien.advanceTime(dt);
		} catch (IllegalArgumentException e) {
			throw new ModelException("The elapsed time can never be negative.");
		}
	}

	@Override
	public int getNbHitPoints(Mazub alien) {
		return alien.getHitPoints();
	}

	@Override
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		try{
			World world = new World(tileSize, nbTilesX, nbTilesY,
					visibleWindowWidth, visibleWindowHeight, targetTileX,
					 targetTileY);
			return world;
		} catch (IllegalArgumentException e){
			throw new ModelException("Illegal Argument for Game World");
		}
	}

	@Override
	public int[] getWorldSizeInPixels(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTileLength(World world) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void startGame(World world) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isGameOver(World world) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean didPlayerWin(World world) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void advanceTime(World world, double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getVisibleWindow(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getTilePositionsIn(World world, int pixelLeft,
			int pixelBottom, int pixelRight, int pixelTop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGeologicalFeature(World world, int pixelX, int pixelY)
			throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setGeologicalFeature(World world, int tileX, int tileY,
			int tileType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMazub(World world, Mazub alien) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isImmune(Mazub alien) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Plant createPlant(int x, int y, Sprite[] sprites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPlant(World world, Plant plant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Plant> getPlants(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getLocation(Plant plant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sprite getCurrentSprite(Plant plant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shark createShark(int x, int y, Sprite[] sprites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addShark(World world, Shark shark) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Shark> getSharks(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getLocation(Shark shark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sprite getCurrentSprite(Shark shark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public School createSchool() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Slime createSlime(int x, int y, Sprite[] sprites, School school) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSlime(World world, Slime slime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Slime> getSlimes(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getLocation(Slime slime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sprite getCurrentSprite(Slime slime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public School getSchool(Slime slime) {
		// TODO Auto-generated method stub
		return null;
	}

}
package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;

public abstract class GameObjects implements CollisionDetect {
	
	/**
	 * @return will return terminated
	 * 			|result == this.terminated
	 */
	boolean isTerminated() {
		return this.terminated;
	}
	
	/**
	 * 
	 * @param value
	 * @post	terminated will equal the given value
	 * 			|new.isTerminated() = value
	 */
	protected void setTerminated(boolean value) {
		this.terminated = value;
	}
	
	/*
	 * This variable contains whether the given Game Object is terminated or not
	 */
	private boolean terminated;
	
	/**
	 * Returns the x location of this Game Object
	 */
	@Basic
	protected double getLocationX() {
		return this.locationX;
	}

	/**
	 * Sets the x location of this Game Object to the given x
	 * 
	 * @param 	x
	 *          the new x location for this Game Object
	 * @post 	the x location of this Game Object is equal to the given x 
	 * 			|new.getLocationX() == x
	 */
	protected void setLocationX(double x) {
		this.locationX = x;
	}

	/**
	 * This variable contains the x location of this Game Object.
	 */
	private double locationX;

	/**
	 * Returns the y location of this Game Object
	 */
	@Basic
	protected double getLocationY() {
		return this.locationY;
	}

	/**
	 * Sets the y location of this Game Object to the given y
	 * 
	 * @param 	y
	 *          the new y location for this Game Object
	 * @post 	the y location of this Game Object is equal to the given y
	 *       	|new.getLocationY() = y
	 */
	protected void setLocationY(double y) {
		this.locationY = y;
	}

	/**
	 * This variable contains the y location of this Game Object.
	 */
	private double locationY;

	/**
	 * Returns the location of this Game Object
	 * 
	 * @return returns an array of integers, the first element is the x
	 *         location, the second element is the y location 
	 *         | result == {(int) getLocationX(), (int) getLocationY()}
	 */
	public int[] getLocation() {
		int[] location = new int[2];
		location[0] = (int) getLocationX();
		location[1] = (int) getLocationY();
		return location;
	}
	
	/**
	 * Return the sprites of this Game Object
	 */
	@Basic
	protected Sprite[] getSprites() {
		return this.sprites;
	}

	/**
	 * Set the sprites of this Game Object to the given sprites
	 * 
	 * @param 	sprites
	 *          the new sprites for this Game Object
	 * @post 	the sprites of this Game Object is equal to the given sprites
	 *       	|new.getSprites() == sprites
	 */
	protected void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}

	/**
	 * This array holds the sprites of this Game Object
	 */
	private Sprite[] sprites;
	
	/**
	 * Returns the hitPoints of this game object
	 */
	protected int getHitPoints() {
		return hitPoints;
	}

	/**
	 * 
	 * @param 	hitPoints
	 * @post 	the amount of hitPoints of this Game Object will equal the given amount of hitPoints
	 * 			|new.getHitPoints() == hitPoints
	 */
	protected void setHitPoints(int hitPoints) {
 		if(hitPoints <= 0){
			this.hitPoints = 0;
 			setdead(true);
 		} else if(
 			hitPoints > getMaxHitPoints()) {
			this.hitPoints = getMaxHitPoints();
 		} else {
			this.hitPoints = hitPoints;
 		}
 	}
	
	/**
	 * This variable contains the amount of hitpoints for this Game Object
	 */
	private int hitPoints;
	
	/**
	 * This variable returns whether this Game Object is dead or not
	 */
	protected boolean isDead() {
		return isDead;
	}

	/**
	 * 
	 * @param 	isDead
	 * 			the new dead state for this Game Object
	 * @post 	the new dead state for this object will equal the given boolean
	 * 			|new.isDead() == isDead
	 */
	void setdead(boolean isDead) {
		this.isDead = isDead;
	}
	
	/**
	 * This variable indicates whether this Game Object is dead or not
	 */
	private boolean isDead;

	/**
	 * Returns how long this Game Object is already dead
	 */
	protected double getTimeDead() {
		return timeDead;
	}

	/**
	 * 
	 * @param 	timeDead
	 * 			the time this Object is already dead
	 * @post 	the new timeDead of this Game Object will equal the given timeDead
	 * 			|new.getTimeDead() == timeDead
	 */
	protected void setTimeDead(double timeDead) {
		this.timeDead = timeDead;
	}
	/**
	 * This variable containts the time that this Game Object is dead
	 */
	private double timeDead;
	
	/**
	 * Returns how long this Game Object is already in the water
	 */
	protected double getInWaterTimer() {
		return inWaterTimer;
	}


	/**
	 * @param 	inWaterTime 
	 * 			the time how long this game object is under water
	 * @post 	the inWaterTimer will equal the given inWaterTime
	 * 			|new.getInWaterTimer() == inWaterTime 
	 */
	protected void setInWaterTimer(double inWaterTime) {
		this.inWaterTimer = inWaterTime;
	}
	
	/**
	 * This variable contains how long this game Object is already under water
	 */
	private double inWaterTimer;

	abstract int getMaxHitPoints();
	
	/**
	 * Returns how long this Game Object is already under water
	 */
	protected double getInMagmaTimer() {
		return inMagmaTimer;
	}


	/**
	 * @param 	inMagmaTime
	 * 			the time how long this game Object is already in magma
	 * @post	the inMagmaTimer will equal the given inMagmaTime
	 * 			|new.getInMagmaTimer() == inMagmaTime
	 */
	protected void setInMagmaTimer(double magmaTimer) {
		this.inMagmaTimer = magmaTimer;
	}
	
	/**
	 * This variable contains the value how long this Game Object is in Magma
	 */
	private double inMagmaTimer;

	public World getWorld() {
		return world;
	}

	protected void setWorld(World world) {
		if(isValidWorld(world))
			this.world = world;
	}
	
	protected abstract boolean isValidWorld(World world);

	protected void removeWorld() {
		this.world = null;
	}
	
	/**
	 * Returns whether this mazub is already in a world or not
	 * @return	returns whether this Mazub is already allocated to a world or not
	 * 			| getWorld() != null
	 */
	protected boolean hasAWorld(){
		return getWorld() != null;
	}
	
	private World world;
}

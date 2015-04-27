package jumpingalien.model;

import java.util.Collection;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.*;

public class Slime extends GameObject {
	/**
	 * @param x
	 * @param y
	 * @param sprites
	 * @throws	IllegalArgumentException()
	 * 			...
	 * 			|((sprites.length != 2) || (!school.canHaveAsSlime(this)))
	 * @throws 	IllegalArgumentException()
	 * 			...	
	 * 			| (!isValidSchool())
	 * @effect 	...
	 * 			| setLocationX(x)
	 * @effect 	...
	 * 			| setLocationY(y)
	 * @effect 	...
	 * 			| setSprites(sprites)
	 * @effect	...
	 * 			| setSchool(school)
	 * @effect	...
	 * 			| setHitPoints(50)
	 * @effect	...
	 * 			| setVelocityX(0)
	 * @effect	...
	 * 			| setVelocityY(0)
	 * @effect	...
	 * 			| setAccelerationX(0)
	 * @effect	...
	 * 			| setAccelerationY(0)
	 * @effect	...
	 * 			| newMovement()
	 * @effect	...
	 * 			| setInMagmaTimer(0.2)
	 * @effect	...
	 * 			| setInWaterTimer(-1) 
	 */
	public Slime (int x, int y, Sprite[] sprites, School school) throws IllegalArgumentException{
		if(sprites.length != 2){
			throw new IllegalArgumentException();
		}
		if (!isValidSchool(school)) {
			throw new IllegalArgumentException();
		}
		setLocationX(x);
		setLocationY(y);
		setSprites(sprites);
		setCurrentSpriteIndex(0);
		setSchool(school);
		school.addSlime(this);
		setHitPoints(100);
		setVelocityX(0);
		setVelocityY(0);
		setAccelerationX(0);
		setAccelerationY(0);
		newMovement();
		setInMagmaTimer(0.2);
		setInWaterTimer(-1);
	}
	
	/**
	 * Returns the current sprite index of this slime
	 */
	@Basic
	private int getCurrentSpriteIndex() {
		return this.currentSpriteIndex;
	}
	
	/**
	 * 
	 * @param index
	 * @post	...
	 * 			| new.getCurrentSpriteIndex() == index
	 */
	private void setCurrentSpriteIndex(int index) {
		this.currentSpriteIndex = index;
	}
	
	/**
	 * Contains the index of the current sprite of this Slime
	 */
	private int currentSpriteIndex;
	
	/**
	 * Returns the current sprite of this Slime
	 */
	@Basic
	public Sprite getCurrentSprite() {
		return getSprites()[getCurrentSpriteIndex()];
	}
	
	/**
	 * Return the school in which this slime is
	 */
	public School getSchool() {
		return school;
	}
	
	/**
	 * 
	 * @param 	school
	 * @return 	...
	 * 			| result == (getSchool() == school)
	 */
	public boolean hasAsSchool(School school) {
		return (getSchool() == school);
	}
	
	/**
	 * 
	 * @param newSchool
	 * @return	...
	 * 			| result == (isValidSchool(newSchool) && (newSchool != getSchool()) && (getSchool().getAmountSlimes() < newSchool.getAmountSlimes()))
	 */
	private boolean isValidNewSchool(School newSchool) {
		return isValidSchool(newSchool) && (newSchool != getSchool()) && (getSchool().getAmountSlimes() < newSchool.getAmountSlimes());
	}
	
	/**
	 * 
	 * @param school
	 * @return	...
	 * 			| result == ((school != null) && (not school.isTerminated()))
	 */
	private boolean isValidSchool(School school) {
		return ((school != null) && (!school.isTerminated()));
	}
	
	/**
	 * @post	...
	 * 			| new.getSchool() == null
	 */
	private void removeSchool() {
		this.school = null;
	}

	/**
	 * @param 	school
	 * @throws	IllegalArgumentException
	 * 			...
	 * 			| ! isValidSchool(school)
	 * @post 	...
	 * 			| new.getSchool() == school;
	 */
	private void setSchool(School school) throws IllegalArgumentException {
		if (!isValidSchool(school))
			throw new IllegalArgumentException();
		this.school = school;
	}
	
	/**
	 * 
	 * @param school
	 * @pre	...
	 * 		| isValidNewSchool(school)
	 * @effect	...
	 * 			| setSchool(school)
	 */
	private void setNewSchool(School school) {
		assert(isValidNewSchool(school));
		setSchool(school);
	}
	
	/**
	 * This variable contains the school in which this slime is
	 */
	private School school;
	
	
	@Basic
	protected double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	 * 
	 * @param velocityX
	 * @post	...
	 * 			| new.getVelocityX() == velocityX
	 */
	private void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	/**
	 * This variable contains the current horizontal velocity of this Slime
	 */
	private double velocityX;
	
	@Basic
	protected double getVelocityY() {
		return this.velocityY;
	}
	
	/**
	 * 
	 * @param velocityY
	 * @post	...
	 * 			| new.getVelocityY() == velocityY
	 */
	private void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	/**
	 * This variable contains the current vertical velocity of this Slime
	 */
	private double velocityY;
	
	@Basic
	protected double getAccelerationX() {
		return accelerationX;
	}
	
	/**
	 * 
	 * @param accelerationX
	 * @post	...
	 * 			| new.getAccelerationX() == accelerationX
	 */
	private void setAccelerationX(double accelerationX) {
		this.accelerationX = accelerationX;
	}
	
	/**
	 * This variable contains the current horizontal acceleration
	 */
	private double accelerationX;
	
	/**
	 * 
	 * @return	...
	 * 			| result == 0.7
	 */
	private static double getInitialAccelerationX() {
		return 0.7;
	}
	
	@Basic
	protected double getAccelerationY() {
		return accelerationY;
	}
	
	/**
	 * 
	 * @param accelerationY
	 * @post	...
	 * 			| new.getAccelerationY() == accelerationY
	 */
	private void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}
	
	
	/**
	 * This variable contains the current vertical acceleration of this Slime
	 */
	private double accelerationY;
	
	/**
	 * Returns whether this Slime is moving left or not.
	 * @return 	true when the movement direction is LEFT
	 * 			| if getMovementDirection() == Direction.LEFT then
	 * 			|	return true
	 * 			| else then
	 * 			| 	return false
	 */
	private boolean isMovingLeft() {
		if (this.getMovementDirection() == Direction.LEFT) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Updates the location of this Slime given a certain amount of seconds
	 * @param 	seconds
	 * 			The seconds to compute the new position.
	 * @pre		seconds needs to be positive.
	 * 			| seconds >= 0
	 * @effect	...
	 * 			| setIsOnGameObject(false)
	 * @effect	...
	 * 			| double[] location = calculateLocation(seconds)
	 * 			| if (locationIsValidInWorld((int) location[0], (int) location[1]) then
	 * 			|	calculateLocationCollisionTerrain(seconds, location)
	 * 			|	calculateLocationCollisionObjects(location)
	 * 			| 	calculateLocationCollisionSlime(location)
	 * 			|	setLocation(location)
	 */
	private void updateLocation(double seconds) {
		assert (seconds >= 0);
		setIsOnGameObject(false);
		double[] location = calculateLocation(seconds);
		if(locationIsValidInWorld((int)location[0],(int) location[1])){
			calculateLocationCollisionTerrain(seconds, location);
			calculateLocationCollisionObjects(location);
			calculateLocationCollisionSlime(location);
			setLocation(location);
		}
	}

	/**
	 * @param location
	 */
	public void calculateLocationCollisionSlime(double[] location) {
		boolean hasCollisionSlime = getWorld().collisionSlimes((int)location[0], (int)location[1], (int) location[0] + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight(), this).size() > 0;
		if(hasCollisionSlime){
			hasCollisionSlime = getWorld().collisionSlimes((int)getLocationX(), (int)location[1], (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight(), this).size() > 0;
			if(!hasCollisionSlime){
				location[0] = getLocationX();				
			} else {
				hasCollisionSlime = getWorld().collisionSlimes((int)location[0], (int)getLocationY(), (int) location[0] + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight(), this).size() > 0;
				if(!hasCollisionSlime){
					location[1] = getLocationY();	
					setIsOnGameObject(true);
				} else {
					location[0] = getLocationX();
					location[1] = getLocationY();
					setIsOnGameObject(true);
				}
			}
		}
	}

	private double getMaximumHorizontalVelocity() {
		return 2.5;
	}
	
	/**
	 * This function returns whether this Slime is on Solid ground or not
	 * @return
	 */
	private boolean isOnSolidGround(){
		return hasCollisionBottom((int)getLocationX(), (int)getLocationY()-1);
	}
	
	public void advanceTime(double dt) throws IllegalArgumentException {
		if(!isDead()){
			double seconds = dt;
			if (seconds < 0 || seconds >= 0.2) 
				throw new IllegalArgumentException();
			updateLocationAndVelocity(dt);
			if (isInWater()) {
				if (getInWaterTimer() >= 0.2) {
					setHitPoints(getHitPoints()-2);
					setInWaterTimer(getInWaterTimer()-0.2);
				}
			} else {
				setInWaterTimer(0);
			}
		} else {
			setTimeDead(getTimeDead() + dt);
			if(getTimeDead() > 0.6){
				terminate();
			}
		}
	}
	
	protected void advanceTimeCollisionDetect(double dt){
		if (! isOnSolidGround()) {
			setAccelerationY(-10);
		}
		setMovementTime(getMovementTime()-dt);
		if(getMovementTime() <= 0){
			newMovement();
		}
		updateVelocity(dt);
		updateLocation(dt);	
		handleMagma(dt);
		handleCollisionMazub();
		handleCollisionShark();		
		handleCollisionSlime();
		handleWater(dt);
	}
	
	private void updateVelocity(double dt){
		double accelerationX = getAccelerationX();
		double velocityX;
		if(isMovingLeft()){
			accelerationX *= -1;
			velocityX = getVelocityX() + accelerationX*dt;
			if (velocityX < -getMaximumHorizontalVelocity()) {
				setVelocityX(-getMaximumHorizontalVelocity());
			} else {
				setVelocityX(velocityX);
			}
		} else {
			velocityX = getVelocityX() + accelerationX*dt;
			if (velocityX > getMaximumHorizontalVelocity()) {
				setVelocityX(getMaximumHorizontalVelocity());
			} else {
				setVelocityX(velocityX);
			}
		}
		if (isOnSolidGround()) {
			setVelocityY(0);
			setAccelerationY(0);
		} else if(isOnGameObject()){
			setVelocityY(0);
		} else {
			setVelocityY(getVelocityY() + getAccelerationY()*dt);
		}
	}
	
	
	private void newMovement(){
		int random = (int)(Math.random()*2);
		switch (random){
			case 0: setMovementDirection(Direction.LEFT);
					setCurrentSpriteIndex(0);
					break;
			case 1: setMovementDirection(Direction.RIGHT);
					setCurrentSpriteIndex(1);
					break;
		}
		double time = 2 + (int)(Math.random()*5);
		
		setVelocityX(0);
		setAccelerationX(getInitialAccelerationX());
		
		setMovementTime(time);
		
	}
	
	@Basic
	private Direction getMovementDirection() {
		return this.movementDirection;
	}
	
	/**
	 * 
	 * @param direction
	 * @post	...
	 * 			| new.getMovementDirection() == direction
	 */
	private void setMovementDirection(Direction direction) {
		this.movementDirection = direction;
	}
	
	private Direction movementDirection;
		
	/**
	 * 
	 * @return	...
	 * 			| result == this.movementTimer
	 */
	private double getMovementTime() {
		return movementTime;
	}

	/**
	 * 
	 * @param time
	 * @post	...
	 * 			| new.getMovementTimer() == time
	 */
	private void setMovementTime(double time) {
		this.movementTime = time;
	}
	
	/**
	 * This variable contains the time that the current movement will last
	 */
	private double movementTime;
	
	/**
	 * 
	 * @effect 	...
	 * 			| setHitPoints(getHitPoints() - 50)
	 * @effect 	...
	 * 			| school.reducePoint(this)
	 */
	void hadCollisionMazub() {
		setHitPoints(getHitPoints() - 50);
		getSchool().reducePoint(this);
	}
	
	private void handleCollisionMazub(){
		if(!getWorld().getMazub().isImmune() && getWorld().collisionMazubInPerimeters((int)getLocationX(), (int)getLocationY(), (int)getLocationX()+getCurrentSprite().getWidth(), (int)getLocationY()+getCurrentSprite().getHeight())){
			getWorld().getMazub().hadCollissionSlime();
			hadCollisionMazub();
		}
	}
	
	private void handleCollisionShark() {
		Collection<Shark> collection = getWorld().collisionSharksInPerimeters((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), (int) getLocationY()+getCurrentSprite().getHeight());
		for (Shark shark : collection) {
			hadCollisionShark();
			shark.hadCollisionSlime();
			if(shark.isTerminated()){
				getWorld().removeShark(shark);				
			}
		}
	}
	
	private void handleCollisionSlime() {
		Collection<Slime> collection = getWorld().collisionSlimesInPerimeters((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), (int) getLocationY()+getCurrentSprite().getHeight());
		for (Slime slime: collection) {
			if ((this.getSchool() != null) && (slime.getSchool() != null) && !(this.getSchool() == slime.getSchool()) && (slime.getSchool().getAmountSlimes() > this.getSchool().getAmountSlimes())) {
				this.changeSchool(slime.getSchool());
			}
		}
	}
	
	private void changeSchool(School newSchool) {
		assert (isValidNewSchool(newSchool));
		school.removeSlime(this);
		// TODO
		setNewSchool(newSchool);
		newSchool.addNewSchoolMember(this);
	}
	
	/**
	 * @effect	...
	 * 			| setWorld(null)
	 * @effect	...
	 * 			| setTerminated(true)
	 */
	@Override
	void terminate() {
		// remove world
		this.removeWorld();
		getSchool().removeSlime(this);
		removeSchool();
		// set boolean
		setTerminated(true);
	}

	public void hadCollisionShark() {
		setHitPoints(getHitPoints()-50);		
	}

	@Override
	int getMaxHitPoints() {
		return Integer.MAX_VALUE;
	}

	@Override
	protected boolean isValidWorld(World world) {
		// TODO niet volledig
		return world != null;
	}

	@Override
	double getActualAccelerationX() {
		// TODO Auto-generated method stub
		return 0;
	}
}

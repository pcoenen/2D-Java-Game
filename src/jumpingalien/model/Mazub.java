package jumpingalien.model;

import java.util.Collection;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

/**
 * 
 * @author Pieter-Jan Coenen (1ste Bacherlor Informatica) en Stijn Caerts (1ste Bacherlor Informatica)
 *
 * @invar	the Mazub's location is always in the game window
 * 			|  ((0 <= getLocationX()) && (getLocationX() < getWindowWidth()) 
 * 				&& (0 <= getLocationY()) && (getLocationY < getWindowHeight())
 * 
 * @invar	the Mazub's hitpoints are always between 0 and 500
 * 			| (0 <= getHitPoints()) && (getHitPoints() <= 500)
 */
public class Mazub extends GameObject {

	/**
	 * 
	 * @param 	pixelLeftX
	 * 			The horizontal pixel from the bottom left corner where Mazub will spawn.
	 * @param 	pixelBottomY
	 * 			The vertical pixel from the bottom left corner where Mazub will spawn.
	 * @param 	sprites
	 * 			The sprites for this Mazub.
	 * @effect	call the constructor of the superclass
	 * 			| super(pixelLeftX, pixelBottomY, sprites)
	 * @effect	sets the initial spriteIndex to 0
	 * 			| setSpriteIndex(0)
	 * @effect 	set the timer to zero
	 * 			| setTimer(0)
	 * @post	calculate the number of sprites used to alternate when moving
	 * 			| new.getAmountSpritesForMovement() = (sprites.length - 8) / 2 - 1
	 * @effect	set hitpoints to startHitpoints()
	 * 			| setHitPoints(startHitpoints())
	 * @effect 	set the magma timer to 0.2
	 * 			| setMagmaTimer(0.2)
	 */
	public Mazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) throws IllegalArgumentException {
		super(pixelLeftX, pixelBottomY, sprites);	
		this.setSpriteIndex(0);
		this.setSpriteTimer(0);
		this.amountSpritesForMovement = (sprites.length - 10) / 2;
		this.setHitPoints(startHitpoints());
		this.setInMagmaTimer(0.2);
		this.setInWaterTimer(-1);
	}
	
	/**
	 * 
	 * @param 	pixelLeftX
	 * 			The horizontal pixel from the bottom left corner where Mazub will spawn.
	 * @param 	pixelBottomY
	 * 			The vertical pixel from the bottom left corner where Mazub will spawn.
	 * @param 	sprites
	 * 			The sprites for this Mazub.
	 * @param	program
	 * 			The program for this Mazub
	 * @effect	call the constructor of the superclass
	 * 			| super(pixelLeftX, pixelBottomY, sprites)
	 * @effect	sets the initial spriteIndex to 0
	 * 			| setSpriteIndex(0)
	 * @effect 	set the timer to zero
	 * 			| setTimer(0)
	 * @post	calculate the number of sprites used to alternate when moving
	 * 			| new.getAmountSpritesForMovement() = (sprites.length - 8) / 2 - 1
	 * @effect	set hitpoints to startHitpoints()
	 * 			| setHitPoints(startHitpoints())
	 * @effect 	set the magma timer to 0.2
	 * 			| setMagmaTimer(0.2)
	 */
	public Mazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites, Program program) throws IllegalArgumentException {
		super(pixelLeftX, pixelBottomY, sprites, program);	
		this.setSpriteIndex(0);
		this.setSpriteTimer(0);
		this.amountSpritesForMovement = (sprites.length - 10) / 2;
		this.setHitPoints(startHitpoints());
		this.setInMagmaTimer(0.2);
		this.setInWaterTimer(-1);
	}
	
	/**
	 * @return 	...
	 * 			| result == 10
	 */
	@Override
	int getRequiredLengthSpriteArray() {
		return 10;
	}
	
	/**
	 * Checks if the given sprites array is valid or not
	 * @param sprites
	 * 			the sprite array to check
	 * @return	false if the length of the sprite array is less than the required length
	 * 			| if (sprites.length < getRequiredLengthSpriteArray()) then
	 * 			|	result == false
	 * @return	if the length of the sprite array is greater or equalt to the required length and 
	 * 				if the sprite array contains a null pointer, return false, otherwise return true	 
	 * 			| if (sprites.length >= getRequiredLengthSpriteArray()) then
	 * 			|	foreach (sprite in sprites) 
	 * 			|		if (sprite == null) then
	 * 			|			result == false
	 * 			|	result == true
	 */
	@Override
	protected boolean isValidSpriteArray(Sprite[] sprites) {
		if (sprites.length < getRequiredLengthSpriteArray()) {
			return false;
		} else {
			for (Sprite sprite : sprites) {
				if (sprite == null) {
					return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * This methode return the amount of hitpoints where this Mazub starts with
	 * @return 	Returns 100
	 * 			| result == 100
	 */
	protected int startHitpoints(){
		return 100;
	}
	
	/**
	 * Return velocityX of this Mazub
	 * 	velocityX expresses the current horizontal velocity of this Mazub
	 */
	@Basic @Override
	public double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	 * Set velocityX of this Mazub to a given value
	 * 
	 * @param 	velocityX
	 * 			The new velocityX for this Mazub
	 * @pre		 if velocity X is greater than zero, the given velocityX needs to be bigger or equal to 
	 * 				the initial horizontal velocity and smaller or equal to 
	 * 				the maximum horizontal velocity of this Mazub
	 * 			| if velocityX > 0 then
	 * 			| velocityX >= getInitialHorizontalVelocity() 
	 * 					&& velocityX <= getMaximumHorizontalVelocity()
	 * @pre		 if velocity X is less than zero, the given velocityX needs to be smaller or equal to 
	 * 				the negative of the initial horizontal velocity and bigger or equal to 
	 * 				the negative of the maximum horizontal velocity of this Mazub
	 * 			| if velocityX < 0 then
	 * 				velocityX <= -getInitialHorizontalVelocity() 
	 * 					&& velocityX >= -getMaximumHorizontalVelocity()
	 * @pre		if velocityX equals zero, there are no further restrictions
	 * 			|if velocityX == 0 then
	 * 			| velocityX == 0
	 * @post 	The velocityX of this Mazub is equal to the given velocityX
	 * 			| new.getvelocityX() == velocityX
	 */
	private void setVelocityX(double velocityX) {
		assert(((velocityX <= getMaximumHorizontalVelocity()) && (velocityX >= getInitialHorizontalVelocity())) 
				|| (velocityX == 0) || ((velocityX >= -getMaximumHorizontalVelocity()) 
						&& (velocityX <= -getInitialHorizontalVelocity())));
		this.velocityX = velocityX;
	}
	
	/**
	 * This variable contains the current horizontal velocity of this Mazub
	 */
	private double velocityX;
	
	/**
	 * Return the maximum Horizontal Velocity of this Mazub
	 * @return	returns 1 if Mazub is ducking, otherwise 3.
	 * 			| if isDucking() then
	 * 			|	return 1
	 * 			| else then
	 * 			|	return 3
	 */
	private double getMaximumHorizontalVelocity() {
		if (this.isDucking()) {
			return 1;
		} else {
			return 3;
		}
	}
	
	/**
	 * Return the initial Horizontal Velocity of this Mazub
	 * 
	 * @note 	the initial velocity is always bigger or equal to one
	 * 			| getInitialHorizontalVelocity() >= 1
	 */
	private final double getInitialHorizontalVelocity() {
		return 1;
	}
	
	/**
	 * Return accelerationX of this Mazub
	 * 	accelerationX expresses the current horizontal acceleration of this Mazub
	 */
	@Basic @Override
	public double getAccelerationX() {
		return accelerationX;
	}

	/**
	 * 
	 * @param 	accelerationX
	 * 			the new accelerationX for this Mazub
	 * @post 	if the given accelerationX is bigger or equal to zero, 
	 * 				the accelerationX of this Mazub is equal to the given accelerationX
	 * 			|if (accelerationX >= 0)
	 * 			|	new.getAcceleration() == accelerationX
	 * @post 	if the given accelerationX is lower than zero, 
	 * 				the accelerationX of this Mazub is equal to zero
	 * 			|if (accelerationX < 0)
	 * 			|	new.getAcceleration() == 0
	 */
	private void setAccelerationX(double accelerationX) {
		if (accelerationX < 0 )
				accelerationX = 0;
		this.accelerationX = accelerationX;
	}
	
	/**
	 * this variable contains the current horizontal acceleration for this Mazub
	 */	
	private double accelerationX;
	
	
	/**
	 * Return velocityY of this Mazub
	 * 	velocityY expresses the current vertical velocity of this Mazub
	 */
	@Basic @Override
	public double getVelocityY() {
		return this.velocityY;
	}

	/**
	 * 
	 * @param 	velocityY
	 * 			the new velocityY for this Mazub
	 * @post 	The velocityY of this Mazub is equal to the given velocityY
	 * 			| new.getvelocityY() == velocityY
	 */
	private void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	/**
	 * This variable contains the current vertical velocity of this Mazub
	 */
	private double velocityY;
	
	/**
	 * Return accelerationY of this Mazub
	 * 	accelerationY expresses the current vertical acceleration of this Mazub
	 */
	@Basic @Override
	public double getAccelerationY() {
		return accelerationY;
	}

	/**
	 * 
	 * @param 	accelerationY
	 * 			the new accelerationY for this Mazub
	 * @post 	The accelerationY of this Mazub is equal to the given accelerationY
	 * 			| new.getAccelerationY() == accelerationY
	 */
	private void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}
	
	/**
	 * this variable contains the current vertical acceleration for this Mazub
	 */
	private double accelerationY;
	
	/**
	 * Returns the right sprite for the current movements
	 */
	@Override
	public Sprite getCurrentSprite() {
		
		// for all moves to the right
		if (this.isMovingRight()) {
			if (this.isJumping())
				setSpriteIndex(4);
			else if (this.isDucking())
				setSpriteIndex(6);
			else {
				setSpriteIndex(spritesMovingRightNormal());
				while (getSpriteTimer() >= 0.075) {
					setSpriteIndex(spritesMovingRightNormal());
				}
			}
		}
		
		// for all moves to the left
		else if (this.isMovingLeft()) {
			if (this.isJumping())
				setSpriteIndex(5);
			else if (this.isDucking())
				setSpriteIndex(7);
			else {
				setSpriteIndex(spritesMovingLeftNormal());
				while (getSpriteTimer() >= 0.075) {
					setSpriteIndex(spritesMovingLeftNormal());
				}
			}
		}
		
		// if the character is Ducking
		else if(this.isDucking()){
			if ((this.lastMoveDirection == Motion.RIGHT_AND_DUCKING) 
					&&(getSpriteTimer() - this.lastMoveTimer < 1)) {
				setSpriteIndex(6);
			}
			else if ((this.lastMoveDirection == Motion.LEFT_AND_DUCKING) 
					&&(getSpriteTimer() - this.lastMoveTimer < 1)) {
				setSpriteIndex(7);
			}
			else {
				setSpriteIndex(1);
			}
		}
				
		// if the character is not moving
		else {
			if (((this.lastMoveDirection == Motion.RIGHT) || 
					(this.lastMoveDirection == Motion.RIGHT_AND_DUCKING)) 
					&&(getSpriteTimer() - this.lastMoveTimer < 1))
				setSpriteIndex(2);
			else if (((this.lastMoveDirection == Motion.LEFT)|| 
					(this.lastMoveDirection == Motion.LEFT_AND_DUCKING)) 
					&& (getSpriteTimer() - this.lastMoveTimer < 1))
				setSpriteIndex(3);
			else
				setSpriteIndex(0);
		}
		
		return getSprites()[this.getSpriteIndex()];
	}
	
	/**
	 * Return the number of the sprite for the right moving animation
	 * 		when this Mazub is moving to the right, the function will return every 75 ms an other sprite index
	 * 		so that it will look like this Mazub walks to the right 
	 * @effect 	the value of the timer will set to the value of the timer minus 75 ms
	 * 			|setTimer(getTimer() - 0.075);
	 */
	private int spritesMovingRightNormal() {
		if ((this.getSpriteIndex() > 8 + this.getAmountSpritesForMovement()) || (this.getSpriteIndex() < 8))
			return 8;
		if (this.getSpriteTimer() >= 0.075){
			this.setSpriteTimer(getSpriteTimer() - 0.075);
			if ((this.getSpriteIndex() == 8 + this.getAmountSpritesForMovement()))
				return 8;
			else
				return this.getSpriteIndex() + 1;
		}
		return this.getSpriteIndex();
	}
	
	/**
	 * Return the number of the sprite for the left moving animation
	 * 		when this Mazub is moving to the left, the function will return every 75 ms an other sprite index
	 * 		so that it will look like this Mazub walks to the left
	 * @effect 	the value of the timer will set to the value of the timer minus 75 ms
	 * 			|setTimer(getTimer() - 0.075); 
	 */
	private int spritesMovingLeftNormal() {
		if ((this.getSpriteIndex() > 9 + getAmountSpritesForMovement() * 2) 
				|| this.getSpriteIndex() < 9 + this.getAmountSpritesForMovement())
			return 9 + getAmountSpritesForMovement();
		if (this.getSpriteTimer() >= 0.075) {
			this.setSpriteTimer(this.getSpriteTimer() - 0.075);
			if ((this.getSpriteIndex() == 9 + getAmountSpritesForMovement() * 2))
				return 9 + getAmountSpritesForMovement();
			else
				return this.getSpriteIndex() + 1;
		}
		return getSpriteIndex();
	}	
	
	/**
	 * 
	 * @param 	direction
	 * 			the direction in which this Mazub needs to start moving
	 * @pre		The given direction needs to be RIGHT or LEFT
	 * 			|direction == Direction.RIGHT || direction == Direction.LEFT
	 * @pre		If the direction is RIGHT, the rightkey can not be pressed already
	 * 			|if (direction == Direction.RIGHT) then
	 * 			|	!isRightKeyPressed()
	 * @pre		If the direction is LEFT, the leftkey can not be pressed already
	 * 			|if (direction == Direction.LEFT) then
	 * 			|	!isLeftKeyPressed() 
	 * @effect 	if the given direction is RIGHT, the horizontal velocity will be set to the 
	 * 				initial horizontal velocity
	 * 				and the acceleration will be equal to the initial horizontal acceleration
	 * 			|if (direction == Direction.RIGHT) then
	 * 			|	setVelocityX(getInitialHorizontalVelocity())
				|	setAccelerationX(getInitialHorizontalAcceleration())
	 * @effect 	if the given direction is LEFT, the horizontal velocity will be set to 
	 * 				the negative of the initial horizontal velocity
	 * 				and the acceleration will be set to the initial horizontal acceleration
	 * 			|if (direction == Direction.LEFT) then
	 * 			|	setVelocityX(getInitialHorizontalVelocity()*(-1))
	 * 			|	setAccelerationX(getInitialHorizontalAcceleration())				
	 */
	public void startMove(Motion direction) {
		assert(direction == Motion.RIGHT || direction == Motion.LEFT);
		if (direction == Motion.RIGHT) {
			assert(!isRightKeyPressed());
			setRightKeyPressed(true);
			setVelocityX(getInitialHorizontalVelocity());
			setAccelerationX(getInitialHorizontalAcceleration());
		} else if (direction == Motion.LEFT){
			assert(!isLeftKeyPressed());
			setLeftKeyPressed(true);
			setVelocityX(getInitialHorizontalVelocity()*-1);
			setAccelerationX(getInitialHorizontalAcceleration());
		}
	}


	/**
	 * Let this Mazub jump
	 * 	@throws IllegalStateException
	 * 			if the up key is already pressed
	 * 			|isUpKeyPressed()
	 *  @pre 	the up key can not be pressed already
	 *  		|!isUpKeyPressed()
	 *  @effect	the vertical velocity will be set to 8 
	 *  			and the vertical acceleration will be set to minus ten
	 * 			|setVelocityY(8)
	 * 			|setAccelerationY(-10) 
	 */
	public void startJump() throws IllegalStateException {
		if (isUpKeyPressed()){
			throw new IllegalStateException();
		}
		if(isOnSolidGround() || isOnGameObject()){
			setVelocityY(8);
			setAccelerationY(-10);
		}
		setUpKeyPressed(true);		
	}
	
	/**
	 * Return the initial Horizontal acceleration of this Mazub
	 */
	private final double getInitialHorizontalAcceleration() {
		return 0.9;
	}
	
	/**
	 * Stops Mazub's horizontal movement and stores the direction of its last movement
	 * @param 	direction
	 * 			the direction in which mazub needs to be stop moving
	 * @pre		if the given direction is needs to be Direction.LEFT or Direction.RIGHT
	 * 			|((direction == Direction.RIGHT) || (direction == Direction.LEFT))
	 * @pre		if the stop direction equals RIGHT, the right key needs to be pressed
	 * 			|if ( direction == Direction.RIGHT ) then
	 * 			|	isRightKeyPressed()
	 * @pre		if the stop direction equals LEFT, the left key needs to be pressed
	 * 			|if ( direction == Direction.LEFT ) then
	 * 			|	isLeftKeyPressed() 
	 * @effect	if the last movement was to the right and this Mazub is not ducking, 
	 * 				then the lastMoveDirection is set to Direction.RIGHT
	 * 			| if isMovingRight() then
	 * 			|	if not isDucking() then
	 * 			|		setLastMoveDirection(Direction.RIGHT)
	 * @effect	if the last movement was to the right, and this Mazub is ducking, 
	 * 				then the lastMoveDirection is set to Direction.RIGHT_AND_DUCKING
	 * 			| if isMovingRight() then
	 * 			|	if isDucking() then
	 * 			|		setLastMoveDirection(Direction.RIGHT_AND_DUCKING)
	 * @effect	if the last movement was to the left, and this Mazub is not ducking, 
	 * 				then the lastMoveDirection equals Direction.LEFT
	 * 			| if isMovingLeft() then
	 * 			|	if not isDucking() then
	 * 			|		setLastMoveDirection(Direction.LEFT)
	 * @effect	if the last movement was to the left, and this Mazub is ducking, 
	 * 				then the lastMoveDirection is set to Direction.LEFT_AND_DUCKING
	 * 			| if isMovingLeft() then
	 * 			|	if isDucking() then
	 * 			|		setLastMoveDirection(Direction.LEFT_AND_DUCKING)
	 * @post 	if Mazub stops moving left and is moving to the left at this moment, 
	 * 				the velocityX equals zero, accelerationX equals zero 
	 * 				and the lastMoveTimer equals to the current value of timer
	 * 			| new.getLastMoveTimer() == getTimer() && new.getVelocityX() == 0 &&
	 * 			| 	new.getAccelerationX() == 0
	 * @post 	if Mazub stops moving right and is moving to the right at this moment, 
	 * 				the velocityX equals zero, accelerationX equals zero 
	 * 				and the lastMoveTimer equals to the current value of timer
	 * 			| new.getLastMoveTimer() == getTimer() && new.getVelocityX() == 0 &&
	 * 			| 	new.getAccelerationX() == 0
	 */
	public void endMove(Motion direction){
		assert ((direction == Motion.RIGHT) || (direction == Motion.LEFT));
		if ( direction == Motion.RIGHT ){
			assert(isRightKeyPressed());
			setRightKeyPressed(false);
			if (isMovingRight()){
				if (isDucking())
					this.setLastMoveDirection(Motion.RIGHT_AND_DUCKING);
				else
					this.setLastMoveDirection(Motion.RIGHT);	
				setVelocityX(0);
				setAccelerationX(0);
				this.setLastMoveTimer(getSpriteTimer());
				if(isLeftKeyPressed()){
					setVelocityX(getInitialHorizontalVelocity()*-1);
					setAccelerationX(getInitialHorizontalAcceleration());
				}
			}
		}
		if ( direction == Motion.LEFT ){
			assert(isLeftKeyPressed());
			setLeftKeyPressed(false);
			if (isMovingLeft()){
				if (isDucking())
					this.setLastMoveDirection(Motion.LEFT_AND_DUCKING);
				else
					this.setLastMoveDirection(Motion.LEFT);
				setVelocityX(0);
				setAccelerationX(0);
				this.setLastMoveTimer(getSpriteTimer());
				if(isRightKeyPressed()){
					setVelocityX(getInitialHorizontalVelocity());
					setAccelerationX(getInitialHorizontalAcceleration());
				}
			}
		}
	}
	
	/**
	 * Return lastMoveDirection of this Mazub
	 * 		lastMoveDirection indicates the direction of the last move of this Mazub
	 */
	@Basic
	private Motion getLastMoveDirection() {
		return lastMoveDirection;
	}

	/**
	 * @param 	lastMoveDirection
	 * 			the new direction in which this Mazub has last moved
	 * @pre		lastMoveDirection equals Direction.RIGHT or Direction.LEFT or
	 * 				Direction.RIGHT_AND_DUCKING or Direction.LEFT_AND_DUCKING
	 * 			| lastMoveDirection == Direction.RIGHT || lastMoveDirection == Direction.LEFT ||
	 * 			| 	lastMoveDirection == Direction.RIGHT_AND_DUCKING || lastMoveDirection == Direction.LEFT_AND_DUCKING
	 * @post 	the new lastMoveDirection of this Mazub will equal to lastMoveDirection
	 * 			| new.getLastMoveDirection() = lastMoveDirection
	 */
	private void setLastMoveDirection(Motion lastMoveDirection) {
		assert((lastMoveDirection == Motion.RIGHT) || (lastMoveDirection == Motion.LEFT) 
				|| (lastMoveDirection == Motion.RIGHT_AND_DUCKING) 
				|| (lastMoveDirection == Motion.LEFT_AND_DUCKING) );
		this.lastMoveDirection = lastMoveDirection;
	}
	
	/**
	 * This variable contains the last direction in which this Mazub has moved
	 */
	private Motion lastMoveDirection;

	/**
	 * Return the value of timer
	 */
	@Basic
	private double getLastMoveTimer() {
		return lastMoveTimer;
	}

	/**
	 * 
	 * @param 	time
	 * 			the new time for this Mazub's lastMoveTimer
	 * @pre		time is greater or equal to zero
	 * @post	lastMoveTimer of this Mazub will be equal to the given time
	 * 			| new.getLastMoveTimer() = time
	 * 			
	 */
	private void setLastMoveTimer(double time) {
		assert(time >= 0);
		this.lastMoveTimer = time;
	}
	
	/**
	 * This variable contains the time since when this Mazub did his last move
	 */
	private double lastMoveTimer;

	/**
	 * Ends this Mazub's jump
	 * @throws 	IllegalStateException
	 * 				if the up key was not pressed
	 * 			| !isUpKeyPressed()
	 * @post	if velocityY is greater than 0, velocityY will be zero
	 * 			| if getVelocityY() > 0 then
	 * 			|	new.getVelocityY == 0
	 */
	public void endJump() throws IllegalStateException {
		if(!isUpKeyPressed()){
			throw new IllegalStateException();
		}
		if (this.getVelocityY() > 0) {
			this.setVelocityY(0);
		}
		setUpKeyPressed(false);
	}
	
	/**
	 * Given a time this methode will update the location, velocity and acceleration of this Mazub	 * 
	 * @param 	dt
	 * 			the elapsed time in seconds
	 * @throws 	IllegalArgumentException
	 * 			if seconds is less than zero or if seconds is equal or bigger than 0.2
	 * 			| (seconds < 0 || seconds >= 0.2)
	 * @effect	the timer will be added with the given dt using addToTimer() 
	 * 				and the given dt as parameter
	 * 			| addToTimer(dt)
	 * @effect 	if mazub is not dead the velocity and location will be updated
	 * 			|if(!isDead()) then
	 * 			|	updateLocationAndVelocity(dt)
	 * @effect	if mazub is not death and this mazub is immune for less than 0.6 seconds
	 * 				then the immunity timer will be add with dt
	 * 			|if(!isDead() && isImmune() && getImmunityTimer() < 0.6) then
	 * 			|	setImmunityTimer(getImmunityTimer() + dt)
	 * @effect	if mazub is not dead and this mazub is immune for 0.6 or more seconds
	 * 				then the immunity timer will be set to zero and this mazub 
	 * 					will not be immune any more
	 * 			|if(!isDead() && isImmune() && getImmunityTimer() >= 0.6) then
	 * 			|	setImmunity(false)
	 * 			|	setImmunityTimer(0) 
	 * @effect	if mazub is not dead and is ducking and wants to stop ducking 
	 * 				and can stop ducking
	 * 				then he stops ducking and he wants to stop ducking
	 * 			|if(!isDead() && getWantToStopDucking() && isDucking() && canStopDucking()) then
	 * 			|	setDucking(false)
	 * 			|	setWantToStopDucking(false)
	 * @effect	if mazub is dead then dt will be add to the deadtimer
	 * 			|if(isDead()) then
	 * 			|	setTimeDead(getTimeDead() + dt)
	 * @effect	if mazub is dead for 0.6 seconds or more than he will be terminated
	 * 			|if(isDead() && getTimeDead() >= 0.6) then
	 * 			|	terminate()
	 */
	public void advanceTime(double dt) throws IllegalArgumentException {
		if (dt < 0 || Util.fuzzyGreaterThanOrEqualTo(dt, 0.2)){
			throw new IllegalArgumentException();
		}
		this.addToTimer(dt);
		if(!isDead()){
			if (isImmune() && getImmunityTimer() < 0.6) {
				setImmunityTimer(getImmunityTimer() + dt);
			} else if (isImmune()) {
				setImmunity(false);
				setImmunityTimer(0);
			}
			updateLocationAndVelocity(dt);			
			if (getWantToStopDucking() && isDucking() && canStopDucking()) {
				setDucking(false);
				setWantToStopDucking(false);
			}
		} else {
			setTimeDead(getTimeDead() + dt);
			if(Util.fuzzyGreaterThanOrEqualTo(getTimeDead(), 0.6)){
				terminate();
			}
		}
	}
	/**
	 * @param 	dt
	 * 			the elapsed time in seconds
	 * @effect	If this Mazub is Moving to the left, updateLocation() will be used
	 * 				to update the location, with the given seconds as parameter
	 * 				and the negative of the value of getAcceleration() as second parameter
	 * 				also the velocityX will be updated using the function getAcceleration()
	 * 				with the given seconds as parameter	and the negative of the
	 * 				value of getAcceleration() as second parameter
	 * 			| if isMovingLeft() then
	 * 			|	updateLocation(seconds, (-1)*getAccelerationX())
	 * 			|	updateVelocityX(seconds, (-1)*getAccelerationX())
	 * @effect	If this Mazub is Moving to the right, updateLocation() will be used
	 * 				to update the location, with the given seconds as parameter
	 * 				and the value of getAcceleration() as second parameter
	 * 				also the velocityX will be updated using the function getAcceleration()
	 * 				with the given seconds as parameter	and the value of getAcceleration() 
	 * 				as second parameter
	 * 			| if !isMovingLeft() then
	 * 			|	updateLocation(seconds, getAccelerationX())
	 * 			|	updateVelocityX(seconds, getAccelerationX())
	 * @effect	Also the vertical velocity and acceleration will be updated using the methode
	 * 				updateVelocityYandAcceleration() with the given seconds as parameter
	 * 			| updateVelocityYAndAccelerationY(seconds);
	 */
	@Override
	protected void advanceTimeCollisionDetect(double dt){	
		updateLocation(dt);		
		updateVelocityX(dt);
		updateVelocityYAndAccelerationY(dt);
		handleCollisionPlant();
		handleCollisionSlime();
		handleCollisionShark();
		handleMagma(dt);
		handleWater(dt);
		
	}

	
	/**
	 * 
	 * this methode update's the vertical velocity and checks the accelereation
	 *		given a time in seconds
	 * @param 	seconds
	 * 			the seconds to compute the new velocity
	 * @post	seconds needs to be bigger or equal to zero
	 * 			| seconds >= 0
	 * @effect 	if the Y coordinate of this Mazub is greater than zero
	 * 				the velocityY will be set to the new vertical velocity calculated with
	 * 				the given seconds and acceleration
	 * 			| if getLocationY > 0 then
	 * 			|	setVelocityY(getVelocityY() + getAccelerationY()*seconds)
	 * @effect 	if the Y coordinate of this Mazub is equal or lower than zero
	 * 				the velocityY will be set to zero 
	 * 				and the accelerationY will be set to zero
	 * 			| if getLocationY <= 0 then
	 * 			|	setVelocityY(0)
	 * 			|	setAccelerationY(0)
	 */
	private void updateVelocityYAndAccelerationY(double seconds) {
		assert(seconds >= 0);
		if (isOnSolidGround()) {
			setVelocityY(0);
			setAccelerationY(0);
		} else if(isOnGameObject()){
			setVelocityY(0);
		} else {
			double velocityY = getVelocityY() + getAccelerationY()*seconds;
			setVelocityY(velocityY);
			setAccelerationY(-10);
		}
	}

	/**
	 * 
	 * @param 	seconds
	 * 			The seconds to compute the new velocity.
	 * @param 	accelerationX
	 * 			The horizontal acceleration.
	 * @pre		seconds needs to be bigger or equal to zero
	 * 			| seconds >= 0
	 * @effect	If the calculated velocity is smaller than the maximum horizontal velocity,
	 * 				and not smaller than minus the maximum horizontal velocity, 
	 * 				set the new velocity to the calculated velocity.
	 *				If the calculated velocity is smaller than minus the maximum horizontal velocity, 
	 *				set the new velocity to minus the maximum horizontal velocity.
	 *				If the calculated velocity is greater than the maximum horizontal velocity, 
	 *				set the new velocity to the maximum horizontal velocity.
	 * 			| if ((getVelocityX() + accelerationX*seconds) < getMaximumHorizontalVelocity()) then
	 * 			|	if ((getVelocityX() + accelerationX*seconds) < -getMaximumHorizontalVelocity()) then
	 * 			|		setVelocityX(-getMaximumHorizontalVelocity)
	 * 			|	else then
	 * 			|		setVelocityX(getVelocityX() + accelerationX*seconds)
	 * 			| else then
	 * 			|	setVelocityX(getMaximumHorizontalVelocity())
	 */
	private void updateVelocityX(double seconds) {
		assert(seconds >= 0);
		double velocityX = getVelocityX() + getActualAccelerationX()*seconds;
		if (velocityX < getMaximumHorizontalVelocity()){
			if (velocityX < -getMaximumHorizontalVelocity()){
				setVelocityX(-getMaximumHorizontalVelocity());
			} else {
				setVelocityX(velocityX);
			}
		} else{
			setVelocityX(getMaximumHorizontalVelocity());
		}
	}

	/**
	 * Updates the location of this Mazub given a certain amount of seconds and a horizontal acceleration.
	 * @param 	seconds
	 * 			The seconds to compute the new position.
	 * @param 	accelerationX
	 * 			The horizontal acceleration.
	 * @pre		seconds needs to be positive.
	 * 			| seconds >= 0
	 * @effect	sets isOnGameObject to false
	 * 			|setIsOnGameObject(false)
	 * @effect if the new calculated location is valid in the world, 
	 * 				there will be checked if there are no collisions with terrain or 
	 * 				other objects and the new location will be saved
	 * 			|location = calculateLocation(seconds);	
	 *			|if(locationIsValidInWorld((int)location[0], (int)location[1])){
	 *			|		calculateLocationCollisionTerrain(seconds, location);
	 *			|		calculateLocationCollisionObjects(location);
	 *			|		setLocation(location);
	 * 
	 */
	private void updateLocation(double seconds) {
		assert (seconds >= 0);
		setIsOnGameObject(false);
		double[] location = calculateLocation(seconds);	
		if(locationIsValidInWorld((int)location[0], (int)location[1])){
			calculateLocationCollisionTerrain(seconds, location);
			calculateLocationCollisionObjects(location);
			setLocation(location);
		}
	}

	
	
	/**
	 * Return the size of the current sprite
	 * @return	the width and height of the current sprite
	 * 			| return size[this.getCurrentSprite().getHeight(), this.getCurrentSprite().getWidth()]
	 * @throws 	ModelException
	 * 			if the current sprite is a null pointer
	 * 			| this.getCurrentSprite() == null
	 */
	public int[] getSize() throws ModelException {
		int[] size = new int[2];
		Sprite sprite = this.getCurrentSprite();
		if (sprite == null)
			throw new ModelException("No valid Sprite given");
		size[0] = sprite.getWidth();
		size[1] = sprite.getHeight();
		return size;
	}
	
	/**
	 * Returns whether Mazub is moving right or not.
	 * @return 	true when the horizontal velocity is greater than zero, false otherwise.
	 * 			| if getVelocityX() > 0 then
	 * 			|	return true
	 * 			| else then
	 * 			| 	return false
	 */
	private boolean isMovingRight() {
		if (this.getVelocityX() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns whether Mazub is moving left or not.
	 * @return 	true when the horizontal velocity is less than zero, false otherwise.
	 * 			| if getVelocityX() < 0 then
	 * 			|	return true
	 * 			| else then
	 * 			| 	return false
	 */
	private boolean isMovingLeft() {
		if (this.getVelocityX() < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns whether Mazub is jumping.
	 * @return	true when the vertical acceleration is not zero, false otherwise.
	 * 			| if getAccelerationY() != 0 then 
	 * 			|	return true
	 * 			| else then 
	 * 			|	return false
	 */
	private boolean isJumping() {
		if (this.getAccelerationY() != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param 	spriteIndex
	 * 			The new spriteIndex for this Mazub
	 * @post	The new spriteIndex for this Mazub is equal to the given spriteIndex.
	 * 			| new.getSpriteIndex() == spriteIndex
	 */
	private void setSpriteIndex(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}
	
	/**
	 * Returns the current spriteIndex for this Mazub.
	 */
	@Basic
	private int getSpriteIndex() {
		return this.spriteIndex;
	}
	
	/**
	 * This variable contains the number of the current sprite for this Mazub.
	 */
	private int spriteIndex;
	
		
	/**
	 * 
	 * @param 	ducking
	 * 			The new ducking state for this Mazub.
	 * @post	The new ducking state of this Mazub is equal to ducking.
	 * 			| new.isDucking() == ducking
	 */
	private void setDucking(boolean ducking){
		this.ducking = ducking;				
	}
	
	/**
	 * Returns whether Mazub is ducking.
	 */
	@Basic
	public boolean isDucking() {
		return this.ducking;
	}
	
	/**
	 * This boolean indicates whether Mazub is ducking or not.
	 */
	private boolean ducking;	
	
	/**
	 * this methode will start this Mazub with ducking
	 * @throws	IllegalStateException
	 * 			if the down key was already pushed
	 * 			| isDownKeyPressed()
	 * @effect 	the ducking state of this Mazub will be set to true
	 * 			|setDucking(true);
	 */
	public void startDucking() throws IllegalStateException {
		if (isDownKeyPressed())
			throw new IllegalStateException();
		setDucking(true);
		setDownKeyPressed(true);
	}

	/**
	 * this methode will stop this Mazub with ducking
	 * @throws	IllegalStateException
	 * 			if the down key is already pushed
	 * 			| !isDownKeyPressed()
	 * @effect 	sets that this Mazub wants to stop ducking
	 * 			| setWantToStopDucking(true)
	 */
	public void endDucking() throws IllegalStateException{
		if (!isDownKeyPressed())
			throw new IllegalStateException();
		setWantToStopDucking(true);	
		setDownKeyPressed(false);
	}
	
	/**
	 * 
	 * @param value
	 * @post	...
	 * 			| new.getWantToStopDucking() == value
	 */
	private void setWantToStopDucking(boolean value) {
		this.wantToStopDucking = value;
	}
	
	/**
	 *	Returns if this mazub wants to stop ducking or not
	 */
	@Basic
	private boolean getWantToStopDucking() {
		return this.wantToStopDucking;
	}
	
	/**
	 * This variable contains whether this Mazub wants to stop ducking.
	 */
	private boolean wantToStopDucking;
	
	/**
	 * Returns the current value of the timer.
	 */
	@Basic
	private double getSpriteTimer() {
		return spriteTimer;
	}

	/**
	 * Sets the timer at the given value.
	 * @param 	time
	 * 		  	The new time for this timer.
	 * @post	The timer of this Mazub is equal to the given time.
	 * 			| new.getTimer() == time
	 */
	private void setSpriteTimer(double time) {
		this.spriteTimer = time;
	}
	
	/**
	 * Adds the timer with a given value.
	 * @param 	time
	 * 		  	the time that needs to be added to the timer
	 * @post	if the current value of the timer plus time is less or equal to Double.MAX_VALUE
	 * 				the new timer will equal the current timer plus the given time
	 * 			| new.getTimer() == this.getTimer() + time
	 * @post	if the current value of the timer plus time is greater than Double.MAX_VALUE
	 * 				the new timer will equal zero
	 * 			| new.getTimer() == 0
	 */
	private void addToTimer(double time) {
		setSpriteTimer(getSpriteTimer()+time);
	}

	/**
	 * This variable contains the current time for this Mazub.
	 */
	private double spriteTimer;
		
	/**
	 * Return the amount of sprites available for a movement to the left or right
	 */
	@Basic
	private final int getAmountSpritesForMovement() {
		return amountSpritesForMovement;
	}
	
	/**
	 * This variable contains the amout of sprites available 
	 * 		for a movement to the left or right
	 */
	private final int amountSpritesForMovement;

	

	/**
	 * Sets the immunity of this Mazub to the given status
	 * @param status
	 * @post	the new immunity is equal to the given status
	 * 			| new.getImmunity() == status 
	 */
	public void setImmunity(boolean status) {
		this.immunity = status;
	}
	
	/**
	 * Returns whether this Mazub is immune.
	 */
	@Basic
	public boolean isImmune() {
		return this.immunity;
	}
	
	/**
	 * This variable contains wether this Mazub is immune
	 */
	private boolean immunity;
	
	/**
	 * Returns the value of the immunty timer for this Mazub
	 */
	private double getImmunityTimer() {
		return this.immunityTimer;
	}
	
	/**
	 * @param value
	 * 			the new time for this immunty timer
	 * @post 	the new immunty timer will equal the given immunity time
	 * 			|new.getImmunityTimer() = value
	 */
	private void setImmunityTimer(double value) {
		this.immunityTimer = value;
	}
	
	/**
	 * This variable contains the immunty timer for this mazub
	 */
	private double immunityTimer;
	
	/**
	 * This function returns whether this Mazub is on Solid ground or not and has no positive vertical velocity
	 * @return true if mazub is on solidground and has no positive vertical velocity, else false will be returned
	 * 			| result == (hasCollisionBottom((int)getLocationX(), (int)getLocationY()-1) && !(getVelocityY() > 0))
	 */
	private boolean isOnSolidGround(){
		return (hasCollisionBottom((int)getLocationX(), (int)getLocationY()-1) && !(getVelocityY() > 0));
	}	
	
	/**
	 * @return Returns if this mazub can stop ducking or not
	 * 			| result == !(getWorld().detectGeologicalFeature((int) getLocationX()+1, 
	 * 			|				(int) getLocationY()+2, (int) getLocationY() + 
	 * 			|				getSprites()[0].getWidth()-2, (int) getLocationY() +
	 * 			|				getSprites()[0].getHeight()-2, 1))
	 */	
	private boolean canStopDucking() {
		int newEndY = (int) getLocationY() + getSprites()[0].getHeight();
		int newEndX = (int) getLocationX() + getSprites()[0].getWidth();
		
		return !(getWorld().detectGeologicalFeature((int) getLocationX()+1, (int) getLocationY()+2, newEndX-2, newEndY-2, 1));
	}
	
	/**
	 * Checks wether the given world is valid or not
	 * @param 	world
	 * 			the world which needs to be checked
	 * @return	true if the given mazub is no null pointer and if the mazub of that world is this mazub
	 * 			| result == ((world != null) && (world.getMazub()==this));
	 */
	@Override
	protected boolean isValidWorld(World world){
		return (world != null) && (world.getMazub()==this);
	}

	/**
	 * @effect if this mazub had a collision with a plant, this collision will be handled
	 * 			|foreach (plant in getWorld().collisionPlants((int) getLocationX(), 
	 * 			|	(int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(),
	 * 			|	(int) getLocationY()+getCurrentSprite().getHeight()))
	 * 			|		handleCollisionPlant(plant)
	 * 			|		if(plant.isTerminated()) then
	 * 			|			getWorld().removePlant(plant)
	 * 			
	 */
	private void handleCollisionPlant() {
		Collection<Plant> collection = getWorld().collisionPlants((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), (int) getLocationY()+getCurrentSprite().getHeight());
		for (Plant plant : collection) {
			handleCollisionPlant(plant);
			if(plant.isTerminated()){
				getWorld().removePlant(plant);
			}
		}
	}
	
	/**
	 * @effect 	adds the hitpoints with 50 and delets the plant if the current 
	 * 				amount of hitpoints is lower then 500
	 * 			|if (getHitPoints() < 500 && !plant.isTerminated()) then
	 * 			|	setHitPoints(getHitPoints() + 50)
	 * 			|	plant.hadCollisionMazub()
	 */
	void handleCollisionPlant(Plant plant) {
		if (getHitPoints() < 500 && !plant.isTerminated()) {
			setHitPoints(getHitPoints() + 50);
			// remove plant
			plant.hadCollisionMazub();
		}
	}
	
	/**
	 * @effect 	if this mazub is not immune and there are collisions with slimes in the left,
	 * 				 right or up perimeter these collisions will be handeld
	 * 			|if (!isImmune()) then
	 * 			|	foreach(slime in getWorld().collisionSlimesInPerimeterExceptBottom(
	 * 			|		(int) getLocationX(), (int) getLocationY(), 
	 * 			|		(int) getLocationX()+getCurrentSprite().getWidth(), 
	 * 			|		(int) getLocationY()+getCurrentSprite().getHeight())) then
	 * 			|			hadCollisionSlime()
	 * 			|			slime.hadCollisionMazub()
	 * 			|			if(slime.isTerminated()) then
	 * 			|				getWorld().removeSlime(slime)
	 * @effect if this mazub had collisions with slimes in the bottom perimeter, 
	 * 			these will be handled
	 * 			|	foreach(slime in getWorld().collisionSlimesInBottomPerimeter(
	 * 			|		(int) getLocationX(), (int) getLocationY(), 
	 * 			|		(int) getLocationX()+getCurrentSprite().getWidth(), 
	 * 			|		(int) getLocationY()+getCurrentSprite().getHeight()))
	 * 			|			if (!slime.isDead()) then
	 * 			|				slime.hadCollisionMazub()
	 * 			|			if(slime.isTerminated()) then
	 * 			|				getWorld().removeSlime(slime)
	 */
	private void handleCollisionSlime() {
		Collection<Slime> collection = getWorld().collisionSlimesInPerimeterExceptBottom((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), (int) getLocationY()+getCurrentSprite().getHeight());
		if (!isImmune()) {
			for (Slime slime : collection) {
				hadCollisionSlime();				
				slime.hadCollisionMazub();
				if(slime.isTerminated()){
					getWorld().removeSlime(slime);				
				}
			}
		}
		collection = getWorld().collisionSlimesInBottomPerimeter((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), (int) getLocationY()+getCurrentSprite().getHeight());
		for (Slime slime : collection) {
			if (!slime.isDead()) {
				slime.hadCollisionMazub();
			}
			if(slime.isTerminated()){
				getWorld().removeSlime(slime);
			}
		}
	}
	
	/**
	 * @effect 	lowers the hitpoints of this mazub with 50 points
	 * 			|setHitPoints(getHitPoints() - 50)
	 * @effect 	sets the immunty for this mazub on
	 * 			|setImmunity(true)
	 */
	void hadCollisionSlime(){
		setHitPoints(getHitPoints() - 50);
		setImmunity(true);	
	}
	
	/**
	 * @effect 	lowers the hitpoints of this mazub with 50 points
	 * 			|setHitPoints(getHitPoints() - 50)
	 * @effect 	sets the immunty for this mazub on
	 * 			|setImmunity(true)
	 */
	void hadCollissionShark(){
		setHitPoints(getHitPoints() - 50);
		setImmunity(true);
	}
	
	/**
	 * @effect 	if this mazub is not immune and there are collisions with sharks in the left,
	 * 				 right or up perimeter these collisions will be handeld
	 * 			|if (!isImmune()) then
	 * 			|	foreach(shark in getWorld().collisionSharksInPerimeterExceptBottom(
	 * 			|		(int) getLocationX(), (int) getLocationY(), 
	 * 			|		(int) getLocationX()+getCurrentSprite().getWidth(), 
	 * 			|		(int) getLocationY()+getCurrentSprite().getHeight())) then
	 * 			|			hadCollisionShark()
	 * 			|			shark.hadCollisionMazub()
	 * 			|			if(shark.isTerminated()) then
	 * 			|				getWorld().removeShark(shark)
	 * @effect if this mazub had collisions with sharks in the bottom perimeter, 
	 * 			these will be handled
	 * 			|	foreach(shark in getWorld().collisionSharksInBottomPerimeter(
	 * 			|		(int) getLocationX(), (int) getLocationY(), 
	 * 			|		(int) getLocationX()+getCurrentSprite().getWidth(), 
	 * 			|		(int) getLocationY()+getCurrentSprite().getHeight()))
	 * 			|			shark.hadCollisionMazub()
	 * 			|			if(shark.isTerminated()) then
	 * 			|				getWorld().removeShark(shark)
	 */
	private void handleCollisionShark() {
		Collection<Shark> collection = getWorld().collisionSharksInPerimetersExceptBottom((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), (int) getLocationY()+getCurrentSprite().getHeight());
		if (!isImmune()) {
			for (Shark shark : collection) {
				hadCollissionShark();
				shark.hadCollisionMazub();
				if(shark.isTerminated()){
					getWorld().removeShark(shark);				
				}
			}
		}
		collection = getWorld().collisionSharksInBottomPerimeter((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), (int) getLocationY()+getCurrentSprite().getHeight());
		for (Shark shark : collection) {
			shark.hadCollisionMazub();
			if(shark.isTerminated()){
				getWorld().removeShark(shark);
			}
		}
	}
	
	/**
	 * @effect 	removes the world for this mazub
	 * 			|removeWorld()
	 * @effect 	sets this mazub terminated
	 * 			|setTerminated(true)
	 */
	@Override
	void terminate(){
		removeWorld();
		setTerminated(true);
	}

	/**
	 * Returns the max hitpoints for this Mazub
	 * @result 	Returns the max hitpoints for this Mazub
	 * 			|result == 500
	 */
	@Override
	int getMaxHitPoints() {
		return 500;
	}



	/**
	 * Returns the actual acceleration
	 * @return 	...
	 * 			|if (!isMovingLeft()) then
	 * 			|	result == getAccelerationX() * -1;
	 * @return 	...
	 * 			|if (!isMovingLeft()) then
	 * 			|	result ==  getAccelerationX()
	 */
	@Override
	double getActualAccelerationX() {
		if (this.isMovingLeft())
			return getAccelerationX() * -1;
		return  getAccelerationX();
	}
	
	/**
	 * Returns whether the right key is pressed or not
	 */
	private boolean isRightKeyPressed() {
		return isRightKeyPressed;
	}

	/**
	 * @param isRightKeyPressed
	 * 			the current pressed status of the right key
	 * @post 	isRightKeyPressed will equal the given value
	 * 			|new.isRightKeyPressed() == isRightKeyPressed
	 */
	private void setRightKeyPressed(boolean isRightKeyPressed) {
		this.isRightKeyPressed = isRightKeyPressed;
	}

	/**
	 * This variable contains the current pressed status of the right key
	 */
	private boolean isRightKeyPressed;

	/**
	 * Returns whether the left key is pressed or not
	 */
	private boolean isLeftKeyPressed() {
		return isLeftKeyPressed;
	}

	/**
	 * @param isLeftKeyPressed
	 * 			the current pressed status of the left key
	 * @post 	isLeftKeyPressed will equal the given value
	 * 			|new.isLeftKeyPressed() == isLeftKeyPressed
	 */
	private void setLeftKeyPressed(boolean isLeftKeyPressed) {
		this.isLeftKeyPressed = isLeftKeyPressed;
	}

	/**
	 * This variable contains the current pressed status of the left key
	 */
	private boolean isLeftKeyPressed;
	
	/**
	 * Returns whether the up key is pressed or not
	 */
	private boolean isUpKeyPressed() {
		return isUpKeyPressed;
	}

	/**
	 * @param isUpKeyPressed
	 * 			the current pressed status of the up key
	 * @post 	isUpKeyPressed will equal the given value
	 * 			|new.isUpKeyPressed() == isUpKeyPressed
	 */
	private void setUpKeyPressed(boolean isUpKeyPressed) {
		this.isUpKeyPressed = isUpKeyPressed;
	}

	/**
	 * This variable contains the current pressed status of the up key
	 */
	private boolean isUpKeyPressed;
	
	/**
	 * Returns whether the down key is pressed or not
	 */
	private boolean isDownKeyPressed() {
		return isDownKeyPressed;
	}

	/**
	 * @param isDownKeyPressed
	 * 			the current pressed status of the down key
	 * @post 	isDownKeyPressed will equal the given value
	 * 			|new.isDownKeyPressed() == isDownKeyPressed
	 */
	private void setDownKeyPressed(boolean isDownKeyPressed) {
		this.isDownKeyPressed = isDownKeyPressed;
	}

	/**
	 * This variable contains the current pressed status of the down key
	 */
	private boolean isDownKeyPressed;
	
	/**
	 * Returns the amount of hitpoints of this Mazub
	 */
	public int getHitpoints(){
		return super.getHitPoints();
	}

}


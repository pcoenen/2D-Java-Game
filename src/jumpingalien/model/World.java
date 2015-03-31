package jumpingalien.model;

/**
 * 
 * @author Pieter-Jan Coenen (1ste Bacherlor Informatica) en Stijn Caerts (1ste Bacherlor Informatica)
 * 
 * @invar	the tileSize is greater than zero
 * 			| getTileSize > 0;
 * @invar	the nbTilesX is greater than zero
 * 			| getNbTilesX > 0;
 * @invar	the nbTilesY is greater than zero
 * 			| getNbTilesY > 0;
 */
public class World  {
	/**
	 * @param tileSize
	 *            Length (in pixels) of a side of each square tile in the world
	 * @param nbTilesX
	 *            Number of tiles in the horizontal direction
	 * @param nbTilesY
	 *            Number of tiles in the vertical direction
	 * @param visibleWindowWidth
	 *            Width of the visible window, in pixels
	 * @param visibleWindowHeight
	 *            Height of the visible window, in pixels
	 * @param targetTileX
	 *            Tile x-coordinate of the target tile of the new world
	 * @param targetTileY
	 *            Tile y-coordinate of the target tile of the new world
	 * @throws	IllegalArgumentException
	 * 			the given tileSize, nbTilesX or nbTilesY is smaller or equal to zero
	 * 			| (tileSize <= 0) || (nbTilesX <= 0) || (nbTilesY <= 0)
	 */
	public World (int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) throws IllegalArgumentException {
		if ((tileSize <= 0) || (nbTilesX <= 0) || (nbTilesY <= 0))
			throw new IllegalArgumentException();
		
		this.tileSize = tileSize;
		this.nbTilesX = nbTilesX;
		this.nbTilesY = nbTilesY;
		this.geologicalFeatureOfTiles = new int[nbTilesX][nbTilesY];
		this.visibleWindowHeight = visibleWindowHeight;
		this.visibleWindowWidth = visibleWindowWidth;
		this.targetTileX = targetTileX;
		this.targetTileY = targetTileY;		
	}
	
	/**
	 * Return the world size as an array in pixels
	 * @return 	The size of the game world, in pixels, as an array of two
	 *         		elements: width (X) and height (Y), in that order.
	 *         	| {getNbTilesX() * getTileSize(), getNbTilesY() * getTileSize()}
	 */
	public int[] getWorldSizeInPixels(){
		int worldSize[] = new int[2];
		worldSize[0] = getNbTilesX() * getTileSize();
		worldSize[1] = getNbTilesY() * getTileSize();
		return worldSize;
	}
	
	/**
	 * Returns the length (in pixels) of a side of each square tile of this world
	 */
	public int getTileSize() {
		return tileSize;
	}
	
	/**
	 * This variable contains the length (in pixels) of a side of each square tile of this world
	 */
	private final int tileSize;
	
	/**
	 * Returns the number of tiles in the horizontal direction of this world
	 */
	private int getNbTilesX() {
		return nbTilesX;
	}
	/**
	 * this variable contains the number of tiles in the horizontal direction of this world
	 */
	private final int nbTilesX;
	
	/**
	 * Returns the number of tiles in the vertical direction of this world
	 */
	private int getNbTilesY() {
		return nbTilesY;
	}
	
	/**
	 * This variable contains the number of tiles in the vertical direction of this world
	 */
	private final int nbTilesY;
	
	/**
	 * Returns the width of the visible window, in pixels of this world
	 */
	private int getVisibleWindowWidth() {
		return visibleWindowWidth;
	}
	
	/**
	 * This variable contains the width of the visible window, in pixels of this world
	 */
	private final int visibleWindowWidth;
	
	/**
	 * Returns the height of the visible window, in pixels of this world
	 */
	private int getVisibleWindowHeight() {
		return visibleWindowHeight;
	}
	
	/**
	 * This variable contains the height of the visible window, in pixels of this world
	 */
	private final int visibleWindowHeight;
	
	/**
	 * 
	 * @return 	The pixel coordinates of the visible window, in the order
	 *         		left, bottom, right, top
	 *         	|{0, 0, getVisibleWindowWidth(), getVisibleWindowHeight()}
	 */
	public int[] getVisibleWindow(){
		int[] window = new int[4];
		window[0] = 0;
		window[1] = 0;
		window[2] = getVisibleWindowWidth();
		window[3] = getVisibleWindowHeight();
		return window;
	}
	
	/**
	 * Returns the tile x-coordinate of the target tile of this world
	 */
	private int getTargetTileX() {
		return targetTileX;
	}
	
	/**
	 * this variable contains the tile x-coordinate of the target tile of this world
	 */
	private final int targetTileX;
	
	/**
	 * Returns the tile y-coordinate of the target tile of this world
	 */
	private int getTargetTileY() {
		return targetTileY;
	}
	
	/**
	 * this variable contains the tile y-coordinate of the target tile of this world
	 */
	private final int targetTileY;
	
	/**
	 * Returns the bottom left pixel coordinate of the tile at the given tile position.
	 * @param tileX
	 * 			The x-position x_T of the tile
	 * @param tileY
	 * 			The y-position y_T of the tile
	 * @return An array which contains the x-coordinate and y-coordinate of the
	 *         bottom left pixel of the given tile, in that order.
	 *         | {tileX * getTileSize(), tileY * getTileSize()}
	 */
	public int[] getBottomLeftPixelOfTile(int tileX, int tileY) {
		int[] bottomLeftPixel = new int[2];
		bottomLeftPixel[0] = tileX * getTileSize();
		bottomLeftPixel[1] = tileY * getTileSize();
		return bottomLeftPixel;
	}
	
	/**
	 * Sets the given alien as the player's character in this world.
	 * @param alien
	 * 			The alien to be set as the player's character.
	 */
	public void setMazub(Mazub alien) {
		this.alien = alien;
	}
	
	/**
	 * This variable contains the player's character in this world.
	 */
	private Mazub alien;
	
	/**
	 * Returns the feature of the given tile
	 * @param 	tileX
	 *            The x-position x_T of the tile for which the feature needs to be returned
	 * @param 	tileY
	 *            The y-position y_T of the tile for which the feature needs to be returned  
	 * @pre		the given tile coordinate x_T and y_T needs to be valid
	 * 			|isValidTileCoordinate(tileX, tileY)
	 * @return 	The type of the tile with the given x-position x_T and y-position y_T ,
	 *         		where
	 *         		the value 0 is provided for an air tile
	 *         		the value 1 is provided for a solid ground tile
	 *            	the value 2 is provided for a water tile
	 *           	the value 3 is provided for a magma tile
	 */
	public int getGeologicalFeatureOfTile(int tileX, int tileY){
		assert(isValidTileCoordinate(tileX, tileY));
		return geologicalFeatureOfTiles[tileX][tileY];
	}
	
	/**
	 * 
	 * @param 	tileX
	 *            The x-position x_T of the tile for which the type needs to be
	 *            modified
	 * @param 	tileY
	 *            The y-position y_T of the tile for which the type needs to be
	 *            modified
	 * @param 	tileType
	 *            The new type for the given tile, where
	 *            the value 0 is provided for an air tile
	 *            the value 1 is provided for a solid ground tile
	 *            the value 2 is provided for a water tile
	 *            the value 3 is provided for a magma tile
	 * @post	the new geological feature of this tile equals the given tileType
	 * 			|new.getGeologicalFeatureOfTile(tileX, tileY) = tileType;
	 * @throws	IllegalArgumentException
	 * 			when the given tileType is less than 
	 */
	public void setGeologicalFeatureOfTile(int tileX, int tileY, int tileType) 
			throws IllegalArgumentException{
		if (! isValidTileType(tileType) || (! isValidTileCoordinate(tileX, tileY)))
			throw new IllegalArgumentException();
		this.geologicalFeatureOfTiles[tileX][tileY] = tileType;
	}
	
	/**
	 * Checks if a given tile type is valid
	 * @param 	tileType
	 * 			the tile type that needs to be checked 
	 * @return	true is the given tileType is between 0 and 3 (inclusive 0 and 3)
	 * 				otherwise returns false
	 * 			|((tileType < 0)||(tileType > 3))
	 */
	public boolean isValidTileType(int tileType){
		return ((tileType >= 0)||(tileType <= 3));
	}
	
	public int getGeologicalFeatureByPixel(int pixelX, int pixelY) throws IllegalArgumentException{
		if(!isValidBottomLeftTilePixel(pixelX, pixelY)){
			throw new IllegalArgumentException();
		}
		int[] tile = pixelInWhichTile(pixelX, pixelY);
		return getGeologicalFeatureOfTile(tile[0], tile[1]);	
		
	}
	
	/**
	 * 
	 * @param 	pixelX
	 * 			The X coordinate of the pixel
	 * @param 	pixelY
	 * 			The Y coordinate of the pixel
	 * @return	...
	 * 			|return((pixelX % getTileSize() == 0)&&(pixelY % getTileSize() == 0))
	 */
	private boolean isValidBottomLeftTilePixel(int pixelX, int pixelY){
		return((pixelX % getTileSize() == 0)&&(pixelY % getTileSize() == 0));	
	}
	
	/**
	 * Returns the tile in which the pixel is located
	 * 
	 * @param 	pixelX
	 * 			The X coordinate of the pixel
	 * @param 	pixelY
	 * 			The Y coordinate of the pixel
	 * @pre		the pixel needs to be in the game World
	 * 			|((pixelX <= getWorldSizeInPixels()[0]) && (pixelY <= getWorldSizeInPixels()[1]))
	 * @return	{pixelX / getTileSize(), pixelY / getTileSize()}
	 */
	private int[] pixelInWhichTile(int pixelX, int pixelY){
		assert((pixelX <= getWorldSizeInPixels()[0]) && (pixelY <= getWorldSizeInPixels()[1]));
		int[] result = new int[2];
		result[0] = pixelX / getTileSize();
		result[1] = pixelY / getTileSize();
		return result;
	}
	
	/**
	 * Returs whereas the given X_T and Y_T coordinate is valid
	 * @param 	xT
	 * 			the X_T coordinate of the tile
	 * @param 	yT
	 * 			the Y_T coordinate of the tile
	 * @return	true when xT and yT are greater or equal to zero and 
	 * 				xT is less than the amount of horzontal tiles and 
	 * 				yT is less than the amount of vertical tilles
	 * 				otherwise returns false
	 * 			|((xT >= 0) && (xT < getNbTilesX()) && (yT >= 0) && (yT < getNbTilesY()))
	 */
	public boolean isValidTileCoordinate(int xT, int yT){
		return ((xT >= 0) && (xT < getNbTilesX()) && (yT >= 0) && (yT < getNbTilesY()));
	}
	
	/**
	 * This table contains the geological feature of all tiles in this world
	 */
	private int[][] geologicalFeatureOfTiles;
}


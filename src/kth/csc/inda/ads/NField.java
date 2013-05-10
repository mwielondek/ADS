package kth.csc.inda.ads;

public class NField {
    
    // The depth and width of the field.
    private int depth, width;

    
    private ActAndDraw[][] field;

    /**
     * Represent a field of the given dimensions.
     * @param depth The depth of the field.
     * @param width The width of the field.
     */
    public NField(int depth, int width) {
        this.depth = depth;
        this.width = width;
        field = new ActAndDraw[width][depth];
    }
    
    /**
     * Empty the field.
     */
    public void clear() {
        for(int row = 0; row < depth; row++) {
            for(int col = 0; col < width; col++) {
                field[row][col] = null;
            }
        }
    }
    
    /**
     * Clear the given location.
     * @param location The location to clear.
     */
    public void clear(Location location) {
        field[location.getRow()][location.getCol()] = null;
    }

    public void place(ActAndDraw actor, Location location)  {
        field[location.getRow()][location.getCol()] = actor;
    }
    
    /**
     * Return the object at the given location, if any.
     * @param location Where in the field.
     * @return The object at the given location, or null if there is none.
     */
    public ActAndDraw getObjectAt(Location location) {
        return getObjectAt(location.getRow(), location.getCol());
    }
    
    /**
     * Return the object at the given location, if any.
     * @param row The desired row.
     * @param col The desired column.
     * @return The object at the given location, or null if there is none.
     */
    public ActAndDraw getObjectAt(int row, int col) {
        try{
        	return field[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
        	return null;
        }
    }

    /**
     * Return the depth of the field.
     * @return The depth of the field.
     */
    public int getDepth() {
        return depth;
    }
    
    /**
     * Return the width of the field.
     * @return The width of the field.
     */
    public int getWidth() {
        return width;
    }
}

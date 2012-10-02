package shadow.world.temp;

import shadow.math.SFVertex3f;


public interface SFGenericWalkablesNet extends SFObstacle{

	public int getWalkablesCount();

	public abstract boolean intersect(int walkable, SFVertex3f Q,
			SFVertex3f Dir, SFVertex3f Point);

	public abstract void moveOnEdge(int walkable, SFVertex3f actualPosition,
			SFVertex3f destination, float ray, float height);

	/**
	 * Return the code -2 if it cannot walk because of obstacles.
	 * 
	 * @param walkable
	 * @param position
	 * @param ray
	 * @param height
	 * @return
	 */
	public abstract int canWalk(int walkable, SFVertex3f position, float ray,
			float height);

	public SFPickable getPickable(SFVertex3f Q,SFVertex3f Dir);
	
	/**
	 * Return the index of a walkable on which this point is placed,
	 *  -1 if you cannot find a place
	 * @param position
	 * @return
	 */
	public abstract int findWalkable(SFVertex3f position);

	public abstract float getY(int walkable,SFVertex3f position);

	/** Tells if 'position' is inside the bounding box of this WalkablesNet,
	 * with a tollerance which is maxDistance.*/
	public abstract boolean isIn(SFVertex3f position,float maxDistance);
	
	/**
	 *  Tells if i can enter in this walkable in this position.
	 * @param position
	 * @return
	 */
	public abstract boolean enter(SFVertex3f position,float ray);
	
	public void setEntered(boolean b);
	
	public abstract boolean canExit(int walkableIndex);
	
}
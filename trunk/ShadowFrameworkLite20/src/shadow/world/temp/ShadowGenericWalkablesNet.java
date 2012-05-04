package shadow.world.temp;
//
//
//public interface ShadowGenericWalkablesNet extends ShadowObstacle{
//
//	//public abstract ShadowWalkable getWalkables(int index);
//
//	public abstract int getWalkablesCount();
//
//	public abstract boolean intersect(int walkable, GLVertex3f Q,
//			GLVertex3f Dir, GLVertex3f Point);
//
//	public abstract void moveOnEdge(int walkable, GLVertex3f actualPosition,
//			GLVertex3f destination, float ray, float height);
//
//	/**
//	 * Return the code -2 if it cannot walk because of obstacles.
//	 * 
//	 * @param walkable
//	 * @param position
//	 * @param ray
//	 * @param height
//	 * @return
//	 */
//	public abstract int canWalk(int walkable, GLVertex3f position, float ray,
//			float height);
//
//	public ShadowPickable getPickable(GLVertex3f Q,GLVertex3f Dir);
//	
//	/**
//	 * Return the index of a walkable on which this point is placed,
//	 *  -1 if you cannot find a place
//	 * @param position
//	 * @return
//	 */
//	public abstract int findWalkable(GLVertex3f position);
//
//	public abstract float getY(int walkable,GLVertex3f position);
//
//	/** Tells if 'position' is inside the bounding box of this WalkablesNet,
//	 * with a tollerance which is maxDistance.*/
//	public abstract boolean isIn(GLVertex3f position,float maxDistance);
//	
//	/**
//	 *  Tells if i can enter in this walkable in this position.
//	 * @param position
//	 * @return
//	 */
//	public abstract boolean enter(GLVertex3f position,float ray);
//	
//	public void setEntered(boolean b);
//	
//	public abstract boolean canExit(int walkableIndex);
//	
//}
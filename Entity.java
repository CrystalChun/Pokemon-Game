import java.io.Serializable;

/**
 * The entity class that stores its
 * max hp, hp, and name.
 * @author Crystal Chun		ID#012680952
 *
 */
public abstract class Entity implements Serializable
{
	/**The entity's name*/
	private String name;
	/**The entity's current hp*/
	private int hp;
	/**The entity's maximum hp*/
	private int maxHp;
	
	/**
	 * Constructs an entity with a name, hp, and max hp
	 * @param name The entity's name
	 * @param hp The hp of the entity
	 */
	public Entity(String name, int hp)
	{
		this.name = name;
		this.hp = hp;
		this.maxHp = hp; //The max hp is the entity passed in
	}
	
	/**
	 * Changes the entity's name
	 * @param name The new name for this entity
	 */
	public void changeName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Gets the entity's name
	 * @return The entity's name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the entity's hp
	 * @return The entity's current hp
	 */
	public int getHP()
	{
		return hp;
	}
	
	/**
	 * Subtracts the hp by how much damage was done to the entity.
	 * @param hit The amount that needs to be subtracted from the hp
	 * @return The hp after the entity gets hit.
	 */
	public int loseHP(int hit)
	{
		//If the hit is greater than the current hp, just sets hp to 0
		if(hit > hp)
		{
			hp = 0;
		}
		//Otherwise subtracts the hit from current hp
		else
		{
			hp = hp - hit;
		}
		
		return hp;
	}
	
	/**
	 * The entity gains hp (like when they get healed)
	 * @param heal The integer value their hp goes up by.
	 * @return The hp after the entity has been healed.
	 */
	public int gainHP(int heal)
	{
		//Tests whether the heal + hp exceeds the maximum hp
		if((heal + hp) > maxHp)
		{
			hp = maxHp; //If it does, just sets hp to maximum hp
		}
		//Otherwise just increases the current hp by how much the entity heals
		else
		{
			hp = hp + heal;
		}
		
		return hp;
	}
	
	/**
	 * Increases the entity's maximum hp when the entity levels up,
	 * which goes up by 8 per level.
	 */
	public void incMaxHp()
	{
		//HP increases by 8 each level
		maxHp = maxHp + 8;
	}
	
	/**
	 * Gets the entity's maximum hp
	 * @return The maximum hp
	 */
	public int getMaxHP()
	{
		return maxHp;
	}
}

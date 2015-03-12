import java.util.ArrayList;

/**
 * Creature class
 * 
 * Essential class. Represents any living thing
 * Consists of parts. Consuming energy, moving
 * 
 * 
 * @version 0.5A
 * @author Àðò¸ì
 *
 */

public class Creature {
	
	//Constant values
	public static final int PEACE_STATE = 0;			//Creature states. Set up with setState()
	public static final int SEEK_FOR_FOOD_STATE = 1;
	public static final int SEEK_FOR_MATING_STATE = 2;
	public static final int AGRESSION_STATE = 3;
	public static final int UNKNOWN_STATE = -1;
	
	public static final int START_CREATURE_ENERGY = 50;	
	//End of Constant values
	
	
	private boolean isAlive;	
	private boolean isCenterPart;					//is the center part exist
	private int weight;
	private int height;
	private int x,y;
	private int width;
	private ArrayList<String> avalibleFunctions;	//name for all avalible funtions
	private ArrayList<BodyPart> parts;
	private String name;
	private int energy;
	private int energyConsumption;
	private GeneCode code;							//Structure that contains gene information 
	private int State;
	
	
	/*
	 * Constructor for new Creature without parents
	 */
	Creature(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		
		this.isAlive = false;
		this.isCenterPart = false;
		this.width = 0;
		this.height = 0;
		this.energy = START_CREATURE_ENERGY;
		this.energyConsumption = 0;
		this.State = -1;
		
		this.add(CreatureManager.getRandomCenterPart());	
		this.comeToLife();
	}
	
	/*
	 * Constructor for creature with one parent, itself
	 */
	Creature(Creature one) {
		
	}
	
	/*
	 * Adds new part
	 * init from class
	 * centerPart
	 */
	public void add(BodyPart part) {
		if(part.getClass() == CenterPart.class) {
			this.isCenterPart = true;
		}
		this.parts.add(part);
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	//can be accessed from getAlive();
	private void setAlive(boolean alive) {
		isAlive = alive;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int w) {
		this.weight = w;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void setAvalibleFunctions(ArrayList<String> funct) {
		this.avalibleFunctions = funct;
	}
	
	public ArrayList<String> getAvalibleFunctions() {
		return this.avalibleFunctions;
	}
	
	public ArrayList<BodyPart> getParts() {
		return this.parts;
	}
	
	public String getName() {
		return name;
	}
	
	public void setEnergy(int e) {
		this.energy = e;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public int getEnergyConsuption() {
		return this.energyConsumption;
	}
	
	public void setEnergyConsumption(int e) {
		this.energyConsumption = e;
	}
	
	public GeneCode getGeneCode() {
		return this.code;
	}
	
	public int getState() {
		return this.State;
	}
	
	public void setState(int s) {
		this.State = s;
	}
	
	
	//Birth of the creature
	private boolean comeToLife() {
		if(isAlive || !this.isCenterPart) return false;
		

		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int minY = minX;
		int maxY = maxX;
		
		for(int i = 0; i < parts.size(); i++) {
			for(int k = 0; k < parts.get(i).getFunctions().length; k++) {
				if(parts.get(i).getFunctions()[k].isAvalible()) {
					this.avalibleFunctions.add(parts.get(i).getFunctions()[k].getName()); 
				}
			}
			
			maxX = Math.max(maxX, parts.get(i).getX()+parts.get(i).getWidth());
			minX = Math.min(minX, parts.get(i).getX());
			
			maxY = Math.max(maxY, parts.get(i).getY() + parts.get(i).getHeight());
			minY = Math.min(minY, parts.get(i).getY());
			
			energyConsumption += parts.get(i).getEnergyConsumption();
		}
		

		this.width = maxX - minX;
		this.height = maxY - minY;
		
		this.setState(PEACE_STATE);
		this.setAlive(5 == 10/2);
		
		return true;
	}
	
	//consumes some energy each tick
	public void consumeEnergy() {
		this.energy -= this.energyConsumption;
	}
	
	//render all parts
	public void render(int[] buffData) {
		for(int i = 0; i < this.parts.size(); i++) {
			parts.get(i).render(buffData);
		}
	}
}

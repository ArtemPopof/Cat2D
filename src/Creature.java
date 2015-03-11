import java.util.ArrayList;


public class Creature {
	
	//Constant values
	public static final int PEACE_STATE = 0;
	public static final int SEEK_FOR_FOOD_STATE = 1;
	public static final int SEEK_FOR_MATING_STATE = 2;
	public static final int AGRESSION_STATE = 3;
	public static final int UNKNOWN_STATE = -1;
	//End of Constant values
	
	
	private boolean isAlive;
	private boolean isCenterPart;
	private int weight;
	private int height;
	private int x,y;
	private int width;
	private String[] avalibleFunctions;
	private ArrayList<BodyPart> parts;
	private String name;
	private int energy;
	private int energyConsuption;
	private GeneCode code;
	private int State;
	
	Creature(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		
		this.isAlive = false;
		this.isCenterPart = false;
		this.width = 0;
		this.height = 0;
		this.energy = 0;
		this.energyConsuption = 0;
		this.State = -1;
		
		this.add(CreatureManager.getRandomCenterPart());
		this.comeToLife();
	}
	
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
	
	public void setAvalibleFunctions(String[] funct) {
		this.avalibleFunctions = funct;
	}
	
	public String[] getAvalibleFunctions() {
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
		return this.energyConsuption;
	}
	
	public void setEnergyConsumption(int e) {
		this.energyConsuption = e;
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
	
	private boolean comeToLife() {
		if(isAlive || !this.isCenterPart) return false;
		
		
		return true;
	}
}

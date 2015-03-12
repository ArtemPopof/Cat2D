import java.io.Serializable;


/**
 * BodyPart class
 * 
 * Represent abstract part of a creature body
 * All part classes must extend this class
 * 
 * @author Artem Popov
 *
 * @version 1.0
 */

public abstract class BodyPart extends Sprite implements Serializable {
	public static final String BODY_PART_IMAGE_PATH = "/res/parts/";
	
	//Material information section
	
	public static final int MEAT_COST = 5; //How many energy each cell consumes
	public static final int FAT_COST = -2; //Fat capable of holding energy
	public static final int SKIN_COST = 1; 
	
	public static final int SKIN_WEIGHT = 2; //Weight of a skin cell
	public static final int FAT_WEIGHT = 1;
	public static final int MEAT_WEIGHT = 3;
	
	public static final int SKIN_COLD_RESIST = 1;
	public static final int FAT_COLD_RESIST = 2;
	public static final int FAT_HOT_RESIST = -1;
	public static final int SKIN_HOT_RESIST = 2;
	
	public static final int MEAT_MATERIAL = 0xff0000; // Meat pixel color
	public static final int SKIN_MATERIAL = 0x939393; // Skin pixel color
	public static final int FAT_MATERIAL = 0x334455; // Fat pixel color
	
	// End of material info section
	
	
	private int relX, relY; //Relative coordinates of part
    private int orientation; //part orientation(read bodyPartINFO.txt)
    private int weight; //affects on speed
    private int defense; //defense against environment influence
    private int energyCosts; //Part's energy consumption
    private PartFunction[] avalibleFunctions; //All potential functions of the part
    private boolean isActive; // active now or not
    private Creature parent;
    
    /*other properties
     * can be supplemented later
     */
    
    private int fatCells; //fat cells quantity
    private int meatCells;
    private int skinCells;
    private int hotResist;  //1-100 
    private int coldResist; //1-100
    
    //end of other properties
    
    BodyPart(int relX, int relY, int width, int height, int orientation
    		,int[] pixels) {
    	
    	super(width, height, pixels);
    	
    	this.relX = relX;
    	this.relY = relY;
    	this.orientation = orientation;
    	
    	calculateProperties();
    }
    
    /** This method can be used
     * for calculating part characteristics
     * like weight, resist, defence etc.
     */
    private void calculateProperties() {
    	//calculate all types of cells
    	
    	for(int i = 0; i < pixels.length; i++) {
    		switch(pixels[i]) {
    		case MEAT_MATERIAL:
    			meatCells++;
    			break;
    		case FAT_MATERIAL:
    			fatCells++;
    			break;
    		case SKIN_MATERIAL:
    			skinCells++;
    			break;
    		}
    	}
    	
    	energyCosts = (fatCells * FAT_COST) + (skinCells * SKIN_COST) + (meatCells * MEAT_COST);
    	weight = (fatCells * FAT_WEIGHT) + (skinCells * SKIN_WEIGHT) + (meatCells * MEAT_WEIGHT);
    	
    	coldResist = fatCells * FAT_COLD_RESIST + skinCells * SKIN_COLD_RESIST;
    	hotResist = fatCells * FAT_HOT_RESIST + skinCells * SKIN_HOT_RESIST;
    }
    
    /**
     * Determines whether function
     * is avalible or not
     */
    protected abstract void unlockFunctions();
    
    public int getOrientation() {
    	return orientation;
    }
    
    public PartFunction[] getFunctions() {
    	return this.avalibleFunctions;
    }
    
    public int getWeight() {
    	return weight;
    }
    
    public int getDefense() {
    	return defense;
    }
    
    public Creature getParent() {
    	return parent;
    }
    
    /*Part function discription
     * 
     * with this class can be
     * determined avalible functions
    */
    public static class PartFunction{
    	private int number;
    	private String name;
    	
    	private int fatRequirement;
    	private int skinRequirement;
    	private int meatRequirement;
    	
    	private boolean isAvalible = false;
    	
    	PartFunction(int number, String name, int fatRequirement, int skinRequirement, int meatRequirement) {
    		this.number = number;
    		this.name = name;
    		this.fatRequirement = fatRequirement;
    		this.skinRequirement = skinRequirement;
    		this.meatRequirement = meatRequirement;
    	}
    	
    	public int getNumber() {
    		return number;
    	}
    	
    	public String getName() {
    		return name;
    	}
    	
    	public int getMeatRequirement() {
    		return this.meatRequirement;
    	}
    	
    	public int getFatRequirement() {
    		return this.fatRequirement;
    	}
    	
    	public int getSkinRequirement() {
    		return this.skinRequirement;
    	}
    	
    	

    	public boolean isAvalible() {
    		return isAvalible;
    	}
    	
    	public void setAvalible(boolean av) {
    		this.isAvalible = av;
    	}
    	
    }

	public void setFunctions(PartFunction[] func) {
		this.avalibleFunctions = func;
	}
	
	public int getFatCells() {
		return this.fatCells;
	}
	
	public int getSkinCells() {
		return skinCells;
	}
	
	public int getMeatCells() {
		return meatCells;
	}
	
	public int getEnergyConsumption() {
		return this.energyCosts;
	}
    
    
}

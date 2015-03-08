/**
 * HeadPart class
 * 
 * represents a some kind of head
 * attack and speech depend of this part
 * 
 * @version 1.0
 * @author Artem Popov
 *
 */
public class HeadPart extends BodyPart {
	
	public static final int MAX_HEAD_WIDTH = 10;
	public static final int MAX_HEAD_HEIGHT = 10;
	
	private int biteForce;
	private int visionPower = 0; // how far can creature observe enemy
	private int mouthType = -1;  // (-1) without mouth, 1 - attack capable mouth, 0 - speechCapableMouth
	
	HeadPart(int relX, int relY, int width, int height,
			int orientation, int pixels[] ) {
		super(relX, relY, Math.max(width, MAX_HEAD_WIDTH),
				Math.max(height, MAX_HEAD_HEIGHT), orientation,pixels);
		
		
		//PartFunction - description of the function
		//which creature can realize
		PartFunction[] func = new PartFunction[4];
		
		func[0] = new PartFunction(0, "LowLevelVision", 6, 4, 4);
		func[1] = new PartFunction(1, "HighLevelVision", 10, 10, 10);
		func[2] = new PartFunction(2, "billROT", 4, 5, 6);
		func[3] = new PartFunction(3, "mouth", 8, 10, 5);
		
		super.setFunctions(func);
		
		unlockFunctions();
	}
	
	
	//check: which functions this part can realize
	// and which don't
	protected void unlockFunctions() {
		for(int i = 0; i < super.getFunctions().length; i++ ) {
			super.getFunctions()[i].setAvalible(
					(super.getFunctions()[i].getMeatRequirement() <= super.getMeatCells()) &&
					(super.getFunctions()[i].getFatRequirement() <= super.getFatCells()) &&
					(super.getFunctions()[i].getSkinRequirement() <= super.getSkinCells()));
		}
		
		visionPower = (super.getFunctions()[1].isAvalible())
				? 100 : ((super.getFunctions()[0].isAvalible()) ? 50 : 0);
		
		mouthType = (super.getFunctions()[3].isAvalible()) ? 1 : 
			((super.getFunctions()[2].isAvalible())) ? 0 : -1;
		
	}
	
}

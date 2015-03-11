
/**
 * Class LimbPart
 * 
 * Represents any limb for creature
 * Only one function managed through
 * this class is movement
 * 
 * @version 1.0
 * @author Àðò¸ì
 *
 */
public class LimbPart extends BodyPart {
	
	//maximal dimension of the limb
	public static final int MAX_LIMB_WIDTH = 20;
	public static final int MAX_LIMB_HEIGHT = 10;
	
	private int limbType = -1; // -1 do not have any functions, 0 - low speed movement, 1 - mid 2 - high;
	
	LimbPart(int relX, int relY, int width, int height, int orientation, int[] pixels) {
		super(relX, relY, Math.max(width, MAX_LIMB_WIDTH), Math.max(height, MAX_LIMB_HEIGHT),
				orientation, pixels);
		
		PartFunction func[] = new PartFunction[3];
		
		func[0] = new PartFunction(1, "LowLevelMovement", 3, 4, 6);
		func[1] = new PartFunction(2, "MidLevelMovement", 3, 4, 10);
		func[2] = new PartFunction(3, "HighLevelMovement", 3, 4, 15);
		
		super.setFunctions(func);
		
		
	}

	//calculate which functions will be avalible 
	protected void unlockFunctions() {
		for(int i = 0; i < super.getFunctions().length; i++ ) {
			PartFunction func = super.getFunctions()[i];
			func.setAvalible((super.getMeatCells() >= func.getMeatRequirement())
					&& (super.getFatCells() >= func.getFatRequirement())
					&& (super.getSkinCells() >= func.getSkinRequirement()));
		}
		
		limbType = super.getFunctions()[2].isAvalible() ? 2 : super.getFunctions()[1].isAvalible() ? 1 : super.getFunctions()[0].isAvalible() ? 0 : -1;
	}

}

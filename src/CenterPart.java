/**
 * CenterPart class
 * represent an essential part
 * for the creature
 * 
 * @version 1.0
 * @author Artem Popov
 *
 */
public class CenterPart extends BodyPart {
	
	public static final int MAX_CENTER_WIDTH = 50;
	public static final int MAX_CENTER_HEIGHT = 50;
	
	//what kind of food the Creature consumes
	//(-1) - can not eat =(
	//0 - sunFeed, 1 - weedFeed
	private int foodType = -1; 
	// reproduction type (self or with partner)
	//(-1) - can not have sex =(
	//0 - selfReproduction 1 - partnerReproduction
	private int reproType = -1; 
	
	CenterPart(int width, int height,int[] pixels) {
		super(0, 0, Math.max(width, MAX_CENTER_WIDTH),
				Math.max(height, MAX_CENTER_HEIGHT), 0, pixels);
		
		PartFunction[] func = new PartFunction[4];
		
		func[0] = new PartFunction(1, "SunFeed", 1, 2, 0);
		func[1] = new PartFunction(2, "WeedFeed", 2, 2, 1);
		func[2] = new PartFunction(3, "SelfRepro", 1, 1, 1);
		func[3] = new PartFunction(4, "PartnerRepro", 3, 3, 3);
		
		super.setFunctions(func);
		unlockFunctions();
	}
	
	protected void unlockFunctions() {
		for(int i = 0; i < super.getFunctions().length; i++ ) {
			PartFunction func = super.getFunctions()[i];
			func.setAvalible((super.getMeatCells() >= func.getMeatRequirement())
					&& (super.getFatCells() >= func.getFatRequirement())
					&& (super.getSkinCells() >= func.getSkinRequirement()));
		}
		
		foodType = (super.getFunctions()[1].isAvalible()) ? 1 : ((super.getFunctions()[0].isAvalible()) ? 0 : -1);
		reproType = (super.getFunctions()[3].isAvalible()) ? 1 : ((super.getFunctions()[2].isAvalible()) ? 0 : -1);

	}
	
	public void addPart(BodyPart part) {
		part.getParent().add(part);
	}

}

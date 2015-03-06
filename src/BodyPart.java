import java.io.Serializable;


public abstract class BodyPart extends Sprite implements Serializable {
	public static final int MEAT_COST = 5; // ������� ������ ������� ������ �������
	public static final int FAT_COST = -2; // ���� ��������� �������� ���������� ��������
	public static final int SKIN_COST = 1; 
	
	public static final int MEAT_MATERIAL = 0xff0000; // ������� ���� �������� ������������� ������ ����
	public static final int SKIN_MATERIAL = 0x939393; // ���� �������� ��� ����
	public static final int FAT_MATERIAL = 0x334455; // ���� �������� ��� ����
	
	private int relX, relY; //�������� ������������ ������ ���� ����
    private int orientation; //���������� ����������(��������� �� � bodyPartINFO
    private int energyCosts; // ������� ���� ������ �������
    private PartFunction[] avalibleFunctions; // �������, ������� �������� ��������� ��� ����� ����
    private boolean isActive; // ������� �� � ������ ������ �����
    
    /*other properties
     * can be supplemented later
     */
    
    private int fatCells; //���������� ������ ����
    private int meatCells;
    private int skinCells;
    private int hotResist;  //1-100 
    private int coldResist; //1-100
    
    //end of other properties
    
    BodyPart(int relX, int relY, int width, int height, int orientation,
    		int hotResist, int coldResist, PartFunction[] avalibleFunctions, 
    		int[] pixels) {
    	
    	super(width, height);
    	
    	this.relX = relX;
    	this.relY = relY;
    	this.orientation = orientation;
    	this.avalibleFunctions = avalibleFunctions;
    	
    	this.hotResist = hotResist;
    	this.coldResist = coldResist;
    	
    	this.pixels = pixels; //������� ��� ����� ����
    	
    	calculateProperties();
    }
    
    /** ������������ ��� ������� ������ �������,
     *  � ������ ������������� ����� ����
     * ����� ����
     */
    private void calculateProperties() {
    	//������� ������ ������� ����
    	
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
    	
    	unlockFunctions();
    }
    
    /**
     * ����������, ����� ������� �� ������ �����
     * �������� ��� ������ ����� ����
     */
    protected abstract void unlockFunctions();
    
    /*��������� ������� ����� ����
     *� ������� ����� ������ �����
     *�����������, �������� �� �� ��� 
     *���� �������
    */
    public static class PartFunction{
    	private int number;
    	
    	private int fatRequirement;
    	private int skinRequirement;
    	private int meatRequirements;
    	
    	PartFunction(int number, int fatRequirement, int skinRequirement, int meatRequirement) {
    		this.number = number;
    		this.fatRequirement = fatRequirement;
    		this.skinRequirement = skinRequirement;
    		this.meatRequirements = meatRequirement;
    	}
    	
    }
}

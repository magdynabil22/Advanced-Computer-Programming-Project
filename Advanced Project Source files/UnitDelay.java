/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advanced.programming.project;

/*
-------------------------------------------
----------------UnitDelay----------------------
     -name: String
     -SID: int 
     -position: double[]
     -Zorder: int
     -BlockMirror: boolean
     -SampleTime: double
     -HasFrameUpgradeWarning: boolean
-------------------------------------------
    +getSID()
    +getZorder()
    +getName()
    +getPosition()
    +getBlockMirror()
    +getSampleTime()
    +getHasFrameUpgradeWarning()
    +setSID()
    +setZorder()
    +setName()
    +setPosition()
    +setBlockMirror()
    +setSampleTime()
    +setHasFrameUpgradeWarning()
-------------------------------------------
 */
public class UnitDelay extends Block{
     //ATTRIBUTES
    private boolean BlockMirror=false;
    private double SampleTime;
    private boolean HasFrameUpgradeWarning=false;
    //CONSTRUCTOR
    public UnitDelay(String name, int SID, double[] position, int Zorder, boolean BlockMirror, double SampleTime, boolean HasFrameUpgradeWarning){
        super(name, SID, position, Zorder);
        this.BlockMirror = BlockMirror;
        this.SampleTime = SampleTime;
        this.HasFrameUpgradeWarning = HasFrameUpgradeWarning;
    }
    //GETTERS
     public boolean getBlockMirror() {
        return BlockMirror;
    }

    public double getSampleTime() {
        return SampleTime;
    }

    public boolean getHasFrameUpgradeWarning() {
        return HasFrameUpgradeWarning;
    }
    //SETTERS
    public void setBlockMirror(boolean BlockMirror)
    {
	this.BlockMirror = BlockMirror;
    }
    public boolean isBlockMirror()
    {
	return this.BlockMirror;
    }
    public void setPosition(double SampleTime) {
        this.SampleTime = SampleTime;
    }
     public void setHasFrameUpgradeWarning(boolean HasFrameUpgradeWarning)
    {
	this.HasFrameUpgradeWarning = HasFrameUpgradeWarning;
    }
    public boolean isHasFrameUpgradeWarning()
    {
	return this.HasFrameUpgradeWarning;
    }
    @Override
    public void print(){
        super.print();
        System.out.println("SampleTime: "+SampleTime);
        System.out.println("BlockMirror: "+BlockMirror);
        System.out.println("HasFrameUpgradeWarning: "+HasFrameUpgradeWarning);
    }
}
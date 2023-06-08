/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advanced.programming.project;

/*
-------------------------------------------
----------------SUM----------------------
     -IconShape: String
     -Inputs: String 
     -ports: double[] (size = 2)
-------------------------------------------
    +getIconShape()
    +getInputs()
    +getPorts()
    +setIconShape()
    +setInputs()
    +setPorts()
-------------------------------------------
*/
public class  Sum extends Block {
    private double[] ports;
    private String IconShape;
    private String Inputs;
    //CONSTRUCTOR
    public Sum(String name,double[] position, int Zorder, int SID, double[] ports
    ,String IconShape, String Inputs)
    {
        super(name,SID,position,Zorder);
        this.IconShape = IconShape;
        this.Inputs = Inputs;
        this.ports = ports;
    }
    //GETTERS
    public double[] GetPorts(){
        return ports;
    }
    public String IconShape(){
        return IconShape;
    }
    public String Inputs(){
        return Inputs;
    }
    //SETTERS
    public void SetPorts(double[] ports){
        this.ports = ports;
    }
    public void SetIconShape(String IconShape){
        this.IconShape = IconShape;
    }
    public void SetInputs(String Inputs){
        this.Inputs = Inputs;
    }
    @Override
    public void print(){
        super.print();
        System.out.println("IconShape: "+IconShape);
        System.out.println("Inputs: "+Inputs);
        System.out.println("Ports: ");
        for(int i =0;i<ports.length;i++){
            System.out.println(ports[i]);
        }
    }
}

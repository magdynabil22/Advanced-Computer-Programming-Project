/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advanced.programming.project;
/*
-------------------------------------------
----------------BLOCK----------------------
     -name: String
     -SID: int 
     -position: double[] (size = 4)
     -Zorder: int
-------------------------------------------
    +getSID()
    +getZorder()
    +getName()
    +getPosition()
    +setSID()
    +setZorder()
    +setName()
    +setPosition()
-------------------------------------------
*/
public abstract class Block {
    //ATTRIBUTES
    private String name;
    private int SID;
    private double[] position;
    private int Zorder;
    //CONSTRUCTOR
    public Block(){
        
    }
    public Block(String name, int SID, double[] position, int Zorder){
        this.name = name;
        this.SID = SID;
        this.position = position;
        this.Zorder = Zorder;
    }
    //GETTERS
    public int getSID() {
        return SID;
    }

    public int getZorder() {
        return Zorder;
    }

    public String getName() {
        return name;
    }

    public double[] getPosition() {
        return position;
    
    }
    
    //SETTERS

    public void setSID(int SID) {
        this.SID = SID;
    }

    public void setZorder(int Zorder) {
        this.Zorder = Zorder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }
    public void print(){
        System.out.println("Name: "+name);
        System.out.println("Zorder: "+Zorder);
        System.out.println("SID: "+SID);
        System.out.println("Position: ");
        for(int i =0;i<position.length;i++){
            System.out.println(position[i]);
        }
    }
}

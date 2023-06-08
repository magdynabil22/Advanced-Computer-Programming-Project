/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advanced.programming.project;

/*
-------------------------------------------
----------------Branch----------------------
     -Zorder: int
     -Points: double[]
     -Dst: String
-------------------------------------------
    +getZorder()
    +getPoints()
    +getDst()
    +setZorder()
    +setPoints()
    +setDst()
-------------------------------------------
 */
public class Branch{
    //ATTRIBUTES
    private double[] Points;
    private String Dst;
    private int Zorder;
    public boolean HasPoints = true;
    //CONSTRUCTOR
    //DEFAULT
    public Branch(String Dst, int Zorder) {
        this.Dst = Dst;
        this.Zorder = Zorder;
        HasPoints = false;
    }
    
    public Branch( int Zorder, double[] Points, String Dst){
        this.Zorder = Zorder;
        this.Points = Points;
        this.Dst = Dst;
    }
    //GETTERS
    public double[] getPoints() {
        return Points;
    }
    public String getDst() {
        return Dst;
    }
     //SETTERS
     public void setPoints(double[] Points) {
        this.Points = Points;
    }

    public void setDst(String Dst) {
        this.Dst = Dst;
    }
     public int getDstSID(){
        String[] arr = Dst.split("#");
        return Integer.parseInt(arr[0]);
    }
    public void print(){
        System.out.println("Dst: "+this.Dst);
        System.out.println("Zorder: "+this.Zorder);
        if(HasPoints == true)
        {
        System.out.println("Points: ");
        for(int i =0;i<Points.length;i++){
            System.out.println(Points[i]);
        }
        }
    }
}
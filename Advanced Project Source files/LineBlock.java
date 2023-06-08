/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advanced.programming.project;

/**
 *
 * @author hp
 */
import java.util.ArrayList;
public class LineBlock {
    private int Zorder;
    private String Src;
    private String Dst;
    private double[] Points;
    public boolean HasPoints = true;
    public boolean HasDst = true;
    public boolean HasBranch;
    private ArrayList<Branch> BranchList = new ArrayList<>();
    //ZORDER SRC AND DST
    public LineBlock(int Zorder, String Src, String Dst,boolean HasBranch) {
        this.Zorder = Zorder;
        this.Src = Src;
        this.Dst = Dst;
        HasPoints = false;
        this.HasBranch = HasBranch; 
    }
    //ZORDER SRC AND POINTS
    public LineBlock(int Zorder, String Src, double[] Points,boolean HasBranch,ArrayList<Branch> BranchList) {
        this.Zorder = Zorder;
        this.Src = Src;
        this.Points = Points;
        HasDst = false;
        this.HasBranch = HasBranch;
        this.BranchList = BranchList;
    }
    
    //ALL
    public LineBlock(int Zorder, String Src, String Dst, double[] Points,boolean HasBranch) {
        this.Zorder = Zorder;
        this.Src = Src;
        this.Dst = Dst;
        this.Points = Points;
        this.HasBranch = HasBranch;
    }
    
    public int getZorder() {
        return Zorder;
    }

    public String getSrc() {
        return Src;
    }

    public String getDst() {
        return Dst;
    }

    public void setZorder(int Zorder) {
        this.Zorder = Zorder;
    }

    public void setSrc(String Src) {
        this.Src = Src;
    }

    public void setDst(String Dst) {
        this.Dst = Dst;
    }
    public int getSrcSID(){
        String[] arr = Src.split("#");
        return Integer.parseInt(arr[0]);
    }
    public int getSrcPin(){
        String[] arr = Src.split(":");
        return Integer.parseInt(arr[1]);
    }
    public int getDstSID(){
        if(HasDst == true){
        String[] arr = Dst.split("#");
        return Integer.parseInt(arr[0]);
        }
        else 
            return -1;
    }
    public int getDstPin(){
        if(HasDst == true){
        String[] arr = Dst.split(":");
        return Integer.parseInt(arr[1]);
        }
        else
            return -1;
    }

    public double[] getPoints() {
        return Points;
    }
    public void SetBranch(ArrayList<Branch> BranchList){
        this.BranchList = BranchList;
    }
    public void print(){
        System.out.println("Zorder: "+Zorder);
        System.out.println("Src: "+Src);
        if(HasDst == true)
        System.out.println("Dst: "+Dst);
        System.out.println("HasBranchWithinLine: "+HasBranch);
        /*if(HasBranch == true)
        {
            for(Branch branch:BranchList){
                branch.print();
                branch_counter++;
                System.out.println("--------------BranchWithinLine: "+branch_counter);
            }
        }*/
        if(HasPoints == true){
        System.out.println("Points: ");
        for(int i =0 ; i<Points.length;i++){
            System.out.println(Points[i]);
        }
        }
    }
    public boolean getHasBranch(){
        return HasBranch;
    }
    public ArrayList<Branch> getBranchList(){
        return BranchList;
    }
    public void printBranchesWithinLine(){
        if(HasBranch == true)
        {   int branch_counter = 0;
            for(Branch branch:BranchList){
                branch.print();
                branch_counter++;
                System.out.println("--------------BranchWithinLine: "+branch_counter);
            }
        }
    }
}

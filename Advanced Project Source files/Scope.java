/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advanced.programming.project;

/**
 *
 * @author hp
 */
public class Scope extends Block {
    private String ScopeSpecificationString;
    private int NumInputPorts;
    private boolean Floating;
    public Scope()
    {}
    public Scope(String name, int SID, double[] position, int Zorder,String ScopeSpecificationString, int NumInputPorts,boolean Floating) {
        super(name,SID,position,Zorder);
        this.ScopeSpecificationString = ScopeSpecificationString;
        this.NumInputPorts = NumInputPorts;
        this.Floating = Floating;
    }

    public String getScopeSpecificationString() {
        return ScopeSpecificationString;
    }

    public int getNumInputPorts() {
        return NumInputPorts;
    }

    public boolean isFloating() {
        return Floating;
    }
    @Override
    public void print(){
        super.print();
        System.out.println("ScopeSpecificationString: "+ScopeSpecificationString);
        System.out.println("NumInputPorts: "+NumInputPorts);
        System.out.println("Floating: "+Floating);
    }
    
}
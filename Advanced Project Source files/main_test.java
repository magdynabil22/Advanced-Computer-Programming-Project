/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advanced.programming.project;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.*;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
/**
 *
 * @author hp
 */
public class main_test extends Application {
    public static Arrow drawArrow(double[] Position){
        return new Arrow(Position[0]-20,Position[1]+20,Position[0],Position[1]+20);
    }
    public static Rectangle drawBlock(double[] Position){
        Rectangle CONSTANT_BLOCK = new Rectangle(Position[0],Position[1],40,40);
        CONSTANT_BLOCK.setFill(Color.WHITE);
        CONSTANT_BLOCK.setStroke(Color.BLUE);
        return CONSTANT_BLOCK;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the file path: ");
        String filepath = in.nextLine();
        in.close();
        ArrayList<Block> Blocks_Array = ParseBlocks(filepath);
        ArrayList<LineBlock> Lines_Array = ParseLines(filepath);
        ArrayList<Branch> Branch_Array = ParseBranches(filepath);
        //CREATING BLOCKS
        Pane pane = new Pane();
        
        for(Block block:Blocks_Array){
            if(block instanceof Constant){
             //CONSTANT BLOCK GUI
             double[] X_Y_CONSTANT = block.getPosition();
             pane.getChildren().add(drawBlock(X_Y_CONSTANT));
             Text CONSTANT_TEXT = new Text("Constant");
             CONSTANT_TEXT.setX(X_Y_CONSTANT[0]);
             CONSTANT_TEXT.setY(X_Y_CONSTANT[1]+50);
             pane.getChildren().add(CONSTANT_TEXT);
            }
            if(block instanceof Saturate){
             //SATURATE BLOCK GUI
             double[] X_Y_SATURATE = block.getPosition();
             pane.getChildren().add(drawBlock(X_Y_SATURATE));
             Text SATURATE_TEXT = new Text("Saturation");
             SATURATE_TEXT.setX(X_Y_SATURATE[0]);
             SATURATE_TEXT.setY(X_Y_SATURATE[1]+50);
             pane.getChildren().add(SATURATE_TEXT);
             pane.getChildren().add(drawArrow(X_Y_SATURATE));
            }
            if(block instanceof Sum){
              //SUM BLOCK GUI
             double[] X_Y_CONSTANT = block.getPosition();
             pane.getChildren().add(drawBlock(X_Y_CONSTANT));
             Text CONSTANT_TEXT = new Text("Add");
             CONSTANT_TEXT.setX(X_Y_CONSTANT[0]);
             CONSTANT_TEXT.setY(X_Y_CONSTANT[1]+50);
             pane.getChildren().add(CONSTANT_TEXT);
             double[] numPorts = ((Sum) block).GetPorts();
             //FOR INPUT PORTS:
             int spacing = 15;
             for(int i =0 ;i<numPorts[0] ;i++)
             {
             Text Plus1 = new Text("+");
             Plus1.setX(X_Y_CONSTANT[0]);
             Plus1.setY(X_Y_CONSTANT[1]+spacing);
             pane.getChildren().add(Plus1);
             if(i<numPorts[0]-1)
             pane.getChildren().add(new Arrow(X_Y_CONSTANT[0]-20,X_Y_CONSTANT[1]+spacing-5,X_Y_CONSTANT[0],X_Y_CONSTANT[1]+spacing-5));
             spacing+=10;
             }
            }
            if(block instanceof Scope){
              //SCOPE BLOCK GUI
             double[] X_Y_CONSTANT = block.getPosition();
             pane.getChildren().add(drawBlock(X_Y_CONSTANT));
             Text CONSTANT_TEXT = new Text("Scope");
             CONSTANT_TEXT.setX(X_Y_CONSTANT[0]);
             CONSTANT_TEXT.setY(X_Y_CONSTANT[1]+50);
             pane.getChildren().add(CONSTANT_TEXT);
             pane.getChildren().add(drawArrow(X_Y_CONSTANT));
            }
            if(block instanceof UnitDelay){
              //UNITDELAY BLOCK GUI
             double[] X_Y_CONSTANT = block.getPosition();
             pane.getChildren().add(drawBlock(X_Y_CONSTANT));
             Text CONSTANT_TEXT = new Text("Unit Delay");
             CONSTANT_TEXT.setX(X_Y_CONSTANT[0]);
             CONSTANT_TEXT.setY(X_Y_CONSTANT[1]+50);
             pane.getChildren().add(CONSTANT_TEXT);
             pane.getChildren().add(new Arrow(X_Y_CONSTANT[0]+60,X_Y_CONSTANT[1]+20,X_Y_CONSTANT[0]+40,X_Y_CONSTANT[1]+20));
            }
        }
        
        //LINES GUI 
        double[] START_POSITION=null;
        double[] END_POSITION=null;
        int line_counter=0;
        for(LineBlock test_line:Lines_Array){
              boolean Start_flag = false;
              boolean End_flag = false;
            for(Block test_block:Blocks_Array){
                //NORMAL LINES
                if (test_line.getSrcSID() == test_block.getSID() && test_line.getHasBranch() == false && test_line.HasDst == true)
                {
                    Start_flag = true;
                    START_POSITION = test_block.getPosition();
                }
                if(test_line.getDstSID() == test_block.getSID() &&test_line.getHasBranch() == false && test_line.HasDst == true){
                    End_flag = true;
                    END_POSITION = test_block.getPosition();
                }
                if(Start_flag == true && End_flag == true && test_line.HasDst == true && test_line.HasPoints == false){
                    Line NormalLine = new Line(START_POSITION[0]+40,START_POSITION[1]+20,END_POSITION[0],END_POSITION[1]+20);
                    line_counter++;
                    System.out.println(line_counter);
                    pane.getChildren().add(NormalLine);
                }
                if(Start_flag == true && End_flag == true && test_line.HasDst == true && test_line.HasPoints == true){
                    if(test_block instanceof UnitDelay){
                        UnitDelay x = (UnitDelay)(test_block);
                        if(x.isBlockMirror() == true){
                        double[] POINTS = test_line.getPoints();
                        pane.getChildren().add(new Line(START_POSITION[0],START_POSITION[1]+25,START_POSITION[0]+POINTS[0],START_POSITION[1]+25));
                        pane.getChildren().add(new Line(START_POSITION[0]+POINTS[0],START_POSITION[1]+25,START_POSITION[0]+POINTS[0],START_POSITION[1]+25+POINTS[3]));
                        pane.getChildren().add(new Arrow(START_POSITION[0]+POINTS[0],START_POSITION[1]+25+POINTS[3],END_POSITION[0],START_POSITION[1]+25+POINTS[3]));
                        pane.getChildren().add(new Line(START_POSITION[0]+POINTS[0],START_POSITION[1]+25+POINTS[3],END_POSITION[0],START_POSITION[1]+25+POINTS[3]));
                        }
                        else
                        {
                            //DRAW WITH REGULAR INPUT OUTPUT
                        }
                     }                
                    }
                //BAD LINES
                if (test_line.getSrcSID() == test_block.getSID() && test_line.getHasBranch() == true && test_line.HasDst == false)
                {
                    START_POSITION = test_block.getPosition();
                    END_POSITION = test_line.getPoints();
                    pane.getChildren().add(new Line(START_POSITION[0]+40,START_POSITION[1]+20,START_POSITION[0]+40+END_POSITION[0],START_POSITION[1]+20+END_POSITION[1]));
                }
            }
        }
        //BRANCHES GUI
        for(LineBlock LineIterator:Lines_Array){
                if(LineIterator.getHasBranch() == false)
                    continue;
                else
                {
                   ArrayList<Branch> BRANCHLIST = LineIterator.getBranchList();
                   //START POSITION FOR BLOCKS
                   double[] BLOCK_START_POSITION = null;
                   for(Block block:Blocks_Array){
                       if(LineIterator.getSrcSID() == block.getSID()){
                           BLOCK_START_POSITION = block.getPosition();
                           break;
                       }
                   }
                   //END POSITION FOR BLOCKS
                   for(Branch branch:BRANCHLIST){
                       double[] SAVED_END_POSITION=null;
                       Block BLOCK=null;
                       for(Block block:Blocks_Array){
                           if(block.getSID() == branch.getDstSID())
                           {
                               SAVED_END_POSITION = block.getPosition();
                               BLOCK = block;
                               break;
                           }
                       }
                       //NOW WE GOT END POSITION
                       if(branch.HasPoints == false){
                           //CHECK WHETHER HORIZONTAL OR VERTICAL
                           double[] linePoints = LineIterator.getPoints();
                           if(linePoints[1] ==0) //HORIZONTAL 
                           {
                               pane.getChildren().add(new Line(BLOCK_START_POSITION[0]+40+linePoints[0],BLOCK_START_POSITION[1]+20,SAVED_END_POSITION[0],BLOCK_START_POSITION[1]+20));
                           }
                           if(linePoints[0] ==0)
                           {
                               //TODO
                               //VERTICAL BRANCHES NOT IN TEST FILE ^_^
                           }
                       }
                       if(branch.HasPoints == true)
                       {    //TODO
                           //DRAWING BRANCHES WITH POINTS 
                        double[] linePoints = LineIterator.getPoints();
                        double[] branchPoints = branch.getPoints();
                        if(branchPoints[1] ==0) //HORIZONTAL 
                         {
                            //TODO
                         }
                        if(branchPoints[0] == 0)
                        {  
                           //VERTICAL
                           pane.getChildren().add(new Line(BLOCK_START_POSITION[0]+40+linePoints[0],BLOCK_START_POSITION[1]+20,
                           BLOCK_START_POSITION[0]+40+linePoints[0],BLOCK_START_POSITION[1]+20+branchPoints[1]));
                           if(!(BLOCK instanceof UnitDelay)){
                             pane.getChildren().add(new Line(BLOCK_START_POSITION[0]+40+linePoints[0],BLOCK_START_POSITION[1]+20+branchPoints[1],
                           SAVED_END_POSITION[0],BLOCK_START_POSITION[1]+20+branchPoints[1]));
                           }
                           else
                           {
                               pane.getChildren().add(new Line(BLOCK_START_POSITION[0]+40+linePoints[0],BLOCK_START_POSITION[1]+20+branchPoints[1],
                           SAVED_END_POSITION[0]+40,BLOCK_START_POSITION[1]+20+branchPoints[1]));
                           }
                        }
                       }
                   }    
                 }
        }
        Scene scene = new Scene(pane,1200,800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SimuLink Parser and GUI");
        primaryStage.show();
    }
    //DONT TOUCH THESE METHODS!!!!
    public static ArrayList<String> File_Reader(String filepath) throws Exception{
        File file = new File(filepath);
        Scanner input = new Scanner(file);
        ArrayList<String> lines = new ArrayList<String>();
        boolean useful_text = false;
            while(input.hasNext()){
                if(input.nextLine().matches("  <P Name=\"SimulinkSubDomain\">Simulink</P>"))
                {   
                    useful_text = true;
                    break;
                }
            }
            while(useful_text){
                String added_line = input.nextLine();
                lines.add(added_line);
                if(added_line.matches("</System>"))
                   useful_text = false;
            }
        return lines;
    }
    public static ArrayList<LineBlock> ParseLines(String filepath) throws Exception{
        ArrayList<String> lines = File_Reader(filepath);
        //LINE VARIABLES
        int Zorder_Line=0;
        String Src_Line=null;
        String Dst_Line=null;
        double[] points_Line=null;
        boolean Dst_Flag_Line = false;
        boolean Points_Flag_Line = false;
        ArrayList<LineBlock> Line_Array = new ArrayList<>();
        ArrayList<Branch> LineBranchArray = new ArrayList<>();
        
        //BRANCH VARIABLES
        int Zorder_Branch=0;
        String Dst_Branch=null;
        double[] points_Branch=null;
        boolean Points_Flag_Branch = false;
        ArrayList<Branch> Branch_Array = new ArrayList<>();
        //ArrayLists for lines and branches
        boolean branch_flag = false;
        boolean line_flag = false;
        boolean HasBranchWithinLine=false;
        for(String line:lines){
            ////////////////////////////////////////////////////////////////////////////
            ///////////////Line BLOCK/////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            if(line.matches(".*<Line>.*"))
            {
                line_flag = true;
                //LineBranchArray.clear();
            }
            if(line.matches(".*\"ZOrder\".*") && line_flag == true)
            {
                Zorder_Line = Zorder_extraction(line);
            }
            if(line.matches(".*\"Src\".*") && line_flag == true){
                Src_Line = Src_Dst_extraction(line);
            }
            if(line.matches(".*\"Dst\".*") && line_flag == true){
                Dst_Line = Src_Dst_extraction(line);
                Dst_Flag_Line = true;
            }
            if(line.matches(".*\"Points\".*") && line_flag == true){
                points_Line = Points_extraction(line);
                Points_Flag_Line = true;
            } 
            ////////////////////////////////////////////////////////////////////////////
            ///////////////Branch BLOCK/////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            /*if(line.matches(".*<Branch>.*"))
            {
                branch_flag = true;
                HasBranchWithinLine = true;
                line_flag = false;
            }*/
            if(line.matches(".*<Branch>.*")){
                line_flag = false;
                HasBranchWithinLine = true;
                branch_flag = true;
            }
            if(line.matches(".*\"Points\".*") && branch_flag == true){
                points_Branch = Points_extraction(line);
                Points_Flag_Branch = true;
            }
            if(line.matches(".*\"Dst\".*") && branch_flag == true){
                Dst_Branch = Src_Dst_extraction(line);
            }
            if(line.matches(".*\"ZOrder\".*") && branch_flag == true)
            {
                Zorder_Branch = Zorder_extraction(line);
            }
            if(line.matches(".*</Branch>.*")){
                branch_flag = false;
                //OBJECT DECLARATION THEN APPEND TO ArrayList<Branch>
                Branch x_branch;
                if(Points_Flag_Branch == true){
                    x_branch =new Branch(Zorder_Branch,points_Branch,Dst_Branch);
                    Branch_Array.add(x_branch);
                    LineBranchArray.add(x_branch);
                }
                else
                {   
                    x_branch = new Branch(Dst_Branch,Zorder_Branch);
                    Branch_Array.add(x_branch);
                    LineBranchArray.add(x_branch);
                }
                Points_Flag_Branch = false;
            }
            if(line.matches(".*</Line>.*"))
            {
                line_flag = false;
                //OBJECT DECLARATION THEN APPEND TO ArrayList<Line> !!
                if(Points_Flag_Line == true && Dst_Flag_Line == true){
                    Line_Array.add(new LineBlock(Zorder_Line,Src_Line,Dst_Line,points_Line,HasBranchWithinLine));
                }
                else if(Dst_Flag_Line == true && Points_Flag_Line == false){
                    Line_Array.add(new LineBlock(Zorder_Line,Src_Line,Dst_Line,HasBranchWithinLine));
                }
                else if(Points_Flag_Line == true && Dst_Flag_Line == false){
                    
                    Line_Array.add(new LineBlock(Zorder_Line,Src_Line,points_Line,HasBranchWithinLine,(ArrayList<Branch>)LineBranchArray.clone()));
                }
                LineBranchArray.clear();
                Points_Flag_Line = false;
                Dst_Flag_Line = false;
                HasBranchWithinLine = false;
            }
    }
        return Line_Array;
 }
     public static ArrayList<Branch> ParseBranches(String filepath) throws Exception{
        ArrayList<String> lines = File_Reader(filepath);
        //LINE VARIABLES
        int Zorder_Line=0;
        String Src_Line=null;
        String Dst_Line=null;
        double[] points_Line=null;
        boolean Dst_Flag_Line = false;
        boolean Points_Flag_Line = false;
        ArrayList<Line> Line_Array = new ArrayList<>();
        
        //BRANCH VARIABLES
        int Zorder_Branch=0;
        String Dst_Branch=null;
        double[] points_Branch=null;
        boolean Points_Flag_Branch = false;
        ArrayList<Branch> Branch_Array = new ArrayList<>();
        //ArrayLists for lines and branches
        boolean branch_flag = false;
        boolean line_flag = false;
        boolean HasBranchWithinLine = false;
        for(String line:lines){
            ////////////////////////////////////////////////////////////////////////////
            ///////////////Line BLOCK/////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            /*if(line.matches(".*<Line>.*"))
            {
                line_flag = true;
            }
            if(line.matches(".*\"ZOrder\".*") && line_flag == true)
            {
                Zorder_Line = Zorder_extraction(line);
            }
            if(line.matches(".*\"Src\".*") && line_flag == true){
                Src_Line = Src_Dst_extraction(line);
            }
            if(line.matches(".*\"Dst\".*") && line_flag == true){
                Dst_Line = Src_Dst_extraction(line);
                Dst_Flag_Line = true;
            }
            if(line.matches(".*\"Points\".*") && line_flag == true){
                points_Line = Points_extraction(line);
                Points_Flag_Line = true;
            }
            if(line.matches(".*\"<Branch>\".*") && line_flag == true){
                line_flag = false;
            }
            if(line_flag == false && branch_flag == true && line.matches(".*\"</Branch>\".*"))
            {
                line_flag = true;
            }
            if(line.matches(".*</Line>.*") && line_flag == true)
            {
                line_flag = false;
                //OBJECT DECLARATION THEN APPEND TO ArrayList<Line> !!
                if(Points_Flag_Line == true && Dst_Flag_Line == true){
                    Line_Array.add(new Line(Zorder_Line,Src_Line,Dst_Line,points_Line,HasBranchWithinLine));
                }
                else if(Dst_Flag_Line == true && Points_Flag_Line == false){
                    Line_Array.add(new Line(Zorder_Line,Src_Line,Dst_Line));
                }
                else if(Points_Flag_Line == true && Dst_Flag_Line == false){
                    Line_Array.add(new Line(Zorder_Line,Src_Line,points_Line));
                }
                Points_Flag_Line = false;
                Dst_Flag_Line = false;
                
            }*/
            ////////////////////////////////////////////////////////////////////////////
            ///////////////Branch BLOCK/////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            if(line.matches(".*<Branch>.*"))
            {
                branch_flag = true;
            }
            if(line.matches(".*\"Points\".*") && branch_flag == true){
                points_Branch = Points_extraction(line);
                Points_Flag_Branch = true;
            }
            if(line.matches(".*\"Dst\".*") && branch_flag == true){
                Dst_Branch = Src_Dst_extraction(line);
            }
            if(line.matches(".*\"ZOrder\".*") && branch_flag == true)
            {
                Zorder_Branch = Zorder_extraction(line);
            }
            if(line.matches(".*</Branch>.*")){
                branch_flag = false;
                //OBJECT DECLARATION THEN APPEND TO ArrayList<Branch>
                if(Points_Flag_Branch == true){
                    Branch_Array.add(new Branch(Zorder_Branch,points_Branch,Dst_Branch));
                }
                else
                {
                    Branch_Array.add(new Branch(Dst_Branch,Zorder_Branch));
                }
                Points_Flag_Branch = false;
            }
        }
        return Branch_Array;
    }
    public static ArrayList<Block> ParseBlocks(String filepath) throws Exception{
        //GETTING FILE USEFUL CONTENT
        ArrayList<String> lines = File_Reader(filepath);
        //VALID
        //Getting all attributes in, looping:
        //Useful flags for parsing, disables parsing attributes which aren't meant for a specific object 
        boolean sum_flag = false;
        boolean constant_flag = false;
        boolean saturate_flag = false;
        boolean scope_flag = false;
        boolean unitdelay_flag = false;
        boolean branch_flag = false;
        boolean line_flag = false;
        
        //SUM VARIABLES
        String name_Sum = null;
        int SID_Sum = 0;
        double[] ports_Sum=null;
        double[] position_Sum=null;
        int Zorder_Sum=0;
        String IconShape_Sum= null;
        String Inputs_Sum = null;
        
        //CONSTANT VARIABLES
        String name_Constant=null;
        int SID_Constant=0;
        double[] position_Constant=null;
        int Zorder_Constant=0;
        
        //SATURATE VARIABLES
        String name_Saturate=null;
        int SID_Saturate=0;
        double[] position_Saturate=null;
        int Zorder_Saturate=0;
        
        //SCOPE VARIABLES
        String name_Scope=null;
        int SID_Scope=0;
        double[] ports_Scope=null;
        double[] position_Scope=null;
        int Zorder_Scope=0;
        boolean Floating=false;
        int NumInputPorts=0;
        String ScopeSpecificationString=null;
        
        //UNITDELAY VARIABLES
        String name_UnitDelay=null;
        int SID_UnitDelay=0;
        double[] position_UnitDelay=null;
        int Zorder_UnitDelay=0;
        boolean BlockMirror_UnitDelay=true;
        double SampleTime_UnitDelay=0;
        boolean HasFrameUpgradeWarning_UnitDelay=false;
        
        //LINE VARIABLES
        int Zorder_Line=0;
        String Src_Line=null;
        String Dst_Line=null;
        double[] points_Line=null;
        boolean Dst_Flag_Line = false;
        boolean Points_Flag_Line = false;
        ArrayList<Line> Line_Array = new ArrayList<>();
        
        //BRANCH VARIABLES
        int Zorder_Branch=0;
        String Dst_Branch=null;
        double[] points_Branch=null;
        boolean Points_Flag_Branch = false;
        ArrayList<Branch> Branch_Array = new ArrayList<>();
        //ArrayLists for lines and branches
        for(String line : lines){
            
            //////////////////////////////////////////////////////////////////////
            ///////////////////////////SUM BLOCK /////////////////////////////////
            //////////////////////////////////////////////////////////////////////
            //SUM name and SID extraction
            if(line.matches(".*\"Sum\".*")){
                SID_Sum = SID_extraction(line);
                name_Sum = name_extraction(line);
                sum_flag = true;
            }
            //SUM PORTS EXTRACTION
            if(line.matches(".*\"Ports\".*") && sum_flag == true)
            {
                ports_Sum = ports_extraction(line);
            }
            
            //SUM position extraction
            if(line.matches(".*\"Position\".*") && sum_flag == true)
            {
               position_Sum = position_extraction(line);
            }
            //SUM Zorder extraction
            if(line.matches(".*\"ZOrder\".*") && sum_flag == true){
                Zorder_Sum = Zorder_extraction(line);
            }
            //SUM IconShape extraction
            if(line.matches(".*\"IconShape\".*") && sum_flag == true){
                IconShape_Sum = IconShape_Inputs_extraction(line);
            }
            //SUM Inputs extraction
            if(line.matches(".*\"Inputs\".*") && sum_flag == true){
                Inputs_Sum = IconShape_Inputs_extraction(line);
                sum_flag = false;
            }
            ////////////////////////////////////////////////////////////////////////////
            ///////////////CONSTANT BLOCK///////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            //CONSTANT name and SID 
            if(line.matches(".*\"Constant\".*")){
                name_Constant = name_extraction(line);
                SID_Constant = SID_extraction(line);
                constant_flag = true;
            }
            //CONSTANT position
            if(line.matches(".*\"Position\".*")&& constant_flag == true){
                position_Constant = position_extraction(line);
            }
            //CONSTANT Zorder
            if(line.matches(".*\"ZOrder\".*") && constant_flag == true){
                Zorder_Constant = Zorder_extraction(line);
                constant_flag = false;
            }
            ////////////////////////////////////////////////////////////////////////////
            ///////////////SATURATE BLOCK///////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            //SATURATE name and SID
            if(line.matches(".*\"Saturate\".*")){
                name_Saturate = name_extraction(line);
                SID_Saturate = SID_extraction(line);
                saturate_flag = true;
            }
            //SATURATE position 
            if(line.matches(".*\"Position\".*") && saturate_flag == true){
                position_Saturate = position_extraction(line);
            }
            //SATURATE Zorder
            if(line.matches(".*\"ZOrder\".*") && saturate_flag == true){
                Zorder_Saturate = Zorder_extraction(line);
                saturate_flag = false;
            }
            ////////////////////////////////////////////////////////////////////////////
            ///////////////SCOPE BLOCK/////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            //SCOPE name and SID
            if(line.matches(".*\"Scope\".*")){
                name_Scope = name_extraction(line);
                SID_Scope = SID_extraction(line);
                scope_flag = true;
            }
            //SCOPE ports
            if(line.matches(".*\"Ports\".*") && scope_flag == true){
                ports_Scope = ports_extraction(line);
            }
            //SCOPE position
            if(line.matches(".*\"Position\".*") && scope_flag == true){
                position_Scope = position_extraction(line);
            }
            //SCOPE Zorder
            if(line.matches(".*\"ZOrder\".*") && scope_flag == true){
                Zorder_Scope = Zorder_extraction(line);
            }
            //SCOPE ScopeSpecificationString
            if(line.matches(".*\"ScopeSpecificationString\".*") && scope_flag == true){
                ScopeSpecificationString = ScopeSpecificationString_extraction(line);
            }
            //SCOPE NumInputPorts
            if(line.matches(".*\"NumInputPorts\".*") && scope_flag == true){
                NumInputPorts = NumInputPorts_extraction(line);
            }
            //SCOPE Floating
            if(line.matches(".*\"Floating\".*") && scope_flag == true){
                Floating = Floating_extraction(line);
                scope_flag = false;
            }
            ////////////////////////////////////////////////////////////////////////////
            ///////////////UnitDelay BLOCK/////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            //UnitDelay name and SID
            if(line.matches(".*\"UnitDelay\".*")){
                name_UnitDelay = name_extraction(line);
                SID_UnitDelay = SID_extraction(line);
                unitdelay_flag = true;
            }
            //UnitDelay position
            if(line.matches(".*\"Position\".*") && unitdelay_flag == true){
                position_UnitDelay = position_extraction(line);
            }
            //UnitDelay Zorder
            if(line.matches(".*\"ZOrder\".*") && unitdelay_flag == true ){
                Zorder_UnitDelay = Zorder_extraction(line);
            }
            //UnitDelay BlockMirror
            if(line.matches(".*\"BlockMirror\".*") && unitdelay_flag == true){
                BlockMirror_UnitDelay = BlockMirror_HasFrameUpgradeWarning_extraction(line);
            }
            //UnitDelay SampleTime
            if(line.matches(".*\"SampleTime\".*") && unitdelay_flag == true){
                SampleTime_UnitDelay = SampleTime_extraction(line);
            }
            //UnitDelay HasFrameUpgradeWarning
            if(line.matches(".*\"HasFrameUpgradeWarning\".*") && unitdelay_flag == true){
                HasFrameUpgradeWarning_UnitDelay = BlockMirror_HasFrameUpgradeWarning_extraction(line);
                unitdelay_flag = true;
            }
            ////////////////////////////////////////////////////////////////////////////
            ///////////////Line BLOCK/////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            /*if(line.matches(".*<Line>.*"))
            {
                line_flag = true;
            }
            if(line.matches(".*\"ZOrder\".*") && line_flag == true)
            {
                Zorder_Line = Zorder_extraction(line);
            }
            if(line.matches(".*\"Src\".*") && line_flag == true){
                Src_Line = Src_Dst_extraction(line);
            }
            if(line.matches(".*\"Dst\".*") && line_flag == true){
                Dst_Line = Src_Dst_extraction(line);
                Dst_Flag_Line = true;
            }
            if(line.matches(".*\"Points\".*") && line_flag == true){
                points_Line = Points_extraction(line);
                Points_Flag_Line = true;
            }
            if(line.matches(".*\"<Branch>\".*") && line_flag == true){
                line_flag = false;
            }
            if(line_flag == false && branch_flag == true && line.matches(".*\"</Branch>\".*"))
            {
                line_flag = true;
            }
            if(line.matches(".*</Line>.*") && line_flag == true)
            {
                line_flag = false;
                //OBJECT DECLARATION THEN APPEND TO ArrayList<Line> !!
                if(Points_Flag_Line == true && Dst_Flag_Line == true){
                    Line_Array.add(new Line(Zorder_Line,Src_Line,Dst_Line,points_Line));
                }
                else if(Dst_Flag_Line == true && Points_Flag_Line == false){
                    Line_Array.add(new Line(Zorder_Line,Src_Line,Dst_Line));
                }
                else if(Points_Flag_Line == true && Dst_Flag_Line == false){
                    Line_Array.add(new Line(Zorder_Line,Src_Line,points_Line));
                }
                Points_Flag_Line = false;
                Dst_Flag_Line = false;
                
            }
            ////////////////////////////////////////////////////////////////////////////
            ///////////////Branch BLOCK/////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
            if(line.matches(".*<Branch>.*"))
            {
                branch_flag = true;
            }
            if(line.matches(".*\"Points\".*") && branch_flag == true){
                points_Branch = Points_extraction(line);
                Points_Flag_Branch = true;
            }
            if(line.matches(".*\"Dst\".*") && branch_flag == true){
                Dst_Branch = Src_Dst_extraction(line);
            }
            if(line.matches(".*\"ZOrder\".*") && branch_flag == true)
            {
                Zorder_Branch = Zorder_extraction(line);
            }
            if(line.matches(".*</Branch>.*")){
                branch_flag = false;
                //OBJECT DECLARATION THEN APPEND TO ArrayList<Branch>
                if(Points_Flag_Branch == true){
                    Branch_Array.add(new Branch(Zorder_Branch,points_Branch,Dst_Branch));
                }
                else
                {
                    Branch_Array.add(new Branch(Dst_Branch,Zorder_Branch));
                }
                Points_Flag_Branch = false;
            }*/
        }
        //BLOCKS INITILIZATION 
        Sum sum = new Sum(name_Sum, position_Sum, Zorder_Sum, SID_Sum, ports_Sum, IconShape_Sum, Inputs_Sum);
        Constant constant = new Constant(name_Constant, SID_Constant, position_Constant, Zorder_Constant);
        UnitDelay unitdelay = new UnitDelay(name_UnitDelay, SID_UnitDelay, position_UnitDelay, Zorder_UnitDelay, BlockMirror_UnitDelay, SampleTime_UnitDelay, HasFrameUpgradeWarning_UnitDelay);
        Scope scope = new Scope(name_Scope, SID_Scope, position_Scope, Zorder_Scope, ScopeSpecificationString, NumInputPorts, Floating);
        Saturate saturate = new Saturate(name_Saturate, SID_Saturate, position_Saturate, Zorder_Saturate);
        ArrayList<Block> Blocks_Array = new ArrayList<>();
        Blocks_Array.add(sum);
        Blocks_Array.add(constant);
        Blocks_Array.add(unitdelay);
        Blocks_Array.add(scope);
        Blocks_Array.add(saturate);
        return Blocks_Array;
        //TEST
        /*sum.print();
        System.out.println("--------END SUM--------");
        constant.print();
        System.out.println("--------CONSTANT END--------");
        unitdelay.print();
        System.out.println("--------UNITDELAY END--------");
        scope.print();
        System.out.println("--------SCOPE END-------------");
        saturate.print();
        System.out.println("--------SATURATE END-----------");
        int line_counter = 0;
        for(Line test_line:Line_Array){
            test_line.print();
            line_counter++;
            System.out.println("---------Line:"+line_counter+"----------");
        }
        int branch_counter =0;
        for(Branch test_branch:Branch_Array){
            test_branch.print();
            branch_counter ++;
            System.out.println("--------Branch:"+branch_counter+"----------");
        }*/
    }
    
    public static void main(String[] args) throws Exception{
        /*Scanner in = new Scanner(System.in);
        System.out.println("Please enter the file path: ");
        String filepath = in.nextLine();
        ArrayList<Block> Blocks_Array = ParseBlocks(filepath);
        ArrayList<LineBlock> Lines_Array = ParseLines(filepath);
        ArrayList<Branch> Branch_Array = ParseBranches(filepath);*/
        //TEST
        /*for(Block block:Blocks_Array){
            block.print();
            System.out.println("----------------------");
        }
        int line_count = 0;
        for(LineBlock line:Lines_Array){
            line.print();
            System.out.println("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>");
            if(line.HasBranch == true)
            line.printBranchesWithinLine();
            line_count ++;
            System.out.println("---------LINE: "+line_count);
        }
        int branch_count =0;
        for(Branch branch:Branch_Array){
            branch.print();
            branch_count++;
            System.out.println("----------Branch:"+branch_count);
        }*/
        Application.launch();
    }
    /////////////////////////////////////////////////////
    //////////////////EXTRACTION METHODS/////////////////
    /////////////////////////////////////////////////////
    public static String name_extraction(String line){
           if(line.matches(".*\"Unit Delay\".*"))
           {
               int name_index = line.indexOf("N");
               return line.substring(name_index+6,name_index+16);
           }
           String [] name_arr = line.strip().split(" ");
           //NAME EXTRACTION
           int name_index = name_arr[2].indexOf("\"");
           return name_arr[2].substring(name_index+1,name_arr[2].length()-1);
    }
    public static int SID_extraction(String line){
           if(line.matches(".*\"Unit Delay\".*")){
               return Integer.parseInt(line.substring(line.length()-3,line.length()-2));
           }
           String [] SID_arr = line.strip().split(" ");
           //SID EXTRACTION
           int sid_index = SID_arr[3].indexOf("\"");
           return Integer.parseInt(SID_arr[3].substring(sid_index+1,sid_index+2));
    }
    public static double[] ports_extraction(String line){
        String[] ports_arr = line.split(">");
        int ports_end = ports_arr[1].indexOf("<");
        String ports_str =ports_arr[1].substring(1,ports_end-1);
        String[] ports_arr2 = ports_str.split(",");
        if (line.matches(".*,.*")){
        //ports_Sum
        double[] ports = {Double.parseDouble(ports_arr2[0]),Double.parseDouble(ports_arr2[1])};
        return ports; 
        }
        else{
        double[] ports = {Double.parseDouble(ports_arr[1].replaceAll("\\D+",""))};
        return ports;
        }
    } 
    public static double[] position_extraction(String line){
        String[] position_arr = line.split(">");
        String position_str = position_arr[1].substring(0, position_arr[1].length() -3);
        position_str = position_str.substring(1,position_str.length()-1);
        String[] position_arr2 = position_str.split(",");
        double[] position = {Double.parseDouble(position_arr2[0]),Double.parseDouble(position_arr2[1]),Double.parseDouble(position_arr2[2]),Double.parseDouble(position_arr2[3])};
        return position;
    }
    public static int Zorder_extraction(String line){
        String Zorder_str = line.replaceAll("\\D+", "");
        return Integer.parseInt(Zorder_str);
    }
    public static String IconShape_Inputs_extraction(String line){
        String[] arr = line.split(">");
        return arr[1].substring(0,arr[1].length()-3);
    } 
    public static String ScopeSpecificationString_extraction(String line){
        String[] arr = line.split(">");
        int end_index;
        if (arr[1].indexOf("<") == 0)
            return "";
        else
        {
            end_index = arr[1].indexOf("<");
            return arr[1].substring(0,end_index-1);
        }
    }
    public static int NumInputPorts_extraction(String line){
        String[] arr = line.split(">");
        int end_index = arr[1].indexOf("<");
        return Integer.parseInt(arr[1].substring(0,end_index));
    }
    public static boolean Floating_extraction(String line){
        String[] arr = line.split(">");
        int end_index = arr[1].indexOf("<");
        if(arr[1].substring(0,end_index).matches(".*off.*"))
            return false;
        else
            return true;
    }
    public static boolean BlockMirror_HasFrameUpgradeWarning_extraction(String line){
        String[] arr = line.split(">");
        int end_index = arr[1].indexOf("<");
        if(arr[1].substring(0,end_index).matches(".*off.*"))
            return false;
        else
            return true;
    }
    public static double SampleTime_extraction(String line){
        String[] arr = line.split(">");
        int end_index = arr[1].indexOf("<");
        return Double.parseDouble(arr[1].substring(0,end_index));
    }
    public static String Src_Dst_extraction(String line){
        String[] arr = line.split(">");
        return arr[1].substring(0,arr[1].length()-3);
    }
    public static double[] Points_extraction(String line){
        String[] arr = line.split(">");
        int end_index = arr[1].indexOf("<");
        int comma_count = 0;
        char x;
        for(int i =0 ; i<line.length();i++){
            x = line.charAt(i);
            if(x == ',')
            {
                comma_count ++;
            }
        }
        if(comma_count ==2){
            //four points
            String points_str = arr[1].substring(1,end_index-1);
            arr = points_str.split(";");
            String[] arr1 = arr[0].split(",");
            String[] arr2 = arr[1].split(",");
            double[] points = {Double.parseDouble(arr1[0]),Double.parseDouble(arr1[1]),Double.parseDouble(arr2[0]),Double.parseDouble(arr2[1])};
            comma_count = 0; 
            return points;
        }
        else
            //two points only
        {
            String points_str = arr[1].substring(1,end_index-1);
            arr = points_str.split(",");
            double[] points = {Double.parseDouble(arr[0]),Double.parseDouble(arr[1])};
            comma_count = 0; 
            return points;
        }
    }
}

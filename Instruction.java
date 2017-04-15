package edu.clemson.cspsc2150.project4;



/**
 * Created by Colin on 4/8/2017.
 */
public class Instruction {
    private int destR; //destination register
    private int src1;  //src1 register, or could be offset for load and store instructions
    private int src2;  //source 2 register, or source 1 register for load and store
    private String op;
    private boolean dependent;

    public Instruction(int destR, int src1, int src2, String op, boolean dependent) {
        this.destR = destR;
        this.src1 = src1;
        this.src2 = src2;
        this.op = op;
        this.dependent = dependent;
    }
    public Instruction(){

        this.destR = -1;
        this.src1 = -1;
        this.src2 = -1;
        this.op = "";
        this.dependent = false;
    }

    public int getDestR() {
        return destR;
    }

    public void setDestR(int destR) {
        this.destR = destR;
    }

    public int getSrc1() {
        return src1;
    }

    public void setSrc1(int src1) {
        this.src1 = src1;
    }

    public int getSrc2() {
        return src2;
    }

    public void setSrc2(int src2) {
        this.src2 = src2;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public boolean isDependent() {
        return dependent;
    }

    public void setDependent(boolean dependent) {
        this.dependent = dependent;
    }


    @Override
    public String toString() {
        String instruction = op;

        if(op.equals("sub") == true || op.equals("add") == true){
            instruction += " $" + destR + ",$" + src1 + ",$"  + src2;
        }
        else if(op.equals("lw") || op.equals("sw")){
            instruction += " $" + destR + "," + src1 +"($" + src2 + ")";
            //src 1 becomes the offset and src2 becomes the source register
        }
        else{
            instruction += " $" + destR + ",$" + src1 + ",(" + src2 + ")";
        }
        return instruction;
    }

    public void checkDependency(Instruction I){

    }
}

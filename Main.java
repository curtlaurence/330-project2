
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args)throws Exception {    File myFile = new File(args[0]);
        System.out.println("Attempting to read from file in: "+myFile.getCanonicalPath());

        Scanner input = new Scanner(myFile);
        String in = "";
        int i = 0;
        ArrayList <Instruction> instList = new ArrayList();
        Instruction[] instructions = new Instruction[8];

        String[] splits = new String[4];
        while(input.hasNext()){
            Instruction newInst = new Instruction();
            in = input.nextLine();

            // System.out.println(instruction.op);

            splits = in.split("\\W+");


            newInst.setOp(splits[0]);
            newInst.setDestR(Integer.parseInt(splits[1]));
            newInst.setSrc1(Integer.parseInt(splits[2]));
            newInst.setSrc2(Integer.parseInt(splits[3]));


            instList.add(newInst);
           /*
            instructions[i].setOp(splits[0]);
            instructions[i].setDestR(Integer.parseInt(splits[1]));
            instructions[i].setSrc1(Integer.parseInt(splits[2]));
            instructions[i].setSrc2(Integer.parseInt(splits[3]));

            */

            //System.out.println(fuck.getOp() + " " + fuck.getDestR() + " " + fuck.getSrc1() + " " + fuck.getSrc2());
            //System.out.println(instList.get(i));

            i++;

        }

        instList.get(0).setDependent(true);
        System.out.println(instList);
        checkDependancy(instList);

        int loadDelay = 3;


        //printing out the instructions
        System.out.println("load delay set to 3");
        System.out.println("level 0 instructions");
        int level = 0;
        boolean printedIndependent;

            //first, we print the first instruction
            System.out.println("    0 " + instList.get(0));
            if(loadDelay(instList.get(0))){
                level += loadDelay;
            }
            //next print out the independent instructions
        for (Instruction I: instList) {
            if(I.isDependent() == false){
                System.out.println("    " + instList.indexOf(I) + " " + I);
            }
        }

        for (int j = 1; j < instList.size(); j++) {

            if(instList.get(j).isDependent() == true) {
                System.out.println("level " + level + " instructions");
                System.out.println("    " + j + " " + instList.get(j));
                if(loadDelay(instList.get(j))) {
                    level += loadDelay;
                }
                else{
                    level++;
                }
            }

        }

        System.out.println("The data flow can be executed in " + level  + " cycles");










	// write your code here
    }

    public static void checkDependancy (ArrayList <Instruction> list){
        for (int i = 0; i < list.size() - 1; i++) {
            Instruction temp = list.get(i);
            for (int j = i + 1; j > 0; j--) {
                if(temp.getDestR() != list.get(j).getDestR() && temp.getDestR() != list.get(j).getSrc1() && temp.getDestR() != list.get(j).getSrc2()) {
                    list.get(j).setDependent(false); //the instruction is not dependent on the instructions before it
                }
                else {
                    list.get(j).setDependent(true);
                    break;
                }
            }

        }


    }
    public static boolean loadDelay(Instruction I){
        if (I.getOp().equals("lw") ){
            return true;
        }
        else return false;


    }


}

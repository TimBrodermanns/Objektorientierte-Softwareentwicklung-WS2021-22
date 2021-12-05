
import bank.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        PrivateBank pb = new PrivateBank();
        ArrayList<Transaction> list = new ArrayList<>();
        list.add(new Payment("One", 1000,"",1,1));

        ArrayList<Transaction> list1 = new ArrayList<>();
        list1.add(new Payment("One", 1000,"",1,1));
        list1.add(new Transfer("One", 1000,"","",""));
        //pb.createAccount("User1", list);
        //pb.createAccount("User2", list1);
        try {

            //pb.writeAccount("User1");
            //pb.writeAccount("User2");


            System.out.println(pb.getAccountBalance("User1"));
            System.out.println(pb.getAccountBalance("User2"));


        }catch (Exception e){
            System.out.println(e.getMessage());
        }




    }
}

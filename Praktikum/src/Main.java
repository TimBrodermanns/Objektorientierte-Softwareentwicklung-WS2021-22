
import bank.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        PrivateBank pb = new PrivateBank();
        ArrayList<Transaction> list = new ArrayList<>();
        list.add(new Payment("One", 1000,"",1,1));
        list.add(new Payment("Two", 3000,"",1,1));
        list.add(new Payment("Three", 2000,"",1,1));
        pb.createAccount("User1", list);
        try {
            pb.writeAccount("User1");

            pb.readAccounts();


        }catch (Exception e){
            System.out.println(e.getMessage());
        }




    }
}

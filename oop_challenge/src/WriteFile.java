import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteFile implements Command {

    public void execute(Message msg) {
        if (msg.getTransactionType().equals("DepositTransaction") && (msg.getTransactionValue() > 10000)){
            String str = "";
            StringBuilder sb = new StringBuilder();
            sb.append(msg.getCustomerId() + "," + msg.getAccountId() + "," + msg.getTransactionValue());
            str = sb.toString();

            DateFormat dateFormat = new SimpleDateFormat("MMddYYYY");
            Date date = new Date();
            File depositFile = new File("DEPOSITSTOREVIEW-" + dateFormat.format(date) + ".txt");
            try{
                if(!depositFile.exists()){
                    System.out.println("Creating new file.");
                    depositFile.createNewFile();
                }
                FileWriter out = new FileWriter(depositFile,true);
                out.write(str + "\n");
                out.close();
            }catch(IOException e){
                System.out.println("Could not write to file.");
            }
        }
    }

}


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BBlisten implements ActionListener{
    private String buttonID;
   
	
    public BBlisten(String arg){
        buttonID = arg;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(this.buttonID == "CONNECT"){
            System.out.println("Heard CONNECT");
        }


    }


}
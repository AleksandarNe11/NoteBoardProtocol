
import javax.swing.JButton;

public class BBbutton extends JButton {
    private String ID;

    public BBbutton(String ID){
        this.ID = ID;
    }

    public String getID(){
        return this.ID;
    }

}

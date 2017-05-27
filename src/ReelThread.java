import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
//import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ReelThread{
	JButton reel1;
	static boolean reel1clicked = false;
	static String img1 = null;
        
	public static ArrayList<Symbol> symbol = new ArrayList<Symbol>();
        
	public ReelThread(){
		
                
        Symbol bell = new Symbol();
        bell.setImage("C:/Users/Thamashi/Documents/NetBeansProjects/SlotMachineApplication/images/bell.png");
        bell.setValue(6);

        Symbol cherry = new Symbol();
        cherry.setImage("C:/Users/Thamashi/Documents/NetBeansProjects/SlotMachineApplication/images/cherry.png");
        cherry.setValue(2);

        Symbol lemon = new Symbol();
        lemon.setImage("C:/Users/Thamashi/Documents/NetBeansProjects/SlotMachineApplication/images/lemon.png");
        lemon.setValue(3);

        Symbol plum = new Symbol();
        plum.setImage("C:/Users/Thamashi/Documents/NetBeansProjects/SlotMachineApplication/images/plum.png");
        plum.setValue(4);

        Symbol redseven = new Symbol();
        redseven.setImage("C:/Users/Thamashi/Documents/NetBeansProjects/SlotMachineApplication/images/redseven.png");
        redseven.setValue(7);

        Symbol watermelon = new Symbol();
        watermelon.setImage("C:/Users/Thamashi/Documents/NetBeansProjects/SlotMachineApplication/images/watermelon.png");
        watermelon.setValue(5);

        symbol.add(bell);
        symbol.add(cherry);
        symbol.add(lemon);
        symbol.add(plum);
        symbol.add(redseven);
        symbol.add(watermelon);
	}
        
        public ArrayList Spin(ArrayList s) {
        Collections.shuffle(s);
        return s;
    }


}

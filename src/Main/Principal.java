package Main;
import Swing.Menu;

public class Principal {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            		new Menu();
                }
           	});
	}

}

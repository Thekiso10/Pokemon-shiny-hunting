package Swing;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

import Hilos.HiloTiempo;
import Main.Documentos;

public class Menu {

	@SuppressWarnings("unused")
	private Marco marco;
	private HiloTiempo hiloTiempo;
	private Documentos documentos;
	
	public Menu() {
		this.hiloTiempo = new HiloTiempo();
		this.documentos = new Documentos();
		this.marco = new Marco();
	}
	
	class Marco extends JFrame {

		private static final long serialVersionUID = 1L;
		private PanelMenu pm;
		
		public Marco() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(600,400);
			//Bloquear que no se pueda redimencionar la ventana
			setResizable(false);
			//Colocar en medio de la pantalla
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			pm = new PanelMenu(hiloTiempo, documentos);
			
			add(pm); 
			setVisible(true);
		}
		
	}
}

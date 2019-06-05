package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Hilos.HiloTiempo;
import Main.Documentos;

public class PanelMenu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private HiloTiempo hilo;
	private Documentos doc;
	
	private Image background;
	//Labels
	private JLabel LTiempo;
	private JLabel LContador;
	//Botones
	private JButton BtnSum;
	private JButton BtnRun;
	private JButton BtnStop;
	private JButton BtnSalir;
	private JButton BtnRest;
	
	private int contador = 0;
	
	public PanelMenu(HiloTiempo hilo, Documentos doc) {
		setLayout(null);
		setBackground("resource/BackGround.jpg");
		//Cargar objectos
		this.hilo = hilo;
		this.doc = doc;
		//Titulo
		JLabel l1 = new JLabel("Aplicacion para Shiny Hunting"); 
		l1.setBounds(90,20,425,30);
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setFont(new Font("Century Gothic",Font.PLAIN,26));
		l1.setForeground(Color.WHITE);
		//Inputs
		Border border = BorderFactory.createLineBorder(Color.BLACK, 3);

		LTiempo = new JLabel();
		LTiempo.setBounds(50,70,500,55);
		LTiempo.setHorizontalAlignment(JLabel.CENTER);
		LTiempo.setFont(new Font("Century Gothic",Font.PLAIN,40));
		LTiempo.setForeground(Color.BLACK);
		LTiempo.setBorder(border);
		LTiempo.setBackground(Color.WHITE);
		LTiempo.setOpaque(true);
		
		LContador = new JLabel();
		LContador.setBounds(50,135,500,55);
		LContador.setHorizontalAlignment(JLabel.CENTER);
		LContador.setFont(new Font("Century Gothic",Font.PLAIN,40));
		LContador.setForeground(Color.BLACK);
		LContador.setBorder(border);
		LContador.setBackground(Color.WHITE);
		LContador.setOpaque(true);
		//Botones
		BtnSum = new JButton("Sumar Huevo");
		BtnSum.setBounds(50,230,150,50);  
		BtnSum.setBackground(Color.BLUE);
		BtnSum.setForeground(Color.WHITE);
		BtnSum.setFont(new Font("Century Gothic",Font.PLAIN,18));
		BtnSum.setBorder(border);
		
		BtnRun = new JButton("Iniciar reloj");
		BtnRun.setBounds(225,230,150,50);  
		BtnRun.setBackground(Color.GREEN);
		BtnRun.setForeground(Color.BLACK);
		BtnRun.setFont(new Font("Century Gothic",Font.PLAIN,18));
		BtnRun.setBorder(border);
		
		BtnStop = new JButton("Parar reloj");
		BtnStop.setBounds(400,230,150,50);  
		BtnStop.setBackground(Color.RED);
		BtnStop.setForeground(Color.BLACK);
		BtnStop.setFont(new Font("Century Gothic",Font.PLAIN,18));
		BtnStop.setBorder(border);
		BtnStop.setEnabled(false);
		
		BtnSalir = new JButton("Salir y Guardar");
		BtnSalir.setBounds(85,300,205,50);  
		BtnSalir.setBackground(Color.BLACK);
		BtnSalir.setForeground(Color.WHITE);
		BtnSalir.setFont(new Font("Century Gothic",Font.PLAIN,21));
		
		BtnRest = new JButton("Resetear");
		BtnRest.setBounds(310,300,205,50);  
		BtnRest.setBackground(Color.BLACK);
		BtnRest.setForeground(Color.WHITE);
		BtnRest.setFont(new Font("Century Gothic",Font.PLAIN,21));
		
		BtnSum.addActionListener(this);
		BtnRun.addActionListener(this);
        BtnStop.addActionListener(this);
        BtnSalir.addActionListener(this);
        BtnRest.addActionListener(this);
		//Cargamos los datos
        CargarDatos();
        
		add(l1);
		add(this.LTiempo);
		add(this.LContador);
		add(BtnSum);
		add(BtnRun);
		add(BtnStop);
		add(BtnSalir);
		add(BtnRest);
		
		this.hilo.setPanelMenu(this);
	}
	
	// Metodo que es llamado automaticamente por la maquina virtual Java cada vez que repinta
	public void paintComponent(Graphics g) {
 
		/* Obtenemos el tamaño del panel para hacer que se ajuste a este
		cada vez que redimensionemos la ventana y se lo pasamos al drawImage */
		int width = this.getSize().width;
		int height = this.getSize().height;
 
		// Mandamos que pinte la imagen en el panel
		if (this.background != null) {
			g.drawImage(this.background, 0, 0, width, height, null);
		}
 
		super.paintComponent(g);
	}
 
	// Metodo donde le pasaremos la dirección de la imagen a cargar.
	public void setBackground(String imagePath) {
		
		// Construimos la imagen y se la asignamos al atributo background.
		this.setOpaque(false);
		this.background = new ImageIcon(imagePath).getImage();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent Boton) {
		
		if(Boton.getSource() == BtnRun) {
			if(hilo.getPausa()) {
				hilo.Reanudar();
			}else {
				hilo.start();
			}
			//Canviar estado botones
			BtnRun.setEnabled(false);
			BtnStop.setEnabled(true);
		}else if(Boton.getSource() == BtnStop) {
			hilo.Pausar();
			//Canviar estado botones
			BtnRun.setEnabled(true);
			BtnStop.setEnabled(false);
		}else if(Boton.getSource() == BtnSum) {
			//Vamos a sumar los huevos
			this.contador++;
			String text = (this.contador <= 9) ? "0"+contador : String.valueOf(contador);
			LContador.setText(text);
		}else if (Boton.getSource() == BtnSalir) {
			//TODO: Falta guardar los datos antes de cerrar la aplicacion
			ArrayList<String> datos = new ArrayList<String>();
			datos.add(String.valueOf(contador));
			datos.addAll(hilo.getTime());
			if(!doc.GuardarDatos(datos)) {
				JOptionPane.showMessageDialog(null, "No se puede guardar los datos", "Error", JOptionPane.ERROR_MESSAGE);
			}
			//Matamos al hilo
			hilo.KillHilo();
			//Paramos la aplicacion
			System.exit(0);
		}else if (Boton.getSource() == BtnRest) {
			//Pausamos el el hilo
			if(hilo.getInicioHilo() && !hilo.getPausa()) {
				hilo.Pausar();
				//Canviar estado botones
				BtnRun.setEnabled(true);
				BtnStop.setEnabled(false);
			}	
			//Reseteamos la parte visual
			LContador.setText("00");
			LTiempo.setText("00 : 00 : 00");
			//Reseteamos la parte de la logica
			hilo.setTime(0, 0, 0);
			this.contador = 0;
		}
		
	}
	
	public void CargarDatos() {
		if(doc.ExistFile()) {
			
			ArrayList<String> datos = new ArrayList<String>();
			boolean correcto = true; 
			
			if(doc.CargarDatos(datos)) {
				try {
					//Colocar Contador
					int contador = Integer.parseInt(datos.get(0));
					LContador.setText( (contador <= 9) ? "0"+contador : String.valueOf(contador));
					//Colocar tiempo
					int hour = Integer.parseInt(datos.get(1));
					int min  = Integer.parseInt(datos.get(2));
					int sec  = Integer.parseInt(datos.get(3));
					
					String hora = (hour <= 9) ? "0"+hour : String.valueOf(hour);
					String minuto = (min <= 9) ? "0"+min : String.valueOf(min); 
					String segundo = (sec <= 9) ? "0"+sec : String.valueOf(sec);
					
					LTiempo.setText(hora + " : " + minuto + " : " + segundo);
					
					hilo.setTime(hour, min, sec);
					this.contador = contador;
					
				}catch (Exception e) {
					//Error
					JOptionPane.showMessageDialog(null, "Error al cargar los datos", "Error", JOptionPane.ERROR_MESSAGE);
					correcto = false;
				}
			}else {
				//Error
				JOptionPane.showMessageDialog(null, "Error en la lectura de ficheros", "Error", JOptionPane.ERROR_MESSAGE);
				correcto = false;
			}
			
			if(!correcto) {
				LContador.setText("00");
				LTiempo.setText("-- : -- : --");
			}
			
		}else {
			LContador.setText("00");
			LTiempo.setText("-- : -- : --");
		}
	}
	
	public void setLTiemp(String tiempo) {
		this.LTiempo.setText(tiempo);
	}
}

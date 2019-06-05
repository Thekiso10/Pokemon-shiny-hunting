package Hilos;

import java.util.ArrayList;
import java.util.Collection;

import Swing.*;

public class HiloTiempo extends Thread {
	
	private PanelMenu panel;
	private boolean pausa;
	private boolean kill;
	private boolean iniciado;
	//Establecer tiempo
	private int hora;
	private int min;
	private int sec;
	
	public HiloTiempo() {
		pausa = false;
		kill = false; 
		iniciado = false;
		//Establecemos el tiempo en 0
		hora = min = sec = 0;
	}
	
	public void run() {
		iniciado = true;
		//Iniciamos el bucle
		while(!kill) {
			while(!pausa) {
				sec++;
				
				if(sec == 60) { min++; sec=0;}
				if(min == 60) {hora++; min=0;}
				
				panel.setLTiemp(ParseTime());
				SleepHilo();
			}
			SleepHilo();
		}
	}
	
	private void SleepHilo() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private String ParseTime() {
		String time = null;
		String hour = null;
		String minut = null;
		String second = null;
		
		hour = (hora<=9) ? "0" + hora : String.valueOf(hora);
		minut = (min<=9) ? "0" + min : String.valueOf(min);
		second = (sec<=9) ? "0" + sec : String.valueOf(sec);
		
		time = hour + " : " + minut + " : " + second;
		
		return time;
	}
	
	public Collection<? extends String> getTime(){
		ArrayList<String> time = new ArrayList<String>();
		
		time.add(String.valueOf(hora));
		time.add(String.valueOf(min));
		time.add(String.valueOf(sec));
		
		return time;
	}
	
	public void setTime(int hour, int min, int sec) {
		this.hora = hour;
		this.min = min;
		this.sec = sec;
	}
	
	public void Pausar() {
		pausa = true;
	}
	
	public void Reanudar() {
		pausa = false;
	}
	
	public void KillHilo() {
		Pausar();
		kill = true;
	}
	
	public boolean getInicioHilo() {
		return iniciado;
	}
	
	public boolean getPausa() {
		return pausa;
	}
	
	public void setPanelMenu(PanelMenu panel) {
		this.panel = panel;
	}
}

package es.studium.Juego;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;

public class Vista extends Frame
{
	private static final long serialVersionUID = 1L;
	Toolkit herramientas;
	Image imgTablero;
	Image imgFB, imgFB2, imgFB3, imgFB4, imgFB5;
	Image imgFN, imgFN2, imgFN3, imgFN4, imgFN5;
	Image icono = Toolkit.getDefaultToolkit().getImage("ficha negra.png");
	int     xFB = 460, yFB = 260,
			xFB2 = 460, yFB2 = 260,
			xFB3 = 460, yFB3 = 260,
			xFB4 = 460, yFB4 = 260,
			xFB5 = 460, yFB5 = 260,
			
			xFN = 460, yFN = 475,
			xFN2 = 460, yFN2 = 475,
			xFN3 = 460, yFN3 = 475,
			xFN4 = 460, yFN4 = 475,
			xFN5 = 460, yFN5 = 475;
	Font fuente = new Font("Impact",Font.PLAIN,40);
	Color color, colorDado;
	String textoTurno, numeroDado;
	
	//Elementos dialogo
	Dialog dialogo= new Dialog(this, "", false);
	Dialog dialogoV= new Dialog(this, "", true);
	Dialog dialogoRanking = new Dialog(this,"",true);
	Dialog dialogoAlta = new Dialog(this,"",true);
	Label lblAlta = new Label();
	Label lblRanking = new Label("Introduce tu nombre:");
	TextField txtRanking = new TextField(20);
	Button btnRanking = new Button("Aceptar");
	Label txtDialogo = new Label("");
	
	//Elemento de dialogo de seleccion de fichas NEGRAS
	Button btnNegra1 = new Button("Ficha 1");
	Button btnNegra2 = new Button("Ficha 2");
	Button btnNegra3 = new Button("Ficha 3");
	Button btnNegra4 = new Button("Ficha 4");
	Button btnNegra5 = new Button("Ficha 5");
	Button[] btnNegras = {btnNegra1, btnNegra2, btnNegra3, btnNegra4, btnNegra5};
	
	//Elemento de dialogo de seleccion de fichas BLANCAS
	Button btnBlanca1 = new Button("Ficha 1");
	Button btnBlanca2 = new Button("Ficha 2");
	Button btnBlanca3 = new Button("Ficha 3");
	Button btnBlanca4 = new Button("Ficha 4");
	Button btnBlanca5 = new Button("Ficha 5");
	Button[] btnBlancas = {btnBlanca1, btnBlanca2, btnBlanca3, btnBlanca4, btnBlanca5};
	
	public Vista() 
	{
		herramientas = getToolkit();
		this.setIconImage(icono);
		imgTablero = herramientas.getImage("tablero.jpg");
		
		imgFB = herramientas.getImage("ficha blanca1.png");
		imgFB2 = herramientas.getImage("ficha blanca2.png");
		imgFB3 = herramientas.getImage("ficha blanca3.png");
		imgFB4 = herramientas.getImage("ficha blanca4.png");
		imgFB5 = herramientas.getImage("ficha blanca5.png");
		
		imgFN = herramientas.getImage("ficha negra1.png");
		imgFN2 = herramientas.getImage("ficha negra2.png");
		imgFN3 = herramientas.getImage("ficha negra3.png");
		imgFN4 = herramientas.getImage("ficha negra4.png");
		imgFN5 = herramientas.getImage("ficha negra5.png");
		
		
		this.setSize(1000,800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void paint(Graphics g) 
	{
		g.drawImage(imgTablero, 0, 0, this);
		
		g.drawImage(imgFB, xFB,yFB, this);
		g.drawImage(imgFB2, xFB2,yFB2, this);
		g.drawImage(imgFB3, xFB3,yFB3, this);
		g.drawImage(imgFB4, xFB4,yFB4, this);
		g.drawImage(imgFB5, xFB5,yFB5, this);
		
		g.drawImage(imgFN, xFN,yFN, this);
		g.drawImage(imgFN2, xFN2,yFN2, this);
		g.drawImage(imgFN3, xFN3,yFN3, this);
		g.drawImage(imgFN4, xFN4,yFN4, this);
		g.drawImage(imgFN5, xFN5,yFN5, this);
		
		g.setFont(fuente);
		g.setColor(color);
		g.drawString(textoTurno, 220, 85);
		
		g.setColor(colorDado);
		g.drawString(numeroDado, 900, 400);
	}
	
	public void actualizarFichaBlanca(int x, int y) 
	{
		this.xFB = x;
		this.yFB = y;
	}
	public void actualizarFichaBlanca2(int x, int y) 
	{
		this.xFB2 = x;
		this.yFB2 = y;
	}
	public void actualizarFichaBlanca3(int x, int y) 
	{
		this.xFB3 = x;
		this.yFB3 = y;
	}
	public void actualizarFichaBlanca4(int x, int y) 
	{
		this.xFB4 = x;
		this.yFB4 = y;
	}
	public void actualizarFichaBlanca5(int x, int y) 
	{
		this.xFB5 = x;
		this.yFB5 = y;
	}
	
	public void actualizarFichaNegra(int x, int y) 
	{
		this.xFN = x;
		this.yFN = y;
	}
	
	public void actualizarFichaNegra2(int x, int y) 
	{
		this.xFN2 = x;
		this.yFN2 = y;
	}
	
	public void actualizarFichaNegra3(int x, int y) 
	{
		this.xFN3 = x;
		this.yFN3 = y;
	}
	
	public void actualizarFichaNegra4(int x, int y) 
	{
		this.xFN4 = x;
		this.yFN4 = y;
	}
	
	public void actualizarFichaNegra5(int x, int y) 
	{
		this.xFN5 = x;
		this.yFN5 = y;
	}
	
	//Método que muestra un diálogo indicando de quien es el turno
	public void dialogoTurno(String turno)
	{
		dialogo.removeAll();
		
		dialogo.setLayout(new FlowLayout());
		txtDialogo.setText("Turno de las fichas " + turno);
		dialogo.add(txtDialogo);
		
		dialogo.setSize(200,200);
		dialogo.setResizable(false);
		dialogo.setLocation(300, 600);
		dialogo.setVisible(true);
	}
	
	public void dialogoSeleccionNegras() 
	{
		dialogo.removeAll();
		
		dialogo.setLayout(new FlowLayout());
		txtDialogo.setText("Selecciona una ficha para mover");
		dialogo.add(txtDialogo);
		for(int i = 0; i<btnNegras.length; i++) 
		{
			dialogo.add(btnNegras[i]);
		}
		dialogo.setSize(200,200);
		dialogo.setResizable(false);
		dialogo.setLocation(300, 600);
		dialogo.setVisible(true);
		
	}
	
	public void dialogoSeleccionBlancas() 
	{
		dialogo.removeAll();
		
		dialogo.setLayout(new FlowLayout());
		txtDialogo.setText("Selecciona una ficha para mover");
		dialogo.add(txtDialogo);
		for(int i = 0; i<btnBlancas.length; i++) 
		{
			dialogo.add(btnBlancas[i]);
		}
		dialogo.setSize(200,200);
		dialogo.setResizable(false);
		dialogo.setLocation(300, 600);
		dialogo.setVisible(true);
		
	}
	
	public void dialogoVictoria(String ganador) 
	{
		dialogoV.removeAll();
		
		dialogoV.setLayout(new FlowLayout());
		txtDialogo.setText("Han ganado las fichas " + ganador);
		dialogoV.add(txtDialogo);
		
		dialogoV.setSize(200,200);
		dialogoV.setResizable(false);
		dialogoV.setLocationRelativeTo(this);
		dialogoV.setVisible(true);
	}
	
	public void mostrarDialogoRanking() 
	{
		dialogoRanking.removeAll();
		
		dialogoRanking.setLayout(new FlowLayout());
		dialogoRanking.add(lblRanking);
		dialogoRanking.add(txtRanking);
		dialogoRanking.add(btnRanking);
		
		dialogoRanking.setSize(300, 200);
		dialogoRanking.setResizable(false);
		dialogoRanking.setLocationRelativeTo(null);
		dialogoRanking.setVisible(true);
		
	}
}

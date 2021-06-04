package es.studium.Juego;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Juego implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Juego Real de Ur");
	Image icono = Toolkit.getDefaultToolkit().getImage("ficha negra.png");
	Label titulo = new Label("EL JUEGO REAL DE UR");
	Font fuenteMenu = new Font("Among Us",Font.PLAIN,20);
	
	Button btnNuevaPartida = new Button("Nueva Partida");
	Button btnRanking = new Button("Ranking");
	Button btnAyuda = new Button("Ayuda");
	Button btnSalir = new Button("Salir");
	Button[] botones = {btnNuevaPartida, btnRanking, btnAyuda, btnSalir};
	
	
	Dialog dialogo = new Dialog(ventana,"Ranking", false);
	TextArea txaRanking = new TextArea(4, 30);
	
	BaseDatos bd;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	

	public static void main(String[] args)
	{
		new Juego();
		
	}
	
	public Juego() 
	{
		ventana.setLayout(new FlowLayout());
		ventana.setIconImage(icono);
		ventana.setBackground(Color.DARK_GRAY);
		
		ventana.addWindowListener(this);
		
		titulo.setForeground(Color.YELLOW);
		titulo.setFont(fuenteMenu);
		ventana.add(titulo);
		ventana.add(btnNuevaPartida);
		ventana.add(btnRanking);
		ventana.add(btnAyuda);
		ventana.add(btnSalir);
		
		for(int i=0; i<botones.length; i++) 
		{
			botones[i].addActionListener(this);
			botones[i].setBackground(Color.gray);
			botones[i].setForeground(Color.white);
			botones[i].setFont(fuenteMenu);
		}
		
		ventana.setSize(260, 200);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		ventana.setVisible(true);
	}
	
	public void empezarJuego() 
	{
		ventana.setVisible(false);
		dialogo.setVisible(false);
		
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		new Controlador(vista, modelo);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(btnNuevaPartida)) 
		{
			empezarJuego();
		}
		else if(ae.getSource().equals(btnRanking))
		{
			mostrarRanking();
		}
		else if(ae.getSource().equals(btnAyuda)) 
		{
			try 
			{
				Runtime.getRuntime().exec("hh.exe ayudaJuego.chm");
			}catch(IOException e) 
			{
				e.printStackTrace();
			}
		}
		else if(ae.getSource().equals(btnSalir)) 
		{
			System.exit(0);
		}
		
	}

	private void mostrarRanking()
	{
		// Conectar
					bd = new BaseDatos();
					connection = bd.conectar();
					// Hacer un SELECT * FROM clientes
					sentencia = "SELECT * FROM rankings ORDER BY puntuacionJugador LIMIT 10";
					// La información está en ResultSet
					// Recorrer el RS y por cada registro,
					// meter una línea en el TextArea
					try
					{
						//Crear una sentencia
						statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
						//Crear un objeto ResultSet para guardar lo obtenido
						//y ejecutar la sentencia SQL
						rs = statement.executeQuery(sentencia);
						txaRanking.selectAll();
						txaRanking.setText("");
						txaRanking.append("Puesto\tJugador\tNºTiradas\n");
						int posicionRanking=0;
						while(rs.next())
						{
							posicionRanking++;
							txaRanking.append(posicionRanking
									+"\t"+rs.getString("nombreJugador")
									+"\t"+rs.getInt("puntuacionJugador")
									+"\n");
						}
					}
					catch (SQLException sqle)
					{
						txaRanking.setText("Error al recoger los datos");
					}
					finally
					{

					}
					txaRanking.setEditable(false);
					dialogo.add(txaRanking);

					dialogo.setSize(230,220);
					dialogo.setResizable(false);
					dialogo.setLocationRelativeTo(null);
					dialogo.addWindowListener(this);
					dialogo.setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if(dialogo.isActive()) 
		{
			dialogo.setVisible(false);
		}
		else 
		{
			System.exit(0);
		}
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}

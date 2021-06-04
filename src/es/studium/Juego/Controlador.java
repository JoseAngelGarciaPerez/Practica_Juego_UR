package es.studium.Juego;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controlador implements WindowListener, MouseListener, ActionListener
{
	Vista vista;
	Modelo modelo;

	int puntosBlancas = 0;
	int puntosNegras = 0;

	int posicionBlanca = 0;
	int posicionNegra = 0;

	int turno;
	int tirada;
	String turnoNombre[] = { "Negras", "Blancas" };

	int tableroBlanco[][] = { { 460, 260 }, { 346, 270 }, { 253, 270 }, { 159, 270 }, { 66, 270 }, { 66, 375 },
			{ 159, 375 }, { 253, 375 }, { 346, 375 }, { 441, 375 }, { 534, 375 }, { 628, 375 }, { 725, 375 },
			{ 724, 270 }, { 630, 270 }, { 580, 123 } };

	int tableroNegro[][] = { { 460, 475 }, { 346, 465 }, { 253, 465 }, { 159, 465 }, { 66, 465 }, { 66, 375 },
			{ 159, 375 }, { 253, 375 }, { 346, 375 }, { 441, 375 }, { 534, 375 }, { 628, 375 }, { 725, 375 },
			{ 724, 465 }, { 630, 465 }, { 580, 634 } };

	boolean dadoPulsado = false;
	boolean puedeMover = false;

	BaseDatos bd;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public Controlador(Vista v, Modelo m)
	{
		this.vista = v;
		this.modelo = m;

		eleccionDeTurno();

		this.vista.addWindowListener(this);
		this.vista.addMouseListener(this);

		for (int i = 0; i < vista.btnNegras.length; i++)
		{
			vista.btnBlancas[i].addActionListener(this);
			vista.btnNegras[i].addActionListener(this);
		}
		vista.btnRanking.addActionListener(this);
	}

	// Método que describe de quien es el turno
	private void eleccionDeTurno()
	{
		turno = this.modelo.eleccionTurno();
		System.out.println("Turno de las fichas " + turnoNombre[turno]);

		this.vista.textoTurno = turnoNombre[turno];

		if (turno == 0)
		{
			this.vista.color = Color.BLACK;
		} else
		{
			this.vista.color = Color.WHITE;
		}

		this.vista.dialogoTurno(turnoNombre[turno]);
	}

	@Override
	public void mouseClicked(MouseEvent me)
	{

		int x = me.getX();
		int y = me.getY();
		if ((x >= 856 && x <= 981) && (y >= 245 && y <= 327) && dadoPulsado != true)
		{
			System.out.println("Pulsaste los dados");
			dadoPulsado = true;

			if (turno == 0)
			{
				modelo.numeroTiradaN++;

				System.out.println("NEGRAS");

				tirada = this.modelo.tirada();
				System.out.println("Tirada = " + tirada);

				this.vista.numeroDado = Integer.toString(tirada);
				this.vista.colorDado = Color.BLACK;
				this.vista.repaint();

				for (int i = 0; i < modelo.posicionesNegras.length; i++)
				{
					if (modelo.comprobarFichaNegra(tirada, i) == false)
					{
						puedeMover = false;
					} else
					{
						puedeMover = true;
						i = 6;
					}
				}

				if (puedeMover)
				{
					if (tirada == 0)
					{
						turno = 1;
						this.vista.textoTurno = turnoNombre[turno];
						this.vista.color = Color.WHITE;

						this.vista.repaint();
						this.vista.dialogoTurno(turnoNombre[turno]);

						dadoPulsado = false;
					} else
					{
						this.vista.dialogoSeleccionNegras();
					}

				} 
				else
				{
					turno = 1;
					this.vista.textoTurno = turnoNombre[turno];
					this.vista.color = Color.WHITE;

					this.vista.repaint();
					this.vista.dialogoTurno(turnoNombre[turno]);

					dadoPulsado = false;
				}
			} else if (turno == 1)
			{
				modelo.numeroTiradaB++;

				System.out.println("BLANCAS");

				tirada = this.modelo.tirada();
				System.out.println("Tirada = " + tirada);

				this.vista.numeroDado = Integer.toString(tirada);
				this.vista.colorDado = Color.WHITE;
				this.vista.repaint();

				puedeMover = false;
				for (int i = 0; i < modelo.posicionesBlancas.length; i++)
				{
					if (modelo.comprobarFichaBlanca(tirada, i) == false)
					{
						puedeMover = false;
					} else
					{
						puedeMover = true;
						i = 6;
					}
				}

				if (puedeMover)
				{
					if (tirada == 0)
					{
						turno = 0;
						this.vista.textoTurno = turnoNombre[turno];
						this.vista.color = Color.BLACK;
						this.vista.repaint();
						this.vista.dialogoTurno(turnoNombre[turno]);

						dadoPulsado = false;
					} else
					{
						this.vista.dialogoSeleccionBlancas();
					}
				} 
				else
				{
					turno = 0;
					this.vista.textoTurno = turnoNombre[turno];
					this.vista.color = Color.BLACK;

					this.vista.repaint();
					this.vista.dialogoTurno(turnoNombre[turno]);

					dadoPulsado = false;
				}

			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

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
		if (vista.dialogoV.isActive())
		{
			vista.dialogoV.setVisible(false);
			vista.dialogo.setVisible(false);

			vista.mostrarDialogoRanking();

		} else if (vista.dialogoAlta.isActive())
		{
			
			vista.dialogo.setVisible(false);
			vista.dialogoV.setVisible(false);
			vista.setVisible(false);
			vista.dialogoRanking.setVisible(false);
			vista.dialogoAlta.setVisible(false);
			vista=null;
			modelo=null;
			
			new Juego();
		} else
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

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		for (int i = 0; i < vista.btnNegras.length; i++)
		{
			if (ae.getSource().equals(vista.btnNegras[i]))
			{
				if (modelo.comprobarFichaNegra(tirada, i) == false)
				{
					turno = 0;
					this.vista.textoTurno = turnoNombre[turno];
					this.vista.color = Color.BLACK;
					this.vista.repaint();

					modelo.numeroTiradaN++;
				} else
				{
					int posicion = modelo.moverFichaNegra(tirada, i);

					int xFicha = tableroNegro[posicion][0];
					int yFicha = tableroNegro[posicion][1];

					switch (i)
					{
					case 0:
						this.vista.actualizarFichaNegra(xFicha, yFicha);
					case 1:
						this.vista.actualizarFichaNegra2(xFicha, yFicha);
					case 2:
						this.vista.actualizarFichaNegra3(xFicha, yFicha);
					case 3:
						this.vista.actualizarFichaNegra4(xFicha, yFicha);
					case 4:
						this.vista.actualizarFichaNegra5(xFicha, yFicha);
					}

					for (int j = 0; j < modelo.posicionesNegras.length; j++)
					{
						int x = tableroNegro[modelo.posicionesNegras[j]][0];
						int y = tableroNegro[modelo.posicionesNegras[j]][1];

						switch (j)
						{
						case 0:
							this.vista.actualizarFichaNegra(x, y);
						case 1:
							this.vista.actualizarFichaNegra2(x, y);
						case 2:
							this.vista.actualizarFichaNegra3(x, y);
						case 3:
							this.vista.actualizarFichaNegra4(x, y);
						case 4:
							this.vista.actualizarFichaNegra5(x, y);
						}
					}

					for (int j = 0; j < modelo.posicionesBlancas.length; j++)
					{
						int x = tableroBlanco[modelo.posicionesBlancas[j]][0];
						int y = tableroBlanco[modelo.posicionesBlancas[j]][1];

						switch (j)
						{
						case 0:
							this.vista.actualizarFichaBlanca(x, y);
						case 1:
							this.vista.actualizarFichaBlanca2(x, y);
						case 2:
							this.vista.actualizarFichaBlanca3(x, y);
						case 3:
							this.vista.actualizarFichaBlanca4(x, y);
						case 4:
							this.vista.actualizarFichaBlanca5(x, y);
						}
					}

					if (posicion == 15)
					{
						puntosNegras++;
						if (puntosNegras == 5)
						{
							System.out.println("Ganaron las negras");
							vista.dialogoV.addWindowListener(this);
							vista.dialogoVictoria(turnoNombre[turno]);

							modelo.vaciarPosiciones();
						}
					}

					if (posicion == 4 || posicion == 8 || posicion == 14)
					{
						if (tirada != 0)
						{
							turno = 0;
							this.vista.textoTurno = turnoNombre[turno];
							this.vista.color = Color.BLACK;
							this.vista.repaint();

							dadoPulsado = false;
							modelo.numeroTiradaN++;
						} else
						{
							turno = 1;
							this.vista.textoTurno = turnoNombre[turno];
							this.vista.color = Color.WHITE;
							this.vista.repaint();
							this.vista.dialogoTurno(turnoNombre[turno]);
							dadoPulsado = false;
							modelo.numeroTiradaN++;
						}
					} else
					{
						turno = 1;
						this.vista.textoTurno = turnoNombre[turno];
						this.vista.color = Color.WHITE;
						this.vista.repaint();
						this.vista.dialogoTurno(turnoNombre[turno]);
						dadoPulsado = false;
						modelo.numeroTiradaN++;
					}
				}
			}
		}

		for (int i = 0; i < vista.btnBlancas.length; i++)
		{
			if (ae.getSource().equals(vista.btnBlancas[i]))
			{
				if (modelo.comprobarFichaBlanca(tirada, i) == false)
				{
					turno = 1;
					this.vista.textoTurno = turnoNombre[turno];
					this.vista.color = Color.WHITE;
					this.vista.repaint();

					modelo.numeroTiradaB++;
				} else
				{
					int posicion = modelo.moverFichaBlanca(tirada, i);

					int xFicha = tableroBlanco[posicion][0];
					int yFicha = tableroBlanco[posicion][1];

					switch (i)
					{
					case 0:
						this.vista.actualizarFichaBlanca(xFicha, yFicha);
					case 1:
						this.vista.actualizarFichaBlanca2(xFicha, yFicha);
					case 2:
						this.vista.actualizarFichaBlanca3(xFicha, yFicha);
					case 3:
						this.vista.actualizarFichaBlanca4(xFicha, yFicha);
					case 4:
						this.vista.actualizarFichaBlanca5(xFicha, yFicha);
					}

					for (int j = 0; j < modelo.posicionesBlancas.length; j++)
					{
						int x = tableroBlanco[modelo.posicionesBlancas[j]][0];
						int y = tableroBlanco[modelo.posicionesBlancas[j]][1];

						switch (j)
						{
						case 0:
							this.vista.actualizarFichaBlanca(x, y);
						case 1:
							this.vista.actualizarFichaBlanca2(x, y);
						case 2:
							this.vista.actualizarFichaBlanca3(x, y);
						case 3:
							this.vista.actualizarFichaBlanca4(x, y);
						case 4:
							this.vista.actualizarFichaBlanca5(x, y);
						}
					}

					for (int j = 0; j < modelo.posicionesNegras.length; j++)
					{
						int x = tableroNegro[modelo.posicionesNegras[j]][0];
						int y = tableroNegro[modelo.posicionesNegras[j]][1];

						switch (j)
						{
						case 0:
							this.vista.actualizarFichaNegra(x, y);
						case 1:
							this.vista.actualizarFichaNegra2(x, y);
						case 2:
							this.vista.actualizarFichaNegra3(x, y);
						case 3:
							this.vista.actualizarFichaNegra4(x, y);
						case 4:
							this.vista.actualizarFichaNegra5(x, y);
						}
					}

					if (posicion == 15)
					{
						puntosBlancas++;
						if (puntosBlancas == 5)
						{
							System.out.println("Ganaron las blancas");
							vista.dialogoV.addWindowListener(this);
							vista.dialogoVictoria(turnoNombre[turno]);

							modelo.vaciarPosiciones();
						}
					}

					if (posicion == 4 || posicion == 8 || posicion == 14)
					{
						if (tirada != 0)
						{
							turno = 1;
							this.vista.textoTurno = turnoNombre[turno];
							this.vista.color = Color.WHITE;
							this.vista.repaint();

							dadoPulsado = false;
							modelo.numeroTiradaB++;
						} else
						{
							turno = 0;
							this.vista.textoTurno = turnoNombre[turno];
							this.vista.color = Color.BLACK;
							this.vista.repaint();
							this.vista.dialogoTurno(turnoNombre[turno]);

							dadoPulsado = false;
							modelo.numeroTiradaB++;
						}
					} else
					{
						turno = 0;
						this.vista.textoTurno = turnoNombre[turno];
						this.vista.color = Color.BLACK;
						this.vista.repaint();
						this.vista.dialogoTurno(turnoNombre[turno]);

						dadoPulsado = false;
						modelo.numeroTiradaB++;
					}
				}
			}
		}

		if (ae.getSource().equals(vista.btnRanking))
		{

			bd = new BaseDatos();
			connection = bd.conectar();

			try
			{
				// Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				// Crear un objeto ResultSet para guardar lo obtenido
				// y ejecutar la sentencia SQL
				if (vista.txtRanking.getText().length() != 0)
				{
					if (puntosNegras == 5)
					{

						sentencia = "INSERT INTO rankings VALUES (null, '"
								+ vista.txtRanking.getText() + "', '" + modelo.numeroTiradaN + "')";
						statement.executeUpdate(sentencia);
						vista.lblAlta.setText("Jugador añadido al ranking");
					} else
					{

						sentencia = "INSERT INTO rankings VALUES (null, '"
								+ vista.txtRanking.getText() + "', '" + modelo.numeroTiradaB + "')";
						statement.executeUpdate(sentencia);
						vista.lblAlta.setText("Jugador añadido al ranking");
					}
				} else
				{
					vista.lblAlta.setText("Faltan datos");
				}
			} catch (SQLException sqle)
			{
				vista.lblAlta.setText("Error en ALTA");
			} finally
			{
				vista.dialogoV.setVisible(false);
				vista.dialogoRanking.setVisible(false);
				vista.dialogo.setVisible(false);

				vista.dialogoAlta.setLayout(new FlowLayout());
				vista.dialogoAlta.addWindowListener(this);
				vista.dialogoAlta.setSize(150, 100);
				vista.dialogoAlta.setResizable(false);
				vista.dialogoAlta.setLocationRelativeTo(null);
				vista.dialogoAlta.add(vista.lblAlta);
				vista.dialogoAlta.setVisible(true);

				modelo.vaciarPosiciones();

				puntosBlancas = 0;
				puntosNegras = 0;
			}
		}
	}
}

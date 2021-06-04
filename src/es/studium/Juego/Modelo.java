package es.studium.Juego;

import java.util.Random;

public class Modelo
{
	/*ELEMENTOS*/
	//Random
	Random rnd = new Random();
	//Puntos de las blancas y las negras y el numero de tiradas totales
	int puntosBlancas, puntosNegras, numeroTiradaB, numeroTiradaN;
	//Posiciones de las fichas blancas
	int posicionB, posicionB2, posicionB3, posicionB4, posicionB5;
	int posicionesBlancas[] = {posicionB, posicionB2, posicionB3, posicionB4, posicionB5};
	//Posiciones de las fichas negras
	int posicionN, posicionN2, posicionN3, posicionN4, posicionN5;
	int posicionesNegras[] = {posicionN, posicionN2, posicionN3, posicionN4, posicionN5};
	boolean ficha1=false,ficha2=false,ficha3=false,ficha4=false,ficha5=false;
	boolean[] fichasComprobadas = {ficha1,ficha2,ficha3,ficha4,ficha5};
	
	//Posiciones del tablero
	int tableroBlanco[][] = {{460,260},
			{346,270}, {253,270}, {159,270}, {66,270},
			{66,375}, {159,375}, {253,375}, {346,375}, {441,375}, {534,375}, {628,375}, {725,375},
			{724,270}, {630,270},
			{580,123}};
	
	int tableroNegro[][] = {{460,475},
			{346,465}, {253,465}, {159,465}, {66,465},
			{66,375}, {159,375}, {253,375}, {346,375}, {441,375}, {534,375}, {628,375}, {725,375},
			{724,465}, {630,465},
			{580,634}};
	
	/*MÉTODOS*/
	// Método que devuelve un número entre 0 y 4, que es la tirada de dados
	public int tirada() 
	{
		return(rnd.nextInt(5));
	}
	
	//Método que devuelve 1 o 0, permitiendo la eleccion del turno
	public int eleccionTurno() 
	{
		return(rnd.nextInt(2));
	}
	
	//Método para mover las fichas negras
	public int moverFichaNegra(int tirada, int ficha) 
	{
		int posicionNegra = posicionesNegras[ficha];	
		
		if(tirada == 0) 
		{
			
		}
		else 
		{
			if(posicionNegra+tirada > 15) 
			{
				System.out.println("Tirada invalida, se sobrepasa el límite");
			}
			else
			{
				posicionNegra = posicionNegra + tirada;
				posicionesNegras[ficha] = posicionNegra;
			}
			
			System.out.println("Posicion negra:" + posicionNegra);
			
			for(int i=0; i<posicionesBlancas.length; i++) 
			{
				int posicionBlanca = posicionesBlancas[i];
				
				if((posicionNegra == posicionBlanca) && (posicionNegra >= 5) && (posicionBlanca >= 5) && (posicionNegra <= 12) && (posicionBlanca <= 12)) 
				{
					if(posicionBlanca==8) 
					{
						System.out.println("La ficha blanca se encuentra en una posicion segura");
						posicionesNegras[ficha]=posicionNegra-tirada;
					}
					else 
					{
						System.out.println("La ficha negra se come a la blanca");
						posicionesBlancas[i]=0;

					}
				}
			}
			
			
		}
		
		return posicionNegra;
	}
	
	//Método para mover las fichas blancas
	public int moverFichaBlanca(int tirada, int ficha) 
	{
		int posicionBlanca = posicionesBlancas[ficha];
		if(tirada == 0) 
		{
			
		}
		else 
		{
			if(posicionBlanca+tirada > 15) 
			{
				System.out.println("Tirada invalida, se sobrepasa el límite");
			}
			else
			{
				posicionBlanca = posicionBlanca + tirada;
				posicionesBlancas[ficha] = posicionBlanca;
			}
			
			System.out.println("Posicion blanca:" + posicionBlanca);
			
			for(int i=0; i<posicionesNegras.length; i++) 
			{
				int posicionNegra = posicionesNegras[i];
				
				if((posicionNegra == posicionBlanca) && (posicionNegra >= 5) && (posicionBlanca >= 5) && (posicionNegra <= 12) && (posicionBlanca <= 12)) 
				{
					if(posicionNegra==8) 
					{
						System.out.println("La ficha negra se encuentra en una posicion segura");
						posicionesBlancas[ficha]=posicionBlanca-tirada;
					}
					else 
					{
						System.out.println("La ficha blanca se come a la negra");
						posicionesNegras[i]=0;
					}
				}
			}
		}
		
		return posicionBlanca;
	}
	
	//Método que comprueba si una ficha negra se puede mover
	public boolean comprobarFichaNegra(int tirada, int ficha) 
	{
		int posicionNegra = posicionesNegras[ficha];
		boolean comprobacion = false;
		
		for(int i=0; i<posicionesNegras.length; i++) 
		{
			int posicionOtraNegra = posicionesNegras[i];
			if(i!=ficha) 
			{
				if(posicionOtraNegra == (posicionNegra+tirada) && posicionOtraNegra!=15) 
				{
					System.out.println("No se puede mover esta ficha, escoge otra");
					comprobacion=false;
					i=posicionesNegras.length+1;
				}
				else 
				{
					comprobacion=true;
				}
			}
		}
		
		if((posicionNegra+tirada)>15) 
		{
			comprobacion=false;
		}
		
		return comprobacion;
	}
	
	//Método que comprueba si una ficha blanca se puede mover
	public boolean comprobarFichaBlanca(int tirada, int ficha) 
	{
		int posicionBlanca = posicionesBlancas[ficha];
		boolean comprobacion = false;
		
		for(int i=0; i<posicionesBlancas.length; i++) 
		{
			int posicionOtraBlanca = posicionesBlancas[i];
			if(i!=ficha) 
			{
				if(posicionOtraBlanca == (posicionBlanca+tirada) && posicionOtraBlanca!=15) 
				{
					System.out.println("No se puede mover esta ficha, escoge otra");
					comprobacion=false;
					i=posicionesBlancas.length+1;
				}
				else 
				{
					comprobacion=true;
				}
			}
		}
		
		if((posicionBlanca+tirada)>15) 
		{
			comprobacion=false;
		}
		
		return comprobacion;
	}
	
	public void vaciarPosiciones() 
	{
		for(int i=0; i<posicionesBlancas.length; i++) 
		{
			posicionesBlancas[i]=0;
			posicionesNegras[i]=0;
		}
	}
	
}

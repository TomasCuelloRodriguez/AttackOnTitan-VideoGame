package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;


public class Mykasa {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private int sentido;
	private int velocidad = 5;
	private boolean gigante;
	private Image imagen;
	private Image imagenIzq;
	private Image imagenDer;
	private Image imagenAtras;
	private Image mikasaTitan;
	private Image mikasaTitanIzq;
	private Image mikasaTitanDer;
	private Image mikasaTitanAtras;
	
	public Mykasa(int x, int y,int alto, int ancho,int sentido,boolean gigante) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.sentido = sentido;
		this.gigante = gigante;
		this.imagen = Herramientas.cargarImagen("imagenes/mikasaFrente.png");
		this.imagenIzq = Herramientas.cargarImagen("imagenes/mikasaIzq.png");
		this.imagenDer = Herramientas.cargarImagen("imagenes/mikasaDer.png");
		this.imagenAtras = Herramientas.cargarImagen("imagenes/mikasaAtras.png");
		this.mikasaTitan = Herramientas.cargarImagen("imagenes/titanFrente.png");
		this.mikasaTitanIzq = Herramientas.cargarImagen("imagenes/titanIzq.png");
		this.mikasaTitanDer = Herramientas.cargarImagen("imagenes/titanDer.png");
		this.mikasaTitanAtras = Herramientas.cargarImagen("imagenes/titanAtras.png");

	}
	
	
	public int getSentido() {
		return this.sentido;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getAlto() {
		return this.alto;
	}
	public int getAncho() {
		return this.ancho;
	}
	public int getVelocidad() {
		return this.velocidad;
	}
	public boolean getGigante() {
		return this.gigante;
	}
	void dibujar(Entorno entorno) {
		//entorno.dibujarRectangulo(this.x,this.y, this.ancho, this.alto,0, Color.CYAN);
		if(sentido == 0) {
			dibujarDer(entorno);
		}
		if(sentido == 1) {
			dibujarIzq(entorno);
		}
		if(sentido == 2) {
			dibujarAtras(entorno);
		}
		if(sentido == 3) {
			dibujarAdelante(entorno);
		}
		if(sentido == 4) {
			dibujarAdelante(entorno);
		}
	}
	
	void moverDe() {
		this.x = this.x +velocidad;
	}
	
	void moverIz() {
		this.x = this.x -velocidad;
	}
	
	void moverAr() {
		this.y = this.y -velocidad;
	}
	
	void moverAb() {
		this.y = this.y +velocidad;
	}
	
	void sentidoD() {
		this.sentido = 0;
	}
	void sentidoI() {
		this.sentido = 1;
	}
	void sentidoAr() {
		this.sentido = 2;
	}
	void sentidoAb() {
		this.sentido = 3;
	}

	void dibujarDer(Entorno entorno) {
		if(this.getGigante() == false) 
			entorno.dibujarImagen(this.imagenDer, this.x, this.y, 0, 0.14);
		if(this.getGigante() == true)
			entorno.dibujarImagen(this.mikasaTitanDer, this.x, this.y, 0,0.4);
	}
	void dibujarIzq(Entorno entorno) {
		if(this.getGigante() == false) 
			entorno.dibujarImagen(this.imagenIzq, this.x, this.y, 0, 0.14);
		if(this.getGigante() == true)
			entorno.dibujarImagen(this.mikasaTitanIzq, this.x, this.y, 0,0.4);
	}
	void dibujarAtras(Entorno entorno) {
		if(this.getGigante() == false) 
			entorno.dibujarImagen(this.imagenAtras, this.x, this.y, 0, 0.16);
		if(this.getGigante() == true)
			entorno.dibujarImagen(this.mikasaTitanAtras, this.x, this.y, 0,0.3);
	}
	void dibujarAdelante(Entorno entorno) {
		if(this.getGigante() == false) 
			entorno.dibujarImagen(this.imagen, this.x, this.y, 0, 0.2);
		if(this.getGigante() == true)
			entorno.dibujarImagen(this.mikasaTitan, this.x, this.y, 0,0.3);
		
	}
	

 	void esGigante() {
		this.gigante = true;
	}
 	void noGigante() {
 		this.gigante = false;
 	}
}
	


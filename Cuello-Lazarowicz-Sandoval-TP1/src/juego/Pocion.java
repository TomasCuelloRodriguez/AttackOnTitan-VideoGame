package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;


public class Pocion {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private java.awt.Image imagen;
	
	public Pocion(int x, int y,int alto, int ancho) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.imagen = Herramientas.cargarImagen("imagenes/pocion.png");
	}
	
	void dibujar(Entorno entorno) {
		entorno.dibujarImagen(this.imagen, this.x, this.y, 0, 0.05);
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


	
	
}

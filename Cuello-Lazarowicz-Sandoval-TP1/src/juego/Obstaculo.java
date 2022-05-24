package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private java.awt.Image imagen;
	
	public Obstaculo(int x, int y,int alto, int ancho) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.imagen = Herramientas.cargarImagen("imagenes/arbol.png");
	}
	
	void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.1);
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

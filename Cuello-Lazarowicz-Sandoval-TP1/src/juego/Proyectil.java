package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private int sentido;
	private int velocidad;
	private Image imagen;


	public Proyectil(int x, int y,int alto, int ancho, int sentido,int velocidad) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.sentido = sentido;
		this.velocidad = velocidad;
		this.imagen = Herramientas.cargarImagen("imagenes/proyectil.png");
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

	void dibujar(Entorno entorno) {
		//entorno.dibujarRectangulo(this.x,this.y, this.ancho, this.alto,1, Color.WHITE);
		double angulo = 0;
		if (this.sentido == 1) {//izq
			angulo = 180;
		}
		if (this.sentido == 2) {//arriba
			angulo = 270;
		}
		if (this.sentido == 3) {//abajo
			angulo = 90;
		}
		entorno.dibujarImagen(this.imagen, this.x, this.y, Math.toRadians(angulo), 0.05);
	}


	void mover() {
		if(sentido == 0) {
			moverD();

		}
		else if(sentido == 1) {
			moverI();
		}
		else if(sentido == 2) {
			moverAb();
		}
		else 
			moverAr();
		
	}

	void moverD() {
		this.x = this.x +velocidad;
	}
	
	void moverI() {
		this.x = this.x -velocidad;
	}
	void moverAb() {
		this.y = this.y -velocidad;
	}
	
	void moverAr() {
		this.y = this.y +velocidad;
	}
}



package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Kyojin {
	private double x;
	private double y;
	private double alto;
	private double ancho;
	private double velocidad = 1.5;
	private Image imagen;
	
	
	public Kyojin(int x, int y,int alto, int ancho) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.imagen = Herramientas.cargarImagen("imagenes/kyojin.png");
		
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public double getAlto() {
		return this.alto;
	}
	public double getAncho() {
		return this.ancho;
	}
	public double getVelocidad() {
		return this.velocidad;
	}
	
	void dibujar(Entorno entorno) {
		//entorno.dibujarRectangulo(this.x,this.y, this.ancho, this.alto,0, Color.RED);
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.13);
	}
	void moverDe() {
		this.x = this.x + this.velocidad;
	}
	
	void moverIz() {
		this.x = this.x - this.velocidad;
	}
	
	void moverAr() {
		this.y = this.y - this.velocidad;
	}
	
	void moverAb() {
		this.y = this.y + this.velocidad;
	}
	
	void moverDe2() {
		this.x = this.x +this.velocidad*2;
	}
	
	void moverIz2() {
		this.x = this.x -this.velocidad*2;
	}
	
	void moverAr2() {
		this.y = this.y -this.velocidad*2;
	}
	
	void moverAb2() {
		this.y = this.y + this.velocidad*2;
	}
	


}


package juego;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;


public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Mykasa mykasa;
	private Pocion pocion;
	private Kyojin kyojin[];
	private Obstaculo obstaculo[];
	private Proyectil proyectil[];
	private int puntaje;
	private int reaparicionKyojines;
	private Image fondo;
	private boolean gameOver;
	private int spawnPocion;
	private boolean ganaste;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
			
		this.fondo = Herramientas.cargarImagen("imagenes/fondo.jpg");
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Attack on Titan, Final Season - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.mykasa = new Mykasa(400,300,40,40,4,false);
		this.proyectil = new Proyectil[1];
		this.kyojin = new Kyojin[4];
		kyojin[0] = this.nuevoKyojin();
		kyojin[1] = this.nuevoKyojin();
		kyojin[2] = this.nuevoKyojin();
		kyojin[3] = this.nuevoKyojin(); //salen todos de lugares aleatorios
		pocion = this.nuevaPocion(); //sale pocion de lugar aleatorio
		this.obstaculo = new Obstaculo[4];
		obstaculo[0] = new Obstaculo(500,200,60,60);
		obstaculo[1] = new Obstaculo(100,500,60,60);
		obstaculo[2] = new Obstaculo(300,70,60,60);
		obstaculo[3] = new Obstaculo(700,450,60,60);
		this.puntaje = 0;
		this.gameOver = false; //valida si el juego puede finalizar
		this.ganaste = false;
		this.spawnPocion = 0;
		this.reaparicionKyojines=150;
		// Inicia el juego!
		this.entorno.iniciar();

		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		//Cuenta Regresiva fin del juego

		
		entorno.dibujarImagen(fondo, 395, 368, 0, 1.1);
		this.textoChico("ENEMIGOS ELIMINADOS: " + this.puntaje, 10,20,Color.RED);
		this.condicionVictoria();
		
		
		if (this.gameOver == true) {
			this.textoGrande("GAME OVER", 180, 300, Color.RED);
			this.textoChico("Enemigos Derrotados: " + this.puntaje,300,350,Color.WHITE);
			//da por perdido el juego y finaliza
			return;
		}
		if(this.ganaste == true) {
			this.textoGrande("VICTORIA", 220, 300, Color.BLUE);
			this.textoChico("Enemigos Derrotados: " + this.puntaje,300,350,Color.WHITE);//da por ganado el juego y finaliza
			return;
		}
		
		
		
		this.mykasa.dibujar(this.entorno);
		// Respawn de Pocion
		if(this.pocion != null) {
			this.pocion.dibujar(this.entorno);
		}
		if(this.pocion == null) {
			this.spawnPocion ++;			
		}
		if(this.spawnPocion == 250) {
			this.spawnPocion = 0;
			this.pocion = this.nuevaPocion();
		}
					

		
		for(int i = 0; i < kyojin.length ; i++) {
			if(this.kyojin[i] != null){
				this.kyojin[i].dibujar(this.entorno);
			}
			if(this.kyojin[i] == null) {
				ReapareceKyojines(i);    //crea un nuevo kiojin en cierto tiempo
			}
			// Respawn de Kyojines
			if(this.reaparicionKyojines== 0) {
				this.reaparicionKyojines=150;
			}
		}
		for(int i = 0; i < obstaculo.length ; i++) {
			this.obstaculo[i].dibujar(this.entorno);
		}
		// Procesamiento de un instante de tiempo

		// movimiento
		
			if(this.mykasa.getX() < 780 && this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) || this.entorno.estaPresionada('d')|| this.entorno.estaPresionada('D')  ) {
				if(!colisionMykasaObstaculoDesdeLadoIz()) {
					this.mykasa.moverDe();
					this.mykasa.sentidoD();
				}
				if(colisionMykasaObstaculoDesdeLadoIz()) {
					this.mykasa.moverIz();
				}
			}

			if(this.mykasa.getX() > 10 && this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA) || this.entorno.estaPresionada('a')|| this.entorno.estaPresionada('A') ) {
				if(!colisionMykasaObstaculoDesdeLadoDe()) {
					this.mykasa.moverIz();
					this.mykasa.sentidoI();
				}
				if(colisionMykasaObstaculoDesdeLadoDe()) {
					this.mykasa.moverDe();
				}
				
			}
			if(this.mykasa.getY() > 10 && this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA) || this.entorno.estaPresionada('w')|| this.entorno.estaPresionada('W')) {
				if(!colisionMykasaObstaculoDesdeAb()) {
					this.mykasa.moverAr();
					this.mykasa.sentidoAr();
				}
				if(colisionMykasaObstaculoDesdeAb()) {
					this.mykasa.moverAb();
				}
			}
			
			if(this.mykasa.getY() < 580 && this.entorno.estaPresionada(this.entorno.TECLA_ABAJO) || this.entorno.estaPresionada('s')|| this.entorno.estaPresionada('S') ) {
				if(!colisionMykasaObstaculoDesdeAr()) {
					this.mykasa.moverAb();
					this.mykasa.sentidoAb();
				}
				if(colisionMykasaObstaculoDesdeAr()) {
					this.mykasa.moverAr();
				}
			}
			
			if(this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)){
				if(this.proyectil[0] ==null && !this.mykasa.getGigante()) { //se agrega la condicion del null para que no se cree cada vez que se aprieta la barra 
				proyectil[0] = new Proyectil(this.mykasa.getX(),this.mykasa.getY(),10,10,this.mykasa.getSentido(),9);
				}
			}
			for(int i = 0;i < this.proyectil.length;i++) { 
				if(this.proyectil[i] !=null ) {
				this.proyectil[i].dibujar(entorno); 
				this.proyectil[i].mover();
				
				
				}
			}


					

		// Dibujado

	
		//colision
        //COLISIONES MYKASA	
			//POCION

			if(this.pocion != null && colisionMykasaPocion()) {
				this.pocion = null;
				this.mykasa.esGigante();
			}
			duracionPocionGigante();

				//KYOJINES
		for(int i = 0; i < this.kyojin.length;i++) {
            if(colisionMykasaKyojin(i) && this.mykasa.getGigante() == true) {
                this.kyojin[i] = null;
                this.mykasa.noGigante();
                puntaje ++;
            }
            else if(colisionMykasaKyojin(i) && this.mykasa.getGigante() == false){
            	this.gameOver = true;
            }
		}	
		     //COLISONES KYOJINES
		          //KYOJIN 
					//OBSTACULOS

	
        		//PROYECTIL
		for(int i = 0; i < this.kyojin.length;i++) {
			for(int x = 0; x < this.proyectil.length;x++) {
				if(this.proyectil[x] != null && colisionKyojinProyectil(i,x)){
					this.proyectil[x] = null;
					this.kyojin[i] = null;
					puntaje ++;
					
				}
			}
		}	
					
		for(int i = 0; i < this.obstaculo.length;i++) {
			for(int x = 0; x < this.proyectil.length;x++) {
				if(this.proyectil[x] != null && colisionProyectilObstaculo(i,x)){
					this.proyectil[x] = null;
				}
			}
		}
		
		for(int x = 0; x < this.proyectil.length;x++) {
			if(this.proyectil[x] != null && colisionProyectilBordes(x)){
				this.proyectil[x] = null;
			}
		}
	

		
   
	
		// movimiento kyojin

		for(int kyojin = 0 ; kyojin < this.kyojin.length; kyojin++){
			if( this.kyojin[kyojin] !=null) {
			if(this.mykasa.getX() < this.kyojin[kyojin].getX())
				this.kyojin[kyojin].moverIz();
			if (this.mykasa.getX() > this.kyojin[kyojin].getX())
				this.kyojin[kyojin].moverDe();
			if(this.mykasa.getY() < this.kyojin[kyojin].getY())
				this.kyojin[kyojin].moverAr();
			if(this.mykasa.getY() > this.kyojin[kyojin].getY())
				this.kyojin[kyojin].moverAb();
			}
			for(int obstaculo = 0 ; obstaculo < this.obstaculo.length; obstaculo++){
				if( colisionKyojinObstaculo(kyojin,obstaculo)&& colisionKyojinObstaculoAr(kyojin,obstaculo))
					this.kyojin[kyojin].moverIz();
				if( colisionKyojinObstaculo(kyojin,obstaculo)&& colisionKyojinObstaculoAb(kyojin,obstaculo))
					this.kyojin[kyojin].moverDe();
				if( colisionKyojinObstaculo(kyojin,obstaculo)&& colisionKyojinObstaculoIzq(kyojin,obstaculo))
					this.kyojin[kyojin].moverAr();
				if( colisionKyojinObstaculo(kyojin,obstaculo)&& colisionKyojinObstaculoDer(kyojin,obstaculo))
					this.kyojin[kyojin].moverAb();
				}	
			for(int kyojinDos = 0; kyojinDos < this.kyojin.length ; kyojinDos++) {	
					if(colisionKyojinKyojin(kyojin,kyojinDos) && this.kyojin[kyojin] !=null && this.kyojin[kyojinDos] !=null && !this.kyojin[kyojin].equals(kyojinDos)){
						if(colisionKyojinKyojinDer(kyojin,kyojinDos) ) {
							this.kyojin[kyojinDos].moverIz2();
						}
						if(colisionKyojinKyojinIzq(kyojin,kyojinDos)) {
							this.kyojin[kyojinDos].moverDe2();
						}
						if(colisionKyojinKyojinAr(kyojin,kyojinDos)) {
							this.kyojin[kyojinDos].moverAb2();
						}
						if(colisionKyojinKyojinAb(kyojin,kyojinDos)) {
								this.kyojin[kyojinDos].moverAr2();
							}
					}
				}
			
		}	
	}
		
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		
		Juego juego = new Juego();
	}

	//crea un nuevo enemigo en posicion random
	private Kyojin nuevoKyojin() {
		Random random = new Random();
		int xRandom = random.nextInt(750);
		int yRandom = random.nextInt(550);
		while( xRandom < this.mykasa.getX() + this.mykasa.getAncho()/2 + 100 &&
			xRandom  >	this.mykasa.getX() - this.mykasa.getAncho()/2 - 100 &&
			yRandom <	this.mykasa.getY() + this.mykasa.getAlto()/2 + 100  &&
			yRandom	 >  this.mykasa.getY() - this.mykasa.getAlto()/2 - 100 
				) {
			xRandom = random.nextInt(750);
			yRandom = random.nextInt(550);
		}
		return new Kyojin(xRandom,yRandom,30,30);	
	}
	
	//crea un nueva pocion en lugar aleatorio
	private Pocion nuevaPocion() {
		Random random = new Random();
		int xRandom = random.nextInt(500) + 150;
		int yRandom = random.nextInt(400) + 100;
		return new Pocion(xRandom,yRandom,40,40);
	}
	
	void ReapareceKyojines(int i) {
		this.reaparicionKyojines--;
		if (reaparicionKyojines==0)
			this.kyojin[i] = this.nuevoKyojin();							//crea un nuevo kyojin cuando encuentra uno muerto 
		
		
	}
	void duracionPocionGigante() {
		if(this.mykasa.getGigante()) {
			this.texto("MODO GIGANTE", 600,20);
		}

	}
	// COLISIONES MYKASA
		
	public boolean colisionMykasaPocion() {
		if(this.mykasa.getX() + this.mykasa.getAncho()/2 >= this.pocion.getX() &&
			this.mykasa.getX()<=this.pocion.getX() + this.pocion.getAncho()/2 &&
			this.mykasa.getY() + this.mykasa.getAlto()/2 >= this.pocion.getY() &&
			this.mykasa.getY() <= this.pocion.getY() + this.pocion.getAlto()/2
			)
			return true;
		return false;
	}
	public boolean colisionMykasaObstaculo() {
		for(int obstaculo =0; obstaculo < this.obstaculo.length; obstaculo++) {
		if(this.mykasa.getX() + this.mykasa.getAncho()/2 > this.obstaculo[obstaculo].getX() -this.obstaculo[obstaculo].getAncho()/2 &&
			this.mykasa.getX() - this.mykasa.getAncho()/2<this.obstaculo[obstaculo].getX() + this.obstaculo[obstaculo].getAncho() /2 &&
			this.mykasa.getY() + this.mykasa.getAlto()/2 > this.obstaculo[obstaculo].getY() -this.obstaculo[obstaculo].getAlto()/2 &&
			this.mykasa.getY() - this.mykasa.getAlto()/2  < this.obstaculo[obstaculo].getY() + this.obstaculo[obstaculo].getAlto()/2
			)
			return true;
		}
		return false;
	}
	// COLISIONES KYOJINES
	public boolean colisionMykasaKyojin(int Kiojin) {
		if(this.kyojin[Kiojin] !=null) {
		if(this.mykasa.getX() + this.mykasa.getAncho()/2 >= this.kyojin[Kiojin].getX() &&
			this.mykasa.getX()<=this.kyojin[Kiojin].getX() + this.kyojin[Kiojin].getAncho()/2 &&
			this.mykasa.getY() + this.mykasa.getAlto()/2 >= this.kyojin[Kiojin].getY() &&
			this.mykasa.getY() <= this.kyojin[Kiojin].getY() + this.kyojin[Kiojin].getAlto()/2
			)
			return true;
		}
		return false;
	}
	public boolean colisionKyojinObstaculo(int Kiojin,int obstaculo) {
        if(this.kyojin[Kiojin] !=null) {
        if(this.kyojin[Kiojin].getX() + this.kyojin[Kiojin].getAncho()/2 >= this.obstaculo[obstaculo].getX() - this.obstaculo[obstaculo].getAncho()/2 &&
            this.kyojin[Kiojin].getX() - this.kyojin[Kiojin].getAncho()/2<=this.obstaculo[obstaculo].getX() + this.obstaculo[obstaculo].getAncho()/2 &&
            this.kyojin[Kiojin].getY() + this.kyojin[Kiojin].getAlto()/2 >= this.obstaculo[obstaculo].getY() -  this.obstaculo[obstaculo].getAlto()/2 &&
            this.kyojin[Kiojin].getY()+ this.kyojin[Kiojin].getAlto()/2 <= this.obstaculo[obstaculo].getY() + this.obstaculo[obstaculo].getAlto()/2
            )
            return true;
        }
        return false;
    }
	public boolean colisionKyojinObstaculoDer(int kyojin , int Obstaculo) {
		if(this.kyojin[kyojin] !=null && this.obstaculo[Obstaculo] != null) {
			if(colisionKyojinObstaculo(kyojin,Obstaculo)) {
				if(this.kyojin[kyojin].getX() + this.kyojin[kyojin].getAncho() > this.obstaculo[Obstaculo] .getX()-this.obstaculo[Obstaculo].getAncho())
						return true;
			}
			}
					return false;
	}
	public boolean colisionKyojinObstaculoIzq(int kyojin , int Obstaculo) {
		if(this.kyojin[kyojin] !=null && this.kyojin[Obstaculo] != null) {
			if(colisionKyojinObstaculo(kyojin,Obstaculo)) {
				if(this.kyojin[kyojin].getX() - this.kyojin[kyojin].getAncho()  < this.obstaculo[Obstaculo].getX() + this.obstaculo[Obstaculo].getAncho())
						return true;
			}
			}
					return false;
					
	}
	public boolean colisionKyojinObstaculoAr(int kyojin , int Obstaculo) {
		if(this.kyojin[kyojin] !=null && this.kyojin[Obstaculo] != null) {
				if(colisionKyojinObstaculo(kyojin,Obstaculo)) {
					if(this.kyojin[kyojin].getY() + this.kyojin[kyojin].getAlto() > this.obstaculo[Obstaculo].getY() -this.obstaculo[Obstaculo].getAlto())
						return true;
			}
			}
					return false;
					
			}
	public boolean colisionKyojinObstaculoAb(int kyojin , int Obstaculo) {
		if(this.kyojin[kyojin] !=null && this.kyojin[Obstaculo] != null) {
			if(colisionKyojinObstaculo(kyojin,Obstaculo)) {
				if(this.kyojin[kyojin].getY() - this.kyojin[kyojin].getAlto() < this.obstaculo[Obstaculo].getY() + this.obstaculo[Obstaculo].getAlto())
					return true;
		}
		}
				return false;
				
		}
	public boolean colisionKyojinProyectil(int Kiojin, int Proyectil) {
		if(this.kyojin[Kiojin] !=null) {
		if(this.kyojin[Kiojin].getX() + this.kyojin[Kiojin].getAncho() >= this.proyectil[Proyectil].getX() -this.proyectil[Proyectil].getAncho() &&
			this.kyojin[Kiojin].getX() - this.kyojin[Kiojin].getAncho()<=this.proyectil[Proyectil].getX() + this.proyectil[Proyectil].getAncho() &&
			this.kyojin[Kiojin].getY()  + this.kyojin[Kiojin].getAlto() >= this.proyectil[Proyectil].getY() -this.proyectil[Proyectil].getAlto() &&
			this.kyojin[Kiojin].getY() -this.kyojin[Kiojin].getAlto() <= this.proyectil[Proyectil].getY() + this.proyectil[Proyectil].getAlto()
			)
			return true;
		}
		return false;
	}
	
	
	public boolean colisionKyojinKyojin(int kyojin , int kyojinDos) {
		if(this.kyojin[kyojin] !=null && this.kyojin[kyojinDos] != null) {
			if(this.kyojin[kyojin].getX() + this.kyojin[kyojin].getAncho() > this.kyojin[kyojinDos].getX() -this.kyojin[kyojinDos].getAncho() &&
					this.kyojin[kyojin].getX()- this.kyojin[kyojin].getAncho() < this.kyojin[kyojinDos].getX() + this.kyojin[kyojinDos].getAncho() &&
					this.kyojin[kyojin].getY() + this.kyojin[kyojin].getAlto() > this.kyojin[kyojinDos].getY() -this.kyojin[kyojinDos].getAlto() &&
					this.kyojin[kyojin].getY() - this.kyojin[kyojin].getAlto() < this.kyojin[kyojinDos].getY() + this.kyojin[kyojinDos].getAlto()
					)
					return true;
		}
				return false;
		}
	public boolean colisionKyojinKyojinDer(int kyojin , int kyojinDos) {
		if(this.kyojin[kyojin] !=null && this.kyojin[kyojinDos] != null) {
		if(colisionKyojinKyojin(kyojin,kyojinDos)) {
			if(this.kyojin[kyojin].getX() + this.kyojin[kyojin].getAncho() > this.kyojin[kyojinDos] .getX() -this.kyojin[kyojinDos].getAncho())
					return true;
		}
		}
				return false;
				
		}
	public boolean colisionKyojinKyojinIzq(int kyojin , int kyojinDos) {
		if(this.kyojin[kyojin] !=null && this.kyojin[kyojinDos] != null) {
		if(colisionKyojinKyojin(kyojin,kyojinDos)) {
			if(this.kyojin[kyojin].getX() - this.kyojin[kyojin].getAncho()  < this.kyojin[kyojinDos].getX() + this.kyojin[kyojinDos].getAncho())
					return true;
		}
		}
				return false;
				
		}
	public boolean colisionKyojinKyojinAr(int kyojin , int kyojinDos) {
		if(this.kyojin[kyojin] !=null && this.kyojin[kyojinDos] != null) {
		if(colisionKyojinKyojin(kyojin,kyojinDos)) {
			if(this.kyojin[kyojin].getY() + this.kyojin[kyojin].getAlto() > this.kyojin[kyojinDos].getY() -this.kyojin[kyojinDos].getAlto())
					return true;
		}
		}
				return false;
				
		}
	public boolean colisionKyojinKyojinAb(int kyojin , int kyojinDos) {
		if(this.kyojin[kyojin] !=null && this.kyojin[kyojinDos] != null) {
		if(colisionKyojinKyojin(kyojin,kyojinDos)) {
			if(this.kyojin[kyojin].getY() - this.kyojin[kyojin].getAlto() < this.kyojin[kyojinDos].getY() + this.kyojin[kyojinDos].getAlto())
					return true;
		}
		}
				return false;
				
	}
	

	// COLISIONES OBSTACULOS
	
	public boolean colisionProyectilObstaculo(int obstaculo, int Proyectil) {
		if(this.obstaculo[obstaculo].getX() + this.obstaculo[obstaculo].getAncho()/2 >= this.proyectil[Proyectil].getX() &&
				this.obstaculo[obstaculo].getX()<=this.proyectil[Proyectil].getX() + this.proyectil[Proyectil].getAncho()/2 &&
				this.obstaculo[obstaculo].getY() + this.obstaculo[obstaculo].getAlto()/2 >= this.proyectil[Proyectil].getY() &&
				this.obstaculo[obstaculo].getY() <= this.proyectil[Proyectil].getY() + this.proyectil[Proyectil].getAlto()/2
				)
				return true;
			return false;
		}
	public boolean colisionProyectilBordes(int Proyectil) {
		if(this.proyectil[Proyectil].getX() >799  || this.proyectil[Proyectil].getX() < 1 ||
				this.proyectil[Proyectil].getY() <1 || this.proyectil[Proyectil].getY() > 599){
			return true;
		}
	
		return false;
	}
		
	
	
	
	public boolean colisionMykasaObstaculoDesdeLadoDe() {
	if ( colisionMykasaObstaculo() ){
		for(int obstaculo =0; obstaculo < this.obstaculo.length; obstaculo++) {
			if(this.mykasa.getX() + this.mykasa.getAncho()/2 < this.obstaculo[obstaculo].getX() -this.obstaculo[obstaculo].getAncho()/2)
				return true;
		}
	}
	return false;
	
		}
	
	
	public boolean colisionMykasaObstaculoDesdeLadoIz() {
		if ( colisionMykasaObstaculo() ){
			for(int obstaculo =0; obstaculo < this.obstaculo.length; obstaculo++) {
				if(this.mykasa.getX() - this.mykasa.getAncho()/2>this.obstaculo[obstaculo].getX() + this.obstaculo[obstaculo].getAncho() /2) 
					return true;
			}
		
		}
		return false;	
	}
	public boolean colisionMykasaObstaculoDesdeAb() {
		if ( colisionMykasaObstaculo() ) {
			for(int obstaculo =0; obstaculo < this.obstaculo.length; obstaculo++) {
				if(this.mykasa.getY() - this.mykasa.getAlto()  < this.obstaculo[obstaculo].getY() + this.obstaculo[obstaculo].getAlto()) 
					return true;


			}
		}
			return false;
	}
	
		
	public boolean colisionMykasaObstaculoDesdeAr() {
		if ( colisionMykasaObstaculo() ) {
			for(int obstaculo =0; obstaculo < this.obstaculo.length; obstaculo++) {
				if(this.mykasa.getY() + this.mykasa.getAlto() > this.obstaculo[obstaculo].getY() - this.obstaculo[obstaculo].getAlto())
				
					return true;

			}
		}
		return false;
	}
	public boolean condicionVictoria() {
		if(this.kyojin[0] == null && this.kyojin[1] == null && this.kyojin[2] == null && this.kyojin[3] == null) {
			return this.ganaste = true;
		
		}
		return this.ganaste = false;
	}
	
	//agrega texto con sombra
	public void texto(String texto, int x, int y) {
		this.entorno.cambiarFont(Font.MONOSPACED,20 ,Color.BLACK);
		this.entorno.escribirTexto(texto, x - 1, y - 1);
		this.entorno.cambiarFont(Font.MONOSPACED,20 ,Color.RED);
		this.entorno.escribirTexto(texto, x, y);
	}
	
	//agrega texto grande con sombra y color
	public void textoGrande(String texto, int x, int y, Color color) {
		entorno.cambiarFont(Font.MONOSPACED,80 ,Color.BLACK);
		entorno.escribirTexto(texto, x - 2, y - 2);
		entorno.cambiarFont(Font.MONOSPACED,80 , color);
		entorno.escribirTexto(texto, x, y);
	}
	public void textoChico(String texto, int x, int y, Color color) {
		entorno.cambiarFont(Font.MONOSPACED,20 ,Color.BLACK);
		entorno.escribirTexto(texto, x - 1, y - 1);
		entorno.escribirTexto(texto, x - 2, y - 2);
		entorno.escribirTexto(texto, x - 1, y - 1);
		entorno.cambiarFont(Font.MONOSPACED,20 , color);
		entorno.escribirTexto(texto, x, y);
	}


	
	
		
}
	


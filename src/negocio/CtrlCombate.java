package negocio;

import data.DataPersonaje;
import entidades.Personaje;
import utils.ApplicationException;

public class CtrlCombate {

	private data.DataPersonaje dataP;
	private Personaje jugador1,jugador2,jugadorTurnoActual;
	private boolean finCombate;
	
	
	public boolean isFinCombate() {
		return finCombate;
	}


	public void setFinCombate(boolean finCombate) {
		this.finCombate = finCombate;
	}


	public CtrlCombate(){
		dataP=new DataPersonaje();
	}
	
	
    public Personaje getJugadorTurnoActual() {
		return jugadorTurnoActual;
	}
  
        
    public void generarNuevoTurno(){

    		if(jugador1.equals(jugadorTurnoActual)){
    			jugadorTurnoActual=jugador2;
    		}else{
    			jugadorTurnoActual=jugador1;
    		}
    }
    	
    
    	
	public void generarPrimerTurnoAleatorio() {
		double numeroAleatorio;
		do {
    		numeroAleatorio=Math.random();
    		
			if (numeroAleatorio > 0.5) {
				jugadorTurnoActual=jugador1;
			} else {
				jugadorTurnoActual=jugador2;
			} 
		} while (numeroAleatorio==0.5);
	}
    
	
    public void ataque(int puntosAtaque) throws ApplicationException{

		if(jugador1==null || jugador2==null){
			throw new ApplicationException("Primero ingrese lo personajes y luego genere una nueva partida");
		}
    	
    	if(jugadorTurnoActual.equals(jugador1)){
    		//jugador1 ataca, jugador2 recibe ataque
    		
    		if(jugador1.getEnergiaActual()<puntosAtaque){
    			throw new ApplicationException("Puntos ataque deben ser menor que energia actual del atacante");
    		}
    		
    		jugador1.ataca(puntosAtaque);
    		jugador2.recibeAtaque(puntosAtaque);
    		if(jugador2.getVidaActual()<=0){
    			finCombate=true;
    			combateGanado(jugador1);
    		}
    	}else{
    		//jugador2 ataca, jugador1 recibe ataque
    		
    		if(jugador2.getEnergiaActual()<puntosAtaque){
    			throw new ApplicationException("Puntos ataque deben ser menor que energia actual del atacante");
    		}
    		
    		jugador1.recibeAtaque(puntosAtaque);
    		jugador2.ataca(puntosAtaque);
    		if(jugador1.getVidaActual()<=0){
    			finCombate=true;
    			combateGanado(jugador2);
    		}
    	}
    	
    	if(!finCombate){
    		generarNuevoTurno();
    	}
    	
    }

    
    public void combateGanado(Personaje j){
    	j.aumentaPuntosTotales();
    	dataP.update(j);
    }
    

	public void setJugador1(Personaje p) {
		jugador1=p;
	}


	public void setJugador2(Personaje p) {
		jugador2=p;
	}
	
	

	public Personaje getJugador1() {
		return jugador1;
	}


	public Personaje getJugador2() {
		return jugador2;
	}


	public void setJugadorTurnoActual(Personaje jugadorTurnoActual) {
		this.jugadorTurnoActual = jugadorTurnoActual;
	}

	public Personaje getByNombre(String nombre) throws ApplicationException{
		Personaje p = new Personaje();
		Personaje respuesta = new Personaje();
		p.setNombre(nombre);
		respuesta=dataP.getByNombre(p);
		if(respuesta==null) throw new ApplicationException();
		return respuesta;
		
	}
	
	public void nuevaPartida(Personaje jugador1,Personaje jugador2) throws ApplicationException{
			
		if(jugador1==null || jugador2==null){
			throw new ApplicationException("Ingrese personajes para jugar");
		}else{
			if(jugador1.equals(jugador2)){
				throw new ApplicationException("Ingrese dos personajes diferentes");
			}else{
								  
			      setJugador1(jugador1);
			      setJugador2(jugador2);
		          finCombate=false;
		          generarPrimerTurnoAleatorio();
			}
		}
	}

	
	public void defensa() throws ApplicationException{
		
		if(jugador1==null || jugador2==null){
			throw new ApplicationException("Primero ingrese lo personajes y luego genere una nueva partida");
		}else{
			
			if(jugadorTurnoActual.equals(jugador1)){
				//jugador1 defiende
				jugador1.defiende();
				}else{
					//jugador2 defiende
					jugador2.defiende();
					}
			//generarNuevoTurno();
	    }

	}
    
	
}

package negocio;

import data.DataPersonaje;
import entidades.Personaje;
import utils.ApplicationException;

public class CtrlPersonajes {	
	private data.DataPersonaje dataP;
	private Object per;
	
	public CtrlPersonajes(){
		dataP=new DataPersonaje();	
	}
	
	public void add(Personaje p) throws ApplicationException{
		per = dataP.getByNombre(p);
		if(per == null){
			dataP.add(p);	
		}else{
			throw new ApplicationException("Ya existe un personaje con ese nombre");
	   }
			
	}

	public void update(Personaje p) throws ApplicationException{
		per = dataP.getByNombre(p);
		if(per != null){
			dataP.update(p);
			}else{
				throw new ApplicationException("Personaje inexistente");
			}
	}
	

	
	public void delete(Personaje p)throws ApplicationException{
		per = dataP.getByNombre(p);
		if(per!=null){
			dataP.delete(p);}
		else{
			throw new ApplicationException("Personaje inexistente");
		}
		
	}

	public Personaje buscar(Personaje p)throws ApplicationException{
		per = dataP.getByNombre(p);
		if(per==null){
			throw new ApplicationException("Personaje inexistente");
			}
		return (Personaje) per;
		
	}
}

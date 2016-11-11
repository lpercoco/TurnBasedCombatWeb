package data;

import java.sql.*;
import entidades.*;
import utils.ApplicationException;

public class DataPersonaje {


	public void add(Personaje p){
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into personajes(nombre,vida,energia,evasion,defensa)"+
					" values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
						
			stmt.setString(1,p.getNombre());
			stmt.setInt(2,p.getVida());
			stmt.setInt(3,p.getEnergia());
			stmt.setInt(4,p.getEvasion());
			stmt.setInt(5,p.getDefensa());
			stmt.execute();
			
			rs=stmt.getGeneratedKeys();
			if(rs!=null && rs.next()){
				p.setCodigo(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
}
	public void update(Personaje p){
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
					"update personajes set vida=?,energia=?,evasion=?,defensa=?,puntosTotales=?"+
					" where nombre=?");

			stmt.setInt(1,p.getVida());
			stmt.setInt(2,p.getEnergia());
			stmt.setInt(3,p.getEvasion());
			stmt.setInt(4,p.getDefensa());
			stmt.setInt(5,p.getPuntosTotales());
			stmt.setString(6,p.getNombre());
            stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		}
}
	public void delete(Personaje p){
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"delete from personajes where nombre=?");
			stmt.setString(1, p.getNombre());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		}
		
}

	public Personaje getByNombre(Personaje per){
		Personaje p=null;
		
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select codigo,nombre,vida,energia,evasion,defensa,puntosTotales from personajes where nombre=?");
			stmt.setString(1, per.getNombre());
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				p=new Personaje();
				p.setCodigo(rs.getInt("codigo"));
				p.setVida(rs.getInt("vida"));
				p.setNombre(rs.getString("nombre"));
				p.setDefensa(rs.getInt("defensa"));
				p.setEvasion(rs.getInt("Evasion"));
				p.setPuntosTotales(rs.getInt("puntosTotales"));
				p.setEnergia(rs.getInt("Energia"));
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		}
		
		return p;
	}

	
	
}

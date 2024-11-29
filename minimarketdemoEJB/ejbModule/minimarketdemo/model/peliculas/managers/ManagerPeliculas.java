package minimarketdemo.model.peliculas.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.Director;
import minimarketdemo.model.core.entities.Pelicula;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerDirectores
 */
@Stateless
@LocalBean
public class ManagerPeliculas {

	@EJB
	private ManagerDAO mDAO; 
    /**
     * Default constructor. 
     */
    public ManagerPeliculas() {
        // TODO Auto-generated constructor stub
    }

    //Directores
    public List<Director> findAllDirectores(){
    	return mDAO.findAll(Director.class);
    }
    
    public void insertarDirectores(Director newDirector) throws Exception {
    	mDAO.insertar(newDirector);
    }
    
    public void actualizarDirector( Director director ) throws Exception {
    	Director updateDirector = (Director) mDAO.findById(Director.class, director.getId());
    	updateDirector.setNombre(director.getNombre());
    	updateDirector.setApellido(director.getApellido());
    	updateDirector.setFechaNacimiento(director.getFechaNacimiento());
    	updateDirector.setNacionalidad(director.getNacionalidad());
    	
    	mDAO.actualizar(updateDirector);
    }
    
    public void eliminarDirector(int id) throws Exception {
    	mDAO.eliminar(Director.class, id);
    }
    
    
    //Peliculas
    public List<Pelicula> findAllPeliculas(){
    	return mDAO.findAll(Pelicula.class);
    }
    
    public void insertarPelicula(Pelicula newPelicula, int IdDirector) throws Exception {
    	Director director = (Director) mDAO.findById(Director.class, IdDirector);
    	newPelicula.setDirector(director);
    	mDAO.insertar(newPelicula);
    }
    
    public void actualizarPelicula( Pelicula pelicula ) throws Exception {
    	Pelicula updatePelicula = (Pelicula) mDAO.findById(Pelicula.class, pelicula.getId());
    	updatePelicula.setTitulo(pelicula.getTitulo());
    	updatePelicula.setDirector(pelicula.getDirector());
    	updatePelicula.setUrlImg(pelicula.getUrlImg());
    	updatePelicula.setFechaEstreno(pelicula.getFechaEstreno());
    	
    	mDAO.actualizar(updatePelicula);
    }
    
    public void eliminarPelicula(int id) throws Exception {
    	Pelicula pelicula = (Pelicula) mDAO.findById(Pelicula.class, id);
    	
    	//Remueve la pelicula de la lista del director en memoria
    	Director director = pelicula.getDirector();
        director.getPeliculas().remove(pelicula);
        pelicula.setDirector(null); 
        
    	mDAO.eliminar(Pelicula.class, pelicula.getId());
    }
}

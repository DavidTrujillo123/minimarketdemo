package minimarketdemo.controller.peliculas;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.Director;
import minimarketdemo.model.core.entities.Pelicula;
import minimarketdemo.model.peliculas.managers.ManagerPeliculas;

@Named
@SessionScoped
public class BeanPeliculas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerPeliculas manager;
	private List<Pelicula> listPeliculas;
	private List<Director> listDirector;
	private Director director;
	private int idDirector;
	private Pelicula pelicula;

	public BeanPeliculas() {
		// TODO Auto-generated constructor stub
	}

	public String redirectPeliculas() {
		listPeliculas = manager.findAllPeliculas();
		listDirector = manager.findAllDirectores();
		pelicula = new Pelicula();
		director = new Director();
		idDirector = 0;
		return "peliculas";
	}

	public void guardarPelicula() {
		try {
			manager.insertarPelicula(pelicula, idDirector);
			listPeliculas = manager.findAllPeliculas();
			pelicula = new Pelicula();
			idDirector = 0;
			JSFUtil.crearMensajeINFO("Pelicula ingresada correctamente!");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
		}
		
	}
	public void actualizarPelicula() {
		try {
			manager.actualizarPelicula(pelicula);
			JSFUtil.crearMensajeINFO("Pelicula actualizada correctamente!");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
		}
	}
	public String redirectEditarPelicula(Pelicula peliculaSeleccionada) {
		pelicula = peliculaSeleccionada;
		idDirector = peliculaSeleccionada.getDirector().getId();
		return "pelicula_edicion";
	}
	public void eliminarPelicula(int id) {
		try {
			manager.eliminarPelicula(id);
			listPeliculas = manager.findAllPeliculas();
			JSFUtil.crearMensajeINFO("Pelicula eliminado correctamente!");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
		}
	}
	public List<Pelicula> getListPeliculas() {
		return listPeliculas;
	}

	public void setListPeliculas(List<Pelicula> listPeliculas) {
		this.listPeliculas = listPeliculas;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public List<Director> getListDirector() {
		return listDirector;
	}

	public void setListDirector(List<Director> listDirector) {
		this.listDirector = listDirector;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public int getIdDirector() {
		return idDirector;
	}

	public void setIdDirector(int idDirector) {
		this.idDirector = idDirector;
	}
	
}

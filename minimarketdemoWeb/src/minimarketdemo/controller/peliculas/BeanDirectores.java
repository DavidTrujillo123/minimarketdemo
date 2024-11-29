package minimarketdemo.controller.peliculas;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.Director;
import minimarketdemo.model.peliculas.managers.ManagerPeliculas;

@Named
@SessionScoped
public class BeanDirectores implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerPeliculas manager;
	private List<Director> listDirectores;
	private Director director;	
	
	public BeanDirectores() {
		// TODO Auto-generated constructor stub
	}

	public String redirectDirectores () {
		listDirectores = manager.findAllDirectores();
		director = new Director();
		return "directores";
	}

	public void guardarNuevoDirector() {
		try {
			manager.insertarDirectores(director);
			listDirectores = manager.findAllDirectores();
			director = new Director();
			JSFUtil.crearMensajeINFO("Director ingresado correctamente!");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
		}
	}
	
	public String redirectEditarDirectores(Director directorSeleccionado) {
		director = directorSeleccionado;
		return "director_edicion";
	}
	
	public void actualizarDirector() {
		try {
			manager.actualizarDirector(director);
			JSFUtil.crearMensajeINFO("Director actualizado correctamente!");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
		}
	}
	
	public void eliminarDirector(int id) {
		try {
			manager.eliminarDirector(id);
			listDirectores = manager.findAllDirectores();
			JSFUtil.crearMensajeINFO("Director eliminado correctamente!");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
		}
	}
	
	public List<Director> getListDirectores() {
		return listDirectores;
	}

	public void setListDirectores(List<Director> listDirectores) {
		this.listDirectores = listDirectores;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director newDirector) {
		this.director = newDirector;
	}
	
	
}

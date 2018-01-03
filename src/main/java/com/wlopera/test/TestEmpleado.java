package com.wlopera.test;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.wlopera.modelo.Empleado;

public class TestEmpleado {
	
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		// Crear gestor de perstiencia EM
		emf = Persistence.createEntityManagerFactory("Persistencia");
		manager = emf.createEntityManager();		
		
		Empleado empleado1 = new Empleado(12L, "Pablo", "Marmol", new GregorianCalendar(1979, 6,6).getTime());
		Empleado empleado2 = new Empleado(22L, "Pedro", "Picapiedra", new GregorianCalendar(1945, 10,10).getTime());
		manager.getTransaction().begin();
		manager.persist(empleado1);
		manager.persist(empleado2);
		manager.getTransaction().commit();
		
		imprimirTodo();
	}
	
	@SuppressWarnings("unchecked")
	public static void imprimirTodo() {
		List<Empleado> empleados = (List<Empleado>)manager.createQuery("FROM Empleado").getResultList();
		System.out.println("En esta Base de datos hay: "+ empleados.size() + " empleados.");
		
		for (Empleado empleado:empleados) {
			System.out.println(empleado.toString());
		}
	}

}

package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.object.Empresa;

public class EmpresaDao {
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	private static SessionFactory buildSessionFactory() {
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;


	}

	public static void createRecord(Empresa empresa) {
		int count = 0;

		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();
			sessionObj.save(empresa);

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("Empresa Criada");

		}catch(Exception sqlException) {
			sessionObj.getTransaction().rollback();
			sqlException.printStackTrace();    		 
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}


	}
	
	@SuppressWarnings("rawtypes")
	public static List displayRecords() {
		List empresas = new ArrayList();

		try {
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();
			empresas = sessionObj.createQuery("FROM Empresa").list();
		} catch(Exception sqlException) {
			sessionObj.getTransaction().rollback();
			sqlException.printStackTrace();

		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}

		return empresas;
	}

	public static void updateRecord(int id, Empresa empresa) {
		try{
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();
			Empresa novaEmpresa = (Empresa) sessionObj.get(Empresa.class, id);

			novaEmpresa.setCnpj(empresa.getCnpj());
			novaEmpresa.setNome(empresa.getNome());
			sessionObj.getTransaction().commit();
		}catch(Exception e) {
			sessionObj.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
	
    public static Empresa findRecordById(Integer id) {
        Empresa empresa = null;
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();
        empresa = (Empresa) sessionObj.load(Empresa.class, id);
        
        return empresa;


    }
}

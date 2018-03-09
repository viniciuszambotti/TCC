package com.paths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.dao.EmpresaDao;
import com.object.Empresa;

@Path("empresa")
public class EmpresaWs {
    @GET
    @Path("test")
    public String getIt() {
    	Empresa empresa= new Empresa();
    	empresa.setCnpj("112312313");
    	empresa.setNome("TESTE");
  
   
    	EmpresaDao.createRecord(empresa);

       return "Go     t it!";
    }

}

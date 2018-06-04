package com.paths;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.dao.DepartamentoDao;
import com.database.DbConnection;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.object.Departamento;

@Path("departamento")
public class DepartamentoWs {
	   @POST
	    @Path("criar")
	    public String criar(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
	    	
	    	ObjectMapper mapper = new ObjectMapper();
	    	Departamento obj = mapper.readValue(util.formataReq(req), Departamento.class);
	    	Connection conn = DbConnection.Conectar();
	    	DepartamentoDao.createRecord(conn, obj);

	       return "ok";
	    }
	    
	    @POST
	    @Path("get")
	    public String getIt() throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
	    	ObjectMapper mapper = new ObjectMapper();
	    	Connection conn = DbConnection.Conectar();
	    	

	       return mapper.writeValueAsString(DepartamentoDao.getAll(conn));
	    }

	    @POST
	    @Path("editar")
	    public String getIt(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
	    	
	    	ObjectMapper mapper = new ObjectMapper();
	    	Departamento obj = mapper.readValue(util.formataReq(req), Departamento.class);
	    	Connection conn = DbConnection.Conectar();
	    	DepartamentoDao.updateRecord(conn, obj);

	       return "ok";
	    }
	    
	    @POST
	    @Path("deletar")
	    public String deletar(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
	    	
	    	ObjectMapper mapper = new ObjectMapper();
	    	Departamento obj = mapper.readValue(util.formataReq(req), Departamento.class);
	    	Connection conn = DbConnection.Conectar();
	    	DepartamentoDao.deleteRecord(conn, obj.getId());

	       return "ok";
	    }
	    
	    @POST
	    @Path("getById")
	    public String getById(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
	    	
	    	ObjectMapper mapper = new ObjectMapper();
	    	Departamento obj = mapper.readValue(util.formataReq(req), Departamento.class);
	    	Connection conn = DbConnection.Conectar();
	    	
	       return mapper.writeValueAsString(DepartamentoDao.findRecordById(conn, obj.getId()));
	    }
}

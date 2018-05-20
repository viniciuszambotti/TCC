package com.paths;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.dao.EmpresaDao;
import com.database.DbConnection;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.object.Empresa;

@Path("empresa")
public class EmpresaWs {
	
    @POST
    @Path("criar")
    public String criar(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	Empresa obj = mapper.readValue(util.formataReq(req), Empresa.class);
    	Connection conn = DbConnection.Conectar();
    	EmpresaDao.createRecord(conn, obj);

       return "ok";
    }
    
    @POST
    @Path("get")
    public String getIt() throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	Connection conn = DbConnection.Conectar();
    	

       return mapper.writeValueAsString(EmpresaDao.getAll(conn));
    }

    @POST
    @Path("editar")
    public String getIt(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	Empresa obj = mapper.readValue(util.formataReq(req), Empresa.class);
    	Connection conn = DbConnection.Conectar();
    	EmpresaDao.updateRecord(conn, obj);

       return "ok";
    }
    
    @POST
    @Path("deletar")
    public String deletar(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	Empresa obj = mapper.readValue(util.formataReq(req), Empresa.class);
    	Connection conn = DbConnection.Conectar();
    	EmpresaDao.deleteRecord(conn, obj.getId());

       return "ok";
    }

}

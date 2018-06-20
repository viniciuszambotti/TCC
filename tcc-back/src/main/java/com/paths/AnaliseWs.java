package com.paths;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.dao.AnaliseDao;
import com.database.DbConnection;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.object.Analise;

@Path("analise")
public class AnaliseWs {
		
	    @POST
	    @Path("criar")
	    public String criar(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
	    	
	    	ObjectMapper mapper = new ObjectMapper();
	    	Analise obj = mapper.readValue(util.formataReq(req), Analise.class);
	    	Connection conn = DbConnection.Conectar();
	    	AnaliseDao.createRecord(conn, obj);

	       return "ok";
	    }
	    
	    @POST
	    @Path("getEmpresa")
	    public String getIdEmpresa(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
	    	ObjectMapper mapper = new ObjectMapper();
	    	Connection conn = DbConnection.Conectar();
	    	Analise obj = mapper.readValue(util.formataReq(req), Analise.class);

	       return mapper.writeValueAsString(AnaliseDao.getAll(conn, obj.getFk_empresa()));
	    }
	    
	    @POST
	    @Path("getId")
	    public String getId(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
	    	ObjectMapper mapper = new ObjectMapper();
	    	Connection conn = DbConnection.Conectar();
	    	Analise obj = mapper.readValue(util.formataReq(req), Analise.class);
	    	

	       return mapper.writeValueAsString(AnaliseDao.findRecordById(conn, obj.getNomeAnalise()));
	    }

	    @POST
	    @Path("editar")
	    public String getIt(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
	    	
	    	ObjectMapper mapper = new ObjectMapper();
	    	Analise obj = mapper.readValue(util.formataReq(req), Analise.class);
	    	Connection conn = DbConnection.Conectar();
	    	AnaliseDao.updateRecord(conn, obj);

	       return "ok";
	    }
	    
	    @POST
	    @Path("deletar")
	    public String deletar(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
	    	
	    	ObjectMapper mapper = new ObjectMapper();
	    	Analise obj = mapper.readValue(util.formataReq(req), Analise.class);
	    	Connection conn = DbConnection.Conectar();
	    	AnaliseDao.deleteRecord(conn, obj.getId());

	       return "ok";
	    }
	}

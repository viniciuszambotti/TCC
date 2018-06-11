package com.paths;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.dao.UserDao;
import com.database.DbConnection;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.object.User;

@Path("usuario")
public class UserWs {
    @POST
    @Path("criar")
    public String criar(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	User obj = mapper.readValue(util.formataReq(req), User.class);
    	Connection conn = DbConnection.Conectar();
    	UserDao.createRecord(conn, obj);

       return "ok";
    }
    
    @POST
    @Path("get")
    public String getIt() throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	Connection conn = DbConnection.Conectar();
    	

       return mapper.writeValueAsString(UserDao.getAll(conn));
    }

    @POST
    @Path("editar")
    public String getIt(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	User obj = mapper.readValue(util.formataReq(req), User.class);
    	Connection conn = DbConnection.Conectar();
    	UserDao.updateRecord(conn, obj);

       return "ok";
    }
    
    @POST
    @Path("deletar")
    public String deletar(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	User obj = mapper.readValue(util.formataReq(req), User.class);
    	Connection conn = DbConnection.Conectar();
    	UserDao.deleteRecord(conn, obj.getId());

       return "ok";
    }
    
    @POST
    @Path("verificar")
    public String verificar(String req) throws ClassNotFoundException, SQLException, JsonParseException, JsonMappingException, IOException {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	User obj = mapper.readValue(util.formataReq(req), User.class);
    	Connection conn = DbConnection.Conectar();
    	 return mapper.writeValueAsString(UserDao.findUserPsw(conn, obj));

    }
}

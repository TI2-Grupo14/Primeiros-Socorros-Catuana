package service;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;

public class UsuarioService {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public UsuarioService() {}
	
	public boolean insert(Request request, Response response) {
		
		boolean status = false;
		
		String nome = request.queryParams("nome");
		String cpf = request.queryParams("cpf");
		String email = request.queryParams("email");
		String celular = request.queryParams("celular");
		String senha = request.queryParams("senha");
		boolean adm = Boolean.parseBoolean(request.queryParams("adm"));
		boolean medico = Boolean.parseBoolean(request.queryParams("medico"));
		
		Usuario usuario = new Usuario(nome, cpf, email, celular, senha, adm, medico);
		
		if(usuarioDAO.insert(usuario) == true) {
            response.status(201); // 201 Created
		} else {
			response.status(404); // 404 Not found
		}
		
		return status;
	}

	
	public JSONObject get(Request request, Response response) {
		
        String cpf = (request.params(":cpf"));
        
        Usuario usuario = usuarioDAO.get(cpf);
		
		if(usuario != null) response.status(200);
        else response.status(404);
		
		return usuario == null ? null : usuario.toJson();
	}
	
	/*
	public JSONObject getOffset(Request request, Response response) {
		
		int index = Integer.parseInt(request.params(":index"));		
		Usuario resp = usuarioDAO.getOffset(index);
		
		if(resp != null) response.status(200);
        else response.status(404);
		
		return resp == null ? null : resp.toJson();
	}
	*/
	/*
	public JSONObject getUser(Request request, Response response) {
		
		String username = request.params(":username");		
		Usuario resp = usuarioDAO.getUser(username);
		
		if(resp != null) response.status(200);
        else response.status(404);
		
		return resp == null ? null : resp.toJson();
	}
	*/
	
	public JSONArray getAll(Request request, Response response) {
		
		JSONArray maior = new JSONArray();
        
		for(Usuario i : usuarioDAO.getAll()) {
			
			maior.put(i.toJson());
		}
		return maior == null ? null : maior;
	}

	public boolean update(Request request, Response response) {
		
		boolean resp = false;
        String cpf = (request.params(":cpf"));
		
        Usuario usuario = usuarioDAO.get(cpf);

        if(usuario != null) {

        	usuario.setNome(request.queryParams("nome"));
        	usuario.setCpf(request.queryParams("cpf"));
        	usuario.setEmail(request.queryParams("email"));
        	usuario.setCelular(request.queryParams("celular"));
        	usuario.setSenha(request.queryParams("senha"));
        	usuario.setAdm(Boolean.parseBoolean(request.queryParams("adm")));
        	usuario.setMedico(Boolean.parseBoolean(request.queryParams("medico")));
      
        	usuarioDAO.update(usuario);
        	
        	response.status(200);
        	
        	resp = true;
        } 
        else response.status(404);
        return resp;
	}

	
	public boolean delete(Request request, Response response) {
		
		boolean resp = false;
        String cpf = (request.params(":cpf"));
        
        Usuario usuario = usuarioDAO.get(cpf);

        if(usuario != null) {
        	
            usuarioDAO.delete(cpf);
            
            resp = true;
           
            response.status(200);
        } 
        else {
        	
        	resp = false;
        	
            response.status(404);
        }
		return resp;
	}
}

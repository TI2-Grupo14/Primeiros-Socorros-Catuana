package service;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.AcidenteDAO;
import model.Acidente;
import spark.Request;
import spark.Response;

public class AcidenteService {

	private AcidenteDAO institutionDAO = new AcidenteDAO();

	public AcidenteService() {}
	
	public boolean insert(Request request, Response response) {
		
		boolean status = false;
		
		String email = request.queryParams("email");
		String nome = request.queryParams("nome");
		String telefone = request.queryParams("telefone");
		String senha = request.queryParams("senha");
		String cep = request.queryParams("cep");
		String cidade = request.queryParams("cidade");
		String rua = request.queryParams("rua");
		int numero = Integer.parseInt(request.queryParams("numero"));
		String complemento = request.queryParams("complemento");
		
		Usuario usuario = new Usuario(email, nome, telefone, senha, cep, cidade, rua, numero, complemento);
		
		if(usuarioDAO.insert(usuario) == true) {
            response.status(201); // 201 Created
		} else {
			response.status(404); // 404 Not found
		}
		
		return status;
	}

	
	public JSONObject get(Request request, Response response) {
		
		int id = Integer.parseInt(request.params(":id"));		
		Institution resp = institutionDAO.get(id);
		
		if(resp != null) response.status(200);
        else response.status(404);
		
		return resp == null ? null : resp.toJson();
	}
	
	public JSONObject getOffset(Request request, Response response) {
		
		int index = Integer.parseInt(request.params(":index"));		
		Institution resp = institutionDAO.getOffset(index);
		
		if(resp != null) response.status(200);
        else response.status(404);
		
		return resp == null ? null : resp.toJson();
	}
	
	public JSONObject getUser(Request request, Response response) {
		
		String username = request.params(":username");		
		Institution resp = institutionDAO.getUser(username);
		
		if(resp != null) response.status(200);
        else response.status(404);
		
		return resp == null ? null : resp.toJson();
	}
	
	public JSONArray getAll(Request request, Response response) {
		
		JSONArray maior = new JSONArray();
        
		for(Institution i : institutionDAO.getAll()) {
			
			maior.put(i.toJson());
		}
		return maior == null ? null : maior;
	}

	
	public boolean update(Request request, Response response) {
		
		boolean resp = false;
        int id = Integer.parseInt(request.params(":id"));
		
        Institution institution = institutionDAO.get(id);

        if(institution != null) {

        	institution.setUsername(request.queryParams("username"));
        	institution.setName(request.queryParams("name"));
        	institution.setPassword(request.queryParams("password"));
        	institution.setImageUrl(request.queryParams("image_url"));
        	institution.setCategory(Integer.parseInt(request.queryParams("category")));
        	institution.setAddress(request.queryParams("address"));
        	institution.setPhone(request.queryParams("phone"));
        	institution.setDescription(request.queryParams("description"));
      
        	institutionDAO.update(institution);
        	
        	response.status(200);
        	
        	resp = true;
        } 
        else response.status(404);
        return resp;
	}

	
	public boolean delete(Request request, Response response) {
		
		boolean resp = false;
        int id = Integer.parseInt(request.params(":id"));
        
        Institution institution = institutionDAO.get(id);

        if(institution != null) {
        	
            institutionDAO.delete(id);
            
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

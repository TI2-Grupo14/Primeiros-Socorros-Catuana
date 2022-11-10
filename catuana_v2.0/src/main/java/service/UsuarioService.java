package service;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;

public class UsuarioService {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	/**
	 * Construtor padrao da classe UsuarioService.
	 */
	public UsuarioService() {}
	
	/**
	 * Metodo para inserir usuario no banco de dados.
	 * @param request -
	 * @param response -
	 * @return true, se inserido corretamente; false, caso contrario.
	 */
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
            response.status(201);
		} else {
			response.status(404);
		}
		
		return status;
	}

	/**
	 * Metodo para obter um JSON com o usuario selecionado pelo cpf.
	 * @param request -
	 * @param response -
	 * @return - objeto JSON usuarios.
	 */
	public JSONObject get(Request request, Response response) {
		
        String cpf = (request.params(":cpf"));
        
        Usuario usuario = usuarioDAO.get(cpf);
		
		if(usuario != null) response.status(200);
        else response.status(404);
		
		return usuario == null ? null : usuario.toJson();
	}
	
	/**
	 * Metodo para obter um array JSON com todos os usuarios contidos no banco
	 * de dados.
	 * @param request
	 * @param response
	 * @return - objeto JSON todos os usuarios.
	 */
	public JSONArray getAll(Request request, Response response) {
		
		JSONArray allUsuarios = new JSONArray();
        
		for(Usuario i : usuarioDAO.getAll()) {
			
			allUsuarios.put(i.toJson());
		}
		return allUsuarios == null ? null : allUsuarios;
	}
    
	/**
	 * Metodo para atualizar um usuario no banco de dados.
	 * @param request -
	 * @param response -
	 * @return true, se atualizado corretamente; false, caso contrario.
	 */
	public boolean update(Request request, Response response) {
		
		boolean status = false;
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
        	
        	status = true;
        } 
        else response.status(404);
        return status;
	}

	/**
	 * Metodo para deletar um usuario no banco de dados.
	 * @param request -
	 * @param response -
	 * @return true, se deletado corretamente; false, caso contrario.
	 */
	public boolean delete(Request request, Response response) {
		
		boolean status = false;
        String cpf = (request.params(":cpf"));
        
        Usuario usuario = usuarioDAO.get(cpf);

        if(usuario != null) {
        	
            usuarioDAO.delete(cpf);
            
            status = true;
           
            response.status(200);
        } 
        else {
        	
        	status = false;
        	
            response.status(404);
        }
		return status;
	}
}

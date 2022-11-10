package service;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.PostagemDAO;
import model.Postagem;
import spark.Request;
import spark.Response;

public class PostagemService {

	private PostagemDAO postagemDAO = new PostagemDAO();

	/**
	 * Construtor padrao da classe PostagemService.
	 */
	public PostagemService() {}
	
	/**
	 * Metodo para inserir postagem no banco de dados.
	 * @param request -
	 * @param statusonse -
	 * @return true, se inserido corretamente; false, caso contrario.
	 */
	public boolean insert(Request request, Response statusonse) {
		
		boolean status = false;
		
		int id = Integer.parseInt(request.queryParams("id"));
		String medico = request.queryParams("medico");
		int acidente = Integer.parseInt(request.queryParams("acidente"));
		String site = request.queryParams("site");
		String videoaula = request.queryParams("videoaula");
		String descricao = request.queryParams("descricao");
		
		Postagem postagem = new Postagem(id, medico, acidente, site, videoaula, descricao);
		
		if(postagemDAO.insert(postagem) == true) {
            statusonse.status(201);
		} else {
			statusonse.status(404);
		}
		
		return status;
	}

	/**
	 * Metodo para obter um JSON com a postagem selecionada pelo id.
	 * @param request -
	 * @param statusonse -
	 * @return - objeto JSON postagems.
	 */
	public JSONObject get(Request request, Response statusonse) {
		
        int id = Integer.parseInt(request.params(":cpf"));
        
        Postagem postagem = postagemDAO.get(id);
		
		if(postagem != null) statusonse.status(200);
        else statusonse.status(404);
		
		return postagem == null ? null : postagem.toJson();
	}
	
	/**
	 * Metodo para obter um array JSON com todas as postagems contidos no banco
	 * de dados.
	 * @param request
	 * @param statusonse
	 * @return - objeto JSON todos as postagems.
	 */
	public JSONArray getAll(Request request, Response statusonse) {
		
		JSONArray allPostagens = new JSONArray();
        
		for(Postagem i : postagemDAO.getAll()) {
			
			allPostagens.put(i.toJson());
		}
		return allPostagens == null ? null : allPostagens;
	}
    
	/**
	 * Metodo para atualizar uma postagem no banco de dados.
	 * @param request -
	 * @param statusonse -
	 * @return true, se atualizada corretamente; false, caso contrario.
	 */
	public boolean update(Request request, Response statusonse) {
		
		boolean status = false;
        int id = Integer.parseInt(request.params(":id"));
		
        Postagem postagem = postagemDAO.get(id);

        if(postagem != null) {

        	postagem.setId(Integer.parseInt(request.queryParams("id")));
        	postagem.setMedico(request.queryParams("medico"));
        	postagem.setAcidente(Integer.parseInt(request.queryParams("acidente")));
        	postagem.setSite(request.queryParams("site"));
        	postagem.setVideoaula(request.queryParams("videoaula"));
        	postagem.setDescricao(request.queryParams("descricao"));
     
        	postagemDAO.update(postagem);
        	
        	statusonse.status(200);
        	
        	status = true;
        } 
        else statusonse.status(404);
        return status;
	}

	/**
	 * Metodo para deletar uma postagem no banco de dados.
	 * @param request -
	 * @param statusonse -
	 * @return true, se deletada corretamente; false, caso contrario.
	 */
	public boolean delete(Request request, Response statusonse) {
		
		boolean status = false;
        int id = Integer.parseInt(request.params(":id"));
        
        Postagem postagem = postagemDAO.get(id);

        if(postagem != null) {
        	
            postagemDAO.delete(id);
            
            status = true;
           
            statusonse.status(200);
        } 
        else {
        	
        	status = false;
        	
            statusonse.status(404);
        }
		return status;
	}
}

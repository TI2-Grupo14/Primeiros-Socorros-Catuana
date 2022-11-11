package service;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.NoticiaDAO;
import model.Noticia;
import spark.Request;
import spark.Response;

public class NoticiaService {

	private NoticiaDAO noticiaDAO = new NoticiaDAO();

	/**
	 * Construtor padrao da classe NoticiaService.
	 */
	public NoticiaService() {}
	
	/**
	 * Metodo para inserir noticia no banco de dados.
	 * @param request -
	 * @param response -
	 * @return true, se inserido corretamente; false, caso contrario.
	 */
	public boolean insert(Request request, Response response) {
		
		boolean status = false;
		
		String titulo = request.queryParams("titulo");
		String data = request.queryParams("data");
		String jornal = request.queryParams("jornal");
		String corpo = request.queryParams("corpo");
		String link = request.queryParams("link");
		String usuario = request.queryParams("usuario");
		boolean aprovacao = Boolean.parseBoolean(request.queryParams("usuario"));
		
		Noticia noticia = new Noticia(titulo, data, jornal, corpo, link, usuario, aprovacao);
		
		if(noticiaDAO.insert(noticia) == true) {
            response.status(201);
		} else {
			response.status(404);
		}
		
		return status;
	}

	/**
	 * Metodo para obter um JSON com a noticia selecionada pelo id.
	 * @param request -
	 * @param response -
	 * @return - objeto JSON noticias.
	 */
	public JSONObject get(Request request, Response response) {
		
        int id = Integer.parseInt(request.params(":id"));
        
        Noticia noticia = noticiaDAO.get(id);
		
		if(noticia != null) response.status(200);
        else response.status(404);
		
		return noticia == null ? null : noticia.toJson();
	}
	
	/**
	 * Metodo para obter um array JSON com todas as noticias contidos no banco
	 * de dados.
	 * @param request
	 * @param response
	 * @return - objeto JSON todos as noticias.
	 */
	public JSONArray getAll(Request request, Response response) {
		
		JSONArray allNoticias = new JSONArray();
        
		for(Noticia i : noticiaDAO.getAll()) {
			
			allNoticias.put(i.toJson());
		}
		return allNoticias == null ? null : allNoticias;
	}

	/**
	 * Metodo para obter um array JSON com todas as noticias contidos no banco
	 * de dados por titulo.
	 * @param request
	 * @param response
	 * @return - objeto JSON todos as noticias.
	 */
	public JSONArray getAllByTitulo(Request request, Response response) {
		
		JSONArray allNoticias = new JSONArray();
        
		for(Noticia i : noticiaDAO.getAllByTitulo()) {
			
			allNoticias.put(i.toJson());
		}
		return allNoticias == null ? null : allNoticias;
	}

	/**
	 * Metodo para atualizar uma noticia no banco de dados.
	 * @param request -
	 * @param response -
	 * @return true, se atualizada corretamente; false, caso contrario.
	 */
	public boolean update(Request request, Response response) {
		
		boolean status = false;
        int id = Integer.parseInt(request.params(":id"));
		
        Noticia noticia = noticiaDAO.get(id);

        if(noticia != null) {

        	noticia.setId(Integer.parseInt(request.queryParams("id")));
        	noticia.setTitulo(request.queryParams("titulo"));
        	noticia.setData(request.queryParams("data"));
        	noticia.setJornal(request.queryParams("jornal"));
        	noticia.setCorpo(request.queryParams("corpo"));
        	noticia.setLink(request.queryParams("link"));
        	noticia.setUsuario(request.queryParams("usuario"));
     
        	noticiaDAO.update(noticia);
        	
        	response.status(200);
        	
        	status = true;
        } 
        else response.status(404);
        return status;
	}

	/**
	 * Metodo para deletar uma noticia no banco de dados.
	 * @param request -
	 * @param response -
	 * @return true, se deletada corretamente; false, caso contrario.
	 */
	public boolean delete(Request request, Response response) {
		
		boolean status = false;
        int id = Integer.parseInt(request.params(":id"));
        
        Noticia noticia = noticiaDAO.get(id);

        if(noticia != null) {
        	
            noticiaDAO.delete(id);
            
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

package service;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.AcidenteDAO;
import model.Acidente;
import spark.Request;
import spark.Response;

public class AcidenteService {

	private AcidenteDAO acidenteDAO = new AcidenteDAO();

	/**
	 * Construtor padrao da classe AcidenteService.
	 */
	public AcidenteService() {}
	
	/**
	 * Metodo para inserir acidente no banco de dados.
	 * @param request -
	 * @param response -
	 * @return true, se inserido corretamente; false, caso contrario.
	 */
	public boolean insert(Request request, Response response) {
		
		boolean status = false;
		
		int codigo = Integer.parseInt(request.queryParams("codigo"));
		String nome = request.queryParams("nome");
		String descricao = request.queryParams("descricao");

		
		Acidente acidente = new Acidente(codigo, nome, descricao);
		
		if(acidenteDAO.insert(acidente) == true) {
            response.status(201);
		} else {
			response.status(404);
		}
		
		return status;
	}

	/**
	 * Metodo para obter um JSON com o acidente selecionado pelo codigo.
	 * @param request -
	 * @param response -
	 * @return - objeto JSON acidentes.
	 */
	public JSONObject get(Request request, Response response) {
		
        int codigo = Integer.parseInt(request.params(":codigo"));
        
        Acidente acidente = acidenteDAO.get(codigo);
		
		if(acidente != null) response.status(200);
        else response.status(404);
		
		return acidente == null ? null : acidente.toJson();
	}
	
	/**
	 * Metodo para obter um array JSON com todos os acidentes contidos no banco
	 * de dados.
	 * @param request
	 * @param response
	 * @return - objeto JSON todos os acidentes.
	 */
	public JSONArray getAll(Request request, Response response) {
		
		JSONArray allAcidentes = new JSONArray();
        
		for(Acidente i : acidenteDAO.getAll()) {
			
			allAcidentes.put(i.toJson());
		}
		return allAcidentes == null ? null : allAcidentes;
	}

	/**
	 * Metodo para obter um array JSON com todos os acidentes contidos no banco
	 * de dados por nome.
	 * @param request
	 * @param response
	 * @return - objeto JSON todos os acidentes.
	 */
	public JSONArray getAllByNome(Request request, Response response) {
		
		JSONArray allAcidentes = new JSONArray();
        
		for(Acidente i : acidenteDAO.getAllByNome()) {
			
			allAcidentes.put(i.toJson());
		}
		return allAcidentes == null ? null : allAcidentes;
	}

	/**
	 * Metodo para atualizar um acidente no banco de dados.
	 * @param request -
	 * @param response -
	 * @return true, se atualizado corretamente; false, caso contrario.
	 */
	public boolean update(Request request, Response response) {
		
		boolean status = false;
		int codigo = Integer.parseInt(request.params(":codigo"));
		
        Acidente acidente = acidenteDAO.get(codigo);

        if(acidente != null) {

        	acidente.setCodigo(Integer.parseInt(request.queryParams("codigo")));
        	acidente.setNome(request.queryParams("nome"));
        	acidente.setDescricao(request.queryParams("descricao"));
      
        	acidenteDAO.update(acidente);
        	
        	response.status(200);
        	
        	status = true;
        } 
        else response.status(404);
        return status;
	}

	/**
	 * Metodo para deletar um acidente no banco de dados.
	 * @param request -
	 * @param response -
	 * @return true, se deletado corretamente; false, caso contrario.
	 */
	public boolean delete(Request request, Response response) {
		
		boolean status = false;
		int codigo = Integer.parseInt(request.params(":codigo"));
        
        Acidente acidente = acidenteDAO.get(codigo);

        if(acidente != null) {
        	
            acidenteDAO.delete(codigo);
            
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

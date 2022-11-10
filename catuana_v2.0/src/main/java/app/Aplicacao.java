package app;

import static spark.Spark.*;
import service.UsuarioService;
//import service.CarouselService;
//import service.NewsService;

import model.Usuario;
import java.util.ArrayList;
import java.util.List;
import DAO.UsuarioDAO;
import org.json.JSONArray;
import org.json.JSONObject;

public class Aplicacao {
	
	private static UsuarioService usuarioService = new UsuarioService();
	//private static CarouselService carouselService = new CarouselService();
	//private static NewsService newsService = new NewsService();
	
    public static void main(String[] args) {
    	
		List<Usuario> usuarios = new ArrayList<Usuario>();
		UsuarioDAO userDAO = new UsuarioDAO();
		usuarios = userDAO.getAll();
		
		for(int i = 0; i < usuarios.size(); i++) {
			Usuario user = usuarios.get(i);
			System.out.println(user);
		}
		
		JSONArray maior = new JSONArray();
        
		for(Usuario i : userDAO.getAll()) {
			
			maior.put(i.toJson());
		}
		System.out.println(maior);
			
        
    	//port(6587);
    	port(5432);
    	
    	staticFiles.location("/public");
    
        get("/usuario", (request, response) -> usuarioService.getAll(request, response));
        post("/usuario/insert", (request, response) -> usuarioService.insert(request, response));
        get("/usuario/:cpf", (request, response) -> usuarioService.get(request, response));
        //get("/institutions/offset/:index", (request, response) -> usuarioService.getOffset(request, response));
        //get("/institutions/user/:username", (request, response) -> usuarioService.getUser(request, response));
        post("/usuario/update/:cpf", (request, response) -> usuarioService.update(request, response));
        get("/usuario/delete/:cpf", (request, response) -> usuarioService.delete(request, response));
        
        /*
        get("/carousel", (request, response) -> carouselService.getAll(request, response));
        get("/carousel/offset/:index", (request, response) -> carouselService.getOffset(request, response));
        post("/carousel/insert", (request, response) -> carouselService.insert(request, response));
        get("/carousel/delete/:id", (request, response) -> carouselService.delete(request, response));
        
        get("/news/:inst_id", (request, response) -> newsService.getAll(request, response));
        post("/news/insert", (request, response) -> newsService.insert(request, response));
        post("/news/update/:id", (request, response) -> newsService.update(request, response));
        get("/news/delete/:id", (request, response) -> newsService.delete(request, response));
        */
    }
}
package app;

import static spark.Spark.*;
import service.UsuarioService;
import service.PostagemService;

import model.Usuario;
import model.Postagem;
import java.util.ArrayList;
import java.util.List;
import DAO.UsuarioDAO;
import DAO.PostagemDAO;
import org.json.JSONArray;
import org.json.JSONObject;

public class Aplicacao {
	
	private static UsuarioService usuarioService = new UsuarioService();
	private static PostagemService postagemService = new PostagemService();
	//private static CarouselService carouselService = new CarouselService();
	//private static NewsService newsService = new NewsService();
	
    public static void main(String[] args) {
    	
    	    /* test UsuarioDAO */
    	    List<Usuario> usuarios = new ArrayList<Usuario>();
    	    
		    UsuarioDAO userDAO = new UsuarioDAO();
		    usuarios = userDAO.getAll();
		    
		    Usuario userInsert = new Usuario("Teste01", "11100000000", "teste01", "11100000000", "teste#01", false, false);
		    Usuario userInsert2 = new Usuario("Teste02", "2220000000", "teste02", "22200000000", "teste#02", true, false);
		    
		    System.out.println("\n" + userInsert);
		    System.out.println("\n" + userInsert2);
		    
		    userDAO.insert(userInsert);
			userDAO.delete("11100000000");
			userDAO.update(userInsert2);
			
			for(int i = 0; i < usuarios.size(); i++) {
			    Usuario user = usuarios.get(i);
			   System.out.println(user);
			}
			   
     	 	JSONArray allUsers = new JSONArray();
		    allUsers = usuarioService.getAll(null, null);
		    System.out.println(allUsers);
    	 
    	
    	    /* test PostagemDAO */
    		List<Postagem> postagens = new ArrayList<Postagem>();
    		
		    PostagemDAO postDAO = new PostagemDAO();
		    postagens = postDAO.getAll();
		    
		    Postagem postInsert = new Postagem("11590442016", 5, "https://teste", "https://www.teste", "teste");
		    Postagem postInsert2 = new Postagem(14, "11590442016", 6, "https://teste2", "https://www.teste2", "teste2");
		    
		    System.out.println("\n" + postInsert);
		    System.out.println("\n" + postInsert2);
		    		    
		    postDAO.insert(postInsert);
		    postDAO.delete(16);   // aumentar mais um
		    postDAO.update(postInsert2);
		

			for(int i = 0; i < postagens.size(); i++) {
			    Postagem post = postagens.get(i);
			    System.out.println(post);
			}
			   
     	 	JSONArray allPosts = new JSONArray();
		    allPosts = postagemService.getAll(null, null);
		    System.out.println(allPosts);		

      
    	//port(6587);
    	port(5432);
    	
    	staticFiles.location("/public");
    	
    	//get("/usuario", usuarioService::getAll);
    
        get("/usuario", (request, response) -> usuarioService.getAll(request, response));
        
        //post("/usuario/insert", (request, response) -> usuarioService.insert(request, response));
        //get("/usuario/:cpf", (request, response) -> usuarioService.get(request, response));
        //get("/institutions/offset/:index", (request, response) -> usuarioService.getOffset(request, response));
        //get("/institutions/user/:username", (request, response) -> usuarioService.getUser(request, response));
        //post("/usuario/update/:cpf", (request, response) -> usuarioService.update(request, response));
        //get("/usuario/delete/:cpf", (request, response) -> usuarioService.delete(request, response));
        
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
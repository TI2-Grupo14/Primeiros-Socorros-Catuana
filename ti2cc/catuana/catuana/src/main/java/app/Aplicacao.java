package app;

import static spark.Spark.*;
import java.util.Arrays;

import service.AcidenteService;
import service.NoticiaService;
import service.PostagemService;
import service.UsuarioService;


public class Aplicacao {
	
	private static AcidenteService acidenteService = new AcidenteService();
	private static NoticiaService noticiaService = new NoticiaService();
	private static PostagemService postagemervice = new PostagemService();
	private static UsuarioService usuarioService = new UsuarioService();
	
	public static void main(String[] args) {
		
        port(6789);
        staticFiles.location("/cutuana");
    	
        post("/usuario/insert", (request, response) -> usuarioService.insert(request, response));
        get("/usuario/:email", (request, response) -> usuarioService.get(request, response));
     // get("/usuario/list/:orderby", (request, response) -> usuarioService.getAll(request, response));
     // get("/usuario/update/:email", (request, response) -> usuarioService.getToUpdate(request, response));
     // post("/usuario/update/:email", (request, response) -> usuarioService.update(request, response));
     // get("/usuario/delete/:email", (request, response) -> usuarioService.delete(request, response));
             
    }
}
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
import spark.Spark;

import spark.Filter;

public class Aplicacao {
	
	private static UsuarioService usuarioService = new UsuarioService();
	private static PostagemService postagemService = new PostagemService();
	
    public static void main(String[] args) {
        port(3300);
        Spark.staticFiles.location("/public");

        before((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "*");
        });

    	get("/acidente", acidenteService::getAll);
    	get("/acidenteByNome", acidenteService::getAllByNome);
    	delete("/acidente/delete/:codigo", acidenteService::delete);
    	post("/acidente/insert", acidenteService::insert);
    	post("/acidente/update/:codigo", acidenteService::update);
        
    	get("/noticia", noticiaService::getAll);
    	get("/noticiaByTitulo", noticiaService::getAllByTitulo);
    	delete("/noticia/delete/:id", noticiaService::delete);
    	post("/noticia/insert", noticiaService::insert);
    	post("/noticia/update/:id", noticiaService::update);
        
    	get("/postagem", postagemService::getAll);
    	delete("/postagem/delete/:id", postagemService::delete);
    	post("/postagem/insert", postagemService::insert);
    	post("/postagem/update/:id", postagemService::update);
        
    	get("/usuario", usuarioService::getAll);
    	get("/usuarioByNome", usuarioService::getAllByNome);
    	get("/usuarioByCpf", usuarioService::getAllByCpf);
    	delete("/usuario/delete/:cpf", usuarioService::delete);
    	post("/usuario/insert", usuarioService::insert);
    	post("/usuario/update/:cpf", usuarioService::update);


    }
}
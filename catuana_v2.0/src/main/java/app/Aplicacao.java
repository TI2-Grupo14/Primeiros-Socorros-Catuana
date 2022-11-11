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
//	private static PostagemService postagemService = new PostagemService();
	
    public static void main(String[] args) {
        port(3300);
        Spark.staticFiles.location("/public");

        before((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "*");
        });

        // Routes que serão escutadas
        post("/usuario/update/:cpf", usuarioService::update);
    	get("/usuario",  usuarioService::getAll);
        get("/usuarioByNome",  usuarioService::getAllByNome);
        post("/usuario/insert",  usuarioService::insert);
        get("/usuario/:cpf", usuarioService::get);
        //get("/institutions/offset/:index", (request, response) -> usuarioService.getOffset(request, response));
        //get("/institutions/user/:username", (request, response) -> usuarioService.getUser(request, response));

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
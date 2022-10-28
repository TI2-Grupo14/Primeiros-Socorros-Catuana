package service;

import java.util.Scanner;
import java.util.List;
import spark.Request;
import spark.Response;

import model.Acidente;
import model.Noticia;
import model.Postagem;
import model.Usuario;

import dao.AcidenteDAO;
import dao.NoticiaDAO;
import dao.PostagemDAO;
import dao.UsuarioDAO;


public class UsuarioService {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	private String form;

	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;

	private final int FORM_ORDERBY_NOME    = 1;
	private final int FORM_ORDERBY_CPF     = 2;
	private final int FORM_ORDERBY_EMAIL   = 3;
	private final int FORM_ORDERBY_CELULAR = 4;
	private final int FORM_ORDERBY_SENHA   = 5;
	

	/**
	 * Construtor padrao, chamando construtor do makeForm.
	 */
	public UsuarioService() {
		makeForm();
	}

	/**
	 * Construtor padrao makeForm.
	 */
	public void makeForm() {
		makeForm(FORM_INSERT, new Usuario(), FORM_ORDERBY_NOME);
	}

	/**
	 * Construtor padrao makeForm.
	 * @param oderBy - numero codificado para ordenacao.
	 */
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Usuario(), orderBy);
	}

	/**
	 * Construtor padrao makeForm.
	 * @param tipo - numero codificado para tipo de movimentacao no banco de 
	 * dados.
	 * @param usuario - objeto do tipo Usuario que sofrera' a movimentacao.
	 * @param oderBy - numero codificado para ordenacao.
	 */
	public void makeForm(int tipo, Usuario usuario, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umUsuario = "";
		if(tipo != FORM_INSERT) {
			umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/usuario/list/1\">Novo usuario</a></b></font></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t</table>";
			umUsuario += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/usuario/";
			String name, email, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Usuario";
				email = "nome@email.com";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + usuario.getEmail();
				name = "Atualizar Usuario (Usuario " + usuario.getEmail() + ")";
				email = usuario.getEmail();
				buttonLabel = "Atualizar";
			}
			umUsuario += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;Nome:  <input class=\"input--register\" type=\"text\"     name=\"nome\"    value=\""+ usuario.getNome()    +"\"></td>";
			umUsuario += "\t\t\t<td>Cpf:         <input class=\"input--register\" type=\"text\"     name=\"cpf\"     value=\""+ usuario.getCpf()     +"\"></td>";
			umUsuario += "\t\t\t<td>Email:       <input class=\"input--register\" type=\"text\"     name=\"email\"   value=\""+ email                +"\"></td>";
			umUsuario += "\t\t\t<td>Celular:     <input class=\"input--register\" type=\"text\"     name=\"celular\" value=\""+ usuario.getCelular() +"\"></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;Senha: <input class=\"input--register\" type=\"password\" name=\"senha\"   value=\""+ usuario.getSenha()   +"\"></td>";
			umUsuario += "\t\t\t<td>Adm:         <input class=\"input--register\" type=\"radio\"    name=\"adm\"     value=\""+ usuario.getAdm()     +"\"></td>";
			umUsuario += "\t\t\t<td>Medico:      <input class=\"input--register\" type=\"radio\"    name=\"medico\"  value=\""+ usuario.getMedico()  +"\"></td>";
			umUsuario += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t</table>";
			umUsuario += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Usuario (e-mail " + usuario.getEmail() + ")</b></font></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;Nome: "+ usuario.getNome()    +"</td>";
			umUsuario += "\t\t\t<td>Cpf: "       + usuario.getCpf()     +"</td>";
			umUsuario += "\t\t\t<td>Email: "     + usuario.getEmail()   +"</td>";
			umUsuario += "\t\t\t<td>Celular: "   + usuario.getCelular() +"</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;Senha: "+ usuario.getSenha()  + "</td>";
			umUsuario += "\t\t\t<td>Adm: "        + usuario.getAdm()    + "</td>";
			umUsuario += "\t\t\t<td>Medico: "     + usuario.getMedico() + "</td>";
			umUsuario += "\t\t\t<td>&nbsp;</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo (" + tipo + ") não identificado! ");
		}
		form = form.replaceFirst("<UM-USUARIO>", umUsuario);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Usuarios</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_NOME    + "\"><b>Nome</b></a></td>\n" +
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_CPF     + "\"><b>Cpf</b></a></td>\n" +
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_EMAIL   + "\"><b>Email</b></a></td>\n" +
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_CELULAR + "\"><b>Celular</b></a></td>\n" +
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_SENHA   + "\"><b>Senha</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";

		List<Usuario> usuarios;
		if (orderBy == FORM_ORDERBY_NOME) {               usuarios = usuarioDAO.getByNome();
		} else if (orderBy == FORM_ORDERBY_CPF)     {	  usuarios = usuarioDAO.getByCpf();
		} else if (orderBy == FORM_ORDERBY_EMAIL)   {	  usuarios = usuarioDAO.getByEmail();
		} else if (orderBy == FORM_ORDERBY_CELULAR) {	  usuarios = usuarioDAO.getByCelular();
		} else if (orderBy == FORM_ORDERBY_SENHA)   {	  usuarios = usuarioDAO.getBySenha();
		} else {										  usuarios = usuarioDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Usuario p : usuarios) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getNome()    + "</td>\n" +
            		  "\t<td>" + p.getCpf()     + "</td>\n" +
            		  "\t<td>" + p.getEmail()   + "</td>\n" +
            		  "\t<td>" + p.getCelular() + "</td>\n" +
            		  "\t<td>" + p.getSenha()   + "</td>\n" +
            		  "\t<td>" + p.getAdm()     + "</td>\n" +
            		  "\t<td>" + p.getMedico()  + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/usuario/" + p.getEmail() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/usuario/update/" + p.getEmail() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteUsuario('" + p.getEmail() + "', '" + p.getNome() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-USUARIO>", list);				
	}
	
	/**
	 * Metodo para inserir, via requisicao, um usuario.
	 * @param request -
	 * @param response -
	 * @return form - Objeto inserido.
	 */
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		String cpf = request.queryParams("cpf");
		String email = request.queryParams("email");
		String celular = request.queryParams("celular");
		String senha = request.queryParams("senha");
		boolean adm = Boolean.parseBoolean(request.queryParams("adm"));
		boolean medico = Boolean.parseBoolean(request.queryParams("medico"));
		
		String resp = "";
		
		Usuario usuario = new Usuario(nome, cpf, email, celular, senha, adm, medico);
		
		if(usuarioDAO.insert(usuario) == true) {
            resp = "Usuario (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Usuario (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	/**
	 * Metodo para listar, via requisicao, um usuario.
	 * @param request -
	 * @param response -
	 * @return form - lista dos objetos.
	 */
	public Object get(Request request, Response response) {
		String email = request.params(":email");		
		Usuario usuario = (Usuario) usuarioDAO.getByEmail(email);
		
		if (usuario != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, usuario, FORM_ORDERBY_EMAIL);
        } else {
            response.status(404); // 404 Not found
            String resp = "Usuario " + email + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	/**
	 * Metodo para obter para atualizacao, via requisicao, um usuario.
	 * @param request -
	 * @param response -
	 * @return form - objeto atualizado.
	 */	
	public Object getToUpdate(Request request, Response response) {
		String email = request.params(":email");		
		Usuario usuario = (Usuario) usuarioDAO.getByPK(email);
		
		if (usuario != null) {
			response.status(200); // success            		  
			makeForm(FORM_UPDATE, usuario, FORM_ORDERBY_EMAIL);
        } else {
            response.status(404); // 404 Not found
            String resp = "Usuario " + email + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" email=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" email=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	/**
	 * Metodo para listar todos os usuarios via requisicao.
	 * @param request -
	 * @param response -
	 * @return form - lista dos objetos.
	 */	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			

	/**
	 * Metodo para atualizar, via requisicao, um usuario.
	 * @param request -
	 * @param response -
	 * @return form - mensagem de confirmacao.
	 */
	public Object update(Request request, Response response) {
        String email = request.params(":email");
        
		Usuario usuario = usuarioDAO.getByEmail(email);
        String resp = "";       

        if (usuario != null) {
        	usuario.setNome(request.queryParams("nome"));
        	usuario.setCpf(request.queryParams("cpf"));
        	usuario.setEmail(request.queryParams("email"));
        	usuario.setCelular(request.queryParams("celular"));
        	usuario.setSenha(request.queryParams("senha"));
        	usuario.setAdm(Boolean.parseBoolean(request.queryParams("adm")));
        	usuario.setMedico(Boolean.parseBoolean(request.queryParams("medico")));

        	usuarioDAO.update(usuario);
			
        	response.status(200); // success
            resp = "Usuario (Email " + usuario.getEmail() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuario (Email " + usuario.getEmail() + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	/**
	 * Metodo para deletar, via requisicao, um usuario.
	 * @param request -
	 * @param response -
	 * @return form - mensagem de confirmacao.
	 */
	public Object delete(Request request, Response response) {
        String email = request.params(":email") //.substring(1);
        Usuario usuario = usuarioDAO.getByPK(email);
        String resp = "";       

        if (usuario != null) {
            usuarioDAO.delete(email);
            response.status(200); // success
            resp = "Usuario (" + email + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuario (" + email + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}
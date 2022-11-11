// Adiciona botoes para cadastrar apenas para medicos

let botaoCadastroVideo = "";
let botaoCadastroNoticia = "";

if (usuarioCorrente.tipo == 2) {
  botaoCadastroVideo += ` <div class="botao_cadastro" > <a href="informacoes-cadastro.html" id="cadastro_video" type="button" class="btn btn-primary my-2"" >Cadastre novas publicações</a> </div>`;

  botaoCadastroNoticia += ` <div class="botao_cadastro" > <a href="noticia-cadastro.html" id="cadastro_noticia" type="button" class="btn btn-primary my-2"" >Cadastre novas noticias</a> </div>`;

  document.getElementsByClassName('titulo-cadastro').style.display = "";

}

document.getElementById('botao_cadastro_video').innerHTML = botaoCadastroVideo;
document.getElementById('botao_cadastro_noticia').innerHTML = botaoCadastroNoticia;
// Adiciona botoes para cadastrar apenas para medicos

user = JSON.parse(sessionStorage.getItem('usuarioCorrente'));



nomeUsuario = document.getElementById('name');
emailUsuario = document.getElementById('email');
tipoUsuario = document.getElementById('tipo');
celUsuario = document.getElementById('cel');

adm = document.getElementById('adm');

nomeUsuario.innerHTML = user.nome;
emailUsuario.innerHTML = user.email
celUsuario.innerHTML = user.celular

if(user.tipo == 1){

  tipoUsuario.innerHTML = 'Comum';

}else if(user.tipo == 2){

  tipoUsuario.innerHTML = 'Médico';

}

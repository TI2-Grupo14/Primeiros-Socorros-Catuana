input = document.querySelectorAll('.input-hiden');
span = document.querySelectorAll('.span-info');
btn = document.getElementById('altera-info');

btn.addEventListener('click', alteraInfo())

function alteraInfo(){
  for(let i = 0; i < 4; i++){
    input[i].style.display = 'inline';
    span[i].style.display = 'none';
  }

  btn.removeEventListener('click', alteraInfo);
  btn.innerHTML = 'Enviar';
  btn.addEventListener('click', enviar);
}

function enviar() {
  
  for(let i = 0; i < 4; i++){
    console.log(input[i].value)
    input[i].style.display = 'none';
    span[i].style.display = 'inline';
  }

  btn.removeEventListener('click', enviar);
  btn.innerHTML = 'Alterar Informações';
  btn.addEventListener('click', alteraInfo);

}
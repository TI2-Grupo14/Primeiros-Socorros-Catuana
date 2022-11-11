token = sessionStorage.getItem('token');
usuario = sessionStorage.getItem('usuarioCorrente');

navHeader = document.querySelector('#nav-header');


// Apaga os dados do usuário corrente no sessionStorage
function logoutUser() {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('usuarioCorrente');
}

// Adiciona botão logout apenas se usuarios estiver logado

if (token != null) {

    // conteúdo do botão
    a = document.createElement('a');
    a.innerHTML = "Logout";
    a.classList.add('flex-sm-fill');
    a.classList.add('text-sm-center');
    a.classList.add('nav-link');

    a.addEventListener('click', logoutUser);
    a.href = 'login.html';

    navHeader.appendChild(a);

} else{

    a = document.createElement('a');
    a.innerHTML = "Login";
    a.href = 'login.html';
    a.classList.add('flex-sm-fill');
    a.classList.add('text-sm-center');
    a.classList.add('nav-link');

    navHeader.appendChild(a);

}


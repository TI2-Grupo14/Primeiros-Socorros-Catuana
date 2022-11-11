const loginButton = document.getElementById('login-button');

var db_cadastros_iniciais = {
    data: [
        {
            id: 1,
            nome: "Ana da Silva",
            email: "anadasilva@email.com",
            tipo: 1,
            cpf: 90433404051,
            celular: 31987654321,
            senha: "Ana1234"
        },
        {
            id: 2,
            nome: "Joana Fontes",
            email: "joanafontes@email.com",
            tipo: 2,
            cpf: 84872372077,
            celular: 11959929989,
            senha: "Joana1234"
        },
        {
            id: 3,
            nome: "Carla Almeida",
            email: "carlaalmeida@email.com",
            tipo: 3,
            cpf: 20871132036,
            celular: 21987532545,
            senha: "Carla1234"
        }
    ]
}

// Caso os dados já estejam no Local Storage; caso contrário, carrega os dados iniciais
var cadastro = JSON.parse(localStorage.getItem('db_cadastros_iniciais'));
if (!cadastro) {
    cadastro = db_cadastros_iniciais
};


// declara um conjunto inicial de cadastros
// tipo 1 = usuário
// tipo 2 = médico

var usuario_Corrente = {
    data: [
        {
            id: undefined,
            nome: undefined,
            email: undefined,
            tipo: undefined,
            cpf: undefined,
            celular: undefined,
            senha: undefined
        }
    ]
};

if (loginButton) {
    loginButton.addEventListener('click', enviar);
}

// usuario corrente
var usuarioCorrente = {};
usuarioCorrenteJSON = sessionStorage.getItem('usuarioCorrente');
if (usuarioCorrenteJSON != undefined ) {
    usuarioCorrente = JSON.parse(usuarioCorrenteJSON);
}

// verificar login
function enviar() {
    
    const usernameLogin = document.getElementById('username-input').value;
    const passwordLogin = document.getElementById('password-input').value;

    for (let i = 0; i < cadastro.data.length; i++) {
        
        let usuarioOk = cadastro.data[i];
        if (usuarioOk.email == usernameLogin) {
            if (usuarioOk.senha == passwordLogin) {
                usuarioCorrente.id = usuarioOk.id;
                usuarioCorrente.nome = usuarioOk.nome;
                usuarioCorrente.email = usuarioOk.email;
                usuarioCorrente.tipo = usuarioOk.tipo;
                usuarioCorrente.cpf = usuarioOk.cpf;
                usuarioCorrente.celular = usuarioOk.celular;
                usuarioCorrente.senha = usuarioOk.senha;

                // Salva os dados do usuário corrente no Session Storage, mas antes converte para string
                sessionStorage.setItem('usuarioCorrente', JSON.stringify(usuarioCorrente));


                sessionStorage.setItem('token', getRanHex(25));

                window.location.assign('index.html');
                return true;

            } else {
                alert('Senha incorreta! Tente novamente.');
                return false;
            }
        }
    }
    
    alert('Cadastro não foi encontrado! Crie uma conta.');
    return false;

}

// gera um token de 'n' caracteres
const getRanHex = size => {

    let result = [];
    let hexRef = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'];

    for (let n = 0; n < size; n++) {
        result.push(hexRef[Math.floor(Math.random() * 16)]);
    }
    
    return result.join('');
}


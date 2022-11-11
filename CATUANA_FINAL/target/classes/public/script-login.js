// declara um conjunto inicial de cadastros
// tipo 1 = usuário
// tipo 2 = médico
var db_cadastros_iniciais = {
    data: [
        {
            id: 1,
            nome: "Ana da Silva",
            email: "anadasilva@email.com",
            tipo: 2,
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
            tipo: 2,
            cpf: 20871132036,
            celular: 21987532545,
            senha: "Carla1234"
        },
        {
            id: 4,
            nome: "Tulio Alves",
            email: "tulioalves@email.com",
            tipo: 2,
            cpf: 11590442016,
            celular: 11961892922,
            senha: "Tulio1234"
        },
        {
            id: 5,
            nome: "Joaquim Pereira",
            email: "joaquimpereira@email.com",
            tipo: 2,
            cpf: 33975352043,
            celular: 31985822999,
            senha: "Joaquim1234"
        },
        {
            id: 6,
            nome: "Giovanne Castro",
            email: "giovannecastro@email.com",
            tipo: 2,
            cpf: 50315422092,
            celular: 31961892922,
            senha: "Giovanne1234"
        },
        {
            id: 7,
            nome: "Pedro Marques",
            email: "pedromarques@email.com",
            tipo: 2,
            cpf: 90811018032,
            celular: 11985268777,
            senha: "Pedro1234"
        },
        {
            id: 8,
            nome: "Sandra Rodrigues",
            email: "sandrarodrigues@email.com",
            tipo: 2,
            cpf: 61690976047,
            celular: 21933665466,
            senha: "Sandra1234"
        },
        {
            id: 9,
            nome: "Gustavo Maia",
            email: "gustavomaia@email.com",
            tipo: 1,
            cpf: 94940169049,
            celular: 31986322348,
            senha: "Gustavo1234"
        },
        {
            id: 10,
            nome: "Ivan Pereira",
            email: "ivanpereira@email.com",
            tipo: 1,
            cpf: 61690976047,
            celular: 21933665466,
            senha: "Ivan1234"
        },
        {
            id: 11,
            nome: "Rodrigo Santana",
            email: "rodrigosantana@email.com",
            tipo: 1,
            cpf: 69937751071,
            celular: 11932549622,
            senha: "Rodrigo1234"
        },
        {
            id: 12,
            nome: "Amanda Lima",
            email: "amandalima@email.com",
            tipo: 1,
            cpf: 69937751071,
            celular: 11932549622,
            senha: "Amanda1234"
        }
    ]
}

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

// Caso os dados já estejam no Local Storage; caso contrário, carrega os dados iniciais
var cadastro = JSON.parse(localStorage.getItem('db_cadastros_iniciais'));
if (!cadastro) {
    cadastro = db_cadastros_iniciais
};


const form = document.getElementById('form');
const nome = document.getElementById('nome');
const email = document.getElementById('email');
//const tipo = document.querySelector('input[name="Cadastro"]:checked');
const cpf = document.getElementById('cpf');
const celular = document.getElementById('celular');
const senha = document.getElementById('senha');
const senhaConfirmacao = document.getElementById('senha-confirmacao');
const loginButton = document.getElementById('login-button');

if (loginButton) {
    loginButton.addEventListener('click', enviar);
}

if (form) {
    form.addEventListener('submit', (e) => {
        e.preventDefault();

        checkInputs();
    });
}

function checkInputs() {
    const nomeCadastrado = nome.value;
    const emailCadastrado = email.value;
    const tipoCadastrado = document.querySelector('input[name="Cadastro"]:checked').value;
    const cpfCadastrado = cpf.value;
    const celularCadastrado = celular.value;
    const senhaCadastrada = senha.value;
    const senhaConfirmacaoCadastrada = senhaConfirmacao.value;

    if (nomeCadastrado === '') {
        setErrorFor(nome, 'O nome é obrigatório.');
    } else {
        setSuccessFor(nome);
    }

    if (emailCadastrado === '') {
        setErrorFor(email, 'O email é obrigatório.');
    } else if (!checkEmail(emailCadastrado)) {
        setErrorFor(email, 'Por favor, insira um email válido.');
    } else {
        setSuccessFor(email);
    }

    if (cpfCadastrado == '') {
        setErrorFor(cpf, 'O CPF é obrigatório.');
    } else if (cpfCadastrado.length !== 11) {
        setErrorFor(cpf, 'Entre com o CPF de 11 dígitos.');
    } else {
        setSuccessFor(cpf);
    }

    if (celularCadastrado == '') {
        setErrorFor(celular, 'O número de celular é obrigatório.');
    } else if (celularCadastrado.length !== 11) {
        setErrorFor(celular, 'Entre os dois dígitos do DDD sucedidos do número de celular');
    } else {
        setSuccessFor(celular);
    }

    if (senhaCadastrada === '') {
        setErrorFor(senha, 'A senha é obrigatória.');
    } else if (senhaCadastrada.length < 7) {
        setErrorFor(senha, 'A senha precisa ter, no mínimo 7, caracteres.');
    } else {
        setSuccessFor(senha);
    }

    if (senhaConfirmacaoCadastrada === '') {
        setErrorFor(senhaConfirmacao, 'A confirmação de senha é obrigatória.');
    } else if (senhaCadastrada !== senhaConfirmacaoCadastrada) {
        setErrorFor(senhaConfirmacao, 'As senhas não conferem.');
    } else {
        setSuccessFor(senhaConfirmacao);
    }

    const formControls = form.querySelectorAll('.form-control');

    const formIsValid = [...formControls].every((formControl) => {
        return formControl.className === 'form-control success';
    });
    if (formIsValid) {
        // Calcula novo Id
        let novoId = 1;
        if (cadastro.data.length != 0)
            novoId = cadastro.data[cadastro.data.length - 1].id + 1;

        // obtem as informacoes 
        let novoCadastro = {
            id: novoId,
            nome: nomeCadastrado,
            email: emailCadastrado,
            tipo: tipoCadastrado,
            cpf: cpfCadastrado,
            celular: celularCadastrado,
            senha: senhaCadastrada,
        };

        // Insere o novo objeto no array
        cadastro.data.push(novoCadastro);
        alert("Cadastro criado com sucesso!");

        // Atualiza os dados no Local Storage
        localStorage.setItem('db_cadastros_iniciais', JSON.stringify(cadastro));

        // Abre pagina de login
        window.location.assign('login.html');

    };


}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');

    // Adiciona a mensagem de erro
    small.innerText = message;

    // Adiciona a classe de erro
    formControl.className = 'form-control error';
}

function setSuccessFor(input) {
    const formControl = input.parentElement;

    // Adicionar a classe de sucesso
    formControl.className = 'form-control success';
}

function checkEmail(email) {
    return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(
        email
    );
}

// usuario corrente
var usuarioCorrente = {};
usuarioCorrenteJSON = sessionStorage.getItem('usuarioCorrente');
if (usuarioCorrenteJSON != undefined ) {
    usuarioCorrente = JSON.parse (usuarioCorrenteJSON);
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
                sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));

                window.location.assign('conteudo.html');
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
async function retornaTodosUsuarios() {
    return await fetch(`http://localhost:3300/usuario`, {
        method: 'GET'
    }).then(e => e.json()).then(e => e);
}
async function insertUser(user) {
    return await fetch(`http://localhost:3300/usuario/insert?nome=${user.nome}&cpf=${user.cpf}&email=${user.email}&celular=${user.celular}&senha=${user.senha}&medico=${user.medico}`, {
        method: 'POST'
    })
}

/*cadastro de usuario*/
const form = document.getElementById('form');

const nome = document.getElementById('nome');
const email = document.getElementById('email');
const tipo = document.querySelector('input[name="Cadastro"]:checked');
const cpf = document.getElementById('cpf');
const celular = document.getElementById('celular');
const senha = document.getElementById('senha');
const senhaConfirmacao = document.getElementById('senha-confirmacao');

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
        let novoCadastro = {
            id: novoId,
            nome: nomeCadastrado,
            email: emailCadastrado,
            cpf: cpfCadastrado,
            celular: celularCadastrado,
            senha: senhaCadastrada,
            adm: false,
            medico: tipoCadastrado == '2'
        };
        retornaTodosUsuarios().then(e => {
            novoId = e.length + 1;
            insertUser(novoCadastro).then(e => {
                if (e.status == 404) {
                    alert("Usuario não cadastrado, houve algum erro com a database")
                }
                if (e.status == 201) {
                    alert("Cadastro criado com sucesso!");
                    // window.location.assign('listaUsuario.html');
                }
                if(e.status == 500){
                    alert("Usuario com cpf existente na base de dados");
                }
            })
        })



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
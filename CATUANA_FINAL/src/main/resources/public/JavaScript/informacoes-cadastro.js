window.onload(e => {

    let inputID, inputSite, inputVideoaula,
        inputDescricao, inputMedico, inputAcidente;
    inputSite = $("inputSite").value;
    inputVideoaula = $("inputVideoaula").value;
    inputDescricao = $("inputDescricao").value;
    inputMedico = $("inputMedico").value;
    inputAcidente = $("inputAcidente").value;

    let postagem = {
        medico: inputMedico,
        acidente: inputAcidente,
        site: inputSite,
        videoaula: inputVideoaula,
        descricao: inputDescricao
    }

    $("#btnInsert").onClick(e => {
        $("#table-informacoes").append(`<tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>`)
    })
})
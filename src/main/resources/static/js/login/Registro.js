document.addEventListener("DOMContentLoaded", () => {
    //Selectores
    const formRegistro = document.querySelector("#formRegistro");

    //Eventos
    formRegistro.addEventListener("submit", peticionNuevoRegistro);

    //Funciones
    function peticionNuevoRegistro(e) {
        e.preventDefault();
        const token = document.querySelector("#_csrf").value;
        const inputNombre = document.querySelector("#nombre").value;
        const inputCorreo =  document.querySelector("#email").value;
        const inputTelefono = document.querySelector("#telefono").value;
        const inputContraseña = document.querySelector("#password").value;

        const bodyRequest = {
            nombre: inputNombre,
            correo: inputCorreo,
            telefono: inputTelefono,
            password: inputContraseña
        }
        fetch("/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                // 6700
                "X-CSRF-TOKEN": token
            },
            body: JSON.stringify(bodyRequest)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            console.log(data)
        }).catch((error) => {
            console.log("Error en peticion al backend")
            console.log(error.message);
        })
    }
})
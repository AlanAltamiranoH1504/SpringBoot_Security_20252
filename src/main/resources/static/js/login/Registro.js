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
            const {status} = data;
            if (status === 201){
                document.querySelector("#exito").classList.remove("d-none");
                setTimeout(() => {
                    document.querySelector("#exito").classList.add("d-none");
                }, 3500)
            } else{
                const erroresArray = Object.values(data);
                mostrarErrores(erroresArray);
            }
        }).catch((error) => {
            console.log("Error en peticion al backend")
            console.log(error.message);
        });
    }

    function mostrarErrores(errores){
        const divErrores = document.querySelector("#errores");
        divErrores.innerHTML = "";
        errores.forEach((error) => {
            const pError = document.createElement("p");
            pError.textContent = error;
            pError.classList.add("px-3", "py-2", "text-center", "fw-semibold", "mb-3", "text-white", "bg-danger", "rounded");
            divErrores.appendChild(pError);
        });
        setTimeout(() => {
            divErrores.innerHTML = "";
        }, 4000)
    }
})
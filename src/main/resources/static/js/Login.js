document.addEventListener("DOMContentLoaded", () => {
    const formLogin = document.querySelector("#formLogin");

    formLogin.addEventListener("submit", registroUsuario);

    function registroUsuario(e) {
        e.preventDefault();
        const token = document.querySelector("#_csrf").value;
        const inputEmail = document.querySelector("#email").value;
        const inputPassword = document.querySelector("#password").value;

        const bodyRequest = {
            email: inputEmail,
            password: inputPassword
        }
        const endPoint = "/auth/login";
        fetch(endPoint, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "X-CSRF-TOKEN": token
            },
            body: JSON.stringify(bodyRequest)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            const {status} = data;
            if (status === 200) {
                window.location.href = "/";
            }else {
                const {message} = data;
                const divAlertas = document.querySelector("#errores");
                divAlertas.textContent = message;
                divAlertas.classList.remove("d-none");
                setTimeout(() => {
                    divAlertas.classList.add("d-none");
                }, 3500);
            }
        }).catch((error) => {
            console.log("ERROR EN PETICION A: " + endPoint);
            console.log(error.message)
        })
    }
});
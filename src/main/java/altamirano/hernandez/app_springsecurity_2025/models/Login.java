package altamirano.hernandez.app_springsecurity_2025.models;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class Login {
    @NotBlank(message = "El email no puede estar vacio")
    private String email;
    @NotBlank(message = "El password no puede estar vacio")
    private String password;

//    public Login(){
//
//    }
    public Login(String email, String password){
        this.email = email;
        this.password = password;
    }

    //Metodos GET y SET
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    //Metodo Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return Objects.equals(email, login.email) && Objects.equals(password, login.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}

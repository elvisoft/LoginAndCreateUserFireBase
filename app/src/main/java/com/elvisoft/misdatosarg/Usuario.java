package com.elvisoft.misdatosarg;

public class Usuario {
    private String uid;
    private String Nombre;
    private String Apellido;
    private String Correo;
    private String Password;
    private String DNI;
    private String respuestasecret;

    public Usuario() {
    }

    public Usuario(String uid, String DNI, String Password,String Nombre, String Apellido,String Correo, String respuestasecret) {
        this.uid=uid;
        this.DNI = DNI;
        this.Password = Password;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Apellido=Apellido;
        this.respuestasecret=respuestasecret;
    }

    public String getRespuestasecret() {
        return respuestasecret;
    }

    public void setRespuestasecret(String respuestasecret) {
        this.respuestasecret = respuestasecret;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

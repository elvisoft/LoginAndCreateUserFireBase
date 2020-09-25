package com.elvisoft.misdatosarg;

public class CuentasBank {
    public int id;
    public int image;
    public String NameCuenta;
    public String NameBanco;

    public CuentasBank(int id, int image, String nameCuenta, String nameBanco) {
        this.id = id;
        this.image = image;
        NameCuenta = nameCuenta;
        NameBanco = nameBanco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameCuenta() {
        return NameCuenta;
    }

    public void setNameCuenta(String nameCuenta) {
        NameCuenta = nameCuenta;
    }

    public String getNameBanco() {
        return NameBanco;
    }

    public void setNameBanco(String nameBanco) {
        NameBanco = nameBanco;
    }
}

package com.mycompany.main;

public class Empleado extends Persona {
    //Atributos
    private String fechaNacimiento;
    private String direccion;
    private long numeroTelefono;
    
    public Empleado(){}
    
    //OBJETO
    
    DatosLaborales datosLaborales = new DatosLaborales();

    //Metodos get y set
    
    public String getFechaNacimiento(){
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }
    ////

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    ////

    public long getNumeroTelefono(){
        return numeroTelefono;
    }

    public void setNumeroTelefono(long numeroTelefono){
        this.numeroTelefono=numeroTelefono;
    }

}

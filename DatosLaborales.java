package com.mycompany.main;
import java.util.Scanner;
import java.util.Calendar;


public class DatosLaborales {
    
    //atributos
    private double listaAsistencia;
    private double listahorasTrabajadas;
    private double sueldo;
    private String fechaContratacion;
    private String puestoTrabajao;
    private double salarioBase;
    private double horasTrabajadas;

     Scanner entrada= new Scanner (System.in);
    //Constructor
    public  DatosLaborales(){}


    //METODOS get y set

    public double getListaAsistencia() {
        return listaAsistencia;
    }


    public void setListaAsistencia(double listaAsistencia) {
        this.listaAsistencia = listaAsistencia;
    }
    ////

    public double getListahorasTrabajadas() {
        return listahorasTrabajadas;
    }


    public void setListahorasTrabajadas(double listahorasTrabajadas) {
        this.listahorasTrabajadas = listahorasTrabajadas;
    }
    ////

    public double getSueldo() {
        return sueldo;
    }


    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    ////

    public String getFechaContratacion() {
        return fechaContratacion;
    }


    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
    ////

    public String getPuestoTrabajao() {
        return puestoTrabajao;
    }


    public void setPuestoTrabajao(String puestoTrabajao) {
        this.puestoTrabajao = puestoTrabajao;
    }
    ////

    public double getSalarioBase() {
        return salarioBase;
    }


    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
    

    Calendar Hora = Calendar.getInstance();
    Calendar Fecha = Calendar.getInstance();
    int hora, minutos, segundos, dia, mes,año;
    String FechaActual, HoraActual; 
    
    

    //METODOS FUNCIONALES 

    public void conocerSalario(){

   
    }
    

    public void registrarEntrada(){

        hora = Hora.get(Calendar.HOUR_OF_DAY);
        minutos = Hora.get (Calendar.MINUTE);
        segundos = Horaget (Calendar.SECOND);
        HoraActual = hora +":" + minutos + ":"+ segundos;

        dia = Fecha.get(Calendar.DATE);
        mes = Fecha.get(Calendar.MONTH);
        año = Fecha.get(Calendar.YEAR);
        FechaActual = dia +"/" + mes + "/"+ año;


        System.out.println("Verifique que la fecha y hora de entrada son las correctas: y CONFIRME");
        System.out.println("Hora Actual es: "+ HoraActual);
        System.out.println("La fecha actual es: "+ FechaActual);
        System.out.println("/nConfirmar.");

    } 

    public void registrarSalida(){
        hora = Hora.get(Calendar.HOUR_OF_DAY);
        minutos = Hora.get (Calendar.MINUTE);
        segundos = Horaget (Calendar.SECOND);
        HoraActual = hora +":" + minutos + ":"+ segundos;

        dia = Fecha.get(Calendar.DATE);
        mes = Fecha.get(Calendar.MONTH);
        año = Fecha.get(Calendar.YEAR);
        FechaActual = dia +"/" + mes + "/"+ año;


        System.out.println("Verifique que la fecha y hora de salida son las correctas: y CONFIRME");
        System.out.println("Hora Actual es: "+ HoraActual);
        System.out.println("La fecha actual es: "+ FechaActual);
        System.out.println("/nConfirmar.");

    }

    public void calcularSalario(){
        sueldo = (salarioBase *horasTrabajadas)/ 8;
        
    }

}

package com.mycompany.main;
import java.util.Scanner;
import java.util.ArrayList;

public class GestionEmpleado {
    Scanner entrada= new Scanner (System.in);
    
    //Atributos 

   
    ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();

    Empleado empleado = new Empleado();

    public ArrayList<Empleado> getListaEmpleados(){
        return listaEmpleados;
    }

    public void setListaEmpleados(Empleado empleado){
        this.listaEmpleados= listaEmpleados;
    }
   
    

    //Métodos funcionales

    public void registarDatosEmpleado(){
                
        System.out.println("\nRegistro de datos personales: \n");

        System.out.println("Ingrese el nombre/s del empleado:");
        empleado.setNombre(entrada.nextLine());

        System.out.println("Ingrese el apellido paterno:");
        empleado.setApellidoPaterno(entrada.nextLine());

        System.out.println("Ingrese el apellido materno:");
        empleado.setApellidoMaterno(entrada.nextLine());

        System.out.println("Ingrese la fecha de nacimiento:");
        empleado.setFechaNacimiento(entrada.nextLine());

        System.out.println("Ingrese la direccion domiciliaria:");
        empleado.setDireccion(entrada.nextLine());

        System.out.println("Ingrese el numero de telefono:");
        empleado.setNumeroTelefono(entrada.nextLong());

        System.out.println("\nRegistro de datos laborales : \n");

        System.out.println("Ingrese la fecha de contratación:");
        empleado.datosLaborales.setFechaContratacion(entrada.nextLine());

        System.out.println("Ingrese la fecha de contratación:");
        empleado.datosLaborales.setPuestoTrabajao(entrada.nextLine());

        System.out.println("Ingrese la fecha de contratación:");
        empleado.datosLaborales.setSalarioBase(entrada.nextDouble());


        listaEmpleados.add(empleado);

        System.out.println("Empleado registrado");
        

    }

    public void actualizarDatosEmpleado(){

        mostrarListaEmpleados();

    
        System.out.println("Ingrese el indice del empleado que desea actualizar:");
        int indiceEmpleado = entrada.nextInt();

        
        if (indiceEmpleado >= 0 && indiceEmpleado < listaEmpleados.size()) {
           
            Empleado empleadoSeleccionado = listaEmpleados.get(indiceEmpleado);

            
            System.out.println("\nIngrese los nuevos datos personales para el empleado:");
            System.out.println("Ingrese el nombre/s del empleado:");
            empleadoSeleccionado.setNombre(entrada.nextLine());

            System.out.println("Ingrese el apellido paterno:");
            empleadoSeleccionado.setApellidoPaterno(entrada.nextLine());

            System.out.println("Ingrese el apellido materno:");
            empleadoSeleccionado.setApellidoMaterno(entrada.nextLine());

            System.out.println("Ingrese la fecha de nacimiento:");
            empleadoSeleccionado.setFechaNacimiento(entrada.nextLine());

            System.out.println("Ingrese la dirección domiciliaria:");
            empleadoSeleccionado.setDireccion(entrada.nextLine());

            System.out.println("Ingrese el número de teléfono:");
            empleadoSeleccionado.setNumeroTelefono(entrada.nextLong());

           
            System.out.println("\nIngrese los nuevos datos laborales para el empleado:");
            System.out.println("Ingrese la fecha de contratación:");
            empleadoSeleccionado.datosLaborales.setFechaContratacion(entrada.nextLine());

            System.out.println("Ingrese el puesto de trabajo:");
            empleadoSeleccionado.datosLaborales.setPuestoTrabajao(entrada.nextLine());

            System.out.println("Ingrese el salario base:");
            empleadoSeleccionado.datosLaborales.setSalarioBase(entrada.nextDouble());

            System.out.println("Datos del empleado actualizados correctamente.");
        } else {
            System.out.println("Indice de empleado no valido.");
        }
    }

    public void eliminarEmpleado(){

        mostrarListaEmpleados();

        System.out.println("Ingrese el indice del empleado que desea eliminar:");
        int indiceEmpleado = entrada.nextInt();

        if (indiceEmpleado >= 0 && indiceEmpleado < listaEmpleados.size()) {
            Empleado empleadoEliminado = listaEmpleados.remove(indiceEmpleado);
            System.out.println("Empleado eliminado correctamente: " + empleadoEliminado.getNombreCompleto());
        } else {
            System.out.println("Indice de empleado no valido.");
        }
    }

    

    public void verHistorialTrabajoEMpleado(){
        mostrarListaEmpleados();

        System.out.println("Ingrese el indice del empleado que desea ver su historial de trabajo:");
        int indiceEmpleado = entrada.nextInt();

        if (indiceEmpleado >= 0 && indiceEmpleado < listaEmpleados.size()) {
            
        } else {
            System.out.println("Indice de empleado no valido.");
        }

    }

    public  void mostrarListaEmpleados() {
        
        System.out.println("Lista de empleados:");
        for (int i = 0; i < listaEmpleados.size(); i++) {
            System.out.println(i + ": " + listaEmpleados.get(i).getNombreCompleto());
        }
    }

}

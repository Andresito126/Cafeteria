import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionEmpleado {
    private ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();

    public ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void registrarDatosEmpleado(Login login) 
        throws InputMismatchException
        {
        int edad = 0;
        long numeroTelefono = 0;
        int salarioBase = 0;
        boolean continuar = true;
        Empleado empleado = new Empleado();
        CredencialAcceso sesion;
        DatoLaboral datosLaborales = new DatoLaboral();
        Scanner input = new Scanner(System.in);

        System.out.println("\n<---------------------------- Registro de empleado ------------------------------>");
        System.out.println(" \n\n<- - - - - - - Datos personales - - - - - - ->");

        System.out.print("\nNombre: ");
        String nombre = input.nextLine();

        System.out.print("Apelidos: ");
        String apellido = input.nextLine();

        do{
            try{
                do {
                    System.out.print("Edad: ");
                    edad = input.nextInt();
                    if(edad<=0){
                        System.out.println("La edad no puede ser un número negativo o cero, por favor introduzca un número válido");
                    }
                } while (edad < 18);
        
                System.out.print("Número de teléfono: ");
                numeroTelefono = input.nextLong();

                continuar = false;
            }
            catch(InputMismatchException ime){
                System.out.println("Introduzca números enteros");
                input.nextLine();
            }
        }while(continuar);
        
        input.nextLine();

        System.out.print("Dirección: ");
        String direccion = input.nextLine();

        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setEdad(edad);
        empleado.setDireccion(direccion);
        empleado.setNumeroTelefono(numeroTelefono);

        System.out.println("\n <-  -  -  -  - Datos laborales -  -  -  -  ->");

        //System.out.print("\nFecha de contratación: ");
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MMMM/yyyy");
        String fechaContratacion = formato.format(fecha);

        System.out.print("Puesto de trabajo: ");
        String puestoTrabajo = input.nextLine();

        continuar = true;
        do{
            try{
                do{
                    System.out.print("Salario base: ");
                    salarioBase = input.nextInt();

                    if(salarioBase<=0){
                        System.out.println("El salario no puede ser un número negativo o cero, por favor introduzca un número válido");
                    }
                    else{

                        if(salarioBase < 1000 || salarioBase > 1400){
                            System.out.println("El salario debe comprender entre $1000 a $1400");
                        }
                    }
                }while(salarioBase <= 0 || salarioBase < 1000 || salarioBase > 1400);
               
                continuar = false;
    
            }catch(InputMismatchException iException){
                System.out.println("\nDigite números enteros");
                input.nextLine();
            }
        }while(continuar);
    
        datosLaborales.setFechaContratacion(fechaContratacion);
        datosLaborales.setPuestoTrabajo(puestoTrabajo);
        datosLaborales.setSalarioBase(salarioBase);

        System.out.println(" \n<-  -  -  - - Credenciales de acceso -  -  -  -  -> ");

        input.nextLine();
        
        System.out.print("\nUsuario: ");
        String user = input.nextLine();

        System.out.print("Contraseña: ");
        String password = input.nextLine();

        sesion = new CredencialAcceso(password, user);

        empleado.setDatosLaborales(datosLaborales);
        empleado.setSesion(sesion);

        this.listaEmpleados.add(empleado);
        login.setListaUsuarios(empleado);
    }

    public void actualizarDatosEmpleado(Login login) {
        Scanner explorador = new Scanner(System.in);
        String nombreBusqueda;
        int datoModificado;
        boolean bandera = true;
        boolean seguirCiclo = true;
        int edad;
        System.out.println("<------------------------ Modificar datos empleado --------------------------------->");

        do{
            try{
                do{
                    System.out.print("\nIngresa el nombre del empleado que deseas modificar: ");
                    nombreBusqueda = explorador.nextLine();
        
                    for(int i=0; i<listaEmpleados.size(); i++){
                        if(listaEmpleados.get(i).getNombre().equalsIgnoreCase(nombreBusqueda)){
                            do{
                                System.out.println("\n¿Qué dato desea modificar de " + listaEmpleados.get(i).getNombre() + "?" + 
                                 "\n\n<--------------- Datos personales ---------------->\n1.Edad \n2.Direccion \n3.Número de telefono" +  
                                 "\n\n<----------------- Datos laborales ------------------>\n4.Salario Base" +
                                 "\n\n<------------ Credenciales de acceso ------------->\n5.Usuario \n6.Contraseña \n7.Salir");
                                 datoModificado = explorador.nextInt();
        
                                switch (datoModificado) {
                                    case 1:
                                        do{
                                            System.out.print("\nIngresa el nuevo valor para edad de " + listaEmpleados.get(i).getNombre() + " : ");
                                            edad = explorador.nextInt();
                                            if(edad<=18){
                                                System.out.println("\nIngresa una edad valida mayor a 18");
                                            }
                                        }while(edad<=18);
                                        System.out.println("La edad es " + listaEmpleados.get(i).getEdad());
                                        listaEmpleados.get(i).setEdad(edad);
                                        explorador.nextLine(); 
                                        System.out.println("La edad modificada " + listaEmpleados.get(i).getEdad());
                                    break;
                                    case 2:
                                        System.out.print("\nIngresa la nueva dirección de " + listaEmpleados.get(i).getNombre() + " : ");
                                        String direccion = explorador.nextLine();
                                        listaEmpleados.get(i).setDireccion(direccion);
                                        explorador.nextLine();
                                        break;
                                    case 3:
                                        System.out.print("\nIngresa el nuevo número de teléfono de " + listaEmpleados.get(i).getNombre() + " : ");
                                        long numeroTelefono = explorador.nextLong();
                                        listaEmpleados.get(i).setNumeroTelefono(numeroTelefono);
                                        explorador.nextLine();
                                        break;
                                    case 4:
                                        System.out.print("\nIngresa el nuevo salario base de " + listaEmpleados.get(i).getNombre() + " : ");
                                        int salarioBase = explorador.nextInt();
                                        listaEmpleados.get(i).getDatosLaborales().setSalarioBase(salarioBase);
                                        explorador.nextLine();
                                        break;
                                    case 5:
                                        explorador.nextLine();
                                        System.out.print("\nIngresa el nuevo usuario de " + listaEmpleados.get(i).getNombre() + " : ");
                                        String user = explorador.nextLine();
                                        listaEmpleados.get(i).getSesion().setUser(user);
                                        login.getListaUsuarios().get(i+1).getSesion().setUser(user);
                                        break;
                                    case 6:
                                        explorador.nextLine();
                                        System.out.print("\nIngrese el nuevo password de " + listaEmpleados.get(i).getNombre() + " : ");
                                        String password = explorador.nextLine();
                                        listaEmpleados.get(i).getSesion().setPassword(password);
                                        login.getListaUsuarios().get(i+1).getSesion().setPassword(password);
                                        break;
                                    case 7:
                                        System.out.println();
                                        break;
                                    default:
                                        System.out.println("\nIngresa un número comprendido entre las distintas opciones");
                                        break;
                                }
                            }while(datoModificado!=7);
                            bandera=false;
                        }
                    }
    
                    if(bandera==true){
                        System.out.println("\nLa persona " + nombreBusqueda + " no se encuentra dentro del sistema. Intenta nuevamente.");
                    }
                }while(bandera);

                seguirCiclo = false;
    
            }catch(InputMismatchException e){
                System.out.println("Ingresa solo números");
                explorador.nextLine();
            }
        }while(seguirCiclo);
    }

    public void eliminarDatosEmpleado(Login login) {
        Scanner teclado = new Scanner(System.in);
        String empleadoAEliminar;
        System.out.println("<---------------------- Eliminar empleado ------------------------>");
        System.out.print("Ingresa el nombre del empleado que deseas modificar: ");
        empleadoAEliminar = teclado.nextLine();

        for(int i=0; i<listaEmpleados.size(); i++){
            System.out.println(listaEmpleados.get(i).getNombre() + ".La fecha de contratacion fue el: " + listaEmpleados.get(i).getDatosLaborales().getFechaContratacion());
            System.out.println();
        }

        for(int i=0; i<listaEmpleados.size();i++){
            if(listaEmpleados.get(i).getNombre().equalsIgnoreCase(empleadoAEliminar)){
                listaEmpleados.remove(i);
                login.getListaUsuarios().remove(i+1);
            }
        }


        for(int i=0; i<listaEmpleados.size(); i++){
            System.out.println(listaEmpleados.get(i).getNombre() + "");
        }

    }

    public void verHistorialTrabajoEmpleado(){

}
}
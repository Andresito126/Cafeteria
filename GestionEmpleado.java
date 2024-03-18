import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionEmpleado {
    private ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();

    public ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public Empleado registrarDatosEmpleado(Empleado empleado) {
        int edad = 0;
        long numeroTelefono = 0;
        boolean continuar = true;
        CredencialAcceso sesion;
        DatoLaboral datosLaborales = new DatoLaboral();
        Scanner input = new Scanner(System.in);

        System.out.println("<---------------------------- Registro de empleado ------------------------------>");
        System.out.println(" <- - - - - - - Datos personales - - - - - - - >");

        System.out.print("Nombre: ");
        String nombre = input.nextLine();

        System.out.print("Apelidos: ");
        String apellido = input.nextLine();

        do{
            try{
                do {
                    System.out.print("Edad: ");
                    edad = input.nextInt();
                    if(edad<=0){
                        System.out.println("La edad no puede ser negativa, por favor introduzca un número válido");
                    }
                    else{
                        System.out.println("Lo sentimos pero dicha persona no puede laborar dentro de la cafetería");
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

        System.out.println(" <-  -  -  -  - Datos laborales -  -  -  -  ->");

        System.out.print("Fecha de contratación: ");
        String fechaContratacion = input.nextLine();

        System.out.print("Puesto de trabajo: ");
        String puestoTrabajo = input.nextLine();

        System.out.print("Salario base: ");
        int salarioBase = input.nextInt();

        datosLaborales.setFechaContratacion(fechaContratacion);
        datosLaborales.setPuestoTrabajo(puestoTrabajo);
        datosLaborales.setSalarioBase(salarioBase);

        System.out.println(" <=  =  =  =  = Credenciales de acceso =  =  =  =  => ");

        input.nextLine();
        
        System.out.print("User: ");
        String user = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        sesion = new CredencialAcceso(password, user);

        empleado.setDatosLaborales(datosLaborales);
        empleado.setSesion(sesion);

        this.listaEmpleados.add(empleado);

        return empleado;
    }

    public void actualizarDatosEmpleado() {
        System.out.println("Bienvenido");
    }

    public void eliminarDatosEmpleado() {
        System.out.println("Que desea realizar?");
    }

    public void verHistorialTrabajoEmpleado() {

    }
}

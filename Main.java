import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatoLaboral obj = new DatoLaboral();
        obj.registrarEntrada();
        obj.registrarSalida();
        obj.calcularSalario();
        obj.conocerSalario();
                          
        GestionEmpleado objGestion = new GestionEmpleado();
        Login login = new Login();
        Administrador administrador = new Administrador("David");
        CredencialAcceso acceso = new CredencialAcceso("Mindft", "David2502");
        administrador.setSesion(acceso);
        login.setListaUsuarios(administrador);
        boolean cicloPrograma = true;

        

        System.out.println("Hello World!");
        do{
            inicioSesion(login,objGestion);
        }while(cicloPrograma); 

    }

    public static void inicioSesion(Login login,GestionEmpleado objGestion) {
        Scanner entrada = new Scanner(System.in);
        Persona persona;
        System.out.println("<------------------- CAFETERIA DEL TIO ------------------->");

        System.out.print("Usuario: ");
        String user = entrada.nextLine();
        
        System.out.print("Password: ");
        String password = entrada.nextLine();
 

        do {
            persona = login.autenticarAcceso(password, user);

            if (persona == null) {
                System.out.println("Datos erróneos, no se encuentran catalogados dentro del sistema, inteta nuevamente");

                System.out.print("Usuario: ");
                user = entrada.nextLine();
                
                System.out.print("Password: ");
                password = entrada.nextLine();
            }

        } while (persona == null);

        if (persona.getNombre().equals("David")) {
            realizarSesionAdmin(login,objGestion);
        } else {
            realizarSesionEmpleado();
        }
    }

    public static void realizarSesionAdmin(Login login, GestionEmpleado objGestion) 
        throws InputMismatchException 
        {
        Scanner teclado = new Scanner(System.in);
        boolean continuarCiclo = true;
        int op;
        System.out.println("Bienvenido David");       

        do{
            try{
                do{
                    System.out.println("\nElije una opción a realizar");
                    System.out.println("1.Registrar empleado \n2.Actualizar datos de un empleado \n3.Eliminar un empleado \n4.Ver historial laboral de un empleado \n5.Salir");
                    op = teclado.nextInt();
                    switch (op) {
                        case 1:
                            Empleado empleado = new Empleado();
                            empleado = objGestion.registrarDatosEmpleado(empleado);
                            login.setListaUsuarios(empleado);
                            break;
                        case 2:
                        DatoLaboral objDatos = new DatoLaboral();
                        objDatos.registrarEntrada();
                            break;
                        case 3:
                            objGestion.eliminarDatosEmpleado();
                            break;
                        case 4:
                            objGestion.verHistorialTrabajoEmpleado();
                            break;
                        case 5:
                            System.out.println("\nSalida exitosa");
                            break;
                        default:
                            System.out.println("\nIngrese valor contemplado entre las distintas opciones anteriormente");
                            break;
                    }
                }while(op!=5);

                continuarCiclo = false;
            }
            catch(InputMismatchException inputMismatchException ){
                System.out.println("\nExcepcion: " + inputMismatchException);
                teclado.nextLine();
                System.out.println("Debe introducir enteros. Intente de nuevo.\n" );
            }
        }while(continuarCiclo);      
        
    }

    public static void realizarSesionEmpleado() {
        Scanner teclado = new Scanner (System.in);
        int seleccion;
        System.out.println("Bienvenido ");
        System.out.println("Que deseas realizar 1.Conocer salario 2.Registrar entrada");
        seleccion = teclado.nextInt();
    }


}

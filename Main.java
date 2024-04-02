import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        GestionEmpleado objGestion = new GestionEmpleado();
        Login login = new Login();
        Administrador administrador = new Administrador("David");
        CredencialAcceso acceso = new CredencialAcceso("D", "D");
        administrador.setSesion(acceso);
        login.setListaUsuarios(administrador);
        boolean cicloPrograma = true;

        do {
            inicioSesion(login, objGestion);
        } while (cicloPrograma);
    }

    public static void inicioSesion(Login login, GestionEmpleado objGestion) {
        Scanner entrada = new Scanner(System.in);
        Persona persona = new Persona();
        System.out.println("\n<------------------- CAFETERIA DEL TIO ------------------->");

        do {
            System.out.print("\n\nUsuario: ");
            String user = entrada.nextLine();

            System.out.print("Password: ");
            String password = entrada.nextLine();
            persona = login.autenticarAcceso(password, user);

            if (persona == null) {
                System.out.println("Datos erróneos, no se encuentran catalogados dentro del sistema, intenta nuevamente");
            }

        } while (persona == null);

        if (persona.getNombre().equals("David")) {
            realizarSesionAdmin(login, objGestion);
        } else {
            realizarSesionEmpleado(login, objGestion, persona);
        }
    }

    public static void realizarSesionAdmin(Login login, GestionEmpleado objGestion)
        throws InputMismatchException{
        Scanner teclado = new Scanner(System.in);
        int op = 0;
        System.out.println("\n¡Bienvenido David!");

        do {
            try {
                System.out.println("\n1.Registrar empleado \n2.Actualizar datos de un empleado \n3.Eliminar un empleado \n4.Ver historial laboral de un empleado \n5.Salir");
                System.out.print("\nElije una opción a realizar: ");
                op = teclado.nextInt();
            }
            catch (InputMismatchException inputMismatchException) {
                System.out.println("\nExcepcion: " + inputMismatchException);
                teclado.nextLine();
                System.out.println("Debe introducir enteros. Intente de nuevo.\n");
                continue;
            }

            switch (op) {
                case 1:
                    objGestion.registrarDatosEmpleado(login);
                    break;
                case 2:
                    objGestion.actualizarDatosEmpleado(login);
                    break;
                case 3:
                    objGestion.eliminarDatosEmpleado(login);
                    break;
                case 4:
                    objGestion.verHistorialTrabajoEmpleado();
                    break;
                case 5:
                    System.out.println();
                    break;
            }
        } while (op!=5);
    }

    public static void realizarSesionEmpleado(Login login, GestionEmpleado objGestion, Persona persona) {
        Scanner teclado = new Scanner(System.in);
        int seleccion = 0;
        boolean bandera = false;
        System.out.println("\n¡Bienvenido " + persona.getNombre() + " !");

        do{
            try{
                System.out.println("\n1.Registrar hora entrada \n2.Registrar hora salida \n3.Consultar salario \n4.Salir");
                System.out.print("\nDigite un número de acuerdo a lo que quiera realizar: ");
                seleccion = teclado.nextInt();
            }
            catch(InputMismatchException inMismatchException){
                System.out.println("\nExcepcion: " + inMismatchException);
                teclado.nextLine();
                System.out.println("Digite solo enteros. Intente nuevamente");
                continue;
            }

            for(int i=0; i<objGestion.getListaEmpleados().size(); i++){
                if(objGestion.getListaEmpleados().get(i).getNombre().equals(persona.getNombre())){
                    switch (seleccion) {
                        case 1:
                            objGestion.getListaEmpleados().get(i).getDatosLaborales().registrarEntrada();
                            break;
                        case 2:
                                objGestion.getListaEmpleados().get(i).getDatosLaborales().registrarSalida();
                            break;
                        case 3:
                            objGestion.getListaEmpleados().get(i).getDatosLaborales().calcularSalario();
                            objGestion.getListaEmpleados().get(i).getDatosLaborales().conocerSalario();
                            break;
                        case 4:
                            System.out.println("\nSalida exitosa");
                            break;
                    }
                }
            }
        }while(seleccion!=4);
}

}
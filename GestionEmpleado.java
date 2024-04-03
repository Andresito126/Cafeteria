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
            throws InputMismatchException {
        int edad = 0;
        long numeroTelefono = 0;
        int salarioBase = 0;
        boolean continuar = true;
        Empleado empleado = new Empleado();
        CredencialAcceso sesion;
        DatoLaboral datosLaborales = new DatoLaboral();
        Scanner input = new Scanner(System.in);
        String numeroTelefonoStr = "";

        System.out.println("\n<---------------------------- Registro de empleado ------------------------------>");
        System.out.println(" \n\n<- - - - - - - Datos personales - - - - - - ->");

        String nombre = "";
        String apellido = "";

        while (true) {
            System.out.print("\nNombre: ");
            nombre = input.nextLine();

            if (nombre.matches("[a-zA-ZñÑ]+")) {
                break;
            }

            System.out.println("Nombre/s inválido/s. Por favor, intenta de nuevo.");
        }
        while (true) {
            System.out.print("Apellidos: ");
            apellido = input.nextLine();

            if (apellido.matches("[a-zA-ZñÑ]+")) {
                break;
            }
            System.out.println("Apellido inválidos. Por favor, intenta de nuevo.");
        }

        do {
            try {
                System.out.print("Edad: ");
                edad = input.nextInt();
                if (edad < 18 || edad > 50) {
                    System.out.println(
                            "La edad no puede ser un número menor a 18 o mayor a 50, por favor introduzca un número válido");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Introduzca números enteros");
                input.nextLine();
            }
        } while (edad < 18 || edad > 50);

        do {
            try {
                System.out.print("Número de teléfono: ");
                numeroTelefono = input.nextLong();
                numeroTelefonoStr = numeroTelefonoStr.valueOf(numeroTelefono);
                if (numeroTelefonoStr.length() == 10) {
                    System.out.println("Número de telefono establecido correctamente");
                    continuar = false;
                } else {
                    System.out.println("El número de teléfono en general debe comprender 10 digitos");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Introduzca números enteros");
                input.nextLine();
            }
        } while (continuar);

        input.nextLine();

        System.out.print("Dirección: ");
        String direccion = input.nextLine();

        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setEdad(edad);
        empleado.setDireccion(direccion);
        empleado.setNumeroTelefono(numeroTelefono);

        System.out.println("\n <-  -  -  -  - Datos laborales -  -  -  -  ->");

        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MMMM/yyyy");
        String fechaContratacion = formato.format(fecha);

        System.out.print("\nPuesto de trabajo: ");
        String puestoTrabajo = input.nextLine();

        do {
            try {
                System.out.print("Salario base: ");
                salarioBase = input.nextInt();

                if (salarioBase <= 0) {
                    System.out.println(
                            "\nEl salario no puede ser un número negativo o cero, por favor introduzca un número válido");
                } else {
                    if (salarioBase < 1100 || salarioBase > 1500) {
                        System.out.println("\nEl salario debe comprender entre $1100 a $1500");
                    }
                }
            } catch (InputMismatchException iException) {
                System.out.println("\nDigite números enteros");
                input.nextLine();
            }
        } while (salarioBase <= 0 || salarioBase < 1100 || salarioBase > 1500);

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

    public void actualizarDatosEmpleado(Login login)
            throws InputMismatchException {
        Scanner explorador = new Scanner(System.in);
        String nombreBusqueda;
        int datoModificado = 0;
        boolean bandera = true;
        int edad = 0;
        int salarioBase = 0;
        boolean entradaValida = true;
        long numeroTelefono = 0;
        String numeroTelefonoStr = "";

        System.out.println(
                "\n<---------------------------------------------- Modificar datos empleado --------------------------------------------------------->");

        System.out.println(
                "\n\nNombre\t\tEdad\t\tNúmero de teléfono\t\tDirección\t\t\tSalario base\t\tUsuario\t\tContraseña");
        for (int i = 0; i < listaEmpleados.size(); i++) {
            System.out.println(listaEmpleados.get(i).getNombre() + "\t " + listaEmpleados.get(i).getEdad() + "\t\t    "
                    + listaEmpleados.get(i).getNumeroTelefono()
                    + "\t\t" + listaEmpleados.get(i).getDireccion() + "\t\t    "
                    + listaEmpleados.get(i).getDatosLaborales().getSalarioBase()
                    + "\t\t\t" + listaEmpleados.get(i).getSesion().getUser() + "\t\t    "
                    + listaEmpleados.get(i).getSesion().getPassword());
        }

        do {
            System.out.print("\nIngresa el nombre del empleado que deseas modificar: ");
            nombreBusqueda = explorador.nextLine();

            for (int i = 0; i < listaEmpleados.size(); i++) {
                if (listaEmpleados.get(i).getNombre().equalsIgnoreCase(nombreBusqueda)) {
                    do {
                        try {
                            System.out.println("\n¿Qué dato desea modificar de " + listaEmpleados.get(i).getNombre()
                                    + "?" +
                                    "\n1.Nombre \n2.Apellido \n3.Edad \n4.Direccion \n5.Número de telefono \n6.Salario Base \n7.Usuario \n8.Contraseña \n9.Salir");
                            datoModificado = explorador.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("\nExcepcion: " + e);
                            explorador.nextLine();
                            System.out.println("Digite solo enteros. Intente nuevamente");
                            continue;
                        }

                        switch (datoModificado) {
                            case 1:
                                boolean nombreValido = false;
                                do {
                                    System.out.print("\nIngrese el nombre: ");
                                    String nombre = explorador.nextLine();
                                    listaEmpleados.get(i).setNombre(nombre);
                                    if (nombre.matches("[a-zA-ZñÑ]+")) {
                                        nombreValido = true;
                                    } else {
                                        System.out.println("Nombre/s inválido/s. Por favor, intenta de nuevo.");
                                    }

                                } while (!nombreValido);
                                break;

                            case 2:
                                boolean apellidoValido = false;
                                do {
                                    System.out.print("\nIngrese el apellido: ");
                                    String apellido = explorador.nextLine();
                                    listaEmpleados.get(i).setApellido(apellido);
                                    if (apellido.matches("[a-zA-ZñÑ]+")) {
                                        apellidoValido = true;
                                    } else {
                                        System.out.println("Apellido/s inválido/s. Por favor, intenta de nuevo.");
                                    }

                                } while (!apellidoValido);
                                break;

                            case 3:
                                do {
                                    try {
                                        System.out.print("\nIngresa el nuevo valor para edad de "
                                                + listaEmpleados.get(i).getNombre() + " : ");
                                        edad = explorador.nextInt();
                                        if (edad < 18 || edad > 50) {
                                            System.out.println("\nIngresa una edad valida mayor a 18 pero menor a 50");
                                        }
                                    } catch (InputMismatchException smallException) {
                                        System.out.println("Ingresa solo números");
                                        explorador.nextLine();
                                    }
                                } while (edad < 18 || edad > 50);
                                listaEmpleados.get(i).setEdad(edad);
                                explorador.nextLine();
                                break;

                            case 4:
                                System.out.print(
                                        "\nIngresa la nueva dirección de " + listaEmpleados.get(i).getNombre() + " : ");
                                String direccion = explorador.nextLine();
                                listaEmpleados.get(i).setDireccion(direccion);
                                explorador.nextLine();
                                break;

                            case 5:
                                do {
                                    try {
                                        System.out.print("\nIngrese el nuevo número de teléfono de "
                                                + listaEmpleados.get(i).getNombre() + " : ");
                                        numeroTelefono = explorador.nextLong();
                                        numeroTelefonoStr = numeroTelefonoStr.valueOf(numeroTelefono);
                                        if (numeroTelefonoStr.length() == 10) {
                                            listaEmpleados.get(i).setNumeroTelefono(numeroTelefono);
                                            entradaValida = false;
                                        } else {
                                            System.out.println(
                                                    "El número de teléfono en general debe comprender 10 digitos");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Introduzca números enteros");
                                        explorador.nextLine();
                                    }
                                } while (entradaValida);
                                break;

                            case 6:
                                do {
                                    try {
                                        System.out.print("\nIngresa el nuevo salario base de "
                                                + listaEmpleados.get(i).getNombre() + " : ");
                                        salarioBase = explorador.nextInt();

                                        if (salarioBase <= 0) {
                                            System.out.println(
                                                    "\nEl salario no puede ser un número negativo o cero, por favor introduzca un número válido");
                                        } else {
                                            if (salarioBase < 1100 || salarioBase > 1500) {
                                                System.out.println("\nEl salario debe comprender entre $1100 a $1500");
                                            }
                                        }
                                    } catch (InputMismatchException iException) {
                                        System.out.println("\nDigite números enteros");
                                        explorador.nextLine();
                                    }
                                } while (salarioBase <= 0 || salarioBase < 1100 || salarioBase > 1500);
                                listaEmpleados.get(i).getDatosLaborales().setSalarioBase(salarioBase);
                                explorador.nextLine();
                                break;

                            case 7:
                                explorador.nextLine();
                                System.out.print(
                                        "\nIngresa el nuevo usuario de " + listaEmpleados.get(i).getNombre() + " : ");
                                String user = explorador.nextLine();
                                listaEmpleados.get(i).getSesion().setUser(user);
                                login.getListaUsuarios().get(i + 1).getSesion().setUser(user);
                                break;

                            case 8:
                                explorador.nextLine();
                                System.out.print(
                                        "\nIngrese el nuevo password de " + listaEmpleados.get(i).getNombre() + " : ");
                                String password = explorador.nextLine();
                                listaEmpleados.get(i).getSesion().setPassword(password);
                                login.getListaUsuarios().get(i + 1).getSesion().setPassword(password);
                                break;

                            case 9:
                                System.out.println();
                                break;
                        }

                    } while (datoModificado != 9);

                    bandera = false;
                }
            }

            if (bandera == true) {
                System.out.println(
                        "\nLa persona " + nombreBusqueda + " no se encuentra dentro del sistema. Intenta nuevamente.");
            }
        } while (bandera);

    }

    public void eliminarEmpleado(Login login) {
        Scanner in = new Scanner(System.in);
        String empleadoAEliminar;
        boolean bandera = true;
        System.out.println("\n<---------------------- Eliminar empleado ------------------------>");

        System.out.println("\nLista de empleados por nombre: ");
        for (int i = 0; i < listaEmpleados.size(); i++) {
            System.out.println("Empleado " + (i + 1) + ": " + listaEmpleados.get(i).getNombre());
        }

        do {
            System.out.print("\nIngresa el nombre del empleado que deseas eliminar: ");
            empleadoAEliminar = in.nextLine();

            for (int i = 0; i < listaEmpleados.size(); i++) {
                if (listaEmpleados.get(i).getNombre().equalsIgnoreCase(empleadoAEliminar)) {
                    listaEmpleados.remove(i);
                    login.getListaUsuarios().remove(i + 1);
                    bandera = false;
                }
            }

            if (bandera == true) {
                System.out.println("\nLa persona " + empleadoAEliminar
                        + " no se encuentra dentro del sistema o está mal escrito. Intenta nuevamente.");
            }
        } while (bandera);

        if (listaEmpleados.isEmpty() == true) {
            System.out.println("\nNo hay un empleado por mostrar");
        } else {
            System.out.println("\nLista de empleados actualizada por nombre: ");
            for (int i = 0; i < listaEmpleados.size(); i++) {
                System.out.println("Empleado " + (i + 1) + ": " + listaEmpleados.get(i).getNombre());
            }
        }
    }

    public void verHistorialTrabajoEmpleado() {
        Scanner teclado = new Scanner(System.in);
        String nombreEmpleadoABuscar;
        String[] diasSemana = { "LU", "MA", "MI", "JU", "VI" };
        boolean bandera = true;
        double[] listaHoraEntrada;
        double[] listaHoraSalida;
        double[] listaSueldo;
        double horaEspecificaDiaE;
        int horaE = 0, minutoE = 0;
        double horaEspecificaDiaS;
        int horaS = 0, minutoS = 0;
        String sueldoFormateado;
        Empleado empleadoHistorial = null;

        System.out.println("\n\n<------------------------ Historial laboral del empleado ------------------------>");

        System.out.println("\nLista de empleados por nombre: ");
        for (int i = 0; i < listaEmpleados.size(); i++) {
            System.out.println("Empleado " + (i + 1) + ": " + listaEmpleados.get(i).getNombre());
        }

        do {
            System.out.print("\nIngrese el nombre del empleado que desee ver su historial de trabajo: ");
            nombreEmpleadoABuscar = teclado.nextLine();

            for (int i = 0; i < listaEmpleados.size(); i++) {
                if (listaEmpleados.get(i).getNombre().equalsIgnoreCase(nombreEmpleadoABuscar)) {
                    empleadoHistorial = listaEmpleados.get(i);
                    bandera = false;
                }
            }

            if (bandera == true) {
                System.out.println("\nLa persona " + nombreEmpleadoABuscar
                        + " no se encuentra dentro del sistema o está mal escrito. Intenta nuevamente.");
            }
        } while (bandera);

        listaHoraEntrada = empleadoHistorial.getDatosLaborales().getListaHoraEntrada();
        listaHoraSalida = empleadoHistorial.getDatosLaborales().getListaHoraSalida();
        listaSueldo = empleadoHistorial.getDatosLaborales().getListaSueldo();

        System.out.println(
                "\n----------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t" + empleadoHistorial.getNombre().toUpperCase());
        System.out.println(
                "----------------------------------------------------------------------------------------------");
        System.out.println("Dia semana\t\tHora Entrada\t\tHora Salida\t\tSalario");

        for (int i = 0; i < listaHoraEntrada.length; i++) {
            // Entrada
            horaEspecificaDiaE = listaHoraEntrada[i];
            horaE = (int) horaEspecificaDiaE;
            minutoE = (int) ((horaEspecificaDiaE - horaE) * 100);

            // Salida
            horaEspecificaDiaS = listaHoraSalida[i];
            horaS = (int) horaEspecificaDiaS;
            minutoS = (int) ((horaEspecificaDiaS - horaS) * 100);

            sueldoFormateado = String.format("%.2f", listaSueldo[i]);
            System.out.println("   " + diasSemana[i] + "\t\t\t   " + horaE + ":" + minutoE + "\t\t\t  " + horaS + ":"
                    + minutoS + "\t\t         " + sueldoFormateado);
        }
    }
}
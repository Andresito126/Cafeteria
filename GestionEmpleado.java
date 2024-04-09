import java.time.format.DateTimeParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            throws InputMismatchException, DateTimeParseException {
        int edad = 0;
        int salarioBase = 0;
        int opcionTrabajo = 0;
        int añoNacimiento = 0;
        long numeroTelefono = 0;
        boolean continuar = true;
        String fechaNacimiento = "";
        String puestoTrabajo = "";
        CredencialAcceso sesion;
        DatoLaboral datosLaborales = new DatoLaboral();
        Scanner input = new Scanner(System.in);
        String numeroTelefonoStr = "";
        boolean userVerificacion;

        System.out.println("\n<---------------------------- Registro de empleado ------------------------------>");

        System.out.println(" \n<-  -  -  - - Credenciales de acceso -  -  -  -  -> ");

        String user;
        do{
            System.out.print("\nUsuario: ");
            user = input.nextLine();
            userVerificacion = false;

            for (int i = 0; i < login.getListaUsuarios().size(); i++) {
                if (user.equals(login.getListaUsuarios().get(i).getSesion().getUser())) {
                    System.out.println("El usuario ya existe, por favor elija otro.");
                    userVerificacion = true;
                }
            }

        }while (userVerificacion);


        System.out.print("Contraseña: ");
        String password = input.nextLine();

        sesion = new CredencialAcceso(password, user);

        System.out.println(" \n\n<- - - - - - - Datos personales - - - - - - ->");

        System.out.print("\nNombre: ");
        String nombre = input.nextLine();

        System.out.print("Apelidos: ");
        String apellido = input.nextLine();

        do {
            System.out.print("Formato de fecha nacimiento dd/MM/yyyy '27/02/2005' \nFecha de nacimiento: ");
            fechaNacimiento = input.nextLine();

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fechaNaci = LocalDate.parse(fechaNacimiento, formatter);
                LocalDate compararNacimiento = LocalDate.now();

                añoNacimiento = fechaNaci.getYear();
                if(añoNacimiento > (compararNacimiento.getYear()-18)){
                    System.out.println("\nEl año de nacimiento debe ser mayor o igual a " + (compararNacimiento.getYear()-18));
                }
                else{
                    continuar = false;
                }
            }
            catch (DateTimeParseException e) {
                System.out.println("La fecha no coincide con el formato");
                input.nextLine();
            }
        } while (continuar);

        continuar = true;

        LocalDate date = LocalDate.now();
        int edadCorrespondiente = date.getYear() - añoNacimiento;

        do {
            try {
                System.out.print("Edad: ");
                edad = input.nextInt();
                if (edad != edadCorrespondiente) {
                    System.out.println("La edad no coincide con el año de nacimiento,por favor introduzca un número válido");
                }
            }
            catch (InputMismatchException ime) {
                System.out.println("Introduzca números enteros");
                input.nextLine();
            }
        } while (edad != edadCorrespondiente);

        do {
            try {
                System.out.print("Número de teléfono: ");
                numeroTelefono = input.nextLong();
                numeroTelefonoStr = numeroTelefonoStr.valueOf(numeroTelefono);
                if (numeroTelefonoStr.length() == 10) {
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

        System.out.println("\n <-  -  -  -  - Datos laborales -  -  -  -  ->");

        LocalDate fechaContrata = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaContratacion = fechaContrata.format(format);

        System.out.print("\nFecha de contratación: " + fechaContratacion);

        do{
            try{
                System.out.println("\nPuesto de trabajo: \n1.Mesero \n2.Cajero \n3.Cocinero");
                System.out.print("\nElija el puesto de trabajo a desempeñar de dicho empleado: ");
                opcionTrabajo = input.nextInt();
            }catch(InputMismatchException iME){
                System.out.println("\nDigite solo enteros");
                input.nextLine();
            }
        }while(opcionTrabajo <1 || opcionTrabajo >3);

        do{
            try{
                System.out.print("Salario base: ");
                salarioBase = input.nextInt();

                if (salarioBase <= 0) {
                    System.out.println("\nEl salario no puede ser un número negativo o cero, por favor introduzca un número válido");
                }
                else {
                    if (salarioBase < 1100 || salarioBase > 1500) {
                        System.out.println("\nEl salario debe comprender entre $1100 a $1500");
                    }
                }
            }
            catch(InputMismatchException iException){
                System.out.println("\nDigite números enteros");
                input.nextLine();
            }
        }while(salarioBase <= 0 || salarioBase < 1100 || salarioBase > 1500);

        if(opcionTrabajo==1){
            Mesero mesero = new Mesero(apellido,fechaNacimiento,edad,direccion,numeroTelefono,nombre,sesion);
            puestoTrabajo = "Mesero";
            datosLaborales.setFechaContratacion(fechaContratacion);
            datosLaborales.setPuestoTrabajo(puestoTrabajo);
            datosLaborales.setSalarioBase(salarioBase);
            mesero.setDatosLaborales(datosLaborales);
            this.listaEmpleados.add(mesero);
            login.setListaUsuarios(mesero);
        }
        else{
            if(opcionTrabajo == 2){
                Cajero cajero = new Cajero(apellido,fechaNacimiento,edad,direccion,numeroTelefono,nombre,sesion);
                puestoTrabajo = "Cajero";
                datosLaborales.setFechaContratacion(fechaContratacion);
                datosLaborales.setPuestoTrabajo(puestoTrabajo);
                datosLaborales.setSalarioBase(salarioBase);
                cajero.setDatosLaborales(datosLaborales);
                this.listaEmpleados.add(cajero);
                login.setListaUsuarios(cajero);
            }
            else{
                Cocinero cocinero = new Cocinero(apellido,fechaNacimiento,edad,direccion,numeroTelefono,nombre,sesion);
                puestoTrabajo = "Cocinero";
                datosLaborales.setFechaContratacion(fechaContratacion);
                datosLaborales.setPuestoTrabajo(puestoTrabajo);
                datosLaborales.setSalarioBase(salarioBase);
                cocinero.setDatosLaborales(datosLaborales);
                this.listaEmpleados.add(cocinero);
                login.setListaUsuarios(cocinero);
            }
        }

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

        System.out.println("\n<---------------------------------------------- Modificar datos empleado --------------------------------------------------------->");

        System.out.println("\n\nNombre\t\tEdad\t\tNúmero de teléfono\t\tDirección\t\t\tSalario base\t\tUsuario\t\tContraseña");
        for(int i=0; i<listaEmpleados.size(); i++){
            System.out.println(listaEmpleados.get(i).getNombre() + "\t " + listaEmpleados.get(i).getEdad() + "\t\t    " + listaEmpleados.get(i).getNumeroTelefono()
                    + "\t\t" + listaEmpleados.get(i).getDireccion() + "\t\t    " + listaEmpleados.get(i).getDatosLaborales().getSalarioBase()
                    + "\t\t" + listaEmpleados.get(i).getSesion().getUser()  + "\t\t    " + listaEmpleados.get(i).getSesion().getPassword());
        }

        do {
            System.out.print("\nIngresa el nombre del empleado que deseas modificar: ");
            nombreBusqueda = explorador.nextLine();

            for (int i = 0; i < listaEmpleados.size(); i++) {
                if (listaEmpleados.get(i).getNombre().equalsIgnoreCase(nombreBusqueda)) {
                    do {
                        try {
                            System.out.println("\n¿Qué dato desea modificar de " + listaEmpleados.get(i).getNombre()+ "?" +
                                    "\n1.Edad \n2.Direccion \n3.Número de telefono \n4.Salario Base \n5.Usuario \n6.Contraseña \n7.Salir");
                            datoModificado = explorador.nextInt();
                        } catch (InputMismatchException e) {
                            explorador.nextLine();
                            System.out.println("Digite solo enteros. Intente nuevamente");
                            continue;
                        }

                        switch (datoModificado) {
                            case 1:
                                do {
                                    try{
                                        System.out.print("\nIngresa el nuevo valor para edad de " + listaEmpleados.get(i).getNombre() + " : ");
                                        edad = explorador.nextInt();
                                        if (edad <= 18 || edad>45) {
                                            System.out.println("\nIngresa una edad valida mayor a 18 pero menor a 46");
                                        }

                                        if(edad == listaEmpleados.get(i).getEdad()){
                                            System.out.println("\nLa edad no puede ser igual a lo que se tiene previamente, debe ser diferente");
                                        }
                                    }
                                    catch(InputMismatchException smallException){
                                        System.out.println("Ingresa solo números");
                                        explorador.nextLine();
                                    }
                                } while (edad == listaEmpleados.get(i).getEdad() || edad<=18 || edad>45);
                                listaEmpleados.get(i).setEdad(edad);
                                explorador.nextLine();
                                break;
                            case 2:
                                explorador.nextLine();
                                System.out.print("\nIngresa la nueva dirección de " + listaEmpleados.get(i).getNombre()+ " : ");
                                String direccion = explorador.nextLine();
                                listaEmpleados.get(i).setDireccion(direccion);
                                break;
                            case 3:
                                do {
                                    try {
                                        System.out.print("\nIngrese el nuevo número de teléfono de " + listaEmpleados.get(i).getNombre() + " : ");
                                        numeroTelefono = explorador.nextLong();
                                        numeroTelefonoStr = numeroTelefonoStr.valueOf(numeroTelefono);
                                        if (numeroTelefonoStr.length() == 10) {
                                            listaEmpleados.get(i).setNumeroTelefono(numeroTelefono);
                                            entradaValida = false;
                                        } else {
                                            System.out.println("El número de teléfono en general debe comprender 10 digitos");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Introduzca números enteros");
                                        explorador.nextLine();
                                    }
                                } while (entradaValida);
                                break;
                            case 4:
                                do{
                                    try{
                                        System.out.print("\nIngresa el nuevo salario base de " + listaEmpleados.get(i).getNombre() + " : ");
                                        salarioBase = explorador.nextInt();

                                        if (salarioBase <= 0) {
                                            System.out.println("\nEl salario no puede ser un número negativo o cero, por favor introduzca un número válido");
                                        }
                                        else {
                                            if (salarioBase < 1100 || salarioBase > 1500) {
                                                System.out.println("\nEl salario debe comprender entre $1100 a $1500");
                                            }
                                        }
                                    }
                                    catch(InputMismatchException iException){
                                        System.out.println("\nDigite números enteros");
                                        explorador.nextLine();
                                    }
                                }while(salarioBase <= 0 || salarioBase < 1100 || salarioBase > 1500);
                                listaEmpleados.get(i).getDatosLaborales().setSalarioBase(salarioBase);
                                explorador.nextLine();
                                break;
                            case 5:
                                explorador.nextLine();
                                System.out.print("\nIngresa el nuevo usuario de " + listaEmpleados.get(i).getNombre() + " : ");
                                String user = explorador.nextLine();
                                listaEmpleados.get(i).getSesion().setUser(user);
                                login.getListaUsuarios().get(i + 1).getSesion().setUser(user);
                                break;
                            case 6:
                                explorador.nextLine();
                                System.out.print("\nIngrese el nuevo password de " + listaEmpleados.get(i).getNombre() + " : ");
                                String password = explorador.nextLine();
                                listaEmpleados.get(i).getSesion().setPassword(password);
                                login.getListaUsuarios().get(i + 1).getSesion().setPassword(password);
                                break;
                            case 7:
                                System.out.println();
                                break;
                        }

                    } while (datoModificado != 7);

                    bandera = false;
                }
            }

            if(bandera == true){
                System.out.println("\nLa persona " + nombreBusqueda + " no se encuentra dentro del sistema. Intenta nuevamente.");
            }
        } while (bandera);

    }

    public void eliminarEmpleado(Login login) {
        Scanner in = new Scanner(System.in);
        String empleadoAEliminar;
        boolean bandera = true;
        System.out.println("\n<---------------------- Eliminar empleado ------------------------>");

        System.out.println("\nLista de empleados por nombre: ");
        for(int i=0; i<listaEmpleados.size(); i++){
            System.out.println("Empleado " + (i+1) + ": " + listaEmpleados.get(i).getNombre());
        }

        do{
            System.out.print("\nIngresa el nombre del empleado que deseas eliminar: ");
            empleadoAEliminar = in.nextLine();

            for(int i=0; i<listaEmpleados.size(); i++){
                if (listaEmpleados.get(i).getNombre().equalsIgnoreCase(empleadoAEliminar)) {
                    listaEmpleados.remove(i);
                    login.getListaUsuarios().remove(i + 1);
                    bandera = false;
                }
            }

            if(bandera == true){
                System.out.println("\nLa persona " + empleadoAEliminar + " no se encuentra dentro del sistema o está mal escrito. Intenta nuevamente.");
            }
        }while(bandera);

        if(listaEmpleados.isEmpty()==true){
            System.out.println("\nNo hay un empleado por mostrar");
        }
        else{
            System.out.println("\nLista de empleados actualizada por nombre: ");
            for(int i=0; i<listaEmpleados.size(); i++){
                System.out.println("Empleado " + (i+1) + ": " + listaEmpleados.get(i).getNombre());
            }
        }
    }

    public void verHistorialTrabajoEmpleado() {
        Scanner teclado = new Scanner(System.in);
        String nombreEmpleadoABuscar;
        String [] diasSemana = {"LU","MA","MI","JU","VI"};
        boolean bandera = true;
        double [] listaHoraEntrada;
        double [] listaHoraSalida;
        double [] listaSueldo;
        double horaEspecificaDiaE;
        int horaE = 0, minutoE = 0;
        double horaEspecificaDiaS;
        int horaS = 0, minutoS = 0;
        String sueldoFormateado;
        Empleado empleadoHistorial = null;

        System.out.println("\n\n<------------------------ Historial laboral del empleado ------------------------>");

        System.out.println("\nLista de empleados por nombre: ");
        for(int i=0; i<listaEmpleados.size(); i++){
            System.out.println("Empleado " + (i+1) + ": " + listaEmpleados.get(i).getNombre());
        }

        do{
            System.out.print("\nIngrese el nombre del empleado que desee ver su historial de trabajo: ");
            nombreEmpleadoABuscar = teclado.nextLine();

            for(int i=0; i<listaEmpleados.size(); i++){
                if(listaEmpleados.get(i).getNombre().equalsIgnoreCase(nombreEmpleadoABuscar)){
                    empleadoHistorial = listaEmpleados.get(i);
                    bandera = false;
                }
            }

            if(bandera == true){
                System.out.println("\nLa persona " + nombreEmpleadoABuscar + " no se encuentra dentro del sistema o está mal escrito. Intenta nuevamente.");
            }
        }while(bandera);

        listaHoraEntrada = empleadoHistorial.getDatosLaborales().getListaHoraEntrada();
        listaHoraSalida = empleadoHistorial.getDatosLaborales().getListaHoraSalida();
        listaSueldo = empleadoHistorial.getDatosLaborales().getListaSueldo();

        System.out.println("\n----------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t" + empleadoHistorial.getNombre().toUpperCase());
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("Dia semana\t\tHora Entrada\t\tHora Salida\t\tSalario");

        for(int i=0; i<listaHoraEntrada.length; i++){
            //Entrada
            horaEspecificaDiaE = listaHoraEntrada[i];
            horaE = (int) horaEspecificaDiaE;
            minutoE = (int)((horaEspecificaDiaE-horaE)*100);

            //Salida
            horaEspecificaDiaS = listaHoraSalida[i];
            horaS = (int) horaEspecificaDiaS;
            minutoS = (int)((horaEspecificaDiaS-horaS)*100);

            sueldoFormateado = String.format("%.2f", listaSueldo[i]);
            System.out.println("   " + diasSemana[i] + "\t\t\t   " + horaE + ":" + minutoE + "\t\t   " + horaS + ":" + minutoS + "\t\t " + sueldoFormateado);
        }

        double propinaTotal = 0;
        if (empleadoHistorial instanceof Mesero) {
            double[] listaPropina = ((Mesero) empleadoHistorial).getListaPropina();
            for (int i = 0; i < listaHoraEntrada.length; i++)
                propinaTotal += listaPropina[i];
        }
        System.out.print("\t\nLa propina semanal que junto es de: " + propinaTotal+"\n");
    }

}
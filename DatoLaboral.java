import java.util.Scanner;
import java.util.spi.CalendarNameProvider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatoLaboral {
    private String listaAsistencia[] = new String[5];
    private double listaHorasTrabajadas[] = new double[5];
    private double listaSueldo[] = new double[5];
    private double listaHoraEntrada[] = new double[5];
    private double listaHoraSalida[] = new double[5];
    private int salarioBase;
    private int retardos;
    private String fechaContratacion;
    private String puestoTrabajo;

    public String[] getListaAsistencia() {
        return listaAsistencia;
    }

    public void setListaAsistencia(String[] listaAsistencia) {
        this.listaAsistencia = listaAsistencia;
    }

    public double[] getListaHorasTrabajadas() {
        return listaHorasTrabajadas;
    }

    public void setListaHorasTrabajadas(double[] listaHorasTrabajadas) {
        this.listaHorasTrabajadas = listaHorasTrabajadas;
    }

    public int getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(int salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    // METODOS FUNCIONALES

    public void conocerSalario() {
        String[] diasSemana = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" };
        double sueldoTotal = 0;
        int totalRetardosSemana = 0;

        for (int i = 0; i < listaSueldo.length; i++) {

            String horasFormateadas = String.format("%.2f", listaHorasTrabajadas[i]);
            String sueldoFormateado = String.format("%.2f", listaSueldo[i]);
            System.out.println(diasSemana[i] + " - " + listaAsistencia[i] + " ----- Horas trabajadas: " + horasFormateadas + " hrs. ----- Sueldo recaudado: $" + sueldoFormateado);
            sueldoTotal += listaSueldo[i];

            if (listaHoraEntrada[i] > 16.25) {
                totalRetardosSemana++;
            }
        }

        System.out.println("El sueldo total es de: $" + String.format("%.2f", sueldoTotal));
        System.out.println("Usted tuvo: " + totalRetardosSemana + " retardo/dds.");
    }

    public void registrarEntrada() {
        Calendar fechaHoy = Calendar.getInstance();
        fechaHoy.set(Calendar.HOUR_OF_DAY, 16);
        Date fechaHoyDate = fechaHoy.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat asistencia = new SimpleDateFormat("dd/MM/yyyy");

        double horaEntradaEstimada = 16.25;

        int horaEntrada = fechaHoy.get(Calendar.HOUR_OF_DAY);
        int minutoEntrada = fechaHoy.get(Calendar.MINUTE);
        String diaEntrada = asistencia.format(fechaHoyDate);
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;
        int indice = 0;
        double horaRegistrada;

        do {

            

            try {
                System.out.println("\n<------------------------------- Registro hora de entrada ----------------------------------->");
                System.out.print("\nVerifique que la fecha y hora sean las correctas" + "\nFecha: " + diaEntrada + " " + "\nHora actual: " + sdf.format(fechaHoyDate));
                System.out.println("\n\nPresione '1' para confirmar su entrada \n1.Confirmar");
                opcion = entrada.nextInt();
            } catch (Exception e) {
                System.out.println("Digite solo el número");
                entrada.nextLine();
                continue;
            }

            if (opcion == 1){
                while (indice < listaHoraEntrada.length && listaHoraEntrada[indice] != 0) {
                    indice++;
                }

                if ((indice==0) || (indice>0  && listaHoraSalida[indice-1] != 0)) {

                    if (indice < listaHoraEntrada.length) {
                        horaRegistrada = (horaEntrada + (minutoEntrada / 60.0));
                        if(horaRegistrada > horaEntradaEstimada){
                            retardos ++;
                            System.out.println("\nUsted tiene un retardo.");
                        }
    
                        listaHoraEntrada[indice] = (horaEntrada + (minutoEntrada / 60.0));
                        listaAsistencia[indice] = diaEntrada;
    
                        System.out.println("Hora de entrada registrada correctamente.");
                        System.out.println("Estas en la posicion " + indice);
                    }
                    
                }
                
                else{
                    System.out.println("Acabas de registrar un entrada anteriormente. No puedes registar dos entradas.");
                }

            }

            else{
                System.out.println("\nIngresaste una opcion no valida. Ingresa una correcta.");
            }

        } while (opcion!=1);
    }

    public void registrarSalida() {
        Calendar fechaHoy = Calendar.getInstance();
        fechaHoy.set(Calendar.HOUR_OF_DAY, 23);
        Date fechaHoyDate = fechaHoy.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat mostrarFecha = new SimpleDateFormat("dd/MM/yyyy");

        Scanner entrada = new Scanner(System.in);
        int horaSalida = fechaHoy.get(Calendar.HOUR_OF_DAY);
        int minutoSalida = fechaHoy.get(Calendar.MINUTE);
        int opcion = 0;
        int indice = 0;

        do {
            try {
                System.out.println("\n<------------------------------- Registro hora de salida ----------------------------------->");
                System.out.println("\nFecha: " + mostrarFecha.format(fechaHoyDate) + "\nHora actual: " + sdf.format(fechaHoyDate));

                System.out.println("\n\nPresione '1' para confirmar su salida \n1.Confirmar");
                opcion = entrada.nextInt();
            } catch (Exception e) {
                System.out.println("Digite solo número");
                entrada.nextLine();
                continue;
            }

            if(opcion==1){
                while (indice < listaHoraSalida.length && listaHoraSalida[indice] != 0) {
                indice++;
                }

                if (indice < listaHoraSalida.length) {
                    if (listaHoraEntrada[indice] != 0) {
                        listaHoraSalida[indice] = horaSalida + (minutoSalida / 60.0);
                        System.out.println("\nHora de salida registrada correctamente.\n");
                    } else {
                        System.out.println("No puedes registrar salida sin haber registrado previamente tu entrada.");}
                }

                else {
                System.out.println("No hay espacio disponible para registrar la salida");
                }

            }

            else{
                System.out.println("\n3Ingresaste una opcion no valida. Ingresa una correcta.");
            }

        } while (opcion != 1);
    }

    public void calcularSalario() {
        double hEntrada = 0, hSalida = 0;
        int faltas = 0;
        double sueldo = 0, horasTrabajadas = 0;

        for (int i = 0; i < listaHoraEntrada.length; i++) {

            if (retardos >= 3) {
                faltas++;
                retardos = 0;
            }

            hEntrada = listaHoraEntrada[i];
            hSalida = listaHoraSalida[i];
            horasTrabajadas = (hSalida - hEntrada);
            if (faltas > 1) {
                horasTrabajadas = 0;
                faltas--;
            }

            if (horasTrabajadas < 0) {
                sueldo = 0;
            }

            else {
                sueldo = ((salarioBase/35) * horasTrabajadas);
            }

            listaHorasTrabajadas[i] = horasTrabajadas;
            listaSueldo[i] = sueldo;

            if(i==0){
                System.out.println("Las horas trabajadas fueron: " + listaHorasTrabajadas[0]);
                System.out.println("El sueldo es " + listaSueldo[0]);
            }

            if(i==1){
                System.out.println("Las horas trabajadas fueron: " + listaHorasTrabajadas[1]);
                System.out.println("El sueldo es " + listaSueldo[1]);
            }

        }
    }
}
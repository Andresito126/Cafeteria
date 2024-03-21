import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DatoLaboral {
    
    private String listaAsistencia[] = new String[5];
    private double listaHorasTrabajadas[] = new double[5];
    private double listaSueldo[]= new double[5];
    private double listaHoraEntrada[] = new double[5];
    private double listaHoraSalida[] = new double[5];
    private int salarioBase= 40; //salario de 1 hora trabajada 
    private int retardos=0;
    private double sueldo;
    private String fechaContratacion;
    private String puestoTrabajo;
    private double horasTrabajadas;


    
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

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
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
    

    //METODOS FUNCIONALES 

    public void conocerSalario() {
        String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        double sueldoTotal = 0;
        int totalRetardosSemana=0;

        for (int i = 0; i < listaSueldo.length; i++) {
            String horasFormateadas = String.format("%.2f", listaHorasTrabajadas[i]); 
            String sueldoFormateado = String.format("%.2f", listaSueldo[i]);
            System.out.println(diasSemana[i] + " - " + listaAsistencia[i] + " ----- Horas trabajadas: " + horasFormateadas + " hrs. ----- Sueldo recaudado: $" + sueldoFormateado);
            sueldoTotal += listaSueldo[i];

            
            if (listaHoraEntrada[i] > 14.25) {
                totalRetardosSemana++;  
            }
        }

        System.out.println("El sueldo total es de: $" + String.format("%.2f", sueldoTotal));
        System.out.println("Usted tuvo: "+ totalRetardosSemana + " retardo/dds.");
    }
    

    public void registrarEntrada(){
        
        Calendar fechaHoy = Calendar.getInstance();
        Date fechaHoyDate = fechaHoy.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat asistencia = new SimpleDateFormat("dd/MM/yyyy");
        
        double horaEntradaEstimada = 14.25;
        double horaEntrada, minutoEntrada;
        String diaEntrada;
        
        horaEntrada = fechaHoy.get(Calendar.HOUR_OF_DAY);
        minutoEntrada = fechaHoy.get(Calendar.MINUTE);
        diaEntrada= asistencia.format(fechaHoy.getTime());
        int opcion= 0;

        do {
            try {
                Scanner entrada = new Scanner(System.in);
                System.out.println("\nVerifique que la fecha y hora de entrada sean las correctas.");
            System.out.println("La fecha y la hora actuales son: "+ sdf.format(fechaHoyDate));
            System.out.println("\n1.-Confirmar.\n2.-Salir");

            opcion = entrada.nextInt();

                if (opcion == 1){

                    int indice = 0;
                    while (indice < listaHoraEntrada.length && listaHoraEntrada[indice] != 0) {
                        indice++;
                    }
    
           
                    if (indice < listaHoraEntrada.length) {
                        double horaRegistrada = horaEntrada + minutoEntrada / 60.0;
                        if(horaRegistrada > horaEntradaEstimada){
                            retardos ++;
                            System.out.println("\nUsted tiene un retardo.");
                        }

                        listaHoraEntrada[indice] = (horaEntrada + (minutoEntrada / 60.0));
                        listaAsistencia[indice] = diaEntrada;

                        System.out.println("Hora de entrada registrada correctamente.");
                    } 

                    else {
                        System.out.println("No hay espacio disponible para registrar la entrada.");
                    }         
                }

                else if(opcion ==2){
                    System.out.println("Usted ha salido exitosamente.");
                    break;
                }

                else{
                    System.out.println("\nIngresaste una opcion no valida. Ingresa una correcta.");
                }
                
            } catch (Exception e) {
                System.out.println("Ingresaste una opcion no valida. Ingresa una correcta.");
            }
            

        } while (opcion != 1 && opcion != 2);
    } 



    public void registrarSalida(){

        Calendar fechaHoy = Calendar.getInstance();
        Date fechaHoyDate = fechaHoy.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        double horaSalida, minutoSalida;

        horaSalida = fechaHoy.get(Calendar.HOUR_OF_DAY);
        minutoSalida = fechaHoy.get(Calendar.MINUTE);
        int opcion=0;
        do {
            try {
                Scanner entrada = new Scanner(System.in);
                System.out.println("\nVerifique que la fecha y hora de salida sean las correctas.");
                System.out.println("La fecha y la hora actuales son: "+ sdf.format(fechaHoyDate));
                System.out.println("\n1.-Confirmar.\n2.-Salir");
                opcion = entrada.nextInt();
    
                if(opcion==1){
    
                    int indice = 0;
                    while (indice < listaHoraSalida.length && listaHoraSalida[indice] != 0) {
                    indice++;
                    }
            
                    if (indice < listaHoraSalida.length) {
                    listaHoraSalida[indice] = horaSalida + (minutoSalida / 60.0)+5;
                    System.out.println("\nHora de salida registrada correctamente.\n");
                    }
                
                    else {
                    System.out.println("No hay espacio disponible para registrar la entrada.");
                    }
    
                }
    
                else if(opcion ==2){
                    System.out.println("\nUsted ha salido exitosamente.");
                    break;
                }
    
                else{
                    System.out.println("\n3Ingresaste una opcion no valida. Ingresa una correcta.");
                }
                
            } catch (Exception e) {
                System.out.println("Ingresaste una opcion no valida. Ingresa una correcta.");
            }
           

        } while (opcion != 1 && opcion != 2); 
    }


    public void calcularSalario(){

        double hEntrada, hSalida;
        int faltas = 0;

        for(int i = 0; i < listaHoraEntrada.length; i++) {

            if (retardos >= 3) {
                faltas++;
                retardos = 0; 
            }
        
            hEntrada = listaHoraEntrada[i];
            hSalida = listaHoraSalida[i];
            horasTrabajadas= (hSalida - hEntrada);

            if(faltas>0){
                horasTrabajadas -= 8; // Restar 8 horas por cada falta
                faltas--;
            }


            if (horasTrabajadas < 0) {
                // Si las horas trabajadas son negativas (debido a las faltas), se establece el sueldo en 0
                sueldo = 0;
            } 

                
            else {
                // Calcular el sueldo basado en las horas trabajadas
                sueldo = salarioBase * horasTrabajadas;
            }

            listaHorasTrabajadas[i] = horasTrabajadas;
            listaSueldo[i] = sueldo;

        }
    }
}

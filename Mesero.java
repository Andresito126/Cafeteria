import java.util.Scanner;
import java.util.InputMismatchException;

public class Mesero extends Empleado{
    private double listaPropina[] = new double [5];
    private double totalPropinaDia;
            
    public Mesero() {
    }
    
    public Mesero(String apellido, String fechaNacimiento, int edad, String direccion, long numeroTelefono, String nombre, CredencialAcceso sesion) {
        super(apellido, fechaNacimiento, edad, direccion, numeroTelefono, nombre, sesion);
    }
    
    public void agregarPropina(){
        Scanner in = new Scanner(System.in);
        double propina = 0;
        double [] listaHoraEntrada = datosLaborales.getListaHoraEntrada();
        double [] listaHoraSalida = datosLaborales.getListaHoraSalida();

        for(int i=0; i<listaHoraSalida.length; i++){
            if(listaHoraEntrada[i] != 0 && listaHoraSalida[i] == 0){
                do{
                    try{
                        System.out.print("Hola " + nombre + " .Ingresa la cantidad de propina que te acaban de dar: ");
                        propina = in.nextDouble(); 
                        if(propina<=0){
                           System.out.println("\nError, no puedes ingresar números negativos o el cero");
                        }
                    }
                            
                    catch(InputMismatchException input){
                        System.out.println("\nIngresa solo números");
                        in.nextLine();
                    }
                            
                }while(propina<=0);
                
                totalPropinaDia = totalPropinaDia + propina;
                listaPropina[i] = totalPropinaDia;
            }
        }
    }
    
    @Override
    public void calcularSalario() {
        double [] listaHoraEntrada = datosLaborales.getListaHoraEntrada();
        double [] listaHoraSalida = datosLaborales.getListaHoraSalida();
        double [] listaHorasTrabajadas = datosLaborales.getListaHorasTrabajadas();
        double [] listaSueldo = datosLaborales.getListaSueldo();
        double hEntrada = 0, hSalida = 0;
        double sueldo = 0, horasTrabajadas = 0;
        
        for (int i = 0; i < listaHoraEntrada.length; i++) {

            hEntrada = listaHoraEntrada[i];
            hSalida = listaHoraSalida[i];

            horasTrabajadas = (hSalida - hEntrada);

            sueldo = ((datosLaborales.getSalarioBase()/35.0) * horasTrabajadas);

            listaHorasTrabajadas[i] = horasTrabajadas;
            listaSueldo[i] = sueldo;
        }
    }
    
    @Override
    public void conocerSalario() {
        String[] diasSemana = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" };
        double sueldoTotal= 0;
        double [] listaHoraEntrada = datosLaborales.getListaHoraEntrada();
        String [] listaAsistencia = datosLaborales.getListaAsistencia();
        double [] listaHorasTrabajadas = datosLaborales.getListaHorasTrabajadas();
        double [] listaSueldo = datosLaborales.getListaSueldo();
        double hEntrada = 0, hSalida = 0;
        double propinaTotal = 0;
        int totalRetardosSemana = 0;
        double salarioDescontado = 0;

        for (int i = 0; i < listaSueldo.length; i++) {

            String horasFormateadas = String.format("%.2f", listaHorasTrabajadas[i]);
            String sueldoFormateado = String.format("%.2f", listaSueldo[i]);
            System.out.println(diasSemana[i] + " - " + listaAsistencia[i] + " ----- Horas trabajadas: " + horasFormateadas + " hrs. ----- Sueldo recaudado: $" + sueldoFormateado);
            sueldoTotal += listaSueldo[i];
            propinaTotal += listaPropina[i];

            if (listaHoraEntrada[i] > 16.15) {
                totalRetardosSemana++;

                if(totalRetardosSemana == 3){
                    salarioDescontado = datosLaborales.getSalarioBase()/5.0;
                    sueldoTotal = sueldoTotal - salarioDescontado;
                }
            }
        }

        System.out.println("\nUsted tuvo: " + totalRetardosSemana + " retardo/dds.");

        if(totalRetardosSemana == 3){
            System.out.println("Se te ha descontado un dia por haber generado 3 retardos. Tu sueldo por dia es " + salarioDescontado);
        }

        System.out.println("El sueldo generado es de: $" + String.format("%.2f", sueldoTotal));
        System.out.println("Haz recibido $" + propinaTotal + " de propina");
        System.out.println("Tu salario total es " + String.format("%.2f", sueldoTotal + propinaTotal));
    }
    
}


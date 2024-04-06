public class Cocinero extends Empleado{

    public Cocinero(String apellido, String fechaNacimiento, int edad, String direccion, long numeroTelefono, String nombre, CredencialAcceso sesion) {
        super(apellido, fechaNacimiento, edad, direccion, numeroTelefono, nombre, sesion);
    }
    
    @Override
    public void calcularSalario() {
        double [] listaHoraEntrada = datosLaborales.getListaHoraEntrada();
        double [] listaHoraSalida = datosLaborales.getListaHoraSalida();
        double [] listaHorasTrabajadas = datosLaborales.getListaHorasTrabajadas();
        double [] listaSueldo = datosLaborales.getListaSueldo();
        double hEntrada = 0, hSalida = 0;
        int faltas = 0;
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
        int totalRetardosSemana = 0;
        double salarioDescontado = 0;

        for (int i = 0; i < listaSueldo.length; i++) {

            String horasFormateadas = String.format("%.2f", listaHorasTrabajadas[i]);
            String sueldoFormateado = String.format("%.2f", listaSueldo[i]);
            System.out.println(diasSemana[i] + " - " + listaAsistencia[i] + " ----- Horas trabajadas: " + horasFormateadas + " hrs. ----- Sueldo recaudado: $" + sueldoFormateado);
            sueldoTotal += listaSueldo[i];

            if (listaHoraEntrada[i] > 16.15) {
                totalRetardosSemana++;

                if(totalRetardosSemana == 3){
                    salarioDescontado = datosLaborales.getSalarioBase()/5.0;
                    sueldoTotal = sueldoTotal - salarioDescontado;
                }
            }
        }

        System.out.println("\nUsted tuvo: " + totalRetardosSemana + " retardo/s.");

        if(totalRetardosSemana == 3){
            System.out.println("Se te ha descontado un dia por haber generado 3 retardos. Tu sueldo por dia es " + salarioDescontado);
        }

        System.out.println("El sueldo total es de: $" + String.format("%.2f", sueldoTotal));
    }
    
}

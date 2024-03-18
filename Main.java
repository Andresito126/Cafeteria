
public class Main {


    public static void main(String[] args) {

        GestionEmpleado objGestion = new GestionEmpleado(); 
        objGestion.registarDatosEmpleado();
        DatosLaborales objDatos = new DatosLaborales();
        objDatos.registrarEntrada();
        

        System.out.println("Hello World!");
    }
}

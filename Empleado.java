public class Empleado extends Persona{
    private String apellido;
    private String fechaNacimiento;
    private int edad;
    private String direccion;
    private long numeroTelefono;
    private DatoLaboral datosLaborales;

    public Empleado(){

    }

    public Empleado(String apellido, String fechaNacimiento, int edad, String direccion, long numeroTelefono, DatoLaboral datosLaborales, String nombre) {
        super(nombre);
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.direccion = direccion;
        this.numeroTelefono = numeroTelefono;
        this.datosLaborales = datosLaborales;
    }

    public String getApellido(){
        return apellido;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }


    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad(){
        return edad;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(long numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public DatoLaboral getDatosLaborales() {
        return datosLaborales;
    }

    public void setDatosLaborales(DatoLaboral datosLaborales) {
        this.datosLaborales = datosLaborales;
}
}
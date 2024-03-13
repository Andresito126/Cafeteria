public class Empleado extends Persona{
    private String fechaNacimiento;
    private String direccion;
    private long numeroTelefono;
    private DatoLaboral datosLaborales;

    public Empleado(String fechaNacimiento, String direccion, long numeroTelefono, DatoLaboral datosLaborales, String nombre, int edad) {
        super(nombre, edad);
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.numeroTelefono = numeroTelefono;
        this.datosLaborales = datosLaborales;
    }
    
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

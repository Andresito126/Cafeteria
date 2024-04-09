public abstract class Empleado extends Persona{
    protected String apellido;
    protected String fechaNacimiento;
    protected int edad;
    protected String direccion;
    protected long numeroTelefono;
    protected DatoLaboral datosLaborales;

    public Empleado(){

    }

    public Empleado(String apellido, String fechaNacimiento, int edad, String direccion, long numeroTelefono, String nombre, CredencialAcceso sesion) {
        super(nombre, sesion);
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.direccion = direccion;
        this.numeroTelefono = numeroTelefono;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
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

    public abstract void calcularSalario();
    
    public abstract void conocerSalario();
   
}
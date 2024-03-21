public class Persona {
    protected String nombre;
    protected CredencialAcceso sesion;

    public Persona(){
        
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CredencialAcceso getSesion() {
        return sesion;
    }

    public void setSesion(CredencialAcceso sesion) {
        this.sesion = sesion;
    }

    
}

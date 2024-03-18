public class DatoLaboral {
    private double listaAsistencia[] = new double[5];
    private double listaHorasTrabajadas[] = new double[5];
    private int salarioBase; 
    private double sueldo;
    private String fechaContratacion;
    private String puestoTrabajo;

    public double[] getListaAsistencia() {
        return listaAsistencia;
    }

    public void setListaAsistencia(double[] listaAsistencia) {
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

    public void conocerSalario(){

    }

    public void registrarEntrada(){

    }

    public void registrarSalida(){

    }

    public void calcularSalario(){

    }
}

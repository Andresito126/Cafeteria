import java.util.ArrayList;

public class Login {
    private ArrayList <Persona> listaUsuarios = new ArrayList<Persona>();

    public ArrayList<Persona> getListaUsuarios() {
        return listaUsuarios;
    }
    
    public void setListaUsuarios(Persona obj) {
        this.listaUsuarios.add(obj);
    }

    public void autenticarAcceso(){

    }
}
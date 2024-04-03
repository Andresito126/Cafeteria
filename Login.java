import java.util.ArrayList;

public class Login {
    private ArrayList <Persona> listaUsuarios = new ArrayList<Persona>();

    public ArrayList<Persona> getListaUsuarios() {
        return listaUsuarios;
    }
    
    public void setListaUsuarios(Persona obj) {
        this.listaUsuarios.add(obj);
    }

    public Persona autenticarAcceso(String password,String user){
        
        for(int i=0; i<listaUsuarios.size(); i++){
            if(password.equals(listaUsuarios.get(i).getSesion().getPassword()) && user.equals(listaUsuarios.get(i).getSesion().getUser())){
                return listaUsuarios.get(i);
            }
        }    
         return null;
    }
}
package Clases;


import Clases.ConexionSQL;
import Clases.Usuario;


public class Administrador extends Usuario {
    int id_admi;
    
    public void Administrador(Usuario us, int id_admi, int id_usu){
        super.Usuario(us.getNom(), us.getApelPat_usu(), us.getApelMat_usu(), 
                us.getfechNaci_usu(), us.getDomi_usu(), us.getTelePart(), us.getTeleCelu());
        
        this.id_admi = id_admi;
        this.id_usu = id_usu;
        
        
    }
    
    public void setId_admi(int id){
        this.id_admi = id;
    }
    public int getid_admi(){
        return this.id_admi;
    }
    public void setId_usu(int id){
        this.id_usu = id;
    }
    public int getid_usu(){
        return this.id_usu;
    }
}

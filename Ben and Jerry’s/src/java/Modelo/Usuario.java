package Modelo;
import java.util.Date;





public class Usuario {
    int id_usuario;
    Dirrecion dirrec_usuario;
    String nom_usuario;
    String apelPat_usurio;
    String apelMat_usurio; 
    String cel_usuario;
    String tel_usuario;
    String user_usuario;
    String pass_usuario;
    Tarjeta tarjeta_usuario;
    Date fechNaci_usurio;
    
    
    
    public void Usuario(){}

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Dirrecion getId_dirrec_usuario() {
        return dirrec_usuario;
    }

    public void setId_dirrec_usuario(Dirrecion dirrec_usuario) {
        this.dirrec_usuario = dirrec_usuario;
    }

    public Tarjeta getTarjeta_usuario() {
        return tarjeta_usuario;
    }

    public void setTarjeta_usuario(Tarjeta tarjeta_usuario) {
        this.tarjeta_usuario = tarjeta_usuario;
    }

    public String getNom_usuario() {
        return nom_usuario;
    }

    public void setNom_usuario(String nom_usuario) {
        this.nom_usuario = nom_usuario;
    }

    public String getApelPat_usurio() {
        return apelPat_usurio;
    }

    public void setApelPat_usurio(String apelPat_usurio) {
        this.apelPat_usurio = apelPat_usurio;
    }

    public String getApelMat_usurio() {
        return apelMat_usurio;
    }

    public void setApelMat_usurio(String apelMat_usurio) {
        this.apelMat_usurio = apelMat_usurio;
    }

    public Date getFechNaci_usurio() {
        return fechNaci_usurio;
    }

    public void setFechNaci_usurio(Date fechNaci_usurio) {
        this.fechNaci_usurio = fechNaci_usurio;
    }

    public String getCel_usuario() {
        return cel_usuario;
    }

    public void setCel_usuario(String cel_usuario) {
        this.cel_usuario = cel_usuario;
    }

    public String getTel_usuario() {
        return tel_usuario;
    }

    public void setTel_usuario(String tel_usuario) {
        this.tel_usuario = tel_usuario;
    }

    public String getUser_usuario() {
        return user_usuario;
    }

    public void setUser_usuario(String user_usuario) {
        this.user_usuario = user_usuario;
    }

    public String getPass_usuario() {
        return pass_usuario;
    }

    public void setPass_usuario(String pass_usuario) {
        this.pass_usuario = pass_usuario;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}

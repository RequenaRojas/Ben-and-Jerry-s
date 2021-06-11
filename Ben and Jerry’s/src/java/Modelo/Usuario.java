package Modelo;
import Controlador.ActDirrecion;
import Controlador.ActTarjeta;
import Controlador.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;





public class Usuario {
    private int id_usuario;
    private Dirrecion dirrec_usuario;
    private String nom_usuario;
    private String apelPat_usurio;
    private String apelMat_usurio; 
    private int cel_usuario;
    private int tel_usuario;
    private String user_usuario;
    private String pass_usuario;
    private Tarjeta tarjeta_usuario;
    private Date fechNaci_usurio;
    private int privilegio;

     public static Usuario verificarUsuario(String user, String pass) throws ClassNotFoundException, SQLException, ServletException{
        Usuario u = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConexionSQL.getConnection();
            String q = "select * from MUsuario "
                    + "where user_usuario = ? AND pass_usuario = ?";
            
            ps = con.prepareStatement(q);
            
            ps.setString(1, user);
            ps.setString(2, pass);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNom_usuario(rs.getString("nombre_usuario"));
                u.setApelPat_usurio(rs.getString("apppat_usuario"));
                u.setApelMat_usurio(rs.getString("apmat_usuario"));
                u.setFechNaci_usurio(rs.getDate("fecnac_usuario"));
                u.setTel_usuario(rs.getInt("tel_usuario"));
                u.setCel_usuario(rs.getInt("cel_usuario"));
                u.setUser_usuario(rs.getString("user_usuario"));
                u.setPass_usuario(rs.getString("pass_usuario"));
                u.setDirrec_usuario(ActDirrecion.getDirrecion(rs.getInt("id_ddireccion")));
                u.setTarjeta_usuario(ActTarjeta.getTarjeta(rs.getInt("id_tarejata")));
                u.setPrivilegio(rs.getInt("privilegio"));
                break;
                
            }
        
        }catch(SQLException sq){
            System.out.println("Error al verificar al usuario");
            System.out.println(sq.getMessage());
            u = null;
        }finally{
            try{
                rs.close();
                ps.close();
                con.close();
            }catch(Exception e){
                System.out.println("Error al desconectar la BD");
                System.out.println(e.getMessage());
            }
        }
        return u;
    }
     
    public int getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(int privilegio) {
        this.privilegio = privilegio;
    }
  
    public void Usuario(){}
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Dirrecion getDirrec_usuario() {
        return dirrec_usuario;
    }

    public void setDirrec_usuario(Dirrecion dirrec_usuario) {
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

    public int getCel_usuario() {
        return cel_usuario;
    }

    public void setCel_usuario(int cel_usuario) {
        this.cel_usuario = cel_usuario;
    }

    public int getTel_usuario() {
        return tel_usuario;
    }

    public void setTel_usuario(int tel_usuario) {
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
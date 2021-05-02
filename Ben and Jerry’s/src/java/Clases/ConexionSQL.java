package Clases;

//Encargada de poder realizar la conexión con la BD
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
//Encargada de poder reaalizar las sentencias sql, creat, insert, delete, drop, update
import java.sql.Statement;
//Encrgada de poder realizar las consultas a la BD
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class ConexionSQL {
    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    
    
    public void ConexionSQL(){}
    public void init(Connection con, Statement set)
    throws ServletException, ClassNotFoundException, SQLException{
        
        Class.forName("com.mysql.jdbc.Driver");
        
        String url = "jdbc:mysql://wcwimj6zu5aaddlj.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/qb9w2pzeh1k667p7";
        String userName = "c35th5wrxgf5hvqg";
        String password = "gbkg98qh51iinx0i";
        
        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(url, userName, password);
        set = con.createStatement();
        
        setCon(con);
        setSET(set);
        
    }
    
    public Connection getCon(){
        return con;
    }
    public Statement getSet(){
        return set;
    }
    public ResultSet getRs(){
        return rs;
    }
    
    public void setCon(Connection con){
        this.con = con;
    }
    public void setSET(Statement set){
        this.set = set;
    }
    public void setRS(ResultSet rs){
        this.rs = rs;
    }
    
    public void agregarUsuarioBD(Usuario usu)
    throws ServletException, IOException, SQLException{
        
        if(this.buscarIdUsuarioBD(usu.getNom(), usu.getApelPat_usu(), usu.getApelMat_usu()) != 0 ){
        }else{
        
            String p = "insert into Usuario (nom_usu, apelPat_usu, apelMat_usu, fechNaci_usu, domi_usu,id_tele )"
                    + "values ('"+usu.getNom()+"','"+usu.getApelPat_usu()+"','"+usu.getApelMat_usu()+"', '"
                     +usu.getfechNaci_usu()+"','"+usu.getDomi_usu()+"', "+ this.buscarIDTelefonoBD(usu.getTeleCelu())+")";

            set.executeUpdate(p);
        }
    }
    
    
    private void agregarTelefonoBD(String telePart_usu, String teleCelu_usu)
    throws ServletException, IOException, SQLException{
        
        if (this.buscarIDTelefonoBD(teleCelu_usu) != 0 | this.buscarIDTelefonoBD(telePart_usu) != 0){
            System.out.println("Ya existe este numero");
        }else{
            String q = "insert into telefono (telePart,teleCelu) values ('"+telePart_usu+"', '"+teleCelu_usu+"')";

            set.executeUpdate(q);
       }
    }
    
    //Si el numero ya existe entonces retornara un 0
    public int buscarIDTelefonoBD(String telefono)
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM telefono";

        set = con.createStatement();
        rs = set.executeQuery(g);

        //Consigo el id de la tabla telefono, si no exite entonces regresará un 0
        int id_tele = 0;
        while(rs.next()){
                if(telefono.equalsIgnoreCase(rs.getString("telePart")) == true | telefono.equalsIgnoreCase(rs.getString("teleCelu")) == true){
                    id_tele = rs.getInt("id_tele");
                    return id_tele;
                }
                
        }
        return id_tele;
                
    }
    
    public int buscarIdUsuarioBD(String nombre, String apelPat, String apelMat)
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM usuario";

        set = con.createStatement();
        rs = set.executeQuery(g);

        
        int id_usu = 0;
        while(rs.next()){
                if(nombre.equalsIgnoreCase(rs.getString("nom_usu")) == true && 
                    apelPat.equalsIgnoreCase(rs.getString("apelPat_usu")) == true &&
                    apelMat.equalsIgnoreCase(rs.getString("apelMat_usu")) == true ){
                        id_usu = rs.getInt("id_usu");
                        return id_usu;
                }
                
        }
        return id_usu;
                
    }
    
    
    
    
}
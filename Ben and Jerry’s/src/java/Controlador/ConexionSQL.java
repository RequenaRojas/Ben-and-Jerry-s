package Controlador;
import Modelo.Administrador;
import Modelo.Clasificacion;
import Modelo.Helado;
import Modelo.Oferta;
import Modelo.Usuario;

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
    
    public static Connection getConnection()
    throws ServletException, ClassNotFoundException, SQLException{
        
        String url = "jdbc:mysql://wcwimj6zu5aaddlj.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/qb9w2pzeh1k667p7";
        String userName = "c35th5wrxgf5hvqg";
        String password = "gbkg98qh51iinx0i";
        
        Connection con = null;
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, userName, password);
        System.out.println("Conexion exitosa con la BD");
        return con;
        
    }
    
    
    
    
    
    
    public void agregarAdmin(int id_usu)
    throws ServletException, IOException, SQLException{
        Administrador usu = new Administrador();
        
        int id_admi = this.buscarIdAdministradorBD(id_usu);
        
        
        if (id_admi != 0){
            System.out.println("Ya existe este numero");
        }else{
            String q = "insert into Administrador (id_usu) values ("+id_usu+") ";

            set.executeUpdate(q);
       }
        
    }
    
    public void agregarTipoDHeladoBD(String nom_tipoHela)
    throws ServletException, IOException, SQLException{
        
        if (this.buscarIdTipoHeladoBD(nom_tipoHela) == 0){
            String q = "insert into tipoHelado (nom_tipoHela) values ('"+nom_tipoHela+"') ";
            set.executeUpdate(q);
        }else{
            System.out.println("Ya existe este Tipo de Helado");
       }
    }
    
    public void agregarCantidadBD(String nom_cant, float gramos)
    throws ServletException, IOException, SQLException{
        
        if (this.buscarIdTipoHeladoBD(nom_cant) == 0){
            String q = "insert into Cantidad (nom_cant , gramos) values ('"+nom_cant+"', "+gramos+") ";
            set.executeUpdate(q);
        }else{
            System.out.println("Ya existe esta Cantidad");
       }
    }
    
    public void agregarPresentacionBD(String nom_pres)
    throws ServletException, IOException, SQLException{
        
        if (this.buscarIdTipoHeladoBD(nom_pres) == 0){
            String q = "insert into Presentacion (nom_pres) values ('"+nom_pres+"') ";
            set.executeUpdate(q);
        }else{
            System.out.println("Ya existe esta Presentacion");
       }
    }
    
    public void agregarClasificacionBD(int id_tipoHela, int id_cant, int id_pres)
    throws ServletException, IOException, SQLException{
        
        if (this.buscarIdClasificacionBD(id_tipoHela, id_cant, id_pres) == 0){
            String q = "insert into Clasificacion (id_tipoHela, id_cant, id_pres) values ("+id_tipoHela+", "+id_cant+", "+id_pres+") ";
            set.executeUpdate(q);
        }else{
            System.out.println("Ya existe esta Presentacion");
       }
    }
    
    public void agregarHeladoBS(Helado hel)
     throws ServletException, IOException, SQLException{
        //Filtro que no haya nombres repetidos
        if(this.buscarIdHeladoBD(hel.getNom_hela()) != 0 ){
        }else{
            
        //Aqui lo creo
            String p = "insert into Helado (nom_hela, id_clas, prec_hela)"
                    + "values ('"+hel.getNom_hela()+"', "+hel.getId_clas()+", "+hel.getPrec_hela()+") ";

            set.executeUpdate(p);
            
        }
        
    }
    
    public void agregarOferta(Oferta ofer)
     throws ServletException, IOException, SQLException{
        //Filtro que no haya nombres repetidos
        if(this.buscarIdOferta(ofer.getNom_ofer()) != 0 ){
        }else{
            
        //Aqui lo creo
            String p = "insert into Oferta (nom_ofer, porc_ofer, id_clas, id_admi)"
                    + "values ('"+ofer.getNom_ofer()+"', "+ofer.getPorc_ofer()+", "+ofer.getId_clas()+", "+ofer.getId_admi()+") ";

            set.executeUpdate(p);
            
        }
        
        
        
        
    }
    
    public void agregarCarrito(int id_usu, int id_hela)
     throws ServletException, IOException, SQLException{
        Usuario usu = new Usuario();
        Helado hela = new Helado();
        
        
        usu = this.buscarUsuarioBD(id_usu);
        hela = this.buscarHelado(id_hela);
        
        if(usu == null | hela == null){
            System.out.println("No se encontro el usuario o el helado");
        }else{
            
            String q = "Insert into Carrito (id_usu, id_hela) values ("+id_usu+", "+id_hela+")";
            
            set.executeUpdate(q);
       
            
        }
        
            
        
        
        
        
        
        
        
    }
    
    
    
    //Si el dato no existe en la BD entonces retornara un 0
    public int buscarIDTelefonoBD(String telefono)
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM telefono";

        set = con.createStatement();
        rs = set.executeQuery(g);

        
        int id_tele = 0;
        while(rs.next()){
                if(telefono.equalsIgnoreCase(rs.getString("telePart")) == true | telefono.equalsIgnoreCase(rs.getString("teleCelu")) == true){
                    id_tele = rs.getInt("id_tele");
                    return id_tele;
                }
                
        }
        return id_tele;
                
    }
    
    public String buscarTelePartBD(int id_tele)
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM telefono";

        set = con.createStatement();
        rs = set.executeQuery(g);
        
        String telePart = null;

        while(rs.next()){
                if(id_tele == rs.getInt("id_tele")){
                    telePart = rs.getString("telePart");
                }
        }
        return telePart;
        
    }
    
    public String buscarTeleCeluBD(int id_tele)
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM telefono";

        set = con.createStatement();
        rs = set.executeQuery(g);
        
        String telePart = null;

        while(rs.next()){
                if(id_tele == rs.getInt("id_tele")){
                    telePart = rs.getString("teleCelu");
                }
        }
        return telePart;
        
    }
    
   
    public int buscarIdAdministradorBD(int id_usu)
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM Administrador";

        set = con.createStatement();
        rs = set.executeQuery(g);

        
        int id_admi = 0;
        while(rs.next()){
                if(id_usu == rs.getInt("id_usu")){
                        id_admi = rs.getInt("id_admi");
                        
                        return id_admi;
                }
                
        }
        return id_admi;
                
    }
    
    public Administrador buscarAdministradorBD(int id_admi)
    throws ServletException, IOException, SQLException{
        Administrador admi = new Administrador();
        String g = "SELECT * FROM Administrador";

        set = con.createStatement();
        rs = set.executeQuery(g);

        
        
        while(rs.next()){
                if(id_admi == rs.getInt("id_admi")){
                        admi.setId(id_admi);
                        admi.setId_usu(rs.getInt("id_usu"));
                        return admi;
                }else{
                    admi=null;
                }
                
        }
        return admi;
        
    }
    
   
    
    public int buscarIdTipoHeladoBD(String nom_tipoHela )
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM TipoHelado";

        set = con.createStatement();
        rs = set.executeQuery(g);

        
        int id_tipoHela = 0;
        while(rs.next()){
                if(nom_tipoHela.equalsIgnoreCase(rs.getString("nom_tipoHela")) == true){
                        id_tipoHela = rs.getInt("id_tipoHela");
                        return id_tipoHela;
                }
                
        }
        return id_tipoHela;
        
    }
    
    public String buscarTipoHeladoBD(int id_tipoHela)
    throws ServletException, IOException, SQLException{
        String nom_tipoHela = null;
        String g = "SELECT * FROM TipoHelado";

        set = con.createStatement();
        rs = set.executeQuery(g);
        
        while(rs.next()){
                if(id_tipoHela == rs.getInt("id_tipoHela")){
                    nom_tipoHela = rs.getString("nom_tipoHela");
                    
                }
        }
        
        
        return nom_tipoHela;
        
    }
    
    
    
    public int buscarIdCantidadBD(String nom_cant )
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM Cantidad";

        set = con.createStatement();
        rs = set.executeQuery(g);

        
        int id_cant = 0;
        while(rs.next()){
                if(nom_cant.equalsIgnoreCase(rs.getString("tamaño")) == true){
                        id_cant = rs.getInt("id_cant");
                        return id_cant;
                }
                
        }
        return id_cant;
        
    }
    
    public String buscarCantidadBD(int id_cant)
    throws ServletException, IOException, SQLException{
        String tamaño= null;
        String g = "SELECT * FROM Cantidad";

        set = con.createStatement();
        rs = set.executeQuery(g);
        
        while(rs.next()){
                if(id_cant == rs.getInt("id_cant")){
                    tamaño = rs.getString("tamaño");
                    
                }
        }
        
        
        return tamaño;
        
    }
    
    public float buscarCantidadGramosBD(int id_cant)
    throws ServletException, IOException, SQLException{
        float gramos = 0;
        String g = "SELECT * FROM Cantidad";

        set = con.createStatement();
        rs = set.executeQuery(g);
        
        while(rs.next()){
                if(id_cant == rs.getInt("id_cant")){
                    gramos = rs.getFloat("gramos");
                    
                }
        }
        
        
        return gramos;
        
    }
    
    public int buscarIdPresentacionoBD(String nom_pres )
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM Presentacion";

        set = con.createStatement();
        rs = set.executeQuery(g);

        
        int id_pres = 0;
        while(rs.next()){
                if(nom_pres.equalsIgnoreCase(rs.getString("nom_pres")) == true){
                        id_pres = rs.getInt("id_pres");
                        return id_pres;
                }
                
        }
        return id_pres;
        
    }
    
    public String buscarPresentacionBD(int id_pres)
    throws ServletException, IOException, SQLException{
        String nom_pres = null;
        String g = "SELECT * FROM Presentacion";

        set = con.createStatement();
        rs = set.executeQuery(g);
        
        while(rs.next()){
                if(id_pres == rs.getInt("id_pres")){
                    nom_pres = rs.getString("nom_pres");
                    
                }
        }
        
        
        return nom_pres;
        
    }
    
    public int buscarIdClasificacionBD(int id_tipoHela, int id_cant, int id_pres)
    throws ServletException, IOException, SQLException{
         String g = "SELECT * FROM Clasificacion";

        set = con.createStatement();
        rs = set.executeQuery(g);

        
        int id_clas = 0;
        while(rs.next()){
            if(id_tipoHela == rs.getInt("id_tipoHela") && id_cant == rs.getInt("id_cant") && id_pres == rs.getInt("id_pres")){
                id_clas = rs.getInt("id_clas");
                return id_clas;
            }
        }
        
        return id_clas;
    }
    
    public Clasificacion buscarClasificacionBD(int id_clas)
    throws ServletException, IOException, SQLException{
        Clasificacion clas = new Clasificacion();
        
        String g = "SELECT * FROM Clasificacion";

        set = con.createStatement();
        rs = set.executeQuery(g);
        
        while(rs.next()){
                if(id_clas == rs.getInt("id_clas")){
                    clas.setId_clas(id_clas);
                    clas.setId_tipoHela(rs.getInt("id_tipoHela"));
                    clas.setId_cant(rs.getInt("id_cant"));
                    clas.setId_pres(rs.getInt("id_pres"));
                    
                }
        }
        return clas;
    }
    
    public int buscarIdHeladoBD(String nom_hela)
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM helado";

        set = con.createStatement();
        rs = set.executeQuery(g);

        
        int id_hela = 0;
        while(rs.next()){
            if(nom_hela.equalsIgnoreCase(rs.getString("nom_hela"))){
                id_hela = rs.getInt("id_clas");
                return id_hela;
            }
        }
        
        return id_hela;
    }
    
    public Helado buscarHelado(int id_hela)
    throws ServletException, IOException, SQLException{
        Helado usu = new Helado();
        
        String g = "SELECT * FROM Helado";

        set = con.createStatement();
        rs = set.executeQuery(g);
        
        while(rs.next()){
                if(id_hela == rs.getInt("id_hela")){
                    usu.setId_hela(id_hela);
                    usu.setNom_hela(rs.getString("nom_hela"));
                    usu.setId_clas(rs.getInt("id_clas"));
                    usu.setPrec_hela(rs.getFloat("prec_hela"));
                    usu.setAtributos(rs.getInt("id_clas"), this);
                    return usu;
                }else{
                    usu = null;
                }
        }
        
        
        return usu;
        
        
    }
    
    
    
    public int buscarIdOferta(String nom_ofer)
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM oferta";

        set = con.createStatement();
        rs = set.executeQuery(g);

        
        int id_hela = 0;
        while(rs.next()){
            if(nom_ofer.equalsIgnoreCase(rs.getString("nom_ofer"))){
                id_hela = rs.getInt("id_ofer");
                return id_hela;
            }
        }
        
        return id_hela;
    }
    
    public Oferta buscarOferta(int id_ofer)
     throws ServletException, IOException, SQLException{
        Oferta ofer = new Oferta();
        String g = "SELECT * FROM Oferta";

        set = con.createStatement();
        rs = set.executeQuery(g);
        
        while(rs.next()){
                if(id_ofer == rs.getInt("id_ofer")){
                    ofer.setId_ofer(id_ofer);
                    ofer.setNom_ofer(rs.getString("nom_ofer"));
                    ofer.setPor_ofer(rs.getInt("porc_ofer"));
                    ofer.setId_clas(rs.getInt("id_clas"));
                    ofer.setId_admi(rs.getInt("id_admi"));
                    return ofer;
                }else{
                    ofer = null;
                }
        }
        
        
        return ofer;
    }
    
    public int[] buscarCarrito(int id_usu)
     throws ServletException, IOException, SQLException{
        
        //Tendre que sacar la cantidad de veces que el usuario esta en la tabla (i) para establecer un limite de la matriz
        String g = "SELECT * FROM carrito";

        set = con.createStatement();
        rs = set.executeQuery(g);
        int i = 0;
        
        while(rs.next()){
                if(id_usu == rs.getInt("id_usu")){
                    
                    i++;
                }
        }
        
        
        //Asigno el tamaño de lista con i
        int id_helados[] = new int[i];

        set = con.createStatement();
        rs = set.executeQuery(g);
        //Reinicio la cuenta de i
        i = 0;
        
        while(rs.next()){
                if(id_usu == rs.getInt("id_usu")){
                    id_helados[i] = rs.getInt("id_hela");
                    i++;
                }
        }
        
        
        
        return id_helados;
        
        
    }
    
    
    //Editar Datos de una fila
    
    public void editarHelado(int id_hela, Helado usu)
     throws ServletException, IOException, SQLException{
        
        
        String q = "update Helado set nom_hela='"+usu.nom_hela+"',id_clas = "+usu.getId_clas()+", prec_hela = "+usu.getPrec_hela()+"   where id_hela = "+id_hela+"  ";
        set.executeUpdate(q);
    }
    
    public void editarOferta(int id_ofer, Oferta ofer)
     throws ServletException, IOException, SQLException{
        
        String q = "update Oferta set nom_ofer='"+ofer.getNom_ofer()+"',por_ofer = "+ofer.getPorc_ofer()+", id_clas = "+ofer.getId_clas()+", id_admi = "+ofer.getId_admi()+"    where id_hela = "+id_ofer+"  ";
        set.executeUpdate(q);
    }
   
    //Eliminar Datos
    
    public void eliminarHelado(int id_hela)
    throws ServletException, IOException, SQLException{
        
        
        
        String q = "delete from Helado where id_hela = "+id_hela;
                
        set.executeUpdate(q);
        
    }
    
    public void eliminarOferta(int id_ofer)
    throws ServletException, IOException, SQLException{
        
        String q = "delete from Oferta where id_hela = "+id_ofer;
                
        set.executeUpdate(q);
        
    }
    
    public void eliminarHeladoCarrito(int id_usu, int id_hela)
     throws ServletException, IOException, SQLException{
        
        String q = "delete from Oferta where id_hela = "+id_hela+", id_usu = "+id_usu+"    ";
                
        set.executeUpdate(q);
        
        
        
    }
    
    
    
    
    
}
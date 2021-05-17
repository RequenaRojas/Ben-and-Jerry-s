import Controlador.ActTelefono;
import Controlador.ActUsuario;
import Controlador.ConexionSQL;
import Modelo.*;
import Servlets.*;

import Modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
//Encargada de poder reaalizar las sentencias sql, creat, insert, delete, drop, update
import java.sql.Statement;
//Encrgada de poder realizar las consultas a la BD
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
public class PruebasConexionSQL {

    public static void main(String[] args)
    throws ServletException, IOException, SQLException, ClassNotFoundException {
     
        
        /*
        //###########Prueba de ActTelefono###########
        
        //Funciona d=====(￣▽￣*)b
        
        Telefono tel = new Telefono();
        tel.setTeleCelu("55722437");
        tel.setTelePart("57852190");
        
        tel.setId(ActTelefono.getID(tel));
        System.out.println(tel.getId());
        */
        ///*
        //###########Prueba Servlet guardarUsuario.java#######################
        
        String nom_usu = "nom_usu";
        String apelPat_usu = "apelPat";
        String apelMat_usu = "apelMat";
        String fechNaci_usu = "fechNac";
        String domi_usu = "domi_usu";
        String telePart = "telaPart";
        String teleCelu = "teleCelu";

        Usuario usu = new Usuario();

        usu.setNom(nom_usu);
        usu.setApelPat_usu(apelPat_usu);
        usu.setApelMat_usu(apelMat_usu);
        usu.setFechNaci_usu(fechNaci_usu);
        usu.setDomi_usu(domi_usu);

        Telefono tel = new Telefono();

        tel.setTelePart(telePart);
        tel.setTeleCelu(teleCelu);


        ActTelefono.RegiTelefono(tel);
        tel.setId(ActTelefono.getID(tel));

        usu.setId_tele(tel.getId());

        ActUsuario.RegiUsuario(usu);
        //*/
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    /*    
        try{
            sql.init();
            System.out.println("Se ha conectado con la base de datos");
            
            //############ Usuario ###############
            String nom_usu = "Roberto";
            String apelPat_usu = "Ramirez";
            String apelMat_usu = "Moza";
            String fechNaci_usu = "2002-04-03";
            String domi_usu = "Cosa larga relacionada con un domicilio";
            String telePart = "5518722437";
            String teleCelu = "57852190"; 
            Usuario usu = new Usuario();
            usu.Usuario(nom_usu, apelPat_usu, apelMat_usu, fechNaci_usu, domi_usu, telePart, teleCelu);
            
            try{
                sql.agregarUsuarioBD(usu);
                System.out.println("Se ha registrado el Usuario Correctamente");
            }catch(Exception e){
                System.out.println("No se ha registrdo el Usuario");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());   
            }


        }catch(Exception e){
            System.out.println("No se ha conectado con la base de datos");
            System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
        } 
    
    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
}

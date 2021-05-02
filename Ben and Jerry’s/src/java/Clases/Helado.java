package Clases;


import java.io.IOException;
import java.util.Date;


//Encargada de poder realizar la conexión con la BD
import java.sql.Connection;
import java.sql.DriverManager;
//Encargada de poder reaalizar las sentencias sql, creat, insert, delete, drop, update
import java.sql.Statement;
//Encrgada de poder realizar las consultas a la BD
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;




public class Helado {
    int id_hela;
    String nom_hela;
    
    int id_clas;
    
    String nom_tipoHela;
    int id_tipoHela;
    
    String tamaño;
    float gramos;
    int id_cant;
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankaccountmanagmentsystem;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
public class test { 
   public static void main(String[] args) { 
     String connectionURL= "jdbc:derby://localhost:1527/bue"; 
//ConnectionURL, username and password should be specified in getConnection() 
try { 
 Connection conn = DriverManager.getConnection(
    "jdbc:derby://localhost:1527/new;create=true",
    "new1",
    "123"
);
 Statement st = conn.createStatement(); 
String sql = "INSERT INTO STUDENT (ID, NAME) VALUES (5, 'demo3')"; 
st.executeUpdate(sql); 
st.close(); conn.close(); }  
catch (SQLException ex) {System.out.println("Connect failed ! ");}    
} 
}

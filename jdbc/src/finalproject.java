import java.util.*;
import java.sql.*;
import java.util.Scanner;
import com.mysql.cj.protocol.Resultset;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.*;
public class finalproject {

    /// I AM CHANGING UI SETTINGS FONT USING UI MANAGER IN STATIC BLOCK ()
    static{
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
            "Arial",Font.BOLD,19))); 
    } 

    static String dbname ;
    static String tablename ;
    public static void main(String[] args) throws Exception{
        System.out.println("\n\n*******  FIRST PROJECT ON JDBC + JAVA + MYSQL BY VAIBHAV AGGRWAL  *******\n\n");
        
        JOptionPane.showMessageDialog(null, "FIRST PROJECT ON JDBC + JAVA + MYSQL BY VAIBHAV AGGRWAL");
        int choice;
        int condition=1;
        while(condition==1){
            JOptionPane.showMessageDialog(null,
            "ENTER 1 TO CREATE DATABASE \n\n"+
            "ENTER 2 TO CREATE TABLE\n\n"+ 
            "ENTER 3 TO INSERT DATA\n\n"+
            "ENTER 4 TO DISPLAY DATA\n "+
            "          \nCHOOSE ABOVE CHOICES \n");
                String abcd =JOptionPane.showInputDialog(null,"ENTER THE CHOICE ");   ///** */
                choice=Integer.parseInt(abcd);    ///**** */
            Scanner sc=new Scanner(System.in);
            switch(choice){
                case 1:createdatabase();
                break;
                case 2:createtable();
                break;
                case 3:insertion();
                break;
                case 4:display();
                break;
            }
            String abcdf=JOptionPane.showInputDialog(null, "ENTER 1 OR 0 TO CONTINUE :");
            condition=Integer.parseInt(abcdf);
        }
    }
    public static void createdatabase(){

        dbname =JOptionPane.showInputDialog(null,"ENTER DATABASE NAME");   ///**** */
        Scanner sc=new Scanner(System.in);
            String username =JOptionPane.showInputDialog(null,"ENTER USERNAME OF MYSQL"); ///*** */
            String password =JOptionPane.showInputDialog(null,"ENTER PASSWORD OF MYSQL");///** */
        String url="jdbc:mysql://localhost:3306/";

        try{
            Connection con=DriverManager.getConnection(url, username, password);
            Statement st=con.createStatement();
            st.execute("create database "+dbname);         
            JOptionPane.showMessageDialog(null, "DATABASE SUCCESSFULY CREATED");
            st.close();
            con.close();
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e,"----- WARNING BRO EXCEPTION HAI ---",JOptionPane.WARNING_MESSAGE);
        }

    }
    public static void createtable(){

            tablename =JOptionPane.showInputDialog(null,"ENTER TABLE NAME");   ///***** */
        Scanner sc=new Scanner(System.in);
        String username="root";
        String password="abcde";
        String url="jdbc:mysql://localhost:3306/";
        try{

            Connection con=DriverManager.getConnection(url, username, password);
            Statement st=con.createStatement();
            st.execute("use "+dbname);
            st.execute("create table "+tablename +"(st_rollno int(4),st_name varchar(20),st_marks int(8))");         
            JOptionPane.showMessageDialog(null, "TABLE CREATED SUCCESSFULLY");
            st.close();
            con.close();
        }
        catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e,"----- WARNING BRO EXCEPTION HAI ---",JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void insertion(){

        Scanner sc=new Scanner(System.in);
            String abcd =JOptionPane.showInputDialog(null,"ENTER ROLLNO ");   ///**** */
            int rollno=Integer.parseInt(abcd); ///****** */

            String abcdefg =JOptionPane.showInputDialog(null,"ENTER NAME"); ///** */
            String name=abcdefg;

            String abcdef =JOptionPane.showInputDialog(null,"ENTER TOTAL MARKS"); ///*** */
            int marks=Integer.parseInt(abcdef); ///**** */

        String username="root";
        String password="abcde";
        String url="jdbc:mysql://localhost:3306/";
        try{

            Connection con=DriverManager.getConnection(url, username, password);
            Statement st=con.createStatement();
            st.execute("use "+dbname);
            PreparedStatement stt=con.prepareStatement("insert into "+tablename+"(st_rollno,st_name,st_marks)values(?,?,?)");
            stt.setInt(1, rollno);
            stt.setString(2,name);
            stt.setInt(3, marks);
            stt.execute();     
            JOptionPane.showMessageDialog(null, "DATA INSERTED SUCCESSFULLY");
            st.close();
            con.close();
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e,"----- WARNING BRO EXCEPTION HAI ---",JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void display(){

        String username="root";
        String password="abcde";
        String url="jdbc:mysql://localhost:3306/";
        try{

            Connection con=DriverManager.getConnection(url, username, password);
            Statement st=con.createStatement();
            st.execute("use "+dbname);
            ResultSet rs=st.executeQuery("select * from "+tablename);
            int i=1;
            while(rs.next()){
                JOptionPane.showMessageDialog(null,"   Record "+i+":\n"+
                "Rollno  "+"Name  "+"Marks  \n"+
                rs.getInt(1)+"        "+rs.getString(2)+"        "+rs.getInt(3)+"\n");
                i++;
            }
            JOptionPane.showMessageDialog(null, "DATA PRINTED SUCCESSFULLY");
            st.close();
            con.close();

        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e,"----- WARNING BRO EXCEPTION HAI ---",JOptionPane.WARNING_MESSAGE);

        }
    }
}

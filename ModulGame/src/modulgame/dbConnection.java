/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulgame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Fauzan
 */
public class dbConnection {
    public static Connection con;
    public static Statement stm;
    
    public void connect(){//untuk membuka koneksi ke database
        try {
            String url ="jdbc:mysql://localhost/db_gamepbo";
            String user="root";
            String pass="";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pass);
            stm = con.createStatement();
            System.out.println("koneksi berhasil;");
        } catch (Exception e) {
            System.err.println("koneksi gagal" +e.getMessage());
        }
    }
    
    public DefaultTableModel readTable(){
        
        DefaultTableModel dataTabel = null;
        try{
            Object[] column = {"No", "Username", "Score", "Waktu", "Score Akhir"};
            connect();
            dataTabel = new DefaultTableModel(null, column);
            String sql = "Select * from highscore order by ScoreAkhir desc";
            ResultSet res = stm.executeQuery(sql);
            
            int no = 1;
            while(res.next()){
                Object[] hasil = new Object[5];
                hasil[0] = no;
                hasil[1] = res.getString("Username");
                hasil[2] = res.getString("Score");
                hasil[3] = res.getString("Waktu");
                hasil[4] = res.getString("ScoreAkhir");
                no++;
                dataTabel.addRow(hasil);
            }
        }catch(Exception e){
            System.err.println("Read gagal " +e.getMessage());
        }
        
        return dataTabel;
    }
    
    public void insertData(String username, int score, int time){
        try{
            connect();
            String sql = "SELECT * from highscore where Username ='"+username+"' limit 1";
            ResultSet res = stm.executeQuery(sql);
//            System.out.println(res);
            sql = "Insert into highscore(username,score,waktu,scoreakhir) values('" + username +"',"+Integer.toString(score)+","+ Integer.toString(time)+ "," + Integer.toString(score + time) + ")";
            while(res.next()){
                int hasil = res.getInt("ScoreAkhir");
//                System.out.println(score + time);
//                System.out.println(hasil);
                if(hasil < score + time){
                    sql = "UPDATE highscore SET scoreakhir = GREATEST(scoreakhir," + Integer.toString(score+time) +")" + ",Score =" + Integer.toString(score) + ",Waktu =" + Integer.toString(time) + " WHERE username = '" +username+"'";
                }
                else{
                    sql = "";
                }
//                System.out.print(sql);
            }
            stm.execute(sql);
        }
        catch(Exception e){
            System.err.println("Gagal memasukkan data : " + e.getMessage());
        }
    }
    
}

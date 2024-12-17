

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionUtil;

/**
 * JDBC stands for Java DataBase Connectivity.  It is utilized to connect our java code with a database.
 * JDBC will allow us to execute SQL statements from java and retrieve the result set of that query to be utilized in java
 *
 * JDBC datatypes to know:
 *  - Connection: Creates an active connection to the database.
 *  - Statement: An object that represents a SQL statement to be executed.
 *  - ResultSet: An object that represents the virtual table return from a query (Only needed for executing DQL statements)
 *
 * Background:
 * Assume we have the following table:
 *      songs table
 *      |   id  |      title        |        artist         |
 *      -----------------------------------------------------
 *      |1      |'Let it be'        |'Beatles'              |
 *      |2      |'Hotel California' |'Eagles'               |
 *      |3      |'Kashmir'          |'Led Zeppelin'         |
 *
 * Assignment: Write JDBC logic in the methods below to achieve the following
 *                  - create a new song in our songs database table
 *                  - retrieve all songs from our database table
 *
 * If this is your first time working with JDBC, I recommend reading through the JDBCWalkthrough file that displays how to use JDBC for a similar scenario.
 */
public class Lab {

    public void createSong(Song song)  {
        try{
            Connection con = ConnectionUtil.getConnection();
    
            String s = "INSERT INTO songs (id, title, artist) VALUES ('" + song.getId() + "', '" + song.gettitle() + "', '" + song.getArtist() +"');";
            
            Statement st = con.createStatement();
            st.executeUpdate(s);
        } 
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public List<Song> getAllSongs(){
        List<Song> songs = new ArrayList<>();

        try {
            Connection con2 = ConnectionUtil.getConnection();

            String s2 = "SELECT * FROM songs;";

            Statement st2 = con2.createStatement();
            ResultSet rs = st2.executeQuery(s2);

            while (rs.next()) {
                songs.add(new Song(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songs;
    }
}

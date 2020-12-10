import java.sql.*;

public abstract class AccessDatabase
{
    protected static Connection con= null;  //statement >> static //PreparedStatement >> dynamic
    protected static Statement st = null;
    protected static PreparedStatement preSt = null;
    protected static ResultSet rs = null;

    static Connection getAccess() throws SQLException, ClassNotFoundException    //, ClassNotFoundException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        if(con==null)
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cafeteria_controlling_system?", "root", "");
        }
        return con;
    }
    static void close()
    {
        try
        {
            con.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Class- "+AccessDatabase.class.getName() + "\nExp-" + ex);
        }
    }

}

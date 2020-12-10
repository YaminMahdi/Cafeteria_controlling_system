import java.sql.SQLException;
import java.util.Scanner;

public class Login extends AccessDatabase
{
    private static String pw, npw;
    private static boolean isOK = false;
    private static Scanner sc = new Scanner(System.in);

    static boolean checkPW()
    {
        System.out.print("\nEnter The Password: ");
        pw = sc.next();

        try
        {
            con = getAccess();
            st = con.createStatement();
            rs  = st.executeQuery("SELECT * FROM login");
            if (rs.next())
            {
                if(rs.getString(1).equals(pw))
                {
                    System.out.println("\n\tAccess Granted.\n");
                    isOK = true;
                }
                else
                    System.out.println("\n\tAccess Denied.\n");
            }
            else
            {
                System.out.println("\n\tAccess Denied.\n");
            }

        } catch (SQLException | ClassNotFoundException throwables)
        {
            throwables.printStackTrace();
        }
        return isOK;
    }

    static void changePW()
    {
        System.out.print("\nEnter New Password: ");
        npw = sc.next();

        try
        {
            con = getAccess();
            preSt = con.prepareStatement("UPDATE login SET Password='"+npw+"' WHERE Password='"+pw+"'");
            preSt.execute();
            //st = con.createStatement();
            //rs = st.executeQuery("UPDATE login SET Password='"+npw+"' WHERE Password='"+pw+"'");
            //UPDATE login SET Password="yk000" WHERE Password="yk00"
            System.out.println("\n\tPassword Changed.\n");

        } catch (SQLException | ClassNotFoundException throwables)
        {
            throwables.printStackTrace();
        }
    }

}

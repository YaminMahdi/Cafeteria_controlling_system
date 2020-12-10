import java.sql.SQLException;
import java.util.Scanner;

public class CustomersReview extends AccessDatabase
{
    private static String name;
    private static String review;
    private static Scanner sc = new Scanner(System.in);

    static void addReview()
    {
        System.out.println("\n[*********************]");
        System.out.println("[ A. Add Review       ]");
        System.out.println("[*********************]");
        System.out.println("[ G. Go Back          ]");
        System.out.println("^*********************^ ");
        boolean run=false;
        while (true)
        {
            System.out.print("Type your option [A,G] then press ENTER: ");
            char ch = sc.next().charAt(0);
            sc.nextLine();
            if (ch == 'A' || ch == 'a')
            {
                run = true;
                break;
            } else if (ch == 'G' || ch == 'g')
                break;
            else
                System.out.println("\n\tWrong Input.\n");
        }
        if (run)
        {
            System.out.print("\nTo give a review please enter your name: ");
            name = sc.nextLine();
            System.out.print("Write down your comment: ");
            review = sc.nextLine();

            try
            {
                con = getAccess();
                preSt = con.prepareStatement("INSERT INTO review (CustomerName, Review) VALUES(?,?)");
                preSt.setString(1, name);
                preSt.setString(2, review);
                preSt.execute();

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("\n Your review has been added.");
            display();
        }
    }
    static void display()
    {
        try
        {
            con = getAccess();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM review");
            String leftAlignFormat = "| %-15s | %-59s |\n";
            String categoryFormat = "| %-58s |\n";
            System.out.format("+-------------------------------------------------------------------------------+\n");
            System.out.format(categoryFormat, "\t\t\tAll Reviews From Customers ");
            System.out.format("+-------------------------------------------------------------------------------+\n");
            System.out.format("| Customer Names  | There Reviews                                               |\n");
            System.out.format("+-----------------+-------------------------------------------------------------+\n");
            while (rs.next())
            {
                System.out.format(leftAlignFormat, rs.getString(2) , rs.getString(3));
                System.out.format("+-----------------+-------------------------------------------------------------+\n");
            }
        } catch (SQLException | ClassNotFoundException throwables)
        {
            throwables.printStackTrace();
        }
        System.out.println(" Press ENTER to continue.");
        sc.nextLine();
        addReview();
    }

}

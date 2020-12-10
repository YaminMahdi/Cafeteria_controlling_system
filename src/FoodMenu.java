import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FoodMenu extends AccessDatabase
{
    //static int itemNo;
    private static String category;
    private static String foodName;
    private static int price;
    private static final Scanner sc = new Scanner(System.in);

    private static String changeCategory()
    {
        System.out.println("\nSelect Food Category.\n");
        System.out.println("[*********************]");
        System.out.println("[ B. Burger           ]");
        System.out.println("[ P. Pizza            ]");
        System.out.println("[ N. Noodles & Pasta  ]");
        System.out.println("[ S. Sandwich         ]");
        System.out.println("[ F. Fry Items        ]");
        System.out.println("[ C. Cakes            ]");
        System.out.println("[ R. Refreshment      ]");
        System.out.println("^*********************^");
        System.out.print("Type your option [B,P,N,S,F,C,R] then press ENTER: ");
        char ch = sc.next().charAt(0);
        sc.nextLine();
        if(ch == 'B'||ch == 'b')
            category="Burger";
        else if(ch == 'P'||ch == 'p')
            category="Pizza";
        else if(ch == 'N'||ch == 'n')
            category="Noodles & Pasta";
        else if(ch == 'S'||ch == 's')
            category="Sandwich";
        else if(ch == 'F'||ch == 'f')
            category="Fry Items";
        else if(ch == 'C'||ch == 'c')
            category="Cakes";
        else if(ch == 'R'||ch == 'r')
            category="Refreshment";
        else
        {
            category = null;
            System.out.println("\n\tWrong Input.");
        }
        return category;
    }
    static void addFood()
    {
        category=changeCategory();
        while (category==null)
        {
            category = changeCategory();
        }
        System.out.println( category + " Selected.\n");
        boolean run = true;
        while (run)
        {
            System.out.print("Enter Food Name : ");
            foodName = sc.nextLine();
            System.out.print("Enter Food Price: ");
            price = sc.nextInt();
            sc.nextLine();
            try {
                con = getAccess();
                preSt = con.prepareStatement("INSERT INTO menu(Category, FoodName, Price) VALUES (?,?,?) ");
                preSt.setString(1, category);
                preSt.setString(2, foodName);
                preSt.setInt(3, price);
                preSt.execute();
                System.out.println("\n" + foodName + " added, which price is " + price + ".");
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            System.out.println("\n+---------------------+");
            System.out.println("|A. Add More Food     |");
            System.out.println("|C. Change Category   |");
            System.out.println("|G. Go back           |");
            System.out.println("+---------------------+");
            while (true)
            {
                System.out.print("Type your option [A,C,G] then press ENTER: ");
                char ch = sc.next().charAt(0);
                sc.nextLine();
                if (ch == 'A' || ch == 'a')
                {
                    run = true;
                    break;
                } else if (ch == 'C' || ch == 'c')
                {
                    changeCategory();
                    run = true;
                    break;
                } else if (ch == 'G' || ch == 'g')
                {
                    run = false;
                    break;
                }else
                    System.out.println("\n\tWrong Input.\n");
            }
        }
        //close();

    }
    void removeFood()
    {

    }
    static void display()
    {
        System.out.println("\n*********************************************************************************");
        System.out.format("[ %-77s ]\n", "                            Menu Of Coder Cafeteria");
        System.out.println("*********************************************************************************\n");
        try
        {
            con  = getAccess();
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery("SELECT * FROM menu");
            rs.next();
            category = rs.getString(2);
            String leftAlignFormat = "| %-8d | %-30s | %-9s |\n";
            String categoryFormat = "| %-53s |\n";
            System.out.format("+-------------------------------------------------------+\n");
            System.out.format(categoryFormat, "Food Type: " + category );
            System.out.format("+-------------------------------------------------------+\n");
            System.out.format("| Item No. | Food Name                      | Price     |\n");
            System.out.format("+----------+--------------------------------+-----------+\n");
            rs.beforeFirst();
            while (rs.next())
            {
                //System.out.println((category.equals(rs.getString(2))));
                if(!(category.equals(rs.getString(2))))
                {
                    category = rs.getString(2);
                    System.out.format("+-------------------------------------------------------+\n");
                    System.out.format("\n\n+-------------------------------------------------------+\n");
                    System.out.format(categoryFormat, "Food Type: " + category );
                    System.out.format("+-------------------------------------------------------+\n");
                    System.out.format("| Item No. | Food Name                      | Price     |\n");
                    System.out.format("+----------+--------------------------------+-----------+\n");
                }
                System.out.format(leftAlignFormat, rs.getInt(1), rs.getString(3), rs.getInt(4) );

            }
            System.out.format("+-------------------------------------------------------+\n");
        }
        catch (SQLException | ClassNotFoundException throwables)
        {
            throwables.printStackTrace();
        }
        //close();
    }

}

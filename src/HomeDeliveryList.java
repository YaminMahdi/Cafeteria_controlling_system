import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HomeDeliveryList extends AccessDatabase
{
    private static String date;
    private static String name;
    private static int[] itemNo = new int[30];
    private static int[] quantity = new int[30];
    private static String location;
    private static int totalPrice = 0;
    private static Scanner sc = new Scanner(System.in);

    static void newOrder()
    {
        String itemNo_Quantity ="";
        System.out.print("Enter Customer Name       : ");
        name = sc.nextLine();
        System.out.print("Where to Delivery?        : ");
        location = sc.nextLine();
        boolean run = true, run2 =true;
        //System.out.print("Use (,) for ordering more than One Item.\nEx. 02-1,15-2 (It means,\nItemNo 2, 1 pcs and\nItemNo 15, 2 pcs.");
        int i;
        for(i=0;run;i++)
        {
            System.out.print("Enter Item No. of Menu : ");
            itemNo[i] = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Quantity         : ");
            quantity[i] = sc.nextInt();
            sc.nextLine();
            System.out.println("Item Added to Home Delivery List.");
            System.out.println("\n  [*********************]");
            System.out.println("  [ O. Order More       ]");
            System.out.println("  [*********************]");
            System.out.println("  [ C. Confirm Order    ]");
            System.out.println("  [*********************]");
            System.out.println("  [ G. Go Back          ]");
            System.out.println("  ^*********************^\n");
            while (true)
            {
                System.out.print("Type your option [O,C,G] then press ENTER: ");
                char ch = sc.next().charAt(0);
                //sc.nextLine();
                if (ch == 'O' || ch == 'o')
                {
                    run = true;
                    break;
                } else if (ch == 'C' || ch == 'c')
                {
                    run = false;
                    System.out.println("Now go and cook.");
                    break;
                } else if (ch == 'G' || ch == 'g')
                {
                    run = false;
                    run2 = false;
                    break;
                }else
                    System.out.println("\n\tWrong Input.\n");
            }
        }
        for(int j=0;j<i;j++)
        {
            if (itemNo[j]<10)
                itemNo_Quantity += "0"+String.valueOf(itemNo[j])+"-"+String.valueOf(quantity[j])+",";
            else
                itemNo_Quantity += String.valueOf(itemNo[j])+"-"+String.valueOf(quantity[j])+",";
        }
        if(run2)
        {
            try {
                con = getAccess();
                st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                rs = st.executeQuery("SELECT DATE(NOW())");
                if(rs.next())
                    date = rs.getString(1);
                //print last data
                rs = st.executeQuery("SELECT * FROM orderlist");
                rs.last();
                String leftAlignFormat = "| %-12s | %-13s | %-20s | %-9s | %-11s |\n";
                String leftAlignFormat2 = "| %-51s | %-9s   %-11s |\n";
                String categoryFormat = "| %-77s |\n";
                System.out.format("+-------------------------------------------------------------------------------+\n");
                System.out.format(categoryFormat, "Today's Date: " + date );
                System.out.format("+-------------------------------------------------------------------------------+\n");
                System.out.format("| Name         | Location      | Food Names           | Quantity  | Price       |\n");
                System.out.format("+--------------+---------------+----------------------+-----------+-------------+\n");
                System.out.format(leftAlignFormat, name, location,"","","");
                for (i =0;i<itemNo_Quantity.length();i+=5)
                {
                    String x = Character.toString(itemNo_Quantity.charAt(i))+Character.toString(itemNo_Quantity.charAt(i+1));
                    itemNo[i]= Integer.parseInt(x);
                    Connection con1 =getAccess();
                    Statement st1 = con1.createStatement();
                    ResultSet rs1 = st1.executeQuery("SELECT * FROM menu WHERE ItemNo='"+itemNo[i]+"'");
                    rs1.next();
                    quantity[i]=Integer.parseInt(Character.toString(itemNo_Quantity.charAt(i+3)));
                    int p= rs1.getInt(4)*quantity[i];
                    totalPrice+=p;
                    System.out.format(leftAlignFormat, "", "", rs1.getString(3), quantity[i]+" pcs", p);
                }
                System.out.format("+-------------------------------------------------------------------------------+\n");
                System.out.format(leftAlignFormat2, "", "Total :", totalPrice);
                System.out.format("+-------------------------------------------------------------------------------+\n");
                /////
                preSt = con.prepareStatement("INSERT INTO homedeliverylist (date, CustomerName, ItemNo_Quantity, TotalPrice, Location) VALUES (?,?,?,?,?)");
                preSt.setString(1, date);
                preSt.setString(2, name);
                preSt.setString(3, itemNo_Quantity);
                preSt.setInt(4, totalPrice);
                preSt.setString(5, location);
                preSt.execute();
                //close();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        System.out.print("\nPress ENTER to continue.");
        sc.nextLine();
    }
    static void displayAll()
    {
        String itemNo_Quantity ="";
        System.out.println("\n*********************************************************************************");
        System.out.format("[ %-77s ]\n", "                          All Past Home Delivery List");
        System.out.println("*********************************************************************************\n");
        try
        {
            con  = getAccess();
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery("SELECT * FROM homedeliverylist");
            if(rs.next())
                date = rs.getString(1);
            String leftAlignFormat = "| %-12s | %-13s | %-20s | %-9s | %-11s |\n";
            String leftAlignFormat2 = "| %-51s | %-9s   %-11s |\n";
            String categoryFormat = "| %-77s |\n";
            System.out.format("+-------------------------------------------------------------------------------+\n");
            System.out.format(categoryFormat, "Date: " + date );
            System.out.format("+-------------------------------------------------------------------------------+\n");
            System.out.format("| Name         | Location      | Food Names           | Quantity  | Price       |\n");
            System.out.format("+--------------+---------------+----------------------+-----------+-------------+\n");
            rs.beforeFirst();
            while (rs.next())
            {
                if(!(date.equals(rs.getString(1))))
                {
                    date = rs.getString(1);
                    System.out.format("\n\n+-------------------------------------------------------------------------------+\n");
                    System.out.format(categoryFormat, "Date: " + date );
                    System.out.format("+-------------------------------------------------------------------------------+\n");
                    System.out.format("| Name         | Location      | Food Names           | Quantity  | Price       |\n");
                    System.out.format("+--------------+---------------+----------------------+-----------+-------------+\n");
                }
                System.out.format(leftAlignFormat, rs.getString(3), rs.getString(6),"","","");
                itemNo_Quantity = rs.getString(4);
                int p1=0;
                for (int i =0;i<itemNo_Quantity.length();i+=5)
                {
                    String x = Character.toString(itemNo_Quantity.charAt(i))+Character.toString(itemNo_Quantity.charAt(i+1));
                    itemNo[i]= Integer.parseInt(x);
                    Connection con1 =getAccess();
                    Statement st1 = con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs1;
                    rs1 = st1.executeQuery("SELECT * FROM menu WHERE ItemNo='"+itemNo[i]+"'");
                    rs1.next();
                    quantity[i]=Integer.parseInt(Character.toString(itemNo_Quantity.charAt(i+3)));
                    int p= rs1.getInt(4)*quantity[i];
                    p1+=p;
                    System.out.format(leftAlignFormat, "", "", rs1.getString(3), quantity[i]+" pcs", p);
                }
                System.out.format("+-------------------------------------------------------------------------------+\n");
                System.out.format(leftAlignFormat2, "", "Total :", p1);
                System.out.format("+-------------------------------------------------------------------------------+\n");
            }
        } catch (SQLException | ClassNotFoundException throwables)
        {
            throwables.printStackTrace();
        }

    }

    static void displayCurrent()
    {
        String itemNo_Quantity ="";
        System.out.println("\n*********************************************************************************");
        System.out.format("[ %-77s ]\n", "                          Today's Home Delivery List");
        System.out.println("*********************************************************************************\n");
        try
        {
            con  = getAccess();
            st = con.createStatement();

            rs = st.executeQuery("SELECT DATE(NOW())");
            rs.next();
            String crDate = rs.getString(1);

            rs = st.executeQuery("SELECT * FROM homedeliverylist WHERE date='"+crDate+"'");

            String leftAlignFormat = "| %-12s | %-13s | %-20s | %-9s | %-11s |\n";
            String leftAlignFormat2 = "| %-51s | %-9s   %-11s |\n";
            String categoryFormat = "| %-77s |\n";
            System.out.format("+-------------------------------------------------------------------------------+\n");
            System.out.format(categoryFormat, "Today's Date: " + crDate );
            System.out.format("+-------------------------------------------------------------------------------+\n");
            System.out.format("| Name         | Location      | Food Names           | Quantity  | Price       |\n");
            System.out.format("+--------------+---------------+----------------------+-----------+-------------+\n");
            //rs.beforeFirst();
            while (rs.next())
            {

                System.out.format(leftAlignFormat, rs.getString(3), rs.getString(6), "", "", "");
                itemNo_Quantity = rs.getString(4);
                int p1 = 0;
                for (int i = 0; i < itemNo_Quantity.length(); i += 5) {
                    String x = Character.toString(itemNo_Quantity.charAt(i)) + Character.toString(itemNo_Quantity.charAt(i + 1));
                    itemNo[i] = Integer.parseInt(x);
                    Connection con1 = getAccess();
                    Statement st1 = con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs1;
                    rs1 = st1.executeQuery("SELECT * FROM menu WHERE ItemNo='" + itemNo[i] + "'");
                    rs1.next();
                    quantity[i] = Integer.parseInt(Character.toString(itemNo_Quantity.charAt(i + 3)));
                    int p = rs1.getInt(4) * quantity[i];
                    System.out.format(leftAlignFormat, "", "", rs1.getString(3), quantity[i] + " pcs", p);
                }
                System.out.format("+-------------------------------------------------------------------------------+\n");
                System.out.format(leftAlignFormat2, "", "Total :", rs.getString(5));
                System.out.format("+-------------------------------------------------------------------------------+\n");
            }
            /*else
            {
                System.out.format("+------------------------------+\n");
                System.out.format("| No Order Had Placed Today.   |\n");
                System.out.format("+------------------------------+\n");
            }*/

        } catch (SQLException | ClassNotFoundException throwables)
        {
            throwables.printStackTrace();
        }

    }
}

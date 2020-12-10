import java.util.Scanner;

public class MainApp
{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {

        while (true)
        {
            System.out.println("\n*********************************************************************************");
            System.out.format("[ %-77s ]\n", "");
            System.out.format("[ %-77s ]\n", "                         Welcome to Coder Cafeteria");
            System.out.format("[ %-77s ]\n", "");
            System.out.println("*********************************************************************************");
            System.out.println("\n\n  [************************]");
            System.out.println("  [ M. View Menu           ]");
            System.out.println("  [************************]");
            System.out.println("  [ O. Order Food          ]");
            System.out.println("  [************************]");
            System.out.println("  [ R. Customer Reviews    ]");
            System.out.println("  [************************]");
            System.out.println("  [ L. Manager Login       ]");
            System.out.println("  ^************************^\n");
            while (true)
            {
                System.out.print("Type your option [M,O,R,L] then press ENTER: ");
                char ch = sc.next().charAt(0);
                if (ch == 'M' || ch == 'm')
                {
                    FoodMenu.display();
                    System.out.println("\n  [*********************]");
                    System.out.println("  [ O. Order Food       ]");
                    System.out.println("  [*********************]");
                    System.out.println("  [ G. Go Back          ]");
                    System.out.println("  ^*********************^\n");
                    while (true)
                    {
                        System.out.print("Type your option [O,G] then press ENTER: ");
                        char ch1 = sc.next().charAt(0);
                        sc.nextLine();
                        if (ch1 == 'O' || ch1 == 'o') {
                            OrderList.order();
                            break;
                        } else if (ch1 == 'G' || ch1 == 'g') {
                            break;
                        } else
                            System.out.println("\n\tWrong Input.\n");
                    }
                    break;
                } else if (ch == 'O' || ch == 'o')
                {
                    OrderList.order();
                    break;
                } else if (ch == 'R' || ch == 'r')
                {
                    CustomersReview.display();
                    break;
                } else if (ch == 'L' || ch == 'l')
                {
                    managerPage();
                    break;
                } else
                    System.out.println("\n\tWrong Input.\n");
            }
        }
    }

    static void managerPage()
    {
        boolean x=true, access=Login.checkPW();
        while (x)
        {
            System.out.println("\n*********************************************************************************");
            System.out.format("[ %-77s ]\n", "                       Coder Cafeteria Manager Page");
            System.out.println("*********************************************************************************");
            if (access)
            {
                System.out.println("  [********************************]");
                System.out.println("  [ o. Today's Order List          ]");
                System.out.println("  [********************************]");
                System.out.println("  [ d. Today's Delivery List       ]");
                System.out.println("  [********************************]");
                System.out.println("  [ O. Monthly Order List          ]");
                System.out.println("  [********************************]");
                System.out.println("  [ D. Monthly Delivery List       ]");
                System.out.println("  [********************************]");
                System.out.println("  [ A. Add New Food to Menu        ]");
                System.out.println("  [********************************]");
                System.out.println("  [ C. Change Password             ]");
                System.out.println("  [********************************]");
                System.out.println("  [ G. Go Back                     ]");
                System.out.println("  ^********************************^");

                while (true)
                {
                    System.out.print("Type your option [o,d,O,D,A,C,G] then press ENTER: ");
                    char ch1 = sc.next().charAt(0);
                    sc.nextLine();
                    if (ch1 == 'o') {
                        OrderList.displayCurrent();
                        break;
                    } else if (ch1 == 'd') {
                        HomeDeliveryList.displayCurrent();
                        break;
                    } else if (ch1 == 'O') {
                        OrderList.displayAll();
                        break;
                    } else if (ch1 == 'D') {
                        HomeDeliveryList.displayAll();
                        break;
                    } else if (ch1 == 'A' || ch1 == 'a') {
                        FoodMenu.addFood();
                        break;
                    }else if (ch1 == 'C' || ch1 == 'c') {
                        Login.changePW();
                        break;
                    } else if (ch1 == 'G' || ch1 == 'g')
                    {
                        x=false;
                        break;
                    } else
                        System.out.println("\n\tWrong Input.\n");
                }
            }
            else
            {
                access = Login.checkPW();
            }
            System.out.print("\nPress ENTER to go back.");
            sc.nextLine();
        }
    }
}

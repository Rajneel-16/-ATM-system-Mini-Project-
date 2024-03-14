// OOP MPR (finalized)
// ATM 
// 190 - Rajneel Sandeep Wagh
// 192 - Rahul Acchaiber Yadav
// 193 - Sarvesh Satish Yogi

import java.util.InputMismatchException;
import java.util.Scanner;

import myatm.*;
import javax.print.DocFlavor.STRING;



class ATM extends BankAccount implements My_Bank 
{
    public double amount;
    public static final double MIN_BALANCE = 1000; // Minimum balance constant

    public void bank_name_display() {
        System.out.print("*************************************************************************************************************");
        System.out.print("\n\n\nWelcome to My_Bank ATM! We provide you the best service on time. \n");
    }

    public ATM(String name, String username, String password) {
        super(name, username, password);
        this.amount = 0;
    }

    public void menu() {
        bank_name_display();

        while(true) {
            System.out.print("\n\nFor new registration, please deposit a minimum amount of INR 1000, else your account will not be accessible. \n\n");
            System.out.print("\n\n");
            System.out.print("\nPress '1' for cash withdrawal.");
            System.out.print("\nPress '2' for cash deposit.");
            System.out.print("\nPress '3' to generate a mini statement for your account.");
            System.out.print("\nPress '4' for changing the Username and Password of your ATM card.");
            System.out.print("\nPress '5' for exiting the process.");
            System.out.print("\nEnter choice: ");
            
            

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                withdrawAmount();
            } else if (choice.equals("2")) {
                depositAmount();
            } else if (choice.equals("3")) {
                miniStatement();
            } else if (choice.equals("4")) {
                changeInfo();
            } else if (choice.equals("5")) {
                System.out.print("\nThank you! Hope we left no stone unturned in our service!! \n\n\n");
                return;
            } else {
                System.out.print("\nChoose at least one option from above");
            }
            
            if(this.amount<1000)
            {
             System.out.print("\n\nYour account is has been blocked due to less than minimum balance! Please reach out to the bank for furthur queries. \n\n");
             return;
            }

            this.menu1();
         }
        
    }    
    
    
    public void menu1() {
        bank_name_display();

        while(true) {
            if(this.amount<1000) 
            {
             System.out.print("\n\nYour account is has been blocked due to less than minimum balance! Please reach out to the bank for furthur queries. \n\n");
             return;
            }
            System.out.print("\n\n");
            System.out.print("\nPress '1' for cash withdrawal.");
            System.out.print("\nPress '2' for cash deposit.");
            System.out.print("\nPress '3' to generate a mini statement for your account.");
            System.out.print("\nPress '4' for changing the Username and Password of your ATM card.");
            System.out.print("\nPress '5' for exiting the process.");
            System.out.print("\nEnter choice: ");
            
            
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                withdrawAmount();
            } else if (choice.equals("2")) {
                depositAmount();
            } else if (choice.equals("3")) {
                miniStatement();
            } else if (choice.equals("4")) {
                changeInfo();
            } else if (choice.equals("5")) {
                System.out.print("\nThank you! Hope we left no stone unturned in our service!!");
                return;
            } else {
                System.out.print("\nChoose at least one option from above");
            }
        }
    }



    public void changeInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the old password for verification: ");
        String old = scanner.nextLine();
        if (authenticate(getUsername(), old)) {
            System.out.print("\nEnter new password that you want to set: ");
            String newPass = scanner.nextLine();

            if (changepass(newPass) == true) {
                System.out.print("\nYour password has been changed successfully!");
            }
        } else {
            System.out.print("\nThis password is not valid, please enter the valid password:");
        }
    }



    public void depositAmount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the amount to be deposited: ");
        double amount = scanner.nextDouble();
        this.amount += amount;
        System.out.print("\nCash has been deposited!");
    }



    public void withdrawAmount() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter the amount to be withdrawn: ");
            double amount = scanner.nextDouble();
            if (this.amount - amount >= MIN_BALANCE) {
                this.amount -= amount;
                System.out.print("\nCollect your cash!");
            } else {
                throw new MyException("You have reached the minimum balance limit (Rs. 1000).");
            }
        } catch (MyException e) {
            System.out.print("\nException is caught! " + e.getMessage());
        }
    }


    public void transfer(){
        System.out.println("enter the username of the person to transfer");
        Scanner scanner=new Scanner(System.in);
        String username_other=scanner.nextLine();
        
    }


    public void miniStatement() {
        System.out.print("\n\nAccount Statement: \nHello " + getName());
        System.out.print("\nYour username is " + getUsername());
        System.out.print("\nYour current balance is INR " + this.amount + "/-");
    }
}




class Main {
    public static ATM find_doner(ATM users[],String username,String pass){
        for(ATM us: users){
            if(us.username.equals(username) && us.password.equals(pass)){
                System.out.println("User Found!");
                ATM obj1=us;
                return obj1;
            }
        }
        return null;
    }
    public static ATM find_accepter(ATM users[],String username){
        for(ATM us: users){
            if(us.username.equals(username)){
                System.out.println("User Found!");
                ATM obj1=us;
                return obj1;
            }
        }
        return null;
    }
    
    

    public static void transfer(ATM users[]) throws NoSuchUserExists{
        Scanner scanner = new Scanner(System.in);
        int amount;
        System.out.println("enter your username: ");
        String person1=scanner.nextLine();

        System.out.println("enter the password for varification: ");
        String password=scanner.nextLine();
        ATM per1=find_doner(users,person1,password);

        System.out.println("enter the username of the RECEIVER: ");
        String person2=scanner.nextLine();
        ATM per2=find_accepter(users,person2);

        if(per1.equals(null) || per2.equals(null)){
            throw new NoSuchUserExists("USERNAME NOT FOUND");
        }else{
            System.out.println("enter the amount to be transfer to  "+person2);
            amount=scanner.nextInt();
            per2.amount+=amount;
            per1.amount-=amount;
            System.out.println("THE AMOUNT HAS BEEN TRANSFER SUCCESSFULLY!!");
        }
    }
    
    
    public static void main(String arg[]) {
        ATM[] users = new ATM[10]; // Create an array of 10 ATM objects
        ATM a = new ATM("","","");
        a.bank_name_display();
        
        while (true) {
            System.out.print("\nPress '1' for new registeration");
            System.out.print("\nPress '2' to login to an existing account");
            System.out.print("\nPress '3' to for money transfer" );
            System.out.print("\nEnter choice = ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            
            if (choice.equals("1")) {
                // Find an empty slot in the array
                int emptySlot = -1;
                for (int i = 0; i < users.length; i++) {
                    if (users[i] == null) {
                        emptySlot = i;
                        break;
                    }
                }

                if (emptySlot != -1) {
                    System.out.print("\nEnter your full name: ");
                    String name = scanner.nextLine();
                    System.out.print("Set your username login credentials: ");
                    String username = scanner.nextLine();
                    System.out.print("Set a password for login credentials: ");
                    String password = scanner.nextLine();
                    users[emptySlot] = new ATM(name, username, password);
                    users[emptySlot].menu();
                } else {
                    System.out.print("Sorry! No available slots for new users!");
                }
            } else if (choice.equals("2")) {
                System.out.print("\nEnter your username: ");
                String username = scanner.nextLine();
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();
                boolean loggedIn = false;

                for (ATM user : users) {
                    if (user != null && user.authenticate(username, password)) {
                        
                        user.menu1();
                        loggedIn = true;
                        break;
                    }
                }

                if (!loggedIn) {
                    System.out.print("\nInvalid data entered! \nPlease enter valid username and password!");
                }
            }else if (choice.equals("3")) {
                try{
                    transfer(users);
                }catch(NoSuchUserExists no){
                    System.out.println(no.getMessage()+"  "+no.toString());
                }catch(Exception e){
                    System.out.println(e.getMessage()+"  "+e.toString());
                }
                
            }
        }
    }
}

import java.util.Arrays;
import java.util.Scanner;

public class SmartBankingApp {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0;0m";

        final String DASHBOARD = "Welcome to Smart Banking App";
        final String OPEN_ACCOUNT = "Open New Account";
        final String DEPOSIT_MONEY = "Deposit Money";
        final String WITHDRAW_MONEY = "Withdraw Money";
        final String TRANSFER_MONEY = "Transfer Money";
        final String CHECK_ACCOUNT_BALANCE = "Check Account Balance";
        final String DROP_ACCOUNT = "Delete Existing Account";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String[][] bankAccounts = new String[0][];

        String screen = DASHBOARD;

        do {

            final String APP_TITLE = String.format("%s", screen);

            System.out.println(CLEAR);
            System.out.println("-".repeat(70));
            System.out.println("\033[32;1m ".repeat((70 - (APP_TITLE.length()))/2).concat(APP_TITLE).concat("\033[32;0m"));
            System.out.println("-".repeat(70));

            switch(screen) {
                case DASHBOARD:
                    System.out.println("\t[1] Open New Account\n\t[2] Deposit Money\n\t[3] Withdraw Money\n\t[4] Transfer Money\n\t[5] Check Account Balance\n\t[6] Drop Existing Account\n\t[7] Exit");
                    System.out.print("\nEnter an option to continue > ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch(option) {
                        case 1: screen = OPEN_ACCOUNT; break;
                        case 2: screen = DEPOSIT_MONEY; break;
                        case 3: screen = WITHDRAW_MONEY; break;
                        case 4: screen = TRANSFER_MONEY; break;
                        case 5: screen = CHECK_ACCOUNT_BALANCE; break;
                        case 6: screen = DROP_ACCOUNT; break;
                        case 7: System.exit(0); break;
                        default: continue;
                    }
                    break;

                    case OPEN_ACCOUNT:
                    int number = (int) (Math.random() * Math.pow(3, 10));
                    String formattedNumber = String.format("SDB-%05d", number);
                    System.out.printf("\tNew Account Number : %s \n", formattedNumber);

                    boolean valid;
                    String name;
                    double amount = 0;
                    do{
                        do{
                        valid = true;
                            System.out.print("\n\tEnter Account Holder's Name : ");
                            name = SCANNER.nextLine().strip();
                            if (name.isBlank()){
                                System.out.printf(ERROR_MSG, "Name can't be empty");
                                valid = false;
                                continue;
                            }
                            for (int i = 0; i < name.length(); i++) {
                                if (!(Character.isLetter(name.charAt(i)) || 
                                    Character.isSpaceChar(name.charAt(i))) ) {
                                    System.out.printf(ERROR_MSG, "Invalid Name");
                                    valid = false;
                                    break;
                                }
                            }
                        } while (!valid);

                        do {
                            valid = true;
                            System.out.print("\n\tEnter Initial Deposit Amount : Rs.");
                            amount = SCANNER.nextDouble();
                            SCANNER.nextLine();
                        
                            if (amount < 5000) {
                                System.out.printf(ERROR_MSG, "Insufficient Initial Amount");
                                valid = false;
                                continue;
                            }
                        } while (!valid);

                    }while(!valid);
                    String[][] newBankAccount = new String[bankAccounts.length + 1][3];
                    
                    for (int i = 0; i < bankAccounts.length; i++) {
                        newBankAccount[i] = bankAccounts[i];
                    }

                    newBankAccount[newBankAccount.length-1][0] = formattedNumber;
                    newBankAccount[newBankAccount.length-1][1] = name;
                    newBankAccount[newBankAccount.length-1][2] = Double.toString(amount);
                    
                    bankAccounts = newBankAccount;
                    
                    System.out.printf(SUCCESS_MSG, String.format("\n\t%s : %s Added Successfully!", formattedNumber, name));

                    // for (int i = 0; i < bankAccounts.length; i++) {
                    //     System.out.println(Arrays.toString(bankAccounts[i]));
                    // }

                    System.out.print("\nDo you want to go back? (Y/n) : ");
                    if(SCANNER.nextLine().strip().toUpperCase().equals("Y")) screen = DASHBOARD;
                    break;


                default: continue;
            }

        } while (true);
    }
}
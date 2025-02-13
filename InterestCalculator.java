
import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Account {
    protected double interestRate;
    protected double amount;

    public abstract double calculateInterest();
}

class FDAccount extends Account {
    private int noOfDays;
    private int ageOfACHolder;

    public FDAccount(double amount, int noOfDays, int ageOfACHolder) {
        if (amount <= 0 || noOfDays <= 0 || ageOfACHolder < 0) {
            throw new IllegalArgumentException("Invalid input values.");
        }
        this.amount = amount;
        this.noOfDays = noOfDays;
        this.ageOfACHolder = ageOfACHolder;
    }

    public double calculateInterest() {
        if (amount < 1_00_00_000) {
            if (noOfDays >= 7 && noOfDays <= 14) {
                interestRate = ageOfACHolder >= 60 ? 5.00 : 4.50;
            } else if (noOfDays >= 15 && noOfDays <= 29) {
                interestRate = ageOfACHolder >= 60 ? 5.25 : 4.75;
            } else if (noOfDays >= 30 && noOfDays <= 45) {
                interestRate = ageOfACHolder >= 60 ? 6.00 : 5.50;
            } else if (noOfDays >= 46 && noOfDays <= 60) {
                interestRate = ageOfACHolder >= 60 ? 7.50 : 7.00;
            } else if (noOfDays >= 61 && noOfDays <= 184) {
                interestRate = ageOfACHolder >= 60 ? 8.00 : 7.50;
            } else if (noOfDays >= 185 && noOfDays <= 365) {
                interestRate = ageOfACHolder >= 60 ? 8.50 : 8.00;
            } else {
                throw new IllegalArgumentException("Invalid number of days.");
            }
        } else {
            if (noOfDays >= 7 && noOfDays <= 14) {
                interestRate = 6.50;
            } else if (noOfDays >= 15 && noOfDays <= 29) {
                interestRate = 6.75;
            } else if (noOfDays >= 30 && noOfDays <= 45) {
                interestRate = 6.75;
            } else if (noOfDays >= 46 && noOfDays <= 60) {
                interestRate = 8.00;
            } else if (noOfDays >= 61 && noOfDays <= 184) {
                interestRate = 8.50;
            } else if (noOfDays >= 185 && noOfDays <= 365) {
                interestRate = 10.00;
            } else {
                throw new IllegalArgumentException("Invalid number of days.");
            }
        }
        return (amount * interestRate) / 100;
    }
}

class SBAccount extends Account {
    private String accountType;

    public SBAccount(double amount, String accountType) {
        if (amount <= 0 || (!accountType.equalsIgnoreCase("Normal") && !accountType.equalsIgnoreCase("NRI"))) {
            throw new IllegalArgumentException("Invalid input values.");
        }
        this.amount = amount;
        this.accountType = accountType;
    }

    @Override
    public double calculateInterest() {
        interestRate = accountType.equalsIgnoreCase("Normal") ? 4.0 : 6.0;
        return (amount * interestRate) / 100;
    }
}

class RDAccount extends Account {
    private int noOfMonths;
    private double monthlyAmount;
    private int ageOfACHolder;

    public RDAccount(double monthlyAmount, int noOfMonths, int ageOfACHolder) {
        if (monthlyAmount <= 0 || noOfMonths <= 0 || ageOfACHolder < 0) {
            throw new IllegalArgumentException("Invalid input values.");
        }
        this.monthlyAmount = monthlyAmount;
        this.noOfMonths = noOfMonths;
        this.ageOfACHolder = ageOfACHolder;
    }

    public double calculateInterest() {
        if (noOfMonths == 6) {
            interestRate = ageOfACHolder >= 60 ? 8.00 : 7.50;
        } else if (noOfMonths == 9) {
            interestRate = ageOfACHolder >= 60 ? 8.25 : 7.75;
        } else if (noOfMonths == 12) {
            interestRate = ageOfACHolder >= 60 ? 8.50 : 8.00;
        } else if (noOfMonths == 15) {
            interestRate = ageOfACHolder >= 60 ? 8.75 : 8.25;
        } else if (noOfMonths == 18) {
            interestRate = ageOfACHolder >= 60 ? 9.00 : 8.50;
        } else if (noOfMonths == 21) {
            interestRate = ageOfACHolder >= 60 ? 9.25 : 8.75;
        } else {
            throw new IllegalArgumentException("Invalid number of months.");
        }
        return (monthlyAmount * noOfMonths * (interestRate / 100));
    }
}

public class InterestCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Select the option:\n1. Interest Calculator - SB\n2. Interest Calculator - FD\n3. Interest Calculator - RD\n4. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter the Average amount in your account:");
                        double sbAmount = scanner.nextDouble();
                        System.out.println("Enter the type of account (Normal/NRI):");
                        String accountType = scanner.next();
                        SBAccount sbAccount = new SBAccount(sbAmount, accountType);
                        System.out.println("Interest gained: Rs. " + sbAccount.calculateInterest());
                        break;
                    case 2:
                        System.out.println("Enter the FD amount:");
                        double fdAmount = scanner.nextDouble();
                        System.out.println("Enter the number of days:");
                        int noOfDays = scanner.nextInt();
                        System.out.println("Enter your age:");
                        int age = scanner.nextInt();
                        FDAccount fdAccount = new FDAccount(fdAmount, noOfDays, age);
                        System.out.println("Interest gained is: Rs. " + fdAccount.calculateInterest());
                        break;
                    case 3:
                        System.out.println("Enter the monthly amount:");
                        double rdMonthlyAmount = scanner.nextDouble();
                        System.out.println("Enter the number of months:");
                        int noOfMonths = scanner.nextInt();
                        System.out.println("Enter your age:");
                        int rdAge = scanner.nextInt();
                        RDAccount rdAccount = new RDAccount(rdMonthlyAmount, noOfMonths, rdAge);
                        System.out.println("Interest gained is: Rs. " + rdAccount.calculateInterest());
                        break;
                    case 4:
                        System.out.println("Exiting... Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numeric values where required.");
                scanner.next(); 
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

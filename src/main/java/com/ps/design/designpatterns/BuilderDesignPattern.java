package com.ps.design.designpatterns;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuilderDesignPattern implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BuilderDesignPattern.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        AbsractFactory bankFactory = FactoryCreater.getFactory("Bank");
        AbsractFactory loanFactory = FactoryCreater.getFactory("Loan");

        Bank bank = bankFactory.getBank("Axis");
        Loan loan = loanFactory.getLoan("Home");

        System.out.println(bank.getBankName() + " "+ loan.getLoanName());
    }
}


interface Bank {
    String getBankName();
}


class IciciBank implements Bank {
    private static final String bankName = "Icici Bank";

    @Override
    public String getBankName() {
        return bankName;
    }
}

class HdfcBank implements Bank {
    private static final String bankName = "Hdfc Bank";

    @Override
    public String getBankName() {
        return bankName;
    }
}

class AxisBank implements Bank {
    private static final String bankName = "Axis Bank";

    @Override
    public String getBankName() {
        return bankName;
    }
}

interface Loan {
    String getLoanName();
}

class BusinessLoan implements Loan {
    public static String loan = "Business Loan";

    @Override
    public String getLoanName() {
        return loan;
    }
}

class HouseLoan implements Loan {
    public static String loan = "House Loan";

    @Override
    public String getLoanName() {
        return loan;
    }
}

class FactoryCreater {
    public static AbsractFactory getFactory(String name) {
        if("Bank".equals(name)) return new BankFactory();
        else return new LoanFactory();
    }
}

interface AbsractFactory {
    public abstract Bank getBank(String bankName);

    public abstract Loan getLoan(String loanName);
}

class BankFactory implements AbsractFactory {
    @Override
    public Bank getBank(String bankName) {
        Bank bank;
        switch (bankName) {
            case "Icici":
                bank = new IciciBank();
                break;
            case "Hdfc":
                bank = new HdfcBank();
                break;
            default:
            case "Axis":
                bank = new AxisBank();
        }
        return bank;
    }

    @Override
    public Loan getLoan(String loanName) {
        return null;
    }
}

class LoanFactory implements AbsractFactory {
    @Override
    public Bank getBank(String bankName) {
        return null;
    }

    @Override
    public Loan getLoan(String loanName) {
        Loan loan;
        switch (loanName) {
            case "Business":
                loan = new BusinessLoan();
                break;
            default:
            case "Home":
                loan = new HouseLoan();
                break;
        }
        return loan;
    }
}




package dk.mk.is.rmi.dbserver;

/**
 * @author Dora Di
 */

import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class BankImplementation extends UnicastRemoteObject implements BankInterface {
    // public static String url = "jdbc:h2:mem:Bank";
    public static String url = "jdbc:h2:file:C:/Users/mak.CONVERGENS/Documents/Skole/SI/Projekter/RMI-DB/src/main/resources/db/bank;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE";
    public static String user = "sa";
    public static String password = "";
    public static String driver = "org.h2.Driver";

    BankImplementation() throws RemoteException {
    }

    @PostMapping("/transaction")
    public void newTransaction(@RequestBody BankTransaction newBankTransaction) {
        try {
            String SQL = "INSERT INTO BANKTRANSACTION  (transactionUUID, accnumFrom, accnumTo,transactionMessage,amount) VALUES (?, ?, ?, ?, ?);";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, newBankTransaction.getTransactionUUID());
            ps.setLong(2, newBankTransaction.getAccnumFrom());
            ps.setLong(3, newBankTransaction.getAccnumTo());
            ps.setString(4, newBankTransaction.getTransactionMessage());
            ps.setDouble(5, newBankTransaction.getAmount());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            System.out.println(rs);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/customer")
    public void newCustomer(@RequestBody Customer newCustomer) {
        try {

            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Customer (accnum, name, amount) VALUES (?, ?, ?);");
            ps.setLong(1, newCustomer.getAccnum());
            ps.setString(2, newCustomer.getName());
            ps.setDouble(3, newCustomer.getAmount());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            System.out.println(rs);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @GetMapping("/getTransactions")
    public List<BankTransaction> getTransactions() {
        List<BankTransaction> list = new ArrayList<BankTransaction>();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from BANKTRANSACTION ;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BankTransaction t = new BankTransaction();
                t.setTransactionMessage(rs.getString(4));
                t.setAmount(rs.getDouble(5));
                t.setAccnumTo(rs.getLong(3));
                t.setAccnumFrom(rs.getLong(2));
                t.setTransactionUUID(rs.getString(1));

                System.out.println(t);
                list.add(t);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    @GetMapping("/bank")
    public List<Customer> getMillionaires() {

        List<Customer> list = new ArrayList<Customer>();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from customer;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setAccnum(rs.getLong(1));
                c.setName(rs.getString(2));
                c.setAmount(rs.getDouble(3));
                System.out.println(c);
                list.add(c);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }


}  




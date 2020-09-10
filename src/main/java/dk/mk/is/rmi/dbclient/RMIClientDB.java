package dk.mk.is.rmi.dbclient;
/**
 * @author Dora Di
 */

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import dk.mk.is.rmi.dbserver.*;

public class RMIClientDB {
    public static void main(String args[]) throws Exception {
        // name =  rmi:// + ServerIP +  /EngineName;
        String remoteEngine = "rmi://localhost/BankServices";

        // Create local stub, lookup in the registry searching for the remote engine - the interface with the methods we want to use remotely
        BankInterface obj = (BankInterface) Naming.lookup(remoteEngine);

        Long accum = System.currentTimeMillis() / 1000;
        String transactionUUID = UUID.randomUUID().toString();

        addCustomer(obj, accum);
        addTransaction(obj, accum, transactionUUID);


        List<Customer> listC = getMillionaires(obj);
        List<BankTransaction> listT = getBankTransactions(obj);

        System.out.println("Number of customers: "+listC.size());
        System.out.println("Number of transactions: "+listT.size());
    }

    private static List<Customer> getMillionaires(BankInterface obj) throws RemoteException {
        List<Customer> listC = obj.getMillionaires();
        for (Customer c : listC) {
            System.out.println(c.getAccnum() + " " + c.getName() + " " + c.getAmount());
        }
        return listC;
    }

    private static List<BankTransaction> getBankTransactions(BankInterface obj) throws RemoteException {
        List<BankTransaction> listT = obj.getTransactions();
        for (BankTransaction t : listT) {
            System.out.println(t.toString());
        }
        return listT;
    }

    private static void addCustomer(BankInterface obj, Long accum) throws RemoteException {
        Customer customer = new Customer();
        customer.setAccnum(accum);
        customer.setAmount(10000000.00);
        customer.setName("yikers kage");
        obj.newCustomer(customer);
    }

    private static void addTransaction(BankInterface obj, Long accum, String transactionUUID) throws RemoteException {
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setAccnumFrom(accum);
        bankTransaction.setAccnumTo(Long.valueOf(3456));
        bankTransaction.setAmount(5000.00);
        bankTransaction.setTransactionMessage("AppTransfer");
        bankTransaction.setTransactionUUID(transactionUUID);
        obj.newTransaction(bankTransaction);
    }

} 

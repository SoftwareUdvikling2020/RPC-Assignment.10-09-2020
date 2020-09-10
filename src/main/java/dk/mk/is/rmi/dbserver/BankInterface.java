package dk.mk.is.rmi.dbserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface BankInterface extends Remote {
    List<Customer> getMillionaires() throws RemoteException;
    void newCustomer(Customer customer) throws RemoteException;
    void newTransaction(BankTransaction bankTransaction) throws RemoteException;
    List<BankTransaction> getTransactions() throws RemoteException;

    // @Query(value = "SELECT name FROM Customer  WHERE amount > 1000000")
    // List<Customer> findAllMillions();
    // List<Customer> findAllByName(String name);
}

package bank;

import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;

import java.util.List;

/**
 * Interface for a generic bank. Provides multiple methods to handle the interaction between
 * accounts and transactions.
 */
public interface Bank {

    /**
     * Adds an account to the bank. If the account already exists, an exception is thrown.
     *
     * @param account the account to be added
     * @throws AccountAlreadyExistsException if the account already exists
     */
    void createAccount(String account) throws AccountAlreadyExistsException;

    /**
     * Adds an account (with all specified transactions) to the bank. If the account already exists,
     * an exception is thrown.
     *
     * @param account the account to be added
     * @throws AccountAlreadyExistsException if the account already exists
     */
    void createAccount(String account, List<Transaction> transactions)
            throws AccountAlreadyExistsException;

    /**
     * Adds a transaction to an account. If the specified account does not exist, an exception is
     * thrown. If the transaction already exists, an exception is thrown.
     *
     * @param account     the account to which the transaction is added
     * @param transaction the transaction which is added to the account
     * @throws TransactionAlreadyExistException if the transaction already exists
     */
    void addTransaction(String account, Transaction transaction)
            throws TransactionAlreadyExistException, AccountDoesNotExistException;

    /**
     * Removes a transaction from an account. If the transaction does not exist, an exception is
     * thrown.
     *
     * @param account     the account from which the transaction is removed
     * @param transaction the transaction which is added to the account
     * @throws TransactionDoesNotExistException if the transaction cannot be found
     */
    void removeTransaction(String account, Transaction transaction)
            throws TransactionDoesNotExistException;

    /**
     * Checks whether the specified transaction for a given account exists.
     *
     * @param account     the account from which the transaction is checked
     * @param transaction the transaction which is added to the account
     */
    boolean containsTransaction(String account, Transaction transaction);

    /**
     * Calculates and returns the current account balance.
     *
     * @param account the selected account
     * @return the current account balance
     */
    double getAccountBalance(String account);

    /**
     * Returns a list of transactions for an account.
     *
     * @param account the selected account
     * @return the list of transactions
     */
    List<Transaction> getTransactions(String account);

    /**
     * Returns a sorted list (-> calculated amounts) of transactions for a specific account . Sorts the list either in ascending or descending order
     * (or empty).
     *
     * @param account the selected account
     * @param asc     selects if the transaction list is sorted ascending or descending
     * @return the list of transactions
     */
    List<Transaction> getTransactionsSorted(String account, boolean asc);

    /**
     * Returns a list of either positive or negative transactions (-> calculated amounts).
     *
     * @param account  the selected account
     * @param positive selects if positive  or negative transactions are listed
     * @return the list of transactions
     */
    List<Transaction> getTransactionsByType(String account, boolean positive);
}

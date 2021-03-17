package repository.account;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMySQL implements AccountRepository{
    private final Connection connection;
    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public String findBiggestId() throws EntityNotFoundException {
        Account account = null;
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                account = getAccountFromResultSet(rs);
            }

            return account.getIdAccount();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String create(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account (type, creationDate, balance) values (?, ?, ?)");
            insertStatement.setString(1, account.getAccountType());
            insertStatement.setString(3, account.getBalance());
            insertStatement.setString(2, account.getDateOfCreation());
            insertStatement.executeUpdate();
            return findBiggestId();
        } catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(String id, Account newAccount) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE account set type=?, creationDate=?, balance=? where id=?");
            insertStatement.setString(1, newAccount.getAccountType());
            insertStatement.setString(2, newAccount.getDateOfCreation());
            insertStatement.setString(3, newAccount.getBalance());
            insertStatement.setString(4, id);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("DELETE FROM account where id=?");
            insertStatement.setString(1, id);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void view() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(" ");
        for (Account x : accounts){
            System.out.println("ID:" + x.getIdAccount() + " | Type:" + x.getAccountType() + " | Balance:" + x.getBalance() + " | Date:" + x.getDateOfCreation());
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account findByID(String id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getAccountFromResultSet(rs);
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setIdAccount(rs.getString("id"))
                .setType(rs.getString("type"))
                .setBalance(rs.getString("balance"))
                .setDateOfCreation(rs.getString("creationDate"))
                .build();
    }
}

package be.pxl.student.dao.impl;

import be.pxl.student.REST.resource.AccountResource;
import be.pxl.student.dao.AccountDAO;
import be.pxl.student.entity.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDAO {
    private static final String SELECT_BY_ID = "SELECT * FROM account WHERE id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM account WHERE name = ?";
    private static final String SELECT_BY_IBAN = "SELECT * FROM account WHERE IBAN = ?";
    private static final String SELECT = "SELECT * FROM account";
    private static final String UPDATE = "UPDATE account SET name=?, IBAN=? WHERE id = ?";
    private static final String INSERT = "INSERT INTO account (name, IBAN) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM account WHERE id = ?";
    private String url;
    private String user;
    private String password;

    public AccountDaoImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(SELECT)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                accounts.add(mapAccount(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public Account createAccount(Account account) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, account.getName());
            stmt.setString(2, account.getIBAN());
            if (stmt.executeUpdate() == 1) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        account.setId(rs.getInt(1));
                        return account;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAccount(Account account, int id) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
            stmt.setString(1, account.getName());
            stmt.setString(2, account.getIBAN());
            stmt.setLong(3, id);
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAccount(int id) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(DELETE)) {
            stmt.setLong(1, id);
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account getAccountById(int id) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapAccount(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountByName(String name) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(SELECT_BY_NAME)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapAccount(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountByIBAN(String IBAN) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(SELECT_BY_IBAN)) {
            stmt.setString(1, IBAN);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapAccount(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean findIbanOrNameInAccounts(Account account) {
        List<Account> accounts = getAllAccounts();
        for (Account accountInList: accounts){
            if (accountInList.getIBAN().equals(account.getIBAN()) || accountInList.getName().equals(account.getName())) {
                return true;
            }
        }
        return false;
    }

    public int ibanInAccountButEmptyName(Account account) {
        List<Account> accounts = getAllAccounts();
        for (Account accountInList: accounts) {
            if (accountInList.getIBAN().equals(account.getIBAN()) && accountInList.getName().equals("")) {
                return account.getId();
            }
        }
        return -1;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private Account mapAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setName(rs.getString("name"));
        account.setIBAN(rs.getString("IBAN"));
        account.setId(rs.getInt("id"));
        return account;
    }
}

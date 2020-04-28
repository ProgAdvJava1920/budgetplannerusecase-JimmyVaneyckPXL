package be.pxl.student.dao.impl;

import be.pxl.student.REST.resource.LabelResource;
import be.pxl.student.dao.LabelDao;
import be.pxl.student.entity.Label;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LabelDaoImpl implements LabelDao {
    private static final String SELECT_BY_ID = "SELECT * FROM label WHERE id = ?";
    private static final String SELECT = "SELECT * FROM label";
    private static final String INSERT = "INSERT INTO label (name, description) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM label WHERE id = ?";
    private String url;
    private String user;
    private String password;

    public LabelDaoImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public List<Label> getLabels() {
        List<Label> labels = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(SELECT)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                labels.add(mapLabel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labels;
    }

    public Label createLabel(Label label) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, label.getName());
            stmt.setString(2, label.getDescription());

            if (stmt.executeUpdate() == 1) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        label.setId(rs.getInt("id"));
                        return label;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteLabel(int id) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(DELETE)) {
            stmt.setLong(1, id);
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean findNameInLabels(Label label) {
        List<Label> labels = getLabels();
        for (Label labelInList: labels){
            if (labelInList.getName().equals(label.getName())) {
                return true;
            }
        }
        return false;
    }

    public Label getLabelById(int id) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapLabel(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private Label mapLabel(ResultSet rs) throws SQLException {
        Label label = new Label(rs.getString("name"), rs.getString("description"));
        label.setId(rs.getInt("id"));
        return label;
    }
}
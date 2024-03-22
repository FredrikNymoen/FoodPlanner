package ntnu.org.IDATG1005.grp3.dao.implementations;

import ntnu.org.IDATG1005.grp3.dao.interfaces.IngredientDao;
import ntnu.org.IDATG1005.grp3.db.DatabaseConnection;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;

public class IngredientDaoImpl implements IngredientDao {

  private static final Logger logger = Logger.getLogger(IngredientDaoImpl.class.getName());

  @Override
  public Ingredient findIngredientById(Integer id) {
    String sql = "SELECT * FROM ingredient WHERE ingredient_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return new Ingredient(
            rs.getInt("ingredient_id"),
            rs.getString("name"),
            rs.getString("image"),
            MeasurementUnit.fromId(rs.getInt("unit_id")));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error finding ingredient by ID", e);
    }
    return null;
  }

  @Override
  public List<Ingredient> findAllIngredients() {
    List<Ingredient> ingredients = new ArrayList<>();
    String sql = "SELECT * FROM ingredient";
    try (Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        ingredients.add(new Ingredient(
            rs.getInt("ingredient_id"),
            rs.getString("name"),
            rs.getString("image"),
            MeasurementUnit.fromId(rs.getInt("unit_id"))));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error finding all ingredients", e);
    }
    return ingredients;
  }
}

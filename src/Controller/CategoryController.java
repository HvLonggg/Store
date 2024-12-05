package Controller;

import Model.Category;
import Model.connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryController extends connect {

    // thêm danh mục 
    public void addCategory(Category category) {
        String query = "INSERT INTO Category (category_name, CategoryDescription) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Phương thức sửa danh mục theo ID
    public void editCategory(int id, String name, String description) {
        String query = "UPDATE Category SET category_name = ?, CategoryDescription = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Phương thức xóa danh mục theo ID
    public void deleteCategory(int id) {
        String query = "DELETE FROM Category WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Phương thức tìm kiếm danh mục
    public List<Category> searchCategory(String keyword) {
        List<Category> result = new ArrayList<>();
        String query = "SELECT * FROM Category WHERE category_name LIKE ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("category_name");
                String description = rs.getString("CategoryDescription");
                result.add(new Category(id, name, description));
            }
        } catch (SQLException e) {
        }
        return result;
    }

    // Lấy danh sách tất cả các danh mục
    public List<Category> getCategories() {
        List<Category> result = new ArrayList<>();
        String query = "SELECT * FROM Category";
        
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("category_name");
                String description = rs.getString("CategoryDescription");
                result.add(new Category(id, name, description));
            }
        } catch (SQLException e) {
        }
        return result;
    }
    public List<Category> getCategoriesById(int id) {
        List<Category> result = new ArrayList<>();
        String query = "SELECT * FROM Category WHERE id = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt("id");
                String name = rs.getString("category_name");
                String description = rs.getString("CategoryDescription");
                result.add(new Category(categoryId, name, description));
            }
        } catch (SQLException e) {
        }

        return result;
    }
    // Lấy tổng số lượng danh mục
    public int getTotalCategories() {
        String query = "SELECT COUNT(*) FROM Category";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0; 
    }
}
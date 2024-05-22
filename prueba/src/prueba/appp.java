import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/quotes_db";
    private static final String USER = "username";
    private static final String PASS = "password";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

 

    // Método para buscar citas
    public List<Quote> searchQuotes(String term, int languageCode) {
        List<Quote> quotes = new ArrayList<>();
        String query = "SELECT quote_text, author FROM quotes WHERE language_id = ? AND (quote_text LIKE ? OR author LIKE ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, languageCode);
            stmt.setString(2, "%" + term + "%");
            stmt.setString(3, "%" + term + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                quotes.add(new Quote(rs.getString("quote_text"), rs.getString("author")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quotes;
    }



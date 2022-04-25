package Default;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class JDBC {
	public static void main(String[] args) {
		int select;
		do {
			Scanner in = new Scanner(System.in);
			try {
				System.out.println("Enter a word: ");
				String word = in.next();
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/wordoccurrences", "root1",
						"123456");

				PreparedStatement ps = con.prepareStatement("insert into word (word) values(?)");
				ps.setString(1, word);
				ps.executeUpdate();
				System.out.println("Updated database after adding your words:");
				Statement state = con.createStatement();
				ResultSet resultSet = state.executeQuery("select * from word");
				while (resultSet.next()) {
					System.out.println(resultSet.getString(1));
				}

				con.close();
			} catch (ClassNotFoundException | SQLException c) {
				System.out.println(c.getMessage() + " error ");
			}
			System.out.println("Enter another word? Type 1 for YES / Type 0 for NO");
			select = in.nextInt();
		} while (select == 1);
		System.out.println("Word frequency in database is:");
		Map<String, Integer> frequency = new LinkedHashMap<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/wordoccurrences", "root1",
					"123456");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from word");
			while (rs.next()) {
				String r = rs.getString(1);
				if (frequency.get(r) == null) {
					frequency.put(r, 1);
				} else {
					frequency.put(r, frequency.get(r) + 1);
				}
			}
			con.close();
		} catch (ClassNotFoundException | SQLException s) {
			System.out.println(s.getMessage());
		}
		Set<String> key = frequency.keySet();
		for (String f : key) {
			System.out.println("Word: " + f + " frequency: " + frequency.get(f));
		}
	}
}
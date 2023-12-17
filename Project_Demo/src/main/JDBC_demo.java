package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_demo {

	public static void main(String[] args) {
		try{
			//2
			Class.forName("com.mysql.cj.jdbc.Driver");
			//3
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jdbc","root","");
			//4
			Statement st = con.createStatement();
			//5
			//ResultSet rs = st.executeQuery("SELECT * FROM student");								
			//int up = st.executeUpdate("INSERT INTO register(username, email, password) VALUES ('" + usr + "','" + mail + "','" + pass + "')");
			int up = st.executeUpdate("UPDATE student SET id='87' WHERE id = '2132020087'");
			//int up = st.executeUpdate("DELETE FROM student WHERE id='2132020056'");
			//6
			if(up==1) {
				System.out.println("1 Row Updated");
			}
//			while(rs.next()) {
//				System.out.println(rs.getInt(1)+ " : " +rs.getString(2));
//			}
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

package Carrozeria;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;

public class ConnectionMySql {
	static Connection cn;
	static Statement st;
	static ResultSet rs;
	static String sql;
	int r;
	public static ArrayList<Socio> s = new ArrayList<Socio>();
	public static ArrayList<Auto> a = new ArrayList<Auto>();
	public static ArrayList<Noleggio> n = new ArrayList<Noleggio>();

	public static void Connection() throws SQLException {

		// ________________________________connessione
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch

		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

		// SELECT Soci

		sql = "SELECT * FROM `soci`";

		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next() == true) {
				System.out.println(rs.getString("cf"));
				s.add(new Socio(rs.getString("cf"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("indirizzo"), rs.getString("telefono")));
			}
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		}

		// SELECT Auto

		sql = "SELECT * FROM `auto`";

		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next() == true) {
				System.out.println(rs.getString("cf"));
				a.add(new Auto(rs.getString("targa"), rs.getString("marca"), rs.getString("modello"),
						Integer.parseInt(rs.getString("costo_giornaliero"))));
			}
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		}

		// SELECT Noleggi

		sql = "SELECT * FROM `noleggi`";

		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next() == true) {
				System.out.println(rs.getString("cf"));
				n.add(new Noleggio(rs.getInt("codice_noleggio"), rs.getString("auto"),
						rs.getString("socio"), rs.getDate("inizio"), rs.getDate("fine"), rs.getBoolean("auto_restituita")));
			}
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		}

	}// fine main
}// fine classe
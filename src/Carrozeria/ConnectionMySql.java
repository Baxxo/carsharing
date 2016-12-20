package Carrozeria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionMySql {
	static Connection cn;
	static Statement st;
	static ResultSet rs;
	static String sql;
	String auto;
	String socio;
	int indexAuto;
	int indexSocio;
	int is = 0;
	int ja = 0;
	int kn = 0;
	public ArrayList<Socio> s = new ArrayList<Socio>();
	public ArrayList<Auto> a = new ArrayList<Auto>();
	public ArrayList<Noleggio> n = new ArrayList<Noleggio>();

	public void Connection() throws SQLException {

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
				s.add(new Socio(rs.getString("cf"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("indirizzo"), rs.getString("telefono"), is));
				is++;
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
				a.add(new Auto(rs.getString("targa"), rs.getString("marca"), rs.getString("modello"),
						rs.getInt("costo_giornaliero"), ja));
				ja++;
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
				auto = rs.getString("auto");
				for (int i = 0; i < a.size(); i++) {
					if (a.get(i).getTarga().equals(auto)) {
						indexAuto = i;
					}
				}
				socio = rs.getString("socio");
				for (int i = 0; i < s.size(); i++) {
					if (s.get(i).getCf().equals(socio)) {
						indexSocio = i;
					}
				}

			}
			while (rs.next() == true) {
				n.add(new Noleggio(rs.getInt("codice_noleggio"), a.get(indexAuto), s.get(indexSocio),
						rs.getDate("inizio"), rs.getDate("fine"), rs.getBoolean("auto_restituita"), kn));
				kn++;
			}

		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		}

	}// fine main
}// fine classe
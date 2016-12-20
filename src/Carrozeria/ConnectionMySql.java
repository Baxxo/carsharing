package Carrozeria;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ibm.icu.util.Calendar;

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
	static int kn = 0;
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
	public static void nuovoNoleggio(Auto a, Socio s, Date ini, Date fin) throws IOException{
		Connection cn;
		Statement st;
		String sql;
		// ________________________________connessione
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch

		try {
			// Creo la connessione al database
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=");
			
			Calendar datai = Calendar.getInstance();
			datai.setTime(ini);
			Calendar dataf = Calendar.getInstance();
			dataf.setTime(fin);
			
			String sqlDataini = datai.get(Calendar.YEAR) + "-" + datai.get(Calendar.MONTH) + "-"
					+ datai.get(Calendar.DAY_OF_MONTH); //2016-12-20
			
			String sqlDatafin = dataf.get(Calendar.YEAR) + "-" + dataf.get(Calendar.MONTH) + "-"
					+ dataf.get(Calendar.DAY_OF_MONTH); 
			sql = "insert into Noleggi (auto,socio,inizio,fine,auto_restituita) values ('" + a.getTarga() + "','" + s.getCf() + "','" +sqlDataini 
					+ ",'"+ dataf+"','0')";
			System.out.println(sql);
			// ________________________________query

			st = cn.createStatement(); // creo sempre uno statement sulla
										// connessione
			st.execute(sql); // faccio la query su uno statement
			cn.close(); // chiusura connessione
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		} // fine try-catch

	}
}// fine classe
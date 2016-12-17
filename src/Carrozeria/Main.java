package Carrozeria;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class Main {

	protected Shell shlCarSharing;
	private ConnectionMySql con = new ConnectionMySql();
	private Table table;
	private Table table_1;
	private Table table_2;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCarSharing.open();
		shlCarSharing.layout();
		while (!shlCarSharing.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		try {
			con.Connection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		shlCarSharing = new Shell();
		shlCarSharing.setSize(1077, 500);
		shlCarSharing.setText("Car Sharing");

		Label lblElencoSoci = new Label(shlCarSharing, SWT.NONE);
		lblElencoSoci.setAlignment(SWT.CENTER);
		lblElencoSoci.setBounds(30, 259, 106, 15);
		lblElencoSoci.setText("Elenco soci");

		Label lblElencoAuto = new Label(shlCarSharing, SWT.NONE);
		lblElencoAuto.setText("Elenco auto");
		lblElencoAuto.setAlignment(SWT.CENTER);
		lblElencoAuto.setBounds(20, 10, 106, 15);

		Label lblElencoNoleggi = new Label(shlCarSharing, SWT.NONE);
		lblElencoNoleggi.setText("Elenco noleggi");
		lblElencoNoleggi.setAlignment(SWT.CENTER);
		lblElencoNoleggi.setBounds(448, 10, 106, 15);
		
		table = new Table(shlCarSharing, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(20, 31, 372, 225);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnTarga = new TableColumn(table, SWT.NONE);
		tblclmnTarga.setWidth(100);
		tblclmnTarga.setText("targa");
		
		TableColumn tblclmnMarca = new TableColumn(table, SWT.NONE);
		tblclmnMarca.setWidth(100);
		tblclmnMarca.setText("marca");
		
		TableColumn tblclmnModello = new TableColumn(table, SWT.NONE);
		tblclmnModello.setWidth(100);
		tblclmnModello.setText("modello");
		
		TableColumn tblclmnCosto = new TableColumn(table, SWT.NONE);
		tblclmnCosto.setWidth(63);
		tblclmnCosto.setText("costo");

		for (int i = 0; i < con.a.size(); i++) {
			tblclmnTarga.setText(con.a.get(i).targa);
			tblclmnMarca.setText(con.a.get(i).marca);
			tblclmnModello.setText(con.a.get(i).modello);
			tblclmnCosto.setText(String.valueOf(con.a.get(i).costo));
		}
		
		table_1 = new Table(shlCarSharing, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(448, 31, 556, 225);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tblclmnCodicenoleggio = new TableColumn(table_1, SWT.NONE);
		tblclmnCodicenoleggio.setWidth(100);
		tblclmnCodicenoleggio.setText("codice_noleggio");
		
		TableColumn tblclmnAuto = new TableColumn(table_1, SWT.NONE);
		tblclmnAuto.setWidth(100);
		tblclmnAuto.setText("auto");
		
		TableColumn tblclmnSocio = new TableColumn(table_1, SWT.NONE);
		tblclmnSocio.setWidth(100);
		tblclmnSocio.setText("socio");
		
		TableColumn tblclmnInizio = new TableColumn(table_1, SWT.NONE);
		tblclmnInizio.setWidth(77);
		tblclmnInizio.setText("inizio");
		
		TableColumn tblclmnFine = new TableColumn(table_1, SWT.NONE);
		tblclmnFine.setWidth(71);
		tblclmnFine.setText("fine");
		
		TableColumn tblclmnAutorestituita = new TableColumn(table_1, SWT.NONE);
		tblclmnAutorestituita.setWidth(100);
		tblclmnAutorestituita.setText("auto_restituita");
		
		for (int i = 0; i < con.n.size(); i++) {
			tblclmnCodicenoleggio.setText("");
			tblclmnAuto.setText("");
			tblclmnSocio.setText("");
			tblclmnInizio.setText("");
			tblclmnFine.setText("");
			tblclmnAutorestituita.setText("");
		}
		
		table_2 = new Table(shlCarSharing, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setBounds(20, 280, 572, 182);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);
		
		TableColumn tblclmnCf = new TableColumn(table_2, SWT.NONE);
		tblclmnCf.setWidth(100);
		tblclmnCf.setText("cf");
		
		TableColumn tblclmnCognome = new TableColumn(table_2, SWT.NONE);
		tblclmnCognome.setWidth(100);
		tblclmnCognome.setText("cognome");
		
		TableColumn tblclmnNome = new TableColumn(table_2, SWT.NONE);
		tblclmnNome.setWidth(100);
		tblclmnNome.setText("nome");
		
		TableColumn tblclmnIndirizzo = new TableColumn(table_2, SWT.NONE);
		tblclmnIndirizzo.setWidth(159);
		tblclmnIndirizzo.setText("indirizzo");
		
		TableColumn tblclmnTelefono = new TableColumn(table_2, SWT.NONE);
		tblclmnTelefono.setWidth(100);
		tblclmnTelefono.setText("telefono");
/*
		for (int i = 0; i < con.a.size(); i++) {
			list_1.add(con.a.get(i).targa);
		}

		for (int i = 0; i < con.n.size(); i++) {
			list_2.add(String.valueOf(con.n.get(i).codice) + ". " + con.n.get(i).socio.substring(0, 6) + " - "
					+ con.n.get(i).autoRestituita);
		}
*/
		// String changedUserString = userString.replace("'","''");

	}
}

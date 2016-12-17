package Carrozeria;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableCursor;

public class Main {

	protected Shell shlCarSharing;
	private ConnectionMySql con = new ConnectionMySql();
	private Table table;
	private Table table_1;
	private Table table_2;
	TableItem item;

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
			e1.printStackTrace();
		}

		shlCarSharing = new Shell();
		shlCarSharing.setSize(1100, 770);
		shlCarSharing.setText("Car Sharing");

		Label lblSelect = new Label(shlCarSharing, SWT.NONE);
		lblSelect.setBounds(417, 253, 624, 15);
		lblSelect.setText("select");

		Label lblElencoSoci = new Label(shlCarSharing, SWT.NONE);
		lblElencoSoci.setAlignment(SWT.CENTER);
		lblElencoSoci.setBounds(10, 10, 525, 15);
		lblElencoSoci.setText("Elenco soci");

		Label lblElencoAuto = new Label(shlCarSharing, SWT.NONE);
		lblElencoAuto.setText("Elenco auto");
		lblElencoAuto.setAlignment(SWT.CENTER);
		lblElencoAuto.setBounds(10, 219, 550, 28);

		Label lblElencoNoleggi = new Label(shlCarSharing, SWT.NONE);
		lblElencoNoleggi.setText("Elenco noleggi");
		lblElencoNoleggi.setAlignment(SWT.CENTER);
		lblElencoNoleggi.setBounds(528, 10, 525, 15);

		table = new Table(shlCarSharing, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 251, 384, 225);
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
			item = new TableItem(table, SWT.NONE);
			item.setText(0, con.a.get(i).targa);
			item.setText(1, con.a.get(i).marca);
			item.setText(2, con.a.get(i).modello);
			item.setText(3, String.valueOf(con.a.get(i).costo));
		}

		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK) {
					lblSelect.setText("You checked " + event.item);
				} else {
					lblSelect.setText("You selected " + event.item);
				}
			}
		});

		table_1 = new Table(shlCarSharing, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(558, 31, 520, 182);
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
			item = new TableItem(table_1, SWT.NONE);
			item.setText(0, con.n.get(i).codice + "");
			item.setText(1, con.n.get(i).auto);
			item.setText(2, con.n.get(i).socio);
			item.setText(3, con.n.get(i).inizio + "");
			item.setText(4, con.n.get(i).fine + "");
			item.setText(5, con.n.get(i).autoRestituita + "");
		}

		table_1.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK) {
					lblSelect.setText("You checked " + event.item);
				} else {
					lblSelect.setText("You selected " + event.item);
				}
			}
		});

		table_2 = new Table(shlCarSharing, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setBounds(10, 31, 520, 182);
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

		Label label = new Label(shlCarSharing, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(624, 0, -4, 731);

		for (int i = 0; i < con.s.size(); i++) {
			item = new TableItem(table_2, SWT.NONE);
			item.setText(0, con.s.get(i).cf);
			item.setText(1, con.s.get(i).cognome);
			item.setText(2, con.s.get(i).nome);
			item.setText(3, con.s.get(i).indirizzo);
			item.setText(4, con.s.get(i).telefono);
		}

		table_2.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK) {
					lblSelect.setText("You checked " + event.item);
				} else {
					lblSelect.setText("You selected " + event.item);
				}
			}
		});

		// String changedUserString = userString.replace("'","''");

	}
}

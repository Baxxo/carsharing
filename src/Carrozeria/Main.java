package Carrozeria;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Main {

	protected Shell shlCarSharing;
	private ConnectionMySql con = new ConnectionMySql();
	String select;
	List list;
	List list_1;
	List list_2;
	List list_3;
	public ArrayList<Socio> s = new ArrayList<Socio>();
	public ArrayList<Auto> a = new ArrayList<Auto>();
	public ArrayList<Noleggio> n = new ArrayList<Noleggio>();
	
	
	public ArrayList<Noleggio> socio = new ArrayList<Noleggio>();
	int sociGiusti;

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

		a = con.getA();
		s = con.getS();
		n = con.getN();

		shlCarSharing = new Shell();
		shlCarSharing.setSize(500, 770);
		shlCarSharing.setText("Car Sharing");

		Label lblSelect = new Label(shlCarSharing, SWT.BORDER | SWT.WRAP | SWT.SHADOW_IN | SWT.CENTER);
		lblSelect.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblSelect.setBounds(10, 677, 212, 44);

		Label lblElencoSoci = new Label(shlCarSharing, SWT.BORDER | SWT.WRAP | SWT.SHADOW_IN | SWT.CENTER);
		lblElencoSoci.setAlignment(SWT.CENTER);
		lblElencoSoci.setBounds(10, 10, 212, 28);
		lblElencoSoci.setText("Elenco soci");

		Label lblElencoAuto = new Label(shlCarSharing, SWT.BORDER | SWT.WRAP | SWT.SHADOW_IN | SWT.CENTER);
		lblElencoAuto.setText("Elenco auto");
		lblElencoAuto.setAlignment(SWT.CENTER);
		lblElencoAuto.setBounds(10, 232, 212, 28);

		Label lblElencoNoleggi = new Label(shlCarSharing, SWT.BORDER | SWT.WRAP | SWT.CENTER);
		lblElencoNoleggi.setText("Elenco noleggi");
		lblElencoNoleggi.setAlignment(SWT.CENTER);
		lblElencoNoleggi.setBounds(10, 452, 212, 26);

		list = new List(shlCarSharing, SWT.BORDER | SWT.V_SCROLL);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				lblSelect.setText(list.getItem(list.getSelectionIndex()));
			}
		});
		list.setBounds(10, 44, 212, 182);

		list_1 = new List(shlCarSharing, SWT.BORDER | SWT.V_SCROLL);
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				lblSelect.setText(list_1.getItem(list_1.getSelectionIndex()));
			}
		});
		list_1.setBounds(10, 264, 212, 182);

		list_2 = new List(shlCarSharing, SWT.BORDER | SWT.V_SCROLL);
		list_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				lblSelect.setText(list_2.getItem(list_2.getSelectionIndex()));
			}
		});
		list_2.setBounds(10, 491, 212, 180);

		for (int i = 0; i < con.s.size(); i++) {
			list.add(con.s.get(i).cognome + "  " + con.s.get(i).nome);
		}

		for (int i = 0; i < con.a.size(); i++) {
			list_1.add(con.a.get(i).targa + " - " + con.a.get(i).modello);
		}

		for (int i = 0; i < con.n.size(); i++) {
			list_2.add(con.n.get(i).codice + " - " + con.n.get(i).auto.targa + " - " + con.n.get(i).socio.nome);
		}

		Label label = new Label(shlCarSharing, SWT.SEPARATOR);
		label.setBounds(228, 10, 2, 722);

		Label lblNomeSocio = new Label(shlCarSharing, SWT.NONE);
		lblNomeSocio.setAlignment(SWT.CENTER);
		lblNomeSocio.setBounds(236, 10, 238, 15);
		lblNomeSocio.setText("Nome Socio");

		DateTime dateTime = new DateTime(shlCarSharing, SWT.BORDER);
		dateTime.setBounds(276, 78, 165, 24);

		Label lblInizio = new Label(shlCarSharing, SWT.NONE);
		lblInizio.setAlignment(SWT.CENTER);
		lblInizio.setBounds(236, 57, 238, 15);
		lblInizio.setText("Inizio");

		list_3 = new List(shlCarSharing, SWT.BORDER);
		list_3.setBounds(276, 143, 165, 138);

		Button btnGet = new Button(shlCarSharing, SWT.NONE);
		btnGet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list_3.removeAll();
				socio.removeAll(socio);
				sociGiusti = list.getSelectionIndex();
				for (int i = 0; i < n.size(); i++) {
					if (s.get(sociGiusti).getCf().equals(n.get(i).socio.getCf())) {
						// list_3.add(s.get(sociGiusti).nome);
						socio.add(n.get(sociGiusti));
					}
				}
				java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dataN = null;
				try {
					dataN = df.parse(dateTime.getYear() + "-" + dateTime.getMonth() + "-" + dateTime.getDay());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 0; i < socio.size(); i++) {
					if (socio.get(i).inizio.after(dataN)) {
						list_3.add(socio.get(i).auto.targa + " " + socio.get(i).socio.nome );
						list_3.add(dataN+"");
					}
				}
			}
		});
		btnGet.setBounds(322, 112, 75, 25);
		btnGet.setText("Get");

		Label lblNomesocio = new Label(shlCarSharing, SWT.NONE);
		lblNomesocio.setAlignment(SWT.CENTER);
		lblNomesocio.setBounds(276, 31, 165, 15);

		// String changedUserString = userString.replace("'","''");

	}
}

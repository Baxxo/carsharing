package Carrozzeria;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Main {

	protected Shell shlCarSharing;
	private ConnectionMySql con = new ConnectionMySql();
	String select, ci;
	List list;
	List list_1;
	List list_2;
	List list_3;
	List list_autolibere;

	int posS, posA;

	String l;
	long diff;
	long ritardo;
	long soldi;
	public ArrayList<Socio> s = new ArrayList<Socio>();
	public ArrayList<Auto> a = new ArrayList<Auto>();
	public ArrayList<Auto> autoDisponibili = new ArrayList<Auto>();
	public ArrayList<Noleggio> n = new ArrayList<Noleggio>();

	public ArrayList<Noleggio> socio = new ArrayList<Noleggio>();
	int sociGiusti;

	Button btnGet;
	Button btnRestituisci;
	Button btnNuovoNoleggio;

	Boolean[] t = { false, false, false };

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
		shlCarSharing.setSize(750, 756);
		shlCarSharing.setText("Car Sharing");

		Label lblSelect = new Label(shlCarSharing, SWT.BORDER | SWT.WRAP | SWT.SHADOW_IN | SWT.CENTER);
		lblSelect.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblSelect.setBounds(10, 677, 212, 31);

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

		list_3 = new List(shlCarSharing, SWT.BORDER);
		list_3.setBounds(397, 336, 165, 162);

		list = new List(shlCarSharing, SWT.BORDER | SWT.V_SCROLL);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				lblSelect.setText(list.getItem(list.getSelectionIndex()));
				// System.out.println(posS);
			}

			@Override
			public void mouseDown(MouseEvent e) {
				posS = list.getSelectionIndex();
				btnGet.setVisible(true);
				t[0] = true;
				if (t[0] == true && t[1] == true && t[2] == true) {
					btnNuovoNoleggio.setVisible(true);
				}
			}
		});
		list.setBounds(10, 44, 212, 182);

		list_1 = new List(shlCarSharing, SWT.BORDER | SWT.V_SCROLL);
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				lblSelect.setText(list_1.getItem(list_1.getSelectionIndex()));

			}

			@Override
			public void mouseDown(MouseEvent e) {
				posA = list_1.getSelectionIndex();
			}
		});
		list_1.setBounds(10, 264, 212, 182);

		list_2 = new List(shlCarSharing, SWT.BORDER | SWT.V_SCROLL);
		list_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				lblSelect.setText(list_2.getItem(list_2.getSelectionIndex()));
			}

			@Override
			public void mouseDown(MouseEvent e) {
				btnRestituisci.setVisible(true);
			}
		});
		list_2.setBounds(10, 491, 212, 180);

		list_autolibere = new List(shlCarSharing, SWT.BORDER);
		list_autolibere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				t[1] = true;
				if (t[0] == true && t[1] == true && t[2] == true) {
					btnNuovoNoleggio.setVisible(true);
				}
			}
		});
		list_autolibere.setBounds(581, 62, 139, 94);

		refresh();

		Label lblSelezionaUn = new Label(shlCarSharing, SWT.CENTER);
		lblSelezionaUn.setAlignment(SWT.CENTER);
		lblSelezionaUn.setBounds(352, 66, 139, 28);
		lblSelezionaUn.setText("1. Seleziona un socio");

		Label lblNoleggiare = new Label(shlCarSharing, SWT.BORDER | SWT.WRAP | SWT.CENTER);
		lblNoleggiare.setBounds(352, 10, 139, 28);
		lblNoleggiare.setText("Noleggiare auto");

		Label lblSelezionaUn_1 = new Label(shlCarSharing, SWT.NONE);
		lblSelezionaUn_1.setAlignment(SWT.CENTER);
		lblSelezionaUn_1.setBounds(352, 95, 139, 31);
		lblSelezionaUn_1.setText("2. Seleziona un auto\r\ndisponibile");

		Label lblSelezionaLa = new Label(shlCarSharing, SWT.CENTER);
		lblSelezionaLa.setBounds(352, 132, 139, 40);
		lblSelezionaLa.setText("3. Seleziona la data \r\ninizio e fine noleggio\r\n");

		DateTime dateTime_2 = new DateTime(shlCarSharing, SWT.BORDER);
		dateTime_2.setBounds(377, 178, 80, 24);

		Label lblI = new Label(shlCarSharing, SWT.NONE);
		lblI.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblI.setAlignment(SWT.CENTER);
		lblI.setBounds(352, 178, 19, 24);
		lblI.setText("i:");

		Label lblF = new Label(shlCarSharing, SWT.NONE);
		lblF.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblF.setBounds(463, 178, 13, 24);
		lblF.setText("f:");

		DateTime dateTime_1 = new DateTime(shlCarSharing, SWT.BORDER);
		dateTime_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				t[2] = true;
				if (t[0] == true && t[1] == true && t[2] == true) {
					btnNuovoNoleggio.setVisible(true);
				}
			}
		});

		dateTime_1.setBounds(482, 178, 80, 24);

		btnNuovoNoleggio = new Button(shlCarSharing, SWT.NONE);
		btnNuovoNoleggio.setVisible(false);
		btnNuovoNoleggio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// conversione da dateTime a date
				java.text.DateFormat dini = new SimpleDateFormat("yyyy-MM-dd");
				Date dataN = null;
				try {
					dataN = dini.parse(dateTime_2.getYear() + "-" + dateTime_2.getMonth() + "-" + dateTime_2.getDay());
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
				java.text.DateFormat dfin = new SimpleDateFormat("yyyy-MM-dd");
				Date dataM = null;
				try {
					dataM = dfin.parse(dateTime_1.getYear() + "-" + dateTime_1.getMonth() + "-" + dateTime_1.getDay());
				} catch (ParseException e2) {

					e2.printStackTrace();
				}
				if (dataN.before(dataM)) {
					// System.out.println("corretto");
					boolean rest = false;
					Noleggio noleg = new Noleggio(posS, autoDisponibili.get(list_autolibere.getSelectionIndex()),
							s.get(list.getSelectionIndex()), dataN, dataM, rest, posA);

					n.add(noleg);

					try {
						con.nuovoNoleggio(autoDisponibili.get(list_autolibere.getSelectionIndex()),
								s.get(list.getSelectionIndex()), dataN, dataM);
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				} else {
					System.out.println("data finale incorretta");
				}
				autoDisponibili.remove(list_autolibere.getSelectionIndex());
				list_autolibere.remove(list_autolibere.getSelectionIndex());

				refresh();
				t[0] = false;
				t[1] = false;
				t[2] = false;
				btnNuovoNoleggio.setVisible(false);
			}
		});
		btnNuovoNoleggio.setBounds(617, 177, 80, 25);
		btnNuovoNoleggio.setText("Noleggia!");

		Label label = new Label(shlCarSharing, SWT.SEPARATOR);
		label.setBounds(228, 10, 2, 722);

		Label lblNomeSocio = new Label(shlCarSharing, SWT.NONE);
		lblNomeSocio.setAlignment(SWT.CENTER);
		lblNomeSocio.setBounds(463, 233, 257, 15);
		lblNomeSocio.setText("Nome Socio");

		DateTime dateTime = new DateTime(shlCarSharing, SWT.BORDER);
		dateTime.setBounds(276, 285, 165, 24);

		Label lblInizio = new Label(shlCarSharing, SWT.NONE);
		lblInizio.setAlignment(SWT.CENTER);
		lblInizio.setBounds(276, 268, 165, 15);
		lblInizio.setText("Inizio");

		btnGet = new Button(shlCarSharing, SWT.NONE);
		btnGet.setVisible(false);
		btnGet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblNomeSocio.setText("Nome Socio: " + list.getItem(list.getSelectionIndex()));
				list_3.removeAll();
				// array fatto a posta
				socio.clear();
				// vaiabile fatta a posta
				sociGiusti = list.getSelectionIndex();
				for (int i = 0; i < n.size(); i++) {
					if (s.get(sociGiusti).getCf().equals(n.get(i).socio.getCf())) {
						socio.add(n.get(i));
					}
				}
				java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dataInizio = null;
				try {
					dataInizio = df.parse(dateTime.getYear() + "-" + dateTime.getMonth() + 1 + "-" + dateTime.getDay());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				if (socio.size() == 0) {
					list_3.add("Nessun noleggio!");
				} else {
					for (int i = 0; i < socio.size(); i++) {
						if (socio.get(i).inizio.after(dataInizio)) {
							list_3.add(socio.get(i).auto.targa + " " + socio.get(i).socio.nome);
							list_3.add(dataInizio + "");
						}
					}
				}
				btnGet.setVisible(false);
			}
		});
		btnGet.setBounds(566, 285, 75, 25);
		btnGet.setText("Get");

		Label label_1 = new Label(shlCarSharing, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(228, 208, 496, 2);

		Label label_2 = new Label(shlCarSharing, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(228, 514, 492, 2);

		Label lblImporto = new Label(shlCarSharing, SWT.NONE);
		lblImporto.setAlignment(SWT.CENTER);
		lblImporto.setBounds(377, 664, 185, 15);
		lblImporto.setText("Importo");

		DateTime dateTime_3 = new DateTime(shlCarSharing, SWT.BORDER);
		dateTime_3.setBounds(463, 545, 80, 24);

		Label lblData = new Label(shlCarSharing, SWT.SHADOW_NONE | SWT.RIGHT);
		lblData.setAlignment(SWT.RIGHT);
		lblData.setBounds(397, 549, 60, 20);
		lblData.setText("Data");

		btnRestituisci = new Button(shlCarSharing, SWT.NONE);
		btnRestituisci.setVisible(false);
		btnRestituisci.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				l = list_2.getItem(list_2.getSelectionIndex());
				l = "" + l.charAt(0);
				sociGiusti = Integer.parseInt(l);
				sociGiusti = sociGiusti - 1;
				int index = (n.get(sociGiusti).getCodice()) - 1;

				// differenza date
				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

				// calcolo prestito
				Date date1 = n.get(index).getInizio();
				Date date2 = n.get(index).getFine();
				diff = date2.getTime() - date1.getTime();
				soldi = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

				// calcolo ritardo
				diff = 0;
				Date date3 = n.get(index).getFine();
				Date date4 = null;
				try {
					date4 = myFormat
							.parse(dateTime_3.getYear() + "-" + dateTime_3.getMonth() + 1 + "-" + dateTime_3.getDay());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				diff = date4.getTime() - date3.getTime();
				ritardo = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

				if (ritardo > 0) {
					soldi = (soldi * 50) + (ritardo * 10);
				} else {
					soldi = soldi * 50;
				}

				lblImporto.setText("Importo = " + soldi);
				n.get(index).setAutoRestituita(true);
				con.restituisciAuto(index);
				refresh();
				btnRestituisci.setVisible(false);
			}
		});
		btnRestituisci.setBounds(431, 598, 75, 25);
		btnRestituisci.setText("Restituisci");

		Label lblAutoDisponibili = new Label(shlCarSharing, SWT.BORDER | SWT.WRAP | SWT.CENTER);
		lblAutoDisponibili.setText("Auto disponibili");
		lblAutoDisponibili.setBounds(581, 10, 139, 28);

		Button btnRefresh = new Button(shlCarSharing, SWT.NONE);
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refresh();
			}
		});
		btnRefresh.setBounds(649, 683, 75, 25);
		btnRefresh.setText("Refresh");

		Label lblElencoNoleggi_1 = new Label(shlCarSharing, SWT.BORDER | SWT.WRAP | SWT.SHADOW_IN | SWT.CENTER);
		lblElencoNoleggi_1.setAlignment(SWT.CENTER);
		lblElencoNoleggi_1.setBounds(280, 216, 161, 28);
		lblElencoNoleggi_1.setText("Elenco Noleggi");
		
	}

	private void refresh() {
		try {
			con.Connection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		s = con.getS();
		a = con.getA();
		n = con.getN();

		autoDisponibili.clear();

		list.removeAll();
		list_1.removeAll();
		list_2.removeAll();
		list_autolibere.removeAll();

		for (int i = 0; i < con.s.size(); i++) {
			list.add(con.s.get(i).cognome + "  " + con.s.get(i).nome);
		}

		for (int i = 0; i < con.a.size(); i++) {
			list_1.add(con.a.get(i).targa + " - " + con.a.get(i).modello);
		}

		for (int i = 0; i < n.size(); i++) {
			list_2.add(n.get(i).codice + " - " + n.get(i).auto.targa + " - " + n.get(i).socio.nome);

		}
		for (int i = 0; i < con.n.size(); i++) {
			if (con.n.get(i).autoRestituita == true) {
				Auto a = new Auto(con.n.get(i).auto.targa, con.n.get(i).auto.marca, con.n.get(i).auto.modello,
						con.n.get(i).auto.costo, i);
				autoDisponibili.add(a);
			}
		}

		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < autoDisponibili.size(); j++) {
				if (!(autoDisponibili.get(j).targa.equals(a.get(i).targa))) {
					autoDisponibili.add(a.get(i));
				}
			}
		}


		for (int i = 0; i < autoDisponibili.size() - 1; i++) {
			if (autoDisponibili.get(i).equals(autoDisponibili.get(i + 1))) {
				autoDisponibili.remove(i);
				i--;
			}
		}

		for (int i = 0; i < autoDisponibili.size(); i++) {
			for (int j = 0; j < autoDisponibili.size(); j++) {
				if (autoDisponibili.get(i).targa.equals(autoDisponibili.get(j).targa)) {
					autoDisponibili.remove(j);

				}
			}
		}

		for (int i = 0; i < autoDisponibili.size(); i++) {
			list_autolibere.add(autoDisponibili.get(i).targa + " - " + autoDisponibili.get(i).modello);
		}
	}
}

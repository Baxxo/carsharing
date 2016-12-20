package Carrozeria;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;

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
	String select;
	List list;
	List list_1;
	List list_2;
	Auto auto;
	Socio socio;
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
		shlCarSharing.setSize(750, 750);
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
		
		Label lblSelezionaUn = new Label(shlCarSharing, SWT.CENTER);
		lblSelezionaUn.setAlignment(SWT.CENTER);
		lblSelezionaUn.setBounds(352, 66, 139, 28);
		lblSelezionaUn.setText("1. Seleziona un socio");
		
		Label lblNoleggiare = new Label(shlCarSharing, SWT.BORDER | SWT.WRAP | SWT.CENTER);
		lblNoleggiare.setBounds(352, 10, 139, 28);
		lblNoleggiare.setText("Noleggiare auto");
		
		Label lblSelezionaUn_1 = new Label(shlCarSharing, SWT.NONE);
		lblSelezionaUn_1.setAlignment(SWT.CENTER);
		lblSelezionaUn_1.setBounds(352, 100, 139, 26);
		lblSelezionaUn_1.setText("2. Seleziona un auto");
		
		Label lblSelezionaLa = new Label(shlCarSharing, SWT.CENTER);
		lblSelezionaLa.setBounds(352, 132, 139, 40);
		lblSelezionaLa.setText("3. Seleziona la data \r\ninizio e fine noleggio\r\n");
		
		DateTime dateTime = new DateTime(shlCarSharing, SWT.BORDER);
		dateTime.setBounds(377, 178, 80, 24);
		
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
		dateTime_1.setBounds(482, 178, 80, 24);
		
		Button btnNuovoNoleggio = new Button(shlCarSharing, SWT.NONE);
		btnNuovoNoleggio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list.addMouseListener(new MouseAdapter() {
					public void mouseDoubleClick(MouseEvent e) {
						
						auto = new Auto();
					}
				});
				
				list_1.addMouseListener(new MouseAdapter() {
					public void mouseDoubleClick(MouseEvent e) {
						list_1.getSelectionCount();
						//socio = new Socio();
					}
				});
				
				//con.nuovoNoleggio(auto, socio, ini,fin);
			}
		});
		btnNuovoNoleggio.setBounds(561, 114, 80, 25);
		btnNuovoNoleggio.setText("Noleggia!");
		for (int i = 0; i < con.s.size(); i++) {
			list.add(con.s.get(i).i + " - " + con.s.get(i).cognome + "  " + con.s.get(i).nome);
		}

		for (int i = 0; i < con.a.size(); i++) {
			list_1.add(con.a.get(i).i + " - " + con.a.get(i).targa + "  " + con.a.get(i).modello);
		}

		System.out.println(con.n.size());

		for (int i = 0; i < con.n.size(); i++) {
			list_2.add(con.n.get(i).codice + " - " + con.n.get(i).auto.targa);
		}

		// String changedUserString = userString.replace("'","''");

	}
}

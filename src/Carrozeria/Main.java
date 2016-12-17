package Carrozeria;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;

public class Main {

	protected Shell shell;
	private ConnectionMySql con = new ConnectionMySql();
	private Socio s;
	List list;

	/**
	 * Launch the application.
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
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
		
		shell = new Shell();
		shell.setSize(939, 500);
		shell.setText("Car Sharing");
		
		list = new List(shell, SWT.BORDER);
		list.setBounds(10, 71, 205, 225);
		
		Label lblElencoSoci = new Label(shell, SWT.NONE);
		lblElencoSoci.setAlignment(SWT.CENTER);
		lblElencoSoci.setBounds(10, 43, 205, 15);
		lblElencoSoci.setText("Elenco soci");
		
		for(int i=0;i<con.s.size();i++){
			list.add(con.s.get(i).nome);	
		}
		
		//String changedUserString = userString.replace("'","''");
	
	}
}

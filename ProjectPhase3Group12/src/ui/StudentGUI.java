/**
 * 
 */
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class StudentGUI extends JPanel implements Observer, ActionListener, TableModelListener {

	
	private static final long serialVersionUID = -8675309L;
	
	private JTextField studentSearchBox = new JTextField();
	private JButton searchButton = new JButton("Search");
	private JTable dbStudentTable = new JTable();
	private JScrollPane dbScrollPane = new JScrollPane();
	
	
	
	
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

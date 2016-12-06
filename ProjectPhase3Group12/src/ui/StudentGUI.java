/**
 * 
 */
package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import employment.Employer;
import employment.EmployerCollection;
import student.Student;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class StudentGUI extends JPanel implements Observer, ActionListener, TableModelListener {

	
	private static final long serialVersionUID = -8675309L;
	
	private Student myStudent;
	
	private JButton myListButton, mySearchButton, myAddButton;
	private JPanel buttonPanel, dataPanel;
	private String[] emploerColumnNames = { "companyName", "startDate", "salary", "position"};

	private Object[][] mData;
	private JTable myTable;
	private JScrollPane myScrollPane;
	private JPanel searchPanel;
	private JLabel titleLabel;
	private JTextField employerTextField;
	private JButton employerSearchButton;

	private JPanel addEmployerPanel;
	private JLabel[] employerTextLabels = new JLabel[8];
	private JTextField[] employerTextFields = new JTextField[8];
	private JButton addEmployersButton;

	/**
	 * Use this for Item administration. Add components that contain the list,
	 * search and add to this.
	 */
	public StudentGUI (Student theStudent) {
		myStudent = theStudent;
		//myEmployers = EmployerCollection.getEmployers(myStudent.getStudentID());
		
		setLayout(new BorderLayout());

		setUpComponents();
		setVisible(true);
		setSize(500, 500);
	}

	/**
	 * Create the three panels to add to this GUI. One for list, one for search,
	 * one for add.
	 */
	private void setUpComponents() {
		
		// A button panel at the top for list, search, add
		buttonPanel = new JPanel();
		myListButton = new JButton("Client List");
		myListButton.addActionListener(this);

		mySearchButton = new JButton("Client Search");
		mySearchButton.addActionListener(this);

		myAddButton = new JButton("Add Client");
		myAddButton.addActionListener(this);

		buttonPanel.add(myListButton);
		buttonPanel.add(mySearchButton);

		buttonPanel.add(myAddButton);
		add(buttonPanel, BorderLayout.NORTH);

		// List Panel
		dataPanel = new JPanel();
		myTable = new JTable(mData, emploerColumnNames);
		myScrollPane = new JScrollPane(myTable);
		dataPanel.add(myScrollPane);
		myTable.getModel().addTableModelListener(this);

		// Search Panel
		searchPanel = new JPanel();
		titleLabel = new JLabel("Enter Name: ");
		employerTextField = new JTextField(25);
		employerSearchButton = new JButton("Search");
		employerSearchButton.addActionListener(this);
		searchPanel.add(titleLabel);
		searchPanel.add(employerTextField);
		searchPanel.add(employerSearchButton);

		// Add Panel
		addEmployerPanel = new JPanel();
		addEmployerPanel.setLayout(new GridLayout(8, 0));
		
		String labelNames[] = { "Company name:", "Start Date:", "Salary: ", "Position: "};
		
		for (int i = 0; i < labelNames.length; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1, 0));
			employerTextLabels[i] = new JLabel(labelNames[i]);
			employerTextFields[i] = new JTextField(25);
			panel.add(employerTextLabels[i]);
			panel.add(employerTextFields[i]);
			addEmployerPanel.add(panel);
		}

		JPanel panel = new JPanel();
		
		addEmployersButton = new JButton("Add");
		addEmployersButton.addActionListener(this);
		
		panel.add(addEmployersButton);
		
		addEmployerPanel.add(panel);
		add(dataPanel, BorderLayout.CENTER);

	}

	/**
	 * Make the buttons work!
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == myListButton) {
			//myEmployers = EmployerCollection.getEmployers();
			dataPanel.removeAll();
			myTable = new JTable(mData, emploerColumnNames);
			myTable.getModel().addTableModelListener(this);
			myScrollPane = new JScrollPane(myTable);
			dataPanel.add(myScrollPane);
			dataPanel.revalidate();
			this.repaint();

		} else if (e.getSource() == mySearchButton) {
			dataPanel.removeAll();
			dataPanel.add(searchPanel);
			dataPanel.revalidate();
			this.repaint();
		} else if (e.getSource() == myAddButton) {
			dataPanel.removeAll();
			dataPanel.add(addEmployerPanel);
			dataPanel.revalidate();
			this.repaint();

		} else if (e.getSource() == employerSearchButton) {
			String title = employerTextField.getText();
			if (title.length() > 0) {
				//myEmployers = EmployerCollection.getEmployers();
				dataPanel.removeAll();
				myTable = new JTable(mData, emploerColumnNames);
				myTable.getModel().addTableModelListener(this);
				myScrollPane = new JScrollPane(myTable);
				dataPanel.add(myScrollPane);
				dataPanel.revalidate();
				this.repaint();
				employerTextField.setText("");
			}
		} else if (e.getSource() == addEmployersButton) {
			performAddItem();
		}

	}

	/**
	 * Allows to add an Item. Only name and address is required.
	 */
	private void performAddItem() {

		String companyName = employerTextFields[0].getText();
		
		if (companyName.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a company name");
			employerTextFields[0].setFocusable(true);
			return;
		}
		
		String startDate = employerTextFields[1].getText();
		
		if (startDate.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a start date for this employer");
			employerTextFields[0].setFocusable(true);
			return;
		}
		
		String salary = employerTextFields[2].getText();
		if (salary.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a start date for this employer");
			employerTextFields[0].setFocusable(true);
			return;
		}
		double parsedSalary = Double.parseDouble(salary);
		
		String position = employerTextFields[3].getText();	
		
		if (position.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a start date for this employer");
			employerTextFields[0].setFocusable(true);
			return;
		}
		
		Employer tempEmployer = new Employer(companyName, startDate, parsedSalary, position);
		
		if (EmployerCollection.add(tempEmployer, myStudent.getStudentID())) {
			JOptionPane.showMessageDialog(null, "Employer added");
		} else {
			JOptionPane.showMessageDialog(null, "Employer failed to add");
		}
	}

	/**
	 * Listen to the cell changes on the table. 
	 */
	@Override
	public void tableChanged(TableModelEvent theEvent) {
		int row = theEvent.getFirstRow();
		int column = theEvent.getColumn();
		
		TableModel tempModel = (TableModel) theEvent.getSource();
		String columnName = tempModel.getColumnName(column);
		Object data = tempModel.getValueAt(row, column);
		
		if (data != null && ((String) data).length() != 0) {
			//Employer tempEmployer = myEmployers.get(row);
			//if (!EmployerCollection.update(tempEmployer, columnName, data)) {
				JOptionPane.showMessageDialog(null, "Update failed");
			}
		}

	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}

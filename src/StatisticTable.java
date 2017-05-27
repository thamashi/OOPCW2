import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class StatisticTable extends JFrame {
	
	
	public StatisticTable(Vector v) {
		
		this.setTitle("Statistics");
		this.setSize(900, 600);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
	
		// headers for the table
		String[] columns = new String[] { "wins", "loses", "average credits" };
		Object[][] data = new Object[][] { { "", "", ""} };
	
		DefaultTableModel tbModel = new DefaultTableModel(data, columns);
		
		JTable t = new JTable(tbModel);
		tbModel = (DefaultTableModel) t.getModel();
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(Color.LIGHT_GRAY);
		
		this.add(new JScrollPane(t));
		t.setShowVerticalLines(true);
		this.add(btnSave);
		
		tbModel.insertRow(0, v);
		this.pack();
		this.setVisible(true);
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < t.getRowCount(); i++) {
					Vector v1 = new Vector();
					v1.add(t.getValueAt(i, 0));
					v1.add(t.getValueAt(i, 1));
					v1.add(t.getValueAt(i, 2));
					Statistics(v1);
				}
				
			}
		});
	}	
	
	public void Statistics(Vector v){
	try {
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:MM:ss");
		String date2 = formatter.format(d);
		System.out.println(date2);
		File file = new File("Statistics.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedWriter Bwriter = new BufferedWriter(new FileWriter(file, true));
		for (int i = 0; i < v.size(); i++) {
			Bwriter.append(v.get(i).toString()); //enter details to the existing line
			Bwriter.newLine();

		}
		
		Bwriter.flush();
		Bwriter.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
}
	
	
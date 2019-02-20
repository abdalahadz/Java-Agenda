import java.util.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.text.SimpleDateFormat;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Zamakhshari Abd Al-Ahad (500751542)
 *
 */
public class AppointmentFrame extends JFrame{
	
	private Calendar calendar = new GregorianCalendar();
	private SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");;
	private ArrayList<Appointment> aps = new ArrayList<Appointment>();;
	
	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 700;
	
	
	
	private JLabel currentDateLabel;
	private JLabel dayLabel, monthLabel, yearLabel, hourLabel, minuteLabel;
	private JTextArea description, agenda, dayArea, monthArea, yearArea, hourArea, minuteArea;
	private JButton shiftLeft, shiftRight, showButton, createButton, cancelButton;
	
	
	/**
	 * Constructor
	 */
	public AppointmentFrame() {
		
		currentDateLabel = new JLabel(sdf.format(calendar.getTime()));
	    
	    add(currentDateLabel, BorderLayout.NORTH);
	    
		agenda = new JTextArea(5, 6);
		
		printAppointments();
	    
	    agenda.setEditable(false);
	    add(agenda, BorderLayout.CENTER);
		
		createControlPanel();
	    setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	/**
	 * @return control panel containing date, action and description subpanels
	 */
	public JPanel createControlPanel()
	   {
	      JPanel descriptionPanel = createDescriptionPanel();
	      JPanel actionGroupPanel = createActionPanel();
	      JPanel dateGroupPanel = createDatePanel();

	      // Line up component panels

	      JPanel controlPanel = new JPanel();
	      controlPanel.setLayout(new GridLayout(3, 1));
	    
	      controlPanel.add(dateGroupPanel);
	      controlPanel.add(actionGroupPanel);
	      controlPanel.add(descriptionPanel);

	      // Add panels to content pane

	      add(controlPanel, BorderLayout.SOUTH);
	      return controlPanel;
	   }
	
	/**
    Creates the Date subpanel and contained buttons.
    @return the panel containing the date shift buttons, daymonthyear text fields and show button
 */
 public JPanel createDatePanel()
 {
    shiftLeft = new JButton("<");
    shiftLeft.addActionListener(new ActionListener()
	{
		/* Goes back a day
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) 
		{
			calendar.add(Calendar.DATE, -1);
			currentDateLabel.setText(sdf.format(calendar.getTime()));
			printAppointments();
		}
	});

    shiftRight = new JButton(">");
    shiftRight.addActionListener(new ActionListener()
	{
		/* Goes ahead a day
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) 
		{
			calendar.add(Calendar.DATE, 1);
			currentDateLabel.setText(sdf.format(calendar.getTime()));
			printAppointments();
		}
	});
    
    dayLabel = new JLabel("Day");
    dayArea = new JTextArea(1,2);
    dayArea.setEditable(true);
    
    
    monthLabel = new JLabel("Month");
    monthArea = new JTextArea(1,2);
    monthArea.setEditable(true);
    
    
    yearLabel = new JLabel("Year");
    yearArea = new JTextArea(1,4);
    yearArea.setEditable(true);
    
    
    showButton = new JButton("Show");
    showButton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent event) 
		{
			int dayShown = Integer.parseInt(dayArea.getText());
			int monthShown = Integer.parseInt(monthArea.getText()) - 1;
			int yearShown = Integer.parseInt(yearArea.getText());
			calendar.set(Calendar.DAY_OF_MONTH, dayShown);
			calendar.set(Calendar.MONTH, monthShown);
			calendar.set(Calendar.YEAR, yearShown);
			currentDateLabel.setText(sdf.format(calendar.getTime()));
			dayArea.setText("");
			monthArea.setText("");
			yearArea.setText("");
			printAppointments();
		}
	});
    
    JPanel shiftButtonsPanel = new JPanel();
    shiftButtonsPanel.setLayout(new GridLayout(1,2));
    shiftButtonsPanel.add(shiftLeft).setLocation(1,1);
    shiftButtonsPanel.add(shiftRight).setLocation(1,2);
    
    JPanel inputPanel = new JPanel();
    inputPanel.add(dayLabel);
    inputPanel.add(dayArea);
    inputPanel.add(monthLabel);
    inputPanel.add(monthArea);
    inputPanel.add(yearLabel);
    inputPanel.add(yearArea);
    
    JPanel showButtonPanel = new JPanel();
    showButtonPanel.add(showButton, BorderLayout.CENTER);
    

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 1));
    panel.add(shiftButtonsPanel).setLocation(1,1);   
    panel.add(inputPanel).setLocation(2,1);
    panel.add(showButtonPanel).setLocation(3,1);
    panel.setBorder(new TitledBorder(new EtchedBorder(), "Date"));

    return panel;
 }
 
 /**
 * @return action panel with create, cancel buttons and hour, minute JTextAreas
 */
public JPanel createActionPanel()
 {
    createButton = new JButton("Create");
    
    createButton.addActionListener(new ActionListener()
	{
		/* Creates new appointment from given variables
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent event) 
		{
			int hour = Integer.parseInt(hourArea.getText());
		    int minute;
		    int day = calendar.get(Calendar.DAY_OF_MONTH);
		    int year = calendar.get(Calendar.YEAR);
		    int month = calendar.get(Calendar.MONTH);
		    
		    if (minuteArea.getText().trim().isEmpty())
		    {
		    	minute = 00;
		    }
		    
		    else minute = Integer.parseInt(minuteArea.getText());
			createAppointment(year, month, day, hour, minute);
			hourArea.setText("");
			minuteArea.setText("");
			description.setText("");
		}
	});

    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(new ActionListener()
	{
    	/* Cancels appointment from given variables
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent event) 
		{
			int hour = Integer.parseInt(hourArea.getText());
		    int minute = Integer.parseInt(minuteArea.getText());
		    int day = calendar.get(Calendar.DAY_OF_MONTH);
		    int year = calendar.get(Calendar.YEAR);
		    int month = calendar.get(Calendar.MONTH);
			cancelAppointment(year, month, day, hour, minute);
			hourArea.setText("");
			minuteArea.setText("");
			description.setText("");
		}
	});
    
    hourLabel = new JLabel("Hour");
    hourArea = new JTextArea(1,2);
    hourArea.setEditable(true);
    
    
    minuteLabel = new JLabel("Minute");
    minuteArea = new JTextArea(1,2);
    minuteArea.setEditable(true);
    
    
    JPanel hourMinutePanel = new JPanel();
    hourMinutePanel.add(hourLabel);
    hourMinutePanel.add(hourArea);
    hourMinutePanel.add(minuteLabel);
    hourMinutePanel.add(minuteArea);
    
    JPanel createCancelPanel = new JPanel();
    createCancelPanel.add(createButton, BorderLayout.CENTER);
    createCancelPanel.add(cancelButton, BorderLayout.CENTER);
    

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 1));
    panel.add(hourMinutePanel).setLocation(1,1);   
    panel.add(createCancelPanel).setLocation(2,1);
    
    panel.setBorder(new TitledBorder(new EtchedBorder(), "Action"));

    return panel;
 }
 
 /**
  * adds a JTextArea for the description of the event
 * @return the description panel, a subpanel of the control panel
 */
public JPanel createDescriptionPanel()
 {
    description = new JTextArea();
    description.setEditable(true);
    
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 1));
    panel.add(description).setLocation(1,1);   
    
    panel.setBorder(new TitledBorder(new EtchedBorder(), "Description"));

    return panel;
 }
 
 /**
 * Clears the agenda JTextArea and reprints the members of the arraylist that satisfy the date
 */
public void printAppointments()
 {
	 agenda.setText("");
	 for(int i = 0; i < aps.size(); i++)
	 {
		 if (aps.get(i).getYear() == calendar.get(Calendar.YEAR)  && aps.get(i).getMonth() == calendar.get(Calendar.MONTH) && aps.get(i).getDay() == calendar.get(Calendar.DAY_OF_MONTH))
		 {
			 agenda.append(aps.get(i).print() + "\n");
		 }
	 }
 }
 
 public boolean findAppointment(int year, int month, int day, int hour, int minute)
 {
	
	 for(int i = 0; i < aps.size(); i++)
	 {
		
		 while (aps.get(i).occursOn(year, month, day, hour, minute) == true)
		 {
			 return true;
			 
		 }
	 }
	 
	 return false;

 }
 
 /**
 * @param year
 * @param month
 * @param day
 * @param hour
 * @param minute
 */
public void createAppointment(int year, int month, int day, int hour, int minute)
 {
	 Appointment newApp = new Appointment(year, month, day, hour, minute, description.getText());
	 if (findAppointment(year, month, day, hour, minute) == true)
	 {
		 description.setText("CONFLICT!!");
	 }
	 
	 else
	 {
		 aps.add(newApp);
		 Collections.sort(aps);
		 printAppointments();
	 }
 }
 
 /**
 * @param year
 * @param month
 * @param day
 * @param hour
 * @param minute
 */
public void cancelAppointment(int year, int month, int day, int hour, int minute)
 {
	 for(int i = 0; i < aps.size(); i++)
	 {
		if (aps.get(i).occursOn(year,month,day,hour,minute) == true) 
		{
			aps.remove(i);
		}
	 }
	 
	 printAppointments();
 }
 
}



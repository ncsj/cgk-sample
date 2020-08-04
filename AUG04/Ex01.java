import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

import  jp.ncsj.datamanager.DataManager;
import  jp.ncsj.datamanager.Employee;

class Ex01 extends Frame{
	ArrayList <Employee> empList = new ArrayList <Employee> ();

	List	list = new List();

	TextField  field1 = new TextField();		// ID
	TextField  field2 = new TextField();		// NAME
	TextField  field3 = new TextField();		// DIV

	Button  btn = new Button("CLOSE");
	
	Button  btn1 = new Button("ADD");
	Button  btn2 = new Button("DELETE");

	DataManager manager = null;

	public Ex01(int type){
		this.manager = DataManager.getInstance(type);

		setBounds(0,0,630,440);
		setLayout(null);

		add(list);
		list.setBounds(30,60,100,340);
		list.addItemListener((ItemEvent e)->{ selectEmployee(); });

		initFields();

		add(btn1);
		btn1.setBounds(150,160,100,30);
		btn1.addActionListener((ActionEvent e)->{addEmployee();});

		add(btn2);
		btn2.setBounds(150,190,100,30);
		btn2.addActionListener((ActionEvent e)->{removeEmployee();});


		add(btn);
		btn.setBounds(265,390,100,30);
		btn.addActionListener((ActionEvent e)->{ close(); });

		addWindowListener(new WindowAdapter(){
			public void windowOpened(WindowEvent e){
				loadData();
			}
			public void windowClosing(WindowEvent e){
				close();
			}
		});

		setVisible(true);
	}

	void loadData(){
		Employee [] emps = manager.list();

		for(Employee emp : emps){
			empList.add(emp);
		}
		
		setList();
	
	}

	void setList(){
		for(Employee emp : empList){
			String name = emp.get(Employee.NAME);
			list.add(name);
		}
	}

	void saveData(){
		for(Employee emp : empList){
			manager.save(emp);
		}
	}

	void addEmployee(){
		String id	= field1.getText();
		String name = field2.getText();
		String div  = field3.getText();

		Employee emp = new Employee(id,name,div);

		list.add(name);
		empList.add(emp);

		setFields("","","");
	}

	void setFields(String s1,String s2,String s3){
		field1.setText(s1);
		field2.setText(s2);
		field3.setText(s3);
	}

	void selectEmployee(){
		int index = list.getSelectedIndex();
		if(index > -1){
			Employee emp = empList.get(index);

			String id = emp.get(Employee.ID);
			String name = emp.get(Employee.NAME);
			String div  = emp.get(Employee.DIV);
			setFields(id,name,div);
		}
	}

	void removeEmployee(){
		int index = list.getSelectedIndex();
		if(index > -1){
			list.remove(index);
			empList.remove(index);

			setFields("","","");
		}
	}

	void initFields(){
		{
			Label label = new Label("ID");
			add(label);
			label.setBounds(200,60,60,20);

			add(field1);
			field1.setBounds(280,60,80,20);
		}

		{
			Label label = new Label("NAME");
			add(label);
			label.setBounds(200,90,60,20);

			add(field2);
			field2.setBounds(280,90,200,20);
		}

		{
			Label label = new Label("DIV");
			add(label);
			label.setBounds(200,120,60,20);

			add(field3);
			field3.setBounds(280,120,200,20);
		}
	}

	void close(){
		saveData();
		manager.close();
		
		System.out.println("Close Window.");
		setVisible(false);
		dispose();
	}

	public static void main(String args[]){
		int type = 0;

		switch(args[0]){
			case "FILE":
			case "File":
			case "file":
				type = DataManager.FILE;
				break;
			case "DBMS":
			case "SQL":
				type = DataManager.DBMS;
				break;
			default:
				break;
		}

		new Ex01(type);
	}
}

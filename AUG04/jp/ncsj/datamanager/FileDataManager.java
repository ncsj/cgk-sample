package	jp.ncsj.datamanager;

import  java.io.*;
import  java.util.ArrayList;

public class FileDataManager extends DataManager{
	FileOutputStream fout = null;
	ObjectOutputStream os = null;

	@Override
	public void save(Employee emp){
		try{
			if(fout == null){
				fout = new FileOutputStream("employee.data");
				os   = new ObjectOutputStream(fout);
			}

			os.writeObject(emp);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}

	@Override
	public Employee [] list(){
		Employee [] emps = null;

		ArrayList <Employee> array = new ArrayList <Employee> ();

		FileInputStream fin = null;
		ObjectInputStream is = null;
		try{
			fin = new FileInputStream("employee.data");
			is = new ObjectInputStream(fin);

			while(true){
				Employee emp = (Employee)is.readObject();
				if(emp != null){
					array.add(emp);
				}
			}

		}
		catch(EOFException e){
			;
		}
		catch(Exception e){
			System.out.println(e.toString());
		}

		try{
			is.close();
			fin.close();
		}
		catch(Exception e){
		}

		emps = new Employee [array.size()];
		for(int i=0;i<array.size();i++){
			emps[i] = array.get(i);
		}
	
		return emps;
	}

	@Override
	public void close(){
		try{
			if(os != null){
				os.close();
			}

			if(fout != null){
				fout.close();
			}
		}
		catch(IOException e){
			System.out.println(e.toString());
		}
	}
}

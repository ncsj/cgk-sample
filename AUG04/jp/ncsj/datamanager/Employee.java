package	jp.ncsj.datamanager;

import  java.io.Serializable;

public class Employee implements Serializable{
	String  id;
	String  name;
	String  div;

	public final static int ID		= 0;
	public final static int NAME	= 1;
	public final static int DIV		= 2;

	public Employee(){
	}

	public Employee(String id,String name,String div){
		this.id		= id;
		this.name	= name;
		this.div	= div;
	}

	public void set(String id,String name,String div){
		this.id		= id;
		this.name	= name;
		this.div	= div;
	}

	public String get(int field){
		String value = null;

		switch(field){
			case ID:
				value = this.id;
				break;
			case NAME:
				value = this.name;
				break;
			case DIV:
				value = this.div;
				break;
			default:
				;
				break;
		}

		return value;
	}
}

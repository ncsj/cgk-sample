package		jp.ncsj.datamanager;

public abstract class DataManager{
	public abstract void save(Employee emp);
	public abstract Employee [] list();
	public abstract void close();

	public static final int FILE = 0;
	public final static int DBMS = 1;

	public static DataManager getInstance(int type){
		DataManager manager = null;
		switch(type){
			case FILE:
				manager = new FileDataManager();
				break;
			case DBMS:
				manager = new DBMSDataManager();
				break;
			default:
				break;
		}
		return manager;
	}
}

public class Session {
	int week;
	char sess;
	public Session(){}
	public Session(int w, char s)
	{
		week = w;
		sess = s;
	}
	public int getWeek()
	{
		return week;
	}
	public char getSession()
	{
		return sess;
	}
	public void show()
	{
		System.out.println("["+week+"]"+sess);
	}
	public void setWeek(int week)
	{
		this.week = week;
	}
	public void setSess(char s)
	{
		sess = s;
	}
	
}

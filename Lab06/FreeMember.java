
public class FreeMember extends Member {

	

	private int FREE_LIMITED_VDOs =3;
	private int numDownloadVDO = 0;
	
	public FreeMember(String email, String password) {
		super(email, password);
	}
	@Override
	
	public boolean addVideo(Video vdo)
	{
		if(getnum()==FREE_LIMITED_VDOs)
		{		super.printMemberInfo();
			System.out.println("cannot be downloaded because the number of video is reaching the limit");
			return false;
			
	}
		else
		{
			super.addVideo(vdo);
			numDownloadVDO++;
			return true;
		}
	}
	@Override
	public boolean removeVideo(Video vdo) {
		
		if(super.removeVideo(vdo)  ==false )
		{
			return false;
		}
		else 
		{
			super.removeVideo(vdo);
			numDownloadVDO--;
			return true;
		}
	}
	@Override
	public void printMemberInfo()
	{
	System.out.println("----FREE MEMBER----");
	super.printMemberInfo();
	}
	public int getnum()
	{
		return numDownloadVDO;
	}
	
}

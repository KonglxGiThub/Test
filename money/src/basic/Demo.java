package basic;


/**
 * ����滻ģʽ
 * �ȼ�������Ϊĳ��ֵ
 * �ж������Ƿ����,�������,�������޸ĳ���Ӧ��ֵ
 * 
 * @author new
 *
 */
public class Demo 
{
	public static void main(String[] args)
	{
	   String pname="���";
	   Demo.checkPerson(pname);
	}
	
	
	private static void checkPerson(String pname)
	{
		String msg=" ����";
		if(pname.equals("���"))
		{
			msg=" ���µ�һ�쵰";
		}
		System.out.println(pname+msg);
	}

}

package test;

import java.util.Map;

import com.neusoft.services.A1010Services;

public class A1010ServicesTest 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try 
		{
			A1010ServicesTest
			   //.addTest();
			   //.deleteTest();
			    .findByIdTest(); 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
	
	private static void findByIdTest()throws Exception
	{
		A1010Services services=new A1010Services();
		Map ins=services.findById("105");
		System.out.println(ins);
	}
	
	private static void deleteTest()throws Exception
	{
		A1010Services services=new A1010Services();
		String msg=services.delete("83")?"����ɾ���ɹ�!":"����ɾ��ʧ��!";
		System.out.println(msg);
	}
	
	private static void addTest()throws Exception
	{
		A1010Services services=new A1010Services();
		String val[]={"���¹���","5000","1","2015-10-27",null};
		String msg=services.add(val)?"��ӳɹ�!":"���ʧ��!";
		System.out.println(msg);
	}

}

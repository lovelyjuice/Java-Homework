import java.util.ArrayList;
import java.util.Scanner;

public class BankAccount
{
	private int id;
	private static int autoincrease=1;
	public String name;
	private StringBuffer passwd=new StringBuffer("");
	private double balance;
	
	BankAccount(String name,String passwd)
	{
		this.id=autoincrease++;
		this.name=name;
		this.passwd.append(passwd);
	}

	public double deposit(double a)
	{
		this.balance+=a;
		return balance;
	}
	
	public void withdraw(double a)
	{
		if(a>balance)System.out.println("钱不够了哦.....(⊙o⊙)");
		else balance-=a;
	}
	
	public boolean auth(StringBuffer password)
	{
		if (this.passwd.toString().equals(password.toString()))return true;
		//StringBuffer没有重写Object类的equals方法
		else return false;
	}
	
	public boolean changePasswd()
	{
		System.out.println("请输入原密码");
		Scanner in=new Scanner(System.in);
		String input=in.next();
		if(this.passwd.toString().equals(input))
		{
			System.out.println("请输入新密码");
			this.passwd.delete(0,this.passwd.length());
			this.passwd.append(in.next());
			System.out.println("密码更改成功！");
			return true;
		}
		else System.out.println("原密码错误！");
		return false;
	}
	
	public static void main(String[] args) 
	{
		Bankmgr manager=new Bankmgr();
		Scanner in=new Scanner(System.in);
		while(true)
		{
			System.out.println("请选择 1.登录    2.注册");
			int a=in.nextInt();
			if(a==1)
			{
				if(manager.login())
				{
					System.out.println("Login successfully!");
					int tag=1;
					while(tag==1)
					{
						System.out.println("请输入你需要的操作： 1.存款   2.取款   3.改密  4.查询 5.退出");
						switch (in.nextInt())
						{
						case 1:System.out.print("存款金额:");manager.currentuser.deposit(in.nextDouble());break;
						case 2:System.out.print("取款金额:");manager.currentuser.withdraw(in.nextDouble());break;
						case 3:manager.currentuser.changePasswd();break;
						case 4:System.out.println("ID:"+manager.currentuser.id+"  Name:"+manager.currentuser.name+"  Balance:"+manager.currentuser.deposit(0));break;
						case 5:tag=0;break;
						}
					}
				}
				else System.out.println("用户不存在或密码错误");
			}
			else if(a==2) manager.reg();
		}
	}
}

class Bankmgr
{
	Scanner in=new Scanner(System.in);
	ArrayList<BankAccount> list=new ArrayList<BankAccount>();
	BankAccount currentuser;
	
	boolean login()
	{
		System.out.print("input username:");
		String username=in.next();
		System.out.print("input password:");
		StringBuffer password=new StringBuffer("");
		password.append(in.next());
		boolean auth=false;
		for(int i=0;i<list.size();i++)
		{
			currentuser=list.get(i);
			if (currentuser.name.equals(username)&&currentuser.auth(password))auth=true;
		}	
		return auth;
	}
	
	boolean reg()
	{
		boolean crash=false;					
		System.out.print("请输入名字：");
		String username=in.next();
		System.out.print("请输入密码：");
		String password=in.next();
			for(int i=0;i<list.size();i++)
			{					
				currentuser=list.get(i);
				if (currentuser.name.equals(username))
					{crash=true;break;}
			}
			if(crash==true)System.out.println("用户名已被占用！请重试");
		else this.list.add(new BankAccount(username,password));
		return crash;
	}
	
}



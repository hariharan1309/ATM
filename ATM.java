package atm;
import java.util.*;
class Customer extends User{
	static Scanner get=new Scanner(System.in);
	static User x=new User();
	void operations(User User) {
		int x=1;
		while(x<=7) {
			System.out.println("Enter the Operation");
			System.out.println("1 => WITHDRAW\n"+"2 => CHECK\n"+"3 => DEPOSIT\n"+"4 => PIN CHANGE\n"+"5 => TRANSFER\n6 => MINI STATEMENT \n7 => BACK");
			x=get.nextInt();
		switch(x) {
		case 1:
			withdraw(User);
			break;
		case 2:check(User);
			break;
		case 3:deposit(User);
			break;
		case 4:change(User);
			break;
		case 5:
			get.nextLine();
			System.out.println("Enter the Name");
			String n=get.nextLine();
			System.out.println("Enter the Id");
			String id=get.nextLine();
			trans(User,n,id,ATM.arr);
			break;
		case 6:
			if(User.min.size()==0) {
				System.out.println("Empty!!!");
			}
			else {
				for(String s:User.min) {
					System.out.println(s);
				}
			}
			break;
		case 7:
			ATM.main(null);
		default:
			System.out.println(">> Enter a Valid Operation !!!");
			operations(User);
		}
	}
	}
	static void trans(User u,String Name,String uId,ArrayList<User> arr) {
		int flag=0;
		for(User a:arr) {
			if(a.uId.equals(uId) &&a.Name.equals(Name)) {
				System.out.println("Enter the Amount :");
				int x=get.nextInt();
				flag=1;
				if(u.bal>100) {
					if(u.bal-x<100) {
						System.out.println("You must need to maintain your minimum balance of 100");
						System.out.println("Your Balance is "+u.bal+" Which is Lesser");
					}
					else {
						flag=0;
						a.bal+=x;				
						System.out.println("Amount Transfered Successfully");
						u.bal-=x;
						System.out.println("Your Balance is "+u.bal);
						if(u.min.size()<6) {
						u.min.add(">> Transfered "+x+" to "+a.Name);
						}
						else {
							u.min.remove(0);
							u.min.add(">> Transfered "+x+" to "+a.Name);							
						}
					}
				}
			 if(flag==1) {
				System.out.println("Some thing is wrong !");
				System.out.println("Account Not found");
			 	}
			}
		}
	}
	void withdraw(User u) {
		System.out.println("Enter the Amount :");
		int x=get.nextInt();
		if(x>u.bal) {
			System.out.println("Insufficient Balance");
		}
		else if(u.bal<100 ||u.bal-x<100) {
			System.out.println("You must need to maintain your minimum balance of 100");
			System.out.println("Your Balance is "+u.bal+" Which is Lesser");
		}
		else {
			u.bal=u.bal-x;
			ATM.Bal=ATM.Bal-x;
			System.out.println("Your Balance is :"+u.bal);
			if(u.min.size()<6) {
				u.min.add(">> Withdrawed "+x);
				}
				else {
					u.min.remove(0);
					u.min.add(">> Withdrawed "+x);
					
				}
		}
	}
	void check(User u) {
		System.out.println("Your Balance is :"+u.bal);
	}
	void deposit(User u) {
		System.out.println("Enter the Amount");
		int x=get.nextInt();
		u.bal+=x;
		ATM.Bal+=x;
		System.out.println("Deposited Successfully");
		System.out.println("Your Balance is :"+u.bal);
		if(u.min.size()<6) {
			u.min.add(">> Deposited "+x);
			}
			else {
				u.min.remove(0);
				u.min.add(">> Deposited "+x);
			}

	}
	void change(User u) {
		System.out.println("Enter the current password");
		get.nextLine();
		String cp=get.nextLine();
		if(u.pass.equals(cp)) {
			System.out.println("Enter the New Password");
			String a=get.nextLine();
			System.out.println("Re-enter the password");
			String b=get.nextLine();
			if(a.equals(b)) {
				System.out.println("Password Changed Successfully");
				this.pass=a;
				if(u.min.size()<6) {
					u.min.add(">> Changed the Password");
					}
					else {
						u.min.remove(0);
						u.min.add(">>Changed the Password");
					}

			}
			else {
				System.out.println("Password doesn't match");
			}
		}
		else {
			System.out.println("Your password is wrong");
		}
	}
}
class Admin extends Customer{
	static int tth=20;
	static int fh=15;
	static int th=20;
	static int hh=25;
	Scanner get=new Scanner(System.in);
	String uId;
	String pass;
	Admin(){	
	}
	static long Bbal=ATM.Bal;
	Admin(String uId,String pass){
		this.uId=uId;
		this.pass=pass;
	}
	
	void operation() {
		int x=1;
		while(x<4) {
			System.out.println("Enter the Operation");
			System.out.println("1 => DENOMINATE\n2 => CHECK\n3 => BACK");
			x=get.nextInt();
		switch(x) {
		case 1:
			demonite();
			break;
		case 2:check();
			break;
		case 3:
			ATM.main(null);
		default:
				System.out.println(">> Enter a Valid Operation !!!");
				operation();
			}
		}
	}
	void demonite() {
		System.out.println("No of 2000 notes");
		int x=get.nextInt();
		Admin.tth+=x;
		System.out.println("No of 500 Notes");
		x=get.nextInt();
		Admin.fh+=x;
		System.out.println("No of 200 Notes");
		x=get.nextInt();
		Admin.th+=x;
		System.out.println("No of 100 Notes");
		x=get.nextInt();
		Admin.hh+=x;
		ATM.Bbal=0;
		ATM.update();		
	}
	 void check() {
		 System.out.println("The Balance in ATM is "+Bbal);		
	}
}
public class ATM extends Admin{
	static Scanner get=new Scanner(System.in);
	static long Bal;
	static int ct=0;
	static Customer cus=new Customer();
	static Admin ad=new Admin();
	static User ur=new User();
	static ArrayList<User> arr=new ArrayList<>();
	static ArrayList<Admin> arr1=new ArrayList<>();
	static void ulogin() {	
		boolean b=true;
		System.out.println("Enter the ID");
		String i=get.nextLine();
		System.out.println("Enter the Password");
		String p=get.nextLine();
		for(int i1=0;i1<arr.size();i1++) {
			if(arr.get(i1).uId.equals(i) && arr.get(i1).pass.equals(p)) {
				cus.operations(arr.get(i1));
			}
			else {
				b=false;
			}
		}
		if(b==false) {
			ct++;
			if(ct<3) {
				System.out.println("---Please Retry !!!---");
				ulogin();
			}
			else {
				System.out.println("You reached the try Limit !!!\n");
			}
		}
	}
	static void update() {
		Bbal+=th*2000+fh*500+th*200+hh*100;
	}
	static void alogin() {	
		boolean b=true;
		System.out.println("Enter the ID");
		String i=get.nextLine();
		System.out.println("Enter the Password");
		String p=get.nextLine();
		for(int i1=0;i1<arr1.size();i1++) {
			if(arr1.get(i1).uId.equals(i) && arr1.get(i1).pass.equals(p)) {
				b=true;
				ad.operation();
				break;
			}
			else {
				b=false;
			}
		}
		if(b==false) {
			ct++;
			if(ct<3) {
				System.out.println("---Please Retry !!!---");
				alogin();
			}
			else {
				System.out.println("You reached the try Limit !!!");
			}
		}
	}
	public static void main(String[] args) {
		update();
		User s=new User("Hariharan","19EC034","1309","AXIS",12000);
		User s1=new User("Charan","19EC034","20501","ICICI",12000);
		User s2=new User("Bose","19EC038","1309","AXIS",17000);
		Admin a=new Admin("11ad1122","112buhu" );
		Admin a1=new Admin("10adb82u9","nsjs113");
		arr.add(s);
		arr.add(s2);
		arr.add(s1);
		arr1.add(a1);
		arr1.add(a);
		int x=0;
		while(x!=3) {
		System.out.println("Welcome to the FF ATM !!!");
		System.out.println("1> User \n2> Admin\n3> Exit");
		x=get.nextInt();
		get.nextLine();
		if(x==1) {
			ulogin();
			continue;
		}
		if(x==2) {
			alogin();
			continue;
		}
		else {
			System.out.println("Thank You !!!");
			break;
		}
		}
	}
}

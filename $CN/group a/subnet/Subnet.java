import java.util.Scanner;
public class Subnet {
	public static void main(String args[])
	{
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter the ip address: ");
	int[] ipint= new int[4];
	String ip = sc.nextLine();
	String split_ip[] = ip.split("\\."); //SPlit the string after every .
	//String split_ip[] = ip.split("\\."); //SPlit the string after every .
	String split_bip[] = new String[4]; //split binary ip
	String bip = "";
	for(int i=0;i<4;i++)
	{
	ipint[i]= Integer.parseInt(split_ip[i]);
	}
	System.out.println("Ip: ");
	for(int i=0;i<4;i++)
	{
	System.out.print(ipint[i]);
    System.out.print(".");
	}
	
	//System.out.println("IP in binary is "+bip);
	
	System.out.print("Enter the number of bits: ");
	int n = sc.nextInt();
    
	//Calculation of mask
	int bits = (int)Math.ceil(Math.log(n)/Math.log(2)); /*eg if address = 120, log 120/log 2 gives log to the base 2 => 6.9068, ceil gives us upper integer */
	//System.out.println("Number of bits required for address = "+bits);
	//int maskbits = 32-bits;
	int[] mask=new int[4];
	int j=0,x,k;
	//
	
	if(ipint[0]<127)
	{  
		j=0;
		
		mask[j]=255;
		mask[j+1]=0;
		mask[j+2]=0;
		mask[j+3]=0;
		
		for(int i=1;i<4;i++)
		{
			if(n<8)
			{ 
				
				
				for(j=7,k=0;k<n;j--,k++)
				{
				
					
					mask[i]=mask[i]+(int) Math.pow(2,j);
				
				}
			break;	
			}
			else
			{
			mask[i]=255;
		    n=	n-8;
			}
		}
	
		System.out.println("class A");
	}
	//class B
	else if(ipint[0]<191)
	{System.out.println("class b");
		j=0;
		mask[j]=255;
		mask[j+1]=255;
		mask[j+2]=0;
		mask[j+3]=0;
		
		for(int i=2;i<4;i++)
		{
			if(n<8)
			{ 
				
				
				for(j=7,k=0;k<n;j--,k++)
				{
					
					
					mask[i]=mask[i]+(int) Math.pow(2,j);
					
				}
			break;	
			}
			else
			{
			mask[i]=255;
		    n=	n-8;
			}
		}
	}
	//class C
	else if(ipint[0]<223)
	{
		System.out.println("class c");
		j=0;
		mask[j]=255;
		mask[j+1]=255;
		mask[j+2]=255;
		mask[j+3]=0;
		
		for(int i=3;i<4;i++)
		{
			if(n<8)
			{ 
				for(j=7,k=0;k<n;j--,k++)
				{
					mask[i]=mask[i]+(int) Math.pow(2,j);
					
				}
			break;	
			}
			else
			{
			mask[i]=255;
		    n=	n-8;
			}
		}
	}
	System.out.println("The subnet mask is = ");
	for(int i=0;i<4;i++)
	System.out.print(mask[i]+".");
	int[] netid=new int[4];
System.out.println("The Network ID is =");	
	for(int i=0;i<4;i++)
	{
		netid[i]=ipint[i]&mask[i];
	}
	
	for(int i=0;i<4;i++)
	System.out.print(netid[i]+".");

	}
	
}

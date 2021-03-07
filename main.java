import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

class main
{
	public static void main(String args[])
	{
		Scanner sobj=new Scanner(System.in);
		System.out.println("--------------------------");
		System.out.println("Marvallous Packer_Unpacker");
		
		while(true)
		{
		System.out.println("--------------------------");
		
		System.out.println("Enter Your Choice \n");
		System.out.println("1:Packing");
		System.out.println("2:Unpacking");
		System.out.println("3:Exit");
		System.out.println("--------------------------");
		
		int	 Choice=0;
		String Dir,Filename;
		Choice=sobj.nextInt();
		
		switch(Choice)
		{
			case 1:
			
			System.out.println("Enter Directry Name");
			Dir=sobj.next();
			
			System.out.println("Enter The File Name For Packing");
			Filename=sobj.next();
			
			packer pobj=new packer(Dir,Filename);
			
			break;
			
			case 2:
			
			System.out.println("Enter Packed File Name");
			String name=sobj.next();
			Unpacker obj = new Unpacker(name);
			break;
			
			case 3:
			
			System.out.println("Thank For Using Marrvallous Packer Unpacking App");
			System.exit(0);
			break;
			
			default:
			
			System.out.println("Wrong Choice");
			break;
		}
		}
	}
	
} 
     class packer
	 {
		 // object for file writing
		 public FileOutputStream outstream= null;
		 
		 // Parametrised Constructor
		 public packer(String FolderName,String FileName)
		 {
			 try
			 {
			 System.out.println("Inside Packer Constructor");
			 
			 //create new file for packing
			 File outfile=new File(FileName);
			 outstream = new FileOutputStream(FileName);
			 
			 //set the Currant working directory for folder Travelsal.
			 // System.setProperty("User.id",FolderName);
			 TravelDirectory(FolderName);
			 }
			 catch(Exception obj)
			 {
				 System.out.println(obj);
			 }
		 }
		 
		 public void TravelDirectory(String path)
		 {
			 File directoryPath=new File(path);
			 int counter=0;
			 
			 // Get All file names from directory
			 File arr[] = directoryPath.listFiles();
			 
			 
			 System.out.println("--------------------------");
			 
			 for(File filename : arr)
			 {
				 //System.out.println(filename.getName());
				 //System.out.println(filename.getAbsolutePath());
				 
				 if(filename.getName().endsWith(".txt"))
				 {
					 counter++;
					 System.out.println("Packed File"+filename.getName());
				 PackFile(filename.getAbsolutePath());
				 }
			 }
			 System.out.println("--------------------------");
			 
			 System.out.println("Sucessfull paked File :"+counter);
			 System.out.println("--------------------------"); 
		 }  
		 
		 public void PackFile(String FilePath)
		 {
			 // packing logic
			 //System.out.println("File name received"+FilePath);
			 byte Header[]=new byte[100];
			 byte Buffer[]=new byte[1024];
			 int length=0;
			 
			 FileInputStream istrem=null;
			 
			 File fobj = new File(FilePath);
			 
			 String temp = FilePath+" "+fobj.length();
			 // Create Header of 100 bytes
			 for(int i= temp.length();i<100;i++)
			 {
				 temp = temp +" ";
			 } 
			 
			 Header = temp.getBytes();
			 try
			 {
				 // open the file for reading
				 istrem=new FileInputStream(FilePath);
				 
				 outstream.write(Header,0,Header.length);
				 while((length=istrem.read(Buffer))>0)
				 {
					 outstream.write(Buffer,0,length);
				 }
				 istrem.close();
				 
			 }
			 catch(Exception obj)
			 {}
			 
		 }
		 
	 }

       class Unpacker
	   {
		 public FileOutputStream outstream=null;
          
         public Unpacker(String src)
		 {
			 System.out.println("Inside Unpacker");
			 unpackFile(src);
		 }		 
		 public void unpackFile(String FilePath)
		 {
			 try
			 {
				 FileInputStream instream = new FileInputStream(FilePath);
				 byte Header[] = new byte[100];
				 int length=0;
				 int counter=0;
				 
				 while((length = instream.read(Header,0,100)) > 0)
				 {
					 String str = new String(Header);
					 String ext = str.substring(str.lastIndexOf("\\"));
					 ext = ext.substring(1);
					 
					 
					 String words[]=ext.split("\\s");
					 String name = words[0];
					 int size=Integer.parseInt(words[1]);
					 
					 byte arr[] = new byte[size]; 
					 instream.read(arr,0,size);
					 
					 System.out.println("New File Gets Created:"+name);
					 
					 FileOutputStream fout = new FileOutputStream(name);
					 fout.write(arr,0,size);
					 counter++;
				 }
				 System.out.println("Sucessfull Unpacked File :"+counter);
				 
				 
			 }
			 catch(Exception obj)
			 {
				 
			 }
			 
		 }
	   }







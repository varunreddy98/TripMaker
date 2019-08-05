package pp1;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
class Jframedemo implements ActionListener{
 	String str;
 	JButton bd;
 	static Date d[]=new Date[7];
 	static Person pa;
 	static boolean check=true;
 	static JLabel j2;
 	static JLabel j3=new JLabel();
 	static Person p[]=new Person[10];
 	static Person cp=null;
 	static int c=0,dc=0;
 	static JPanel p1,jpp,j1;
 	static JTextField mail;
 	static JPasswordField pass;
 	static JFrame j=new JFrame("varun's blog");
 	static JTabbedPane jp=new JTabbedPane();
 	static
 	{   
 		j.setVisible(true);
 		j.add(jp);
 		
 	}
 	Jframedemo()
 	{

 	}
 	Jframedemo(int n)
 	{
 		p1=new JPanel();
 		new Entry1(p1,pass,mail,j2);
 		p1.add(j2);
 		jp.add(p1);
 		//jp.add(p2);
 		j.addWindowListener(new WindowAdapter() {
 		   public void windowClosing(WindowEvent evt) {
 			     try {
					onExit();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 			   }
		});
 	}
 	public void onExit() throws IOException
 	{
		FileOutputStream fo=new FileOutputStream("E:\\java\\programs\\My\\friendsf.txt");
		ObjectOutputStream oj=new ObjectOutputStream(fo);
		for(int i=0;i<Jframedemo.c;i++)
		{   //Jframedemo.p[i].display();
		     System.out.println(p[i]);
			oj.writeObject(Jframedemo.p[i]);
		}
		oj.flush();
		oj.close();
 	 System.out.println("closed");
 	 System.exit(0);
 	}
 	public void actionPerformed(ActionEvent e) {
 		int indc=0;
 		str=e.getActionCommand();
 		if(str.equals("Login"))
 		{   String em=mail.getText();
 			String pa;
 			pa=pass.getText();
 			System.out.println(em+" "+pa);
 			//System.out.println(p[0]);
 			if(c!=0) {
 				for(int i=0;i<c;i++)
 				{if((p[i].email).equals(em)&&(p[i].password).equals(pa))
 					      {indc=1;
 					        cp=p[i];
 					        break;
 					      }
 				  // System.out.println(p[i]+" "+indc);
 				} 				if(indc==1) { 
 					jpp=new JPanel(new GridLayout(1,3));
 					Menupage l=new Menupage(jpp);
 					jp.remove(p1);
 					jp.add(jpp);
 				}
 			else
				{j2.setText("Invalid Email or Password");
				}
 			}

 		}
 		if(str.equals("Create account"))
 		{ JPanel aa=new JPanel(new GridBagLayout());
 		Createacc p=new Createacc(aa,jp,bd);
 		jp.remove(p1);
 		jp.add(aa);
 		//while(p.check()==0);
 		}
 	}
 	// TODO Auto-generated method stub
 	public static void main(String args[]) throws IOException, ClassNotFoundException,FileNotFoundException, InterruptedException
 	{   File f=new File("E:\\java\\programs\\My\\friendsf.txt");
 		FileInputStream oi=new FileInputStream(f);
 		ObjectInputStream os = null;
 		try{os=new ObjectInputStream(oi);
 		Person pp=null;
 		while((pp=(Person) os.readObject())!=null) {
 		System.out.println(pp);
 		Jframedemo.p[Jframedemo.c++]=pp;	
 		}
 		os.close();
 		}
 		catch(EOFException e)
 		{
 			System.out.println(e);
 		}
 		catch(Exception e)
 		{
 			System.out.println(e);
 		}
 		Jframedemo d=new Jframedemo(1);
 		
 	}

 	public static void createPanel()
 	{p[c++]=pa;
 	  //System.out.println(p[c-1].email);
 	 p1=new JPanel();
 	Entry1 e=new Entry1(p1,pass,mail,j2);
 	jp.add(p1);
 	
 	}
 	class Menupage extends JPanel implements ActionListener
 	{   Menupage(JPanel jp1)
 		{int c=0;
 		 JPanel p1=new JPanel(new GridBagLayout());
 		 GridBagConstraints cp=new GridBagConstraints();
 		 cp.gridx=0;
 		 cp.gridy=0;
 		for(int i=0;i<Jframedemo.c;i++)
 		{  if(!(Jframedemo.p[i].fname).equals(Jframedemo.cp.fname))
 		        { c++;
 		         if(c==1)
 		         {cp.gridy=i+1;
 		           JLabel lb=new JLabel("Your Friends");
 		          lb.setFont(new Font("Serif",Font.BOLD,35));
 		          p1.add(lb,cp);
 		         }
 			       cp.gridy=i+2;
 			       JLabel lab=new JLabel(Jframedemo.p[i].lname);
 			       lab.setFont(new Font("Serif",Font.BOLD,30));
 			       p1.add(lab,cp);
 			       System.out.println("i"+Jframedemo.p[i]+"    ");  
 		        }
 		}	
 		p1.setBackground(Color.green);
 		jp1.add(p1,BorderLayout.WEST);
 		JPanel p2=new JPanel(new GridLayout(3,1));
 	    JButton jb1=new JButton("EXPENSES");
 		JButton jb2=new JButton("REPORTS");
 		JLabel ll=new JLabel("                                                      HEllo!"+" "+Jframedemo.cp.fname);
 		ll.setFont(new Font("Serif",Font.BOLD,23));
 		p2.add(ll);
 		p2.add(jb1);
 		p2.add(jb2);
 		jb1.addActionListener(this);
 		jb2.addActionListener(this);
 		jp1.add(p2,BorderLayout.CENTER);
 		}
 	@Override
 	public void actionPerformed(ActionEvent e) {
 		String str=e.getActionCommand();
 		if(str.equals("EXPENSES"))
 		   { j1=new JPanel(new GridLayout(4,1));
 		    Expenses elf=new Expenses(j1);
 		    Jframedemo.jp.remove(Jframedemo.jpp);
 			Jframedemo.jp.add(j1);
 		   }
 		
 		if(str.equals("REPORTS"))
 		{JPanel j5=new JPanel(new GridLayout(3,1));
 		 Report rr=new Report(j5);
 		 Jframedemo.jp.remove(Jframedemo.jpp);
 		 Jframedemo.jp.add(j5);
 		}
 		  
 		
 	}
 	}


 }
 class Createacc extends JPanel implements ActionListener
 {   JTextField t[];
 int count=0;
 JLabel ll=new JLabel();
 JTabbedPane pp1;
 JPanel panel1,jp1;
 JButton bd;
 Createacc(JPanel j,JTabbedPane j1,JButton bd)
 {   JLabel l[]=new JLabel[6];
 t=new JTextField[6];
 panel1=j;
 this.bd=bd;
 pp1=j1;
 l[0]=new JLabel("First name:");
 l[1]=new JLabel("last name");
 l[2]=new JLabel("email id");
 l[3]=new JLabel("password");
 l[4]=new JLabel("confirm password");
 l[5]=new JLabel("phno");
 t[0]=new JTextField(15);
 t[1]=new JTextField(15);
 t[2]=new JTextField(20);
 t[3]=new JTextField(15);
 t[4]=new JTextField(15);
 t[5]=new JTextField(15);
 GridBagConstraints c = new GridBagConstraints();
 int a,b=0;
 for(int i=0;i<6;i++)
 {   c.gridx=b;
 c.gridy=i;
 j.add(l[i],c);
 c.gridx=b+1;
 c.gridy=i;
 j.add(t[i],c);

 }
 JButton bb=new JButton("submit");
 j.add(bb);
 j.add(ll);
 for(int i=0;i<6;i++)
 {
 	t[i].addActionListener(this);
 }
 bb.addActionListener(this);
 }
 @Override
 public void actionPerformed(ActionEvent e) {
 	// TODO Auto-generated method stub
	 int c=0;
 	String s[]=new String[6];
 	String str1=e.getActionCommand();
 	if(str1.equals("submit"))
 	{     String strr= "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
 	     /* Pattern pat=Pattern.compile(strr);
         if(!pat.matcher(s[2]).matches())
         {ll.setText("invalid email address");
          c=-9;
         }*/
 		for(int i=0;i<6;i++)
 		{String str=t[i].getText();
 		s[i]=str;
 		if(str.equals(""))
 			c++;
 		}

 		if(c>=0)
 		{ll.setText("some fields are empty");
 		}

 		if(c==0)
 		{Jframedemo.pa=new Person(s[0],s[1],s[2],s[3],s[5]);
 		ll.setText("");
 		jp1=new JPanel(new GridBagLayout());
 		GridBagConstraints cc=new GridBagConstraints();
 		cc.gridx=0;
 		cc.gridy=0;
 		JLabel l1=new JLabel("Account created Successfully");
 		JLabel jl=new JLabel("click here to login");
 		bd=new JButton("Login here");
 		jp1.add(l1,cc);
 		cc.gridx=1;
 		cc.gridy=0;
 		jp1.add(jl,cc);
 		jp1.add(bd);
 		pp1.remove(panel1);
 		pp1.add(jp1);
 		bd.addActionListener(this);
 		}
 	}
 	if(str1.equals("Login here"))
 	{   pp1.remove(jp1);
 	Jframedemo.createPanel();

 	}
 } 
 }
 class Person implements Serializable 
 { String fname,lname,email,password,ph;
 boolean exist=false;
 TreeMap<Date,Journey> tp;
 String list[]=new String[5];
 Person(String fnam,String lnam,String mail,String pass,String phn)
 { fname=fnam;
 lname=lnam;
 email=mail;
 password=pass;
 exist=true;
 ph=phn;
 tp=new TreeMap<Date,Journey> ();
 }
Person()
{
	
}
 public String toString()
 {
    
 	return(fname+lname+email+password+ph);  

 }
 
 
  void display()
  {
	  System.out.println("name"+fname+"phn"+ph);
  }
 }

 class Entry1 extends JPanel
 {   
 	Entry1(JPanel p1,JTextField pass,JTextField mail,JLabel j2){
 	p1.setBackground(Color.YELLOW);
 	JLabel ma=new JLabel("Mail id");
 	Jframedemo.mail=new JTextField(10); 
 	JLabel pa=new JLabel("Password");
 	Jframedemo.pass=new JPasswordField(10);
 	JButton b1=new JButton("Login");
 	JButton b2=new JButton("Create account");
 	Jframedemo.j2=new JLabel(); 
 	p1.add(ma);
 	p1.add(Jframedemo.mail);
 	p1.add(pa);
 	p1.add(Jframedemo.pass);
 	p1.add(b1);
 	p1.add(b2);
 	p1.add(Jframedemo.j2);
 	Jframedemo.mail.addActionListener(new Jframedemo());
 	Jframedemo.pass.addActionListener(new Jframedemo());
 	b1.addActionListener(new Jframedemo());
 	b2.addActionListener(new Jframedemo());
 	// bd.addActionListener(this);

 }
 }
 class Expenses extends JPanel implements ActionListener,ItemListener
 {   String sel;
	 JTextField jt[];
	 JLabel lbw;
	 Expenses(JPanel pp)
	 { 
	  int b=2;
	  JLabel jb=new JLabel("select the of expenditure");
	  jb.setFont(new Font("Times New Roman",Font.BOLD,20));
	    String[] str= {"Breakfast","Lunch","Dinner","Transport","Accommodation","Shopping"};
	    JPanel p1=new JPanel(new GridLayout(2,1));
	    JComboBox jc=new JComboBox(str);
	    p1.add(jb);
	    p1.add(jc);
	    JPanel p2=new JPanel(new GridLayout(3,1));
	    JPanel pl[]=new JPanel[3];
	    for(int i=0;i<3;i++)
	    	  pl[i]=new JPanel();
	    JLabel jl[]=new JLabel[3];
	    jt=new JTextField[3];
	    jl[0]=new JLabel("Date(DD.MM.YYYY)");
	    jl[1]=new JLabel("Location");
	    jl[2]=new JLabel("cost");
	    jt[0]=new JTextField(15);
	    jt[1]=new JTextField(15);
	    jt[2]=new JTextField(15);
	    for(int i=0;i<3;i++)
	    {    pl[i].add(jl[i]);
	    	 pl[i].add(jt[i]);
	    	 jt[i].addActionListener(this);
	    	 p2.add(pl[i]);
	    	
	    }
	    JPanel p3=new JPanel(new GridLayout(2,1));
	    JButton b1=new JButton("SUBMIT");
	    JButton b2=new JButton("RESET");
	    lbw=new JLabel();
	    p3.add(b1);
	    p3.add(b2);
	    p1.setBackground(Color.MAGENTA);
	    //p2.setBackground(Color.ORANGE);
	    pp.add(p1);
	    pp.add(p2);
	    pp.add(p3);
	    pp.add(lbw);
	    b1.addActionListener(this);
	    b2.addActionListener(this);
	    jc.addItemListener(this);
	    Jframedemo.jp.add(pp);
	 }
	@Override
	public void itemStateChanged(ItemEvent e1) {
		// TODO Auto-generated method stub
        if(e1.getStateChange() == ItemEvent.SELECTED)
            {sel=(String)e1.getItem();
            }
         
	}
           
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String loc,dat,cos;
		int count=0,ind=0;
		String tmp=e.getActionCommand();
		if(tmp.equals("SUBMIT"))
		{loc=jt[1].getText();
		 dat=jt[0].getText();
		 System.out.println(dat);
		 cos=jt[2].getText();
		if((loc.equals(""))||(dat.equals(""))||(cos.equals("")))
        {lbw.setText("some fields are empty or in invalid format");
        }
		else {try {
		SimpleDateFormat ds=new SimpleDateFormat("dd.MM.yyyy");
	    TreeMap<Date,Journey> hm=Jframedemo.cp.tp;
	    Set<Map.Entry<Date,Journey>> set=hm.entrySet();
        for(Map.Entry<Date,Journey> me:set)
		{
			if((me.getKey().equals(ds.parse(dat)))&&(me.getValue().equals(sel)))
			 {count=1;
		     }
		}
        if(count==1)
        	lbw.setText("already exists");
		 else
		    {      Date dt; 
			       lbw.setText("");
					// ds=new SimpleDateFormat("dd.MM.yyyy");
				     Double d=Double.parseDouble(cos);
				     Journey jj=new Journey(loc,sel,d);
				     try {
						Jframedemo.cp.tp.put(dt=ds.parse(dat),jj);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 
				 }
			} catch (NumberFormatException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
		
		else
		if(tmp.equals("RESET"))
		{for(int i=0;i<3;i++)
                jt[i].setText("");
		 lbw.setText("");
			
		}
		
	}
 }
 class Journey implements Serializable
 { String place;
   double cost;
   String type;
   Journey(String place,String type,Double cost)
   {this.place=place;
    this.type=type;
    this.cost=cost;
   }
   public String toString()
   {
	   return(place+cost+type);
   }
 }
 
 class Report extends JPanel implements ItemListener,ActionListener
 {   JTable tb;
     DefaultTableModel def;
     String dat1;
     String[] col= {"name","Breakfast","Lunch","Dinner","Transport","Accommodation","Shopping"};;
     JPanel p2;
	 Report(JPanel ppl)
	 {   JPanel p1=new JPanel();
	     int cout=0;
		 String tem[]=new String[7];
		 for(int i=0;i<Jframedemo.c;i++)
		 {  TreeMap<Date,Journey> tm=Jframedemo.p[i].tp;
	      Set<Map.Entry<Date,Journey>> set=tm.entrySet(); 
	      for(Map.Entry<Date,Journey> me:set)
	               {int flag=0;
	    	         SimpleDateFormat ds=new SimpleDateFormat("dd.MM.yyyy");
	    	         String ss=ds.format(me.getKey());
	    	         for(int j=0;j<cout;j++)
	                          {if(tem[j].equals(ss))
	                        	    flag++;
	                          }
	    	         if(flag==0)
	    	         {tem[cout++]=ss;
	    	         }
	    	          
	               }
		 
		 }
			 
	     SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy");
	      //col= {"name","Breakfast","Lunch","Dinner","Transport","Accommodation","Shopping"};
	      JComboBox jb=new JComboBox(tem);
	      p1.add(jb);
	      p2=new JPanel();
	      String data[][]=new String[Jframedemo.c][7];
	      for(int i=0;i<Jframedemo.c;i++)
	      {for(int j=0;j<7;j++)
	         {
	    	      data[i][j]="";
	         }
	      } 
	     def= new DefaultTableModel(data,col);
	     tb=new JTable(def);
	     p2.add(tb);
	     JPanel p3=new JPanel();
	     JButton btt=new JButton("DRAW GRAPHS");
	     p3.add(btt);
	     btt.addActionListener(this);
	     jb.addItemListener(this);
	     ppl.add(p1);
	     ppl.add(p2);
	     ppl.add(p3);
	 }

@Override
public void itemStateChanged(ItemEvent e1) {
	// TODO Auto-generated method stub
	 if(e1.getStateChange() == ItemEvent.SELECTED) {
         dat1=(String)e1.getItem();	
     System.out.println(dat1);
	// TODO Auto-generated method stu
	String datas[][]=new String[Jframedemo.c][7];
	for(int i=0;i<Jframedemo.c;i++)
	{for(int j=0;j<7;j++)
	        {
                datas[i][j]="--";		
	        }
		
	}
	SimpleDateFormat s=new SimpleDateFormat("dd.MM.yyyy");
	int i=0;
	Date d1 = null;
	try {
	  d1=s.parse(dat1);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for(i=0;i<Jframedemo.c;i++)
	    {TreeMap<Date,Journey> tm=Jframedemo.p[i].tp;
	      Set<Map.Entry<Date,Journey>> set=tm.entrySet(); 
	      for(Map.Entry<Date,Journey> me:set)
	      {if(dat1.equals(s.format(me.getKey())))
	            { datas[i][0]=Jframedemo.p[i].lname;
	    	      Journey jr=me.getValue();
	              if(jr.type.equals("Breakfast"))
	                       datas[i][1]=Double.toString(jr.cost);  
	              if(jr.type.equals("Lunch"))
                      datas[i][2]=Double.toString(jr.cost);  
	              if(jr.type.equals("Dinner"))
                      datas[i][3]=Double.toString(jr.cost);  
	              if(jr.type.equals("Transport"))
                      datas[i][4]=Double.toString(jr.cost);  
	              if(jr.type.equals("Accommodation"))
                      datas[i][5]=Double.toString(jr.cost);  
	              if(jr.type.equals("Shopping"))
                      datas[i][6]=Double.toString(jr.cost);      
	              System.out.println(jr);
	            }
	    	  
	      }
	  }
      for(int i1=0;i1<Jframedemo.c;i1++)
       {for(int j=0;j<7;j++)
            {
    	       def.setValueAt(datas[i1][j],i1,j);
    	     }
	
       }
    }
	 
  }

  public void actionPerformed(ActionEvent e)
  {
	  
  }
 }


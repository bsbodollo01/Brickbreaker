package pt1;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.event.*;

/*
<applet code="Dxball1.class" width=670 height=300>
</applet>
*/
public class Dxball1 extends Applet implements
Runnable,KeyListener,MouseListener
{
	Thread th;
	static	Rectangle ob[] = new Rectangle[30];
	Ball ball;
	Bar bar;
	Message mes;
	int i,j;
	int x = 1;
	int y=1;
	int z = 0;
	int xx,yy1,zz;
	int b,b1;
	int flag;
	//static int yy=0;
	boolean runing=true;
	Image dbimage;
	Graphics dbg;
	int c1,c2,c3,c4;
	int flagg=1;
	//int ballCheck=0;
	String ss = "";
	String ss1="";
	String me = "";
	static int score = 0;

	static Font ff;
	TextArea ta;

	public void start()
	{
		th = new Thread(this);
		runing = false;
		//th.start();
	}
	public void stop()
	{

		runing = true;
		th = null;
	}
	public void run()
		{

			for(; ;)
			{
				if(ball.over())
				{
					ss = "Game Over!!!!!";
					ss1 = "You have loss the game";
					repaint();
					System.out.print("braek");
					break;

				}
				try
				{	Thread.sleep(20);
					if(runing)break;

					int tempx=Ball.flagx;
					int tempy=Ball.flagy;
					if (tempx==1)
						ball.move();

					else
						ball.move1();
					if(tempy==1)
						ball.move2();
					else
						ball.move3();
					if(tempy==1)
						ball.move();
					else
						ball.move3();

					repaint();

				}

				catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}
			}
		}
	public void init()
	{
		/*me ="
THIS IS A DXBALL GAME.

"+
		"
This Game is Devlopped by 
 Md.Mujibur Rahman
"+
		" University of DIU
 BATCH 9th ROLL 9007
"+
		" Created by Mujibur's Group
"+
		"
     HOW TO PLAY
"+
		"
 	Level 1:   

First You press mouse 
"+
		"when you press mouse the the ball is moved
 "+
		"Then Score will be increase
"+
		"
 Control the bar for using left arrow and right arrow
"+
		"the  Ball moved Randomly
"+
		"If the ball hits the Rectangle 
"+
		"Then Score will Increase 
"+
		"if the ball is passed the bar level 
"+
		"Game will be over and Game is finished";

		setLayout(null);
		ta = new TextArea(me);
		ta.setBounds(470,120,150,150);
		ta.setEditable(false);
		add(ta);*/
		int xx=30,mm=30;
		for(int i =0 ;i<24;i=i+3)
		{
			ob[i] = new Rectangle(xx,mm);
			ob[i].display=true;
			mm=mm+20;
			ob[i+1] = new Rectangle(xx,mm);
			ob[i+1].display=true;
			mm=mm+20;
			ob[i+2] = new Rectangle(xx,mm);
			ob[i+2].display=true;
			xx=xx+50;
			mm = 30;


		}

		mes =new Message();

		ball = new Ball(90,250);
		bar = new Bar(70);

		ff = new Font("arial",Font.BOLD,20);

		this.addKeyListener(this);
		this.addMouseListener(this);
	}

	public void paint(Graphics g)
	{

		int count=0;
		for(int i=0;i<24;i++)
		{
			if(ob[i].display==false)
				count++;
		}
		if(count==24)
		{
			mes.mess(g);
			return;
		}
		g.setFont(new Font("arial",Font.PLAIN,15));
		g.setColor(Color.gray);
		g.fillRect(470,30,150,70);
		g.setColor(Color.blue);
		g.drawString("Score:  "+score,480,50);
		g.drawString("Status:"+Ball.over,480,70);
		g.drawString("Life:  "+Ball.life,480,90);
		//g.drawString(""+Bar.yy,455,50);
		g.setColor(Color.black);
		g.fillRect(20,20,410,300);
		g.setColor(Color.red);
		g.setFont(ff);
		g.drawString(ss,100,200);
		g.drawString(ss1,100,230);
		g.setColor(Color.red);
		for(int j = 0;j<24;j++)
		{
			if(ob[j].display==false)
			{
				continue;
			}
			System.out.print("check   false  :  "+ob[j].display);
			if(ob[j].display==true)
			{
				breaking(j) ;

			}
			if(ob[j].display==true)
				ob[j].ppaint(g);


		}

		ball.bpaint(g);
		bar.barpaint(g);

	}


	public void keyTyped(KeyEvent k)
	{
	}
	public void keyReleased(KeyEvent k)
	{

	}

	public void  keyPressed(KeyEvent k)
	{
		int R =k.getKeyCode();
		if(R==k.VK_LEFT)
		{
			if(bar.yy > 25)
				bar.yy=bar.yy-10;
			System.out.println("Left");
			repaint();

		}
		if(R==k.VK_RIGHT)
		{
			if(bar.yy < 365 )
			{
				bar.yy=bar.yy+10;
			}
			System.out.println("Right");
			repaint();




		}

	}
	public void mousePressed(MouseEvent me)
	{
		th.start();
	}
	public void mouseReleased(MouseEvent me)
	{

	}
	public void mouseClicked(MouseEvent me)
	{

	}
	public void mouseExited(MouseEvent me)
	{

	}
	public void mouseEntered(MouseEvent me)
	{
	}
	public void update(Graphics g) //to remove flickering
	{
		if (dbimage==null)
		{
			dbimage=createImage(this.getSize().width,this.getSize().height);
			dbg=dbimage.getGraphics();
		}
		dbg.setColor(getBackground());
		dbg.fillRect(0,0,this.getSize().width,this.getSize().height);
		dbg.setColor(getForeground());
		paint(dbg);
		g.drawImage(dbimage,0,0,this);

	}


	public boolean breaking(int i)
	{
			if(check(i))
				return true;
			else
				return false;


	}
	public boolean check(int i)
	{
		int am,ami;
		am = Math.abs(ball.x1-ob[i].x);
		ami = Math.abs(ball.y1-ob[i].y);
		if(am<20 && ami<20 )
		{
			ob[i].display=false;
			score=score+10;
			Ball.flagy=1;
			return true;

		}
		else
		{

			return false;

		}

	}


} //Class main is end




class Rectangle
{
	int i;
	int x,y;
	Graphics g;
	int ballCheck=0;
	boolean display;
	Rectangle(int m ,int n)
	{

		x = m;
		y =n;
	}
	public void ppaint(Graphics g)
	{
		g.fillRect(x,y,40,10);

	}

}

class Ball
{
	static int flag=3;
	static int flagx=1;
	static int flagy=2;
	static int Point = 0;
	static String over="";
	static int life=1;
	int x,y,m;
	int x1,y1;
	Graphics g;
	Ball(int a1,int a2)
	{
		x1 = a1;
		y1 = a2;
	}
	public void bpaint(Graphics g)
	{
		//g.drawString("  y : "+y1,455,250);
		if(20>=x1||x1>=410||y1<=20||y1>=260)   //checking boundaries
		{

			getflag();
		}
		g.setColor(Color.white);
		g.fillOval(x1,y1,15,15);

	}
	public boolean over()
	{
		//System.out.println(""+y1);
		if(y1>290)
			return true;
		else
			return false;
	}
	public void move()
	{
		x1++;

	}
	public void move1()
	{
		x1--;

	}
	public void move2()
	{
		y1=y1+2;
	}
	public void move3()
	{
		y1=y1-2;
	}

	public void move4()
	{
		x1++;
		y1--;
	}

	public void getflag()
	{


		if(x1<=20)  // check boundaries and ball motion
		{
			x1=20;
			flagx=1;
		}
		if(x1>=410)
		{
			x1=410;
			flagx=2;
		}
		if(y1<=20)
		{
			y1=20;
			Point = Point +10;
			flagy=1;
		}
		if(y1>=260)
		{
			if(Bar.yy>=x1||x1<=(Bar.yy+50))
			{
				System.out.print("/n /n true Bar.yy :"+Bar.yy+"Y1:"+y1);
				System.out.print(" /n/n/n/ncheck ok");
				y1=260;
				flagy=2;
			}
			else
			{
				System.out.print("/n /n falseBar.yy :"+Bar.yy+"Y1:"+y1);
				if(Bar.yy>260 && Bar.yy<200)
				{
					flag=2;
					System.out.print("/n game over");
				}
				else
				{
					if(Bar.yy>290)
					{
					}
					else
					{
						life=0;
						System.out.print(Bar.yy);

						over="LOSS GAME";
					}
				}

			} //else1 end
		}


	}

}//end class



class Bar
{
	int ss;
	static int yy=0;
	int q;
	static int pp;
	Bar(int a)
	{
		yy=a+yy;
	}
	public void barpaint(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(yy,270,60,10);

	}
}


class Message
{
	public void mess(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0,0,400,300);
		g.setColor(Color.red);
		g.setFont(Dxball1.ff);
		g.drawString("YOU HAVE WON THE GAME",160,180);
		g.drawString("Your Score is:"+Dxball1.score,160,200);

	}
}

//rock paper scissor

package pt1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/** rock paper scissors class */
public class qp1 extends JApplet
{
	// Variables declaration
	private JLabel titleLbl;
	private JLabel selectionLbl;
	private JLabel resultsLbl;
	private JTextArea resultTextArea;
	private JButton rockBn;
	private JButton paperBn;
	private JButton scissorBn;
	private Container contentPane;
      private int cpu = 0;
      private int wins = 0;
      private int loses = 0;
      private int ties = 0;
      private final int CHOICE_MAX = 3;
      private final int ROCK = 0;
      private final int PAPER = 1;
      private final int SCISSORS = 2;
      private Random rand = new Random();
	// End of variables declaration

        /** Applet initiation method */
	public void init()	{
		initializeComponent();
		this.setVisible(true);
	}

        /** initializing componets */
	private void initializeComponent()
	{
		titleLbl = new JLabel();
		selectionLbl = new JLabel();
		resultsLbl = new JLabel();
		resultTextArea = new JTextArea();
		rockBn = new JButton();
		paperBn = new JButton();
		scissorBn = new JButton();
		contentPane = (JPanel)this.getContentPane();

		//
		// titleLbl
		//
		titleLbl.setText("Rock, Paper, Scissors");
                titleLbl.setFont(new Font("Garrmond", Font.BOLD, 30));
		//
		// selectionLbl
		//
		selectionLbl.setText("Choose one button");
                selectionLbl.setFont(new Font("Arial",Font.BOLD,14));
		//
		// resultsLbl
		//
		resultsLbl.setText("-----Results-----");
                selectionLbl.setFont(new Font("Arial",Font.BOLD,14));
		//
		// resultTextArea
		//
		resultTextArea.setOpaque(false);
		resultTextArea.setBackground(new Color(236, 233, 216));
                
		//
		// rockBn
		//
		rockBn.setText("Rock");
		rockBn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				rockBn_actionPerformed(e);
			}

		});
		//
		// paperBn
		//
		paperBn.setText("Paper");
		paperBn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				paperBn_actionPerformed(e);
			}

		});
		//
		// scissorBn
		//
		scissorBn.setText("Scissors");               
		scissorBn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				scissorBn_actionPerformed(e);
			}

		});
		//
		// contentPane
		//
		contentPane.setLayout(null);
		addComponent(contentPane, titleLbl, 5,9,370,47);
		addComponent(contentPane, selectionLbl, 9,54,150,35);
		addComponent(contentPane, resultsLbl, 9,93,144,38);
		addComponent(contentPane, resultTextArea, 5,132,398,111);
		addComponent(contentPane, rockBn, 162,58,78,31);
		addComponent(contentPane, paperBn, 247,58,81,31);
		addComponent(contentPane, scissorBn, 334,58,87,31);
		//
		// JRockPaperScissors
		//
		this.setLocation(new Point(0, 0));
		this.setSize(new Dimension(435, 290));
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}

	/** action event methods */
	private void rockBn_actionPerformed(ActionEvent e)
	{
            //play the game choosing rock
            play(ROCK);
	}

	private void paperBn_actionPerformed(ActionEvent e)
	{
            //play the game choosing paper
            play(PAPER);
	}

	private void scissorBn_actionPerformed(ActionEvent e)
	{
            //play the game choosing scissors
            play(SCISSORS);
	}
        //method to play the game
        private void play(int pick){
             String resultStr = "";                   
             //random computer choice
             cpu = rand.nextInt(CHOICE_MAX);
             //nested if statments to determine winner loser or tie
             if(pick == cpu){
                 ties++;
                 resultStr = "You picked " + selection(pick) + " ---- Computer picked " + selection(cpu);
                 resultStr += "\nWinner: Tie";
                 resultStr += "\nYou: " + Integer.toString(wins) + "  " + "Computer: " + Integer.toString(loses) + " Ties: " + Integer.toString(ties);
                 resultTextArea.setText(resultStr);
             }
             else if( (pick == ROCK) && (cpu == SCISSORS) ){
                 wins++;
                 resultStr = "You picked " + selection(pick) + " ---- Computer picked " + selection(cpu);
                 resultStr += "\nWinner: You";
                 resultStr += "\nYou: " + Integer.toString(wins) + "  " + "Computer: " + Integer.toString(loses) + " Ties: " + Integer.toString(ties);
                 resultTextArea.setText(resultStr);
             }
             else if( (pick == PAPER) && (cpu == ROCK) ){
                 wins++;
                 resultStr = "You picked " + selection(pick) + " ---- Computer picked " + selection(cpu);
                 resultStr += "\nWinner: You";
                 resultStr += "\nYou: " + Integer.toString(wins) + "  " + "Computer: " + Integer.toString(loses) + " Ties: " + Integer.toString(ties);
                 resultTextArea.setText(resultStr);                 
             }
             else if( (pick == SCISSORS) && (cpu == PAPER) ){
                 wins++;
                 resultStr = "You picked " + selection(pick) + " ---- Computer picked " + selection(cpu);
                 resultStr += "\nWinner: You";
                 resultStr += "\nYou: " + Integer.toString(wins) + "  " + "Computer: " + Integer.toString(loses) + " Ties: " + Integer.toString(ties);
                 resultTextArea.setText(resultStr);
             }
             else{
                 loses++;
                 resultStr = "You picked " + selection(pick) + " ---- Computer picked " + selection(cpu);
                 resultStr += "\nWinner: Computer";
                 resultStr += "\nYou: " + Integer.toString(wins) + "  " + "Computer: " + Integer.toString(loses) + " Ties: " + Integer.toString(ties);
                 resultTextArea.setText(resultStr);                 
             }
             
        }
            //function returns paper rock or scissors as a string
             public String selection(int choice){
                 String tempStr;
                 switch(choice){
                   case 0:
                         tempStr = "rock";
                         break;
                   case 1:
                         tempStr = "paper";
                         break;
                   case 2:
                         tempStr = "scissors";
                         break;
                   default:
                         tempStr = "invalid";
                  }
                  return tempStr;
             }
}

// MAde with <3 by Bryner
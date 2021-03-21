import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class ShowFrame extends JApplet implements ActionListener {
	JButton ezbtn,nmbtn,hdbtn,mubtn,stbtn,mdbtn,opbtn;
	Container c;
	String file = "bensound-funkyelement.wav";
	File musicpath;
	URL url;
	AudioClip clip,sound1;
	Puzzle p;
	JLabel label1;
	
	
	boolean status = false,mute=false;

	public void init() {
		c=getContentPane();
		c.setLayout(null);
		ezbtn = new JButton("Easy Mode");
		nmbtn = new JButton("Normal Mode");
		hdbtn = new JButton("Hard Mode");
		mubtn = new JButton("On : Music");
		stbtn = new JButton("Start : Game");
		mdbtn = new JButton("Free Mode");
		opbtn = new JButton("Open File");
		
		ezbtn.setBounds(300, 200, 150, 50);
		nmbtn.setBounds(300, 255, 150, 50);
		hdbtn.setBounds(300, 310, 150, 50);
		stbtn.setBounds(300, 365, 150, 50);
		opbtn.setBounds(300, 420, 150, 50);
		mubtn.setBounds(675, 10, 100, 50);
		
		ezbtn.addActionListener(this);
		nmbtn.addActionListener(this);
		hdbtn.addActionListener(this);
		mubtn.addActionListener(this);
		stbtn.addActionListener(this);
		mdbtn.addActionListener(this);
		opbtn.addActionListener(this);
		mdbtn.setBounds(10,10, 150, 50);
		
		c.add(ezbtn);
		c.add(nmbtn);
		c.add(hdbtn);
		c.add(stbtn);
		c.add(mubtn);
		c.add(mdbtn);
		c.add(opbtn);
		
		
		
		setSize(800,640);
		
		sound1 = getAudioClip( getDocumentBase(),
				"bensound-funkyelement.wav" );
		clip = sound1;
		p=new Puzzle();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ezbtn)  p.type=1;
		else if(e.getSource()==nmbtn)  p.type=2;
		else if(e.getSource()==hdbtn)  p.type=3;
		if(e.getSource()==stbtn) {
			if(status!=true) {
				status=true;
				ezbtn.setVisible(false);
				nmbtn.setVisible(false);
				hdbtn.setVisible(false);
				mdbtn.setVisible(false);
				stbtn.setBounds(10,10, 150, 50);
				start();
				stbtn.setText("Close : Game");
			}
			else {
				status=false;
				ezbtn.setVisible(true);
				nmbtn.setVisible(true);
				hdbtn.setVisible(true);
				stbtn.setBounds(300, 365, 150, 50);
				Stop();
				stbtn.setText("Start : Game");
			}
		}
		if(e.getSource()==mubtn) {
			if(!mute) {
				mute=true;
				clip.play();
				mubtn.setText("Off : music");
				}
			else {
				mute=false;
				clip.stop();
				mubtn.setText("On : music");
			}
		}
		if(e.getSource()==mdbtn) {
			if(!status) {
				status = true;
				p.type=1;
				ezbtn.setVisible(false);
				nmbtn.setVisible(false);
				hdbtn.setVisible(false);
				mdbtn.setVisible(false);
				stbtn.setBounds(10,10, 150, 50);
				p.display2();
				stbtn.setText("Close : Game");
			}
		}
		if(e.getSource()==opbtn) {
			try {
	            Path file = Paths.get("D:\\example2.txt");
	            BufferedReader reader = Files.newBufferedReader(file , 
	                    StandardCharsets.UTF_8);
	            String line = null;

	            while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }

	            reader.close();
	        } catch (IOException k) {
	            System.err.println("IOException: " + k.getMessage());
	        }
		}
	}
	public void start() {
		p.display(p.type);
	}
	public void Stop() {
		  
		  System.exit(0);
	  }
}

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.*;

public class SplashScreen extends JFrame implements Runnable{
	
	Thread t;
	SplashScreen() {
		setSize(600,400);
		setDefaultCloseOperation(3);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setTitle("Teacher Companion");
		
		
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/Splash.png"));
		Image imgScaled = img.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);		
		ImageIcon splash = new ImageIcon(imgScaled);
		JLabel image = new JLabel(splash);
		add(image);
		
		t = new Thread(this);
		t.start();
		
		
		setVisible(true);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			dispose();
			new LoginFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

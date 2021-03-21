import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SimpleStopWatch {
	public String t="";
    public static void main(String[] args) {
        new SimpleStopWatch();
    }

    public SimpleStopWatch() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new StopWatchPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class StopWatchPane extends JPanel {

        private JLabel label;
        private long lastTickTime;
        private Timer timer;
        
        long runningTime;
        Duration duration;
        long hours;
        long minutes;
        long millis;
        long seconds;
        Puzzle p;
        
        public StopWatchPane() {
            setLayout(new GridBagLayout());
            label = new JLabel(String.format("%04d:%02d:%02d.%03d", 0, 0, 0, 0));

            timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     runningTime = System.currentTimeMillis() - lastTickTime;
                     duration = Duration.ofMillis(runningTime);
                     hours = duration.toHours();
                    duration = duration.minusHours(hours);
                     minutes = duration.toMinutes();
                    duration = duration.minusMinutes(minutes);
                     millis = duration.toMillis();
                    seconds = millis / 1000;
                    millis -= (seconds * 1000);
                    label.setText(String.format("%04d:%02d:%02d.%03d", hours, minutes, seconds, millis));
                }
            });

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.insets = new Insets(4, 4, 4, 4);
            add(label, gbc);

            JButton start = new JButton("Start");
            start.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!timer.isRunning()) {
                        lastTickTime = System.currentTimeMillis();
                        timer.start();
                      
                    }
                }
            });
            JButton stop = new JButton("Stop");
            stop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	t+=hours+":"+minutes+":"+seconds+"."+millis+"\n";
                	try {
                        Path file = Paths.get("D:\\example2.txt");
                        BufferedWriter writer = Files.newBufferedWriter(file, 
                                StandardCharsets.UTF_8);
                        writer.newLine();
                        writer.write("\t"+t+"\n");

                        writer.close();       
                    } catch (IOException ew) {
                        System.err.println("IOException: " + ew.getMessage());
                    }
                    timer.stop();
                    JOptionPane.showMessageDialog(null,t);
                }
            });

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.weightx = 0;
            gbc.gridwidth = 1;
            add(start, gbc);
            gbc.gridx++;
            add(stop, gbc);
        }

    }

}

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;

public class Main implements ActionListener {
    public static void main(String[] args) {
        Main linkPuller = new Main();
    }
private JFrame frame;
private JPanel panel;
private JPanel console;
private JButton GoButton;
private JButton StopButton;
private JTextField Tfield;
private JTextField Tfield2;
private JTextArea linkDisplay;
public ArrayList<String> links = new ArrayList<String>();


    public Main() {
        layoutFour();


    }
    public void linkPuller(String URL, String searchTerm){

        try {

            URL url = new URL(URL);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line= "t";
           while((line = reader.readLine()) != null) {
               if (line.contains("/wiki/")) {
                    int start = line.indexOf('/');
                    int end = line.indexOf('"', start);
                    String Occurrence = line.substring(start, end);
                    //System.out.println(Occurrence);
                    links.add(Occurrence);
                }

            }

            reader.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        for(int n=0; n<links.size(); n++){
            String modifiedlink = links.get(n);
            if (modifiedlink.contains("//")!=true) {
                String domain = "https://en.wikipedia.org";


                // Inserting at the end
                modifiedlink = domain + modifiedlink;
                links.set(n,modifiedlink);
                // Print and display the above string
                //System.out.println(modifiedlink);
            }else if(modifiedlink.contains("https:")!=true){
                String domain = "https:";
                modifiedlink = domain + modifiedlink;
                links.set(n,modifiedlink);
            }
            //System.out.println(modifiedlink);

        }
        linkMatrix(links);

    }

    public void linkMatrix(ArrayList<String> links){
        linkDisplay = new JTextArea();
        for(int n=0; n<links.size(); n++){
            String visibleLink = links.get(n);
            linkDisplay.append(visibleLink);
            linkDisplay.append("\n");
        }
        console.add(linkDisplay, BorderLayout.CENTER);
        frame.add(console);


        frame.setVisible(true);
    }



    public void layoutFour(){
        panel = new JPanel(new GridLayout(8,8));
        console = new JPanel(new BorderLayout());
        GoButton = new JButton("Go");
        GoButton.addActionListener(this);
        StopButton = new JButton("Stop");
        StopButton.addActionListener(this);
        Tfield = new JTextField("Link Here");
        Tfield2 = new JTextField("Search Term Here");
        frame = new JFrame();



        console.add(GoButton,BorderLayout.WEST);
        console.add(Tfield,BorderLayout.SOUTH);
        console.add(StopButton, BorderLayout.EAST);
        frame.setTitle("Aarav's two panel layout");
        frame.add(console);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Go"){
            linkPuller(Tfield.getText(),Tfield2.getText());
        }else if(e.getActionCommand()=="Stop") {
        }
    }
}




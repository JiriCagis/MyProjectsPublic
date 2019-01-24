package view;

import view.component.Clock;
import data.Constant;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import utils.xmlParser.XmlParser;
import utils.xmlParser.XmlParserImpl;

public class MainWindow extends JFrame {

    private final XmlParser xmlParser;
    private final File xmlFile= new File("configuration.xml");
    
    public MainWindow() {
        //general
        setTitle(Constant.APP_NAME + " " + Constant.APP_VERSION);
        setResizable(true);
        xmlParser = new XmlParserImpl<Dimension>();
        List<Dimension> list = xmlParser.parse(xmlFile);
        if(!list.isEmpty() && list.size()>1){
            setSize(list.get(0));
            Dimension location = list.get(1);
            setLocation(location.width, location.height);
        } else
        {
            setSize(300, 300);
            setLocationRelativeTo(null);
        }
        //add components to window
        Clock clock = new Clock();
        getContentPane().add(clock);
        
        //register listeners
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //persist data
                List<Dimension> list = new ArrayList<Dimension>();
                Dimension windowSize = getSize();
                Dimension positionOnScreen = new Dimension( getLocationOnScreen().x,getLocation(null).y);
                list.add(windowSize);
                list.add(positionOnScreen);
                xmlParser.save(list, xmlFile);
                //exit window
                dispose();
                System.exit(0);
            }
        });
    }

}

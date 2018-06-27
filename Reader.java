package JavaFXMappingApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

//Use of Reader class to fetch the coordinates from the server and putting those coordinate values into arraylist
public class Reader implements Runnable{
    
    String url = "http://daily.digpro.se/bios/servlet/bios.servlets.web.RecruitmentTestServlet";
    
    ArrayList<Coord> list;
    
    public Reader(ArrayList list) {
        this.list = list;
    }
    
//    Encoding the values using streams
    public  String getContent() throws IOException {
        URL tempurl = new URL(url);
        InputStream is = tempurl.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
        String line;
        String returnData = "";
        while ((line = br.readLine()) != null) {
            if (line.contains("#")) {
                continue;
            }
            returnData += line + "\n";
        }
        return returnData;
    }
    
//    use of arrayList to update the data
    public ArrayList<Coord> updateData() {
        try {
            String[] $lines = getContent().split("\n");
            if (!list.isEmpty()) {
                list.clear();
            }
            
            for (String $line : $lines) {
                $line = $line.replaceAll("\\s+", "");
                list.add(new Coord(Integer.parseInt($line.split(",")[0]), Integer.parseInt($line.split(",")[1]), $line.split(",")[2]));
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                updateData();
                Thread.sleep(30000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
}

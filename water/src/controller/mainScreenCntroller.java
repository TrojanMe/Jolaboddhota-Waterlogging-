package controller;


import algorithmCodes.AreaDivider;
import algorithmCodes.ConvexHull;
import algorithmCodes.Node;
import database.DatabaseCommands;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Tabledata;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by User on 09-May-17.
 */
public class mainScreenCntroller  {

    @FXML
    AnchorPane scrolcanvas;

    @FXML
    ScrollPane sp;

    @FXML
    TableView datatable;
    @FXML
    Button gop;
    @FXML
    Button pp;
    @FXML
    Button zoomo;
    @FXML
    Button useless1;
    @FXML
    Button useless2;
    @FXML
    Button genGraph;


    @FXML
    Label goplab;
    @FXML
    Label ppla;
    @FXML
    Label zoomolab;
    @FXML
    Label useless1lab;
    @FXML
    Label useless2lab;


    @FXML
    Slider slider;

    @FXML
    ImageView zo;



    double lat;
    double lon;

    private static DatabaseCommands databaseCommands;
    private ObservableList<ObservableList> data;
    ObservableList<Tabledata> itemsfromDatabase = FXCollections.observableArrayList();

    public void setDatabaseCommands(DatabaseCommands databaseCommands) {
        mainScreenCntroller.databaseCommands = databaseCommands;
    }


    public void primarySetup()
    {
        gop.setVisible(false);
        pp.setVisible(false);
        zoomo.setVisible(false);
        useless1.setVisible(false);
        useless2.setVisible(false);
        goplab.setVisible(false);
        ppla.setVisible(false);
        zoomolab.setVisible(false);
        useless1lab.setVisible(false);
        useless2lab.setVisible(false);
        slider.setVisible(false);
        zo.setVisible(false);
        sp.setVisible(false);


        TableColumn<Tabledata, String> dataid = new TableColumn<Tabledata, String>("Data id");
        dataid.setMinWidth(70);
        dataid.setCellValueFactory(new PropertyValueFactory<>("dataid"));
        dataid.setStyle("-fx-alignment: CENTER;");

        TableColumn<Tabledata, String> userid = new TableColumn<Tabledata, String>("user id");
        userid.setCellValueFactory(new PropertyValueFactory<>("userid"));
        userid.setMinWidth(70);
        userid.setStyle("-fx-alignment: CENTER;");

        TableColumn<Tabledata, String> locx = new TableColumn<Tabledata, String>("Latitude");
        locx.setCellValueFactory(new PropertyValueFactory<>("locx"));
        locx.setMinWidth(70);
        locx.setStyle("-fx-alignment: CENTER;");

        TableColumn<Tabledata, String> locy = new TableColumn<Tabledata, String>("Longitude");
        locy.setCellValueFactory(new PropertyValueFactory<>("locy"));
        locy.setMinWidth(70);
        locy.setStyle("-fx-alignment: CENTER;");

        TableColumn<Tabledata, String> customdata = new TableColumn<Tabledata, String>("Custom Data Value");
        customdata.setCellValueFactory(new PropertyValueFactory<>("customdata"));
        customdata.setMinWidth(130);
        customdata.setStyle("-fx-alignment: CENTER;");

        TableColumn<Tabledata, String> custommode = new TableColumn<Tabledata, String>("Custom Data mode");
        custommode.setCellValueFactory(new PropertyValueFactory<>("custommode"));
        custommode.setMinWidth(130);
        custommode.setStyle("-fx-alignment: CENTER;");

        TableColumn<Tabledata, String> approximatedata = new TableColumn<Tabledata, String>("Approximate Data");
        approximatedata.setCellValueFactory(new PropertyValueFactory<>("approximatedata"));
        approximatedata.setMinWidth(120);
        approximatedata.setStyle("-fx-alignment: CENTER;");

        TableColumn<Tabledata, String> time = new TableColumn<Tabledata, String>("Data & Time of upload");
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        time.setMinWidth(140);
        time.setStyle("-fx-alignment: CENTER;");

        TableColumn<Tabledata, String> comment = new TableColumn<Tabledata, String>("Comment from user");
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        comment.setMinWidth(130);
        comment.setStyle("-fx-alignment: CENTER;");


        datatable.getColumns().addAll(dataid, userid, locx, locy, customdata, custommode, approximatedata, time, comment);
    }


    @FXML
    private void loadDataintotable() {
        gop.setVisible(false);
        pp.setVisible(false);
        zoomo.setVisible(false);
        useless1.setVisible(false);
        useless2.setVisible(false);
        goplab.setVisible(false);
        ppla.setVisible(false);
        zoomolab.setVisible(false);
        useless1lab.setVisible(false);
        useless2lab.setVisible(false);
        slider.setVisible(false);
        zo.setVisible(false);
        datatable.setVisible(true);
        sp.setVisible(false);



        data = databaseCommands.fetchtableData();

        for(int i=0; i<data.size() ; i++)
        {
            Tabledata row = new Tabledata();
            String s = data.get(i).toString();
            s = s.substring(1,s.length()-1);
            //System.out.println(s);
            String[] div = s.split(",");
            row.setDataid(div[0]);
            row.setUserid(div[1]);
            row.setLocx(div[2]);
            row.setLocy(div[3]);
            row.setCustomdata(div[4]);
            row.setCustommode(div[5]);
            row.setApproximatedata(div[6]);
            row.setTime("13/1/2017" + div[7]);
            row.setComment(div[8]);

            itemsfromDatabase.add(row);
        }

        datatable.setItems(itemsfromDatabase);

    }

    void loadmap()
    {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        File f = new File("C:\\Users\\User\\IdeaProjects\\water\\src\\controller\\mp.html");
        webEngine.load(f.toURI().toString());
        webView.setPrefHeight(800.0);
        webView.setPrefWidth(944.0);
        scrolcanvas.getChildren().add(webView);
    }


    public void generateGraph(ActionEvent actionEvent) {
        gop.setVisible(true);
        pp.setVisible(true);
        zoomo.setVisible(true);        useless1.setVisible(true);
        useless2.setVisible(true);
        goplab.setVisible(true);
        ppla.setVisible(true);
        zoomolab.setVisible(true);
        useless1lab.setVisible(true);
        useless2lab.setVisible(true);
        slider.setVisible(true);
        zo.setVisible(true);
        datatable.setVisible(false);
        scrolcanvas.setVisible(true);
        sp.setVisible(true);

        loadGraph();
        //loadmap();


    }

    private void loadGraph() {

        Canvas canvas = new Canvas(2500,2500);
        sp.setContent(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);

    }

    private void drawShapes(GraphicsContext gc) {

        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(1);

        for(int i=0;i<250;i++){
            for(int j=0;j<250; j++)
            {
                gc.strokeLine(i*10, j*10, i*10, 2500);
                gc.strokeLine(i*10, j*10, 2500, j*10);

            }
        }


        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);


        for(int i=0;i< itemsfromDatabase.size() ; i++){
            gc.fillOval(Integer.parseInt(itemsfromDatabase.get(i).getLocx().replaceAll(" ","")), Integer.parseInt(itemsfromDatabase.get(i).getLocy().replaceAll(" ","")), 5, 5);
        }

        AreaDivider areaDivider = new AreaDivider(10 , itemsfromDatabase.size());

        List<Node> nodeshere = new ArrayList<>(3000);
        for(int i=0;i<itemsfromDatabase.size();i++){
            areaDivider.nodes.add(new Node(Integer.parseInt(itemsfromDatabase.get(i).getLocx().replaceAll(" ","")) , Integer.parseInt(itemsfromDatabase.get(i).getLocy().replaceAll(" ","")) , 0, Integer.parseInt(itemsfromDatabase.get(i).getCustomdata().replaceAll(" ",""))));
        }

        //for(int i=0;i<areaDivider.nodes.size();i++){
            //System.out.println(i + " " +areaDivider.nodes.get(i).getX() +" "+ areaDivider.nodes.get(i).getY() +" "+ areaDivider.nodes.get(i).getC() +" "+ areaDivider.nodes.get(i).getWaterlevel()) ;
        //}
        //areaDivider.nodes = nodeshere;
        //areaDivider.process();
        //areaDivider.areaConnector();

        Map < Integer , List<Node> > regionmap ;
        regionmap = new HashMap<>(5000);

        Map < Integer , List < Integer > > regionmapnode ;
        regionmapnode = new HashMap<>(5000);

        Map < Integer , List < Integer > > regionmapn ;
        regionmapn = new HashMap<>(5000);

        Map < Integer , Integer > regionmapc ;
        regionmapc = new HashMap<>(5000);

        int m , r;
        int l=2500 , w=2500;
        int scale =50;

        m = (((float)l/(float)scale)!=(l/scale)) ? (l/scale+1): l/scale ;
        r = (((float)w/(float)scale)!=(w/scale)) ? (w/scale+1): w/scale ;


        System.out.println("Size of the region grid is " +  m  +  "  X  " + r );
        int idx , idy;

        List < Integer > done = new ArrayList<>(5000);
        for(int y =0; y<5000 ; y++){
            done.add(y,0);
        }


        for(int i=0; i< m+1 ; i++) {
            for (int j = 0; j < r + 1; j++) {
                List< Node> a = new ArrayList<>(3000);
                List < Integer> ai = new ArrayList<>(3000);
                regionmap.put(i*m+j, a);
                regionmapn.put(i*m+j,ai);
                regionmapc.put(i*m+j,0);
                regionmapnode.put(i*m+j,ai);
            }
        }

        for(int i=0;i<itemsfromDatabase.size();i++)
        {
            idx = ((areaDivider.nodes.get(i).getX())/scale);
            idy = ((areaDivider.nodes.get(i).getY())/scale);
            Node temp = areaDivider.nodes.get(i);
            regionmap.get(idx*m+idy).add(temp);
            if(done.get(i)==0) { regionmapnode.get(idx*m+idy).add(i) ; done.add(i,1); }
            regionmapc.put(idx*m+idy,regionmapc.get(idx*m+idy)+1);
            //System.out.println( "Node : "  + areaDivider.nodes.get(i).getX() + "  " + areaDivider.nodes.get(i).getY() + " entering  region " + idx + " " + idy);
        }

        int i,j,k;
        int help=0;

        for(i=0;i<m+1; i++) for(j=0;j<r+1;j++) {
            System.out.println(i*m+j + " "+ regionmapc.get(i*m+j));
            help +=  regionmapc.get(i*m+j);
            System.out.println("inner help is "+ help);

        }



       for(i=0;i<m+1; i++) for(j=0;j<r+1;j++) {
            for(k=0;k<3;k++){
                if ( (0<=(j-1+k)) && (j-1+k<r+1) && (0<=i) && (i<m) )
                {
                   if(regionmapc.get(i*(m-1)+j-1+k) !=0) regionmapn.get(i*m+j).add(i*(m-1)+j-1+k);
                }
            }
            for(k=0;k<3;k++){

                if ( (0<=(j-1+k)) &&(j-1+k<r+1) && (0<=i) && (i<m)) {
                    if(regionmapc.get(i*(m+1)+j-1+k) !=0  )regionmapn.get(i*m+j).add(i*(m+1)+j-1+k);
                }
            }
            for(k=0;k<3;k+=2) {

                if ((0<=(j-1+k)) &&(j-1+k<r+1) && (0<=i) && (i<m))
                {
                    if(regionmapc.get(i*(m)+j-1+k) !=0 ) regionmapn.get(i*m+j).add(i*(m)+j-1+k);
                }
            }
        }

        List<Integer> check = new ArrayList<>((m+1)*(r+1));
        for(i=0;i<3000;i++){
            for(j=0;j<3000;j++){
                check.add(i*3000+j,0);
            }
        }




        System.out.println("Area connection :");

        for(i=0;i<m+1;i++){
            for(j=0;j<r+1;j++){
                help+= regionmapnode.get(i*m+j).size();
            }
        }



        for(i=0;i< m+1; i++) {
            for (j = 0; j < r + 1; j++) {


                if (check.get(i * m + j) == 0) {
                    ConvexHull c = new ConvexHull();
                    List<Node> need = new ArrayList<>(3000);

                        for (int s = 0; s < regionmapnode.get(i * m + j).size(); s++) {
                            need.add(new Node(Integer.parseInt(itemsfromDatabase.get(regionmapnode.get(i * m + j).get(s)).getLocx().replaceAll(" ","")) , Integer.parseInt(itemsfromDatabase.get(regionmapnode.get(i * m + j).get(s)).getLocy().replaceAll(" ","")) ,0, Integer.parseInt(itemsfromDatabase.get(regionmapnode.get(i * m + j).get(s)).getCustomdata().replaceAll(" ",""))));
                        }
                    check.add(i * m + j, 1);


                        int ni;
                    for (int e = 0; e < regionmapn.get(i * m + j).size(); e++) {
                        ni = regionmapn.get(i * m + j).get(e);
                        if (check.get(ni) == 0) {
                                System.out.println("working with  " + regionmapnode.get(ni).size());
                                /*for (int as = 0; as < regionmapnode.get(ni).size(); as++) {
                                    need.add(new Node(Integer.parseInt(itemsfromDatabase.get(regionmapnode.get(ni).get(as)).getLocx().replaceAll(" ","")) , Integer.parseInt(itemsfromDatabase.get(regionmapnode.get(ni).get(as)).getLocy().replaceAll(" ","")) ,0, Integer.parseInt(itemsfromDatabase.get(regionmapnode.get(ni).get(as)).getCustomdata().replaceAll(" ",""))));
                                }*/

                            check.add(ni, 1);
                        }
                    }
                    c.nodes = need;
                    help += c.nodes.size();
                    System.out.println("Region " + i * m + j + " containing nodes : ");
                    System.out.println(help);



                for(int t=0;t<c.snodes.size(); t++){
                    System.out.println(c.snodes.get(t).getX() + " " + c.snodes.get(t).getY());
                }

                }
            }

        }


        System.out.println("help is " + help);


        //gc.strokeLine(40, 10, 10, 40);

        /*gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);*/
    }





}

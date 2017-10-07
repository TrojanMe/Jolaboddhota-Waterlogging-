package controller;


import algorithmCodes.*;
import database.DatabaseCommands;
import javafx.application.Platform;
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
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Tabledata;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.pow;


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
    Button loadmap;


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


    @FXML
    WebView WebView1;


    double lat;
    double lon;
    Canvas canvas;

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
        goplab.setVisible(false);
        ppla.setVisible(false);
        sp.setVisible(false);
        WebView1.setVisible(false);


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

        goplab.setVisible(false);
        ppla.setVisible(false);

        datatable.setVisible(true);
        sp.setVisible(false);

        WebView1.setVisible(false);



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

    @FXML
    void loadmap()
    {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        File f = new File("C:\\Users\\User\\IdeaProjects\\water\\src\\controller\\mp.html");
        webEngine.load(f.toURI().toString());
        webView.setPrefHeight(800.0);
        webView.setPrefWidth(944.0);
        scrolcanvas.getChildren().add(webView);
        canvas.setVisible(false);
    }


    public void generateGraph(ActionEvent actionEvent) {
        gop.setVisible(true);
        pp.setVisible(true);
        goplab.setVisible(true);
        ppla.setVisible(true);
        datatable.setVisible(false);
        scrolcanvas.setVisible(true);
        sp.setVisible(true);
        WebView1.setVisible(false);

        loadGraph();
        //loadmap();


    }

    private void loadGraph() {

        canvas = new Canvas(2500,2500);
        sp.setContent(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);

    }

    private void drawShapes(GraphicsContext gc) {

        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(1);

        /*

        for(int i=0;i<250;i++){
            for(int j=0;j<250; j++)
            {
                gc.strokeLine(i*10, j*10, i*10, 2500);
                gc.strokeLine(i*10, j*10, 2500, j*10);

            }
        }

        */


        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);


        List<Node> allnodes = new ArrayList<>(3500);

        for(int i=0;i< itemsfromDatabase.size() ; i++){
            int xi = Integer.parseInt(itemsfromDatabase.get(i).getLocx().replaceAll(" ",""));
            int yi = Integer.parseInt(itemsfromDatabase.get(i).getLocy().replaceAll(" ",""));
            int cd =  Integer.parseInt(itemsfromDatabase.get(i).getCustomdata().replaceAll(" ",""));

            allnodes.add(new Node(xi,yi,0,cd));

            gc.fillOval(xi , yi , 5, 5);

        }

        //System.out.println("Size of all nodes : "  + allnodes.size());

        AreaDivider areaDivider = new AreaDivider(300 , allnodes.size());
        areaDivider.setNodes(allnodes);
        areaDivider.process();
        areaDivider.areaConnector();
        areaDivider.bigAreaGenerator();

        List<BigRegion> allbigRegions = areaDivider.getBigRegions();

        int counter =0;

        for(int a= 0 ; a<allbigRegions.size() ; a++){


            //System.out.println("BigRegion with id : " + a + '\n');

            List < Node > bignodes = allbigRegions.get(a).getAllnodenumbers();

            for(int b=0 ; b<bignodes.size() ; b++){

                counter++;

                //System.out.println("x : " + bignodes.get(b).getX() + " y : "+ bignodes.get(b).getY() + " c :" + bignodes.get(b).getC() + " coustom Data : " + bignodes.get(b).getWaterlevel());

            }

            //System.out.println();


        }

        //System.out.println("Counter value is " + counter);


        int solutionnodescount=0;



        //System.out.println("Solving with quick hull ");


        List < NodeforFF > ffnodes = new ArrayList<>(1000);

        ffnodes.add(new NodeforFF(0,1250 , 1250 , (float) 100000000000000000.0));

        int ff=1;

        for(int a= 0 ; a<allbigRegions.size() ; a++){


            //System.out.println("BigRegion with id : " + a + '\n');

            List < Node > bignodes = allbigRegions.get(a).getAllnodenumbers();

            int bs = bignodes.size();

            if(bs !=0 ){

                allbigRegions.get(a).generateTotalVolume();
                allbigRegions.get(a).geneatecenter();

                double curx = allbigRegions.get(a).getCenterx();
                double cury = allbigRegions.get(a).getCentery();
                float volume = allbigRegions.get(a).getTotalwaterVolume();


                ffnodes.add(new NodeforFF(ff, curx , cury , volume));
                ff++;


                System.out.println("So the region " + a + " containing center at " + curx + " " + cury + " "+ " and volume : " + abs(volume));

                QuickHull c = new QuickHull();


                List < Node > solutionNodes = c.quickHull((ArrayList<Node>) bignodes);

                int ss = solutionNodes.size();

                double[] solpointsX = new double[ss];
                double[] solpointsY = new double[ss];

                int sx=0;
                int sy=0;

                for(int q=0; q<solutionNodes.size() ; q++){

                    solutionnodescount++;

                    solpointsX[q] = (double)solutionNodes.get(q).getX();
                    solpointsY[q] = (double)solutionNodes.get(q).getY();

                    //System.out.println("x : " + solutionNodes.get(q).getX() + " y : "+ solutionNodes.get(q).getY() + " c :" + solutionNodes.get(q).getC() + " coustom Data : " + solutionNodes.get(q).getWaterlevel());
                }


                //System.out.println("SS is : " + ss);

                if(ss>2){

                    for(int t=0; t<ss; t++){
                        System.out.println(solpointsX[t] + " " + solpointsY[t]);
                    }

                    gc.setStroke(Color.RED);
                    gc.setLineWidth(1);
                    gc.strokePolygon(solpointsX , solpointsY , ss);
                }


            }

            //System.out.println();

        }

        //System.out.println("Total number of solutionNodes " + solutionnodescount);

        ffnodes.add(new NodeforFF(ff , 2500/2 , 2500/2 , (float) 100000.20));

        float[][] disttocap = new float[1000][1000];

        for(int ll=0 ; ll<1000 ; ll++){
            for(int jj=0 ; jj<1000 ; jj++)
                disttocap[ll][jj]=0;
        }



        for(int h=1; h<ff ; h++) {

            ArrayList <SNode> forsortingdistance = new ArrayList<SNode>();

            for(int sa=1 ; sa<ff; sa++) {

                forsortingdistance.add(new SNode(sa , dist(ffnodes.get(h).getX(), ffnodes.get(h).getY(), ffnodes.get(sa).getX(), ffnodes.get(sa).getY())));

            }

            Collections.sort(forsortingdistance);
            for(int asa =1 ; asa<=3 ; asa++){

                disttocap[h][forsortingdistance.get(asa).getId()] = abs(ffnodes.get(h).getCap());

            }


        }


        for(int gh=1; gh<ff; gh++) disttocap[0][gh]= (float) 1000000000000000.0;

        disttocap[ff-1][ff]= (float) 1000000000000.0;
        disttocap[ff-2][ff+1] = (float) 1000000000000.0;

        disttocap[ff][ff+2]= (float) 1000000000000.0;
        disttocap[ff+1][ff+2] = (float) 1000000000000.0;

        FFalgo fFalgo = new FFalgo();
        fFalgo.generatePipeline(disttocap,ff+1);

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


    private double dist(double x1 , double y1 , double x2 , double y2){

        return (Math.pow(pow(x1-x2,2) + pow(y1-y2 , 2) , 0.5));

    }


    public void loadMap(ActionEvent actionEvent) throws MalformedURLException {

        gop.setVisible(true);
        pp.setVisible(true);
        goplab.setVisible(true);
        ppla.setVisible(true);
        datatable.setVisible(false);
        scrolcanvas.setVisible(true);
        sp.setVisible(true);
        WebView1.setVisible(true);


        File f = new File("C:\\Users\\User\\IdeaProjects\\water\\src\\controller\\mp.html");
        WebEngine webEngine;

        WebView1.setMinHeight(800);
        WebView1.setMinWidth(800);

        webEngine =  WebView1.getEngine();

        webEngine.load(f.toURI().toString());

        /*HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefHeight(300);

        TextArea htmlText = new TextArea();
        htmlText.setWrapText(true);

        sp.setContent(htmlText);

        LoadPageThread loadPageThread =
                new LoadPageThread("C:\\Users\\User\\IdeaProjects\\water\\src\\controller\\mp.html", htmlEditor, htmlText);
        loadPageThread.start();

        */

        //WebView1.getEngine().load(new File("C:\\Users\\User\\IdeaProjects\\water\\src\\controller\\mp.html").toURI().toURL().toString());

    }

    class LoadPageThread extends Thread {

        String PageSrc;
        HTMLEditor editor;
        TextArea textArea;

        public LoadPageThread(String src, HTMLEditor editor, TextArea textArea) {
            PageSrc = src;
            this.editor = editor;
            this.textArea = textArea;
        }

        @Override
        public void run() {
            String result = loadPage(PageSrc);

            Platform.runLater(() -> {
                //update html code in HTMLEditor
                editor.setHtmlText(result);

                //get html code from HTMLEditor
                String html = editor.getHtmlText();
                textArea.setText(html);
            });
        }

        private String loadPage(String src) {

            String pageCode = "";

            try {
                URL url = new URL(src);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                Scanner scanner = new Scanner(inputStream);

                while (scanner.hasNextLine()) {
                    pageCode += scanner.nextLine() + "\n";
                }

            } catch (MalformedURLException ex) {

            } catch (IOException ex) {

            }

            return pageCode;
        }
    }
}

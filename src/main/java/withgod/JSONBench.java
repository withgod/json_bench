package withgod;

import com.google.gson.Gson;
import net.arnx.jsonic.JSON;
import org.codehaus.jackson.map.ObjectMapper;
import twitter4j.internal.org.json.JSONArray;
import withgod.model.PublicTimelineModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONBench {
    private String publicTlStr = null;
    private int loopCount = 10000;
    private static String fileName = "/publictl.json";

    public JSONBench() throws Exception {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName), "UTF-8"));
        StringBuffer buf = new StringBuffer();
        String str;
        while ((str = reader.readLine()) != null) {
            buf.append(str);
        }
        reader.close();
        this.publicTlStr = buf.toString();
    }

    private long startTime = 0l;
    private long endTime = 0l;

    public void doStart(String target) {
        System.out.println("bench start [" + target + "]");
        this.startTime = System.currentTimeMillis();
    }

    public void doFirstEnd() {
        this.endTime = System.currentTimeMillis();
        System.out.println("first ");
        System.out.println("\t start [" + this.startTime + "]");
        System.out.println("\t end   [" + this.endTime + "]");
        System.out.println("\t diff  [" + (this.endTime - this.startTime) + "]");
        this.startTime = System.currentTimeMillis();
    }

    public void doEnd(String target) {
        this.endTime = System.currentTimeMillis();
        System.out.println("--");
        System.out.println(this.loopCount + " loop");
        System.out.println("\t start   [" + this.startTime + "]");
        System.out.println("\t end     [" + this.endTime + "]");
        System.out.println("\t diff    [" + (this.endTime - this.startTime) + "]");
        System.out.println("\t average [" + ((double)(this.endTime - this.startTime) / this.loopCount) + "]");
        System.out.println("bench end [" + target + "]");
        System.out.println("----");
    }

    public void doJSONIC() {
        doStart("jsonic");
        PublicTimelineModel[] x = JSON.decode(this.publicTlStr, PublicTimelineModel[].class);
//        System.out.println(ToStringBuilder.reflectionToString(x[1].user));
        doFirstEnd();
        for (int i = 0; i < this.loopCount; i++) {
            JSON.decode(this.publicTlStr, PublicTimelineModel[].class);
        }
        doEnd("jsonic");
    }

    //こいつはPOJO変換とか便利な機能ないっぽい
    public void doJSONORG() throws Exception {
        doStart("jsonorg");
        JSONArray obj = new JSONArray(this.publicTlStr);
        //System.out.println(ToStringBuilder.reflectionToString(obj.get(0)));
        //System.out.println(ToStringBuilder.reflectionToString(x[1].user));
        doFirstEnd();
        for (int i = 0; i < this.loopCount; i++) {
            new JSONArray(this.publicTlStr);
        }
        doEnd("jsonorg");
    }

    public void doJACKSON() throws IOException {
        doStart("jackson");
        ObjectMapper mapper = new ObjectMapper();
        PublicTimelineModel[] x = mapper.readValue(this.publicTlStr, PublicTimelineModel[].class);
        //System.out.println(ToStringBuilder.reflectionToString(x[1]));
        //System.out.println(ToStringBuilder.reflectionToString(x[1].user));
        doFirstEnd();
        for (int i = 0; i < this.loopCount; i++) {
            mapper.readValue(this.publicTlStr, PublicTimelineModel[].class);
        }
        doEnd("jackson");
    }

    public void doGSON() {
        doStart("gson");
        Gson gson = new Gson();
        PublicTimelineModel[] x = gson.fromJson(this.publicTlStr, PublicTimelineModel[].class);
        //System.out.println(ToStringBuilder.reflectionToString(x[1].user));
        doFirstEnd();
        for (int i = 0; i < this.loopCount; i++) {
            gson.fromJson(this.publicTlStr, PublicTimelineModel[].class);
        }
        doEnd("gson");
    }

    private void start() throws Exception {
        doJSONIC();
        doGSON();
        doJACKSON();
        doJSONORG();
    }

    public static void main(String[] args) throws Exception {
        JSONBench bench = new JSONBench();
        bench.start();
    }
}

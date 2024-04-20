


public class HTTPRequest {

    public HttpMethod method;
    public String url;
    public String content;
    public String htmlVersion;




    public HTTPRequest(String fullMessage){
        String[] split = fullMessage.split("\r\n");
        String[] line = split[0].split(" ");

        if(split.length < 3){
            nullInstanceInitialization();
            return;

        }

        method = HttpMethod.stringToMethod(line[0]);
        url = line[1];
        htmlVersion = line[2];

        if(method == HttpMethod.GET){
            content = "";
        }else{
            content = split[split.length - 1];
        }

    }


    private void nullInstanceInitialization(){
        method = HttpMethod.NULL;
        url = "";
        content = "";
        htmlVersion = "";
    }


    
}

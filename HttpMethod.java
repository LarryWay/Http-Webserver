import java.net.*;



public enum HttpMethod{
    GET,
    POST,
    PUT,
    DELETE,
    NULL;

    public static String methodToString(HttpMethod method){
        switch (method){
            case GET:
                return "GET";
            case POST:
                return "POST";
            case PUT:
                return "PUT";
            case DELETE:
                return "DELETE";
            default:
                return "";

        }
    }

    public static HttpMethod stringToMethod(String method){
        if(method.equals("GET")) return HttpMethod.GET;
        else if(method.equals("POST")) return HttpMethod.POST;
        else if(method.equals("PUT")) return HttpMethod.PUT;
        else if(method.equals("DELETE")) return HttpMethod.DELETE;
        else return HttpMethod.NULL;
    }

}
public enum StatusCode{

    OK_200,
    FOUND_302, 
    NOT_FOUND_404,
    SERVER_ERROR_500;
    
    public static String statusToString(StatusCode status){
        switch(status){
            case OK_200:
                return "200 OK";
            case FOUND_302:
                return "302";
            case NOT_FOUND_404:
                return "404 Not Found";
            case SERVER_ERROR_500:
                return "500";
            default:
                return "0";
        }
    }

}

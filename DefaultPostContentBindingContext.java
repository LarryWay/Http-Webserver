
public class DefaultPostContentBindingContext extends HttpContext{

    private static final Integer DPCBC_IDENTIFIER = 10;

    public DefaultPostContentBindingContext(HttpContext context, String url){
        super(context, DPCBC_IDENTIFIER);
        this.ruleset.setUrlRule(url);
        this.ruleset.setMethodRule(HttpMethod.POST);
    }


    @Override
    public void run(){
        try{
            getOutputStream().write(request.content.getBytes("UTF-8"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

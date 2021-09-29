package interview.dataquery.api;

public class NoDataFound extends Exception {

    public NoDataFound(String query) {
        super("no data found for " + query);
    }
}
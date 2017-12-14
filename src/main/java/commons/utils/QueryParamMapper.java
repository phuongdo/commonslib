package commons.utils;

import java.util.Map;

public class QueryParamMapper {
    public static String createQuery(String strParams, Map<String, String> mapParams) {
        for (Map.Entry<String, String> entry : mapParams.entrySet()) {
            strParams = strParams.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return strParams;
    }
}

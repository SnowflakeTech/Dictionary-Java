package uet.oop.ourtreedictionary;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.Objects;

public class GoogleTransAPIClient {
    /* Language Constants */
    static final String VN = "vi";
    static final String EN = "en";

    public static String callGoogleTrans(String translateThis, String toLanguage) {
        // Default query
        // final String emptyQueryValue = "%3CREQUIRED%3E";     Can be excluded
        String translateThisQuery = "q=EMPTY&target=TO&source=FROM";

        // Finish the query
        if (Objects.equals(translateThis, "")) {
//            translateThisQuery = translateThisQuery.replace("EMPTY", emptyQueryValue);
            return "";  // Return an empty String if the input is empty.
        } else {
            translateThisQuery = translateThisQuery.replace("EMPTY", translateThis);
        }

        if (toLanguage.equals(VN)) {
            translateThisQuery = translateThisQuery.replace("FROM", EN);
            translateThisQuery = translateThisQuery.replace("TO", VN);
        } else {
            translateThisQuery = translateThisQuery.replace("FROM", VN);
            translateThisQuery = translateThisQuery.replace("TO", EN);
        }

        // Json format: {"data":{"translations":[{"translatedText":"TRANSLATED WORD"}]}}
        // Length:      0123456789ABCDEFGHIJ123456789abcdefghij123456789ABCDEFGHIJ123456
        String response = getResponse(translateThisQuery);
        return response.substring(44, response.length() - 5);   // Return the translatedText only!
    }

    public static String getResponse(String translateThisQuery) {
        HttpResponse<String> response = Unirest.post("https://google-translate1.p.rapidapi.com/language/translate/v2")
                .header("content-type", "application/x-www-form-urlencoded")
                .header("accept-encoding", "application/gzip")
                .header("x-rapidapi-host", "google-translate1.p.rapidapi.com")
                .header("x-rapidapi-key", "dfacd18491mshcaa77bed9cf5f81p1504bejsn833f6ee177ab")
                .body(translateThisQuery)
                .asString();
        return response.getBody();
    }
}


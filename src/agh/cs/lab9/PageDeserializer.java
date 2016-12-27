package agh.cs.lab9;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by yurii on 27.12.16.
 */
public class PageDeserializer implements JsonDeserializer<Page> {

    int kadencja;

    public Page deserialize(final JsonElement jElement, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        // get array of "posel"
        JsonObject jPosly = jElement.getAsJsonObject();
        Posel[] posly = context.deserialize(jPosly.get("Dataobject"), Posel[].class);
        JsonElement links = jElement.getAsJsonObject().get("Links");

        // get links to next and last pages
        JsonObject jLinks = links.getAsJsonObject();
        String nextUrl;
        String lastUrl;

        // if it's last page
        try {
            nextUrl = jLinks.get("next").getAsString();
            lastUrl = jLinks.get("last").getAsString();
        } catch (NullPointerException nullEx) {
            nextUrl = "";
            lastUrl = "";
        }

        // make new page object
        Page page = new Page(posly);
        page.setNextUrl(nextUrl);
        page.setLastUrl(lastUrl);
        return page;
    }
}

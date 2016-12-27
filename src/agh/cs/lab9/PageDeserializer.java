package agh.cs.lab9;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by yurii on 27.12.16.
 */
public class PageDeserializer implements JsonDeserializer<PoselMap> {

    int kadencja;


    public PoselMap deserialize(final JsonElement jElement, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jPosly = jElement.getAsJsonObject();
        Posel[] posly = context.deserialize(jPosly.get("Dataobject"), Posel[].class);
        PoselMap poselMap = new PoselMap(kadencja);
        poselMap.setPosly(posly);
        return poselMap;
    }
}

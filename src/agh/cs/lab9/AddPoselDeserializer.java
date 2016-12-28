package agh.cs.lab9;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by yurii on 27.12.16.
 */
public class AddPoselDeserializer implements JsonDeserializer<Posel> {

    public Posel deserialize(final JsonElement jElement, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        // "layers" is nested so get it as a json element
        JsonElement layers = jElement.getAsJsonObject().get("layers");
        JsonObject jLayers = layers.getAsJsonObject();
        JsonElement wydatki = jLayers.getAsJsonObject().get("wydatki");
        JsonObject jWydatki = wydatki.getAsJsonObject();
        // deserialize it as a regular json element
        Wydatki[] wydatki1 = context.deserialize(jWydatki.get("roczniki"), Wydatki[].class);
        Posel posel = new Posel();
        posel.setWydatki(wydatki1);
        try {
            Wyjazdy[] wyjazdy = context.deserialize(jLayers.get("wyjazdy"), Wyjazdy[].class);
            posel.setWyjazdy(wyjazdy);
        } catch (Exception ex) {
            posel.setNoInfo("Niema informacji o wyjazdach.");
        }
        return posel;
    }

}

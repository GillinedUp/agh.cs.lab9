package agh.cs.lab9;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by yurii on 28.12.16.
 */
public class WydatkiDeserializer implements JsonDeserializer<Wydatki> {

    public Wydatki deserialize(final JsonElement jElement, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jWydatki = jElement.getAsJsonObject();
        JsonArray jPolaArray = jWydatki.get("pola").getAsJsonArray();
        float[] pola = new float[jPolaArray.size()];
        for (int i = 0; i < pola.length; i++) {
            JsonElement jPola = jPolaArray.get(i);
            pola[i] = jPola.getAsFloat();
        }
        int dokument_id = jWydatki.get("dokument_id").getAsInt();
        int rok = jWydatki.get("rok").getAsInt();
        Wydatki wydatki = new Wydatki();
        wydatki.setDokument_id(dokument_id);
        wydatki.setRok(rok);
        wydatki.setPola(pola);
        return wydatki;
    }

}

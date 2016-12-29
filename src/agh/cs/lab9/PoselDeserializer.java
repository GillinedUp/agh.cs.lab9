package agh.cs.lab9;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Created by yurii on 12/20/16.
 */
public class PoselDeserializer implements JsonDeserializer<Posel> {

    public Posel deserialize(final JsonElement jElement, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException{

        // "data" is nested so get it as a json element
        JsonElement data = jElement.getAsJsonObject().get("data");
        JsonObject jPosel = data.getAsJsonObject();

        // deserialize it as a regular json element
        String imiePierwsze = jPosel.get("poslowie.imie_pierwsze").getAsString();
        String imieDrugie = jPosel.get("poslowie.imie_drugie").getAsString();
        String nazwisko = jPosel.get("poslowie.nazwisko").getAsString();
        int id = jPosel.get("poslowie.id").getAsInt();

        Posel posel = new Posel();
        posel.setImiePierwsze(imiePierwsze);
        posel.setImieDrugie(imieDrugie);
        posel.setNazwisko(nazwisko);
        posel.setId(id);
        return posel;
    }

}

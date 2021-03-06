package bank;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  Class provides an adapter for GSON to Serialize and Deserialize the abstract class Transaction
 *  @see Transaction
 */

public class TransactionElementAdapter implements JsonSerializer<Transaction>, JsonDeserializer<Transaction> {

    /**
     * if its a list of transactions it will be stored here
     */
    private List<Transaction> lt = new ArrayList<>();

    /**
     * List of Deserilized Tranactions
     * @return Transaction
     */
    public List<Transaction> getTransactions(){ return lt; }

    /**
     * Resets Adapter
     */
    public void cleanAdapter(){lt = new LinkedList<Transaction>();

    }


    /**
     * Serilizer for Transactions
     * @param src the Transaction to Serilize
     * @param typeOfSrc Type of src
     * @param context Serlize Context
     * @return the json Element
     */
    @Override
    public JsonElement serialize(Transaction src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        JsonObject instance = new JsonObject();
        String classname = src.getClass().toString();
        String[] name = classname.split("\\.");
        classname = name[name.length-1];
        result.addProperty("CLASSNAME", classname);

        instance.addProperty("date", src.getDate());
        instance.addProperty("amount", src.getAmount());
        instance.addProperty("description", src.getDescription());

        if(src instanceof Payment){
            Payment p = (Payment) src;
            instance.addProperty("IncomingInterest", p.getIncomingInterest());
            instance.addProperty("OutgoingInterest", p.getOutgoingInterest());
        }else if(src instanceof Transfer){
            Transfer t = (Transfer)src;
            instance.addProperty("sender", t.getSender());
            instance.addProperty("recipient", t.getRecipient());
        }

        result.add("INSTANCE", instance);
        return result;
    }

    /**
     *
     * @param json The JSON to Deserilize
     * @param typeOfT The Type of the Json
     * @param context The context of the JSON
     * @return the Deserilized Transaction
     * @throws JsonParseException if something went wrong: read message
     */
    @Override
    public Transaction deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        // from here
        JsonArray ary = json.getAsJsonArray();
        for (int i = 0; i < ary.size(); i++){
            JsonObject jsonObject = ary.get(i).getAsJsonObject();
            String type = jsonObject.get("CLASSNAME").getAsString();
            JsonElement element = jsonObject.get("INSTANCE");
            switch (type) {
                case "Payment" -> this.lt.add(new Gson().fromJson(element, Payment.class));
                case "IncomingTransfer" -> this.lt.add(new Gson().fromJson(element, IncomingTransfer.class));
                case "OutgoingTransfer" -> this.lt.add(new Gson().fromJson(element, OutgoingTransfer.class));
                case "Transfer" -> this.lt.add(new Gson().fromJson(element, Transfer.class));
                default -> throw new JsonParseException("Unknown Typ");
            }
        }
        // To here is BODGE!

        return new Transaction() {
            @Override
            public double calculate() {
                return 0;
            }
        };
/*
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("CLASSNAME").getAsString();
        JsonElement element = jsonObject.get("INSTANCE");

        System.out.println(type);
        return switch (type){
            case "Payment" -> (new Gson().fromJson(element, Payment.class));
            case "IncomingTransfer" -> (new Gson().fromJson(element, IncomingTransfer.class));
            case "OutgoingTransfer" -> (new Gson().fromJson(element, OutgoingTransfer.class));
            case "Transfer" -> (new Gson().fromJson(element, Transfer.class));
            default -> throw new JsonParseException("Unknown Typ");
        };
 */
    }
}
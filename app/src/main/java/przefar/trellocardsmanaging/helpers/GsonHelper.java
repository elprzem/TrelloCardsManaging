package przefar.trellocardsmanaging.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import przefar.trellocardsmanaging.trello_classes.Board;
import przefar.trellocardsmanaging.trello_classes.NullResponse;

/**
 * Created by Przemys?aw Farion on 6/2/2015.
 */
public class GsonHelper {

    public static Board getBoardFromJson(String json) throws JsonSyntaxException {
        Board board;

        Gson gson = new Gson();
        board = gson.fromJson(json, Board.class);

        return board;
    }

    public static NullResponse getNullResponseFromJson(String json) {
        NullResponse nullResponse;

        Gson gson = new Gson();
        nullResponse = gson.fromJson(json, NullResponse.class);

        return nullResponse;
    }
}

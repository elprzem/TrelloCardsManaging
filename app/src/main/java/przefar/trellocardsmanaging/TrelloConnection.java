package przefar.trellocardsmanaging;

import android.content.Context;

import przefar.trellocardsmanaging.async_tasks.AddCardToListTask;
import przefar.trellocardsmanaging.async_tasks.DeleteCardTask;
import przefar.trellocardsmanaging.async_tasks.GetBoardTask;

/**
 * Created by Przemys?aw Farion on 6/2/2015.
 */
public class TrelloConnection {

    private Context context;

    public void initialize(Context context) {
        this.context = context;
    }


    public void getBoard(GetBoardTask.Callback callback) {
        new GetBoardTask(callback, context).execute(
                "https://api.trello.com/1/board/556c67a0d31d222c3a4380cd?lists=open&cards=all&" +
                        "key=5a8e15eab66ef71d1abfc0a1c597ac7d"
        );
    }

    public void addCardToList(AddCardToListTask.Callback callback, String listId) {
        new AddCardToListTask(callback, context).execute(
                listId
        );
    }

    public void deleteCard(DeleteCardTask.Callback callback, String cardId) {
        new DeleteCardTask(callback, context).execute(
                cardId
        );
    }
}

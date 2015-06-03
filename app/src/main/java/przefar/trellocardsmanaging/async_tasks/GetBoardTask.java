package przefar.trellocardsmanaging.async_tasks;

import android.content.Context;
import android.os.AsyncTask;

import przefar.trellocardsmanaging.trello_classes.Board;
import przefar.trellocardsmanaging.helpers.GsonHelper;
import przefar.trellocardsmanaging.helpers.JsonHelper;

/**
 * Created by Przemys?aw Farion on 6/2/2015.
 */
public class GetBoardTask extends AsyncTask<String, Integer, Board> {
    private GetBoardTask.Callback callback;
    private Context context;
    private final String LogTag = "GetBoardTask";

    public GetBoardTask(GetBoardTask.Callback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Board doInBackground(String... params) {
        Board board = null;
        try {
            String boardJson = JsonHelper.getJSON(params[0]);
            board = GsonHelper.getBoardFromJson(boardJson);
        } catch (Exception e){
            e.printStackTrace();
        }
        return board;
    }

    protected void onPostExecute(Board board) {
        callback.doAfterGetBoardTask(board);
    }

    public interface Callback {
        void doAfterGetBoardTask(Board board);
    }
}

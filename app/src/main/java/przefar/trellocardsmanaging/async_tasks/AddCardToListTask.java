package przefar.trellocardsmanaging.async_tasks;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Przemys?aw Farion on 6/3/2015.
 */
public class AddCardToListTask extends AsyncTask<String, Integer, Boolean> {
    private final Callback callback;
    private final Context context;

    public AddCardToListTask(Callback callback, Context context) {
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
    protected Boolean doInBackground(String... params) {
        String listId = params[0];

/*        try {
            String boardJson = JsonHelper.getJSON(params[0]);
            board = GsonHelper.getBoardFromJson(boardJson);
        } catch (Exception e){
            if(e.getMessage() != null){
                Log.e(LogTag, e.getMessage());
            } else {
                Log.e(LogTag,
                        "Occured some exception without message, e.toString(): " + e.toString());
            }
        }*/

        return null;
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p/>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param b The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Boolean b) {
        super.onPostExecute(b);
    }

    public interface Callback{
        void doAfterAddCardToListTask(boolean isSucceed);
    }
}

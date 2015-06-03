package przefar.trellocardsmanaging;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import przefar.trellocardsmanaging.async_tasks.DeleteCardTask;
import przefar.trellocardsmanaging.trello_classes.Card;

/**
 * Created by Przemys?aw Farion on 6/1/2015.
 */
public class CardsListAdapter extends RecyclerView.Adapter<CardsListAdapter.ViewHolder> {
    private final Context context;
    private final Callback callback;
    private ArrayList<Card> cards;
    private String LogTag = "CardsListAdapter";

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout cardItemLayout;
        public TextView infoText;
        public ViewHolder(LinearLayout v) {
            super(v);
            cardItemLayout = v;
            infoText = (TextView) v.findViewById(R.id.info_text);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CardsListAdapter(ArrayList<Card> cards, Context context, Callback callback) {
        this.cards = cards;
        this.context = context;
        this.callback = callback;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CardsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.infoText.setText(cards.get(position).getName());
        holder.cardItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(context)
                        .items(R.array.options_dialog_items)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(
                                    MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        editCard(position);
                                        break;
                                    case 1:
                                        moveCard(position);
                                        break;
                                    case 2:
                                        deleteCard(position);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
    }

    private void editCard(int position) {
        Log.i(LogTag, "editCard performing...");
    }

    private void moveCard(int position) {
        Log.i(LogTag, "moveCard performing...");
    }

    private void deleteCard(final int position) {
        TrelloConnection trelloConnection = new TrelloConnection();
        trelloConnection.initialize(context);
        trelloConnection.deleteCard(new DeleteCardTask.Callback() {
            @Override
            public void doAfterDeleteCardTask(Boolean isSucceed) {
                if(isSucceed){
                    cards.remove(position);
                    callback.refreshAfterAction();
                }
            }
        }, cards.get(position).getId());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cards.size();
    }

    public interface Callback{
        void refreshAfterAction();
    }
}

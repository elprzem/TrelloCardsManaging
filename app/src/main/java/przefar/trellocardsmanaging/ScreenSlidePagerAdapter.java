package przefar.trellocardsmanaging;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import przefar.trellocardsmanaging.fragments.CardsListFragment;
import przefar.trellocardsmanaging.trello_classes.Board;
import przefar.trellocardsmanaging.trello_classes.Card;
import przefar.trellocardsmanaging.trello_classes.List;

/**
 * Created by Przemys?aw Farion on 6/1/2015.
 */
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private Board board;

    public ScreenSlidePagerAdapter(FragmentManager fm, Board board) {
        super(fm);
        this.board = board;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragmentPage = new CardsListFragment();
        Bundle args = new Bundle();
        List list = board.getLists().get(position);
        String listId = list.getId();
        ArrayList<Card> cards = board.getCardsByListId(listId);
        args.putParcelableArrayList(BundleKeys.CARDS, cards);
        fragmentPage.setArguments(args);
        return fragmentPage;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return board.getLists().get(position).getName();
    }

    @Override
    public int getCount() {
        return board.getLists().size();
    }
}

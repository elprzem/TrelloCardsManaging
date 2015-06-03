package przefar.trellocardsmanaging.actvities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import przefar.trellocardsmanaging.async_tasks.AddCardToListTask;
import przefar.trellocardsmanaging.trello_classes.Board;
import przefar.trellocardsmanaging.fragments.CardsListFragment;
import przefar.trellocardsmanaging.async_tasks.GetBoardTask;
import przefar.trellocardsmanaging.R;
import przefar.trellocardsmanaging.ScreenSlidePagerAdapter;
import przefar.trellocardsmanaging.TrelloConnection;


public class MainActivity extends AppCompatActivity
        implements CardsListFragment.OnCardsListFragmentInteractionListener {

    private android.support.v4.view.ViewPager pager;
    private Board myBoard;
    private ScreenSlidePagerAdapter pagerAdapter;
    private TrelloConnection trelloConnection;
    private final String LogTag = "MainActivity";
    private String toDoListId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trelloConnection = new TrelloConnection();
        trelloConnection.initialize(this);
        downloadBoard();
    }

    private void downloadBoard() {
        try {
            trelloConnection.getBoard(new GetBoardTask.Callback() {
                @Override
                public void doAfterGetBoardTask(Board board) {
                    if(board == null){
                        Log.e(LogTag, "Variable 'board' is null");
                    } else {
                        myBoard = board;
                        try {
                            toDoListId = myBoard.getFirstListIdWithName("To do");
                        } catch (Exception e) {
                            e.printStackTrace();
                            toDoListId = "";
                        }
                        initializeTabsAndPager();
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initializeTabsAndPager() {
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), myBoard);
        pager.setAdapter(pagerAdapter);
        PagerSlidingTabStrip listsTabs = (PagerSlidingTabStrip) findViewById(R.id.lists_tabs);
        listsTabs.setViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_card) {
            addCard();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addCard() {
        trelloConnection.addCardToList(new AddCardToListTask.Callback() {
            @Override
            public void doAfterAddCardToListTask(boolean isSucceed) {

            }
        }, toDoListId);
    }

    @Override
    public void onDataStateChanged() {
        downloadBoard();
    }
}

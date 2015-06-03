package przefar.trellocardsmanaging.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import przefar.trellocardsmanaging.BundleKeys;
import przefar.trellocardsmanaging.trello_classes.Card;
import przefar.trellocardsmanaging.CardsListAdapter;
import przefar.trellocardsmanaging.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCardsListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CardsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardsListFragment extends Fragment {
    private ArrayList<Card> cards;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private OnCardsListFragmentInteractionListener mListener;
    private final String LogTag = "CardsListFragment";
    private SwipeRefreshLayout swipeRefreshLayout;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cards object with list of cards
     * @return A new instance of fragment CardsListFragment.
     */
    public static CardsListFragment newInstance(ArrayList<Card> cards) {
        CardsListFragment fragment = new CardsListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(BundleKeys.CARDS, cards);
        fragment.setArguments(args);
        return fragment;
    }

    public CardsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            cards = getArguments().getParcelableArrayList(BundleKeys.CARDS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards_list, container, false);
        initializeRecylerViewAndCards(view);
        return view;
    }

    private void updateData() {
        if(mListener != null) {
            mListener.onDataStateChanged();
        }
    }

    private void initializeRecylerViewAndCards(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        if(mRecyclerView != null) {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new CardsListAdapter(cards, getActivity(), new CardsListAdapter.Callback() {
                @Override
                public void refreshAfterAction() {
                    updateData();
                }
            });
            mRecyclerView.setAdapter(mAdapter);
        } else {
            Log.e(LogTag, "Variable 'mRecyclerView' is null");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnCardsListFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCardsListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCardsListFragmentInteractionListener {
        void onDataStateChanged();
    }

}

package przefar.trellocardsmanaging.trello_classes;

import java.util.ArrayList;

/**
 * Created by Przemys?aw Farion on 6/1/2015.
 */
public class Board {
    private String id;
    private String name;
    private String desc;
    private String url;
    private String shortLink;
    private String dateLastActivity;
    private String dateLastView;
    private String shortUrl;
    private ArrayList<List> lists;
    private ArrayList<Card> cards;

    public String  getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public String getShortLink() {
        return shortLink;
    }

    public String getDateLastActivity() {
        return dateLastActivity;
    }

    public String getDateLastView() {
        return dateLastView;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public ArrayList<List> getLists() {
        return lists;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Card> getCardsByListId(String id) {
        ArrayList<Card> cardsByListId = new ArrayList<>();
        for(Card card : cards){
            if(card.getIdList().equals(id)){
                cardsByListId.add(card);
            }
        }
        return cardsByListId;
    }

    public String getFirstListIdWithName(String name) throws Exception {
        for(List list : lists){
            if(list.getName().equals(name)){
                return list.getId();
            }
        }
        throw new Exception("no such listId");
    }
}

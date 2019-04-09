package mygame.game;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mygame.item.BagItem;
import mygame.item.BagSlot;
import mygame.type.BagItemType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeroBag {

    private static final int MAX_LENGTH = 80;
    private BagSlot[] bag = new BagSlot[MAX_LENGTH];

    public HeroBag() {
        for (int i = 0; i < MAX_LENGTH; i++){
            bag[i] = new BagSlot();
        }
    }

    public BagItem getBagItem(int index){
        BagSlot slot = bag[index];
        BagItem item = slot.getBagItem();
        return item;
    }

    public boolean setBagItem(BagItem bagItem){
        if (isFull())
            return false;
//        BagSlot slot = bag[MAX_LENGTH - 1];
        for (BagSlot slot:
             bag) {
            if (slot.getBagItem() == null){
                slot.setBagItem(bagItem);
                break;
            }
        }
        return true;
    }

    public void removeBagItem(int index){
        BagSlot slot = bag[index];
        slot.setBagItem(null);
    }
    public int nowLength(){
        int nowLength = 0;
        for (int i = 0; i < MAX_LENGTH; i++) {
            if(bag[i].getBagItem() != null)
                nowLength++;
        }
        return nowLength;
    }
    public boolean isFull(){
         return nowLength() == MAX_LENGTH;
    }

    public JSONArray getBagInfo(){
        JSONArray array = new JSONArray();
        for (int i = 0; i < MAX_LENGTH; i++) {
            JSONObject object = new JSONObject();
            BagSlot slot = bag[i];
            BagItem item = slot.getBagItem();
            object.put("index", i);
            if (item != null){
                BagItemType bagItemType = item.getBagItemType();
                int level = slot.getBagItem().getLevel();
                object.put("type", bagItemType);
                object.put("level", level);
            }else {
                object.put("type", "none");
                object.put("level", 0);
            }
            array.add(object);
        }
        return array;
    }
}

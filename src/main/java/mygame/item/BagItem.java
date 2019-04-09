package mygame.item;

import mygame.type.BagItemType;

public interface BagItem {
    BagItemType getBagItemType();
    String getName();
    int getLevel();
    int getEnhanceLevel();
}

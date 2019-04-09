package mygame.item;

import mygame.type.BagItemType;

public class BagSlot implements Slot, Comparable<BagSlot> {

    private BagItem bagItem;

    public void setBagItem(BagItem bagItem) {
        this.bagItem = bagItem;
    }

    @Override
    public BagItem getBagItem() {
        return bagItem;
    }

    @Override
    public int compareTo(BagSlot o) {
        if (o.bagItem == null)
            return 4;
        if (bagItem == null)
            return -4;
        return bagItem.getBagItemType().getIndex() - o.bagItem.getBagItemType().getIndex();
    }
}

package com.panjin.rocketmq.event;

/**
 * @author panjin
 */
public class CartItemEvent implements java.io.Serializable {

    private static final long serialVersionUID = 2731744483631408936L;
    private String itemId;
    private int quantity;

    public CartItemEvent() {
    }
    public CartItemEvent(String itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemEvent{" + "itemId='" + itemId + '\'' + ", quantity=" + quantity + '}';
    }
}

package wtool;

/**
* Item
* Maintenance tool for MacBooks
* Copyright (c) 2019. All Rights Reserved.
* @author Martin Sanfilippo
* @version 1.0
*
*/

public class Item {

    private int id;
    private String order;
    private String source;
    private String target;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", order=" + order + ", source=" + source + ", target=" + target + "]";
    }

}
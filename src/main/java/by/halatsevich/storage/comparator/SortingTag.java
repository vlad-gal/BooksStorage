package by.halatsevich.storage.comparator;

public enum SortingTag {
    BOOK_ID(true), NAME(true), AUTHORS(true), PAGE(true), PRICE(true);

    private boolean ascend;

    SortingTag(boolean ascend) {
        this.ascend = ascend;
    }

    public boolean isAscend() {
        return ascend;
    }

    public void setAscend(boolean ascend) {
        this.ascend = ascend;
    }
}


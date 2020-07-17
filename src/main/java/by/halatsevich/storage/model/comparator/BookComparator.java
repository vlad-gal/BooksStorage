package by.halatsevich.storage.model.comparator;

import by.halatsevich.storage.model.entity.Book;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
    private SortingTag sortingTag;

    public BookComparator(SortingTag sortingTag, boolean ascend) {
        this.sortingTag = sortingTag;
        this.sortingTag.setAscend(ascend);
    }

    public void setSortingType(SortingTag sortingTag, boolean ascend) {
        if (sortingTag == null) {
            sortingTag = SortingTag.BOOK_ID;
        }
        this.sortingTag = sortingTag;
        this.sortingTag.setAscend(ascend);
    }

    public SortingTag getSortingType() {
        return sortingTag;
    }

    @Override
    public int compare(Book o1, Book o2) {
        int compareResult;
        switch (sortingTag) {
            case BOOK_ID:
                if (sortingTag.isAscend()) {
                    compareResult = (int) (o1.getBookId() - o2.getBookId());
                } else {
                    compareResult = (int) (o2.getBookId() - o1.getBookId());
                }
                break;
            case NAME:
                if (sortingTag.isAscend()) {
                    compareResult = o1.getName().compareTo(o2.getName());
                } else {
                    compareResult = o2.getName().compareTo(o1.getName());
                }
                break;
            case PAGE:
                if (sortingTag.isAscend()) {
                    compareResult = o1.getPages() - o2.getPages();
                } else {
                    compareResult = o2.getPages() - o1.getPages();
                }
                break;
            case PRICE:
                if (sortingTag.isAscend()) {
                    compareResult = Double.compare(o1.getPrice(), o2.getPrice());
                } else {
                    compareResult = Double.compare(o2.getPrice(), o1.getPrice());
                }
                break;
            case AUTHORS:
                if (sortingTag.isAscend()) {
                    compareResult = o1.getAuthors().size() - o2.getAuthors().size();
                } else {
                    compareResult = o2.getAuthors().size() - o1.getAuthors().size();
                }
                break;
            default:
                if (sortingTag.isAscend()) {
                    compareResult = (int) (o1.getBookId() - o2.getBookId());
                } else {
                    compareResult = (int) (o2.getBookId() - o1.getBookId());
                }
                break;
        }
        return compareResult;
    }
}

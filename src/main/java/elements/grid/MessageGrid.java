package elements.grid;

import elements.Element;
import elements.util.CustomBy;

public class MessageGrid extends Element {
    private static final String MESSAGE_GRID_XPATH = ".//div[contains(@class, 'UI')]//table[contains(@role, 'grid')]";

    public MessageGrid() {
        super(CustomBy.xpath(MESSAGE_GRID_XPATH));
    }

    public Row getRowByText(String text) {
        return new Row(CustomBy.text(text)).withParent(this);
    }
}

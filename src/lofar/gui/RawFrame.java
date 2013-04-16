package lofar.gui;

import javax.swing.JFrame;

import lofar.Viz;
import lofar.dataFormats.rawData.RawData;

@SuppressWarnings("serial")
public final class RawFrame extends JFrame {
    Viz viz;
    RawPanel panel;

    public RawFrame(final Viz viz, final RawData rawData) {
        this.viz = viz;
        panel = new RawPanel(this, rawData);
        add(panel);
    }
}

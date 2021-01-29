package uk.ac.ebi.intact.style.mapper.continuous;

import uk.ac.ebi.intact.style.mapper.Mapper;
import uk.ac.ebi.intact.style.model.legend.SummaryEdgeWidthLegend;

public class SummaryEdgeWidthMapper implements Mapper<Integer, Integer> {
    @Override
    public Integer getStyleOf(Integer key) {
        return 2 + Math.min(key, 25);
    }

    public SummaryEdgeWidthLegend createLegend() {
        SummaryEdgeWidthLegend summaryEdgeWidthLegend = new SummaryEdgeWidthLegend();
        summaryEdgeWidthLegend.setMinWidth(3);
        summaryEdgeWidthLegend.setMaxWidth(28);
        summaryEdgeWidthLegend.setMinValue(1);
        summaryEdgeWidthLegend.setMaxValue(25);
        return summaryEdgeWidthLegend;
    }
}

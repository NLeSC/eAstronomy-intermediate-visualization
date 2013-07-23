package lofar.flaggers;

/*
Wanneer je informatie hebt in frequentierichting zou je ook kunnen
overwegen om (iteratief?) te "smoothen" in die richting: dus eerst een
eerste ongevoelige (sum)threshold om de grootste pieken eruit te halen,
dan een lowpass filter (e.g. 1d Gaussian convolution) op de
frequentierichting uitvoeren, thresholden+smoothen nogmaals herhalen op
het verschil en dan een laatste keer sumthresholden. Daardoor
"vergelijk" je kanalen onderling en wordt je ook gevoeliger voor
veranderingen niet-broadband RFI. Wanneer je een "absolute" sumthreshold
"broadband" detector + de gesmoothde spectrale detector combineert is
dit is erg effectief, denk ik. Dit is natuurlijk een geavanceerdere
thresholder en vast niet het eerste algoritme wat je wilt implementeren
-- ik weet uberhaubt niet of het technisch mogelijk is op de blue gene,
maar het is een idee. Het is niet zoo zwaar om dit te doen.
 */
public class PostCorrelationSmoothedSumThresholdFlagger extends PostCorrelationSumThresholdFlagger {

    public PostCorrelationSmoothedSumThresholdFlagger(final int nrChannels, final float baseSensitivity, final float SIREtaValue) {
        super(nrChannels, baseSensitivity, SIREtaValue);
    }

    // we have the data for one second, all frequencies in a subband.
    // if one of the polarizations exceeds the threshold, flag them all.
    @Override
    protected void flag(final float[] powers, final boolean[] flagged, final int pol) {

        calculateWinsorizedStatistics(powers, flagged); // sets mean, median, stdDev

        //System.err.println("mean = " + mean + ", median = " + median + ", stdDev = " + stdDev);

        // first do an insensitive sumthreshold
        final float originalSensitivity = getBaseSensitivity();
        setBaseSensitivity(originalSensitivity * 1.0f); // higher number is less sensitive!
        sumThreshold1D(powers, flagged); // sets flags, and replaces flagged samples with threshold

        //                int flagged1 = getNrFlags(flagged);

        // smooth
        final float[] smoothedPower = oneDimensionalGausConvolution(powers, 0.5f); // 2nd param is sigma, heigth of the gauss curve

        // calculate difference
        final float[] diff = new float[nrChannels];
        for (int i = 0; i < nrChannels; i++) {
            diff[i] = powers[i] - smoothedPower[i];
        }

        //                for (int i = 0; i < nrChannels; i++) {
        //                    System.err.println("power i = " + power[i] + ", smoothed = " + smoothedPower[i] + ", diff = " + diff[i]);
        //                }

        // flag based on difference
        calculateWinsorizedStatistics(diff, flagged); // sets mean, median, stdDev                
        setBaseSensitivity(originalSensitivity * 1.0f); // higher number is less sensitive!
        sumThreshold1D(diff, flagged);
        //                int flagged2 = getNrFlags(flagged);

        // and one final pass on the flagged power
        calculateWinsorizedStatistics(powers, flagged); // sets mean, median, stdDev
        setBaseSensitivity(originalSensitivity * 0.80f); // higher number is less sensitive!
        sumThreshold1D(powers, flagged);
        //                int flagged3 = getNrFlags(flagged);
        /*
                        if (flagged1 != flagged2) {
                            System.err.println("A flagged1 = " + flagged1 + ", flagged2 = " + flagged2 + ", flagged3 = " + flagged3);
                        }
                        if (flagged2 != flagged3) {
                            System.err.println("B flagged1 = " + flagged1 + ", flagged2 = " + flagged2 + ", flagged3 = " + flagged3);
                        }
        */
        setBaseSensitivity(originalSensitivity);
    }
}

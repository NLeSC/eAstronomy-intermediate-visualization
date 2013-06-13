package lofar.dataFormats.preprocessedData;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.channels.FileChannel;

import lofar.dataFormats.DataProvider;
import lofar.dataFormats.MinMaxVals;
import lofar.flaggers.Flagger;
import lofar.flaggers.IntermediateFlagger;

public abstract class PreprocessedData extends DataProvider {

    private static final boolean SHOW_SMOOTH = false;
    private static final boolean SHOW_SMOOTH_DIFF = false;

    protected float[][][][] data; // [time][nrSubbands][nrPolarizations][nrChannels]
    //    protected float[][][][] data; // [time][nrSubbands][nrChannels][nrPolarizations]
    protected boolean[][][] initialFlagged; // [time][nrSubbands][nrChannels]
    protected boolean[][][] flagged; // [time][nrSubbands][nrChannels]
    protected int nrStations;
    protected int nrSubbands;
    protected int nrChannels;
    protected int nrTimes;
    protected int nrPolarizations;
    protected int integrationFactor;
    protected MinMaxVals minMaxVals;
    private static final boolean SCALE_PER_SUBBAND = false;
    private float min;
    private float scaleValue;

    private int station = 0;
    private int pol = 0;

    public PreprocessedData(final String fileName, final int integrationFactor, final int maxSequenceNr, final int maxSubbands) {
        super(fileName, maxSequenceNr, maxSubbands, new String[] { "none", "Intermediate" });
        this.integrationFactor = integrationFactor;
    }

    public void read() throws IOException {
        final FileInputStream fin = new FileInputStream(fileName);
        final DataInputStream din = new DataInputStream(fin);

        // TODO READ CORRECT STATION (Skip data)

        nrStations = din.readInt();
        nrTimes = din.readInt() / integrationFactor;
        final int nrSubbandsInFile = din.readInt();
        nrChannels = din.readInt();
        nrPolarizations = din.readInt();

        nrSubbands = nrSubbandsInFile;
        if (maxSubbands < nrSubbandsInFile) {
            nrSubbands = maxSubbands;
        }

        System.err.println("nrTimes = " + (nrTimes * integrationFactor) + ", with integration, time = " + nrTimes
                + ", nrSubbands = " + nrSubbandsInFile + ", nrChannels = " + nrChannels);

        if (maxSequenceNr < nrTimes) {
            nrTimes = maxSequenceNr;
        }

        data = new float[nrTimes][nrSubbands][nrPolarizations][nrChannels];
        flagged = new boolean[nrTimes][nrSubbands][nrChannels];
        initialFlagged = new boolean[nrTimes][nrSubbands][nrChannels];

        final long start = System.currentTimeMillis();

        final ByteBuffer bb = ByteBuffer.allocateDirect(integrationFactor * nrSubbandsInFile * nrChannels * nrPolarizations * 4);
        bb.order(ByteOrder.BIG_ENDIAN);
        final FloatBuffer fb = bb.asFloatBuffer();
        final FileChannel channel = fin.getChannel();

        for (int second = 0; second < nrTimes; second++) {
            if (second > maxSequenceNr) {
                break;
            }

            bb.rewind();
            fb.rewind();
            final int res = channel.read(bb); // TODO can return less bytes!
            if (res != bb.capacity()) {
                System.err.println("read less bytes! Expected " + bb.capacity() + ", got " + res);
            }
            if (res < 0) {
                break;
            }

            for (int time = 0; time < integrationFactor; time++) {
                for (int sb = 0; sb < nrSubbandsInFile; sb++) {
                    for (int ch = 0; ch < nrChannels; ch++) {
                        for (int pol = 0; pol < nrPolarizations; pol++) {
                            float sample = fb.get();
                            if (sb < maxSubbands) {
                                if (sample < 0.0f) {
                                    initialFlagged[second][sb][ch] = true;
                                    flagged[second][sb][ch] = true;
                                } else {
                                    data[second][sb][pol][ch] += sample;
                                }
                            }
                        }
                    }
                }
            }
        }

        final long end = System.currentTimeMillis();
        final double iotime = (end - start) / 1000.0;
        final double mbs = (integrationFactor * nrTimes * nrSubbandsInFile * nrChannels * 4.0) / (1024.0 * 1024.0);
        final double speed = mbs / iotime;
        System.err.println("read " + mbs + "MB in " + iotime + " s, speed = " + speed + " MB/s.");

        fin.close();
        din.close();

        long initialFlaggedCount = 0;

        if (SHOW_SMOOTH || SHOW_SMOOTH_DIFF) {
            for (int time = 0; time < nrTimes; time++) {
                for (int sb = 0; sb < nrSubbands; sb++) {
                    for (int pol = 0; pol < nrPolarizations; pol++) {
                        float[] tmp = new float[nrChannels];
                        for (int ch = 0; ch < nrChannels; ch++) {
                            tmp[ch] = data[time][sb][pol][ch];
                        }
                        float[] tmp2 = Flagger.oneDimensionalGausConvolution(tmp, 10.0f);
                        for (int ch = 0; ch < nrChannels; ch++) {
                            if (SHOW_SMOOTH) {
                                data[time][sb][pol][ch] = tmp2[ch];
                            } else if (SHOW_SMOOTH_DIFF) {
                                data[time][sb][pol][ch] = (float) Math.abs(tmp2[ch] - data[time][sb][ch][pol]);
                            }
                        }
                    }
                }
            }
        }

        // calc min and max for scaling
        // set flagged samples to 0.
        minMaxVals = new MinMaxVals(nrSubbandsInFile);
        for (int time = 0; time < nrTimes; time++) {
            for (int sb = 0; sb < nrSubbands; sb++) {
                for (int ch = 0; ch < nrChannels; ch++) {
                    for (int pol = 0; pol < nrPolarizations; pol++) {
                        if (initialFlagged[time][sb][ch]) {
                            data[time][sb][pol][ch] = 0.0f;
                            initialFlaggedCount++;
                        } else {
                            final float sample = data[time][sb][pol][ch];
                            minMaxVals.processValue(sample, sb);
                        }
                    }
                }
            }
        }
        min = minMaxVals.getMin();
        scaleValue = minMaxVals.getMax() - min;

        System.err.println("sampled already flagged in data set: " + initialFlaggedCount);

    }

    @Override
    public void flag() {
        for (int time = 0; time < nrTimes; time++) {
            for (int sb = 0; sb < nrSubbands; sb++) {
                for (int ch = 0; ch < nrChannels; ch++) {
                    flagged[time][sb][ch] = initialFlagged[time][sb][ch];
                }
            }
        }

        if (flaggerType.equals("none")) {
            return;
        }

        if (nrChannels > 1) {
            final IntermediateFlagger[] flaggers = new IntermediateFlagger[nrSubbands];
            for (int i = 0; i < nrSubbands; i++) {
                flaggers[i] = new IntermediateFlagger(flaggerSensitivity, flaggerSIRValue);
            }

            for (int time = 0; time < nrTimes; time++) {
                for (int sb = 0; sb < nrSubbands; sb++) {
                    flaggers[sb].flag(data[time][sb], flagged[time][sb]);
                }
            }
        } else {
            final IntermediateFlagger flagger = new IntermediateFlagger(flaggerSensitivity, flaggerSIRValue);
            for (int time = 0; time < nrTimes; time++) {
                final boolean[] tmpFlags = new boolean[nrSubbands];
                final float[][] tmp = new float[nrPolarizations][nrSubbands];

                for (int pol = 0; pol < nrPolarizations; pol++) {
                    for (int sb = 0; sb < nrSubbands; sb++) {
                        tmp[pol][sb] = data[time][sb][pol][0];
                    }
                }
                flagger.flag(tmp, tmpFlags);
                for (int sb = 0; sb < nrSubbands; sb++) {
                    flagged[time][sb][0] = tmpFlags[sb];
                }
            }
        }
    }

    public final int getTotalTime() {
        return nrTimes;
    }

    public final int getTotalFreq() {
        return nrSubbands * nrChannels;
    }

    public final int getNrChannels() {
        return nrChannels;
    }

    public final int getNrSubbands() {
        return nrSubbands;
    }

    @Override
    public final int getSizeX() {
        return nrTimes;
    }

    @Override
    public final int getSizeY() {
        return nrSubbands * nrChannels;
    }

    @Override
    public final float getRawValue(final int x, final int y) {
        final int subband = y / nrChannels;
        final int channel = y % nrChannels;

        return data[x][subband][pol][channel];
    }

    @Override
    public final float getValue(final int x, final int y) {
        final int subband = y / nrChannels;
        final int channel = y % nrChannels;
        final float sample = data[x][subband][pol][channel];

        if (SCALE_PER_SUBBAND) {
            return (sample - minMaxVals.getMin(subband)) / (minMaxVals.getMax(subband) - minMaxVals.getMin(subband));
        } else {
            return (sample - min) / scaleValue;
        }
    }

    @Override
    public final boolean isFlagged(final int x, final int y) {
        final int subband = y / nrChannels;
        final int channel = y % nrChannels;
        return flagged[x][subband][channel];
    }
}

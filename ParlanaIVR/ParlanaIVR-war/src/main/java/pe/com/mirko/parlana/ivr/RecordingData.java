/*****************************************************************************
 * © 2016 Avaya Inc. All rights reserved.
 ****************************************************************************/
package pe.com.mirko.parlana.ivr;


public enum RecordingData
{
    INSTANCE;
    private String recordingFilename = null;

    public String getRecordingFilename()
    {
        return recordingFilename;
    }

    public void setRecordingFilename(final String recordingFilename)
    {
        this.recordingFilename = recordingFilename;
    }
}

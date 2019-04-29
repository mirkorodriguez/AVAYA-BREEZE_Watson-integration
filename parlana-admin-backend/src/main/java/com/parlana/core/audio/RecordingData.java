/*****************************************************************************
 * © 2016 Avaya Inc. All rights reserved.
 ****************************************************************************/
package com.parlana.core.audio;


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

/*****************************************************************************
 * © 2016 Avaya Inc. All rights reserved.
 ****************************************************************************/
package pe.com.mirko.parlana.ivr;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import com.avaya.collaboration.util.logger.Logger;

@WebServlet("/StoreRecordingServlet/*")
@MultipartConfig
public class StoreRecordingServlet extends HttpServlet
{   
    private static final long serialVersionUID = 1L;
    private transient final Logger logger = Logger.getLogger(StoreRecordingServlet.class);

    public StoreRecordingServlet()
    {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        logger.info("StoreRecordingServlet doPost ENTER");
        try
        {
            final File audioFile = getFile(request, response);
            final boolean isDeleted = audioFile.delete();
            if (isDeleted)
            {
                logger.info("StoreRecordingServlet doPost previous record file is removed.");
            }
            final FileOutputStream saveAudioFile = new FileOutputStream(audioFile);
            final Part audioPartOfFile = request.getPart("rec_data");

            final InputStream audioInput = audioPartOfFile.getInputStream();
            final byte audioBytes[] = new byte[(int) audioPartOfFile.getSize()];

            while ((audioInput.read(audioBytes)) != -1)
            {
                InputStream byteAudioStream = new ByteArrayInputStream(decode(audioBytes));
                final AudioFormat audioFormat = getAudioFormat();
                AudioInputStream audioInputStream =
                        new AudioInputStream(byteAudioStream, audioFormat, audioBytes.length);

                if (AudioSystem.isFileTypeSupported(AudioFileFormat.Type.WAVE,
                        audioInputStream))
                {
                    AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, saveAudioFile);
                }

            }
            audioInput.close();
            saveAudioFile.flush();
            saveAudioFile.close();
            logger.info("recording saved as " + audioFile.getAbsolutePath());
            RecordingData.INSTANCE.setRecordingFilename(audioFile.getName());
        }
        catch (final Exception e)
        {
            response.setContentType("text/plain");
            PrintWriter pw = response.getWriter();
            pw.write("doPost exception=" + e);
        }
        logger.info("StoreRecordingServlet doPost EXIT");
        
    }
	
    @Override
    protected void doPut(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException,
            IOException
    {
        logger.info("StoreRecordingServlet doPut ENTER");
        final File playPrevRecordedFile = getFile(request, response);

        if (playPrevRecordedFile.exists())
        {
            logger.finer("StoreRecordingServlet doPut record file exists");
            response.setContentType("text/plain");
            PrintWriter pw = response.getWriter();
            pw.write("EXISTS");
        }
        logger.info("StoreRecordingServlet doPut EXIT");
    }

    @Override
    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
    {
        logger.info("StoreRecordingServlet doDelete ENTER");
        final ServletContext callableServiceServletContext = getServletContext();
        final String contextPath = callableServiceServletContext.getRealPath("/");
        final String filename = RecordingData.INSTANCE.getRecordingFilename();
        logger.info(filename);
        if (filename != null)
        {
            final File audioFile = new File(contextPath, filename);
            logger.info("Is file exstis " + audioFile.exists());
            if (audioFile.exists())
            {
                logger.info(audioFile.getAbsolutePath());
            }
            final boolean isDeleted = audioFile.delete();
            if (isDeleted)
            {
                RecordingData.INSTANCE.setRecordingFilename(null);
                logger.info("file deleted " + audioFile.getAbsolutePath());
            }
        }
        logger.info("StoreRecordingServlet doDelete EXIT");
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException
    {
        logger.info("StoreRecordingServlet doGet ENTER");
        final ServletContext callableServiceServletContext = getServletContext();
        final String contextPath = callableServiceServletContext.getRealPath("/");
        final String filename = RecordingData.INSTANCE.getRecordingFilename();
        logger.info("filename: " + filename);
        if (filename != null)
        {
            final File audioFile = new File(contextPath, filename);
            if (audioFile.exists())
            {
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
                final FileInputStream audioFileStream = new FileInputStream(audioFile);
                final PrintWriter out = response.getWriter();
                int data = 0;
                while ((data = audioFileStream.read()) != -1)
                {
                    out.write(data);
                }
                out.close();
                audioFileStream.close();
            }
            else
            {
                response.setStatus(404);
                response.sendError(404, "audio file does not exist");
            }
        }
        logger.info("StoreRecordingServlet doGet EXIT");
            
    }

	
    public File getFile(final HttpServletRequest request, final HttpServletResponse response)
    {
        final ServletContext callableServiceServletContext = getServletContext();
        final String contextPath = callableServiceServletContext.getRealPath("/");
        final String filename = request.getPathInfo();
        logger.info("StoreRecordingServlet store recording as " + filename);
        return new File(contextPath, filename);
    }

    public static byte[] decode(byte[] encodedAudioBytes) throws MessagingException, IOException
    {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encodedAudioBytes);
        InputStream b64InputStream = MimeUtility.decode(byteArrayInputStream, "base64");

        byte[] tmpAudioBytes = new byte[encodedAudioBytes.length];
        int numberOfBytes = b64InputStream.read(tmpAudioBytes);
        byte[] decodedAudioBytes = new byte[numberOfBytes];

        System.arraycopy(tmpAudioBytes, 0, decodedAudioBytes, 0, numberOfBytes);

        return decodedAudioBytes;
    }

    /*
     * Avaya recommends that audio played by Avaya Aura MS be encoded as 16-bit, 8 kHz, single channel, PCM files. Codecs other than PCM or
     * using higher sampling rates for higher quality recordings can also be used, however, with reduced system performance. Multiple
     * channels, like stereo, are not supported.
     * 
     * @see Using Web Services on Avaya Aura Media Server Release 7.7, Issue 1, August 2015 on support.avaya.com
     */
    private AudioFormat getAudioFormat()
    {
        final float sampleRate = 8000.0F;
        // 8000,11025,16000,22050,44100
        final int sampleSizeInBits = 16;
        // 8,16
        final int channels = 1;
        // 1,2
        final boolean signed = true;
        // true,false
        final boolean bigEndian = false;
        // true,false
        return new AudioFormat(
                sampleRate,
                sampleSizeInBits,
                channels,
                signed,
                bigEndian);
    }
}

package org.myorg;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import javax.xml.stream.util.StreamReaderDelegate;
import java.io.*;

public class Log4netRecordReader extends RecordReader<LongWritable, Text> {
    String[] locations;
    BufferedReader br;
    String currentLine;
    private int pos;

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        locations = inputSplit.getLocations();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if(br == null) {
            FileInputStream fstream = new FileInputStream(locations[0]);
            DataInputStream in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));
        }

        pos++;
        String line = read();
        while(line != "")
        {
            currentLine += line;
        }

        return currentLine == "";
    }

    private String read() throws IOException {
        String line = br.readLine();
        if(line == null)
            return "";
        return line;
    }

    @Override
    public LongWritable getCurrentKey() throws IOException, InterruptedException {
        return new LongWritable();
    }

    @Override
    public Text getCurrentValue() throws IOException, InterruptedException {
        return new Text(currentLine);
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return pos;
    }

    @Override
    public void close() throws IOException {
        br.close();
    }
}

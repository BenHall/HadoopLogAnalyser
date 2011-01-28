package org.myorg;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.SplittableCompressionCodec;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class Log4netInputFormat extends FileInputFormat<LongWritable, Text> {

  public RecordReader<LongWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context)
  {
    return new Log4netRecordReader();
  }

  protected boolean isSplitable(JobContext context, Path file)
  {
    CompressionCodec codec = new CompressionCodecFactory(context.getConfiguration()).getCodec(file);

    if (null == codec) {
      return true;
    }
    return codec instanceof SplittableCompressionCodec;
  }
}

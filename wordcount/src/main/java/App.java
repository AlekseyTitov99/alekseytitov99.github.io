import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

import java.awt.*;
import java.io.FileNotFoundException;

public class App {

    public static void main( String[] args ) throws FileNotFoundException, java.io.IOException  {
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(500);
        frequencyAnalyzer.setMinWordLength(3);


        final java.util.List<WordFrequency> wordFrequencies = frequencyAnalyzer.load("song.txt");

        final Dimension dimension = new Dimension(438, 171);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(2);
        wordCloud.setBackground(new PixelBoundryBackground("work.png"));
        wordCloud.setFontScalar(new LinearFontScalar(4, 10));
        wordCloud.setBackgroundColor(new Color(0xC0C0C0));
        wordCloud.setColorPalette(new ColorPalette(new Color(0x000000), new Color(0xFFFFFF)));


        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("wordcloud.png");
    }
}

package org.podcast.wizz;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.podcast.wizz.model.PodcastDownload;
import org.podcast.wizz.service.PodcastManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class PodcastTest {
    static List<PodcastDownload> podcastDownloadList;

    @BeforeClass
    public static void setup() {
        podcastDownloadList = extractPodcastsFromFile("/downloads.txt");
    }

    @Test
    public void testJsonUnmarshal() {
        Assert.assertNotNull(podcastDownloadList);
        Assert.assertFalse(podcastDownloadList.isEmpty());
        Assert.assertEquals("Not full list, should be 100!", 100, podcastDownloadList.size());
    }

    @Test
    public void testMostDownloadedPodcast() {
        PodcastManager manager = new PodcastManager();
        Map.Entry<String, Integer> results = manager.extractMostDownloadedPodcastFromCity(podcastDownloadList, "san francisco");
        Assert.assertNotNull(results);
        Assert.assertEquals("Show Id is not correct", "Who Trolled Amber", results.getKey());
        Assert.assertEquals("Should contains 24", 24, results.getValue().intValue()); //downloads.txt contains 40 -- this will fail
    }

    @Test
    public void testExtractTopDevice() {
        PodcastManager manager = new PodcastManager();
        Map.Entry<String, Integer> results = manager.extractTopDevice(podcastDownloadList);
        Assert.assertNotNull(results);
        System.out.println("Cel mai ascultat device este "+results.getKey()+" si este downloaded de "+results.getValue());
        Assert.assertEquals("DeviceId is not correct", "mobiles & tablets", results.getKey());
        Assert.assertEquals("Should contains 60", 60, results.getValue().intValue());
    }



    @Test
    public void testShowIdPrerollCount() {
        PodcastManager manager = new PodcastManager();
        LinkedHashMap<String, Long> results = manager.showIdPrerollCount(podcastDownloadList);

        String[] resultKeys = results.keySet().toArray(new String[4]);
        Assert.assertTrue("Should be: Show Id: Stuff You Should Know, Preroll Opportunity Number: 40", "Stuff You Should Know".equals(resultKeys[0]) && 40 == results.get(resultKeys[0]));
        Assert.assertTrue("Show Id: Who Trolled Amber, Preroll Opportunity Number: 40", "Who Trolled Amber".equals(resultKeys[1]) && 40 == results.get(resultKeys[1]));
        Assert.assertTrue("Show Id: Crime Junkie, Preroll Opportunity Number: 30", "Crime Junkie".equals(resultKeys[2]) && 30 == results.get(resultKeys[2]));
        Assert.assertTrue("Show Id: The Joe Rogan Experience, Preroll Opportunity Number: 10", "The Joe Rogan Experience".equals(resultKeys[3]) && 10 == results.get(resultKeys[3]));

    }

    private static List<PodcastDownload> extractPodcastsFromFile(String fileName) {
        List<PodcastDownload> podcastDownloadList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = PodcastTest.class.getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();

            while (line != null) {
                podcastDownloadList.add(mapper.readValue(line, PodcastDownload.class));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return podcastDownloadList;
    }
}

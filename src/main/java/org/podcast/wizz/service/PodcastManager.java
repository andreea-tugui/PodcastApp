package org.podcast.wizz.service;

import org.podcast.wizz.model.Opportunity;
import org.podcast.wizz.model.PodcastDownload;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class PodcastManager {

    public Entry<String, Integer> extractMostDownloadedPodcastFromCity(List<PodcastDownload> podcasts, String city) {
        Map<String, Integer> results = new HashMap<>();
        for (PodcastDownload podcast : podcasts) {
            if (city.equals(podcast.getCity())){

                String showId = podcast.getDownloadIdentifier().getShowId();

                if (results.containsKey(showId)) {
                    results.put(showId, results.get(showId) + 1);
                } else {
                    results.put(showId, 1);
                }
            }

        }

        int maxvalue = 0;
        Entry<String, Integer> resultKey = null;

        for (Entry<String, Integer> key : results.entrySet()) {
            if (results.get(key.getKey()) > maxvalue) {
                maxvalue = results.get(key.getKey());
                resultKey = key;
            }
        }

        return resultKey;
    }


    public Entry<String, Integer> extractTopDevice(List<PodcastDownload> podcasts) {
        Map<String, Integer> results = new HashMap<>();
        for (PodcastDownload podcast : podcasts) {
            String deviceType = podcast.getDeviceType();

            if (results.containsKey(deviceType)) {
                results.put(deviceType, results.get(deviceType) + 1);
            } else {
                results.put(deviceType, 1);
            }

        }

        int maxvalue = 0;
        Entry<String, Integer> resultKey = null;

        for (Entry<String, Integer> key : results.entrySet()) {
            if (results.get(key.getKey()) > maxvalue) {
                maxvalue = results.get(key.getKey());
                resultKey = key;
            }
        }

        return resultKey;
    }

    public LinkedHashMap<String, Long> showIdPrerollCount(List<PodcastDownload> podcasts) {
        Map<String, Long> results = new HashMap<>();

        for (PodcastDownload podcast : podcasts) {
            String showId = podcast.getDownloadIdentifier().getShowId();

            long prerollCount =  podcast.getOpportunities().stream()
                                   .map(Opportunity::getPositionUrlSegments)
                                   .filter(s -> Arrays.asList(s.getAdBreakIndex()).contains("preroll"))
                                   .count();

            if (results.containsKey(showId)) {
                results.put(showId, results.get(showId) + prerollCount);
            } else {
                results.put(showId, prerollCount);
            }
        }

        return results.entrySet().stream()
                .sorted(Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}

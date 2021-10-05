package com.example.covidtracker.services;

import com.example.covidtracker.models.LocationStatistics;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {
    private static final String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStatistics> allStats=new ArrayList<LocationStatistics>();

    @PostConstruct
    @Scheduled(cron = "* * * 1 * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStatistics> newStats=new ArrayList<LocationStatistics>();

        HttpClient client=HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        StringReader csvReader=new StringReader(httpResponse.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            LocationStatistics locationStatistic=new LocationStatistics();

            locationStatistic.setState( record.get("Province/State"));
            locationStatistic.setCountry(record.get("Country/Region"));
            locationStatistic.setLatestTotalCases(Integer.parseInt(record.get(record.size()-1)));
            locationStatistic.setTotalCasesYesterday(Integer.parseInt(record.get(record.size()-1))-Integer.parseInt(record.get(record.size()-2)));

            newStats.add(locationStatistic);
        }
        this.allStats=newStats;
    }

    public List<LocationStatistics> getAllStats() {
        return allStats;
    }


}

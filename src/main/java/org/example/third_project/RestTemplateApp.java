package org.example.third_project;

import org.example.third_project.DTO.MeasurementDTO;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateApp {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String urlForSensorRegistration = "http://localhost:8080/sensors/registration";

        String urlForMeasurementAdding = "http://localhost:8080/measurements/add";

        String urlForMeasurementGetting = "http://localhost:8080/measurements";

        //создали сенсор
//        Map<String,String> jsonToSend1 = new HashMap<>();
//        jsonToSend1.put("name","RestServiceSensor");
//        HttpEntity<Map<String,String>> request1 = new HttpEntity<>(jsonToSend1);
//        String response1 = restTemplate.postForObject(urlForSensorRegistration, request1, String.class);
//        System.out.println(response1+"\n");


        //1000 запросов со случайными температурами и дождями


//        Map<String, Object> jsonToSend2;
//        HttpEntity<Map<String,Object>> request2;
//        Sensor sensor = new Sensor("RestServiceSensor");
//        for (int i = 0; i<60; i++){
//            jsonToSend2 = new HashMap<>();
//            jsonToSend2.put("value", String.valueOf(ThreadLocalRandom.current().nextDouble(-79, 79)));
//            jsonToSend2.put("raining", String.valueOf(ThreadLocalRandom.current().nextBoolean()));
//            jsonToSend2.put("sensor", sensor);
//            request2 = new HttpEntity<>(jsonToSend2);
//            String response2 = restTemplate.postForObject(urlForMeasurementAdding,request2, String.class);
//            System.out.println(response2);
//        }


        //Получаем это все с сервера


        MeasurementDTO[] resultList;
        HttpEntity<String> request3 = new HttpEntity<>("");
        resultList = restTemplate.getForObject(urlForMeasurementGetting, MeasurementDTO[].class);
        float[] temp = new float[resultList.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = resultList[i].getValue();
        }
        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(800)
                .title("График температур")
                .xAxisTitle("Замеры")
                .yAxisTitle("Температура")
                .build();
        chart.addSeries("График температур", temp);

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        new SwingWrapper<>(chart).displayChart();

    }
}
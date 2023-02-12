import core.Line;
import core.Station;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class RouteCalcTest {
    StationIndex stationIndex = new StationIndex();
    RouteCalculator routeCalculator;
    List<Station> connection1, connection2;
    List<Station> stations;

    @BeforeEach
    public void setUp()
    {
        stations = new ArrayList<>();
        connection1 = new ArrayList<>();
        connection2 = new ArrayList<>();

        Line line1 = new Line(1, "Действующая");
        Line line2 = new Line(2, "Западно-Восточная");
        Line line3 = new Line(3, "Северно-Южная");

        Station station1_1 = new Station("Проспект Космонавтов", line1);
        Station station1_2 = new Station("Уралмаш", line1);
        Station station1_3 = new Station("Машиностроителей", line1);
        Station station1_4 = new Station("Уральская", line1);
        Station station1_5 = new Station("Динамо", line1);
        Station station1_6 = new Station("Площадь 1905 года", line1);
        Station station1_7 = new Station("Геологическая", line1);
        Station station1_8 = new Station("Чкаловская", line1);
        Station station1_9 = new Station("Ботаническая", line1);

        Station station2_1 = new Station("Верх-Исетская", line2);
        Station station2_2 = new Station("Площадь 1905 года", line2);
        Station station2_3 = new Station("Оперный театр", line2);
        Station station2_4 = new Station("Каменные палатки", line2);

        Station station3_1 = new Station("Калиновская", line3);
        Station station3_2 = new Station("Оперный театр", line3);
        Station station3_3 = new Station("Геологическая", line3);
        Station station3_4 = new Station("Волгоградская", line3);

        stations.add(station1_1); //0
        stations.add(station1_2); //1
        stations.add(station1_3); //2
        stations.add(station1_4); //3
        stations.add(station1_5); //4
        stations.add(station1_6); //5
        stations.add(station1_7); //6
        stations.add(station1_8); //7
        stations.add(station1_9); //8
        stations.add(station2_1); //9
        stations.add(station2_2); //10
        stations.add(station2_3); //11
        stations.add(station2_4); //12
        stations.add(station3_1); //13
        stations.add(station3_2); //14
        stations.add(station3_3); //15
        stations.add(station3_4); //16

        line1.addStation(station1_1);
        line1.addStation(station1_2);
        line1.addStation(station1_3);
        line1.addStation(station1_4);
        line1.addStation(station1_5);
        line1.addStation(station1_6);
        line1.addStation(station1_7);
        line1.addStation(station1_8);
        line1.addStation(station1_9);

        line2.addStation(station2_1);
        line2.addStation(station2_2);
        line2.addStation(station2_3);
        line2.addStation(station2_4);

        line3.addStation(station3_1);
        line3.addStation(station3_2);
        line3.addStation(station3_3);
        line3.addStation(station3_4);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(station1_1);
        stationIndex.addStation(station1_2);
        stationIndex.addStation(station1_3);
        stationIndex.addStation(station1_4);
        stationIndex.addStation(station1_5);
        stationIndex.addStation(station1_6);
        stationIndex.addStation(station1_7);
        stationIndex.addStation(station1_8);
        stationIndex.addStation(station1_9);
        stationIndex.addStation(station2_1);
        stationIndex.addStation(station2_2);
        stationIndex.addStation(station2_3);
        stationIndex.addStation(station2_4);
        stationIndex.addStation(station3_1);
        stationIndex.addStation(station3_4);

        connection1.add(station1_6);
        connection1.add(station2_2);
        connection2.add(station2_3);
        connection2.add(station3_2);
        stationIndex.addConnection(connection1);
        stationIndex.addConnection(connection2);

        routeCalculator = new RouteCalculator(stationIndex);
    }

    @Test
    @DisplayName("Получить маршрут вдоль одной линии")
    public void testGetRouteOnTheLine() {
        List<Station> actual = routeCalculator.getShortestRoute(stations.get(0), stations.get(3));
        List<Station> expected = List.of(stations.get(0), stations.get(1), stations.get(2), stations.get(3));
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Получить маршрут с одним переходом")
    public void testGetRouteWithOneConnection() {
        List<Station> actual = routeCalculator.getShortestRoute(stations.get(6), stations.get(11));
        List<Station> expected = List.of(stations.get(6), stations.get(5), stations.get(10), stations.get(11));
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Получить маршрут с двумя переходами")
    public void testGetRouteWithTwoConnections() {
        List<Station> actual = routeCalculator.getShortestRoute(stations.get(4), stations.get(13));
        List<Station> expected = List.of(stations.get(4), stations.get(5), stations.get(10), stations.get(11),
                                        stations.get(14), stations.get(13));
        Assertions.assertEquals(actual, expected);
    }
    
    @Test
    @DisplayName("Проверить длительность перемещения")
    public void testCalculateDuration() {
        Double actual = 5.0;
        Double expected = RouteCalculator.calculateDuration(List.of(stations.get(1), stations.get(2), stations.get(3)));
        Assertions.assertEquals(actual, expected);
    }
}

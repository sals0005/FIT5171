package rockets.mining;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.dataaccess.neo4j.Neo4jDAO;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.Rocket;
import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RocketMinerUnitTest {
    Logger logger = LoggerFactory.getLogger(RocketMinerUnitTest.class);

    private DAO dao;
    private RocketMiner miner;
    private List<Rocket> rockets;
    private List<LaunchServiceProvider> lsps;
    private List<Launch> launches;

    @BeforeEach
    public void setUp() {
        dao = mock(Neo4jDAO.class);
        miner = new RocketMiner(dao);
        rockets = Lists.newArrayList();


         LaunchServiceProvider lsp1 = new LaunchServiceProvider("ULA", 1990, "USA");
         LaunchServiceProvider lsp2 = new LaunchServiceProvider("SpaceX", 2002, "USA");
         LaunchServiceProvider lsp3 = new LaunchServiceProvider("ESA", 1975, "Europe");

        lsps = new ArrayList<LaunchServiceProvider>();
         lsps.add(lsp1);
         lsps.add(lsp2);
         lsps.add(lsp3);

        // index of lsp of each rocket
        int[] lspIndex = new int[]{0, 1, 1, 1, 1, 2 , 2 , 2 , 2 , 2};

        // 5 rockets
        for (int i = 0; i < 10; i++) {
            rockets.add(new Rocket("rocket_" + i, "USA", lsps.get(lspIndex[i])));
        }
        // month of each launch
        int[] months = new int[]{1, 6, 4, 3, 4, 11, 6, 5, 12, 5};

        // index of rocket of each launch
        int[] rocketIndex = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9, 9};

        BigDecimal bg1 = new BigDecimal("2000");
        BigDecimal bg2 = new BigDecimal("3000");
        BigDecimal bg3 = new BigDecimal("5000");
        BigDecimal bg4 = new BigDecimal("7000");
        BigDecimal bg5 = new BigDecimal("9000");

        List<BigDecimal> bgs = new ArrayList<BigDecimal>();
        bgs.add(bg1);
        bgs.add(bg2);
        bgs.add(bg3);
        bgs.add(bg4);
        bgs.add(bg5);

        int[] bgIndex = new int[]{0, 0, 1, 2, 3, 3, 3, 3, 3, 4 };

        // 10 launches
        launches = IntStream.range(0, 10).mapToObj(i -> {
            logger.info("create " + i + " launch in month: " + months[i]);
            Launch l = new Launch();
            l.setLaunchDate(LocalDate.of(2017, months[i], 1));
            l.setLaunchVehicle(rockets.get(rocketIndex[i]));
            l.setLaunchSite("VAFB");
            l.setLaunchServiceProvider(lsps.get(lspIndex[i]));
            l.setOrbit("LEO");
            l.setLaunchOutcome(Launch.LaunchOutcome.SUCCESSFUL);
            l.setPrice(bgs.get(bgIndex[i]));
            spy(l);
            return l;
        }).collect(Collectors.toList());
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void shouldReturnTopMostRecentLaunches(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<Launch> sortedLaunches = new ArrayList<>(launches);
        sortedLaunches.sort((a, b) -> -a.getLaunchDate().compareTo(b.getLaunchDate()));
        List<Launch> loadedLaunches = miner.mostRecentLaunches(k);
        assertEquals(k, loadedLaunches.size());
        assertEquals(sortedLaunches.subList(0, k), loadedLaunches);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2})
    public void shouldReturnTopMostLaunchedRockets(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<Rocket> rockets = miner.mostLaunchedRockets(k);
        assertEquals(k, rockets.size());
        assertEquals(rockets.get(0).getName(),"rocket_9");
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2})
    public void shouldReturnTopMostReliableLaunchServiceProviders(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<LaunchServiceProvider> lsp = miner.mostReliableLaunchServiceProviders(k);
        assertEquals(k, lsp.size());
        assertEquals(lsp.get(0).getName(),"ESA");
    }

    @Test
    public void shouldReturnTopMostDominantCountry() {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        String country = miner.dominantCountry("LEO");
        assertEquals(country,"USA");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void shouldReturnTopMostExpensiveLaunches(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<Launch> launchEX = miner.mostExpensiveLaunches(k);
        assertEquals(k, launchEX.size());
        BigDecimal b = new BigDecimal("9000");
        assertEquals(launchEX.get(0).getPrice(),b);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void shouldReturnHighestRevenueLaunchServiceProvider(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<LaunchServiceProvider> lsp = miner.highestRevenueLaunchServiceProviders(k,2017);
        assertEquals(k, lsp.size());
        assertEquals(lsp.get(0).getName(),"ESA");
    }
}
package rockets.mining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.Rocket;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RocketMiner {
    private static Logger logger = LoggerFactory.getLogger(RocketMiner.class);

    private DAO dao;

    public RocketMiner(DAO dao) {
        this.dao = dao;
    }

    /**
     * TODO: to be implemented & tested!
     * Returns the top-k most active rockets, as measured by number of completed launches.
     *
     * @param k the number of rockets to be returned.
     * @return the list of k most active rockets.
     */
    public List<Rocket> mostLaunchedRockets(int k)
    {
        logger.info("Top Most :" + k + " rockets");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        Collection<Rocket> rockets = new ArrayList<Rocket>();
        Iterator<Launch> i = launches.iterator();
        while (i.hasNext()) {
            Launch a = i.next();
            rockets.add(a.getLaunchVehicle());
        }
        Map<Rocket, Integer> hm = new HashMap<Rocket,Integer>();

        for (Rocket m : rockets) {
            Integer j = hm.get(m);
            hm.put(m, (j == null) ? 1 : j + 1);
        }
        Map<Rocket, Integer> sorted = hm
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        List<Rocket> list = new ArrayList<Rocket>(sorted.keySet());

        return list.stream().limit(k).collect(Collectors.toList());
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the top-k most reliable launch service providers as measured
     * by percentage of successful launches.
     *
     * @param k the number of launch service providers to be returned.
     * @return the list of k most reliable ones.
     */
    public List<LaunchServiceProvider> mostReliableLaunchServiceProviders(int k)
    {
        logger.info("Most Reliable Launch Service Providers :" + k + " Measured by percentage of Successful launches");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        Collection<LaunchServiceProvider> lsp = new ArrayList<LaunchServiceProvider>();
        Iterator<Launch> i = launches.iterator();
        while (i.hasNext()) {
            Launch a = i.next();
            if (a.getLaunchOutcome().equals(Launch.LaunchOutcome.SUCCESSFUL) && a.getLaunchServiceProvider()!= null)
            {
                lsp.add(a.getLaunchServiceProvider());
            }
        }
        Map<LaunchServiceProvider, Integer> hm = new HashMap<LaunchServiceProvider, Integer>();

        for (LaunchServiceProvider m : lsp) {
            Integer j = hm.get(m);
            hm.put(m, (j == null) ? 1 : j + 1);
        }
        logger.info("size hm :"+hm.size());
        Map<LaunchServiceProvider, Integer> sorted = hm
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
        List<LaunchServiceProvider> list = new ArrayList<LaunchServiceProvider>(sorted.keySet());
        return list.stream().limit(k).collect(Collectors.toList());
    }

    /**
     * <p>
     * Returns the top-k most recent launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most recent launches.
     */
    public List<Launch> mostRecentLaunches(int k) {
        logger.info("find most recent " + k + " launches");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        Comparator<Launch> launchDateComparator = (a, b) -> -a.getLaunchDate().compareTo(b.getLaunchDate());
        return launches.stream().sorted(launchDateComparator).limit(k).collect(Collectors.toList());
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the dominant country who has the most launched rockets in an orbit.
     *
     * @param orbit the orbit
     * @return the country who sends the most payload to the orbit
     */
    public String dominantCountry(String orbit)
    {
        logger.info("Most Dominant Country : Measured by most launched rockets in an orbit");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        Collection<String> country = new ArrayList<String>();
        Iterator<Launch> i = launches.iterator();
        while (i.hasNext()) {
            Launch a = i.next();
            if (a.getLaunchOutcome().equals(Launch.LaunchOutcome.SUCCESSFUL) && a.getOrbit().equals(orbit))
                country.add(a.getLaunchServiceProvider().getCountry());
        }
        Map<String, Integer> hm = new HashMap<String, Integer>();

        for (String m : country) {
            Integer j = hm.get(m);
            hm.put(m, (j == null) ? 1 : j + 1);
        }
        Map<String, Integer> sorted = hm
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
        List<String> list = new ArrayList<String>(sorted.keySet());
        return list.get(0);
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the top-k most expensive launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most expensive launches.
     */
    public List<Launch> mostExpensiveLaunches(int k)
    {
        logger.info("find most expensive " + k + " launches");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        Comparator<Launch> launchDateComparator = (a, b) -> b.getPrice().compareTo(a.getPrice());
        return launches.stream().sorted(launchDateComparator).limit(k).collect(Collectors.toList());
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns a list of launch service provider that has the top-k highest
     * sales revenue in a year.
     *
     * @param k the number of launch service provider.
     * @param year the year in request
     * @return the list of k launch service providers who has the highest sales revenue.
     */
    public List<LaunchServiceProvider> highestRevenueLaunchServiceProviders(int k, int year)
    {
        logger.info("Highest Revenue earning "+k+": Launch Service Provider in year: "+year);
        Collection<Launch> launches = dao.loadAll(Launch.class);
        Iterator<Launch> i = launches.iterator();
        Map<LaunchServiceProvider, BigDecimal> lsp = new HashMap<LaunchServiceProvider, BigDecimal>();
        LaunchServiceProvider ls ;
        while (i.hasNext()) {
            Launch a = i.next();
            if (a.getLaunchDate().getYear() == year)
            {
                ls = a.getLaunchServiceProvider();
                BigDecimal cost = lsp.get(ls);
                lsp.put(ls,(cost == null)? a.getPrice() : cost.add(a.getPrice()));
            }
        }
        Map<LaunchServiceProvider, BigDecimal> sorted = lsp
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
        List<LaunchServiceProvider> list = new ArrayList<LaunchServiceProvider>(sorted.keySet());
        return list.stream().limit(k).collect(Collectors.toList());
    }
}

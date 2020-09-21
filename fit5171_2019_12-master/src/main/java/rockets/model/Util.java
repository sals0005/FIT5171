package rockets.model;

import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Util {

    public static void isPasswordValid(String password) {
        // Validate that password greater or equal than 8 and it must contain at least a Capital letter
        if(password.length()<8 ||password.equals(password.toLowerCase())) {
            Validate.isTrue(false, "Password should not be shorter than 8, and must include at least one Capital letter");
        }
    }

    public static void isEmailValid(String email){
        // Validate that email should contain an @ symbol
        if (!email.contains("@")) {
            Validate.isTrue(false,"email format is wrong");
        }
    }

    public static void isValidMass(String str){
        boolean isValid = true;
        // Mass can be enter as an empty string: https://en.wikipedia.org/wiki/Comparison_of_orbital_launch_systems
        if (str.trim().equals("")){
            Validate.isTrue(isValid,"Mass should be an integer greater than 0 and less than 200000");
        } else {
            try {
                // Check if string is an integer
                Integer number = Integer.parseInt(str);
                // Check if integer is less than 1
                if (number < 1 || number > 200000) {
                    isValid = false;
                }
            }   catch (NumberFormatException e) {
                isValid = false;
            }
            Validate.isTrue(isValid,"Mass should be an integer greater than 0 and less than 200000");
        }

    }

    public static void isValidVariation(String family, String variation){
        // validate that variation should contain the family name
        boolean isValid = true;
        if(!variation.contains(family)){
            isValid = false;
        }
        Validate.isTrue(isValid, "Variation name is not valid");
    }

    // Get List of Rockets Sorted (descending) by the Number of Launches
    public static List<Rocket> getSortedRocketsByLaunches (List<Launch> listLaunches){
        Map<Rocket,Integer> mapRockets = new HashMap<>();
        for (Launch launch: listLaunches){
            Rocket rocket = launch.getLaunchVehicle();
            if (mapRockets.containsKey(rocket)){
                int number_launches = mapRockets.get(rocket);
                mapRockets.put(rocket,++number_launches);
            } else {
                mapRockets.put(rocket,1);
            }
        }
        Map<Rocket, Integer> sortedRocket = mapRockets
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
        List<Rocket> topRockets = new ArrayList<>();
        for (Rocket rocket : sortedRocket.keySet()){
            topRockets.add(rocket);
        }
        return topRockets;
    }



    // Get Map of LSP with the Total Revenue Amount for the year

    public static Map<LaunchServiceProvider, BigDecimal> getRevenuePerLspInYear(Collection<Launch> launches, int year){
        // FILTER LAUNCHES PER YEAR
        List<Launch> filteredLaunchList = launches.stream().filter(Launch -> Launch.getLaunchDate().getYear() == year).collect(Collectors.toList());
        Map<LaunchServiceProvider, BigDecimal> mapByLsp = new HashMap<>();
        for (Launch l : filteredLaunchList) {
            if (mapByLsp.containsKey(l.getLaunchServiceProvider())){
                BigDecimal bd = mapByLsp.get(l.getLaunchServiceProvider()).add(l.getPrice());
                mapByLsp.put(l.getLaunchServiceProvider(),bd);
            } else {
                BigDecimal tmp = l.getPrice();
                mapByLsp.put(l.getLaunchServiceProvider(),tmp);
            }
        }
        return mapByLsp;
    }


    // Get List of LSP Sorted (descending) by the Revenue Amount
    public static List<LaunchServiceProvider> getSortedLspByRevenue(Map<LaunchServiceProvider,BigDecimal> mapUnsorted){
        Map<LaunchServiceProvider, BigDecimal> sortedLSP = mapUnsorted
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
        return new ArrayList<>(sortedLSP.keySet());
    }






}

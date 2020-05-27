class Medium1 {
    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> food = new HashSet<>();
        Set<String> table = new HashSet<>();
        for(int i = 0; i<orders.size(); i++){
            food.add(orders.get(i).get(2));
            table.add(orders.get(i).get(1));
        }
        List<String> sortedFood = new ArrayList<>(food);
        List<String> sortedTable = new ArrayList<>(table);
        Collections.sort(sortedFood);
        Collections.sort(sortedTable, (a, b) -> Integer.compare(Integer.parseInt(a), Integer.parseInt(b)));
        // System.out.println(sortedFood);
        // System.out.println(sortedTable);
        Map<String, Map<String, Integer>> dp = new HashMap<>();
        for(int i = 0; i<orders.size(); i++){
            if(dp.containsKey(orders.get(i).get(1))){
                Map<String, Integer> curr = dp.get(orders.get(i).get(1));
                if(curr.containsKey(orders.get(i).get(2))){
                    curr.put(orders.get(i).get(2), curr.get(orders.get(i).get(2)) + 1);
                }else{
                    curr.put(orders.get(i).get(2), 1);
                }
            }else{
                Map<String, Integer> curr = new HashMap<>();
                curr.put(orders.get(i).get(2), 1);
                dp.put(orders.get(i).get(1), curr);
            }
        }
        List<List<String>> ret = new ArrayList<>();
        for(int i = 0; i<sortedTable.size(); i++){
            List<String> currTable = new ArrayList<>();
            String thisTable = sortedTable.get(i);
            currTable.add(thisTable);
            Map<String, Integer> map = dp.get(thisTable);
            for(int j = 0; j<sortedFood.size(); j++){
                Integer value = map.get(sortedFood.get(j));
                currTable.add(value == null ? "0" : value + "");
            }
            ret.add(currTable);
        }
        sortedFood.add(0, "Table");
        ret.add(0, sortedFood);
        // System.out.println(ret);
        return ret;
    }
}

class Medium2 {
    public int minNumberOfFrogs(String croakOfFrogs) {
        if(croakOfFrogs.length() % 5 != 0) return -1;
        int max = 0;
        int stack = 0;
        for(int i = 0; i<croakOfFrogs.length(); i++){
            if(croakOfFrogs.charAt(i) == 'c') stack ++;
            if(croakOfFrogs.charAt(i) == 'k') stack --;
            max = Math.max(max, stack);
        }
        if(works(croakOfFrogs)) return max;
        else return -1;
    }
    public static boolean works(String s){
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++){
            if(!arrayWorks(count)) return false;
            count[(int) s.charAt(i) - 97]++;
        }
        int check = s.length()/5;
        for(int i = 0; i < 26; i++){
            if(count[i] % check != 0) return false;
        }
        return true;
    }
    public static boolean arrayWorks(int[] arr){
        int[] check = {2, 17, 14, 0, 10};
        for(int i = 0; i < check.length - 1; i++){
            if(arr[check[i]] < arr[check[i + 1]]) return false;
        }
        return true;
    }
}
